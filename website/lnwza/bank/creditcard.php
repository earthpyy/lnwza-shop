<?php
    include("../connect.php");

    function getShopName($id) {
        global $db;
        $datas = $db->select('shop', 'shopName', ['id' => $id, 'LIMIT' => 1]);
        return $datas[0];
    }

?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>lnwza Bank</title>

<script type="text/javascript">
function success() {
    app.success();
}

function fail() {
    app.fail();
}
</script>
</head>
<body style="text-align: center;">
    <h2>lnwza BANK</h2>
    pay by credit card
    <p><b>SHOP ID: </b> <?=$_GET['shopId']?></p>
    <p><b>SHOP NAME: </b> <?=getShopName($_GET['shopId'])?></p>
    <p><b>AMOUNT: </b> <?=$_GET['amount']?></p>

    <input type="button" value="Pay!" onclick="success()">
    <input type="button" value="Cancel" onclick="fail()">

</body>
</html>