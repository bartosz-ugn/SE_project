<?php
include "connProd.php";

$stmt = $conn->prepare("SELECT IMAGE, TITLE, CAMPAIGN, PRICE, STORE FROM product");

$stmt ->execute();
$stmt ->bind_result($image, $title, $campaign, $price, $store);

$products = array();

while($stmt -> fetch()){
  $temp = array();
 
  $temp['image'] = $image;
  $temp['title'] = $title;
  $temp['campaign'] = $campaign;
  $temp['price'] = $price;
  $temp['store'] = $store;

  array_push($products, $temp);
}

//echo json_encode($products);
echo json_encode($products,JSON_UNESCAPED_SLASHES);
?>