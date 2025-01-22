/*
 * Copyright &copy MSI Global Pte Ltd (Singapore) 2022. All rights reserved.
 * The contents of this document are property of MSI Global Pte Ltd (Singapore).
 * No part of this work may be reproduced or transmitted in any form or by any means,
 * except as permitted by written license agreement with the MSI Global Pte Ltd
 * (Singapore).
 */
package com.qre.val.query.api.route;

import com.qre.val.query.api.route.processor.ExceptionHandlerProcessor;
import com.qre.val.query.api.route.processor.PurchaseMessageProcessor;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class PurchaseMessageRouter extends RouteBuilder {

    /**
     * MessageProcessor
     */
    private final PurchaseMessageProcessor purchaseMessageProcessor;

    /**
     * exceptionHandlerProcessor
     */
    private final ExceptionHandlerProcessor exceptionHandlerProcessor;

    /**
     *
     * @param purchaseMessageProcessor
     * @param exceptionHandlerProcessor
     */
    public PurchaseMessageRouter(PurchaseMessageProcessor purchaseMessageProcessor,
                                 ExceptionHandlerProcessor exceptionHandlerProcessor) {
        this.purchaseMessageProcessor = purchaseMessageProcessor;
        this.exceptionHandlerProcessor = exceptionHandlerProcessor;
    }

    @Override
    public void configure() throws Exception {
        from("activemq:{{app.consumer.purchase.queue}}")
                .autoStartup(true)
                .routeId("PURCHASE_MESSAGE_ROUTE")
                    .log(LoggingLevel.INFO, "Received message from ActiveMQ : ${body}")
                .doTry()
                    .process(purchaseMessageProcessor)
                    .log(LoggingLevel.INFO, "Process completed")
                .doCatch(Exception.class)
                    .log(LoggingLevel.ERROR, "Message failed to process" + exceptionMessage())
                    .process(exceptionHandlerProcessor)
                .end();
    }

}
