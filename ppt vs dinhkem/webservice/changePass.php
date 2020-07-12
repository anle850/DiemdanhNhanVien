<?php
	require"dbCon.php";

	$ma = $_POST['ma'];
	$matkhau = $_POST['pass'];

	$matkhau = password_hash($matkhau, PASSWORD_DEFAULT);


	$query = "UPDATE `nguoidung` SET `matkhau` = '$matkhau' WHERE `ma` = '$ma' ";

	if(mysqli_query($connect,$query)){
			echo "true";
		}else{
			echo "false";
		}
	
 ?>