<?php
require "DBConnection.php";
require "chkSession.php";

$stmt = $conn->prepare("SELECT * FROM FILM WHERE CodFilm=?");
$stmt->bind_param("i", $_GET['idFilm']);
$stmt->execute();

$result = $stmt->get_result();

if ($result->num_rows == 0)
    die("Film non trovato");
else if ($result->num_rows > 1)
    die("Più di un film trovato. Questo non è possibile! Hai rotto tutto!!!! :/");

$film = $result->fetch_assoc();
?>

<h1>
    <?php echo $film['Titolo']; ?>
</h1>

<div id="container" style="white-space:nowrap">

    <div id="texts" style="display:inline-block;">
        <p>
            <b>Anno di produzione:</b> <?php echo $film['AnnoProduzione']; ?>
            <br>
            <b>Nazionalità: </b><?php echo $film['Nazionalita']; ?>
            <br>
            <b>Regista: </b><?php echo $film['Regista']; ?>
            <br>
            <b>Genere: </b><?php echo $film['Genere']; ?>
            <br>
            <b>Durata: </b><?php echo $film['Durata']; ?>
        </p>
    </div>

    <div id="image" style="float:right">
        <img src="<?php echo $film['Immagine']; ?>" style="max-width: 680; max-height: 400;" />
    </div>

    <?php
    if ($_SESSION['admin']) {
    ?>
        <br><br>
        <a href="elimina.php?idFilm=<?php echo $_GET['idFilm']; ?>">Elimina film</a>
    <?php
    }
    ?>

</div>

<style>
    p {
        font-size: 20px;
    }

    table {
        border-collapse: collapse;
    }

    table,
    td,
    th {
        border: 1px solid black;
    }
</style>