package com.gogoair.ps.ifs.encrypt.service;

import com.gogoair.ps.ifs.encrypt.model.DataInfo;

import java.util.Optional;

/**
 * Created by cjemison on 9/30/16.
 */
public interface SecureService {
  Optional<DataInfo> encrypt(final DataInfo dataInfo);

  Optional<DataInfo> decrypt(final DataInfo dataInfo);
}
