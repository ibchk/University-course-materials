<?php
session_start();
include '../components/header.php';
include '../post.php';
include '../friend.php';

if (isset($_GET['id']) || isset($_GET['un'])) {
    if (isset($_GET['id']) && $_GET['id'] != $_SESSION["id"]) {
        $id = htmlspecialchars($_GET['id']);
        $query = "SELECT name, username, city, country, description, created_at FROM users WHERE id =?";
        $res = db_get_one($query, $id, "s");
        $username = $res["username"];
    } else if (isset($_GET['un']) && $_GET['un'] != $_SESSION["username"]) {
        $username = htmlspecialchars($_GET['un']);
        $query = "SELECT id, name, city, country, description, created_at FROM users WHERE username =?";
        $res = db_get_one($query, $username, "s");
        $id = $res["id"];
    } else {
        header('Location: profile.php');
        exit();
    }
    $name = $res["name"];
    $city = $res["city"];
    $country = $res["country"];
    $description = $res["description"];
    $date = date("F j, Y", strtotime($res["created_at"]));
} else {
    $id = $_SESSION["id"];
    $name = $_SESSION["name"];
    $username = $_SESSION["username"];
    $city = $_SESSION["city"];
    $country = $_SESSION["country"];
    $description = $_SESSION["description"];
    $email = $_SESSION["email"];
    $date = date("F j, Y", strtotime($_SESSION["created_at"]));
}

if (isset($_POST['post_button']) && !empty($_REQUEST['post_text'])) {
    new_post();
}


if (isset($_POST['remove_friend_button'])) {
    remove_friend($id);
}

if (isset($_POST['add_friend_button'])) {
    add_friend($id);
}

if (isset($_POST['save_button'])) {
    include '../profile_change.php';
    change();
}

?>
    <!DOCTYPE html>
    <head>
        <meta charset="UTF-8">
        <title>Fakebook</title>
        <link href="../css/style.css" rel="stylesheet">
    </head>
    <body>
    <div>

        <div class="panel">
            <div class="fb-timeline-img">
                <img src="https://bootdey.com/img/Content/bg1.jpg" alt="">
            </div>
            <div class="fb-name">
                <h2><?php echo $name ?> | <?php echo $username ?> </h2>
                <h2><?php echo $city . ', ' . $country ?> </h2>
                <h3><?php echo $description ?></h3>
            </div>
            <div>
                <form method="post">
                    <?php if (isset($_GET['id']) || isset($_GET['un'])) {
                        if (check_subscribed($id)) {
                            ?>
                            <div>
                                <input type="submit" value="Remove friend" name="remove_friend_button">
                            </div>
                            <?php
                        } else {
                            ?>
                            <div>
                                <input type="submit" value="Add to friends" name="add_friend_button">
                            </div>
                            <?php
                        }
                    } elseif (!isset($_POST['change_settings'])) {
                        ?>
                        <div>
                            <input type="submit" value="Change settings" name="change_settings">
                        </div>
                        <?php
                    } ?>
                </form>
            </div>
            <div class="panel-body">
                <div class="profile-thumb">
                    <img src="../asserts/avatar.png" alt="avatar">
                </div>
                <a>Here since <?php echo $date ?></a>
            </div>
        </div>

        <?php
        if (isset($_POST['change_settings'])) {
            ?>
            <form method="post" class="auth">
                <input type="hidden" name="action" value="login">
                <div class="field">
                    <label>New name:</label>
                    <input type="text" name="name_change">
                </div>

                <div class="field">
                    <label>New city/town:</label>
                    <input type="text" name="city_change">
                </div>

                <div class="field">
                    <label>New country:</label>
                    <input type="text" name="country_change">
                </div>

                <div class="field">
                    <label>New description:</label>
                    <input type="text" name="description_change">
                </div>

                <div class="field">
                    <input type="submit" class="save_button" value="Save" name="save_button">
                </div>

            </form>
            <?php
        } else {
            if (!isset($_GET['id']) && !isset($_GET['un'])) {
                ?>
                <form method="post" class="post_creation">
                        <div class="post_text_field">
                <textarea class="form-control input-lg p-text-area" rows="2"
                          placeholder="Whats in your mind today?" name="post_text"></textarea>
                        </div>
                        <div>
                            <input type="submit" value="Post" name="post_button">
                        </div>
                </form>
                <?php
            }
            get_all_posts($id, $name);
        }
        ?>
    </div>
    </body>
    </html>
<?php

