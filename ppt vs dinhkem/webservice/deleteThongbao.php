<?php
	require"dbCon.php";

	$ma = $_POST['id'];
	

	$query = "DELETE FROM `thongbao`WHERE `mathongbao` = '$ma' ";

	if(mysqli_query($connect,$query)){
			echo "true";
		}else{
			echo "false";
		}
	
 ?>