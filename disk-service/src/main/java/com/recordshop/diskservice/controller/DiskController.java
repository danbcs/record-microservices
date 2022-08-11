package com.recordshop.diskservice.controller;

import com.recordshop.diskservice.dto.DiskRequest;
import com.recordshop.diskservice.dto.DiskResponse;
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
    public void createProduct(@RequestBody DiskRequest diskRequest) {
        diskService.createDisk(diskRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<DiskResponse> getAllDisks() {
        return diskService.getAllDisks();
    }
}
