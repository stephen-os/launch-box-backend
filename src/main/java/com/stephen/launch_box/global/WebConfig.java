package com.stephen.launch_box.global;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {

        // Enable CORS for the frontend during development.
        // This allows the React app (running on localhost:5173) to make HTTP requests to this backend (localhost:8080).
        // Without this, the browser would block cross-origin requests due to security restrictions.
        // IMPORTANT: In production, restrict allowed origins to your actual frontend domain.
        registry.addMapping("/api/**")
                .allowedOrigins("http://localhost:5173")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*");
    }
}
