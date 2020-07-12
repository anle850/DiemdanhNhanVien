<?php
require "dbCon.php";
date_default_timezone_set('Asia/Ho_Chi_Minh');

// $hoten = $_POST['hotenSV'];
// $namsinh = $_POST['namsinhSV'];
// $diachi = $_POST['diachiSV'];
// $ngaylam = $_POST['ngaylamSV'];

$date = date("Y-m-d H:i:s",time()) ;

$manv = $_POST['manv'];

$month = date("m",time()) ;
// $ngaylam = "2019-07-06";

// $manv = "17503131";

// $date = date("Y-m-d H:i:s",$po) ;  
// $date->format('Y-m-d H:i:s');
//echo date("Y-m-d",$po );


//$query = "INSERT INTO `nguoidung` VALUES('$ma', '$matkhau', '$status', '$ten', '$email')";
$query = "INSERT INTO ghidanh VALUES (null,'$manv','$month', '$date')";

$query1 = "SELECT * FROM ghidanh WHERE msnv = '$manv'";

// mysqli_query($connect,$query);

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
$ngay = date("Y-m-d",time()) ;


if(  $result ) {
    $a = "true";
    while($row = mysqli_fetch_assoc($result)){
        if("$ngay" == date("Y-m-d", strtotime($row['ngaytao'])) )
        $a = "false";
    }
    if ($a == "true") {
        mysqli_query($connect,$query);
    }

    echo $a;
    
}else{
    
    if (mysqli_query($connect,$query)) {
        echo "true";
    }
}




//  $query = "INSERT INTO ghidanh VALUES (null,'$manv','$month', '$date')";

// if(mysqli_query($connect, $query)){
//     // thành công
//     echo "success";
// }else{
//     //lỗi
//     echo "error";
// }
?>