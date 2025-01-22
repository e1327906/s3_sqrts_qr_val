package com.qre.val.query.api.route.processor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qre.val.dto.qr.TicketDataRequest;
import com.qre.val.dto.qr.TicketUpdateStatusRequest;
import com.qre.val.query.api.service.TicketService;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class PurchaseMessageProcessor implements Processor {

    /**
     * logger
     */
    private Logger logger = LoggerFactory.getLogger(PurchaseMessageProcessor.class);

    /**
     * objectMapper
     */
    private final ObjectMapper objectMapper;

    private final TicketService ticketService;

    public PurchaseMessageProcessor(ObjectMapper objectMapper, TicketService ticketService) {
        this.objectMapper = objectMapper;
        this.ticketService = ticketService;
    }

    /**
     * @param exchange
     * @throws Exception
     */
    @Override
    public void process(Exchange exchange) throws Exception {
        try {

            String strMessage = exchange.getIn().getBody(String.class);
            logger.info("Processing message: {}", strMessage);
            //To capture the data from the message
            TicketDataRequest ticketDataRequest = objectMapper.readValue(strMessage, TicketDataRequest.class);
            ticketService.purchaseTicket(ticketDataRequest);

        } catch (Exception ex) {
            logger.error("Error processing message", ex);
            throw new Exception("Failed to process message", ex);
        }
    }

}
