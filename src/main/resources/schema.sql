CREATE TABLE IF NOT EXISTS gateway_routes (
  route_id VARCHAR(100) PRIMARY KEY,
  uri VARCHAR(255) NOT NULL,
  predicates TEXT,
  filters TEXT,
  order_num INT DEFAULT 0,
  enabled BOOLEAN DEFAULT TRUE
);