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
        <?php
            include "../login/encryption.php";
            session_start();
            $which_user = $_SESSION['usr'];
            $get_info = "SELECT * FROM user WHERE ID =".$which_user.";";
            $result_row = mysqli_query($db,$get_info);
            list($ID, $UNAME, $PASSWORD) = mysqli_fetch_row($result_row);
            $decryptedUNAME = openssl_decrypt($UNAME, $ciphering, $encryption_key, $options, $encryption_iv);
            $decryptedPASSWORD = openssl_decrypt($PASSWORD, $ciphering, $encryption_key, $options, $encryption_iv);

            //echo    "<input type=\"text\" class=\"textcss\"  value=\"".$ID."\" name=\"id\" />";
            echo    "<input type=\"text\"  class=\"textcss\" value=\"".$decryptedUNAME."\" name=\"uname\"/>";
            echo    "<input type=\"text\" class=\"textcss\"  value=\"".$decryptedPASSWORD."\" name=\"password\"/>";
        ?>
            <input type="submit" name="submit" class="button-9" value="Edit"/>
        
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

    //$store = validate($_POST['id']);
    $uname = validate($_POST['uname']);
    $password = validate($_POST['password']);

    include "../login/encryption.php";
    $uname = openssl_encrypt($uname, $ciphering, $encryption_key, $options, $encryption_iv);
    $password = openssl_encrypt($password, $ciphering, $encryption_key, $options, $encryption_iv);

    $query = "UPDATE user SET UNAME = '".$uname."', PASSWORD = '".$password."' WHERE user.ID =".$which_user.";";
    $edit_result = mysqli_query($db, $query);
    if(!$edit_result){
	    echo("<P>Error performing query: </P>");
	}
    header("Location: home.php");
}
?>
</body>

</html>

