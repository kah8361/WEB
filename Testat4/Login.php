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
    <form>
<!-- Login Dialog -->

<div id="loginDialog" class="modal fade" role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header loginHeader">
                <h2 class="modal-title incenter">Login zum Bearbeiten der Daten von ?name</h2>
            </div>
            <div class="modal-body incenter">
                <form class="form-horizontal" role="form">
                    <div class="form-group">
                        <label class="control-label col-sm-4" for="password">Passwort:
                            </label>
                        <div class="col-sm-8">
                            <input type="password" class="form-control" name="password">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-8">
                            <button class="btn btn-primary loginDlgButton" formaction="editPage.php">login</button>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer loginFooter">
                <!-- Fehlermeldung -->

            </div>
        </div>
    </div>
</div>
</form>


</body>