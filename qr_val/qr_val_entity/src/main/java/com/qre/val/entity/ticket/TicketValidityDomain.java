package com.qre.val.entity.ticket;

import com.qre.val.entity.base.AbstractPersistableEntity;
import com.qre.val.entity.base.DbFieldName;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = DbFieldName.TICKET_VALIDITY_DOMAIN)
public class TicketValidityDomain extends AbstractPersistableEntity<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = DbFieldName.VALIDITY_DOMAIN_ID)
    private int validityDomainId;

    @Column(name = DbFieldName.VALIDITY_DOMAIN)
    private String validityDomain;

    @Override
    public Integer getId() {
        return validityDomainId;
    }
}
