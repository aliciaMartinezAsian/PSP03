package view;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.Ctr;
import model.Cuenta;
import model.CuentaAhorro;
import model.CuentaCorriente;

public class PanelVer extends JPanel {
    private static List<Cuenta> cuentas;
    private int indiceActual = 0;
    
    private Ctr ctr = Ctr.getControlador();

    private JButton btnCalcular, btnAnterior, btnSiguiente, btnBorrar;

    private JTextField numeroField, titularField, saldoField, saldoMinField, fechaAperturaField, tipoComisionField, tipoAhorroField, interesField,comisionField;
    private JLabel numCuenta, comisionLabel, interesLabel,tipoAhorroLabel, tipoComisionLabel ;

    public PanelVer() {
    	
        setLayout(null);
        cuentas=new ArrayList<Cuenta>();
        
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
        titularField.setBounds(120, 40, 150, 20);

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
        interesLabel = new JLabel("Interés:");
        interesField = new JTextField(20);
        interesField.setEditable(false);
        add(interesLabel);
        add(interesField);
        interesLabel.setBounds(280, 70, 100, 20);
        interesField.setBounds(376, 70, 80, 20);
        interesLabel.setVisible(false);
        interesField.setVisible(false);

        tipoAhorroLabel = new JLabel("Tipo ahorro:");
        tipoAhorroField = new JTextField(20);
        tipoAhorroField.setEditable(false);
        add(tipoAhorroLabel);
        add(tipoAhorroField);
        tipoAhorroLabel.setBounds(280, 100, 100, 20);
        tipoAhorroField.setBounds(376, 100, 114, 20);
        tipoAhorroLabel.setVisible(false);
        tipoAhorroField.setVisible(false);

        // Campos específicos para CuentaCorriente
        comisionLabel = new JLabel("Comisión:");
        comisionField = new JTextField(20);
        comisionField.setEditable(false);
        add(comisionLabel);
        add(comisionField);
        comisionLabel.setBounds(280, 70, 100, 20);
        comisionField.setBounds(376, 70, 80, 20);
        comisionLabel.setVisible(false);
        comisionField.setVisible(false);

        tipoComisionLabel = new JLabel("Tipo comisión:");
        tipoComisionField = new JTextField(20);
        tipoComisionField.setEditable(false);
        add(tipoComisionLabel);
        add(tipoComisionField);
        tipoComisionLabel.setBounds(280, 100, 100, 20);
        tipoComisionField.setBounds(376, 100, 114, 20);
        tipoComisionLabel.setVisible(false);
        tipoComisionField.setVisible(false);
        

        // Botones de navegación
        btnCalcular = new JButton("Calcular");
        btnAnterior = new JButton("Anterior");
        btnSiguiente = new JButton("Siguiente");
        btnBorrar = new JButton("Borrar");

        add(btnCalcular);
        add(btnBorrar);
        add(btnSiguiente);
        add(btnAnterior);

        btnCalcular.setBounds(50, 230, 90, 25);
        btnAnterior.setBounds(150, 230, 90, 25);
        btnSiguiente.setBounds(250, 230, 90, 25);
        btnBorrar.setBounds(350, 230, 90, 25);

        // Etiqueta para el índice actual
        numCuenta = new JLabel("Cuenta: 0 de 0");
        add(numCuenta);
        numCuenta.setBounds(200, 260, 150, 20);

        initListeners();

        if (cuentas.isEmpty()) mostrarCuenta(indiceActual);
    }

    private void initListeners() {
        btnCalcular.addActionListener(e -> calcular());
        btnBorrar.addActionListener(e -> borrar());
        btnSiguiente.addActionListener(e -> mostrarCuenta(indiceActual + 1));
        btnAnterior.addActionListener(e -> mostrarCuenta(indiceActual - 1));
    }

    private void borrar() {
    	ctr.borrar(cuentas.get(indiceActual));
    	cuentas = ctr.getLista();
    	if(indiceActual!=0)indiceActual--;	
		mostrarCuenta(indiceActual);
		
	}

	private void calcular() {
		ctr.actualizarSaldo(cuentas.get(indiceActual));
		mostrarCuenta(indiceActual);
		
	}


    public void mostrarCuenta(int indice) {
        if (indice < 0 || indice >= cuentas.size()) return;

        indiceActual = indice;
        Cuenta cuenta = cuentas.get(indice);

        // Mostrar campos comunes
        
        numeroField.setText(String.valueOf(cuenta.getNumero()));
        titularField.setText(cuenta.getTitular());
        saldoField.setText(String.format("%.2f", cuenta.getSaldo()));
        saldoMinField.setText(String.format("%.2f",cuenta.getSaldoMinimo()));
        fechaAperturaField.setText(cuenta.getFechaApertura().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));

        // Determinar el tipo de cuenta y ajustar visibilidad
        if (cuenta instanceof CuentaAhorro) {
            CuentaAhorro ahorro = (CuentaAhorro) cuenta;
            interesField.setText(String.format("%.2f",ahorro.getInteres()));
            tipoAhorroField.setText(ahorro.getTipo().name());
            mostrarCamposAhorro(true);
            mostrarCamposCorriente(false);
        } else if (cuenta instanceof CuentaCorriente) {
            CuentaCorriente corriente = (CuentaCorriente) cuenta;
            comisionField.setText(String.format("%.2f",corriente.getComisionMantenimiento()));
            tipoComisionField.setText(corriente.getTipo().name());
            mostrarCamposAhorro(false);
            mostrarCamposCorriente(true);
        }

        numCuenta.setText("Cuenta: " + (indiceActual + 1) + " de " + cuentas.size());
    }

    private void mostrarCamposAhorro(boolean mostrar) {
    	interesLabel.setVisible(mostrar);
        interesField.setVisible(mostrar);
        tipoAhorroLabel.setVisible(mostrar);
        tipoAhorroField.setVisible(mostrar);
    }

    private void mostrarCamposCorriente(boolean mostrar) {
    	comisionLabel.setVisible(mostrar);
        comisionField.setVisible(mostrar);
        tipoComisionLabel.setVisible(mostrar);
        tipoComisionField.setVisible(mostrar);
    }
    
    public void cargarCuentas(List<Cuenta> cuentasCargadas) {
    	cuentas = cuentasCargadas;
    	mostrarCuenta(indiceActual);
    }
    
    public void vaciarCampos() {
    	numeroField.setText("");
    	titularField.setText("");
    	saldoField.setText("");
    	saldoMinField.setText("");
    	fechaAperturaField.setText("");
    	tipoComisionField.setText("");
    	tipoAhorroField.setText("");
    	interesField.setText("");
    	comisionField.setText("");
    	numCuenta.setText("Cuenta 0 de 0");
    }
}



