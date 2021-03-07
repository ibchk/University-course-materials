<?php
include 'db.php';

function register()
{

    $name = htmlspecialchars($_POST['first_name']) . " " . htmlspecialchars($_POST['last_name']);
    $username = strtolower(htmlspecialchars($_POST['first_name'])) . "." . strtolower(htmlspecialchars($_POST['last_name']));
    $country = htmlspecialchars($_POST['country']);
    $city = htmlspecialchars($_POST['city']);
    $email = htmlspecialchars($_POST['email_reg']);
    $password = htmlspecialchars($_POST['password_reg']);
    $password_con = htmlspecialchars($_POST['con_password_reg']);
    $description = htmlspecialchars($_POST['description']);
    $uppercase = preg_match('@[A-Z]@', $password);
    $lowercase = preg_match('@[a-z]@', $password);
    $number = preg_match('@[0-9]@', $password);
    $specialChars = preg_match('@[^\w]@', $password);
    $password_crypt = crypt($password, '$2a$09$anexamplestringforsalt$');

    if (empty($name && $country && $city && $email && $password && $password_con) && $description) {
        // Could not get the data that should have been sent.
        echo 'Please fill all fields.';
    } elseif (!filter_var($email, FILTER_VALIDATE_EMAIL)) {
        echo 'Wrong email address.';
    } elseif (db_get_one("SELECT * FROM users WHERE email =?", $email, "s") != null) {
        echo 'There is somebody with the same email address';
    } elseif (db_get_one("SELECT * FROM users WHERE password =?", $password_crypt, "s") != null) {
        echo 'This password is not allowed';
    } elseif (!$uppercase || !$lowercase || !$number || !$specialChars || strlen($password) < 8) {
        echo 'Password should be at least 8 characters in length and should include at least one upper case letter, one number, and one special character.';
    } elseif ($password !== $password_con) {
        echo 'The password confirmation does not match.';
    } else {
        $username = username_check($username, 0);
        $query = "INSERT INTO users (name, username, city, email, password, description, country) VALUES (?,?,?,?,?,?,?)";
        add_remove_update7($query, $name, $username, $city, $email, $password_crypt, $description, $country, "sssssss");
        $new_query = "SELECT * FROM users WHERE email =? and password =?";
        $res = db_get_two($new_query, $email, $password_crypt, "ss");
        if ($res == null) {
            echo "Something went wrong, call +37253950586";
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
            header('Location: profile.php');
        }

    }
}

function username_check($username, $try)
{
    if ($try != 0) {
        $new_username = $username . "." . $try;
    } else {
        $new_username = $username;

    }
    $new_query = "SELECT * FROM users WHERE username =?";
    $res = db_get_one($new_query, $new_username, "s");
    if ($res == null) {
        return $new_username;
    } else {
        $try++;
        return username_check($username, $try);
    }
}