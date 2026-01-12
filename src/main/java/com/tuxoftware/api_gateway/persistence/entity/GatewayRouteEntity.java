package com.tuxoftware.api_gateway.persistence.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.relational.core.mapping.Column;
import lombok.Data;

@Data
@Table("gateway_routes")
public class GatewayRouteEntity {
    @Id
    @Column("route_id")
    private String routeId;

    private String uri;

    private String predicates; // JSON String

    private String filters;    // JSON String

    @Column("order_num")
    private Integer order;

    private Boolean enabled;
}