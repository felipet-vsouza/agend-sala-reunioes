/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor. 
 */
/**
 * Author:  henrique.ostermann
 * Created: 15/12/2016
 */
-- -- TABELA USUARIO
Insert into USUARIO (ID_USUARIO,EMAIL_USUARIO,NOME_USUARIO,SENHA_USUARIO) values (SEQ_USUARIO.nextval,'felipe.souza@cwi.com.br','Felipe Souza','$2a$10$ofd6tqa.yBM3N0BgNqFbTuBnO3EwoGbx6Np2GizCjMkvJLZbjlxxK');
Insert into USUARIO (ID_USUARIO,EMAIL_USUARIO,NOME_USUARIO,SENHA_USUARIO) values (SEQ_USUARIO.nextval,'henrique.ostermann@cwi.com.br','Henrique Ostermann','$2a$10$RIJaNA3vfkRxoSpqeJ83K.cWGACTtve8EfWENLWiSVOOur9DwHKDG');
Insert into USUARIO (ID_USUARIO,EMAIL_USUARIO,NOME_USUARIO,SENHA_USUARIO) values (SEQ_USUARIO.nextval,'henrique.mentz@cwi.com.br','Henrique Mentz','$2a$10$V7T2i916qHS6y4ZTOsB6DeySxFYttRsWxb1oj9ZSrYnKlJJ3Z8WRu');
Insert into USUARIO (ID_USUARIO,EMAIL_USUARIO,NOME_USUARIO,SENHA_USUARIO) values (SEQ_USUARIO.nextval,'andre.nunes@cwi.com.br','Andr� Nunes','$2a$10$ofd6tqa.yBM3N0BgNqFbTuBnO3EwoGbx6Np2GizCjMkvJLZbjlxxK');
Insert into USUARIO (ID_USUARIO,EMAIL_USUARIO,NOME_USUARIO,SENHA_USUARIO) values (SEQ_USUARIO.nextval,'fabricio.risetto@cwi.com.br','Fabr�cio Risetto','$2a$10$ofd6tqa.yBM3N0BgNqFbTuBnO3EwoGbx6Np2GizCjMkvJLZbjlxxK');
Insert into USUARIO (ID_USUARIO,EMAIL_USUARIO,NOME_USUARIO,SENHA_USUARIO) values (SEQ_USUARIO.nextval,'bernardo.rezende@cwi.com.br','Bernardo Rezende','$2a$10$ofd6tqa.yBM3N0BgNqFbTuBnO3EwoGbx6Np2GizCjMkvJLZbjlxxK');
Insert into USUARIO (ID_USUARIO,EMAIL_USUARIO,NOME_USUARIO,SENHA_USUARIO) values (SEQ_USUARIO.nextval,'benhur@cwi.com.br','Ben-Hur Santos Ott','$2a$10$ofd6tqa.yBM3N0BgNqFbTuBnO3EwoGbx6Np2GizCjMkvJLZbjlxxK');
Insert into USUARIO (ID_USUARIO,EMAIL_USUARIO,NOME_USUARIO,SENHA_USUARIO) values (SEQ_USUARIO.nextval,'carlos.nonnemacher@cwi.com.br','Carlos Henrique Nonnemacher','$2a$10$ofd6tqa.yBM3N0BgNqFbTuBnO3EwoGbx6Np2GizCjMkvJLZbjlxxK');
Insert into USUARIO (ID_USUARIO,EMAIL_USUARIO,NOME_USUARIO,SENHA_USUARIO) values (SEQ_USUARIO.nextval,'caroline.leite@cwi.com.br','Carolina Leite','$2a$10$ofd6tqa.yBM3N0BgNqFbTuBnO3EwoGbx6Np2GizCjMkvJLZbjlxxK');

--TABELA EQUIPAMENTOS   
INSERT INTO EQUIPAMENTO (ID_EQUIPAMENTO, NOME_EQUIPAMENTO)  VALUES (SEQ_EQUIPAMENTO.nextval, 'Televis�o');
INSERT INTO EQUIPAMENTO (ID_EQUIPAMENTO, NOME_EQUIPAMENTO)  VALUES (SEQ_EQUIPAMENTO.nextval, 'Telefone');
INSERT INTO EQUIPAMENTO (ID_EQUIPAMENTO, NOME_EQUIPAMENTO)  VALUES (SEQ_EQUIPAMENTO.nextval, 'Computador');
INSERT INTO EQUIPAMENTO (ID_EQUIPAMENTO, NOME_EQUIPAMENTO)  VALUES (SEQ_EQUIPAMENTO.nextval, 'Ar condicionado');
INSERT INTO EQUIPAMENTO (ID_EQUIPAMENTO, NOME_EQUIPAMENTO)  VALUES (SEQ_EQUIPAMENTO.nextval, 'Churrasqueria');
INSERT INTO EQUIPAMENTO (ID_EQUIPAMENTO, NOME_EQUIPAMENTO)  VALUES (SEQ_EQUIPAMENTO.nextval, 'Microondas');
INSERT INTO EQUIPAMENTO (ID_EQUIPAMENTO, NOME_EQUIPAMENTO)  VALUES (SEQ_EQUIPAMENTO.nextval, 'Projetor');





-- TABELA SALA
INSERT INTO SALA (ID_SALA, NOME_SALA, CAPACIDADE_SALA)  VALUES (SEQ_SALA.nextval, 'S�o Leopoldo - Sala 3.1', 8 );
INSERT INTO SALA (ID_SALA, NOME_SALA, CAPACIDADE_SALA)  VALUES (SEQ_SALA.nextval, 'S�o Leopoldo - Sala 3.2', 8 );
INSERT INTO SALA (ID_SALA, NOME_SALA, CAPACIDADE_SALA)  VALUES (SEQ_SALA.nextval, 'S�o Leopoldo - Sala 4.1', 8 );
INSERT INTO SALA (ID_SALA, NOME_SALA, CAPACIDADE_SALA)  VALUES (SEQ_SALA.nextval, 'S�o Leopoldo - Sala 4.2', 4 );
INSERT INTO SALA (ID_SALA, NOME_SALA, CAPACIDADE_SALA)  VALUES (SEQ_SALA.nextval, 'S�o Leopoldo - Sala 4.3', 4 );
INSERT INTO SALA (ID_SALA, NOME_SALA, CAPACIDADE_SALA)  VALUES (SEQ_SALA.nextval, 'S�o Leopoldo - Sala 4.4', 4 );
INSERT INTO SALA (ID_SALA, NOME_SALA, CAPACIDADE_SALA)  VALUES (SEQ_SALA.nextval, 'S�o Leopoldo - Sala 5.1', 8 );
INSERT INTO SALA (ID_SALA, NOME_SALA, CAPACIDADE_SALA)  VALUES (SEQ_SALA.nextval, 'S�o Leopoldo - Sala 5.2', 4 );
INSERT INTO SALA (ID_SALA, NOME_SALA, CAPACIDADE_SALA)  VALUES (SEQ_SALA.nextval, 'S�o Leopoldo - Audit�rio', 60 );
INSERT INTO SALA (ID_SALA, NOME_SALA, CAPACIDADE_SALA)  VALUES (SEQ_SALA.nextval, 'S�o Leopoldo - Churrasqueira', 300 );

-- TABELA SALA_EQUIPAMENTO
INSERT INTO SALA_EQUIPAMENTO (SALA_ID_SALA, EQUIPAMENTO_ID_EQUIPAMENTO)  VALUES (1, 1);
INSERT INTO SALA_EQUIPAMENTO (SALA_ID_SALA, EQUIPAMENTO_ID_EQUIPAMENTO)  VALUES (1, 2);
INSERT INTO SALA_EQUIPAMENTO (SALA_ID_SALA, EQUIPAMENTO_ID_EQUIPAMENTO)  VALUES (1, 3);
INSERT INTO SALA_EQUIPAMENTO (SALA_ID_SALA, EQUIPAMENTO_ID_EQUIPAMENTO)  VALUES (2, 1);
INSERT INTO SALA_EQUIPAMENTO (SALA_ID_SALA, EQUIPAMENTO_ID_EQUIPAMENTO)  VALUES (2, 2);
INSERT INTO SALA_EQUIPAMENTO (SALA_ID_SALA, EQUIPAMENTO_ID_EQUIPAMENTO)  VALUES (3, 1);
INSERT INTO SALA_EQUIPAMENTO (SALA_ID_SALA, EQUIPAMENTO_ID_EQUIPAMENTO)  VALUES (3, 3);
INSERT INTO SALA_EQUIPAMENTO (SALA_ID_SALA, EQUIPAMENTO_ID_EQUIPAMENTO)  VALUES (4, 1);
INSERT INTO SALA_EQUIPAMENTO (SALA_ID_SALA, EQUIPAMENTO_ID_EQUIPAMENTO)  VALUES (5, 2);
INSERT INTO SALA_EQUIPAMENTO (SALA_ID_SALA, EQUIPAMENTO_ID_EQUIPAMENTO)  VALUES (6, 3);
INSERT INTO SALA_EQUIPAMENTO (SALA_ID_SALA, EQUIPAMENTO_ID_EQUIPAMENTO)  VALUES (7, 2);
INSERT INTO SALA_EQUIPAMENTO (SALA_ID_SALA, EQUIPAMENTO_ID_EQUIPAMENTO)  VALUES (8, 3);
INSERT INTO SALA_EQUIPAMENTO (SALA_ID_SALA, EQUIPAMENTO_ID_EQUIPAMENTO)  VALUES (8, 1);
INSERT INTO SALA_EQUIPAMENTO (SALA_ID_SALA, EQUIPAMENTO_ID_EQUIPAMENTO)  VALUES (9, 3);
INSERT INTO SALA_EQUIPAMENTO (SALA_ID_SALA, EQUIPAMENTO_ID_EQUIPAMENTO)  VALUES (9, 4);
INSERT INTO SALA_EQUIPAMENTO (SALA_ID_SALA, EQUIPAMENTO_ID_EQUIPAMENTO)  VALUES (9, 7);
INSERT INTO SALA_EQUIPAMENTO (SALA_ID_SALA, EQUIPAMENTO_ID_EQUIPAMENTO)  VALUES (10, 6);
INSERT INTO SALA_EQUIPAMENTO (SALA_ID_SALA, EQUIPAMENTO_ID_EQUIPAMENTO)  VALUES (10, 5);

--AGENDAMENTO
INSERT INTO Agendamento(ID_AGENDAMENTO, DT_INICIO_AGENDAMENTO, DT_FINAL_AGENDAMENTO, ID_USUARIO, DS_AGENDAMENTO, ID_SALA) 
values(SEQ_AGENDAMENTO.nextval, TO_DATE('2016/12/18 09:00', 'yyyy/mm/dd hh24:mi'), TO_DATE('2016/12/18 12:00', 'yyyy/mm/dd hh24:mi'), 1, 'Reuni�o com cliente', 1);

INSERT INTO Agendamento(ID_AGENDAMENTO, DT_INICIO_AGENDAMENTO, DT_FINAL_AGENDAMENTO, ID_USUARIO, DS_AGENDAMENTO, ID_SALA) 
values(SEQ_AGENDAMENTO.nextval, TO_DATE('2016/12/18 12:10', 'yyyy/mm/dd hh24:mi'), TO_DATE('2016/12/18 14:00', 'yyyy/mm/dd hh24:mi'), 1, 'Defini��o de logo', 2);

INSERT INTO Agendamento(ID_AGENDAMENTO, DT_INICIO_AGENDAMENTO, DT_FINAL_AGENDAMENTO, ID_USUARIO, DS_AGENDAMENTO, ID_SALA) 
values(SEQ_AGENDAMENTO.nextval, TO_DATE('2016/12/18 15:00', 'yyyy/mm/dd hh24:mi'), TO_DATE('2016/12/18 16:00', 'yyyy/mm/dd hh24:mi'), 1, 'Convers�o de dados', 3);

INSERT INTO Agendamento(ID_AGENDAMENTO, DT_INICIO_AGENDAMENTO, DT_FINAL_AGENDAMENTO, ID_USUARIO, DS_AGENDAMENTO, ID_SALA) 
values(SEQ_AGENDAMENTO.nextval, TO_DATE('2016/12/18 17:30', 'yyyy/mm/dd hh24:mi'), TO_DATE('2016/12/18 19:00', 'yyyy/mm/dd hh24:mi'), 1, 'Testes centralizados', 4);

INSERT INTO Agendamento(ID_AGENDAMENTO, DT_INICIO_AGENDAMENTO, DT_FINAL_AGENDAMENTO, ID_USUARIO, DS_AGENDAMENTO, ID_SALA) 
values(SEQ_AGENDAMENTO.nextval, TO_DATE('2016/12/19 17:30', 'yyyy/mm/dd hh24:mi'), TO_DATE('2016/12/19 19:00', 'yyyy/mm/dd hh24:mi'), 1, 'DOJO: integra��o cont�nua', 1);

INSERT INTO Agendamento(ID_AGENDAMENTO, DT_INICIO_AGENDAMENTO, DT_FINAL_AGENDAMENTO, ID_USUARIO, DS_AGENDAMENTO, ID_SALA) 
values(SEQ_AGENDAMENTO.nextval, TO_DATE('2016/12/17 17:30', 'yyyy/mm/dd hh24:mi'), TO_DATE('2016/12/17 19:00', 'yyyy/mm/dd hh24:mi'), 1, 'Confraterniza��o', 2);

INSERT INTO Agendamento(ID_AGENDAMENTO, DT_INICIO_AGENDAMENTO, DT_FINAL_AGENDAMENTO, ID_USUARIO, DS_AGENDAMENTO, ID_SALA) 
values(SEQ_AGENDAMENTO.nextval, TO_DATE('2016/12/18 13:30', 'yyyy/mm/dd hh24:mi'), TO_DATE('2016/12/18 17:30', 'yyyy/mm/dd hh24:mi'), 1, 'Aula Crescer', 9);


--PARTICIPANTES
INSERT INTO PARTICIPANTE(ID_PARTICIPANTE, ID_AGENDAMENTO, ID_USUARIO, STATUS) 
VALUES (SEQ_PARTICIPANTE.nextval, 1, 2, 'CONFIRMADO');
INSERT INTO PARTICIPANTE(ID_PARTICIPANTE, ID_AGENDAMENTO, ID_USUARIO, STATUS) 
VALUES (SEQ_PARTICIPANTE.nextval, 1, 3, 'CONFIRMADO');
INSERT INTO PARTICIPANTE(ID_PARTICIPANTE, ID_AGENDAMENTO, ID_USUARIO, STATUS) 
VALUES (SEQ_PARTICIPANTE.nextval, 2, 3, 'CONFIRMADO');
INSERT INTO PARTICIPANTE(ID_PARTICIPANTE, ID_AGENDAMENTO, ID_USUARIO, STATUS) 
VALUES (SEQ_PARTICIPANTE.nextval, 5, 3, 'CONFIRMADO');
INSERT INTO PARTICIPANTE(ID_PARTICIPANTE, ID_AGENDAMENTO, ID_USUARIO, STATUS) 
VALUES (SEQ_PARTICIPANTE.nextval, 6, 3, 'CONFIRMADO');
INSERT INTO PARTICIPANTE(ID_PARTICIPANTE, ID_AGENDAMENTO, ID_USUARIO, STATUS) 
VALUES (SEQ_PARTICIPANTE.nextval, 4, 1, 'CONFIRMADO');
INSERT INTO PARTICIPANTE(ID_PARTICIPANTE, ID_AGENDAMENTO, ID_USUARIO, STATUS) 
VALUES (SEQ_PARTICIPANTE.nextval, 5, 1, 'CONFIRMADO');
INSERT INTO PARTICIPANTE(ID_PARTICIPANTE, ID_AGENDAMENTO, ID_USUARIO, STATUS) 
VALUES (SEQ_PARTICIPANTE.nextval, 6, 1, 'CONFIRMADO');

INSERT INTO PARTICIPANTE(ID_PARTICIPANTE, ID_AGENDAMENTO, ID_USUARIO, STATUS) 
VALUES (SEQ_PARTICIPANTE.nextval, 6, 2, 'PENDENTE');
              
              insert into Email(ID_EMAIL, DATA_ENVIO, HASH_EMAIL, PARTICIPANTE_ID_PARTICIPANTE) values
              (SEQ_EMAIL.nextval, TO_DATE('2016/12/19 00:30', 'yyyy/mm/dd hh24:mi'), '123456789', 9) 