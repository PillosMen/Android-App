<?php include ('functions.php');

$codigo=$_GET['codigo'];
$nombre=$_GET['nombre'];
$carrera=$_GET['carrera'];
$experencia=$_GET['experencia'];
$cursos=$_GET['cursos'];

ejecutarSQLCommand("INSERT INTO  `perfil` (codigo, nombre, carrera, experencia, cursos) 
	VALUES ('$codigo','$nombre','$carrera','$experencia','$cursos' ) ");

?>