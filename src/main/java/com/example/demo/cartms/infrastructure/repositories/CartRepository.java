package com.example.demo.cartms.infrastructure.repositories;

import com.example.demo.cartms.domain.projecttions.Cart;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;

@RestResource
public interface CartRepository extends JpaRepository<Cart, String> {
    @Override
    @RestResource(exported = false)
    // Prevents POST /carts and PATCH /carts/:id
    Cart save(Cart cart);

    @Override
    @RestResource(exported = false)
    // Prevents DELETE /carts/:id
    void deleteById(String cartNumber);

    @Override
    @RestResource(exported = false)
    void delete(Cart entity);
}
