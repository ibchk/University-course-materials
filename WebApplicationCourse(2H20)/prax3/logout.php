<?php
log_out();

function log_out(){
    $_SESSION['loggedin'] = false;
    session_start();
    session_destroy();
// Redirect to the login page:
    header('Location: ../prax3/index.php');
}
