package view;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.Ctr;
import model.CuentaAhorro;
import model.FechaInvalidaException;
import model.InteresInvalidoException;
import model.SaldoInvalidoException;
import model.SaldoMinInvalidoException;
import model.TipoCuentaAhorro;
import model.TitularInvalidoException;


import javax.swing.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import model.*;

import javax.swing.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import model.*;

package view;

import javax.swing.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import controller.Ctr;
import model.*;

public class PanelAltaCorriente extends JPanel {
    private JButton btnGuardar, btnCancelar;
    private JTextField titularField, saldoField, saldoMinField, fechaAperturaField, comisionField;
    private JComboBox<String> tipoComboBox;
    private Ctr ctr = Ctr.getControlador();

    public PanelAltaCorriente() {
        setLayout(null);

        // Campo Titular
        JLabel titular = new JLabel("Titular:");
        titularField = new JTextField(20);
        add(titularField);
        add(titular);
        titular.setBounds(20, 20, 100, 25);
        titularField.setBounds(130, 20, 320, 25);

        // Campo Saldo
        JLabel saldo = new JLabel("Saldo:");
        saldoField = new JTextField(20);
        add(saldo);
        add(saldoField);
        saldo.setBounds(20, 60, 100, 25);
        saldoField.setBounds(130, 60, 150, 25);

        // Campo Saldo Mínimo
        JLabel saldoMin = new JLabel("Saldo mínimo:");
        saldoMinField = new JTextField(20);
        add(saldoMinField);
        add(saldoMin);
        saldoMin.setBounds(290, 60, 100, 25);
        saldoMinField.setBounds(390, 60, 60, 25);

        // Campo Fecha de Apertura
        JLabel fechaApertura = new JLabel("Fecha apertura:");
        fechaAperturaField = new JTextField(20);
        add(fechaApertura);
        add(fechaAperturaField);
        fechaApertura.setBounds(20, 100, 100, 25);
        fechaAperturaField.setBounds(130, 100, 150, 25);

        // Campo Comisión Mantenimiento
        JLabel comision = new JLabel("Comisión:");
        comisionField = new JTextField(20);
        add(comision);
        add(comisionField);
        comision.setBounds(290, 100, 100, 25);
        comisionField.setBounds(390, 100, 60, 25);

        // ComboBox Tipo de Comisión
        JLabel tipoCuenta = new JLabel("Tipo de comisión:");
        tipoComboBox = new JComboBox<>(new String[]{"MANTENIMIENTO", "ADMINISTRACION", "TRANSFERENCIAS", "TARJETAS"});
        add(tipoCuenta);
        add(tipoComboBox);
        tipoCuenta.setBounds(20, 140, 120, 25);
        tipoComboBox.setBounds(150, 140, 300, 25);

        // Botón Guardar
        btnGuardar = new JButton("Guardar");
        add(btnGuardar);
        btnGuardar.setBounds(150, 200, 90, 30);

        // Botón Cancelar
        btnCancelar = new JButton("Cancelar");
        add(btnCancelar);
        btnCancelar.setBounds(260, 200, 90, 30);

        initListeners();
    }

    private void initListeners() {
        btnGuardar.addActionListener(e -> agregarCuenta());
        btnCancelar.addActionListener(e -> limpiarCampos());
    }

    private void agregarCuenta() {
        String titular = titularField.getText().trim();
        String strSaldo = saldoField.getText().trim();
        String strSaldoMin = saldoMinField.getText().trim();
        String strFecha = fechaAperturaField.getText().trim();
        String strComision = comisionField.getText().trim();
        String strTipo = (String) tipoComboBox.getSelectedItem();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        try {
            // Validar campos obligatorios
            if (titular.isEmpty() || strSaldo.isEmpty() || strSaldoMin.isEmpty() ||
                strFecha.isEmpty() || strComision.isEmpty()) {
                throw new IllegalArgumentException("Todos los campos son obligatorios.");
            }

            // Validar y parsear fecha
            LocalDate fecha;
            try {
                fecha = LocalDate.parse(strFecha, formatter);
            } catch (DateTimeParseException e) {
                throw new FechaInvalidaException("La fecha no cumple el formato dd-MM-yyyy.");
            }

            if (fecha.isAfter(LocalDate.now())) {
                throw new FechaInvalidaException("La fecha de apertura no puede ser futura.");
            }

            // Parsear valores numéricos con validación
            double saldo = validarYConvertirADouble(strSaldo, "Saldo");
            double saldoMin = validarYConvertirADouble(strSaldoMin, "Saldo mínimo");
            double comision = validarYConvertirADouble(strComision, "Comisión");

            // Validar tipo de comisión
            TipoComisionMensual tipo;
            try {
                tipo = TipoComisionMensual.valueOf(strTipo);
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Tipo de comisión no válido.");
            }

            // Crear la nueva cuenta corriente
            CuentaCorriente nuevaCuenta = new CuentaCorriente(titular, saldo, saldoMin, fecha, comision, tipo);
            ctr.anadir(nuevaCuenta);

            JOptionPane.showMessageDialog(this, "Cuenta corriente guardada con éxito.", "Información", JOptionPane.INFORMATION_MESSAGE);

        } catch (SaldoInvalidoException e) {
            JOptionPane.showMessageDialog(this, "Saldo inválido, \ndatos no guardados", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (FechaInvalidaException e1) {
            JOptionPane.showMessageDialog(this, "Fecha inválida, \ndatos no guardados", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (TitularInvalidoException e2) {
            JOptionPane.showMessageDialog(this, "Titular inválido, \ndatos no guardados", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (InteresInvalidoException e3) {
            JOptionPane.showMessageDialog(this, "Interés inválido, \ndatos no guardados", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (SaldoMinInvalidoException e4) {
            JOptionPane.showMessageDialog(this, "Saldo mínimo inválido, \ndatos no guardados", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            limpiarCampos();
        }
    }

    // Método auxiliar para validar y convertir
    private double validarYConvertirADouble(String valor, String campo) {
        try {
            return Double.parseDouble(valor);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(campo + " debe ser un número válido.");
        }
    }

    private void limpiarCampos() {
        titularField.setText("");
        saldoField.setText("");
        saldoMinField.setText("");
        fechaAperturaField.setText("");
        comisionField.setText("");
        tipoComboBox.setSelectedIndex(0);
    }
}


