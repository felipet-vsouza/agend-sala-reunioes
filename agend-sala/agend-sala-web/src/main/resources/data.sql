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
Insert into EXPORT_TABLE (ID_USUARIO,EMAIL_USUARIO,NOME_USUARIO,SENHA_USUARIO) values ('1','felipe.souza@cwi.com.br','Felipe Souza','$2a$10$ofd6tqa.yBM3N0BgNqFbTuBnO3EwoGbx6Np2GizCjMkvJLZbjlxxK');
Insert into EXPORT_TABLE (ID_USUARIO,EMAIL_USUARIO,NOME_USUARIO,SENHA_USUARIO) values ('2','henrique.ostermann@cwi.com.br','Henrique Ostermann','$2a$10$RIJaNA3vfkRxoSpqeJ83K.cWGACTtve8EfWENLWiSVOOur9DwHKDG');
Insert into EXPORT_TABLE (ID_USUARIO,EMAIL_USUARIO,NOME_USUARIO,SENHA_USUARIO) values ('3','henrique.mentz@cwi.com.br','Henrique Mentz','$2a$10$V7T2i916qHS6y4ZTOsB6DeySxFYttRsWxb1oj9ZSrYnKlJJ3Z8WRu');

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


