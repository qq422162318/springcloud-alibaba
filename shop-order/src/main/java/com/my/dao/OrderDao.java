package com.my.dao;

import com.my.domain.Order;
import com.my.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDao extends JpaRepository<Order,Integer> {
}
