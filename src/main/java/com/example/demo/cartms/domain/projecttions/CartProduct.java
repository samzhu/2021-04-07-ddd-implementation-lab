/*
 * This file is generated by jOOQ.
 */
package com.example.demo.cartms.domain.projecttions;


import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


/**
 * 購物車商品資料
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
@Entity
@Table(
    name = "cart_product",
    uniqueConstraints = {
        @UniqueConstraint(name = "KEY_cart_product_PRIMARY", columnNames = { "id" })
    },
    indexes = {
        @Index(name = "cart_number", columnList = "cart_number ASC")
    }
)
public class CartProduct implements Serializable {

    private static final long serialVersionUID = 1L;

    private String        id;
    private String        cartNumber;
    private String        productId;
    private String        productName;
    private Integer       amount;
    private String        createdBy;
    private LocalDateTime createdDate;
    private String        lastModifiedBy;
    private LocalDateTime lastModifiedDate;

    public CartProduct() {}

    public CartProduct(CartProduct value) {
        this.id = value.id;
        this.cartNumber = value.cartNumber;
        this.productId = value.productId;
        this.productName = value.productName;
        this.amount = value.amount;
        this.createdBy = value.createdBy;
        this.createdDate = value.createdDate;
        this.lastModifiedBy = value.lastModifiedBy;
        this.lastModifiedDate = value.lastModifiedDate;
    }

    public CartProduct(
        String        id,
        String        cartNumber,
        String        productId,
        String        productName,
        Integer       amount,
        String        createdBy,
        LocalDateTime createdDate,
        String        lastModifiedBy,
        LocalDateTime lastModifiedDate
    ) {
        this.id = id;
        this.cartNumber = cartNumber;
        this.productId = productId;
        this.productName = productName;
        this.amount = amount;
        this.createdBy = createdBy;
        this.createdDate = createdDate;
        this.lastModifiedBy = lastModifiedBy;
        this.lastModifiedDate = lastModifiedDate;
    }

    /**
     * Getter for <code>testdb.cart_product.id</code>. 流水編號
     */
    @Id
    @Column(name = "id", nullable = false, length = 60)
    @NotNull
    @Size(max = 60)
    public String getId() {
        return this.id;
    }

    /**
     * Setter for <code>testdb.cart_product.id</code>. 流水編號
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Getter for <code>testdb.cart_product.cart_number</code>. 購物車編號
     */
    @Column(name = "cart_number", nullable = false, length = 60)
    @NotNull
    @Size(max = 60)
    public String getCartNumber() {
        return this.cartNumber;
    }

    /**
     * Setter for <code>testdb.cart_product.cart_number</code>. 購物車編號
     */
    public void setCartNumber(String cartNumber) {
        this.cartNumber = cartNumber;
    }

    /**
     * Getter for <code>testdb.cart_product.product_id</code>. 商品編號
     */
    @Column(name = "product_id", nullable = false, length = 10)
    @NotNull
    @Size(max = 10)
    public String getProductId() {
        return this.productId;
    }

    /**
     * Setter for <code>testdb.cart_product.product_id</code>. 商品編號
     */
    public void setProductId(String productId) {
        this.productId = productId;
    }

    /**
     * Getter for <code>testdb.cart_product.product_name</code>. 商品編號
     */
    @Column(name = "product_name", nullable = false, length = 10)
    @NotNull
    @Size(max = 10)
    public String getProductName() {
        return this.productName;
    }

    /**
     * Setter for <code>testdb.cart_product.product_name</code>. 商品編號
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * Getter for <code>testdb.cart_product.amount</code>. 單價金額
     */
    @Column(name = "amount", nullable = false, precision = 10)
    @NotNull
    public Integer getAmount() {
        return this.amount;
    }

    /**
     * Setter for <code>testdb.cart_product.amount</code>. 單價金額
     */
    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    /**
     * Getter for <code>testdb.cart_product.created_by</code>.
     */
    @Column(name = "created_by", nullable = false, length = 60)
    @NotNull
    @Size(max = 60)
    public String getCreatedBy() {
        return this.createdBy;
    }

    /**
     * Setter for <code>testdb.cart_product.created_by</code>.
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * Getter for <code>testdb.cart_product.created_date</code>.
     */
    @Column(name = "created_date", nullable = false)
    @NotNull
    public LocalDateTime getCreatedDate() {
        return this.createdDate;
    }

    /**
     * Setter for <code>testdb.cart_product.created_date</code>.
     */
    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * Getter for <code>testdb.cart_product.last_modified_by</code>.
     */
    @Column(name = "last_modified_by", nullable = false, length = 60)
    @NotNull
    @Size(max = 60)
    public String getLastModifiedBy() {
        return this.lastModifiedBy;
    }

    /**
     * Setter for <code>testdb.cart_product.last_modified_by</code>.
     */
    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    /**
     * Getter for <code>testdb.cart_product.last_modified_date</code>.
     */
    @Column(name = "last_modified_date", nullable = false)
    @NotNull
    public LocalDateTime getLastModifiedDate() {
        return this.lastModifiedDate;
    }

    /**
     * Setter for <code>testdb.cart_product.last_modified_date</code>.
     */
    public void setLastModifiedDate(LocalDateTime lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }
}