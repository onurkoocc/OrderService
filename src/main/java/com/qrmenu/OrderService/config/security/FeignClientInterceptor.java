package com.qrmenu.OrderService.config.security;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.stereotype.Component;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Component
public class FeignClientInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate requestTemplate) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getCredentials() != null) {
            String jwtToken = authentication.getCredentials().toString();
            requestTemplate.header("Authorization", "Bearer " + jwtToken);
            // Log the outgoing request
            System.out.println("Adding Authorization header to Feign request: Bearer " + jwtToken);
        } else {
            System.out.println("No Authentication found in SecurityContext");
        }
    }

}
