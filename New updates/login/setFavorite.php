<?php
// Check if email and password are set
if(isset($_POST['email']) && isset($_POST['password'])){
    // Include the necessary files
    require_once "conn.php";
    require_once "validate.php";
    require_once "encryption.php";
    
    // Call validate, pass form data as parameter and store the returned value
    $email = validate($_POST['email']);
    $password = validate($_POST['password']);
    //Encryption of specified information to find in database
    $encryptedEmail = openssl_encrypt($email, $ciphering, $encryption_key, $options, $encryption_iv);
    $encryptedPassword = openssl_encrypt($password, $ciphering, $encryption_key, $options, $encryption_iv);
    // Create the SQL query string
    $sql = "SELECT * FROM user where UNAME='".$encryptedEmail."' AND PASSWORD='".$encryptedPassword."';";
    // Execute the query
    $result = $conn->query($sql);
    session_start();
    $_SESSION["username"] = $email;
 //   print_r($_SESSION);
    // If number of rows returned is greater than 0 (that is, if the record is found), we'll print "success", otherwise "failure"
    if($result->num_rows > 0){
        //print_r($_SESSION);
        echo "success";
    } else{
        // If no record is found, print "failure"
        echo "failure";
    }
}
?>