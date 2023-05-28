package com.example.sbfs.repo;

import com.example.sbfs.entity.DestinationImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DestinationImageRepository extends JpaRepository<DestinationImage,Long> {
    Optional<DestinationImage> findByName(String name);
}
