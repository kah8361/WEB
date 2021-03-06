<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css" integrity="sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb"
        crossorigin="anonymous">
    <link rel="stylesheet" href="homepage.component.css">
</head>

<body>

    <?php include ("homepage.php"); ?>
    
    <form>
        <div class="container">
            <h2 value="<?php echo $heading;?>">Willkommen auf der Homepage von {{ me.name }}!</h2>
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
                                    
                                    <label class="input-field" for="birthplace" value="<?php echo $name;?>">Name</label>
                                    
                                </div>
                                <div class="form-group">
                                    <label for="birthdate">Geburtsdatum:</label>
                                    
                                    <label class="input-field" for="birthplace">Datum</label>
                                    
                                </div>
                                <div class="form-group">
                                    <label for="birthplace">Ort:</label>
                                    
                                    <label class="input-field" for="birthplace">Ort</label>
                                    
                                </div>

                            </form>
                        </div>

                    </div>

                </div>

            </div>
            <!-- content -->

            
                <button class="btn btn-primary btn-xs" formaction="Login.html">
                    Angaben ändern
                </button>
            
        </div>
        <!-- container -->


</body>

</html>