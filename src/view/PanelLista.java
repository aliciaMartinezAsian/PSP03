package view;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import model.Cuenta;
import model.CuentaAhorro;

public class PanelLista extends JPanel {

    private static final long serialVersionUID = 1L;
    private DefaultListModel<String> listModel;
    private JList<String> list;

    public PanelLista() {
        setLayout(new BorderLayout());

        // Crear el modelo de lista
        listModel = new DefaultListModel<>();

        // Crear el JList y asignarle el modelo
        list = new JList<>(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Scroll Pane para la lista
        JScrollPane scrollPane = new JScrollPane(list);
        add(scrollPane, BorderLayout.CENTER);
    }

    public void cargarCuentas(List<Cuenta> cuentas) {
        // Limpiar la lista antes de agregar nuevos elementos
        listModel.clear();

        // Agregar cuentas al modelo con información del tipo
        for (Cuenta cuenta : cuentas) {
            String tipo = (cuenta instanceof CuentaAhorro) ? "Cuenta Ahorro" : "Cuenta Corriente";
            String texto = String.format("%s - Nº: %d - Titular: %s", tipo, cuenta.getNumero(), cuenta.getTitular());
            listModel.addElement(texto);
        }
    }
}

