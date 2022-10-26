<?php
session_start();
if(isset($_POST['storename'])){
    require_once "conn.php";
    require_once "encryption.php";


    $username =  $_POST['username'];
    $username = openssl_encrypt($username, $ciphering, $encryption_key, $options, $encryption_iv);
    $storename = $_POST['storename'];

    $sql = "INSERT INTO user_favorite_store (USER_ID, STORE_ID)
    VALUES ((SELECT ID FROM user WHERE UNAME='".$username."'), 
    (SELECT STORE_ID FROM store WHERE STORE_NAME = '".$storename."'));";

    if(!$conn->query($sql)){
        echo "failure";
    }else{
        echo "success";   
    }
}
?>