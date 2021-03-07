<?php
session_start();
include '../components/header.php';
include '../friend.php';

$search = htmlspecialchars($_GET['ask']);
find_all_users(strtoupper($search));
?>
<html>
<head>
    <meta charset="utf-8">
    <title>Sample App</title>
    <link rel="stylesheet" type="text/css" href="../css/style.css">
</head>
<body>
</body>
</html>
