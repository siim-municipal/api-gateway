package com.tuxoftware.api_gateway.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class RouteRefreshController {

    private final ApplicationEventPublisher publisher;

    @PostMapping("/api/admin/routes/refresh")
    public Mono<ResponseEntity<String>> refreshRoutes() {
        publisher.publishEvent(new RefreshRoutesEvent(this));
        return Mono.just(ResponseEntity.ok("Rutas recargadas desde BD"));
    }
}