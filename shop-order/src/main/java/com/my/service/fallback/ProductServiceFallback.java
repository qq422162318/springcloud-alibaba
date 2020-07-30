package com.my.service.fallback;

import com.my.domain.Product;
import com.my.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

//@Component
@Slf4j
public class ProductServiceFallback implements ProductService {
    @Override
    public Product findByPid(Integer pid) {
        Product product=new Product();
        product.setPid(-100);
        product.setPname("y远程调用商品微服务出现异常,进入容错逻辑");
        return product;
    }

    @Override
    public void reduceInventory(Integer pid, Integer number) {

    }

}
