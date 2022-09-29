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
        <input type="text" class="textcss"  name="store" placeholder="Store" />
        <input type="text" class="textcss"  name="category" placeholder="Category" />
        <input type="text" class="textcss"  name="title" placeholder="Title/Name" />
        <input type="text" class="textcss"  name="campaign" placeholder="Campaign" />
        <input type="text" class="textcss"  name="price" placeholder="Price" />
        <input type="submit" name="submit" class="button-9" value="Add"/>
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

     
     $query = "INSERT INTO product (`STORE`, `CATEGORY`, `IMAGE`, `TITLE`, `CAMPAIGN`, `PRICE`) 
               VALUES ('".$store."', '".$category."', '', '".$title."', '".$campaign."', '".$price."');";
     $result = mysqli_query($db, $query);
     if(!$result){
	 echo("<P>Error performing query: </P>");
	 }

}
?>
</body>

</html>

