insert into usuario(email,senha) values ('marlon@gmail.com','$2a$10$y51fMv/8G3hX2XUsJw6k1.uPnLpvGCvFQf66uu6J49dhBpmr7sqcS')
insert into role(role) values ('ROLE_ADMINISTRADOR')
insert into usuario_role(usuario_id,role_id) values (1,1)

insert into usuario(email,senha) values ('bianca@gmail.com','$2a$10$YVBO7TOFQ5r2JDvw7BvxGOk9nVo4o/6vV6oikMiJw1T6jgiEUpVvG')
insert into role(role) values ('ROLE_GERENTE')
insert into usuario_role(usuario_id,role_id) values (2,2)

insert into usuario(email,senha) values ('bernardo@gmail.com','$2a$10$lqnxTX/lMlhd3/BnMAaRrO2e8I3DAQ3QU47CoN9xa3xOLaZBhTrYe')
insert into role(role) values ('ROLE_COLABORADOR')
insert into usuario_role(usuario_id,role_id) values (3,3)