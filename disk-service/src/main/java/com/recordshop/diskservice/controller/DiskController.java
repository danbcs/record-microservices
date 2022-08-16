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

    @GetMapping("/find/{params}")
    public List<DiskResponse> searchParams(@PathVariable Integer params,
                                           @RequestParam(value = "search") String search) {
        return diskService.searchParams(params, search);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDisk(@PathVariable Long id) {
        diskService.deleteDisk(id);
    }

    @PutMapping("/{id}/qtd/{quantidade}")
    @ResponseStatus(HttpStatus.OK)
    public void updateDisk(@PathVariable Long id, @PathVariable Integer quantidade) {
        diskService.updateQuantidade(id, quantidade);
    }

    @GetMapping("/qtd/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Integer getQuantidade(@PathVariable Long id) {
        return diskService.getQuantidade(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<DiskResponse> getAllDisks() {
        return diskService.getAllDisks();
    }
}
