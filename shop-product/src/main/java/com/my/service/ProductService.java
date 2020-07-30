package com.my.service;

import com.my.domain.Product;

public interface ProductService {
    Product findByPid(Integer pid);

   void reduceInventory(Integer pid, Integer number);
}
