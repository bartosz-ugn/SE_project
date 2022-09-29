<?php

    $db = mysqli_connect("localhost", "root", "", "bartugni100_dvgc22_product");

    if(!$db){
        echo("Could not connect to MySQL server!" . mysqli_connect_error());
        }
 
 
