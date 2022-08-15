package com.recordshop.diskservice.service;

import com.recordshop.diskservice.dto.DiskRequest;
import com.recordshop.diskservice.dto.DiskResponse;
import com.recordshop.diskservice.model.Disk;
import com.recordshop.diskservice.repository.DiskRepository;
import com.recordshop.diskservice.model.util.SearchCriteria;
import com.recordshop.diskservice.repository.specification.DiskSpecification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
                .valor(diskRequest.getValor())
                .build();
        diskRepository.save(disk);
        log.info("Disk {} is saved", disk.getId());
    }

    public void updateDisk(Disk disk) {
        Disk savedDisk = getDiskById(disk.getId());
        savedDisk.setNome(disk.getNome());
        savedDisk.setArtista(disk.getArtista());
        savedDisk.setAno(disk.getAno());
        savedDisk.setEstilo(disk.getEstilo());
        savedDisk.setQuantidade(disk.getQuantidade());
        savedDisk.setValor(disk.getValor());

        diskRepository.save(savedDisk);
    }

    private Disk getDiskById(Long id) {
        Disk savedDisk = diskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        String.format("Cannot find Disk by ID %s", id)));
        return savedDisk;
    }

    public List<DiskResponse> getAllDisks() {
        List<Disk> disks = diskRepository.findAll();

        return disks.stream().map(this::mapToDiskResponse).toList();
    }

    public void deleteDisk(Long id) {
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
                .valor(disk.getValor())
                .build();
    }

    public void updateQuantidade(Long id, Integer quantidade) {
        Disk savedDisk = getDiskById(id);
        savedDisk.setQuantidade(quantidade);

        diskRepository.save(savedDisk);
    }

    public Integer getQuantidade(Long id) {
        Disk savedDisk = getDiskById(id);

        return savedDisk.getQuantidade();
    }

    public List<DiskResponse> getDiskByEstilo(String estilo) {
        List<Disk> disks = diskRepository.findByEstilo(estilo);
        return disks.stream().map(this::mapToDiskResponse).toList();
    }

    public List<DiskResponse> getDiskByAno(String ano) {
        List<Disk> disks = diskRepository.findByAno(ano);
        return disks.stream().map(this::mapToDiskResponse).toList();
    }

    public List<DiskResponse> searchParams(Integer params, String search) {
        List<DiskSpecification> spec = new ArrayList();
        Pattern pattern = Pattern.compile("(\\w+?)(:|<|>)(\\w+?),");
        Matcher matcher = pattern.matcher(search + ",");
        while (matcher.find()) {
            spec.add(new DiskSpecification(new SearchCriteria(matcher.group(1), matcher.group(2), matcher.group(3))));
        }
        List<Disk> disks = null;
        switch(params) {
            case 1:
                disks = diskRepository.findAll(Specification.where(spec.get(0)));
                break;
            case 2:
                disks = diskRepository.findAll(Specification.where(spec.get(0))
                        .and(spec.get(1)));
                break;
            case 3:
                disks = diskRepository.findAll(Specification.where(spec.get(0))
                        .and(spec.get(1))
                        .and(spec.get(2)));
                break;
            case 4:
                disks = diskRepository.findAll(Specification.where(spec.get(0))
                        .and(spec.get(1))
                        .and(spec.get(2))
                        .and(spec.get(3)));
                break;

        }
        return disks.stream().map(this::mapToDiskResponse).toList();
    }

    public List<DiskResponse> searchThreeParams(String search) {
        List<DiskSpecification> spec = new ArrayList();
        Pattern pattern = Pattern.compile("(\\w+?)(:|<|>)(\\w+?),");
        Matcher matcher = pattern.matcher(search + ",");
        while (matcher.find()) {
            spec.add(new DiskSpecification(new SearchCriteria(matcher.group(1), matcher.group(2), matcher.group(3))));
        }

        List<Disk> disks = diskRepository.findAll(Specification.where(spec.get(0)).and(spec.get(1)));

        return disks.stream().map(this::mapToDiskResponse).toList();
    }

    public List<DiskResponse> searchFourParams(String search) {
        List<DiskSpecification> spec = new ArrayList();
        Pattern pattern = Pattern.compile("(\\w+?)(:|<|>)(\\w+?),");
        Matcher matcher = pattern.matcher(search + ",");
        while (matcher.find()) {
            spec.add(new DiskSpecification(new SearchCriteria(matcher.group(1), matcher.group(2), matcher.group(3))));
        }

        List<Disk> disks = diskRepository.findAll(Specification.where(spec.get(0)).and(spec.get(1)));

        return disks.stream().map(this::mapToDiskResponse).toList();
    }
}
