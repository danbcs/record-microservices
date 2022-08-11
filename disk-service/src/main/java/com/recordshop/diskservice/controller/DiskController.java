package com.recordshop.diskservice.controller;

import com.recordshop.diskservice.dto.DiskRequest;
import com.recordshop.diskservice.dto.DiskResponse;
import com.recordshop.diskservice.model.Disk;
import com.recordshop.diskservice.service.DiskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/disk")
@RequiredArgsConstructor
public class DiskController {

    private final DiskService diskService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createDisk(@RequestBody DiskRequest diskRequest) {
        diskService.createDisk(diskRequest);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public void updateDisk(@RequestBody Disk disk) {
        diskService.updateDisk(disk);
    }

    @GetMapping("/find/like")
    @ResponseStatus(HttpStatus.OK)
    public List<DiskResponse> getDiskByParamsLike(@RequestParam String nome,
                                                  @RequestParam String artista,
                                                  @RequestParam String ano,
                                                  @RequestParam String estilo,
                                                  @RequestParam Integer quantidade){
        return diskService.getDiskByParamsLike(nome, artista, ano, estilo, quantidade);
    }

    @GetMapping("/find/exact")
    @ResponseStatus(HttpStatus.OK)
    public List<DiskResponse> getDiskByParamsExact(@RequestParam String nome,
                                                   @RequestParam String artista,
                                                   @RequestParam String ano,
                                                   @RequestParam String estilo,
                                                   @RequestParam Integer quantidade){
        return diskService.getDiskByParamsExact(nome, artista, ano, estilo, quantidade);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDisk(@PathVariable String id) {
        diskService.deleteDisk(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<DiskResponse> getAllDisks() {
        return diskService.getAllDisks();
    }
}
