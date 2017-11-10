<?php
include('functions.php');
$id=$_GET['codigo'];

//SELECT * FROM `perfil` WHERE codigo='$id'

if($resultset=getSQLResultSet("SELECT u.nombre, p.codigo, p.carrera, p.experencia, p.cursos FROM usuarios u INNER JOIN perfil p ON u.username = p.codigo WHERE p.codigo='$id'")){
	while ($row = $resultset->fetch_array(MYSQLI_NUM)){
		echo json_encode($row);
	}
}

?>


