package com.qre.val.query.api.service;

import com.qre.val.dto.qr.ValidationRequest;
import com.qre.val.query.api.common.QRData;

public interface QRValidatorService{

    void validate(ValidationRequest request) throws Exception;
    QRData getTicketDetail(ValidationRequest request) throws Exception;

}
