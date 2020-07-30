package com.my.dao;

import com.my.domain.Product;
import com.my.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.beans.PropertyDescriptor;

public interface ProductDao extends JpaRepository<Product,Integer> {
}
