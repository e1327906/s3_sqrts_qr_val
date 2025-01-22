package com.qre.val.dao.ticket;

import com.qre.val.entity.ticket.JourneyType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JourneyTypeRepository extends JpaRepository<JourneyType, Integer> {
}
