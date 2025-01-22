package com.qre.val.query.api.service.impl;

import com.qre.cmel.cache.service.HazelcastCacheService;
import com.qre.cmel.exception.EntryExitMismatchException;
import com.qre.cmel.exception.InvalidJourneyTypeException;
import com.qre.cmel.exception.InvalidTicketException;
import com.qre.cmel.security.component.Crypto;
import com.qre.cmel.security.component.DigitalSignature;
import com.qre.val.dao.ticket.JourneyDetailsRepository;
import com.qre.val.dao.ticket.TicketMasterRepository;
import com.qre.val.dto.qr.TicketDataRequest;
import com.qre.val.dto.qr.TicketUpdateStatusRequest;
import com.qre.val.entity.ticket.*;
import com.qre.val.query.api.common.JourneyTypeEnum;
import com.qre.val.query.api.common.TicketStatusEnum;
import com.qre.val.query.api.config.ApplicationProperties;
import com.qre.val.query.api.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
@Transactional
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {

    private final Logger logger = LoggerFactory.getLogger(TicketServiceImpl.class);

    private final TicketMasterRepository ticketMasterRepository;

    private final JourneyDetailsRepository journeyDetailsRepository;

    @Override
    public void updateTicketStatus(TicketUpdateStatusRequest request) {

        Optional<TicketMaster> ticketMasterOpt = ticketMasterRepository.findAllBySerialNumber(request.getSerialNumber());
        if (ticketMasterOpt.isEmpty()) {
            throw new InvalidTicketException("Invalid ticket[" + request.getSerialNumber() + "]");
        }

        TicketMaster ticketMaster = ticketMasterOpt.get();
        Optional<JourneyDetails> journeyDetailsOpt = ticketMaster.getJourneyDetails().stream().findFirst();
        if (journeyDetailsOpt.isEmpty()) {
            throw new InvalidTicketException("Invalid journey of ticket [" + request.getSerialNumber() + "]");
        }

        JourneyDetails journeyDetails = journeyDetailsOpt.get();
        if (request.getStatus().equals(TicketStatusEnum.ENTRY.getValue())) {
            if (!journeyDetails.getStatus().equals(TicketStatusEnum.ACTIVE.getValue())) {
                throw new EntryExitMismatchException("Ticket [" + request.getSerialNumber() + "] is not available for entry");
            }
            journeyDetails.setEntryDateTime(new Date(request.getEntryDateTime()));
            journeyDetails.setStatus(request.getStatus());
            journeyDetails.setUpdatedDatetime(new Date());
        } else if (request.getStatus().equals(TicketStatusEnum.EXIT.getValue())) {
            if (!journeyDetails.getStatus().equals(TicketStatusEnum.ENTRY.getValue())) {
                throw new EntryExitMismatchException("Ticket [" + request.getSerialNumber() + "] is not available for exit");
            }
            journeyDetails.setExitDateTime(new Date(request.getExitDateTime()));
            journeyDetails.setStatus(request.getStatus());
            journeyDetails.setUpdatedDatetime(new Date());
        } else {
            throw new EntryExitMismatchException("Invalid ticket status: " + request.getStatus());
        }
        journeyDetailsRepository.save(journeyDetails);
    }

    @Override
    public void purchaseTicket(TicketDataRequest request) throws Exception {

        List<JourneyDetails> journeyDetailsList = new ArrayList<>();
        journeyDetailsList.add(JourneyDetails.builder()
                .status(request.getJourneyDetails().get(0).getStatus())
                .departurePoint(request.getJourneyDetails().get(0).getDeparturePoint())
                .arrivalPoint(request.getJourneyDetails().get(0).getArrivalPoint())
                .build());

        TicketMaster ticketMaster = TicketMaster.builder()
                .ticketType(request.getTicketType())
                .journeyTypeId(request.getJourneyTypeId())
                .serialNumber(request.getSerialNumber())
                .issuerId(request.getIssuerId())
                .creatorId(5) //tg_id
                .groupSize(request.getGroupSize())
                .phoneNo(request.getPhoneNo())
                .email(request.getEmail())
                .creationDateTime(new Date(request.getCreationDateTime()))
                .effectiveDateTime(new Date(request.getEffectiveDateTime()))
                .operatorId(request.getOperatorId()) // transport operator
                .validityDomain(1) // train/bus/flight/all
                .validityPeriod(request.getValidityPeriod())
                .transactionData(TransactionData.builder()
                        .paymentRefNo(request.getTransactionData().getPaymentRefNo())
                        .currency(request.getTransactionData().getCurrency())
                        .amount((long) request.getTransactionData().getAmount())
                        .build())
                .journeyDetails(journeyDetailsList)
                .additionalInfo(AdditionalInfo.builder().build())
                .build();
        ticketMaster.setCreatedDatetime(new Date());
        ticketMasterRepository.save(ticketMaster);
    }

}
