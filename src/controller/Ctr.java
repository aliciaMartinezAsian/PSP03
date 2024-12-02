package controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.List;

import model.ComisionInvalidaException;
import model.Cuenta;
import model.CuentaAhorro;
import model.CuentaAhorro.TipoCuentaAhorro;
import model.CuentaCorriente;
import model.CuentaCorriente.TipoComisionMensual;
import model.InteresInvalidoException;
import model.SaldoInvalidoException;
import model.SaldoMinInvalidoException;
import model.TitularInvalidoException;

public class Ctr {

	
	private Lista listaCuentas;
	
	//Control=1 CuentaCorriente
	//Control=2 CuentaAhorro
	
	public Ctr() {
		listaCuentas=new Lista();
		//Cargar fichero
		 try (BufferedReader br = new BufferedReader(new FileReader("Cuentas.dat"))) {
			 while(br.ready()) {
				 int control= br.read();
				 
				 if(control==1) cargarCuentaCorriente(br);
				 if(control==2) cargarCuentaAhorro(br);
			 
			 }
			
			
		 }catch(Exception e){
			 System.out.println(e.getMessage());
		 }
		
	}
	
	 public void cargarCuentaCorriente(BufferedReader br) throws TitularInvalidoException, SaldoInvalidoException, ComisionInvalidaException, SaldoMinInvalidoException, IOException {
	       
	            String linea;
	            while ((linea = br.readLine()) != null) { //Lee los datos separados en coma con un \n al final
	                String[] datos = linea.split(",");
	                if (datos.length == 7) {
	                        // Convertir los datos a los tipos adecuados
	                        int numero = Integer.parseInt(datos[0].trim());
	                        String titular = datos[1].trim();
	                        double saldo = Double.parseDouble(datos[2].trim());
	                        double saldoMinimo = Double.parseDouble(datos[3].trim());
	                        LocalDate fechaApertura = LocalDate.parse(datos[4].trim());
	                        double comisionMantenimiento = Double.parseDouble(datos[5].trim());
	                        TipoComisionMensual tipo = TipoComisionMensual.valueOf(datos[6].trim());

	                        // Crear la cuenta y añadirla a la lista
	                        CuentaCorriente cuenta = new CuentaCorriente(numero, titular, saldo, saldoMinimo, fechaApertura, comisionMantenimiento, tipo);
	                        listaCuentas.agregar(cuenta);


	                }
	            }


	    }
	
	 public void cargarCuentaAhorro(BufferedReader br) throws TitularInvalidoException, SaldoInvalidoException, InteresInvalidoException, SaldoMinInvalidoException, IOException {
		    String linea;
		    while ((linea = br.readLine()) != null) {
		        String[] datos = linea.split(",");
		        if (datos.length == 7) {

		                // Convertir los datos a los tipos adecuados
		                int numero = Integer.parseInt(datos[0].trim());
		                String titular = datos[1].trim();
		                double saldo = Double.parseDouble(datos[2].trim());
		                double saldoMinimo = Double.parseDouble(datos[3].trim());
		                LocalDate fechaApertura = LocalDate.parse(datos[4].trim());
		                double interes = Double.parseDouble(datos[5].trim());  // 'interes' es un valor entero
		                TipoCuentaAhorro tipo = TipoCuentaAhorro.valueOf(datos[6].trim());  // Cambiar a TipoCuentaAhorro

		                // Crear la cuenta de ahorro y añadirla a la lista
		                CuentaAhorro cuenta = new CuentaAhorro(numero, titular, saldo, saldoMinimo, fechaApertura, interes, tipo);
		                listaCuentas.agregar(cuenta);  // Supongo que listaCuentas es una colección de tipo List<CuentaAhorro>

		        }
		    }
		}

	 
	public void anadir(Cuenta c) {
		this.listaCuentas.agregar(c);
		guardar();
	}
	public void borrarTodo() {
		this.listaCuentas=new Lista<Cuenta>();
	}
	public void borrar(Cuenta c) {
		this.listaCuentas.eliminar(c);
	}
	public void modificar() {
		
	}
	public void guardar() {
		
	}
	public List<Cuenta> getLista() {
		return this.listaCuentas.getLista();
	}
	public void actualizarSaldo(Cuenta c) {
		LocalDate ahora=LocalDate.now();
		//Si es cuenta ahorra aumenta su interes por año en %
		if(c instanceof CuentaAhorro) {
			long aniosDiferencia=ChronoUnit.YEARS.between(c.getFechaApertura(), ahora);
			
		CuentaAhorro caux=(CuentaAhorro) c;
			double nuevoSaldo=c.getSaldo()+aniosDiferencia*caux.getInteres();
		}
		
		
		//Si es cuenta corriente disminuye la comision por mes.
		if(c instanceof CuentaCorriente) {
			long mesesDiferencia=ChronoUnit.MONTHS.between(c.getFechaApertura(), ahora);
			
		CuentaAhorro caux=(CuentaAhorro) c;
			double nuevoSaldo=c.getSaldo()+mesesDiferencia*caux.getInteres();
		}
		//Guardar nuevo dato
		
	}
}
