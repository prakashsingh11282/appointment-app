package com.example.labour.repository;

import com.example.labour.entity.Labour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LabourRepository extends JpaRepository<Labour, Long> {
  Optional<Labour> findByUserId(Long userId);

  List<Labour> findAll();
}
