<!DOCTYPE html>

<html>

<head>
    <link rel="stylesheet" type="text/css" href="style.css" />
    <title>Cinema</title>
</head>

<body>
    <div>
        <h1>Registrazione</h1>
        <form method="post" action="chkRegistrazione.php" onsubmit="return validateForm()">
            <?php
            if (isset($_GET['error']))
                echo "<font color='red'>{$_GET['error']}</font><br>";

            ?>
            <label for="email">Username</label>
            <input type="email" name="email" id="email" required />
            <br>
            <br>

            <label for="pw">Password</label>
            <input type="password" name="pw" id="pw" required />
            <br>
            <br>

            <label for="pw2">Conferma la passwod</label>
            <input type="password" name="pw2" id="pw2" required />
            <br>
            <br>

            <input type="submit" value="Registrati" />
        </form>
    </div>
</body>


<script>
    function validateForm() {
        var pw1 = document.getElementById("pw").value;
        var pw2 = document.getElementById("pw2").value;
        if (pw1 !== pw2) {
            alert("Le password devono essere uguali");
            return false;
        }
        return true;
    }
</script>

</html>