<?php
$connect = mysqli_connect("localhost", "root", "", "db_nhanvien");
mysqli_query($connect, "SET NAMES 'utf8'");

//  if ($_SERVER['REQUEST_METHOD'] == 'POST') {

    $ma = $_POST['id'];
    $matkhau = $_POST['pass'];
    $ten = $_POST['ten'];
    $email = $_POST['email'];
    $activity = $_POST['activity'];
    // $ma = "1009900";
    // $matkhau = "2";
    // $email = "";
    // $ten = "Giáo Viên Phản Biện 2";

    // $matkhau = password_hash($matkhau, PASSWORD_DEFAULT);

    if($activity == "0"){
        $matkhau = password_hash($matkhau, PASSWORD_DEFAULT);
    }

    if(strlen($matkhau) > 0 && strlen($ten) > 1 && strlen($email) > 1  ){
        $query = "UPDATE `nguoidung` SET matkhau = '$matkhau', ten = '$ten', email ='$email'
        WHERE  ma = '$ma'";
        $result = mysqli_query($connect,$query);

        if(  $result) {
            echo "true";
        }else{
            echo "false";
            }
    } else if (strlen($ten) > 1 && strlen($email) > 1  ){
        $query = "UPDATE `nguoidung` SET ten = '$ten', email ='$email'
        WHERE  ma = '$ma'";
        $result = mysqli_query($connect,$query);

        if(  $result) {
            echo "true";
        }else{
            echo "false";
            }
    } else if (strlen($ten) > 1 && strlen($matkhau) > 0  ){
        $query = "UPDATE `nguoidung` SET matkhau = '$matkhau', ten = '$ten'
        WHERE  ma = '$ma'";
        $result = mysqli_query($connect,$query);

        if(  $result) {
            echo "true";
        }else{
            echo "false";
            }
    } else if (strlen($matkhau) > 0 && strlen($email) > 1  ){
        $query = "UPDATE `nguoidung` SET matkhau = '$matkhau', email ='$email'
        WHERE  ma = '$ma'";
        $result = mysqli_query($connect,$query);

        if(  $result) {
            echo "true";
        }else{
            echo "false";
            }
    } else if (strlen($email) > 1  ){
        $query = "UPDATE `nguoidung` SET email ='$email'
        WHERE  ma = '$ma'";      $result = mysqli_query($connect,$query);

        if(  $result) {
            echo "true";
        }else{
            echo "false";
            }
    } else if (strlen($matkhau) > 1  ){
        $query = "UPDATE `nguoidung` SET matkhau = '$matkhau'
        WHERE  ma = '$ma'";
        $result = mysqli_query($connect,$query);

        if(  $result) {
            echo "true";
        }else{
            echo "false";
            }
    } else if(strlen($ten) > 1  ){
        $query = "UPDATE `nguoidung` SET ten = '$ten'
        WHERE  ma = '$ma'";
        $result = mysqli_query($connect,$query);

        if(  $result) {
            echo "true";
        }else{
            echo "false";
            }
    }


?>