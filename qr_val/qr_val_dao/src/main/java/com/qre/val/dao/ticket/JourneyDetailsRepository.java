package com.qre.val.dao.ticket;

import com.qre.val.entity.ticket.JourneyDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface JourneyDetailsRepository extends JpaRepository<JourneyDetails, UUID> {
}
