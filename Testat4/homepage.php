    <?php
        if ($_SERVER["REQUEST_METHOD"] == "POST") {
         if (empty($_POST["name"])) {
           $nameErr = "Name is required";
         } else {
           $heading = "Willkommen auf der Homepage von " . $name ;
         }
        }

    \>