<?php
require "DBConnection.php";

if ($_SERVER['REQUEST_METHOD'] === 'GET') {
    if (isset($_GET['idFilm'])) {
        $stmt = $conn->prepare("SELECT * FROM FILM WHERE CodFilm=?");
        $stmt->bind_param("i", $_GET['idFilm']);
        $stmt->execute();

        $result = $stmt->get_result();

        if ($result->num_rows == 0)
            die("Film non trovato");
        else if ($result->num_rows > 1)
            die("Più di un film trovato. Questo non è possibile! Hai rotto tutto!!!! :/");

        $film = $result->fetch_assoc();

        echo "<film>" .
            "<CodFilm>{$film['CodFilm']}</CodFilm>" .
            "<Titolo>{$film['Titolo']}</Titolo>" .
            "<AnnoProduzione>{$film['AnnoProduzione']}</AnnoProduzione>" .
            "<Nazionalita>{$film['Nazionalita']}</Nazionalita>" .
            "<Regista>{$film['Regista']}</Regista>" .
            "<Genere>{$film['Genere']}</Genere>" .
            "<Durata>{$film['Durata']}</Durata>" .
            "</film>";
    } else {
        $result = $conn->query("SELECT * FROM film");
        if ($result->num_rows > 0) {
            // output data of each row
            echo "<films>";
            while ($row = $result->fetch_assoc()) {
                echo "<film>" .
                    "<CodFilm>{$row['CodFilm']}</CodFilm>" .
                    "<Titolo>{$row['Titolo']}</Titolo>" .
                    "<AnnoProduzione>{$row['AnnoProduzione']}</AnnoProduzione>" .
                    "<Nazionalita>{$row['Nazionalita']}</Nazionalita>" .
                    "<Regista>{$row['Regista']}</Regista>" .
                    "<Genere>{$row['Genere']}</Genere>" .
                    "<Durata>{$row['Durata']}</Durata>" .
                    "</film>";
            }
            echo "</films>";
        }
    }
} else if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    if (isset($_POST['film'])) {
        $film = simplexml_load_string($_POST['film']);
        //var_dump($film);
        if (
            property_exists($film, 'CodFilm') &&
            property_exists($film, 'Titolo') &&
            property_exists($film, 'AnnoProduzione') &&
            property_exists($film, 'Nazionalita') &&
            property_exists($film, 'Regista') &&
            property_exists($film, 'Genere') &&
            property_exists($film, 'Durata')
        ) {
            $stmt = $conn->prepare("INSERT INTO film (Titolo, AnnoProduzione, Nazionalita, Regista, Genere, Durata) VALUES (?,?,?,?,?,?)");
            $stmt->bind_param("sisssi", $film->Titolo, $film->AnnoProduzione, $film->Nazionalita, $film->Regista, $film->Genere, $film->Durata,);


            if ($stmt->execute())
                echo "OK";
            else
                echo "KO";
        } else
            die("Parametri non corretti!");
    }
}
