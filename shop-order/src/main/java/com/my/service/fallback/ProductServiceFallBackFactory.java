package com.my.service.fallback;

import com.my.domain.Product;
import com.my.service.ProductService;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
//@Service
@Slf4j
public class ProductServiceFallBackFactory implements FallbackFactory<ProductService> {

    @Override
    public ProductService create(Throwable throwable) {

        return new ProductService() {
            @Override
            public Product findByPid(Integer pid) {
                log.error("{}",throwable);
                Product product=new Product();
                product.setPid(-100);
                product.setPname("y远程调用商品微服务出现异常,进入容错逻辑");
                return product;
            }

            @Override
            public void reduceInventory(Integer pid, Integer number) {

            }

        };
    }
}
