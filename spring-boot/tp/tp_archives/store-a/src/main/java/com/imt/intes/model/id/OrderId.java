package com.imt.intes.model.id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.time.LocalDateTime;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
public class OrderId implements Serializable {

    private static final long serialVersionUID = 2012207137846303529L;
    private LocalDateTime creationDatetime;
    private String buyerLogin;
    private Long itemId;

}
