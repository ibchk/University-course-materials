<?php
session_start();
include '../components/header.php';
include '../friend.php';

?>
    <html>
    <head>
        <meta charset="utf-8">
        <title>Sample App</title>
        <link rel="stylesheet" type="text/css" href="../css/style.css">
    </head>
    <body>
    <?php friend_requests(); ?>

    <?php get_all_friends(); ?>
    </body>
    </html>
<?php
