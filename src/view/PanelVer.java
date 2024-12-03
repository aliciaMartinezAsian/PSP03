package view;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Cuenta;
import model.CuentaAhorro;
import model.CuentaCorriente;

public class PanelVer extends JPanel {
    private List<Cuenta> cuentas;
    private int indiceActual = 0;

    private JButton btnPrimero, btnAnterior, btnSiguiente, btnUltimo;

    private JTextField numeroField, titularField, saldoField, saldoMinField, fechaAperturaField;
    private JTextField interesField, tipoAhorroField, comisionField;
    private JComboBox<String> tipoComisionComboBox;
    private JLabel numCuenta;

    public PanelVer() {
        cuentas = new ArrayList<>();
        setLayout(null);

        // Campos comunes a ambas cuentas
        JLabel numeroLabel = new JLabel("Número:");
        numeroField = new JTextField(20);
        numeroField.setEditable(false);
        add(numeroLabel);
        add(numeroField);
        numeroLabel.setBounds(20, 10, 100, 20);
        numeroField.setBounds(120, 10, 150, 20);

        JLabel titularLabel = new JLabel("Titular:");
        titularField = new JTextField(20);
        titularField.setEditable(false);
        add(titularLabel);
        add(titularField);
        titularLabel.setBounds(20, 40, 100, 20);
        titularField.setBounds(120, 40, 250, 20);

        JLabel saldoLabel = new JLabel("Saldo:");
        saldoField = new JTextField(20);
        saldoField.setEditable(false);
        add(saldoLabel);
        add(saldoField);
        saldoLabel.setBounds(20, 70, 100, 20);
        saldoField.setBounds(120, 70, 150, 20);

        JLabel saldoMinLabel = new JLabel("Saldo mínimo:");
        saldoMinField = new JTextField(20);
        saldoMinField.setEditable(false);
        add(saldoMinLabel);
        add(saldoMinField);
        saldoMinLabel.setBounds(20, 100, 100, 20);
        saldoMinField.setBounds(120, 100, 150, 20);

        JLabel fechaAperturaLabel = new JLabel("Fecha apertura:");
        fechaAperturaField = new JTextField(20);
        fechaAperturaField.setEditable(false);
        add(fechaAperturaLabel);
        add(fechaAperturaField);
        fechaAperturaLabel.setBounds(20, 130, 100, 20);
        fechaAperturaField.setBounds(120, 130, 150, 20);

        // Campos específicos para CuentaAhorro
        JLabel interesLabel = new JLabel("Interés:");
        interesField = new JTextField(20);
        interesField.setEditable(false);
        add(interesLabel);
        add(interesField);
        interesLabel.setBounds(20, 160, 100, 20);
        interesField.setBounds(120, 160, 150, 20);

        JLabel tipoAhorroLabel = new JLabel("Tipo ahorro:");
        tipoAhorroField = new JTextField(20);
        tipoAhorroField.setEditable(false);
        add(tipoAhorroLabel);
        add(tipoAhorroField);
        tipoAhorroLabel.setBounds(20, 190, 100, 20);
        tipoAhorroField.setBounds(120, 190, 150, 20);

        // Campos específicos para CuentaCorriente
        JLabel comisionLabel = new JLabel("Comisión:");
        comisionField = new JTextField(20);
        comisionField.setEditable(false);
        add(comisionLabel);
        add(comisionField);
        comisionLabel.setBounds(300, 70, 100, 20);
        comisionField.setBounds(400, 70, 80, 20);

        JLabel tipoComisionLabel = new JLabel("Tipo comisión:");
        tipoComisionComboBox = new JComboBox<>();
        tipoComisionComboBox.setEnabled(false);
        add(tipoComisionLabel);
        add(tipoComisionComboBox);
        tipoComisionLabel.setBounds(300, 100, 100, 20);
        tipoComisionComboBox.setBounds(400, 100, 80, 20);

        // Botones de navegación
        btnPrimero = new JButton("Primero");
        btnAnterior = new JButton("Anterior");
        btnSiguiente = new JButton("Siguiente");
        btnUltimo = new JButton("Último");

        add(btnPrimero);
        add(btnAnterior);
        add(btnSiguiente);
        add(btnUltimo);

        btnPrimero.setBounds(50, 230, 90, 25);
        btnAnterior.setBounds(150, 230, 90, 25);
        btnSiguiente.setBounds(250, 230, 90, 25);
        btnUltimo.setBounds(350, 230, 90, 25);

        // Etiqueta para el índice actual
        numCuenta = new JLabel("Cuenta: 0 de 0");
        add(numCuenta);
        numCuenta.setBounds(200, 260, 150, 20);

        initListeners();
        obtenerCuentas();

        if (!cuentas.isEmpty()) mostrarCuenta(0);
    }

    private void initListeners() {
        btnPrimero.addActionListener(e -> mostrarCuenta(0));
        btnAnterior.addActionListener(e -> mostrarCuenta(indiceActual - 1));
        btnSiguiente.addActionListener(e -> mostrarCuenta(indiceActual + 1));
        btnUltimo.addActionListener(e -> mostrarCuenta(cuentas.size() - 1));
    }

    public void obtenerCuentas() {
        // Simula obtener cuentas (ejemplo).
        cuentas = new ArrayList<>(); // Aquí cargarías las cuentas reales.
    }

    public void mostrarCuenta(int indice) {
        if (indice < 0 || indice >= cuentas.size()) return;

        indiceActual = indice;
        Cuenta cuenta = cuentas.get(indice);

        // Mostrar campos comunes
        numeroField.setText(String.valueOf(cuenta.getNumero()));
        titularField.setText(cuenta.getTitular());
        saldoField.setText(String.valueOf(cuenta.getSaldo()));
        saldoMinField.setText(String.valueOf(cuenta.getSaldoMinimo()));
        fechaAperturaField.setText(cuenta.getFechaApertura().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));

        // Determinar el tipo de cuenta y ajustar visibilidad
        if (cuenta instanceof CuentaAhorro) {
            CuentaAhorro ahorro = (CuentaAhorro) cuenta;
            interesField.setText(String.valueOf(ahorro.getInteres()));
            tipoAhorroField.setText(ahorro.getTipo().name());
            mostrarCamposAhorro(true);
            mostrarCamposCorriente(false);
        } else if (cuenta instanceof CuentaCorriente) {
            CuentaCorriente corriente = (CuentaCorriente) cuenta;
            comisionField.setText(String.valueOf(corriente.getComisionMantenimiento()));
            tipoComisionComboBox.setSelectedItem(corriente.getTipo().name());
            mostrarCamposAhorro(false);
            mostrarCamposCorriente(true);
        }

        numCuenta.setText("Cuenta: " + (indiceActual + 1) + " de " + cuentas.size());
    }

    private void mostrarCamposAhorro(boolean mostrar) {
        interesField.setVisible(mostrar);
        tipoAhorroField.setVisible(mostrar);
    }

    private void mostrarCamposCorriente(boolean mostrar) {
        comisionField.setVisible(mostrar);
        tipoComisionComboBox.setVisible(mostrar);
    }
}



