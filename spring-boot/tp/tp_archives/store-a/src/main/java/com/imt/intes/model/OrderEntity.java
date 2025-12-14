package com.imt.intes.model;

import com.imt.intes.model.id.OrderId;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class OrderEntity {

    @EmbeddedId
    private OrderId id;
    private int quantity;
    private LocalDate deliveryDate;

    @MapsId("buyerLogin")
    @ManyToOne
    private UserEntity buyer;
    @MapsId("itemId")
    @ManyToOne
    private ItemEntity item;

}
