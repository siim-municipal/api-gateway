-- 1. MS-PADRON
-- Ruta para Sujetos Pasivos
INSERT INTO gateway_routes (route_id, uri, predicates, filters, order_num, enabled)
VALUES (
           'padron-sujetos',
           'lb://ms-padron-unico',
           '[{"name":"Path", "args":{"pattern":"/api/v1/sujetos-pasivos/**"}}]',
           '[]',
           0,
           true
       )ON CONFLICT (route_id) DO NOTHING;;

-- Ruta para Predios
INSERT INTO gateway_routes (route_id, uri, predicates, filters, order_num, enabled)
VALUES (
           'padron-predios',
           'lb://ms-padron-unico',
           '[{"name":"Path", "args":{"pattern":"/api/v1/predios/**"}}]',
           '[]',
           0,
           true
       )ON CONFLICT (route_id) DO NOTHING;;

-- Ruta para Licencias
INSERT INTO gateway_routes (route_id, uri, predicates, filters, order_num, enabled)
VALUES (
           'padron-licencias',
           'lb://ms-padron-unico',
           '[{"name":"Path", "args":{"pattern":"/api/v1/licencias/**"}}]',
           '[]',
           0,
           true
       )ON CONFLICT (route_id) DO NOTHING;;

-- Ruta para Catálogos
INSERT INTO gateway_routes (route_id, uri, predicates, filters, order_num, enabled)
VALUES (
           'padron-catalogos',
           'lb://ms-padron-unico',
           '[{"name":"Path", "args":{"pattern":"/api/v1/catalogos/**"}}]',
           '[]',
           0,
           true
       )ON CONFLICT (route_id) DO NOTHING;;

-- 2. MS-CALCULO
INSERT INTO gateway_routes (route_id, uri, predicates, filters, order_num, enabled)
VALUES (
           'calculo-impuestos',
           'lb://ms-calculo-impuestos',
           '[{"name":"Path", "args":{"pattern":"/api/v1/calculos/**"}}]',
           '[]',
           0,
           true
       )ON CONFLICT (route_id) DO NOTHING;;

-- 3. MS-AGUA
INSERT INTO gateway_routes (route_id, uri, predicates, filters, order_num, enabled)
VALUES (
           'ms-agua',
           'lb://ms-agua',
           '[{"name":"Path", "args":{"pattern":"/api/v1/agua/**"}}]',
           '[]',
           0,
           true
       )ON CONFLICT (route_id) DO NOTHING;;

-- 3. MS-SECURITY
INSERT INTO gateway_routes (route_id, uri, predicates, filters, order_num, enabled)
VALUES (
           'security-users',
           'lb://ms-security',
           '[{"name":"Path", "args":{"pattern":"/api/v1/users/**"}}]',
           '[]',
           0,
           true
       )ON CONFLICT (route_id) DO NOTHING;

INSERT INTO gateway_routes (route_id, uri, predicates, filters, order_num, enabled)
VALUES (
           'ms-tesoreria-recaudacion',
           'lb://ms-tesoreria-recaudacion',
           '[{"name":"Path", "args":{"pattern":"/api/v1/cajas/**"}}]',
           '[]',
           0,
           true
       )ON CONFLICT (route_id) DO NOTHING;;