package com.imt.intes.repository;

import com.imt.intes.model.OrderEntity;
import com.imt.intes.model.id.OrderId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends CrudRepository<OrderEntity, OrderId> {
}
