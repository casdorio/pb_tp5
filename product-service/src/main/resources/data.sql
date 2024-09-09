INSERT INTO category (name) VALUES ('Eletrônicos');
INSERT INTO category (name) VALUES ('Roupas');
INSERT INTO category (name) VALUES ('Móveis');
INSERT INTO category (name) VALUES ('Brinquedos');
INSERT INTO category (name) VALUES ('Alimentos');

INSERT INTO product (country, description, category_id, name, price, stock, id) VALUES
('Brasil', 'Cadeira ergonomica com ajuste de altura e apoio lombar.', 1, 'Cadeira de Escritorio', 249.99, 20, DEFAULT),
('Argentina', 'Smartphone com camera de 48MP e 128GB de armazenamento.', 2, 'Smartphone X1', 1799.90, 15, DEFAULT),
('Chile', 'Notebook leve e potente com processador Intel i7.', 3, 'Notebook Ultra Slim', 3499.99, 8, DEFAULT),
('Colombia', 'Tenis confortável para corrida e atividades fisicas.', 1, 'Tenis de Corrida', 159.90, 25, DEFAULT),
('Peru', 'Relogio com monitoramento de saude e conectividade Bluetooth.', 4, 'Relogio Inteligente', 699.00, 12, DEFAULT),
('Uruguai', 'Televisor 4K Ultra HD com tecnologia LED e Smart TV.', 3, 'Televisor 55 4K', 2399.99, 5, DEFAULT),
('Mexico', 'Camera profissional com lente intercambiavel e gravacao em Full HD.', 2, 'Camera DSLR', 2999.90, 7, DEFAULT),
('Ecuador', 'Micro-ondas com funcoes de descongelamento e aquecimento rapido.', 4, 'Micro-ondas', 499.90, 30, DEFAULT),
('Paraguai', 'Aspirador de po com potencia de 1500W e filtro HEPA.', 1, 'Aspirador de Po', 379.99, 10, DEFAULT),
('Bolivia', 'Tablet com tela de 10.5 e 64GB de armazenamento.', 2, 'Tablet Pro', 1299.90, 18, DEFAULT);

