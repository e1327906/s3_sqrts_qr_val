package com.qre.val.query.api.service;

import com.qre.val.dto.qr.ValidationRequest;

public interface QRValidatorService{

    void validate(ValidationRequest request) throws Exception;
}
