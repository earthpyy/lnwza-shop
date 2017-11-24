<?php
    include("connect.php");

// NAMESPACE
use LINE\LINEBot;
use LINE\LINEBot\HTTPClient\CurlHTTPClient;

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

?>
