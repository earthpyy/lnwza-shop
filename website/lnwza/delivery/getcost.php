<?php
    include("functions.php");

    // just sample system so... just "GET" it!
    if (!isset($_GET['postCode'])) {
        return;
    }
    $postCode = $_GET['postCode'];

    echo getCost($postCode);

?>