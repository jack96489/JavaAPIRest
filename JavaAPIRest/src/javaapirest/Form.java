/*
 * Created by JFormDesigner on Thu Mar 26 09:44:50 CET 2020
 */

package javaapirest;

import java.awt.*;
import java.io.IOException;
import javax.swing.*;
import javax.xml.parsers.ParserConfigurationException;

import com.intellij.uiDesigner.core.*;
import org.xml.sax.SAXException;

/**
 * @author Giacomo Orsenigo
 */
public class Form  {

    private WSConsumer webService;

    public Form() {
        initComponents();
        OKButton.addActionListener(e -> caricaLista());
        DETTAGLIButton.setEnabled(!lista.isSelectionEmpty());
        lista.addListSelectionListener(e -> DETTAGLIButton.setEnabled(!lista.isSelectionEmpty()));
        textField1.setText("http://localhost/cinema/service.php");
        DETTAGLIButton.addActionListener(e -> new FilmForm(webService, lista.getSelectedValue().getCodFilm()));
        NUOVOButton.addActionListener(e -> new FilmForm(webService, -1));
        caricaLista();
    }

    private void caricaLista() {
        webService = new WSConsumer(textField1.getText());
        System.out.println(webService.get("localhost"));
        final String result = webService.getResult();

        try {
            Films films = Films.fromXml(result);
            DefaultListModel<Film> listModel = new DefaultListModel<>();
            for (Film f : films)
                listModel.addElement(f);
            lista.setModel(listModel);
        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("WebService");
        frame.setContentPane(new Form().panelMain);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        panelMain = new JPanel();
        scroll = new JScrollPane();
        lista = new JList<>();
        textField1 = new JTextField();
        OKButton = new JButton();
        DETTAGLIButton = new JButton();
        NUOVOButton = new JButton();

        //======== panelMain ========
        {
            panelMain.setLayout(new GridLayoutManager(3, 3, new Insets(0, 0, 0, 0), -1, -1));

            //======== scroll ========
            {

                //---- lista ----
                lista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                scroll.setViewportView(lista);
            }
            panelMain.add(scroll, new GridConstraints(1, 0, 1, 3,
                GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH,
                GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW | GridConstraints.SIZEPOLICY_WANT_GROW,
                GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW | GridConstraints.SIZEPOLICY_WANT_GROW,
                null, new Dimension(700, 500), null));
            panelMain.add(textField1, new GridConstraints(0, 0, 1, 1,
                GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL,
                GridConstraints.SIZEPOLICY_CAN_GROW | GridConstraints.SIZEPOLICY_WANT_GROW,
                GridConstraints.SIZEPOLICY_FIXED,
                null, null, null));

            //---- OKButton ----
            OKButton.setText("OK / AGGIORNA");
            panelMain.add(OKButton, new GridConstraints(0, 1, 1, 2,
                GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
                GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                GridConstraints.SIZEPOLICY_FIXED,
                null, null, null));

            //---- DETTAGLIButton ----
            DETTAGLIButton.setText("DETTAGLI");
            panelMain.add(DETTAGLIButton, new GridConstraints(2, 2, 1, 1,
                GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
                GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                GridConstraints.SIZEPOLICY_FIXED,
                null, null, null));

            //---- NUOVOButton ----
            NUOVOButton.setText("NUOVO");
            panelMain.add(NUOVOButton, new GridConstraints(2, 1, 1, 1,
                GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
                GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                GridConstraints.SIZEPOLICY_FIXED,
                null, null, null));
        }
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - unknown
    private JPanel panelMain;
    private JScrollPane scroll;
    private JList<Film> lista;
    private JTextField textField1;
    private JButton OKButton;
    private JButton DETTAGLIButton;
    private JButton NUOVOButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
