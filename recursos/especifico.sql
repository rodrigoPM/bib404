INSERT INTO "EDITORIAL" (ID, ANIO_PUBLICACION, NOMBRE_EDITORIAL) VALUES ('1', TO_DATE('2018-10-01 01:59:38', 'YYYY-MM-DD HH24:MI:SS'), 'tucan');

INSERT INTO "EDITORIAL" (ID, ANIO_PUBLICACION, NOMBRE_EDITORIAL) VALUES ('2', TO_DATE('2018-10-02 02:02:42', 'YYYY-MM-DD HH24:MI:SS'), 'santillana');

INSERT INTO "FORMATO_RECURSO" (ID, NOMBRE_FORMATO) VALUES ('1', 'mp3');
INSERT INTO "FORMATO_RECURSO" (ID, NOMBRE_FORMATO) VALUES ('2', 'mp4');


INSERT INTO "TIPO_RECURSO" (ID, NOMBRE_TIPO_RECURSO) VALUES ('1', 'libro');

INSERT INTO "TIPO_RECURSO" (ID, NOMBRE_TIPO_RECURSO) VALUES ('2', 'cd');

INSERT INTO "RECURSO_BIB" (ID, DESCRIPCION_RECURSO_BIB, DIGITAL_RECURSO_BIB, FISICO_RECURSO_BIB, IMAGEN_RECURSO_BIB, NOMBRE_RECURSO_BIB, SINOPSIS_RECURSO_BIB, TOTAL_RECURSO_BIB,BIBLIOTECA_ID,TIPO_RECURSO_ID) VALUES ('1', 'Materia tranquila', '1', '1', 'a', 'Fisica resnick', 'sinopsis', '1', '1','1');
INSERT INTO "RECURSO_BIB" (ID, DESCRIPCION_RECURSO_BIB, DIGITAL_RECURSO_BIB, FISICO_RECURSO_BIB, IMAGEN_RECURSO_BIB, NOMBRE_RECURSO_BIB, SINOPSIS_RECURSO_BIB, TOTAL_RECURSO_BIB,BIBLIOTECA_ID,TIPO_RECURSO_ID) VALUES ('2', 'Materia dificil', '2', '2', 'b', 'Filosofia', 'Astralidades', '2', '1','2');




INSERT INTO "DETALLE_RECURSO" (ID, FECHA_INGRESO_R_E, TOTAL_DIG_REC_BIB, TOTAL_FIS_REC_BIB, TOTAL_REC_BIB, RECURSO_BIB_ID) VALUES ('1', TO_DATE('2018-10-03 02:10:31', 'YYYY-MM-DD HH24:MI:SS'), '1', '1', '1', '1');
INSERT INTO "DETALLE_RECURSO" (ID, FECHA_INGRESO_R_E, TOTAL_DIG_REC_BIB, TOTAL_FIS_REC_BIB, TOTAL_REC_BIB, RECURSO_BIB_ID) VALUES ('2', TO_DATE('2018-10-04 02:11:58', 'YYYY-MM-DD HH24:MI:SS'), '2', '2', '2', '2');


INSERT INTO "RECURSO_ESP" (ID, CODIGO_REC_ESP, CONSULTA_INTERNA, EDICION_RECURSO, PRESTADO, VOLUMEN_RECURSO, DETALLE_RECURSO_ID, EDITORIAL_ID, FORMATO_RECURSO_ID) VALUES ('1', '1', '1', '1', '1', '1', '1', '1', '1');
INSERT INTO "RECURSO_ESP" (ID, CODIGO_REC_ESP, CONSULTA_INTERNA, EDICION_RECURSO, PRESTADO, VOLUMEN_RECURSO, DETALLE_RECURSO_ID, EDITORIAL_ID, FORMATO_RECURSO_ID) VALUES ('2', '2', '2', '2', '2', '2', '2', '2', '2');
