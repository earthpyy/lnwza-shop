<?php
    include("../connect.php");

// NAMESPACE
use LINE\LINEBot;
use LINE\LINEBot\HTTPClient\CurlHTTPClient;
// use LINE\LINEBot\TemplateActionBuilder\{UriTemplateActionBuilder, PostbackTemplateActionBuilder, MessageTemplateActionBuilder};
use LINE\LINEBot\MessageBuilder\{TextMessageBuilder, ImageMessageBuilder};
// use LINE\LINEBot\MessageBuilder\TemplateBuilder\{ButtonTemplateBuilder, CarouselTemplateBuilder, CarouselColumnTemplateBuilder, ConfirmTemplateBuilder};

// LINE BOT
$httpClient = new CurlHTTPClient($token);
$bot = new LINEBot($httpClient, ['channelSecret' => $secret]);

// Date & Time
date_default_timezone_set('Asia/Bangkok');
$dayofweek_def = ['', 'จันทร์', 'อังคาร', 'พุธ', 'พฤหัสบดี', 'ศุกร์', 'เสาร์', 'อาทิตย์'];
$month_def = ['', 'มกราคม', 'กุมภาพันธ์', 'มีนาคม', 'เมษายน', 'พฤษภาคม', 'มิถุนายน', 'กรกฎาคม', 'สิงหาคม', 'กันยายน', 'ตุลาคม', 'พฤศจิกายน', 'ธันวาคม'];

$dayofweek = $dayofweek_def[date('N')];
$date = date('j');
$month = $month_def[date('n')];
$year = date('Y') + 543;

// RECEIVE TOKEN
$content = file_get_contents('php://input');
$arrJson = json_decode($content, true);
$userType = $arrJson['events'][0]['source']['type'];
$userid = $arrJson['events'][0]['source'][$userType.'Id'];
$token = $arrJson['events'][0]['replyToken'];
$texte = $arrJson['events'][0]['message']['text'];
$postback = $arrJson['events'][0]['postback']['data'];

$text = explode(" ", $texte);
$count = count($text);

// CUSTOM FUNCTIONS
function dateBuilder($dat, $eng = true)
{
    global $month_def;
    $res = '';

    if ($eng)
    {
        return date('F j, Y', strtotime($dat));
    }

    if (preg_match('/^([0-9]{4})-([0-9]{2})-([0-9]{2})$/', $dat, $str))
    {
        // date
        $res .= ltrim($str[3], '0');
        $res .= ' ';
        // month
        $res .= $month_def[ltrim($str[2], '0')];
        $res .= ' ';
        // year
        $res .= $str[1] + 543;
    }

    return $res;
}

function getStatusName($status) {
    switch ($status) {
        case 'PREPARING':
            return "Preparing order";
        case 'PACKING':
            return "Packing order";
        case 'DELIVERING':
            return "Package delivering";
        case 'RECEIVED':
            return "Package received";
        case 'RETURN':
            return "Returning to shop";
        case 'CANCELLED':
            return "Cancelled";
        default:
            return "Error";
    }
}

if ($text[0] == 'สถานะ') {
    if ($count == 1) {
        $bot->replyText($token, "Usage: สถานะ [Order ID]");
    } else if ($count == 2) {
        $orderId = (int) $text[1];
        $datas = $db->select('status', 'status', ['orderId' => $orderId, 'LIMIT' => 1]);
        if (count($datas) > 0) {
            $bot->replyText($token, "Order ID: ".$orderId.PHP_EOL."Status: ".getStatusName($datas[0]));
        } else {
            $bot->replyText($token, "ไม่พบ Order ID ดังกล่าว หรือคุณใส่ Order ID ไม่ถูกต้อง!");
        }
    }
} else if ($text[0] == 'สินค้า') {
    if ($count == 1) {
        $bot->replyText($token, "Usage: สินค้า [Product ID]");
    } else if ($count == 2) {
        $productId = (int) $text[1];
        $datas = $db->select('products', '*', ['productId' => $productId, 'LIMIT' => 1]);
        if (count($datas) > 0) {
            $imgUrl = $datas[0]['photo'];
            $image = new ImageMessageBuilder($imgUrl, $imgUrl);
            $bot->pushMessage($userid, $image);
            $bot->replyText($token, 
            "Product ID: ".$productId.PHP_EOL.
            "Name: ".$datas[0]['name'].PHP_EOL.
            "Size: ".$datas[0]['name'].PHP_EOL.
            "Color: ".$datas[0]['color'].PHP_EOL.
            "Price: ฿".$datas[0]['price']);
        } else {
            $bot->replyText($token, "ไม่พบ Product ID ดังกล่าว หรือคุณใส่ Product ID ไม่ถูกต้อง!");
        }
    }
}

?>
