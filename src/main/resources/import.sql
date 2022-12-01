
/* Creamos algunos usuarios con sus roles */
INSERT INTO users_gs (id, username, password, enabled) VALUES (1, 'lalo','$2a$10$O9wxmH/AeyZZzIS09Wp8YOEMvFnbRVJ8B4dmAMVSGloR62lj.yqXG',1);
INSERT INTO users_gs (id, username, password, enabled) VALUES (2, 'admin','$2a$10$DOMDxjYyfZ/e7RcBfUpzqeaCs8pLgcizuiQWXPkU35nOhZlFcE9MS',1);
INSERT INTO users_gs (id, username, password, enabled) VALUES (3, 'prueba','$2a$10$DOMDxjYyfZ/e7RcBfUpzqeaCs8pLgcizuiQWXPkU35nOhZlFcE9MS',1);

INSERT INTO authorities_gs (id, user_id, authority) VALUES (1, 1,'ROLE_MESA_CONTROL');
INSERT INTO authorities_gs (id, user_id, authority) VALUES (2, 2,'ROLE_ADMIN');
INSERT INTO authorities_gs (id, user_id, authority) VALUES (3, 3,'ROLE_USER');