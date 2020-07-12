<?php
     require"dbCon.php";

    
    //  if ($_SERVER['REQUEST_METHOD'] == 'POST') {

    //  $ma = $_POST['id'];
     //$email = $_POST['email'];
     //$ma = $_POST['ma'];
    //  $email = "10052121@gmail.com";
    
    $query = "SELECT ten, mathongbao, tenthongbao, noidung, ngaytao FROM ( nguoidung  INNER JOIN 
    thongbao  ON nguoidung.ma = thongbao.ma )";
     //$data = mysqli_query($connect, $query); WHERE ma = '$ma' AND matkhau = '$pass'
    $response = mysqli_query($connect, $query);

    $result = array();
    $result['login'] = array();

    if ( $response )
    {
         //$row = mysqli_fetch_assoc($response);
         while($row = mysqli_fetch_assoc($response)) {
         

            $index["mathongbao"] = $row['mathongbao'];
            $index["ten"] = $row['ten'];
            $index["tenthongbao"] = $row['tenthongbao'];
            $index["noidung"] = $row['noidung'];
            $index["ngaytao"] = $row['ngaytao'];
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