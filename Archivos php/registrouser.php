<?php include ('functions.php');

$username=$_GET['username'];
$nombre=$_GET['nombre'];
$password=$_GET['password'];
$email=$_GET['email'];

ejecutarSQLCommand("INSERT INTO  `usuarios` (nombre, username, password, email) VALUES ('$nombre','$username','$password','$email') ");

?>