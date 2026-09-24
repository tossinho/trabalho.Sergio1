insert into categoria(nome , descriçao ) VALUES('informatica','produtos de informatica');
insert into categoria(nome , descriçao ) VALUES('Livros','livros Técnicos' );
INSERT INTO categoria ( nome, descriçao) VALUES ('maquina de lavar', 'Eletrônicos');
INSERT INTO categoria ( nome, descriçao) VALUES ('computador', 'Informática');
INSERT INTO categoria ( nome, descriçao) VALUES ('forno', 'Casa');
INSERT INTO categoria ( nome, descriçao) VALUES ('livro de matematica', 'Livros tecnicos');

INSERT into produtos(nome , descriçao,preco , estoque , categoria_id) values ('Codigo Limpo','Livro do Autor Robert C Martins', 84,34,2);
INSERT INTO produtos(nome,descriçao, preco,estoque, categoria_id) VALUES ('Smartphone','Smartphone de última geração', 1500.00,10 ,1);

INSERT INTO produtos(nome, descriçao, preco, estoque, categoria_id) VALUES ('Codigo Limpo', 'Livro do Autor Robert C Martins', 84, 34, 2);

INSERT INTO produtos(nome, descriçao, preco, estoque, categoria_id) VALUES ('Smartphone', 'Smartphone de última geração', 1500.00, 10, 1);

INSERT INTO produtos(nome, descriçao, preco, estoque, categoria_id) VALUES ('Notebook', 'Notebook de última geração', 3500.00, 10, 2);

INSERT INTO produtos(nome, descriçao, preco, estoque, categoria_id) VALUES ('Cafeteira', 'Cafeteira elétrica', 300.00, 10, 3);

INSERT INTO produtos(nome, descriçao, preco, estoque, categoria_id) VALUES ('Livro Java', 'Livro para aprender programação Java', 100.00, 10, 4);

INSERT INTO produtos(nome, descriçao, preco, estoque, categoria_id) VALUES ('Bola de Futebol', 'Bola para prática de futebol', 120.00, 10, 5);

INSERT INTO cliente(id, nome, email, telefone) VALUES (1, 'João Silva', 'joao@email.com', '14999990001');

INSERT INTO cliente(id, nome, email, telefone) VALUES (2, 'Maria Souza', 'maria@email.com', '14999990002');

INSERT INTO cliente(id, nome, email, telefone) VALUES (3, 'Carlos Lima', 'carlos@email.com', '14999990003');

INSERT INTO cliente(id, nome, email, telefone) VALUES (4, 'Ana Santos', 'ana@email.com', '14999990004');

INSERT INTO cliente(id, nome, email, telefone) VALUES (5, 'Pedro Oliveira', 'pedro@email.com', '14999990005');

INSERT INTO pedido(id, data, status, valor_total, cliente_id) VALUES (1, '2026-09-01 10:00:00', 'PAGO', 1500.00, 1);

INSERT INTO pedido(id, data, status, valor_total, cliente_id) VALUES (2, '2026-09-01 11:00:00', 'PAGO', 3500.00, 2);

INSERT INTO pedido(id, data, status, valor_total, cliente_id) VALUES (3, '2026-09-01 12:00:00', 'PENDENTE', 300.00, 3);

INSERT INTO pedido(id, data, status, valor_total, cliente_id) VALUES (4, '2026-09-01 13:00:00', 'PAGO', 100.00, 4);

INSERT INTO pedido(id, data, status, valor_total, cliente_id) VALUES (5, '2026-09-01 14:00:00', 'PENDENTE', 120.00, 5);

INSERT INTO item_pedido(id, quantidade, valor_unitario, pedido_id, produto_id) VALUES (1, 1, 1500.00, 1, 1);

INSERT INTO item_pedido(id, quantidade, valor_unitario, pedido_id, produto_id) VALUES (2, 1, 3500.00, 2, 2);

INSERT INTO item_pedido(id, quantidade, valor_unitario, pedido_id, produto_id) VALUES (3, 1, 300.00, 3, 3);

INSERT INTO item_pedido(id, quantidade, valor_unitario, pedido_id, produto_id) VALUES (4, 1, 100.00, 4, 4);

INSERT INTO item_pedido(id, quantidade, valor_unitario, pedido_id, produto_id) VALUES (5, 1, 120.00, 5, 5);

INSERT INTO pagamento(id, valor, data, status, tipo, pedido_id) VALUES (1, 1500.00, '2026-09-01 10:05:00', 'APROVADO', 'PIX', 1);

INSERT INTO pagamento(id, valor, data, status, tipo, pedido_id) VALUES (2, 3500.00, '2026-09-01 11:05:00', 'APROVADO', 'CARTAO', 2);

INSERT INTO pagamento(id, valor, data, status, tipo, pedido_id) VALUES (3, 300.00, '2026-09-01 12:05:00', 'PENDENTE', 'BOLETO', 3);

INSERT INTO pagamento(id, valor, data, status, tipo, pedido_id) VALUES (4, 100.00, '2026-09-01 13:05:00', 'APROVADO', 'PIX', 4);

INSERT INTO pagamento(id, valor, data, status, tipo, pedido_id) VALUES (5, 120.00, '2026-09-01 14:05:00', 'PENDENTE', 'BOLETO', 5);