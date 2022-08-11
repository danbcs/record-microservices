package com.recordshop.diskservice.repository;

import com.recordshop.diskservice.model.Disk;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DiskRepository extends MongoRepository<Disk, String> {
}
