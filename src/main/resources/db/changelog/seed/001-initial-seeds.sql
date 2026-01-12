-- Liquibase changeset o ejecución manual

-- 1. MS-PADRON
INSERT INTO gateway_routes (route_id, uri, predicates, filters, order_num, enabled)
VALUES (
           'padron-route',
           'lb://ms-padron-unico',
           '[{"name":"Path", "args":{"pattern":"/api/v1/sujetos-pasivos/**, /api/v1/predios/**, /api/v1/licencias/**, /api/v1/catalogos/**"}}]',
           '[]',
           0,
           true
       );

-- 2. MS-CALCULO
INSERT INTO gateway_routes (route_id, uri, predicates, filters, order_num, enabled)
VALUES (
           'calculo-impuestos',
           'lb://ms-calculo-app', -- Asegúrate que este sea el spring.application.name del ms-calculo
           '[{"name":"Path", "args":{"pattern":"/api/v1/calculos/**"}}]',
           '[]',
           0,
           true
       );

-- 3. MS-AGUA (Ejemplo si existiera)
INSERT INTO gateway_routes (route_id, uri, predicates, filters, order_num, enabled)
VALUES (
           'ms-agua',
           'lb://ms-agua',
           '[{"name":"Path", "args":{"pattern":"/api/v1/**"}}]', -- Cuidado con este wildcard tan amplio
           '[]',
           0,
           true
       );