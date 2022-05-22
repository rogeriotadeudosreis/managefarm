-- inserts
INSERT INTO public.tb_user (data_inicial,sobrenome,nome,senha,username,data_atualizada,ativo,telefone,profiles_id) VALUES
('2022-05-05 15:59:00', 'Reis', 'André Luiz Avelino dos Reis', '$2a$10$KCwiJr2r2w5dZXJN2.bZIumXn8OQdh/GPiNwMhefAaQGDO8B4iCQu', 'andre@gmail.com',NULL,true,NULL,1),
('2022-05-05 15:59:10', 'Avelino', 'Débora Regina Avelino dos Reis', '$2a$10$KCwiJr2r2w5dZXJN2.bZIumXn8OQdh/GPiNwMhefAaQGDO8B4iCQu', 'debora@gmail.com',NULL,true,NULL,1),
('2022-05-05 15:59:15', 'Costa', 'Ivone Costa Reis', '$2a$10$KCwiJr2r2w5dZXJN2.bZIumXn8OQdh/GPiNwMhefAaQGDO8B4iCQu', 'ivone@gmail.com',NULL,true,NULL,1),
('2022-05-05 15:59:20', 'Bento', 'Ricardo Bento', '$2a$10$KCwiJr2r2w5dZXJN2.bZIumXn8OQdh/GPiNwMhefAaQGDO8B4iCQu', 'ricardo@gmail.com',NULL,true,NULL,1),
('2022-05-05 15:59:25', 'Reis', 'Rogerio Tadeu dos Reis', '$2a$10$KCwiJr2r2w5dZXJN2.bZIumXn8OQdh/GPiNwMhefAaQGDO8B4iCQu', 'rogerio@gmail.com',NULL,true,62985915534,1),
('2022-05-05 15:59:30', 'Paulista', 'Francisco Jd Paulista', '$2a$10$KCwiJr2r2w5dZXJN2.bZIumXn8OQdh/GPiNwMhefAaQGDO8B4iCQu', 'francisco@gmail.com',NULL,true,NULL,1);

INSERT INTO public.perfil
(nome)
VALUES
('ADMIN'),
('USUARIO'),
('CLIENTE');

INSERT INTO public.tb_user_profiles
(user_id, profiles_id)
VALUES
(1, 1),
(1, 2),
(1, 3),
(2, 2),
(2, 3),
(3, 2),
(3, 3),
(4, 2),
(5, 2),
(6, 2);


