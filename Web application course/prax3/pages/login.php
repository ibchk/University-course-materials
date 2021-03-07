<?php
include_once '../components/header.php';

session_start();

if (isset($_SESSION['loggedin']) && $_SESSION['loggedin'] === true) {
    session_start();
    header('Location: feed.php');
}

if (isset($_POST['log_in_button'])) {
    include '../authenticate.php';
    log_in();
}

if (isset($_POST['reg_button'])) {
    include '../registration.php';
    register();
}

// Now we check if the data from the login form was submitted, isset() will check if the data exists.
?>
<head>
    <title>Fakebook</title>
    <meta charset="UTF-8">
</head>
<body>

<?php
if (isset($_POST['new_user'])) {
    ?>
    <h1>Registration</h1>

    <form method="post" class="reg">
        <input type="hidden" name="action" value="registration">
        <div class="field">
            <label>First name:</label>
            <input type="text" name="first_name">
        </div>

        <div class="field">
            <label>Last name:</label>
            <input type="text" name="last_name">
        </div>

        <div class="field">
            <label>Country:</label>
            <input type="text" name="country">
        </div>

        <div class="field">
            <label>City/town:</label>
            <input type="text" name="city">
        </div>

        <div class="field">
            <label>Describe yourself:</label>
            <input type="text" name="description">
        </div>

        <div class="field">
            <label>Email:</label>
            <input type="text" name="email_reg">
        </div>

        <div class="field">
            <label>Password:</label>
            <input type="password" name="password_reg">
        </div>

        <div class="field">
            <label>Confirm password:</label>
            <input type="password" name="con_password_reg">
        </div>

        <div class="field">
            <input type="submit" class="button_reg" value="Register" name="reg_button">
        </div>

    </form>
    <?php
} else {
    ?>
    <h1>Login</h1>

    <form method="post" class="auth">
        <input type="hidden" name="action" value="login">
        <div class="field">
            <label>Email:</label>
            <input type="text" name="email_log">
        </div>

        <div class="field">
            <label>Password:</label>
            <input type="password" name="password_log">
        </div>

        <div class="field">
            <input type="submit" class="button_log_in" value="Log In" name="log_in_button">
        </div>

    </form>
    <form method="post" class="reg">
        <p>Do not have an account?</p>
        <input type="submit" class="button_reg" value="Register" name="new_user">
    </form>
    <?php
}
?>


</body>






