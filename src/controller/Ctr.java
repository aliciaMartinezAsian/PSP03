package controller;

import java.io.BufferedReader; 
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import model.ComisionInvalidaException;
import model.Cuenta;
import model.CuentaAhorro;
import model.CuentaCorriente;
import model.InteresInvalidoException;
import model.SaldoInvalidoException;
import model.SaldoMinInvalidoException;
import model.TipoComisionMensual;
import model.TipoCuentaAhorro;
import model.TitularInvalidoException;

public class Ctr {

	
	private Lista<Cuenta> listaCuentas;
	private static Ctr ctr=null;
	private String ruta="Cuentas.csv";
	//Control=1 CuentaCorriente
	//Control=2 CuentaAhorro
	public static Ctr  getControlador(){
		if(ctr==null) {
			ctr=new Ctr();
			return ctr;
		}else {
			return ctr;
		}
	}
	public Ctr() {
		//Elementos auxiliares
		listaCuentas=new Lista();
		
		List<Long> listaNumeros=new ArrayList<Long>();
		File fichero = new File(ruta);
		
		//Comprobamos si existe
		if(fichero.exists()) {
			//Si existe cargamos el fichero
			 try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
				 while(br.ready()) {
					 int control= br.read();
					 
					 if(control==1) {
						CuentaCorriente cuenta=cargarCuentaCorriente(br);
						  listaCuentas.agregar(cuenta);
						  listaNumeros.add(cuenta.getNumero());
					 }
					 if(control==2) {
						 CuentaAhorro cuenta=cargarCuentaAhorro(br);
						  listaCuentas.agregar(cuenta);
						  listaNumeros.add(cuenta.getNumero());
					 }
				 
				 }
				
				
			 }catch(Exception e){
				 System.out.println(e.getMessage());
			 }
			 //Para calcular los numeros de cuenta
			 if(listaNumeros.size()!=0) {
				 Cuenta.numCuenta=Collections.max(listaNumeros)+1;
			 }else {
				 Cuenta.numCuenta=1L;
			 }
			 
		}else {
			//Si no existe lo creamos
			try {
				fichero.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}
	
	 public CuentaCorriente cargarCuentaCorriente(BufferedReader br) throws TitularInvalidoException, SaldoInvalidoException, ComisionInvalidaException, SaldoMinInvalidoException, IOException {
	       
	            String linea;
	            linea = br.readLine();
	                String[] datos = linea.split(",");
	                if (datos.length == 7) {
	                        // Convertir los datos a los tipos adecuados
	                        Long numero = Long.parseLong(datos[0].trim());
	                        String titular = datos[1].trim();
	                        double saldo = Double.parseDouble(datos[2].trim());
	                        double saldoMinimo = Double.parseDouble(datos[3].trim());
	                        LocalDate fechaApertura = LocalDate.parse(datos[4].trim());
	                        double comisionMantenimiento = Double.parseDouble(datos[5].trim());
	                        TipoComisionMensual tipo = TipoComisionMensual.valueOf(datos[6].trim());

	                        // Crear la cuenta y añadirla a la lista
	                        return new CuentaCorriente(numero, titular, saldo, saldoMinimo, fechaApertura, comisionMantenimiento, tipo);
	                      


	                }

	            
				return null;


	    }
	
	 public CuentaAhorro cargarCuentaAhorro(BufferedReader br) throws TitularInvalidoException, SaldoInvalidoException, InteresInvalidoException, SaldoMinInvalidoException, IOException {
		    String linea;
		    linea = br.readLine();
		        String[] datos = linea.split(",");
		        if (datos.length == 7) {

		                // Convertir los datos a los tipos adecuados
		        		Long numero = Long.parseLong(datos[0].trim());
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
		    
			return null;
		}
	 
	 public void guardar() {
		    try (BufferedWriter bw = new BufferedWriter(new FileWriter(ruta))) {
		        // Recorrer la lista de cuentas y guardarlas
		    	List<Cuenta> listAux=this.listaCuentas.getLista();
		        for (Cuenta cuenta : listAux) {
		            if (cuenta instanceof CuentaCorriente) {
		                guardarCuentaCorriente((CuentaCorriente) cuenta, bw);
		            } else if (cuenta instanceof CuentaAhorro) {
		                guardarCuentaAhorro((CuentaAhorro) cuenta, bw);
		            }
		        }
		    } catch (IOException e) {
		        System.out.println("Error al guardar el archivo: " + e.getMessage());
		    }
		}

		private void guardarCuentaCorriente(CuentaCorriente cuenta, BufferedWriter bw) throws IOException {
		    // Escribir en el archivo los datos de la cuenta corriente
		    bw.write("1\n");  // Indicador de que es una cuenta corriente
		    bw.write(cuenta.getNumero() + "," + cuenta.getTitular() + "," + cuenta.getSaldo() + "," +
		             cuenta.getSaldoMinimo() + "," + cuenta.getFechaApertura() + "," + cuenta.getComisionMantenimiento() + "," +
		             cuenta.getTipo().name() + "\n");
		}

		private void guardarCuentaAhorro(CuentaAhorro cuenta, BufferedWriter bw) throws IOException {
		    // Escribir en el archivo los datos de la cuenta de ahorro
		    bw.write("2\n");  // Indicador de que es una cuenta de ahorro
		    bw.write(cuenta.getNumero() + "," + cuenta.getTitular() + "," + cuenta.getSaldo() + "," +
		             cuenta.getSaldoMinimo() + "," + cuenta.getFechaApertura() + "," + cuenta.getInteres() + "," +
		             cuenta.getTipo().name() + "\n");
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

	public List<Cuenta> getLista() {
		return this.listaCuentas.getLista();
	}
	
	public Cuenta actualizarSaldo(Cuenta c) {
		LocalDate ahora=LocalDate.now();
		double nuevoSaldo=c.getSaldo();
		
		//Si es cuenta ahorra aumenta su interes por año en %
		if(c instanceof CuentaAhorro) {
			long aniosDiferencia=ChronoUnit.YEARS.between(c.getFechaApertura(), ahora);
			
		CuentaAhorro caux=(CuentaAhorro) c;
			nuevoSaldo=c.getSaldo()+aniosDiferencia*caux.getInteres();
		}
		
		
		//Si es cuenta corriente disminuye la comision por mes.
		if(c instanceof CuentaCorriente) {
			long mesesDiferencia=ChronoUnit.MONTHS.between(c.getFechaApertura(), ahora);
			
			CuentaCorriente caux=(CuentaCorriente) c;
			nuevoSaldo=c.getSaldo()-mesesDiferencia*caux.getComisionMantenimiento();
		}
		//Guardar nuevo dato
		this.listaCuentas.eliminar(c);
		try {
			c.setSaldo(nuevoSaldo);
		} catch (SaldoInvalidoException e) {
			try {
				c.setSaldo(c.getSaldoMinimo());
			} catch (SaldoInvalidoException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		this.listaCuentas.agregar(c);
		guardar();
		return c;
	}
	
	public void generarCuentas() {
		int cuentasGenerar=1000;
		 String[] nombres = {
		            "Ana", "Luis", "Carlos", "Marta", "Pedro", 
		            "Julia", "Javier", "Rosa", "Daniel", "Lucia", 
		            "Victor", "Isabel", "Manuel", "Sonia", "Jorge"
		        };
		 
		for(int i=0;i<=cuentasGenerar;i++) {
			//Generar aleatoriamente cuentaAhorro o cuentaCorriente:
			int aux=(int) Math.random()*2;
			int nombre=(int) (Math.random()*(nombres.length+1));
			double saldoMinimo=Math.random()*1000;
				double f = Math.random()/Math.nextDown(1.0);
			double saldo=saldoMinimo*(1.0 - f);
			LocalDate fechaApertura=generarFecha(1950,LocalDate.now().getYear());
			
			if(aux==0) {
				//Genera una CuentaCorriente
				double comisionMantenimiento=Math.random()*100; //de 0 a 100;
				TipoComisionMensual tipo=TipoComisionMensual.values()[(int) Math.random()*4];
				
				//Anadir a la lista
				try {
					CuentaCorriente caux=new CuentaCorriente(nombres[nombre],saldo,saldoMinimo,fechaApertura,comisionMantenimiento,tipo);
					this.listaCuentas.agregar(caux);
				} catch (Exception e) {}

				
				
			}else {
				//Genera una CuentaAhorro
				double interes=Math.random()*10; //de 0 a 10;
				TipoCuentaAhorro tipo=TipoCuentaAhorro.values()[(int) Math.random()*4];
				
				//Anadir a la lista
				try {
					CuentaAhorro caux=new CuentaAhorro(nombres[nombre],saldo,saldoMinimo,fechaApertura,interes,tipo);
					this.listaCuentas.agregar(caux);
				} catch (Exception e) {}
			}
					

		}
		
	}
	private LocalDate generarFecha(int yearStart, int yearEnd) {
		 // Crear un objeto Random
        Random random = new Random();
        // Generar un año aleatorio entre yearStart y yearEnd
        int year = random.nextInt(yearEnd - yearStart) + yearStart;
        // Generar un mes aleatorio entre 1 y 12
        int month = random.nextInt(12) + 1;

        // Para obtener un día aleatorio, debemos tener en cuenta la cantidad de días de ese mes
        int day = 0;

        // Se puede usar Month para obtener el número de días de cada mes
        switch (month) {
            case 1: case 3: case 5: case 7: case 8: case 10: case 12:
                day = random.nextInt(31) + 1; // Meses con 31 días
                break;
            case 4: case 6: case 9: case 11:
                day = random.nextInt(30) + 1; // Meses con 30 días
                break;
            case 2:
                // Si el mes es febrero, verificar si el año es bisiesto
                if (esBisiesto(year)) {
                    day = random.nextInt(29) + 1; // Año bisiesto, febrero tiene 29 días
                } else {
                    day = random.nextInt(28) + 1; // Año no bisiesto, febrero tiene 28 días
                }
                break;
        }

        // Crear la fecha aleatoria
        LocalDate fechaGenerada=LocalDate.of(year, month, day);
        if(fechaGenerada.isBefore(LocalDate.now())){
        	return fechaGenerada;
        }else {
            return this.generarFecha(yearStart, yearEnd);
        }
   
    }
	
	// Método para comprobar si un año es bisiesto
    public static boolean esBisiesto(int year) {
        return (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0));
    }
    
	public void generarPrueba() throws TitularInvalidoException, SaldoInvalidoException, InteresInvalidoException, SaldoMinInvalidoException, ComisionInvalidaException 
	{
		
        CuentaCorriente cuentaCorriente1 = new CuentaCorriente("Juan Perez", 1500.0, 500.0,LocalDate.now(), 10.0, TipoComisionMensual.MANTENIMIENTO);
        CuentaCorriente cuentaCorriente2 = new CuentaCorriente("Ana Garcia", 2300.0, 800.0,LocalDate.now(), 15.0, TipoComisionMensual.ADMINISTRACION);
        
        CuentaAhorro cuentaAhorro1 = new CuentaAhorro("Carlos Lopez", 5000.0, 300.0, LocalDate.now(), 2.5, TipoCuentaAhorro.ONLINE);
        CuentaAhorro cuentaAhorro2 = new CuentaAhorro("Lucia Martinez", 12000.0, 1000.0,LocalDate.now(), 3.0, TipoCuentaAhorro.PLAZO_FIJO);
        
        
        
        // Agregar las cuentas a la lista de cuentas
        listaCuentas.agregar(cuentaCorriente1);
        listaCuentas.agregar(cuentaCorriente2);
        listaCuentas.agregar(cuentaAhorro1);
        listaCuentas.agregar(cuentaAhorro2);
    }
		
	
}
