package com.learning.elastic.repository;

import com.learning.elastic.entity.PersonelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PersonelEntityRepository extends JpaRepository<PersonelEntity, UUID> {
}
