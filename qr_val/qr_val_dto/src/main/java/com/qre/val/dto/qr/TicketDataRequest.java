package com.qre.val.dto.qr;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TicketDataRequest {

    private int ticketType;
    private int journeyTypeId;
    private String serialNumber;
    private int groupSize;
    private int creatorId;
    private int validityPeriod;
    private int operatorId;
    private long effectiveDateTime;
    private long creationDateTime;
    private int issuerId;
    private int validityDomain;
    private String phoneNo;
    private String email;
    private List<JourneyDetail> journeyDetails;
    private TransactionData transactionData;
    private AdditionalInfo additionalInfo;
}
