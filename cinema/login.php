<html>

<head>
    <link rel="stylesheet" type="text/css" href="style.css"/>
    <title>Cinema</title>
</head>

<body>

    <div>

    <h1>Accedi</h1>

        <form method="post" action="chkLogin.php">
            <?php
            if (isset($_GET['error']))
                echo "<font color='red'>{$_GET['error']}</font><br>";
            ?>
            <label for="email">Email: </label>
            <input type="email" name="email" id="email" required />
            <br><br>
            <label for="pw">Password: </label>
            <input type="password" name="pw" id="pw" required />
            <br>
            <input type="reset" value="Cancella" />
            <input type="submit" value="Login" />
            <br><br>
            <p>Per registrarsi cliccare <a href="registrazione.php">qui</a></p>

        </form>

    </div>

</body>

</html>