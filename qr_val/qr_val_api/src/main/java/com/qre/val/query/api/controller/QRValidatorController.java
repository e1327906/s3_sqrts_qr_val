package com.qre.val.query.api.controller;

import com.qre.val.dto.base.APIResponse;
import com.qre.val.dto.qr.ValidationRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface QRValidatorController {

    ResponseEntity<APIResponse> validate(@RequestBody ValidationRequest request) throws Exception;
    ResponseEntity<APIResponse> getTicketDetail(@RequestBody ValidationRequest request) throws Exception;
}
