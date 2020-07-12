<?php
     $connect = mysqli_connect("localhost", "root", "", "db_nhanvien" );
     mysqli_query($connect,"SET NAMES 'utf8'");

     date_default_timezone_set('Asia/Ho_Chi_Minh');

    
    //  if ($_SERVER['REQUEST_METHOD'] == 'POST') {

     $ma = $_POST['id'];
     //$email = $_POST['email'];
    //  $ma = "17503131";
    //  $thang = "2020-7-08";
     $month = date("m",time());
    //  $email = "10052121@gmail.com";
    // date('m', time())
    // $query = "SELECT * FROM ghidanh WHERE msnv = '$ma'";
     //$data = mysqli_query($connect, $query); WHERE ma = '$ma' AND matkhau = '$pass'
    $query2 = "SELECT count(msnv), msnv FROM ghidanh WHERE msnv = '$ma' AND thang = $month GROUP BY msnv" ;
    
    //$response = mysqli_query($connect, $query);
    $response2 = mysqli_query($connect, $query2);
    $result = array();
    $result['login'] = array();

    if ( $response2 )
    {
         //$row = mysqli_fetch_assoc($response);
         while($row = mysqli_fetch_assoc($response2)) {
            
            
                $index["songay"]   = $row['count(msnv)'];
                $result["success"] = "1";
                $result["messsge"] = "success";
                array_push($result["login"], $index);
            
            
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