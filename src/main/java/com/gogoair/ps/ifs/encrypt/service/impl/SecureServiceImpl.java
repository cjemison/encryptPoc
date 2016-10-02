package com.gogoair.ps.ifs.encrypt.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gogoair.ps.ifs.encrypt.model.DataInfo;
import com.gogoair.ps.ifs.encrypt.service.SecureService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by cjemison on 9/30/16.
 */
@Service
public class SecureServiceImpl implements SecureService {
  private static final Logger logger = LoggerFactory.getLogger(SecureServiceImpl.class);
  private final ObjectMapper objectMapper = new ObjectMapper();

  @Override
  public Optional<DataInfo> encrypt(final DataInfo dataInfo) {
    Optional<DataInfo> dataInfoOptional = Optional.empty();
    if (dataInfoOptional != null) {
      if (!dataInfo.getUxdId().trim().equals("")
            && !dataInfo.getByPass().trim().equals("")) {

        final Map<String, Object> map = new HashMap<>();
        map.put("uxdId", dataInfo.getUxdId().trim());
        map.put("byPass", dataInfo.getByPass().trim());
        map.put("date", dataInfo.getDate());

        try {
          final String json = objectMapper.writeValueAsString(map).trim();
          final SecretKeySpec skeySpec = new SecretKeySpec(dataInfo.getUxdId().getBytes(), "AES");
          final Cipher cipher = Cipher.getInstance("AES");
          cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
          final byte[] encryptedBytes = cipher.doFinal(json.getBytes());
          dataInfo.setData(Base64.getEncoder().encodeToString(encryptedBytes));
          dataInfoOptional = Optional.of(dataInfo);
        } catch (Exception e) {
          logger.warn("", e);
        }
      }
    }
    return dataInfoOptional;
  }

  @Override
  public Optional<DataInfo> decrypt(final DataInfo dataInfo) {
    Optional<DataInfo> dataInfoOptional = Optional.empty();
    if (dataInfo != null && !dataInfo.getUxdId().trim().equals("")) {
      logger.debug("DataInfo: {}", dataInfo);
      if (dataInfo.getData() != null && !dataInfo.getData().trim().equals("")) {
        final byte[] encryptedBytes = Base64.getDecoder().decode(dataInfo.getData().getBytes());
        final SecretKeySpec skeySpec = new SecretKeySpec(dataInfo.getUxdId().getBytes(), "AES");
        try {
          final Cipher cipher = Cipher.getInstance("AES");
          cipher.init(Cipher.DECRYPT_MODE, skeySpec);
          final byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
          final String json = new String(decryptedBytes);
          final DataInfo di = objectMapper.readValue(json, DataInfo.class);
          if (di != null) {
            di.setData(dataInfo.getData());
          }
          dataInfoOptional = Optional.ofNullable(di);
        } catch (Exception e) {
          logger.warn("", e);
        }
      }
    }
    return dataInfoOptional;
  }
}
