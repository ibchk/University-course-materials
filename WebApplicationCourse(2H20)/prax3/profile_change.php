<?php

function change()
{
    $id = $_SESSION["id"];
    $name = htmlspecialchars($_POST['name_change']);
    $country = htmlspecialchars($_POST['country_change']);
    $city = htmlspecialchars($_POST['city_change']);
    $description = htmlspecialchars($_POST['description_change']);
    if (!empty($name)) {
        $sql = "UPDATE users SET name=? WHERE id='$id'";
        add_remove_update1($sql, $name, "s");
    }
    if (!empty($country)) {
        $sql = "UPDATE users SET country=? WHERE id='$id'";
        add_remove_update1($sql, $country, "s");
    }
    if (!empty($city)) {
        $sql = "UPDATE users SET city=? WHERE id='$id'";
        add_remove_update1($sql, $city, "s");
    }
    if (!empty($description)) {
        $sql = "UPDATE users SET description=? WHERE id='$id'";
        add_remove_update1($sql, $description, "s");
    }
    update($id);
}

function update($id)
{
    $query = "SELECT * FROM users WHERE id =?";
    $res = db_get_one($query, $id, "i");
    $_SESSION["name"] = $res['name'];
    $_SESSION["city"] = $res['city'];
    $_SESSION["country"] = $res['country'];
    $_SESSION["description"] = $res['description'];
    $_SESSION["updated_at"] = $res['updated_at'];
    header('Location: profile.php');
}
