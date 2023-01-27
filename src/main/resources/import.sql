insert into time(id,nome)values(1,'Brasil');
insert into time(id,nome)values(2,'Itália');
insert into time(id,nome)values(3,'Uruguai');
insert into time(id,nome)values(4,'Argentina');

insert into permissao (id, nome, descricao) values (1, 'CONSULTAR_TIMES', 'Permite consultar times');
insert into permissao (id, nome, descricao) values (2, 'EDITAR_TIMES', 'Permite editar times');


insert into grupo (id, nome) values (1, 'Gerente'), (2, 'Vendedor'), (3, 'Secretária'), (4, 'Cadastrador');

insert into grupo_permissao (grupo_id, permissao_id) values (1, 1), (1, 2), (2, 1), (2, 2), (3, 1);
