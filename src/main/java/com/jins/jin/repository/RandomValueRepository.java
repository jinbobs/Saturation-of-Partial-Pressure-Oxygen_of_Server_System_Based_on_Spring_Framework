package com.jins.jin.repository;

import com.jins.jin.entity.RandomValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RandomValueRepository extends JpaRepository<RandomValue, Long> {
}