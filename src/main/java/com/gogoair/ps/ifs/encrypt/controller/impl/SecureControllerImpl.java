package com.gogoair.ps.ifs.encrypt.controller.impl;

import com.gogoair.ps.ifs.encrypt.controller.SecureController;
import com.gogoair.ps.ifs.encrypt.model.DataInfo;
import com.gogoair.ps.ifs.encrypt.service.SecureService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * Created by cjemison on 9/30/16.
 */
@RestController
@RequestMapping(value = "/v1", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class SecureControllerImpl implements SecureController {
  private static final Logger logger = LoggerFactory.getLogger(SecureControllerImpl.class);
  private final SecureService secureService;

  @Autowired
  public SecureControllerImpl(final SecureService secureService) {
    this.secureService = secureService;
  }

  @Override
  @RequestMapping(value = "/validate/encrypt", method = RequestMethod.POST,
        consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public ResponseEntity<?> encrypt(@RequestBody final DataInfo dataInfo) {
    Optional<DataInfo> dataInfoOptional = secureService.encrypt(dataInfo);
    if (dataInfoOptional.isPresent()) {
      return ResponseEntity.ok(dataInfoOptional.get());
    } else {
      return ResponseEntity.badRequest().build();
    }
  }

  @Override
  @RequestMapping(value = "/validate", method = RequestMethod.POST,
        consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public ResponseEntity<?> decrypt(@RequestBody final DataInfo dataInfo) {
    Optional<DataInfo> dataInfoOptional = secureService.decrypt(dataInfo);
    if (dataInfoOptional.isPresent()) {
      return ResponseEntity.ok(dataInfoOptional.get());
    } else {
      return ResponseEntity.badRequest().build();
    }
  }
}
