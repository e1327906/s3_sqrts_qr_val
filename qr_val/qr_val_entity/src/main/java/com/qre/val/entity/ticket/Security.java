package com.qre.val.entity.ticket;

import com.qre.val.entity.base.DbFieldName;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Security implements Serializable {

    @Column(name = DbFieldName.DIGITAL_SIGNATURE, columnDefinition = "text")
    private String digitalSignature;
}
