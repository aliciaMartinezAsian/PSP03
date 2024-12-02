package view;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.Ctr;
import model.Cuenta;

public class PanelVer extends JPanel {
    private ArrayList<Cuenta> cuentas;
    private int indiceActual = 0;
    
    private JButton btnPrimero, btnAnterior, btnSiguiente, btnUltimo;
    
    private JTextField nombreField, sueldoField, sueldoMaxField, fechaField;
    private JLabel numEmple;
    
    public PanelVer() {
        empleados = new ArrayList<>();
        setLayout(null);
        
        JLabel nombre = new JLabel("Nombre:");
        nombreField = new JTextField(20);
        
        add(nombreField);
        add(nombre);
        
        nombre.setBounds(28,30,50,20);
        nombreField.setBounds(85,30,100,20);
       
        JLabel fecha = new JLabel("Fecha de nacimiento:");
        fechaField = new JTextField(20);
        
        add(fecha);
        add(fechaField);
        
        fecha.setBounds(200,28,150,20);
        fechaField.setBounds(330,28,80,20);
        
        JLabel sueldo = new JLabel("Sueldo:");
        sueldoField = new JTextField(20);
        
        add(sueldoField);
        add(sueldo);
        
        sueldo.setBounds(28,60,80,20);
        sueldoField.setBounds(80,60,100,20);
        
        JLabel sueldoMax = new JLabel("Sueldo máximo:");
        sueldoMaxField = new JTextField(20);
        
        add(sueldoMax);
        add(sueldoMaxField);
        
        sueldoMax.setBounds(200,60,100,20);
        sueldoMaxField.setBounds(300,60,100,20);

        btnPrimero = new JButton("Primero");
        
        btnAnterior = new JButton("Anterior");
        
        btnSiguiente = new JButton("Siguiente");
        
        btnUltimo = new JButton("Último");
       
        
        add(btnPrimero);
        add(btnAnterior);
        add(btnSiguiente);
        add(btnUltimo);
        
        btnPrimero.setBounds(30,125,90,27);
        btnSiguiente.setBounds(230,125,90,27);
        btnAnterior.setBounds(130,125,90,27);
        btnUltimo.setBounds(330,125,90,27);
        
        
        initListeners();
        
        obtenerEmpleados();
        
        numEmple = new JLabel("Empleado: "+(indiceActual+1)+" de "+empleados.size());
        add(numEmple);
        numEmple.setBounds(180,170,150,20);
        
        if (!empleados.isEmpty()) mostrarEmpleado(0);
        

    }
    
    private void initListeners() {
    	btnPrimero.addActionListener(e -> mostrarEmpleado(0));
    	btnAnterior.addActionListener(e -> mostrarEmpleado(indiceActual - 1));
    	btnSiguiente.addActionListener(e -> mostrarEmpleado(indiceActual + 1));
    	btnUltimo.addActionListener(e -> mostrarEmpleado(empleados.size() - 1));
    }
    
    public void obtenerEmpleados() {
    	CtrEmpleado ctr = new CtrEmpleado();
    	
    	empleados = ctr.listaEmpleados();
    }

    public void mostrarEmpleado(int indice) {
    	btnSiguiente.setEnabled(true);
    	btnAnterior.setEnabled(true);
    	
    	if(indice==0) btnAnterior.setEnabled(false);
    	if(indice==empleados.size()-1) btnSiguiente.setEnabled(false);
    	
        if (indice >= 0 && indice < empleados.size()) {
            Empleado empleado = empleados.get(indice);
            nombreField.setText(empleado.getNombre());
            sueldoField.setText(String.valueOf(empleado.getSueldo()));
            sueldoMaxField.setText(String.valueOf(empleado.getSueldoMax()));
            fechaField.setText(String.valueOf(empleado.getFechaNacimiento()));
            indiceActual = indice;
            numEmple.setText("Empleado: "+(indiceActual+1)+" de "+empleados.size());
        }
    }
}
