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
Insert into USUARIO (ID_USUARIO,EMAIL_USUARIO,NOME_USUARIO,SENHA_USUARIO) values ('1','felipe.souza@cwi.com.br','Felipe Souza','$2a$10$ofd6tqa.yBM3N0BgNqFbTuBnO3EwoGbx6Np2GizCjMkvJLZbjlxxK');
Insert into USUARIO (ID_USUARIO,EMAIL_USUARIO,NOME_USUARIO,SENHA_USUARIO) values ('2','henrique.ostermann@cwi.com.br','Henrique Ostermann','$2a$10$RIJaNA3vfkRxoSpqeJ83K.cWGACTtve8EfWENLWiSVOOur9DwHKDG');
Insert into USUARIO (ID_USUARIO,EMAIL_USUARIO,NOME_USUARIO,SENHA_USUARIO) values ('3','henrique.mentz@cwi.com.br','Henrique Mentz','$2a$10$V7T2i916qHS6y4ZTOsB6DeySxFYttRsWxb1oj9ZSrYnKlJJ3Z8WRu');

--TABELA EQUIPAMENTOS   
INSERT INTO EQUIPAMENTO (ID_EQUIPAMENTO, NOME_EQUIPAMENTO)  VALUES (SEQ_EQUIPAMENTO.nextval, 'TELEVISAO');
INSERT INTO EQUIPAMENTO (ID_EQUIPAMENTO, NOME_EQUIPAMENTO)  VALUES (SEQ_EQUIPAMENTO.nextval, 'TELEFONE');
INSERT INTO EQUIPAMENTO (ID_EQUIPAMENTO, NOME_EQUIPAMENTO)  VALUES (SEQ_EQUIPAMENTO.nextval, 'COMPUTADOR');





-- TABELA SALA
INSERT INTO SALA (ID_SALA, NOME_SALA, CAPACIDADE_SALA)  VALUES (SEQ_SALA.nextval, '3.1', 10 );
INSERT INTO SALA (ID_SALA, NOME_SALA, CAPACIDADE_SALA)  VALUES (SEQ_SALA.nextval, '3.2', 10 );
INSERT INTO SALA (ID_SALA, NOME_SALA, CAPACIDADE_SALA)  VALUES (SEQ_SALA.nextval, '4.1', 10 );
INSERT INTO SALA (ID_SALA, NOME_SALA, CAPACIDADE_SALA)  VALUES (SEQ_SALA.nextval, '4.2', 10 );
INSERT INTO SALA (ID_SALA, NOME_SALA, CAPACIDADE_SALA)  VALUES (SEQ_SALA.nextval, '4.3', 10 );
INSERT INTO SALA (ID_SALA, NOME_SALA, CAPACIDADE_SALA)  VALUES (SEQ_SALA.nextval, '4.4', 10 );
INSERT INTO SALA (ID_SALA, NOME_SALA, CAPACIDADE_SALA)  VALUES (SEQ_SALA.nextval, '5.1', 10 );
INSERT INTO SALA (ID_SALA, NOME_SALA, CAPACIDADE_SALA)  VALUES (SEQ_SALA.nextval, '5.2', 10 );
INSERT INTO SALA (ID_SALA, NOME_SALA, CAPACIDADE_SALA)  VALUES (SEQ_SALA.nextval, 'Auditorio', 30 );

-- TABELA SALA_EQUIPAMENTO
INSERT INTO SALA_EQUIPAMENTO (SALA_ID_SALA, EQUIPAMENTO_ID_EQUIPAMENTO)  VALUES (1, 1);
INSERT INTO SALA_EQUIPAMENTO (SALA_ID_SALA, EQUIPAMENTO_ID_EQUIPAMENTO)  VALUES (1, 2);
INSERT INTO SALA_EQUIPAMENTO (SALA_ID_SALA, EQUIPAMENTO_ID_EQUIPAMENTO)  VALUES (1, 3);
INSERT INTO SALA_EQUIPAMENTO (SALA_ID_SALA, EQUIPAMENTO_ID_EQUIPAMENTO)  VALUES (2, 1);
INSERT INTO SALA_EQUIPAMENTO (SALA_ID_SALA, EQUIPAMENTO_ID_EQUIPAMENTO)  VALUES (2, 2);
INSERT INTO SALA_EQUIPAMENTO (SALA_ID_SALA, EQUIPAMENTO_ID_EQUIPAMENTO)  VALUES (3, 1);
INSERT INTO SALA_EQUIPAMENTO (SALA_ID_SALA, EQUIPAMENTO_ID_EQUIPAMENTO)  VALUES (3, 3);
INSERT INTO SALA_EQUIPAMENTO (SALA_ID_SALA, EQUIPAMENTO_ID_EQUIPAMENTO)  VALUES (3, 3);
INSERT INTO SALA_EQUIPAMENTO (SALA_ID_SALA, EQUIPAMENTO_ID_EQUIPAMENTO)  VALUES (3, 3);
INSERT INTO SALA_EQUIPAMENTO (SALA_ID_SALA, EQUIPAMENTO_ID_EQUIPAMENTO)  VALUES (3, 3);
INSERT INTO SALA_EQUIPAMENTO (SALA_ID_SALA, EQUIPAMENTO_ID_EQUIPAMENTO)  VALUES (4, 1);
INSERT INTO SALA_EQUIPAMENTO (SALA_ID_SALA, EQUIPAMENTO_ID_EQUIPAMENTO)  VALUES (5, 2);
INSERT INTO SALA_EQUIPAMENTO (SALA_ID_SALA, EQUIPAMENTO_ID_EQUIPAMENTO)  VALUES (6, 3);
INSERT INTO SALA_EQUIPAMENTO (SALA_ID_SALA, EQUIPAMENTO_ID_EQUIPAMENTO)  VALUES (7, 2);
INSERT INTO SALA_EQUIPAMENTO (SALA_ID_SALA, EQUIPAMENTO_ID_EQUIPAMENTO)  VALUES (8, 3);
INSERT INTO SALA_EQUIPAMENTO (SALA_ID_SALA, EQUIPAMENTO_ID_EQUIPAMENTO)  VALUES (8, 3);
INSERT INTO SALA_EQUIPAMENTO (SALA_ID_SALA, EQUIPAMENTO_ID_EQUIPAMENTO)  VALUES (8, 1);
INSERT INTO SALA_EQUIPAMENTO (SALA_ID_SALA, EQUIPAMENTO_ID_EQUIPAMENTO)  VALUES (9, 3);

--AGENDAMENTO
INSERT INTO Agendamento(ID_AGENDAMENTO, DT_INICIO_AGENDAMENTO, DT_FINAL_AGENDAMENTO, ID_USUARIO, DS_AGENDAMENTO, ID_SALA) 
values(SEQ_AGENDAMENTO.nextval, TO_DATE('2016/12/16 09:00', 'yyyy/mm/dd hh24:mi'), TO_DATE('2016/12/15 12:00', 'yyyy/mm/dd hh24:mi'), 1, 'Reuniao com cliente', 1);
INSERT INTO Agendamento(ID_AGENDAMENTO, DT_INICIO_AGENDAMENTO, DT_FINAL_AGENDAMENTO, ID_USUARIO, DS_AGENDAMENTO, ID_SALA) 
values(SEQ_AGENDAMENTO.nextval, TO_DATE('2016/12/16 12:10', 'yyyy/mm/dd hh24:mi'), TO_DATE('2016/12/15 14:00', 'yyyy/mm/dd hh24:mi'), 1, 'Definicao de logo', 2);
INSERT INTO Agendamento(ID_AGENDAMENTO, DT_INICIO_AGENDAMENTO, DT_FINAL_AGENDAMENTO, ID_USUARIO, DS_AGENDAMENTO, ID_SALA) 
values(SEQ_AGENDAMENTO.nextval, TO_DATE('2016/12/16 15:00', 'yyyy/mm/dd hh24:mi'), TO_DATE('2016/12/15 16:00', 'yyyy/mm/dd hh24:mi'), 1, 'Conversao de dados', 3);
INSERT INTO Agendamento(ID_AGENDAMENTO, DT_INICIO_AGENDAMENTO, DT_FINAL_AGENDAMENTO, ID_USUARIO, DS_AGENDAMENTO, ID_SALA) 
values(SEQ_AGENDAMENTO.nextval, TO_DATE('2016/12/16 17:30', 'yyyy/mm/dd hh24:mi'), TO_DATE('2016/12/15 19:00', 'yyyy/mm/dd hh24:mi'), 1, 'Testes centralizados', 4);
INSERT INTO Agendamento(ID_AGENDAMENTO, DT_INICIO_AGENDAMENTO, DT_FINAL_AGENDAMENTO, ID_USUARIO, DS_AGENDAMENTO, ID_SALA) 
values(SEQ_AGENDAMENTO.nextval, TO_DATE('2016/12/17 17:30', 'yyyy/mm/dd hh24:mi'), TO_DATE('2016/12/16 19:00', 'yyyy/mm/dd hh24:mi'), 1, 'DOJO: integracao cont�nua', 1);
INSERT INTO Agendamento(ID_AGENDAMENTO, DT_INICIO_AGENDAMENTO, DT_FINAL_AGENDAMENTO, ID_USUARIO, DS_AGENDAMENTO, ID_SALA) 
values(SEQ_AGENDAMENTO.nextval, TO_DATE('2016/12/15 17:30', 'yyyy/mm/dd hh24:mi'), TO_DATE('2016/12/14 19:00', 'yyyy/mm/dd hh24:mi'), 1, 'Confraternizacao', 2);
INSERT INTO Agendamento(ID_AGENDAMENTO, DT_INICIO_AGENDAMENTO, DT_FINAL_AGENDAMENTO, ID_USUARIO, DS_AGENDAMENTO, ID_SALA) 
values(SEQ_AGENDAMENTO.nextval, TO_DATE('2016/12/16 13:30', 'yyyy/mm/dd hh24:mi'), TO_DATE('2016/12/15 17:30', 'yyyy/mm/dd hh24:mi'), 1, 'Aula Crescer', 9);


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
VALUES (SEQ_PARTICIPANTE.nextval, 4, 1, 'CONFIRMADO');//
INSERT INTO PARTICIPANTE(ID_PARTICIPANTE, ID_AGENDAMENTO, ID_USUARIO, STATUS) 
VALUES (SEQ_PARTICIPANTE.nextval, 5, 1, 'CONFIRMADO');
INSERT INTO PARTICIPANTE(ID_PARTICIPANTE, ID_AGENDAMENTO, ID_USUARIO, STATUS) 
VALUES (SEQ_PARTICIPANTE.nextval, 6, 1, 'CONFIRMADO');