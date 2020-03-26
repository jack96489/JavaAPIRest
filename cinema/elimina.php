<?php
require "chkSession.php";
require "DBConnection.php";

if (!isset($_GET['idFilm']))
    die("Devi selezionare un film!");

$id = $_GET['idFilm'];

$stmt = $conn->prepare("DELETE FROM film WHERE CodFilm=?");
$stmt->bind_param("s", $_GET['idFilm']);

if ($stmt->execute()) {
    header("location: elencoFilm.php");
} else
    die("Film non eliminato");
