package io.joca.rest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.joca.rest.api.v1.model.Vendor;

/**
 * 
 * @author Joao Berardo
 * @since Feb. 19, 2019
 *
 */
@Repository
public interface VendorRepository extends JpaRepository<Vendor, Long> {
}