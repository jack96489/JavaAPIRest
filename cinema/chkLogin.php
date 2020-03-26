<?php
session_start();
include("DBConnection.php");


if ($_SERVER['REQUEST_METHOD'] == 'POST' && isset($_POST['email']) && isset($_POST['pw'])) {

    $email = trim($_POST['email']);
    var_dump(filter_var($email, FILTER_VALIDATE_EMAIL));
    if (!filter_var($email, FILTER_VALIDATE_EMAIL)) {
        header("location: login.php?error=E-mail non valida!");
        die;
    }
    $pw = md5($_POST['pw']);

    if (!empty($email) && !empty($pw)) {

        $query = $conn->prepare("SELECT * FROM utenti WHERE email=? AND password=?");
        $query->bind_param("ss", $email, $pw);

        if ($query->execute() === true) {
            $result = $query->get_result();

            if ($result->num_rows == 1) {
                $user = $result->fetch_array();
                $_SESSION['user'] = $email;
                $_SESSION['userID'] = $user['ID'];
                $_SESSION['admin'] = $user['Admin'];
                header("location: elencoFilm.php");
            } else
                header("location: login.php?error=Login errato!");
        } else
            header("location: login.php?error=Errore di connessione al database!");
    }
}