<?php 

include "dbconnection.php";
include "../login/encryption.php";
session_start();
$query = "SELECT * FROM user";
$result = mysqli_query($db,$query);
if(!$result){
     echo("<P>Error performing query: </P>");
}

if (isset($_SESSION['ADMIN_NAME'])) {

 ?>

<!DOCTYPE html>

<html>

<head>
<link rel="stylesheet" type="text/css" href="home2_style.css">
    <title>USER DATABASE</title>


</head>

<body>

          <h3>Hello, <?php echo $_SESSION['ADMIN_NAME']; ?>! To edit or delete, select the user</h3>

          <input type="submit" form="myform1" name="edit" class="button-9" value="Edit"/>
          <input type="submit" form="myform1" name="delete" style="background-color: #FF0000;" class="button-9" value="Delete"/>
          

          <a href="add_user.php"><button class="button-9" style="margin-left: 10%; background-color: #50C878;">Add</button><A>
     <div style="float: right;">
          <a href="logout.php"><button class="button-9">Log out</button></a>
     </div>

     <div>
     <table border="1">
     <tr>   
          <th style='width: auto;'>Select</th>
          <th style='width: auto;'>ID</th>
          <th style='width: auto;'>User Name</th>
          <th style='width: auto;'>Password</th>

     </tr>
     <?php
          echo "<form id=\"myform1\" method=\"post\">";
          while (list($ID, $UNAME, $PASSWORD) = mysqli_fetch_row($result))
          {
          $decryption1 = openssl_decrypt ($UNAME, $ciphering, 
               $decryption_key, $options, $decryption_iv);
          $decryption2 = openssl_decrypt ($PASSWORD, $ciphering, 
               $decryption_key, $options, $decryption_iv);
          echo "<tr>";
          echo "<td><input type=\"radio\" id=".$ID." value=\"".$ID."\" name=\"radio\"></td>";
          echo "<label for=\"".$ID."\">";
          echo "<td>" . $ID . "</td></label>";
          echo "<td>" . $decryption1 . "</td>";
          echo "<td>" . $decryption2 . "</td>";
          echo "</tr>";
          }
          echo "</form>";

          // EDITING USER
          if(isset($_POST['edit'])){
               if(isset($_POST['radio'])){
                  
                    $_SESSION['usr'] = $_POST['radio'];
                    echo("<P>Value of tobedeleted: </P>".$_SESSION['usr']);
                    header("Location: edit_user.php");
                    
               }
          }
          //DELETING USER
          if(isset($_POST['delete'])){
               if(isset($_POST['radio'])){
                    $to_be_deleted = $_POST['radio'];
                    $query = "DELETE FROM user WHERE ID =".$to_be_deleted.";";
                    $result = mysqli_query($db, $query);
                    if(!$result){
                         echo("<P>Error performing query: </P>");
                    }else{
                         header("Refresh:0");
                    }

               }
          }

     ?>
     </table>
     </div>
</body>

</html>

<?php 

}else{

     header("Location: productsdb_login.php");

     exit();

}

 ?>