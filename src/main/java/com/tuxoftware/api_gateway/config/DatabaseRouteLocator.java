package com.tuxoftware.api_gateway.config;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tuxoftware.api_gateway.persistence.entity.GatewayRouteEntity;
import com.tuxoftware.api_gateway.persistence.repository.GatewayRouteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.FilterDefinition;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionRepository;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class DatabaseRouteLocator implements RouteDefinitionRepository {

    private final GatewayRouteRepository repository;
    private final ObjectMapper objectMapper;

    @Override
    public Flux<RouteDefinition> getRouteDefinitions() {
        return repository.findAllByEnabledTrue()
                .map(this::mapToRouteDefinition)
                .onErrorContinue((e, o) -> log.error("Error cargando ruta desde BD: {}", e.getMessage()));
    }

    @Override
    public Mono<Void> save(Mono<RouteDefinition> route) {
        return Mono.error(new UnsupportedOperationException("Guardar desde API no implementado, usa la BD directa"));
    }

    @Override
    public Mono<Void> delete(Mono<String> routeId) {
        return Mono.error(new UnsupportedOperationException("Borrar desde API no implementado"));
    }

    // Mapeo Manual: Entidad BD -> Objeto Gateway
    private RouteDefinition mapToRouteDefinition(GatewayRouteEntity entity) {
        RouteDefinition routeDefinition = new RouteDefinition();
        routeDefinition.setId(entity.getRouteId());
        routeDefinition.setUri(URI.create(entity.getUri()));
        routeDefinition.setOrder(entity.getOrder());

        try {
            // Deserializar Predicados (Path, Method, etc.)
            if (entity.getPredicates() != null) {
                List<PredicateDefinition> predicates = objectMapper.readValue(
                        entity.getPredicates(),
                        new TypeReference<List<PredicateDefinition>>() {}
                );
                routeDefinition.setPredicates(predicates);
            }

            // Deserializar Filtros (StripPrefix, AddHeader, etc.)
            if (entity.getFilters() != null) {
                List<FilterDefinition> filters = objectMapper.readValue(
                        entity.getFilters(),
                        new TypeReference<List<FilterDefinition>>() {}
                );
                routeDefinition.setFilters(filters);
            }
        } catch (Exception e) {
            log.error("Error parseando JSON de ruta {}: {}", entity.getRouteId(), e.getMessage());
            throw new RuntimeException("JSON inválido en ruta " + entity.getRouteId());
        }

        return routeDefinition;
    }
}