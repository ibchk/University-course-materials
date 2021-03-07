<?php
if (!empty($_SERVER['HTTPS']) && ('on' == $_SERVER['HTTPS'])) {
    $uri = 'https://';
} else {
    $uri = 'http://';
}
$uri .= $_SERVER['HTTP_HOST'];
header('Location: ' . $uri . '/~ilboit/iti0205-2020/prax3/pages/login.php');
exit;
?>
Something is wrong with the XAMPP installation :-(


