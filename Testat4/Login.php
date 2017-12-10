<?php
// Start the session
session_start();
if(!isset($_SESSION["name"])){
    $_SESSION["name"] = "Bart";
}

$pw = "779";
$error= "";
if(isset($_POST["password"])){
    if($_POST["password"]!= $pw){
        $error= "Falsches Passwort!";
    }else{
        $_SESSION["isloggedin"] = true;
        header("Location:editPage.php");
        exit();
    }
}
?>

<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <link rel="stylesheet" href="style.css">
</head>

<body>
<!-- Login Dialog -->

<div id="loginDialog" class="modal fade" role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header loginHeader">
                <?php echo '<h2 class="modal-title incenter">' . "Login zum Bearbeiten der Daten von " . $_SESSION["name"] . "!</h2>"; ?>
            </div>
            <div class="modal-body">
                <form action="Login.php" method="post" class="form-horizontal" role="form">
                    <div class="form-group">
                        <label class="control-label col-sm-4" for="password">Passwort:</label>
                        <div class="col-sm-8">
                            <input id="password" type="password" class="form-control" name="password">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-8">
                            <button class="btn btn-primary loginDlgButton" name="submitButton">login</button>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer loginFooter">
                <?php echo $error ?><!-- Fehlermeldung -->

            </div>
        </div>
    </div>
</div>


</body>
</html>