package com.recordshop.diskservice.service;

import com.recordshop.diskservice.dto.DiskRequest;
import com.recordshop.diskservice.dto.DiskResponse;
import com.recordshop.diskservice.model.Disk;
import com.recordshop.diskservice.repository.DiskRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class DiskService {

    private final DiskRepository diskRepository;

    public void createDisk(DiskRequest diskRequest) {
        Disk disk = Disk.builder()
                .nome(diskRequest.getNome())
                .artista(diskRequest.getArtista())
                .ano(diskRequest.getAno())
                .estilo(diskRequest.getEstilo())
                .quantidade(diskRequest.getQuantidade())
                .build();
        diskRepository.save(disk);
        log.info("Disk {} is saved", disk.getId());
    }

    public void updateDisk(Disk disk) {
        Disk savedDisk = diskRepository.findById(disk.getId())
                .orElseThrow(() -> new RuntimeException(
                        String.format("Cannot find Disk by ID %s", disk.getId())));
        savedDisk.setNome(disk.getNome());
        savedDisk.setArtista(disk.getArtista());
        savedDisk.setAno(disk.getAno());
        savedDisk.setEstilo(disk.getEstilo());
        savedDisk.setQuantidade(disk.getQuantidade());

        diskRepository.save(savedDisk);
    }

    public List<DiskResponse> getAllDisks() {
        List<Disk> disks = diskRepository.findAll();

        return disks.stream().map(this::mapToDiskResponse).toList();
    }

    public List<DiskResponse> getDiskByParamsLike(String nome,
                                                  String artista,
                                                  String ano,
                                                  String estilo,
                                                  Integer quantidade) {
        List<Disk> disk = diskRepository.findByNomeLikeOrArtistaLikeOrAnoLikeOrEstiloLikeOrQuantidadeLike(nome, artista, ano, estilo, quantidade);
        return disk.stream().map(this::mapToDiskResponse).toList();
    }

    public List<DiskResponse> getDiskByParamsExact(String nome,
                                                  String artista,
                                                  String ano,
                                                  String estilo,
                                                  Integer quantidade) {
        List<Disk> disk = diskRepository.findByNomeAndArtistaAndAnoAndEstiloAndQuantidade(nome, artista, ano, estilo, quantidade);
        return disk.stream().map(this::mapToDiskResponse).toList();
    }

    public void deleteDisk(String id) {
        diskRepository.deleteById(id);
    }

    private DiskResponse mapToDiskResponse(Disk disk) {
        return DiskResponse.builder()
                .id(disk.getId())
                .nome(disk.getNome())
                .artista(disk.getArtista())
                .ano(disk.getAno())
                .estilo(disk.getEstilo())
                .quantidade(disk.getQuantidade())
                .build();
    }
}
