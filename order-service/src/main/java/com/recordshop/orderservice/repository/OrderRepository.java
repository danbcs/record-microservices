package com.recordshop.orderservice.repository;

import com.recordshop.orderservice.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query(value="SELECT * FROM tb_pedido WHERE id_cliente = ?1", nativeQuery = true)
    Optional<Order> findByIdClient(Long idClient);

    @Query(value="SELECT * FROM tb_pedido WHERE data_hora > ?1 AND data_hora <= ?2", nativeQuery = true)
    Optional<Order> findByPeriod(Date startDate, Date endDate);
}
