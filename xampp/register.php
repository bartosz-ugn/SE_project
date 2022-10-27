<?php
if(isset($_POST['email']) && isset($_POST['password'])){
    // Include the necessary files
    require_once "conn.php";
    require_once "validate.php";
    require_once "encryption.php";
    // Call validate, pass form data as parameter and store the returned value
    $email = validate($_POST['email']);
    $password = validate($_POST['password']);
    //ENCRYPTION
    $encryptedEmail = openssl_encrypt($email, $ciphering, $encryption_key, $options, $encryption_iv);
    $encryptedPassword = openssl_encrypt($password, $ciphering, $encryption_key, $options, $encryption_iv);
    // Create the SQL query string. We'll use md5() function for data security. It calculates and returns the MD5 hash of a string
    $sql = "INSERT INTO `user` (`ID`, `UNAME`, `PASSWORD`) VALUES (NULL, '".$encryptedEmail."', '".$encryptedPassword."');";
    // Execute the query. Print "success" on a successful execution, otherwise "failure".
    if(!$conn->query($sql)){
        echo "failure";
    }else{
        echo "success";   
    }
}
?>