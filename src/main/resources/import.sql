-- Inserção de times
INSERT INTO time (id, nome) VALUES (1, 'São Paulo');
INSERT INTO time (id, nome) VALUES (2, 'Palmeiras');
INSERT INTO time (id, nome) VALUES (3, 'Corinthians');
INSERT INTO time (id, nome) VALUES (4, 'Flamengo');
INSERT INTO time (id, nome) VALUES (5, 'Vasco');
INSERT INTO time (id, nome) VALUES (6, 'Grêmio');
INSERT INTO time (id, nome) VALUES (7, 'Internacional');
INSERT INTO time (id, nome) VALUES (8, 'Atlético Mineiro');
INSERT INTO time (id, nome) VALUES (9, 'Cruzeiro');
INSERT INTO time (id, nome) VALUES (10, 'Santos');

---- Inserção de campeonatos
INSERT INTO campeonato (id, nome) VALUES (1, 'Brasileiro Série A');
INSERT INTO campeonato (id, nome) VALUES (2, 'Brasileiro Série B');
INSERT INTO campeonato (id, nome) VALUES (3, 'Libertadores');
INSERT INTO campeonato (id, nome) VALUES (4, 'Paulista');
INSERT INTO campeonato (id, nome) VALUES (5, 'Carioca');

---- Inserção de partidas
INSERT INTO partida (id, campeonato_id, time_casa_id, time_fora_id, data_partida, resultado) VALUES(1, 1, 1, 2, '2022-02-15 20:00:00', 'CASA');
INSERT INTO partida (id, campeonato_id, time_casa_id, time_fora_id, data_partida, resultado) VALUES(2, 2, 3, 4, '2022-02-15 18:30:00', 'FORA');
INSERT INTO partida(id, campeonato_id, time_casa_id, time_fora_id, data_partida, resultado) VALUES(3, 3, 5, 6, '2022-02-16 18:30:00', 'FORA');
INSERT INTO partida (id, campeonato_id, time_casa_id, time_fora_id, data_partida, resultado) VALUES(4, 4, 7, 8, '2022-02-16 18:30:00', 'FORA');

------------ Inserir Permissões ---------
insert into permissao (id, nome, descricao) values (1, 'CONSULTAR_TIMES', 'Permite consultar times');
insert into permissao (id, nome, descricao) values (2, 'EDITAR_TIMES', 'Permite editar times');

-------- Inserir Grupos -------------
insert into grupo (id, nome) values (1, 'Gerente'), (2, 'Vendedor'), (3, 'Secretária'), (4, 'Cadastrador');
insert into grupo_permissao (grupo_id, permissao_id) values (1, 1), (1, 2), (2, 1), (2, 2), (3, 1);


----- Inserir Usuários ------
INSERT INTO usuario (nome, email, senha, data_cadastro)VALUES ('Marcos', 'marcos@gmail.com', '123', '2022-02-17 10:00:00');
INSERT INTO usuario (nome, email, senha, data_cadastro)VALUES ('Venicios', 'venicios@gmail.com', '123', '2022-02-17 10:00:00');

------- Usuario Grupo ------
insert into usuario_grupo (usuario_id, grupo_id) values (1, 1), (1, 2), (2, 2);