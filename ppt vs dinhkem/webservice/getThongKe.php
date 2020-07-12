<?php
     $connect = mysqli_connect("localhost", "root", "", "db_nhanvien" );
     mysqli_query($connect,"SET NAMES 'utf8'");

    
    //  if ($_SERVER['REQUEST_METHOD'] == 'POST') {

    //  $thang = $_POST['thang'];
     //$email = $_POST['email'];
    //  $ma = "10052121";
    //  $email = "10052121@gmail.com";
    $thang = $_POST['thang'];
    
    $query = "SELECT count(msnv), ma, ten FROM ( ghidanh  INNER JOIN 
    nguoidung  ON ma = msnv  ) WHERE thang = $thang  GROUP BY ma, ten";
     //$data = mysqli_query($connect, $query); WHERE ma = '$ma' AND matkhau = '$pass'
    $response = mysqli_query($connect, $query);

    $result = array();
    $result['login'] = array();

    if ( $response )
    {
         //$row = mysqli_fetch_assoc($response);
         while($row = mysqli_fetch_assoc($response)) {
         

            $index["tennhanvien"] = $row['ten'];
            $index["manv"] = $row['ma'];
            $index["songay"] = $row['count(msnv)'];
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