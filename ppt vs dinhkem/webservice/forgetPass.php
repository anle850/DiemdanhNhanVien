<?php
     $connect = mysqli_connect("localhost", "root", "", "db_nhanvien" );
     mysqli_query($connect,"SET NAMES 'utf8'");

    
    //  if ($_SERVER['REQUEST_METHOD'] == 'POST') {

     $ma = $_POST['id'];
     $email = $_POST['email'];
    //  $ma = "10052121";
    //  $email = "10052121@gmail.com";
    
     $query = "SELECT * FROM nguoidung WHERE ma = '$ma' AND email = '$email' ";
     //$data = mysqli_query($connect, $query); WHERE ma = '$ma' AND matkhau = '$pass'
    $response = mysqli_query($connect, $query);

    $result = array();
    $result['login'] = array();

    if ( mysqli_num_rows($response) === 1)
    {
         $row = mysqli_fetch_assoc($response);
         
         

            $index["status"] = $row['status'];
            $index["id"] = $row['ma'];
            array_push($result["login"], $index);

            $result["success"] = "1";
            $result["messsge"] = "success";
            
            echo json_encode($result);
            mysqli_close($connect);
    }else{

        $result["success"] = "0" ;
        $result["messsge"] = "error";
        
        echo json_encode($result);
        mysqli_close($connect);
    } 
?>