/*
 * Created by JFormDesigner on Thu Mar 26 09:44:45 CET 2020
 */

package javaapirest;

import java.awt.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import javax.swing.*;
import javax.xml.parsers.ParserConfigurationException;

import com.intellij.uiDesigner.core.*;
import org.xml.sax.SAXException;

/**
 * @author Giacomo Orsenigo
 */
public class FilmForm  {
    private final WSConsumer server;

    FilmForm(WSConsumer server, int codFilm) {
        initComponents();
        this.server = server;
        JFrame frame = new JFrame("Film");
        frame.setContentPane(panel);

        if (codFilm > 0) {
            INSERISCIButton.setVisible(false);
            try {
                server.addParameter("idFilm", String.valueOf(codFilm));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

            System.out.println(server.get("localhost"));

            final String film = server.getResult();
            try {
                Film f = Film.fromXml(film);
                loadFilm(f);
            } catch (ParserConfigurationException | IOException | SAXException e) {
                e.printStackTrace();
            }
        } else {
            MODIFICAButton.setVisible(false);
            ELIMINAButton.setVisible(false);
            textFieldCodice.setVisible(false);
            codicaLabel.setVisible(false);
        }

        INSERISCIButton.addActionListener(e -> {
            try {
                final Film nuovo = new Film(
                        codFilm,
                        textFieldTitolo.getText(),
                        Integer.parseInt(textFieldAnno.getText()),
                        textFieldNazionalita.getText(),
                        textFieldRegista.getText(),
                        textFieldGenere.getText(),
                        Integer.parseInt(textFieldDurata.getText())
                );

                server.addParameter("film", nuovo.toXML());
                server.post("localhost");
                System.out.println(server.getResult());
                if (server.getResult().equals("OK")) {
                    JOptionPane.showMessageDialog(frame, "Film inserito correttamente", "Inserimento completato", JOptionPane.INFORMATION_MESSAGE);
                } else if (server.getResult().equals("KO")) {
                    JOptionPane.showMessageDialog(frame, "Film non inserito", "Inserimento non completato", JOptionPane.ERROR_MESSAGE);
                } else
                    throw new RuntimeException("Errore sconosciuto durante l'inserimento del film");

                frame.dispose();

            } catch (UnsupportedEncodingException ex) {
                ex.printStackTrace();
            }
        });
        MODIFICAButton.addActionListener(e -> JOptionPane.showMessageDialog(frame, "Funzionalità non ancora implementata", "Attenzione", JOptionPane.WARNING_MESSAGE));
        ELIMINAButton.addActionListener(e -> JOptionPane.showMessageDialog(frame, "Funzionalità non ancora implementata", "Attenzione", JOptionPane.WARNING_MESSAGE));


        frame.pack();
        frame.setVisible(true);
    }


    private void loadFilm(Film film) {
        textFieldCodice.setText(String.valueOf(film.getCodFilm()));
        textFieldTitolo.setText(film.getTitolo());
        textFieldAnno.setText(String.valueOf(film.getAnnoProduzione()));
        textFieldRegista.setText(film.getRegista());
        textFieldNazionalita.setText(film.getNazionalita());
        textFieldGenere.setText(film.getGenere());
        textFieldDurata.setText(String.valueOf(film.getDurata()));
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        panel = new JPanel();
        codicaLabel = new JLabel();
        JLabel label1 = new JLabel();
        JLabel label2 = new JLabel();
        JLabel label3 = new JLabel();
        JLabel label4 = new JLabel();
        JLabel label5 = new JLabel();
        JLabel label6 = new JLabel();
        textFieldCodice = new JTextField();
        textFieldTitolo = new JTextField();
        textFieldAnno = new JTextField();
        textFieldRegista = new JTextField();
        textFieldNazionalita = new JTextField();
        textFieldGenere = new JTextField();
        textFieldDurata = new JTextField();
        INSERISCIButton = new JButton();
        ELIMINAButton = new JButton();
        MODIFICAButton = new JButton();

        //======== panel ========
        {
            panel.setLayout(new GridLayoutManager(8, 4, new Insets(0, 0, 0, 0), -1, -1));

            //---- codicaLabel ----
            codicaLabel.setText("CODICE");
            panel.add(codicaLabel, new GridConstraints(0, 0, 1, 1,
                GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
                GridConstraints.SIZEPOLICY_FIXED,
                GridConstraints.SIZEPOLICY_FIXED,
                null, null, null));

            //---- label1 ----
            label1.setText("Titolo");
            panel.add(label1, new GridConstraints(1, 0, 1, 1,
                GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
                GridConstraints.SIZEPOLICY_FIXED,
                GridConstraints.SIZEPOLICY_FIXED,
                null, null, null));

            //---- label2 ----
            label2.setText("Anno produzione");
            panel.add(label2, new GridConstraints(2, 0, 1, 1,
                GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
                GridConstraints.SIZEPOLICY_FIXED,
                GridConstraints.SIZEPOLICY_FIXED,
                null, null, null));

            //---- label3 ----
            label3.setText("Regista");
            panel.add(label3, new GridConstraints(3, 0, 1, 1,
                GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
                GridConstraints.SIZEPOLICY_FIXED,
                GridConstraints.SIZEPOLICY_FIXED,
                null, null, null));

            //---- label4 ----
            label4.setText("Nazionalit\u00e0");
            panel.add(label4, new GridConstraints(4, 0, 1, 1,
                GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
                GridConstraints.SIZEPOLICY_FIXED,
                GridConstraints.SIZEPOLICY_FIXED,
                null, null, null));

            //---- label5 ----
            label5.setText("Genere");
            panel.add(label5, new GridConstraints(5, 0, 1, 1,
                GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
                GridConstraints.SIZEPOLICY_FIXED,
                GridConstraints.SIZEPOLICY_FIXED,
                null, null, null));

            //---- label6 ----
            label6.setText("Durata");
            panel.add(label6, new GridConstraints(6, 0, 1, 1,
                GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
                GridConstraints.SIZEPOLICY_FIXED,
                GridConstraints.SIZEPOLICY_FIXED,
                null, null, null));

            //---- textFieldCodice ----
            textFieldCodice.setEditable(false);
            panel.add(textFieldCodice, new GridConstraints(0, 1, 1, 3,
                GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL,
                GridConstraints.SIZEPOLICY_CAN_GROW | GridConstraints.SIZEPOLICY_WANT_GROW,
                GridConstraints.SIZEPOLICY_FIXED,
                null, null, null));
            panel.add(textFieldTitolo, new GridConstraints(1, 1, 1, 3,
                GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL,
                GridConstraints.SIZEPOLICY_CAN_GROW | GridConstraints.SIZEPOLICY_WANT_GROW,
                GridConstraints.SIZEPOLICY_FIXED,
                null, null, null));
            panel.add(textFieldAnno, new GridConstraints(2, 1, 1, 3,
                GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL,
                GridConstraints.SIZEPOLICY_CAN_GROW | GridConstraints.SIZEPOLICY_WANT_GROW,
                GridConstraints.SIZEPOLICY_FIXED,
                null, null, null));
            panel.add(textFieldRegista, new GridConstraints(3, 1, 1, 3,
                GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL,
                GridConstraints.SIZEPOLICY_CAN_GROW | GridConstraints.SIZEPOLICY_WANT_GROW,
                GridConstraints.SIZEPOLICY_FIXED,
                null, null, null));
            panel.add(textFieldNazionalita, new GridConstraints(4, 1, 1, 3,
                GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL,
                GridConstraints.SIZEPOLICY_CAN_GROW | GridConstraints.SIZEPOLICY_WANT_GROW,
                GridConstraints.SIZEPOLICY_FIXED,
                null, null, null));
            panel.add(textFieldGenere, new GridConstraints(5, 1, 1, 3,
                GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL,
                GridConstraints.SIZEPOLICY_CAN_GROW | GridConstraints.SIZEPOLICY_WANT_GROW,
                GridConstraints.SIZEPOLICY_FIXED,
                null, null, null));
            panel.add(textFieldDurata, new GridConstraints(6, 1, 1, 3,
                GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL,
                GridConstraints.SIZEPOLICY_CAN_GROW | GridConstraints.SIZEPOLICY_WANT_GROW,
                GridConstraints.SIZEPOLICY_FIXED,
                null, null, null));

            //---- INSERISCIButton ----
            INSERISCIButton.setText("INSERISCI");
            panel.add(INSERISCIButton, new GridConstraints(7, 1, 1, 1,
                GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
                GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                GridConstraints.SIZEPOLICY_FIXED,
                null, null, null));

            //---- ELIMINAButton ----
            ELIMINAButton.setText("ELIMINA");
            panel.add(ELIMINAButton, new GridConstraints(7, 3, 1, 1,
                GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
                GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                GridConstraints.SIZEPOLICY_FIXED,
                null, null, null));

            //---- MODIFICAButton ----
            MODIFICAButton.setText("MODIFICA");
            panel.add(MODIFICAButton, new GridConstraints(7, 2, 1, 1,
                GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
                GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                GridConstraints.SIZEPOLICY_FIXED,
                null, null, null));
        }
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - unknown
    private JPanel panel;
    private JLabel codicaLabel;
    private JTextField textFieldCodice;
    private JTextField textFieldTitolo;
    private JTextField textFieldAnno;
    private JTextField textFieldRegista;
    private JTextField textFieldNazionalita;
    private JTextField textFieldGenere;
    private JTextField textFieldDurata;
    private JButton INSERISCIButton;
    private JButton ELIMINAButton;
    private JButton MODIFICAButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
