package com.qrmenu.OrderService.delegate.service;

import com.qrmenu.OrderService.delegate.model.dto.ProductDTO;

public interface MenuServiceDelegate {
    ProductDTO getProductById(Long productId);
}
