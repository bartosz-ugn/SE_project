<?php
    $ciphering = "AES-128-CTR";
    $iv_length = openssl_cipher_iv_length($ciphering);
    $options   = 0;
    $encryption_iv= '1234567891011121';
    $encryption_key= "dvgc22";
    $decryption_iv= '1234567891011121';
    $decryption_key= "dvgc22";
?>