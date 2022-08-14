package com.recordshop.clientservice.repository;

import com.recordshop.clientservice.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Observable;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
/*
    @Modifying
    @Query(value = "update tb_cliente set status = ? where id = ?", nativeQuery = true)
    int disableById(Boolean status, Long id);*/

    Optional<Client> findByDocumento(String documento);
}
