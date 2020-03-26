<?php
session_start();
?>
<!DOCTYPE html>
<html>
    <head>
        <title>Cinema</title>
    </head>
    <body>
        <h1>Cinema</h1>
        <?php 
        if(isset($_SESSION['user'])){
            echo "<h4>Benvenuto {$_SESSION['user']}<h4><br><br>";

        ?>
        <a href="elencoFilm.php"><button>Visualizza film</button></a>

        <?php
        }else{
        ?>

        <a href="login.php"><button>ACCEDI</button></a>
        <a href="registrazione.php"><button>REGISTRATI</button></a>

        <?php
        }
        ?>

    </body>
</html>