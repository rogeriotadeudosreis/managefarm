-- inserts
INSERT INTO public.tb_user (data_inicial,sobrenome,nome,senha,username,data_atualizada,ativo,telefone) VALUES
('2022-05-05 15:59:00', 'Reis', 'André Luiz Avelino dos Reis', '123456', 'andre@gmail.com',NULL,true,NULL),
('2022-05-05 15:59:10', 'Avelino', 'Débora Regina Avelino dos Reis', '123456', 'debora@gmail.com',NULL,true,NULL),
('2022-05-05 15:59:15', 'Costa', 'Ivone Costa Reis', '123456', 'ivone@gmail.com',NULL,true,NULL),
('2022-05-05 15:59:20', 'Bento', 'Ricardo Bento', '123456', 'ricardo@gmail.com',NULL,true,NULL),
('2022-05-05 15:59:25', 'Reis', 'Rogerio Tadeu dos Reis', '123456', 'rogerio@gmail.com',NULL,true,62985915534),
('2022-05-05 15:59:30', 'Paulista', 'Francisco Jd Paulista', '123456', 'francisco@gmail.com',NULL,true,NULL);

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


