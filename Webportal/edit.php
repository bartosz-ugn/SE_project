<?php 

include "dbconnection2.php";

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
            session_start();
            $which_product = $_SESSION['prod'];
            $get_info = "SELECT * FROM product WHERE PROD_ID =".$which_product.";";
            $result_row = mysqli_query($db,$get_info);
            list($STORE, $CATEGORY, $IMAGE, $TITLE, $CAMPAIGN, $PRICE, $PROD_ID) = mysqli_fetch_row($result_row);


            echo    "<input type=\"text\" class=\"textcss\"  value=\"".$STORE."\" name=\"store\" placeholder=\"Store\" />";
            echo    "<input type=\"text\"  class=\"textcss\" value=\"".$CATEGORY."\" name=\"category\" placeholder=\"Category\" />";
            echo    "<input type=\"text\" class=\"textcss\"  value=\"".$TITLE."\" name=\"title\" placeholder=\"Title/Name\" />";
            echo    "<input type=\"text\" class=\"textcss\"  value=\"".$CAMPAIGN."\" name=\"campaign\" placeholder=\"Campaign\" />";
            echo    "<input type=\"text\" class=\"textcss\"  value=\"".$PRICE."\" name=\"price\" placeholder=\"Price\" />";
           // session_destroy();
        ?>
            <input type="submit" name="submit" class="button-9" value="Edit"/>
        
    </form>
    <a href="home2.php"><button class="button-9">Go back</button><A>
<?php
if(isset($_POST['submit']))
{
    function validate($data){
        $data = trim($data);
        $data = stripslashes($data);
        $data = htmlspecialchars($data);
        return $data;
    }

    $store = validate($_POST['store']);
    $category = validate($_POST['category']);
    $title = validate($_POST['title']);
    $campaign = validate($_POST['campaign']);
    $price = validate($_POST['price']);

    $query = "UPDATE product SET STORE = '".$store."', IMAGE = '".$category."', 
    TITLE = '".$title."', CAMPAIGN = '".$campaign."', PRICE = '".$price."' WHERE PROD_ID =".$which_product.";";
    $edit_result = mysqli_query($db, $query);
    if(!$edit_result){
	    echo("<P>Error performing query: </P>");
	}
    header("Location: home2.php");
}
?>
</body>

</html>

