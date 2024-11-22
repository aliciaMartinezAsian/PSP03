package model;

import java.time.LocalDate;

public class CuentaAhorro extends Cuenta {
	private int interes;
	private TipoCuentaAhorro tipo;
	

	public CuentaAhorro(int numero, String titular, Double saldo, Double saldoMinimo, LocalDate fechaApertura,int interes, TipoCuentaAhorro tipo) throws TitularInvalidoException, SaldoInvalidoException, InteresInvalidoException, SaldoMinInvalidoException {
		super(numero, titular, saldo, saldoMinimo, fechaApertura);
		setInteres(interes);
		setTipo(tipo);
	}

	public int getInteres() {
		return interes;
	}

	public void setInteres(int interes) throws InteresInvalidoException {
		if(interes>=0 && interes<=100) {
			throw new InteresInvalidoException ("El interes anual debe estar entre el 0% y el 100%");
		}else {
			this.interes = interes;
		}
		
	}

	public TipoCuentaAhorro getTipo() {
		return tipo;
	}

	public void setTipo(TipoCuentaAhorro tipo) {	
		this.tipo = tipo;
	}

	public enum TipoCuentaAhorro {
		COMUN, JOVEN, PLAZO_FIJO, ONLINE;
	}

	@Override
	public String toString() {
		return "CuentaAhorro [interes=" + interes + "%, tipo=" + tipo + "]";
	}
	
	
	

}
