package com.learning.elastic.service;

import com.learning.elastic.entity.PersonelEntity;

import java.util.UUID;

public interface PersonelService {
    /**
     * Saves a personel entity to the database.
     *
     * @param personelEntity the personel entity to save
     * @return the saved personel entity
     */
    PersonelEntity save(PersonelEntity personelEntity);

    /**
     * Updates an existing personel entity.
     *
     * @param personelEntity the personel entity with updated information
     * @return the updated personel entity
     */
    PersonelEntity update(PersonelEntity personelEntity);

    /**
     * Retrieves all personel entities.
     *
     * @return an iterable collection of all personel entities
     */
    Iterable<PersonelEntity> findAll();

    /**
     * Retrieves a personel entity by its ID.
     *
     * @param id the ID of the personel entity
     * @return the personel entity with the specified ID, or null if not found
     */
    PersonelEntity findById(UUID id);

    /**
     * Deletes a personel entity by its ID.
     *
     * @param id the ID of the personel entity to delete
     */
    void deleteById(UUID id);
}
