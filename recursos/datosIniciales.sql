INSERT INTO BIBLIOTECA (ID, CODIGO_BIBLIOTECA, FECHA_REGISTRO, NOMBRE_BIBLIOTECA, MUNICIPIO_ID) VALUES ('1', 'BBLB01', TO_DATE('2018-07-01 18:39:53', 'YYYY-MM-DD HH24:MI:SS'), 'Biblioteca 404', '1');
INSERT INTO USUARIO (ID, APELLIDO, EMAIL, ENABLE, FECHA_NACIMIENTO, FECHA_REGISTRO, FOTO_PERFIL, GENERO, LUGAR_ESTUDIO, NOMBRE, NOMBRE_MADRE, NOMBRE_PADRE, NUMERO_TELEFONO, OCUPACION, PASSWORD, ROL, USERNAME, BIBLIOTECA_ID, MUNICIPIO_ID) VALUES ('1','super', 'correo@gmail.com', '1', TO_DATE('1996-11-01 18:31:53', 'YYYY-MM-DD HH24:MI:SS'), TO_DATE('2018-11-01 18:32:16', 'YYYY-MM-DD HH24:MI:SS'), 'none', 'masculino', 'UES', 'usuario', 'nose', 'nose', '75859685', 'Estudiante', 'B4xPjfMf6co=', 'SUPER_SUPERUSER', 'user', '1', '86');

INSERT INTO "TIPO_RECURSO" (ID, NOMBRE_TIPO_RECURSO) VALUES (1, 'Libro');
INSERT INTO "TIPO_RECURSO" (ID, NOMBRE_TIPO_RECURSO) VALUES (2, 'Trabajo graduacion');
INSERT INTO "TIPO_RECURSO" (ID, NOMBRE_TIPO_RECURSO) VALUES (3, 'Revista');
INSERT INTO "TIPO_RECURSO" (ID, NOMBRE_TIPO_RECURSO) VALUES (4, 'CD');
INSERT INTO "TIPO_RECURSO" (ID, NOMBRE_TIPO_RECURSO) VALUES (5, 'DVD');
INSERT INTO "TIPO_RECURSO" (ID, NOMBRE_TIPO_RECURSO) VALUES (6, 'Video');
INSERT INTO "TIPO_RECURSO" (ID, NOMBRE_TIPO_RECURSO) VALUES (7, 'Mapa');
INSERT INTO "TIPO_RECURSO" (ID, NOMBRE_TIPO_RECURSO) VALUES (8, 'Hemeroteca');
INSERT INTO "TIPO_RECURSO" (ID, NOMBRE_TIPO_RECURSO) VALUES (9, 'Plano');

INSERT INTO "FORMATO_RECURSO" (ID, NOMBRE_FORMATO) VALUES (1, 'Fisico');
INSERT INTO "FORMATO_RECURSO" (ID, NOMBRE_FORMATO) VALUES (2, 'PDF');
INSERT INTO "FORMATO_RECURSO" (ID, NOMBRE_FORMATO) VALUES (3, 'MP4');

INSERT INTO "RECURSO_BIB" (ID, DESCRIPCION_RECURSO_BIB, DIGITAL_RECURSO_BIB, FISICO_RECURSO_BIB, IMAGEN_RECURSO_BIB, NOMBRE_RECURSO_BIB, SINOPSIS_RECURSO_BIB, TOTAL_RECURSO_BIB,BIBLIOTECA_ID,TIPO_RECURSO_ID) VALUES ('1', 'Materia tranquila', 0, 1, 'a', 'Fisica resnick', 'sinopsis', '1', '1','1');
INSERT INTO "RECURSO_BIB" (ID, DESCRIPCION_RECURSO_BIB, DIGITAL_RECURSO_BIB, FISICO_RECURSO_BIB, IMAGEN_RECURSO_BIB, NOMBRE_RECURSO_BIB, SINOPSIS_RECURSO_BIB, TOTAL_RECURSO_BIB,BIBLIOTECA_ID,TIPO_RECURSO_ID) VALUES ('2', 'Materia dificil', 0, 1, 'b', 'Filosofia', 'Astralidades', '1', '1','1');

