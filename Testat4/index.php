<?php
// Start the session
session_start();
?>

<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css" integrity="sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb"
        crossorigin="anonymous">
    <link rel="stylesheet" href="homepage.component.css">
</head>

<body>

    <?php
        $_SESSION["name"] = $name;
    ?>
    
    <form>
        <div class="container">
                     <?php echo '<h2>' . "Favorite color is " . $name . "</h2>"; ?>

            <div class="content">
                    
                        <button class="active tab">Das bin ich</button>
                        <button class="active tab">Meine Vergangenheit</button>
                        <button class="active tab">Was ich mag</button>
                
                <div class="tab-content">
                    <div class="tab-pane active">
                        <h3>Mein Steckbrief:</h3>
                        <div>
                            <img class= "img" alt="Fehler beim Laden des Bildes. Bitte gib einen gültigen Pfad ein!" src="bart.jpeg">
                        </div>
                        <div class="form-horizontal">
                            <form  role="form" >

                                <div class="form-group">
                                    <label for="name">Name:</label>
                                    <?php
                                    ob_start();
                                    ?>
                                    <label>Kathi</label>
                                    <?php
                                    $name = ob_get_clean();
                                    ?>
                                </div>
                                <div class="form-group">
                                    <label for="birthdate">Geburtsdatum:</label>
                                    
                                    <label for="birthdate" name="date">Datum</label>
                                    
                                </div>
                                <div class="form-group">
                                    <label for="birthplace">Ort:</label>
                                    
                                    <label for="place" name="place">Ort</label>
                                    
                                </div>

                            </form>
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