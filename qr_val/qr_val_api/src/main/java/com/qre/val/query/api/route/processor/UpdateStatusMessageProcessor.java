package com.qre.val.query.api.route.processor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qre.val.dto.qr.TicketUpdateStatusRequest;
import com.qre.val.query.api.service.TicketService;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class UpdateStatusMessageProcessor implements Processor {

    /**
     * logger
     */
    private Logger logger = LoggerFactory.getLogger(UpdateStatusMessageProcessor.class);

    /**
     * ticketService
     */
    private final TicketService ticketService;

    /**
     * objectMapper
     */
    private final ObjectMapper objectMapper;

    public UpdateStatusMessageProcessor(TicketService ticketService, ObjectMapper objectMapper) {
        this.ticketService = ticketService;
        this.objectMapper = objectMapper;
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

            TicketUpdateStatusRequest ticketUpdateStatusRequest =
                    objectMapper.readValue(strMessage, TicketUpdateStatusRequest.class);
            ticketService.updateTicketStatus(ticketUpdateStatusRequest);


        } catch (Exception ex) {
            logger.error("Error processing message", ex);
            throw new Exception("Failed to process message", ex);
        }
    }

}
