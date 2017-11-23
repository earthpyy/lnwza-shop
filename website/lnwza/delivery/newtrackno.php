<?php
    include("functions.php");

    if (!isset($_GET['shopId']) || !isset($_GET['postCode'])) {
        return;
    }
    $shopId = $_GET['shopId'];
    $postCode = $_GET['postCode'];

    echo addPackage($shopId, $postCode);
?>