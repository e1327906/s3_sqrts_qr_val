package com.qre.val.query.api.service;

import com.qre.val.dto.qr.TicketDataRequest;
import com.qre.val.dto.qr.TicketUpdateStatusRequest;

public interface TicketService{

    void updateTicketStatus(TicketUpdateStatusRequest request);

    void purchaseTicket(TicketDataRequest request) throws Exception;
}
