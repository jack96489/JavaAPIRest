<?php
require "chkSession.php";
$generi = array(
    "Animazione",
    "Avventura",
    "Biografico",
    "Commedia",
    "Documentario",
    "Drammatico",
    "Pornografico",
    "Erotico",
    "Fantascienza",
    "Fantasy/Fantastico",
    "Guerra",
    "Horror",
    "Musical",
    "Storico",
    "Thriller",
    "Western"
);
?>

<!DOCTYPE html>
<html>

<head>
    <title>Cinema</title>
    <link rel="stylesheet" type="text/css" href="style.css" />
</head>

<body>
    <div>
        <h1>Inserimento film</h1>
        <form enctype="multipart/form-data" method="post" action="add.php">
            <label for="titolo">Titolo: </label>
            <input type="text" id="titolo" name="titolo" required />
            <br><br>

            <label for="anno">Anno di produzione: </label>
            <input type="number" id="anno" name="anno" required/>
            <br><br>

            <label for="nazionalita">Nazionalità: </label>
            <input type="text" id="nazionalita" name="nazionalita" required />
            <br><br>

            <label for="regista">Regista: </label>
            <input type="text" id="regista" name="regista" required />
            <br><br>

            <label for="genere">Genere: </label>
            <select type="text" id="genere" name="genere" required>
                <option disabled selected>Seleziona un genere...</option>
                <?php
                foreach ($generi as $g)
                    echo "<option value='$g'>$g</option>";
                ?>
            </select>
            <br><br>

            <label for="durata">Durata: </label>
            <input type="number" id="durata" name="durata" required />
            <br><br>

            <label for="immagine">Immagine: </label>
            <input type="file" id="immagine" name="immagine" accept="image/*" />
            <br><br>

            <input type="submit" value="Aggiungi" />

        </form>
    </div>
</body>

</html>