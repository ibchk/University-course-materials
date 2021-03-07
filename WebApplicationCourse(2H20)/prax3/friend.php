<?php
include_once 'db.php';

if (isset($_POST['add_friend'])) {
    $id = $_POST['user_id'];
    add_friend($id);
}

function check_friend($id)
{
    $my_id = $_SESSION["id"];
    $new_query = "SELECT * FROM friends WHERE (user1_id = '$my_id' and user2_id = '$id')";
    $res = db_get($new_query);
    if ($res != null) {
        $new_query2 = "SELECT * FROM friends WHERE (user1_id = '$id' and user2_id = '$my_id')";
        $res2 = db_get($new_query2);
        if ($res2 != null) {
            return true;
        }
    }
    return false;
}

function friend_requests(){
    $query = 'SELECT id, name, city, country FROM users ';
    $user = json_decode(json_encode(db_get_list($query)), true);
    $title = false;
    for ($i = 0; $i < count($user); ++$i) {
        if ($_SESSION["id"] != $user[$i]['id']) {
            $name = $user[$i]['name'];
            $city = $user[$i]['city'];
            $country = $user[$i]['country'];
            $id = $user[$i]['id'];
            if (check_back_subscription($id) && !check_subscribed($id)) {
                if (!$title){
                    ?>Friendship requests:<?php
                    $title = true;
                }
                ?>
                <div class="line" style="box-shadow: rgb(9,93,119) 0px 0px 0px 3px;">
                    <img src="../asserts/avatar.png" alt="avatar">
                    <a href="profile.php?id=<?php echo $id ?>"
                       class="name"><?php echo $name . '<br>' . $city . ', ' . $country ?></a>
                    <form method="post">
                        <div>
                            <input type="hidden" name="user_id" value="<?php echo $id ?>">
                            <input type="submit" value="Add to friends" name="add_friend">
                        </div>
                    </form>
                </div>
                <?php

            }
        }
    }
}

function check_back_subscription($id)
{
    $my_id = $_SESSION["id"];
    $new_query = "SELECT * FROM friends WHERE (user1_id = '$id' and user2_id = '$my_id')";
    $res = db_get($new_query);
    if ($res != null) {
        return true;
    }
    return false;
}

function check_subscribed($id)
{
    $my_id = $_SESSION["id"];
    $new_query = "SELECT * FROM friends WHERE (user1_id = '$my_id' and user2_id = '$id')";
    $res = db_get($new_query);
    if ($res != null) {
        return true;
    }
    return false;
}

function add_friend($id)
{
    $my_id = $_SESSION["id"];
    $query = "INSERT INTO friends (user1_id, user2_id) VALUES ('$my_id', '$id')";
    add_remove_update($query);
}

function remove_friend($id)
{
    $my_id = $_SESSION["id"];
    $query = "DELETE FROM `friends` WHERE (user1_id = '$my_id' and user2_id = '$id') or (user1_id = '$id' and user2_id = '$my_id')";
    add_remove_update($query);
}

function get_all_friends()
{
    $friends = friends_array();
    if (count($friends) == 0) {
        echo "You need to find friends.";
    }
    $title = false;
    $query = 'SELECT id, name, city, country FROM users ';
    $user = json_decode(json_encode(db_get_list($query)), true);
    for ($i = 0; $i < count($user); ++$i) {
        if (in_array($user[$i]['id'], $friends) && $_SESSION["id"] != $user[$i]['id']) {
            $name = $user[$i]['name'];
            $city = $user[$i]['city'];
            $country = $user[$i]['country'];
            $id = $user[$i]['id'];
            if (check_friend($id)) {
                if (!$title){
                    ?>Your friends:<?php
                    $title = true;
                }
                ?>
                <div class="line" style="box-shadow: rgba(3, 102, 214, 0.3) 0px 0px 0px 3px;">
                    <img src="../asserts/avatar.png" alt="avatar">
                    <a href="profile.php?id=<?php echo $id ?>"
                       class="name"><?php echo $name . '<br>' . $city . ', ' . $country ?></a>
                </div>
                <?php

            }
        }
    }
}

function friends_array()
{
    $id = $_SESSION["id"];
    $query = "SELECT user1_id, user2_id FROM friends WHERE user1_id = '$id' or user2_id = '$id'";
    $friendship = json_decode(json_encode(db_get_list($query)), true);
    $friends = [];
    if ($friendship != null) {
        for ($i = 0; $i < count($friendship); ++$i) {
            if ($friendship[$i]['user1_id'] != $id) {
                array_push($friends, $friendship[$i]['user1_id']);
            } else {
                array_push($friends, $friendship[$i]['user2_id']);
            }
        }
    }
    return $friends;
}

function find_all_users($search)
{
    $query = 'SELECT id, name, city, country FROM users';
    $user = json_decode(json_encode(db_get_list($query)), true);
    for ($i = 0; $i < count($user); ++$i) {
        $name = $user[$i]['name'];
        $city = $user[$i]['city'];
        $country = $user[$i]['country'];
        $id = $user[$i]['id'];
        if ((strpos(strtoupper($name), $search) !== false || strpos(strtoupper($city), $search) !== false || strpos(strtoupper($country), $search) !== false) && $_SESSION["id"] != $id) {
            ?>
            <div class="line" style="box-shadow: rgba(3, 102, 214, 0.3) 0px 0px 0px 3px;">
                <img src="../asserts/avatar.png" alt="avatar">
                <a href="profile.php?id=<?php echo $id ?>"
                   class="name"><?php echo $name . '<br>' . $city . ', ' . $country ?></a>
            </div>
            <?php
        }
    }
}