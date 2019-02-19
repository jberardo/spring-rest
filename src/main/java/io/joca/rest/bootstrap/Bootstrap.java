package io.joca.rest.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import io.joca.rest.api.v1.model.Customer;
import io.joca.rest.api.v1.model.Vendor;
import io.joca.rest.domain.Category;
import io.joca.rest.repositories.CategoryRepository;
import io.joca.rest.repositories.CustomerRepository;
import io.joca.rest.repositories.VendorRepository;

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
    private VendorRepository vendorRepository;
    
    public Bootstrap(CategoryRepository categoryRepository, CustomerRepository customerRepository, VendorRepository vendorRepository) {
        this.categoryRepository = categoryRepository;
        this.customerRepository = customerRepository;
        this.vendorRepository = vendorRepository;
    }

    @Override
    public void run(String... args) throws Exception {
    	loadCategories();
    	loadCustomers();
    	loadVendors();
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
    
    private void loadVendors() {
    	Vendor vendor1 = new Vendor();
    	vendor1.setName("Vendor 1");
    	
    	Vendor vendor2 = new Vendor();
    	vendor2.setName("Vendor 2");
    	
    	Vendor vendor3 = new Vendor();
    	vendor3.setName("Vendor 3");
    	
    	Vendor vendor4 = new Vendor();
    	vendor4.setName("Vendor 4");
    	
    	Vendor vendor5 = new Vendor();
    	vendor5.setName("Vendor 5");

    	vendorRepository.save(vendor1);
    	vendorRepository.save(vendor2);
    	vendorRepository.save(vendor3);
    	vendorRepository.save(vendor4);
    	vendorRepository.save(vendor5);
    	
    	System.out.println("Vendors Loaded: " + categoryRepository.count() );
    }
}