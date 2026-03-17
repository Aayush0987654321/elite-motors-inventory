package com.example.demo.Repository;

import com.example.demo.model.car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<car, Long> {
}
