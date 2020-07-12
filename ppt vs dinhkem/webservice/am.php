<?php
    
    require"dbCon.php";
    date_default_timezone_set('Asia/Ho_Chi_Minh');
    // $q = strtotime('Jul 7, 2020');
    // $date = date('m', time());
    // $w = strtotime('2020-07-06 10:51:46');

    // if ($q == $w) echo "Ngon";
    if("2020-07-09" == date("Y-m-d",time())) echo  "true" ;
    else echo "false";
    // date_default_timezone_set('Asia/Ho_Chi_Minh');
      
    
    //echo date("Y-m-d H:i:s",time());  
?>