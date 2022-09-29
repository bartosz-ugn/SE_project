<?php 

include "dbconnection2.php";
session_start();
$is_empty = "SELECT * FROM Product";
$result_empty = mysqli_query($db,$is_empty);
if(!mysqli_num_rows($result_empty)){
     $query = "LOAD DATA INFILE 'output.txt' INTO TABLE Product FIELDS TERMINATED BY ',' ENCLOSED BY '|' LINES TERMINATED BY '\r\n'";
     $result = mysqli_query($db,$query);
     if(!$result)
     {
     echo("<P>Error performing query: </P>");
     }
}


if (isset($_SESSION['ADMIN_NAME'])) {



 ?>

<!DOCTYPE html>

<html>

<head>
<link rel="stylesheet" type="text/css" href="home2_style.css">
    <title>PRODUCT DATABASE</title>


</head>

<body>

          <h3>Hello, <?php echo $_SESSION['ADMIN_NAME']; ?>! To edit or delete, select the desired product</h3>

          <input type="submit" form="myform1" name="edit" class="button-9" value="Edit"/>
          <input type="submit" form="myform1" name="delete" style="background-color: #FF0000;" class="button-9" value="Delete"/>
          

          <a href="add.php"><button class="button-9" style="margin-left: 10%; background-color: #50C878;">Add</button><A>
     <div style="float: right;">
          <a href="logout.php"><button class="button-9">Log out</button></a>
     </div>

     <div>
     <table border="1">
     <tr>   
          <th style='width: auto;'>Select</th>
          <th style='width: auto;'>ID</th>
          <th style='width: 200px;'>Store</th>
          <th style='width: 200px;'>Category</th>
          <th style='width: auto;'>Title</th>
          <th style='width: 200px'>Campaign</th>
          <th style='width: auto;'>Price</th>
     </tr>
     <?php
          echo "<form id=\"myform1\" method=\"post\">";
          while (list($STORE, $CATEGORY, $IMAGE, $TITLE, $CAMPAIGN, $PRICE, $PROD_ID) = mysqli_fetch_row($result_empty))
          {
          echo "<tr>";
          echo "<td><input type=\"radio\" id=".$PROD_ID." value=\"".$PROD_ID."\" name=\"radio\"></td>";
          echo "<label for=\"".$PROD_ID."\">";
          echo "<td>" . $PROD_ID . "</td></label>";
          echo "<td>" . $STORE . "</td>";
          echo "<td>" . $CATEGORY . "</td>";
          echo "<td>" . $TITLE . "</td>";
          echo "<td>" . $CAMPAIGN . "</td>";
          echo "<td>" . $PRICE . "</td>";
          echo "</tr>";
          }
          echo "</form>";

          //$to_be_deleted;
          if(isset($_POST['edit'])){
               if(isset($_POST['radio'])){
                  
                    $_SESSION['prod'] = $_POST['radio'];
                    echo("<P>Value of tobedeleted: </P>".$_SESSION['prod']);
                    header("Location: edit.php");
                    
               }
          }
          if(isset($_POST['delete'])){
               if(isset($_POST['radio'])){
                    $to_be_deleted = $_POST['radio'];
                  //  echo("<P>Value of tobedeleted: </P>".$to_be_deleted);
                    $query = "DELETE FROM product WHERE PROD_ID =".$to_be_deleted.";";
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