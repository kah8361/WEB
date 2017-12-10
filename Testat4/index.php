<?php
// Start the session
session_start();
if( !isset($_SESSION["name"]) || empty( $_SESSION["name"])){
    $_SESSION["name"] = "Bart";
    $_SESSION["ort"] = "Hollywood";
    $_SESSION["date"] = "?.?.1985";
}
?>

<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <!-- <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css" integrity="sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb"
        crossorigin="anonymous"> -->
    <link rel="stylesheet" href="homepage.component.css">
</head>

<body>

    <form>
        <div class="container">
            <?php echo '<h2>' . "Willkommen auf der Homepage von " . $_SESSION["name"] . "!</h2>"; ?>

            <div class="content">
            
                <button class="active tab">Das bin ich</button>
                <button class="active tab">Meine Vergangenheit</button>
                <button class="active tab">Was ich mag</button>
        
                <div class="tab-content">
                    <div class="tab-pane active">
                        <h3>Mein Steckbrief:</h3>
                        <div class= "img">
                            <img alt="Fehler beim Laden des Bildes. Bitte gib einen gültigen Pfad ein!" src="bart.jpeg">
                        </div>
                        <div class="form-horizontal">

                            <div class="form-group">
                                <div>
                                    <span>Name:</span>
                               
                                    <span><?php echo $_SESSION["name"] ?></span>
                                    
                                </div>
                                <div>
                                    <span>Geburtsdatum:</span>
                                    
                                    <span><?php echo $_SESSION["date"] ?></span>
                                </div>
                                <div>
                                    <span>Ort:</span>
                                    
                                    <span><?php echo $_SESSION["ort"] ?></span>
                                </div>
                            </div>

                        </div>

                    </div>

                </div>

            </div>
            <!-- content -->

            
                <button class="btn btn-primary btn-xs" formaction="Login.php">
                    Angaben ändern
                </button>
            
        </div>
        <!-- container -->


</body>

</html>