package com.qrmenu.OrderService.delegate.service.impl;

import com.qrmenu.OrderService.delegate.client.MenuServiceClient;
import com.qrmenu.OrderService.delegate.model.dto.ProductDTO;
import com.qrmenu.OrderService.delegate.service.MenuServiceDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class MenuServiceDelegateImpl implements MenuServiceDelegate {
    private final MenuServiceClient menuServiceClient;

    @Autowired
    public MenuServiceDelegateImpl(MenuServiceClient menuServiceClient) {
        this.menuServiceClient = menuServiceClient;
    }

    @Override
    public ProductDTO getProductById(Long productId) {
        return menuServiceClient.getProductById(productId);
    }
}
