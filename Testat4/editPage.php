<?php
// Start the session
session_start();

if(!isset($_SESSION["isloggedin"]) || $_SESSION["isloggedin"] == false){
    header("Location:Login.php");
    exit();
}
if(isset($_POST["name"])){
    if(isset($_POST["save"])){
        $_SESSION["name"] = $_POST["name"];
        $_SESSION["ort"] = $_POST["place"];
        $_SESSION["date"] = $_POST["date"];
    }
    header("Location:index.php");
    exit();
}
?>

<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <link rel="stylesheet" href="style.css">
</head>

<body>
    <form action="editPage.php" method="post" class="incenter">
            <div class="modal-header loginHeader incenter">
            <h1 class="header">Daten bearbeiten</h1>
            </div>

            <div>
                <label >Name</label>
                <input class="label" type="text" name="name"><br>
                <label >Geburtsdatum</label>
                <input class="label" type="text" name="date"><br>
                <label >Geburtsort</label>
                <input class="label" type="text" name="place"><br>

            </div>

            <div class="incenter">
            <button name="save" class="btn">speichern</button>
            <button name="cancel" class="btn">abbrechen</button>
        </div>
    </form>


</body>