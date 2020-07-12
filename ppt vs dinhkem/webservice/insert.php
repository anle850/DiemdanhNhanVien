<?php
$connect = mysqli_connect("localhost", "root", "", "db_nhanvien");
mysqli_query($connect, "SET NAMES 'utf8'");

 if ($_SERVER['REQUEST_METHOD'] == 'POST') {

    $ma = $_POST['id'];
    $matkhau = $_POST['pass'];
    $status = "0";
    $ten = $_POST['ten'];
    $email = $_POST['email'];
    // $ma = "17520229";
    // $matkhau = "1";
    // $status = "1";
    // $ten = "An";

    // $matkhau = password_hash($matkhau, PASSWORD_DEFAULT);

    $query = "INSERT INTO `nguoidung` VALUES('$ma', '$matkhau', '$status', '$ten', '$email')";

    $query1 = "SELECT ma FROM nguoidung WHERE ma = '$ma'";

    $result = mysqli_query($connect,$query1);
    // if (mysqli_query($connect, $query)) {
    //     $result["success"] = "1";
    //     $result["message"] = "success";

    //     echo json_encode($result);
    //     mysqli_close($connect);
    // } else {
    //     $result["success"] = "0";
    //     $result["message"] = "error";

    //     echo json_encode($result);
    //     mysqli_close($connect);
    // }

    if( mysqli_num_rows( $result) ) {
		echo "false";
		
	}else{
		if (mysqli_query($connect,$query)) {
			echo "true";
		}
	}
// }
    // class nguoidung{
    //     function nguoidung($ma, $matkhau, $status, $ten){
    //         this-> mA = $ma;
    //         this-> matKhau = $matkhau;
    //         this-> staTus = $status;
    //         this-> Ten = $ten;
    //     }
 }
?>