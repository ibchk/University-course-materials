<?php
$image_url = "../asserts/logo.jpg";

if(isset($_POST['search_button']) && !empty($_REQUEST['search_text'])) {
    include '../search.php';
    $search=$_REQUEST['search_text'];
    header('Location: ../pages/search.php?ask=' . $search);
}

?>
<nav>
    <ul>
        <img src="<?php echo $image_url;?>" alt="Logo of the Fakebook"/>
        <?php
        if (isset($_SESSION['loggedin']) && $_SESSION['loggedin'] === true) {
        ?>
        <li><a href="../pages/feed.php">Feed</a></li>
        <li><a href="../pages/profile.php">Profile</a></li>
        <li><a href="../pages/friends.php">Friends</a></li>
        <?php } ?>

    </ul>

    <ul>
        <?php

        if (isset($_SESSION['loggedin']) && $_SESSION['loggedin'] === true) {
            ?>
            <form method="post">
                <div>
                    <input size="30" placeholder="Search.." name="search_text">
                    <input type="submit" class="search_button" value="Find" name="search_button">
                </div>
            </form>
        <?php } ?>
    </ul>

    <ul>
        <?php
        if (isset($_SESSION['loggedin']) && $_SESSION['loggedin'] === true) { ?>
            <li><a href="../logout.php">Log out</a></li>
        <?php } ?>
    </ul>
</nav>
