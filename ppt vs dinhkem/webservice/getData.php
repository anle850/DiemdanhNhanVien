<?php
     $connect = mysqli_connect("localhost", "id14296461_root", "Trian0853355523@", "id14296461_db_nhanvien" );
     mysqli_query($connect,"SET NAMES 'utf8'");

    
    //  if ($_SERVER['REQUEST_METHOD'] == 'POST') {

    //  $ma = $_POST['id'];
     //$email = $_POST['email'];
     $ma = "10052121";
    //  $email = "10052121@gmail.com";
    
    $query = "SELECT * FROM nguoidung WHERE status = '0' ";
     //$data = mysqli_query($connect, $query); WHERE ma = '$ma' AND matkhau = '$pass'
    $response = mysqli_query($connect, $query);

    $result = array();
    $result['login'] = array();

    if ( $response )
    {
         //$row = mysqli_fetch_assoc($response);
         while($row = mysqli_fetch_assoc($response)) {
         

            $index["email"] = $row['email'];
            $index["ten"] = $row['ten'];
            $index["id"] = $row['ma'];
            array_push($result["login"], $index);

            $result["success"] = "1";
            $result["messsge"] = "success";
            
            
         }
        echo json_encode($result);
        mysqli_close($connect);
    }else{

        $result["success"] = "0" ;
        $result["messsge"] = "error";
        
        echo json_encode($result);
        mysqli_close($connect);
    } 
?>