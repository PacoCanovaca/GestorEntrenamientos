-- Crear nuevo ejercicio

INSERT INTO Ejercicio (3, 'Press banca con mancuernas alternativo', 'https://www.youtube.com/watch?v=GsXKE-I16O4', 'Las dos manos empiezan arriba. Solo baja 1 mientras la otra se queda arriba. Despues bajamos la contraria mientras que la mano que se movio inicialmente quedaria arriba. BAJAMOS DESPACIO COGIENDO AIRE Y SUBIMOS CON UNA MAYOR VELOCIDAD MIENTRAS SOLTAMOS EL AIRE.')

-- Actualizar ejercicio existente

UPDATE Ejercicio 
SET id_tipo_movimiento = 4, nombre = 'Press banca con mancuernas', enlace_video = 'https://www.youtube.com/watch?v=GsXKE-I16O4', explicacion = 'Las dos manos empiezan arriba. Solo baja 1 mientras la otra se queda arriba. Despues bajamos la contraria mientras que la mano que se movio inicialmente quedaria arriba. BAJAMOS DESPACIO COGIENDO AIRE Y SUBIMOS CON UNA MAYOR VELOCIDAD MIENTRAS SOLTAMOS EL AIRE.' 
WHERE id_ejercicio = 13;

-- Eliminar ejercicio existente

DELETE FROM Ejercicio 
WHERE id_ejercicio = 13;