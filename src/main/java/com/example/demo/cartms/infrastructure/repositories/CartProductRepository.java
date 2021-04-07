package com.example.demo.cartms.infrastructure.repositories;

import com.example.demo.cartms.domain.projecttions.CartProduct;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;

@RestResource
public interface CartProductRepository extends JpaRepository<CartProduct, Integer> {
    @Override
    @RestResource(exported = false)
    // Prevents POST //cartProducts and PATCH //cartProducts/:id
    CartProduct save(CartProduct cart);

    @Override
    @RestResource(exported = false)
    // Prevents DELETE //cartProducts/:id
    void deleteById(Integer id);

    @Override
    @RestResource(exported = false)
    void delete(CartProduct entity);
}
