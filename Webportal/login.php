<?php 

 include "dbconnection.php";
 session_start();

 if (isset($_POST['uname']) && isset($_POST['password'])) {

    function validate($data){

       $data = trim($data);

       $data = stripslashes($data);

       $data = htmlspecialchars($data);

       return $data;

    }

    $uname = validate($_POST['uname']);

    $pass = validate($_POST['password']);

    if (empty($uname)) {

        header("Location: userdb_login.php?error=User name is required");

        exit();

    }else if(empty($pass)){

        header("Location: userdb_login.php?error=Password is required");

        exit();

    }else{ 

        $sql = "SELECT * FROM Admin WHERE ADMIN_NAME='".$uname."' AND ADMIN_PASSWORD='".$pass."';";
	
        $result = mysqli_query($db, $sql);
	
	$gowno = mysqli_num_rows($result);

        if (mysqli_num_rows($result) === 1) {

            $row = mysqli_fetch_assoc($result);

            if ($row['ADMIN_NAME'] === $uname && $row['ADMIN_PASSWORD'] === $pass) {

                echo "Logged in!";

                $_SESSION['ADMIN_NAME'] = $row['ADMIN_NAME'];

                header("Location: home.php");

                exit();

            }else{

                header("Location: userdb_login.php?error=1Incorect User name or password");

                exit();

            }
        }else{
		
	 // header("Location: userdb_login.php?error=".$uname . "---" . $gowno);

           header("Location: userdb_login.php?error=Incorect User name or password");

            exit();

        }

    }

}else{

    header("Location: userdb_loginz.php");

    exit();

}
