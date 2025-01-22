package com.qre.val.dto.qr;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JourneyDetail {
    private String journeyId;
    private int departurePoint;
    private int arrivalPoint;
    private int status;
}