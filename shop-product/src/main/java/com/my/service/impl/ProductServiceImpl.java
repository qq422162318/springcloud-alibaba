package com.my.service.impl;

import com.my.dao.ProductDao;
import com.my.domain.Product;
import com.my.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductDao productDao;

    @Override
    public Product findByPid(Integer pid) {

        return productDao.findById(pid).get();
    }
    @Transactional
    @Override
    public void reduceInventory(Integer pid, Integer number) {
        Product product=productDao.findById(pid).get();

        product.setStock(product.getStock()-number);
         int i=1/0;

        productDao.save(product);

    }
}
