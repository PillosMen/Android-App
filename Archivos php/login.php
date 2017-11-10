<?php
include('functions.php');
$cod=$_POST['codigo'];
$pass=$_POST['password'];

if($resultset=getSQLResultSet("SELECT * FROM `usuarios` WHERE codigo='$cod' AND password='$pass' ")){
	while ($row = $resultset->fetch_array(MYSQLI_NUM)){
		echo json_encode($row);
	}
}

?>


