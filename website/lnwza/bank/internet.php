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
    <h2>lnwza SHOP</h2>
    pay by internet
    <p><b>SHOP ID: </b> <?=$_GET['shopId']?></p>
    <p><b>AMOUNT: </b> <?=$_GET['amount']?></p>

    <input type="button" value="Click to success!" onclick="success()">
    <input type="button" value="Click to fail!" onclick="fail()">

</body>
</html>