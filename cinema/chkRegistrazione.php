<?php
session_start();
include("DBConnection.php");


if ($_SERVER['REQUEST_METHOD'] == 'POST' && isset($_POST['email']) && isset($_POST['pw']) && isset($_POST['pw2'])) {

    $email = trim($_POST['email']);
    if ($_POST['pw'] !== $_POST['pw2'])
        header("location: registrazione.php?error=Le password non corrispondono");
    $pw = md5($_POST['pw']);


    if (!empty($email) && !empty($pw)) {

        $query = $conn->prepare("INSERT INTO utenti (email,password) VALUES (?,?)");
        $query->bind_param("ss", $email, $pw);

        if ($query->execute() === TRUE) {
            $_SESSION['user'] = $email;
            header("location: index.php");
        } else
            header("location: registrazione.php?error=Registrazione non completata");
    } else
        header("location: registrazione.php?error=email e password non possono essere vuote");
}
