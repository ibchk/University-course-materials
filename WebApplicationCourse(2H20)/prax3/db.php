<?php

if ('127.0.0.1' == $_SERVER['REMOTE_ADDR']) {
    //Local server configuration. These are for local test database only so right now I do not care putting it up to Git,
    //Otherwise passwords should not be shared on Git
    define('DB_HOST', 'localhost');
    define('DB_USER', '');
    define('DB_PASS', '');
    define('DB_BASE', 'prax3');
    $db = connect();
} else {
    //Live server configuration
    define('DB_HOST', 'localhost');
    define('DB_USER', 's_ilboit');
    define('DB_PASS', '6Rlk8wqZ');
    define('DB_BASE', 'ilboit');
    $db = connect();
}

function connect()
{
    return new mysqli(DB_HOST, DB_USER, DB_PASS, DB_BASE);
}

if ($db->connect_errno) {
    echo "connection failed";
    exit();
} else {
    $db->close();
}

function db_get_list($query)
{
    $con = connect();
    $data = array();
    $result = mysqli_query($con, $query);
    while ($row = mysqli_fetch_object($result)) {
        array_push($data, $row);
    }
    $count = mysqli_num_rows($result);
    $con->close();
    if ($count > 0) {
        return $data;
    }
    return array();
}


function db_get($query)
{
    $con = connect();
    $result = mysqli_query($con, $query);
    $row = mysqli_fetch_array($result, MYSQLI_ASSOC);

    $count = mysqli_num_rows($result);
    $con->close();
    if ($count > 0) {
        return $row;
    } else {
        return null;
    }
}

function db_get_one($query, $sth, $type)
{

    $con = connect();
    $stmt = $con->prepare($query);
    $stmt->bind_param($type, $sth);
    $stmt->execute();
    $result = $stmt->get_result()->fetch_assoc();
    $con->close();
    return $result;

}

function db_get_two($query, $sth1, $sth2, $type)
{

    $con = connect();

    if ($stmt = $con->prepare($query)) {
        $stmt->bind_param($type, $sth1, $sth2);
        $stmt->execute();
        $result = $stmt->get_result()->fetch_assoc();
        $con->close();
        return $result;
    } else {
        echo $con->error;
    }
    return null;
}

function add_remove_update($query)
{
    $con = connect();
    $con->query($query);
    $con->close();
}

function add_remove_update2($query, $sth1, $sth2, $type)
{
    $con = connect();
    $stmt = $con->prepare($query);
    $stmt->bind_param($type, $sth1, $sth2);
    $stmt->execute();
    $con->close();
}

function add_remove_update3($query, $sth1, $sth2, $sth3, $type)
{
    $con = connect();
    $stmt = $con->prepare($query);
    $stmt->bind_param($type, $sth1, $sth2, $sth3);
    $stmt->execute();
    $con->close();
}

function add_remove_update7($query, $sth1, $sth2, $sth3, $sth4, $sth5, $sth6, $sth7, $type)
{
    $con = connect();
    $stmt = $con->prepare($query);
    $stmt->bind_param($type, $sth1, $sth2, $sth3, $sth4, $sth5, $sth6, $sth7);
    $stmt->execute();
    $con->close();
}

function add_remove_update1($query, $sth1, $type)
{
    $con = connect();
    $stmt = $con->prepare($query);
    $stmt->bind_param($type, $sth1);
    $stmt->execute();
    $con->close();
}