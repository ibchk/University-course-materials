<?php

include 'db.php';


if (isset($_POST['comment_button'])) {
    $id = $_SESSION["id"];
    $text = htmlspecialchars($_POST['comment_text']);
    $post = $_POST['comment_post_id'];
    $query = "INSERT INTO comments (user_id, post_id, content) VALUES (?,?,?)";
    add_remove_update3($query, $id, $post, $text, "sss");
}

if (isset($_POST['unlike_button'])) {
    $post_id = $_POST['liked_post_id'];
    $my_id = $_SESSION["id"];
    $query = "DELETE FROM reactions WHERE post_id =? and user_id =?";
    add_remove_update2($query, $post_id, $my_id, "ss");
}

if (isset($_POST['like_button'])) {
    $post_id = $_POST['liked_post_id'];
    $my_id = $_SESSION["id"];
    $query = "INSERT INTO reactions (post_id, user_id) VALUES (?, ?)";
    add_remove_update2($query, $post_id, $my_id, "ss");
}

if (isset($_POST['repost'])){
    $post_text = $_POST['repost_post_text'];
    $my_id = $_SESSION["id"];
    $creator_name = $_POST['repost_post_user_name'];
    $query = "INSERT INTO posts (creator_name, user_id, content) VALUES (?, ?, ?)";
    add_remove_update3($query, $creator_name, $my_id, $post_text, "sss");
}

function new_post()
{
    $id = $_SESSION["id"];
    $text = htmlspecialchars($_REQUEST['post_text']);
    $query = "INSERT INTO posts (creator_name, user_id, content) VALUES (?, ?, ?)";
    add_remove_update3($query, '', $id, $text, "sss");
}

function get_all_posts($id, $name)
{
    $query = "SELECT * FROM posts ORDER BY created_at DESC";
    $posts = json_decode(json_encode(db_get_list($query)), true);
    for ($i = 0; $i < count($posts); ++$i) {
        if ($posts[$i]['user_id'] == $id) {
            $content = $posts[$i]['content'];
            $post_id = $posts[$i]['id'];
            $date = date("F j, Y", strtotime($posts[$i]['created_at']));
            ?>
            <div class="post" style="box-shadow: rgb(42,151,236) 0px 0px 0px 3px;">
                <img src="../asserts/avatar.png" alt="avatar">
                <h2><?php echo $name ?></h2>
                <br>
                <h3><?php echo $date ?></h3>
                <br>
                <?php
                if ($posts[$i]['creator_name'] != ''){
                    echo "Creator: " . $posts[$i]['creator_name'];
                }
                ?>
                <br>
                <?php echo $content ?>

                <?php
                echo 'Likes: ' . count_likes($post_id);
                if (liked($post_id)) {
                    ?>
                    <div method="post" class="like">
                        <form class="field" method="post">
                            <input type="hidden" name="liked_post_id" value="<?php echo $post_id ?>">
                            <button name="unlike_button">Unlike</button>
                        </form>
                    </div>
                    <?php
                } else {
                    ?>
                    <div method="post" class="like">
                        <form class="field" method="post">
                            <input type="hidden" name="liked_post_id" value="<?php echo $post_id ?>">
                            <button name="like_button">Like</button>
                        </form>
                    </div>
                    <form class="repost" method="post">
                        <input type="hidden" name="repost_post_text" value="<?php echo $content ?>">
                        <input type="hidden" name="repost_post_user_name" value="<?php echo $name ?>">
                        <button name="repost">Repost</button>
                    </form>
                    <?php
                }
                get_all_comments($post_id); ?>
                <div method="post" class="comment_creation">
                    <form class="field" method="post">
                        <input type="text" placeholder="Comment this?" name="comment_text">
                        <input type="hidden" name="comment_post_id" value="<?php echo $post_id ?>">
                        <button type="submit" name="comment_button" value="Post">Button</button>
                    </form>
                </div>
            </div>
            <?php
        }
    }
}

function get_all_comments($post_id)
{
    $query = "SELECT user_id, content FROM comments WHERE post_id = '$post_id' ORDER BY created_at";
    $comments = json_decode(json_encode(db_get_list($query)), true);
    if ($comments != null) {
        for ($i = 0; $i < count($comments); ++$i) {
            $content = $comments[$i]['content'];
            $user_id = $comments[$i]['user_id'];
            $query = "SELECT name FROM users WHERE id = '$user_id'";
            $res = db_get($query);
            ?>
            <div class="comment" style="box-shadow: rgb(66,203,131) 0px 0px 0px 3px;">
                <?php echo $res['name'] ?>
                <br>
                <?php echo $content ?>
            </div>
            <?php
        }
    }
}

function count_likes($post_id)
{
    $query = "SELECT * FROM reactions WHERE post_id = '$post_id'";
    $res = db_get_list($query);
    if ($res != null) {
        return count($res);
    }
    return 0;
}

function liked($post_id)
{
    $my_id = $_SESSION["id"];
    $new_query = "SELECT * FROM reactions WHERE user_id = '$my_id' and post_id = '$post_id'";
    $res = db_get($new_query);
    if ($res != null) {
        return true;
    }
    return false;
}

function friends_posts()
{
    include 'friend.php';
    $query = "SELECT id, creator_name, user_id, content, created_at FROM posts ORDER BY created_at DESC";
    $friends = friends_array();
    $posts = json_decode(json_encode(db_get_list($query)), true);
    for ($i = 0; $i < count($posts); ++$i) {
        $user_id = $posts[$i]['user_id'];
        if (in_array($user_id, $friends) && check_friend($user_id)) {
            $query = "SELECT name FROM users WHERE id = '$user_id'";
            $res = db_get($query);
            $name = $res['name'];
            $content = $posts[$i]['content'];
            $post_id = $posts[$i]['id'];
            $date = date("F j, Y", strtotime($posts[$i]['created_at']));
            ?>
            <div class="post" style="box-shadow: rgb(42,151,236) 0px 0px 0px 3px;">
                <img src="../asserts/avatar.png" alt="avatar">
                <h2><?php echo $name ?></h2>
                <br>
                <h3><?php echo $date ?></h3>
                <br>
                <?php
                if ($posts[$i]['creator_name'] != ''){
                    echo "Creator: " . $posts[$i]['creator_name'];
                }
                ?>
                <br>
                <?php echo $content ?>
                <?php
                echo 'Likes: ' . count_likes($post_id);
                if (liked($post_id)) {
                    ?>
                    <div method="post" class="like">
                        <form class="field" method="post">
                            <input type="hidden" name="liked_post_id" value="<?php echo $post_id ?>">
                            <button name="unlike_button">Unlike</button>
                        </form>
                    </div>
                    <?php
                } else {
                    ?>
                    <div method="post" class="like">
                        <form class="field" method="post">
                            <input type="hidden" name="liked_post_id" value="<?php echo $post_id ?>">
                            <button name="like_button">Like</button>
                        </form>
                    </div>
                    <form class="repost" method="post">
                        <input type="hidden" name="repost_post_text" value="<?php echo $content ?>">
                        <input type="hidden" name="repost_post_user_name" value="<?php echo $name ?>">
                        <button name="repost">Repost</button>
                    </form>
                    <?php
                }
                get_all_comments($post_id);
                ?>
                <div method="post" class="comment_creation">
                    <form class="field" method="post">
                        <input type="text" placeholder="Comment this?" name="comment_text">
                        <input type="hidden" name="comment_post_id" value="<?php echo $post_id ?>">
                        <button type="submit" name="comment_button" value="Post">Button</button>
                    </form>
                </div>
            </div>
            </div>
            <?php
        }
    }
}
