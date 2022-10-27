<?php
    session_start();
    require_once "conn.php";
    require_once "encryption.php";


    $username =  $_POST['username'];
    $username = openssl_encrypt($username, $ciphering, $encryption_key, $options, $encryption_iv);
    $storetag = $_POST['storetag'];

    $sql = "SELECT * FROM user_favorite_store
    WHERE user_favorite_store.STORE_ID IN (SELECT store.STORE_ID FROM store WHERE store.SOTRE_TAG = '".$storetag."') 
    AND user_favorite_store.USER_ID
    IN (SELECT user.ID FROM user WHERE user.UNAME = '".$username."');";

    $result = $conn->query($sql);

    if($result->num_rows > 0){;
        echo "success";
    } else{
        echo "failure";
    }
?>