<?php
require "DBConnection.php";
require "chkSession.php";
?>
<!DOCTYPE html>
<html>

<head>
    <title>Cinema</title>
    <style>
        table {
            border-collapse: collapse;
        }

        table,
        td,
        th {
            border: 1px solid black;
        }

        div {
            border-radius: 5px;
            background-color: #f2f2f2;
            width: 500px;
            padding: 20px;
            margin: 0 auto;
        }
    </style>
</head>

<body>
    <?php
    echo "<h1>Benvenuto {$_SESSION['user']}</h1>";
    ?>
    <a href="logout.php">LOGOUT</a>

    <div>

        <h3>Elenco film</h3>

        <?php
        $result = $conn->query("SELECT DISTINCT Genere FROM film");
        $generi = array("Tutti");
        if ($result->num_rows > 0) {
            // output data of each row
            while ($row = $result->fetch_assoc()) {
                $generi[] = $row['Genere'];
            } ?>

            <form id="form">
                <label for="filtro">Filtra per genere</label>

                <select type="text" id="filtro" name="genere" required>
                    <?php
                    foreach ($generi as $g) {
                        echo "<option value='$g'";
                        if (isset($_GET['genere']) && $g === $_GET['genere'])
                            echo " selected";
                        echo ">$g</option>";
                    }
                    ?>
                </select>
                <input type="submit" value="Applica" />
                <input type="button" value="Reset" onclick="resetFiltro()" />
            </form>
            <br>


        <?php
        }

        ?>



        <?php
        if (isset($_GET['genere'])) {
            if (in_array($_GET['genere'], $generi)) {
                if ($_GET['genere'] === "Tutti")
                    $result = $conn->query("SELECT * FROM film");
                else {
                    $stmt = $conn->prepare("SELECT * FROM film WHERE Genere = ?");
                    $stmt->bind_param("s", $_GET['genere']);
                    if (!$stmt->execute())
                        die("Errore durante l'esecuzione della query");
                    $result = $stmt->get_result();
                }
            } else
                die("Genere inesistente!");
        } else {
            $result = $conn->query("SELECT * FROM film");
        }


        if ($result->num_rows == 0)
            die("Nessun film disponibile!");
        ?>

        <table>
            <tr>
                <th>TITOLO</th>
                <th>Anno produzione</th>
                <th>Nazionalita</th>
                <th>Regista</th>
                <th>Genere</th>
                <th>Durata</th>
            </tr>
            <?php
            while ($row = $result->fetch_assoc()) {
            ?>

                <tr>
                    <td>
                        <a href="schedaFilm.php?idFilm=<?php echo $row['CodFilm']; ?>">
                            <?php echo $row['Titolo']; ?>
                        </a>
                    </td>
                    <td>
                        <?php echo $row['AnnoProduzione']; ?>
                    </td>
                    <td>
                        <?php echo $row['Nazionalita']; ?>
                    </td>
                    <td>
                        <?php echo $row['Regista']; ?>
                    </td>
                    <td>
                        <?php echo $row['Genere']; ?>
                    </td>
                    <td>
                        <?php echo $row['Durata']; ?>
                    </td>
                    <?php
                    if ($_SESSION['admin']) {
                    ?>
                        <td>
                            <button onclick="confermaEliminazione(<?php echo $row['CodFilm']; ?>)">X</button>
                        </td>
                    <?php
                    }
                    ?>
                </tr>

            <?php
            }
            ?>
        </table>


        <?php
        if ($_SESSION['admin']) {
        ?>
            <br>
            <a href="inserisci.php"><button>Inserisci nuovo film</button></a>
        <?php
        }
        ?>
    </div>

    <script>
        function confermaEliminazione(idFilm) {
            if (confirm("Confermi l'eliminazione del film?"))
                window.location.href = "elimina.php?idFilm=" + idFilm;
        }

        function resetFiltro() {
            document.getElementById("filtro").selectedIndex = 0;
            document.getElementById("form").submit();

        }
    </script>


</body>

</html>