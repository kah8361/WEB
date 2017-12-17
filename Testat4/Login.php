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
                <?php echo '<h2 class="header incenter">' . "Login zum Bearbeiten der Daten von " . $_SESSION["name"] . "!</h2>"; ?>
            </div>
            <div class="modal-body">
                <form action="Login.php" method="post" class="form-horizontal" role="form">
                    <div class="form-group">
                        <label class="control" for="password">Passwort:</label>
                        
                        <input id="password" type="password" class="control" name="password">
                    
                        <button class="control" name="submitButton">login</button>
                        
                    </div>
                </form>
            </div>
            <div class="modal-body errormsg">
                <?php echo $error ?><!-- Fehlermeldung -->

            </div>
        </div>
    </div>
</div>


</body>
</html>