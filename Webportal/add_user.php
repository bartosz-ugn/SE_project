<?php 

include "dbconnection.php";

 ?>

<!DOCTYPE html>

<html>

<head>
<link rel="stylesheet" type="text/css" href="add_style.css">
    <title>ADDING TO THE DATABASE</title>
</head>

<body>
    <form method="post"> 
        <input type="text" class="textcss"  name="uname" placeholder="User name" />
        <input type="text" class="textcss"  name="password" placeholder="Password" />
        <input type="submit" name="submit" class="button-9" value="Add"/>
    </form>
    <a href="home.php"><button class="button-9">Go back</button><A>
<?php
if(isset($_POST['submit']))
{   
    function validate($data){
        $data = trim($data);
        $data = stripslashes($data);
        $data = htmlspecialchars($data);
        return $data;
    }

    $uname = validate($_POST['uname']);
    $password = validate($_POST['password']);
    include "../login/encryption.php";
    $uname = openssl_encrypt($uname, $ciphering, $encryption_key, $options, $encryption_iv);
    $password = openssl_encrypt($password, $ciphering, $encryption_key, $options, $encryption_iv);
     
     $query = "INSERT INTO user (`UNAME`, `PASSWORD`) 
               VALUES ('".$uname."', '".$password."');";
     $result = mysqli_query($db, $query);
     if(!$result){
	 echo("<P>Error performing query: </P>");
	 }

}
?>
</body>

</html>

