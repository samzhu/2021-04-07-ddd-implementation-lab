package com.example.demo.cartms.infrastructure.repositories;

import com.example.demo.cartms.domain.projecttions.Cart;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;

@RestResource
public interface CartRepository extends JpaRepository<Cart, String> {
    
}
