package io.joca.rest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.joca.rest.api.v1.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}