package io.joca.rest.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import io.joca.rest.api.v1.model.Customer;
import io.joca.rest.domain.Category;
import io.joca.rest.repositories.CategoryRepository;
import io.joca.rest.repositories.CustomerRepository;

/**
 * 
 * @author Joao Berardo
 * @since Feb. 18, 2019
 *
 */
@Component
public class Bootstrap implements CommandLineRunner {
	
    private CategoryRepository categoryRepository;
    private CustomerRepository customerRepository;

    public Bootstrap(CategoryRepository categoryRepository, CustomerRepository customerRepository) {
        this.categoryRepository = categoryRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public void run(String... args) throws Exception {
    	loadCategories();
    	loadCustomers();
    }
    
    private void loadCustomers() {
    	Customer customer1 = new Customer();
    	customer1.setFirstname("John");
    	customer1.setLastname("Doe");
    	
    	Customer customer2 = new Customer();
    	customer2.setFirstname("Jane");
    	customer2.setLastname("Doe");
    	
    	customerRepository.save(customer1);
    	customerRepository.save(customer2);
    	
    	System.out.println("Customers loaded: " + customerRepository.count());
    }
    
    private void loadCategories() {
        Category fruits = new Category();
        fruits.setName("Fruits");

        Category dried = new Category();
        dried.setName("Dried");

        Category fresh = new Category();
        fresh.setName("Fresh");

        Category exotic = new Category();
        exotic.setName("Exotic");

        Category nuts = new Category();
        nuts.setName("Nuts");

        categoryRepository.save(fruits);
        categoryRepository.save(dried);
        categoryRepository.save(fresh);
        categoryRepository.save(exotic);
        categoryRepository.save(nuts);

        System.out.println("Categories Loaded: " + categoryRepository.count() );    	
    }
}