<?php
        session_start();
        require_once "conn.php";
        require_once "encryption.php";

        $username =  $_POST['username'];
        $username = openssl_encrypt($username, $ciphering, $encryption_key, $options, $encryption_iv);

        $sql = "DELETE FROM user_favorite_store
        WHERE user_favorite_store.USER_ID
        IN (SELECT user.ID FROM user WHERE user.UNAME = '".$username."');";

        if(!$conn->query($sql)){
            echo "failure";
        }else{
            echo "success";   
        }
    
?>