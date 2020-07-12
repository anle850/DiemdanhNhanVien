<?php
     $connect = mysqli_connect("localhost", "root", "", "db_nhanvien" );
     mysqli_query($connect,"SET NAMES 'utf8'");

    
    //  if ($_SERVER['REQUEST_METHOD'] == 'POST') {

     $ma = $_POST['id'];
     $matkhau = $_POST['pass'];
    //  $ma = "17503131";
    //  $matkhau = "12";
    
     $query = "SELECT * FROM nguoidung WHERE ma = '$ma' ";
     //$data = mysqli_query($connect, $query); WHERE ma = '$ma' AND matkhau = '$pass'
    $response = mysqli_query($connect, $query);
    // if($data->num_row > 0){
    //     echo "thanh Cong";
    // }
    // else echo "Loi";
    //password_verify($matkhau, $row)
    

    $result = array();
    $result['login'] = array();

    if ( mysqli_num_rows($response) === 1)
    {
         $row = mysqli_fetch_assoc($response);
        
        if ( password_verify($matkhau, $row['matkhau'])) {
            $index["status"] = $row['status'];
            
            $index["id"] = $row['ma'];
            $index["name"] = $row['ten'];
            $index["pass"] = "1";
            array_push($result["login"], $index);

            $result["success"] = "1";
            $result["messsge"] = "success";
            
            echo json_encode($result);
            mysqli_close($connect);
        } else if( $matkhau == $row['matkhau']) {
            $index["status"] = $row['status'];
            
            $index["id"] = $row['ma'];
            $index["name"] = $row['ten'];
            $index["pass"] = "0";
            array_push($result["login"], $index);

            $result["success"] = "1";
            $result["messsge"] = "success";
            
            echo json_encode($result);
            mysqli_close($connect);
            } else{

                $result["success"] = "0" ;
                $result["messsge"] = "error";
                
                echo json_encode($result);
                mysqli_close($connect);
            }
         
    }else{

        $result["success"] = "0" ;
        $result["messsge"] = "error";
        
        echo json_encode($result);
        mysqli_close($connect);
    }
    
        
     //}

    // while ($row = mysqli_fetch_assoc($data)){
    //     array_push($arryrr, new cv( $row["status"] ));
    // }
    // echo json_encode($arryrr);

    // class cv{
    //     function cv( $status){
    //         // $this -> maNguoiDung = $id;
    //         // $this -> matKhau = $password;$row['ma'], $row['matkhau'],
    //         $this -> status = $status;
    //     }
    // }

   
?>