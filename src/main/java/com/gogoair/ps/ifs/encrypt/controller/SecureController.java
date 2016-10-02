package com.gogoair.ps.ifs.encrypt.controller;

import com.gogoair.ps.ifs.encrypt.model.DataInfo;

import org.springframework.http.ResponseEntity;

/**
 * Created by cjemison on 9/30/16.
 */
public interface SecureController {

  ResponseEntity<?> encrypt(final DataInfo dataInfo);

  ResponseEntity<?> decrypt(final DataInfo dataInfo);
}
