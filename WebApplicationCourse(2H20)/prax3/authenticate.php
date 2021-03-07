<?php
include 'db.php';

function log_in()
{

    if (empty($_POST['email_log'] && $_POST['password_log'])) {
        // Could not get the data that should have been sent.
        echo 'Please fill both the email address and password fields!';
    } elseif (!filter_var($_POST['email_log'], FILTER_VALIDATE_EMAIL)) {
        echo 'Wrong email address!';
    } else {
        $email = htmlspecialchars($_POST['email_log']);
        $password = crypt(htmlspecialchars($_POST['password_log']),'$2a$09$anexamplestringforsalt$');
        $new_query = "SELECT * FROM users WHERE email =? and password =?";
        $res = db_get_two($new_query, $email, $password, "ss");

        if ($res == null) {
            echo "Wrong email or password!";
        } else {
            session_start();
            // Store data in session variables
            $_SESSION['loggedin'] = true;
            $_SESSION["id"] = $res['id'];
            $_SESSION["name"] = $res['name'];
            $_SESSION["username"] = $res['username'];
            $_SESSION["city"] = $res['city'];
            $_SESSION["country"] = $res['country'];
            $_SESSION["description"] = $res['description'];
            $_SESSION["email"] = $res['email'];
            $_SESSION["created_at"] = $res['created_at'];
            $_SESSION["updated_at"] = $res['updated_at'];
            header('Location: feed.php');
            exit();
        }

    }
}

