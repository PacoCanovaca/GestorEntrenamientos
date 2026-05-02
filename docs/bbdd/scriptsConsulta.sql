-- Recuperar ejercicios

SELECT * 
FROM Ejercicio;

-- Recuperar tipos de movimientos

SELECT * 
FROM Tipo_movimiento;

-- Filtrar ejercicios por tipo de movimiento (ejemplo para tracción vertical)

SELECT *
FROM Ejercicio
WHERE id = 1;

-- Filtrar ejercicios por tipo de movimiento recibiendo el nombre del tipo de movimiento en lugar del id

SELECT e.*
FROM Ejercicio e
JOIN Tipo_movimiento tm ON e.id_tipo_movimiento = tm.id_tipo_movimiento
WHERE tm.nombre = 'Tracción vertical';