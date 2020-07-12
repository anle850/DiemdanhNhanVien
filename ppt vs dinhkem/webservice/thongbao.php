<?php
    $connect = mysqli_connect("localhost", "root", "", "db_nhanvien");
    mysqli_query($connect, "SET NAMES 'utf8'");

    date_default_timezone_set('Asia/Ho_Chi_Minh');
    $ma = $_POST['ma'];
    $mathongbao = $_POST['mathongbao'];
    $td = $_POST['td'];
    $noidung = $_POST['nd'];
    // $mathongbao = "aq";
    $date = date("Y-m-d H:i:s",time()) ;  
    // $ma = "10052121";
    // $td = "Ú Òa";
    // $noidung = "Ú Òa Ú Òa";
    // $date->format('Y-m-d H:i:s');


    $query = "INSERT INTO `thongbao` VALUES( '$mathongbao' ,'$ma', '$td', '$noidung', '$date')";

    $query1 = "SELECT * FROM `thongbao` WHERE mathongbao = '$mathongbao' ";

    $result = mysqli_query($connect, $query1);

    if( mysqli_num_rows( $result) ) {
		echo "false";
		
	}else{
		if (mysqli_query($connect,$query)) {
			echo "true";
		}
	}

    // mysqli_query($connect,$query);

    // while($row = mysqli_fetch_assoc($response)){
    //     $i = strtotime($row['ngaytao']);
    //     $q = strtotime($date);
    //     echo ""+ $i + " KKK " + $q;
    // }

    
    
    //     if(func_name() == true){
    //         echo "true";
    //     } else echo "false";
    
    
    // function func_name()
    // {   
            
    //         while($row = mysqli_fetch_assoc($response)){
                
    //             $index = $row['ngaytao'];
                
    //             $ngay = strtotime($index);
    //             if(strtotime($date) == $ngay) return true;
    //         }
        
        
    // }
    
    
        // $row = mysqli_fetch_assoc($response);
        // $index['ngaytao'] = $row['ngaytao'];
        // array_push($result["login"], $index);
        // echo json_encode($result);
        //     mysqli_close($connect);
        // $ngay = strtotime($index);
        // if($date == $ngay) echo "true";
        // else echo "false";
        // }
        
    

 
?>