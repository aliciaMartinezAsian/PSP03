package view;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.FechaInvalidaException;
import model.SaldoInvalidoException;
import model.SaldoMinInvalidoException;


public class PanelAltaAhorro extends JPanel {
    private JButton btnGuardar, btnCancelar;
    
    private JTextField titularField, saldoField, saldoMinField, fechaAperturaField, interesField, tipoField;

    public PanelAltaAhorro() {
        setLayout(null);
        
        JLabel titular = new JLabel("Titular:");
        titularField = new JTextField(20);
        
        add(titularField);
        add(titular);
        
        titular.setBounds(28,30,50,20);
        titularField.setBounds(85,30,100,20);
       
        JLabel saldo = new JLabel("Saldo:");
        saldoField = new JTextField(20);
        
        
        add(saldo);
        add(saldoField);
        
        saldo.setBounds(200,28,150,20);
        saldoField.setBounds(330,28,80,20);
        
        JLabel saldoMin = new JLabel("Saldo mínimo:");
        saldoMinField = new JTextField(20);
        
        add(saldoMinField);
        add(saldoMin);
        
        saldoMin.setBounds(28,60,80,20);
        saldoMinField.setBounds(80,60,100,20);
        
        JLabel fechaApertura = new JLabel("Fecha apertura:");
        fechaAperturaField = new JTextField(20);
        
        add(fechaApertura);
        add(fechaAperturaField);
        
        fechaApertura.setBounds(200,60,100,20);
        fechaAperturaField.setBounds(300,60,100,20);

        btnGuardar = new JButton("Guardar");
        
        btnCancelar = new JButton("Cancelar");
             
        add(btnGuardar);
        add(btnCancelar);
        
        btnGuardar.setBounds(120,125,85,27);
        btnCancelar.setBounds(220,125,85,27);
        
        
        initListeners();
        
  
    }

    private void initListeners() {
    	btnGuardar.addActionListener(e -> agregarEmpleado());
    	btnCancelar.addActionListener(e -> limpiarCampos());
	}

	private void agregarEmpleado() {
        String titular = titularField.getText();
        String strSaldo = saldoField.getText();
        String strSaldoMin = saldoMinField.getText();
        
        String strFecha = fechaAperturaField.getText();
        
        // Formato de la fecha
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        
        // Hacemos los parse dentro del trycatch para gestionar los errores
        try {
	        LocalDate fecha;
	        try {
	        	fecha = LocalDate.parse(strFecha, formatter);
	        	if(fecha.isAfter(LocalDate.now())) {
	        		throw new FechaInvalidaException("La fecha de apertura no puede ser futura");
	        	}
	        }catch(Exception e) {
	        	throw new FechaInvalidaException("La fecha de apertura no cumple con el formato correcto");
	        }
	        
	        Integer saldo;
	        try{
	        	saldo = Integer.parseInt(strSaldo);
	        }catch(Exception e) {
	        	throw new SaldoInvalidoException("El saldo debe un número válido");
	        }
	        Integer saldoMin;
	        try{
	        	saldoMin = Integer.parseInt(strSaldoMin);
	        }catch(Exception e) {
	        	throw new SaldoMinInvalidoException();
	        }
        

        Empleado nuevoEmpleado;
		
			nuevoEmpleado = new Empleado(nombre, sueldo, sueldoMax, fecha);
			CtrEmpleado.agregarEmpleado(nuevoEmpleado);
		} catch (SueldoMaximoInvalidoException e) {
			JOptionPane.showMessageDialog(this, "Sueldo Maximo invalido, \ndatos no guardados", "Error", JOptionPane.ERROR_MESSAGE);
			
		} catch (SueldoInvalidoException e1) {
			JOptionPane.showMessageDialog(this, "Sueldo invalido, \ndatos no guardados", "Error", JOptionPane.ERROR_MESSAGE);
			
		} catch (FechaNacimientoInvalidaException e2) {
			JOptionPane.showMessageDialog(this, "Fecha de nacimiento invalida, \ndatos no guardados", "Error", JOptionPane.ERROR_MESSAGE);
			
		}finally {
			limpiarCampos();
		}

    }

    private void limpiarCampos() {
        nombreField.setText("");
        sueldoField.setText("");
        sueldoMaxField.setText("");
        fechaField.setText("");
    }
}