<?php
// Start the session
session_start();
?>

<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <link rel="stylesheet" href="style.css">
</head>

<body>
    <form class="incenter">
            <div class="modal-header loginHeader incenter">
            <h1>Daten bearbeiten</h1>
            </div>

            <div>
                <label>Name</label>
                <input type="text"><br>
                <label>Geburtsdatum</label>
                <input type="text"><br>
                <label>Geburtsort</label>
                <input type="text"><br>

            </div>

            <div class="incenter">
            <button class="btn btn-primary loginDlgButton" formaction="index.php">speichern</button>
            <button class="btn btn-default loginDlgButton" formaction="index.php">abbrechen</button>
        </div>
    </form>


</body>