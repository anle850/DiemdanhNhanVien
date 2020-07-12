<?php
	require"dbCon.php";

	$ma = $_POST['id'];
	

	$query = "DELETE FROM `nguoidung`WHERE `ma` = '$ma' ";

	if(mysqli_query($connect,$query)){
			echo "true";
		}else{
			echo "false";
		}
	
 ?>