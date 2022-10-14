<?php
// Create 4 variables to store these information
$server="localhost";
$username="root";
$password="";
$database = "bartugni100_dvgc22_product";


/*($db = mysqli_connect($server, $username, $password, $database);

if(!$db){
    echo("Could not connect to MySQL server!" . mysqli_connect_error());
    }

*/
// Create connection
$conn = new mysqli($server, $username, $password, $database);
// Check connection
if ($conn->connect_error) {
  die("Connection failed: " . $conn->connect_error);
}
?>
