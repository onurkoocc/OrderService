package com.qrmenu.OrderService.delegate.client;

import com.qrmenu.OrderService.config.security.FeignClientConfig;
import com.qrmenu.OrderService.delegate.model.dto.ProductDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// Replace 'menu-service' with the actual service name or URL
@FeignClient(name = "menu-service", url = "http://localhost:8080/api", configuration = FeignClientConfig.class)
public interface MenuServiceClient {

    @GetMapping("/products/{productId}")
    ProductDTO getProductById(@PathVariable Long productId);
}
