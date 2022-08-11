package com.recordshop.diskservice.repository;

import com.recordshop.diskservice.model.Disk;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface DiskRepository extends MongoRepository<Disk, String> {
    //@Query("{'nome': ?0, 'artista': ?1, 'ano': ?2, 'estilo': ?3, 'quantidade': ?4}")

    List<Disk> findByNomeLikeOrArtistaLikeOrAnoLikeOrEstiloLikeOrQuantidadeLike(String nome,
                                                                                String artista,
                                                                                String ano,
                                                                                String estilo,
                                                                                Integer quantidade);
    List<Disk> findByNomeAndArtistaAndAnoAndEstiloAndQuantidade(String nome,
                                                                String artista,
                                                                String ano,
                                                                String estilo,
                                                                Integer quantidade);
}
