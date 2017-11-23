<?php
    require_once("../connect.php");

    function addPackage($shopId, $postCode) {
        $trackNo = getTrackNo($postCode);
        $db->insert("packages", ["shopId" => $shopId, "postCode" => $postCode, "trackNo" => $trackNo, "cost" => getCost($postCode)]);
        return $trackNo;
    }

    function getCost($postCode) {
        switch (getGeo($postCode)) {
            case 1:
            case 6:
                return 40;
            case 3:
                return 30;
            case 4:
            case 5:
                return 20;
            case 2:
                return 10;
            default:
                return 0;
        }
    }

    function getTrackNo($postCode) {
        // just sample system so... random!
        $code = sprintf("%08d", rand(10000000, 99999999));
        switch (getGeo($postCode)) {
            case 1:
                return "NO".$code;
            case 2:
                return "CE".$code;
            case 3:
                return "NE".$code;
            case 4:
                return "WE".$code;
            case 5:
                return "EA".$code;
            case 6:
                return "SO".$code;
            default:
                return "ERROR!!!!!";
        }
    }

    function getGeo($postCode) {
        $amphur = $db->select("th_amphur", ["GEO_ID"], ["POSTCODE" => $postCode, "LIMIT" => 1]);
        return $amphur[0]["GEO_ID"];
    }

?>