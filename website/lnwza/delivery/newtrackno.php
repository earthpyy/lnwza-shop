<?php
    include("functions.php");

    // just sample system so... just "GET" it!
    if (!isset($_GET['shopId']) || !isset($_GET['postCode'])) {
        return;
    }
    $shopId = $_GET['shopId'];
    $postCode = $_GET['postCode'];

    echo addPackage($shopId, $postCode);
?>