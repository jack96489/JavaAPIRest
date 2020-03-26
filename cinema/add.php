<?php
require "chkSession.php";
require "DBConnection.php";

//var_dump($_POST);
//var_dump($_FILES);

if (
    !isset($_POST['titolo']) || empty($_POST['titolo'])
    || !isset($_POST['anno']) || empty($_POST['anno'])
    || !isset($_POST['nazionalita']) || empty($_POST['nazionalita'])
    || !isset($_POST['regista']) || empty($_POST['regista'])
    || !isset($_POST['genere']) || empty($_POST['genere'])
    || !isset($_POST['durata']) || empty($_POST['durata'])
)
    die("Parametri mancanti");

if ($_FILES['immagine']['error'] === 0) {

    $imageName = "images/{$_POST['titolo']}_" . time() . ".png";
    move_uploaded_file($_FILES['immagine']['tmp_name'], $imageName);

    $stmt = $conn->prepare("INSERT INTO film (Titolo, AnnoProduzione, Nazionalita, Regista, Genere, Durata,Immagine) VALUES (?,?,?,?,?,?,?)");
    $stmt->bind_param("sisssis", $_POST['titolo'], $_POST['anno'], $_POST['nazionalita'], $_POST['regista'], $_POST['genere'], $_POST['durata'], $imageName);
} else {
    $stmt = $conn->prepare("INSERT INTO film (Titolo, AnnoProduzione, Nazionalita, Regista, Genere, Durata) VALUES (?,?,?,?,?,?)");
    $stmt->bind_param("sisssi", $_POST['titolo'], $_POST['anno'], $_POST['nazionalita'], $_POST['regista'], $_POST['genere'], $_POST['durata']);
}

if ($stmt->execute()) {
    header("location: elencoFilm.php");
} else
    die("FILM NON AGGIUNTO");
