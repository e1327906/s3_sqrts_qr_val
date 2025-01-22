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
@Table(name = DbFieldName.JOURNEY_TYPES)
public class JourneyType extends AbstractPersistableEntity<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = DbFieldName.JOURNEY_TYPE_ID)
    private int journeyTypeId;

    @Column(name = DbFieldName.JOURNEY_TYPE)
    private String journeyType;

    @Override
    public Integer getId() {
        return journeyTypeId;
    }
}
