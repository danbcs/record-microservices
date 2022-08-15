package com.recordshop.diskservice.repository;

import com.recordshop.diskservice.model.Disk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface DiskRepository extends JpaRepository<Disk, Long>, JpaSpecificationExecutor<Disk> {
    //@Query("{'nome': ?0, 'artista': ?1, 'ano': ?2, 'estilo': ?3, 'quantidade': ?4}")

    List<Disk> findByEstilo(String estilo);

    List<Disk> findByAno(String ano);
}
