package com.tuxoftware.api_gateway.persistence.repository;

import com.tuxoftware.api_gateway.persistence.entity.GatewayRouteEntity;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Flux;

public interface GatewayRouteRepository extends R2dbcRepository<GatewayRouteEntity, String> {
    Flux<GatewayRouteEntity> findAllByEnabledTrue();
}
