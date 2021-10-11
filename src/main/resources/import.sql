insert into tab_cozinhas (id, nm_cozinha) values (1, 'Australiana');
insert into tab_cozinhas (id, nm_cozinha) values (2, 'Indiana');
insert into tab_cozinhas (id, nm_cozinha) values (3, 'Tailandesa');

insert into tab_restaurantes (nm_restaurante, taxa_frete, cozinha_id) values ('Outback', '15.00', 1);
insert into tab_restaurantes (nm_restaurante, taxa_frete, cozinha_id) values ('Frango na Brasa', '9.99', 2);
insert into tab_restaurantes (nm_restaurante, taxa_frete, cozinha_id) values ('Tailand Food', '9.99', 3);

insert into tab_estados (id, nome) values (1, 'Acre');
insert into tab_estados (id, nome) values (2, 'Alagoas');
insert into tab_estados (id, nome) values (3, 'Amazonas');
insert into tab_estados (id, nome) values (4, 'Amapá');
insert into tab_estados (id, nome) values (5, 'Bahia');
insert into tab_estados (id, nome) values (6, 'Ceará');
insert into tab_estados (id, nome) values (7, 'Distrito Federal');
insert into tab_estados (id, nome) values (8, 'Espírito Santo');
insert into tab_estados (id, nome) values (9, 'Goiás');
insert into tab_estados (id, nome) values (10, 'Maranhão');
insert into tab_estados (id, nome) values (11, 'Minas Gerais');
insert into tab_estados (id, nome) values (12, 'Mato Grosso do Sul');
insert into tab_estados (id, nome) values (13, 'Mato Grosso');
insert into tab_estados (id, nome) values (14, 'Pará');
insert into tab_estados (id, nome) values (15, 'Paraíba');
insert into tab_estados (id, nome) values (16, 'Pernambuco');
insert into tab_estados (id, nome) values (17, 'Piauí');
insert into tab_estados (id, nome) values (18, 'Paraná');
insert into tab_estados (id, nome) values (19, 'Rio de Janeiro');
insert into tab_estados (id, nome) values (20, 'Rio Grande do Norte');
insert into tab_estados (id, nome) values (21, 'Rondônia');
insert into tab_estados (id, nome) values (22, 'Roraima');
insert into tab_estados (id, nome) values (23, 'Rio Grande do Sul');
insert into tab_estados (id, nome) values (24, 'Santa Catarina');
insert into tab_estados (id, nome) values (25, 'Sergipe');
insert into tab_estados (id, nome) values (26, 'São Paulo');
insert into tab_estados (id, nome) values (27, 'Tocantins');

insert into tab_cidades (id, nome, estado_id) values (1, 'Rio de Janeiro', 19);