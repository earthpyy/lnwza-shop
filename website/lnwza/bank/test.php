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
    document.getElementById("test").innerHTML = "DONE!";
}

function fail() {
    app.fail();
    document.getElementById("test").innerHTML = "FAIL!";
}
</script>
</head>
<body>
    <p id="test">HAHAHA</p>
    <p><b>SHOP ID: </b> <?=$_GET['shopId']?></p>
    <p><b>AMOUNT: </b> <?=$_GET['amount']?></p>

    <input type="button" value="Click to success!" onclick="success()">
    <input type="button" value="Click to fail!" onclick="fail()">

</body>
</html>