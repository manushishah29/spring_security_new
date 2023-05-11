package com.example.springSecurityNew.repository;

import com.example.springSecurityNew.entity.TestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TestRepo extends JpaRepository<TestEntity,Integer> {

    Optional<TestEntity> findByEmail(String email);
}
