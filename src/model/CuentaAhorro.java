package model;

import java.time.LocalDate;

public class CuentaAhorro extends Cuenta {
	private Double interes;
	private TipoCuentaAhorro tipo;
	

	public CuentaAhorro(int numero, String titular, Double saldo, Double saldoMinimo, LocalDate fechaApertura,Double interes, String tipo) throws TitularInvalidoException, SaldoInvalidoException, InteresInvalidoException, SaldoMinInvalidoException {
		super(numero, titular, saldo, saldoMinimo, fechaApertura);
		setInteres(interes);
		setTipo(TipoCuentaAhorro.valueOf(tipo.toUpperCase()));
	}

	public Double getInteres() {
		return interes;
	}

	public void setInteres(Double interes2) throws InteresInvalidoException {
		if(interes2>=0 && interes2<=100) {
			throw new InteresInvalidoException ("El interes anual debe estar entre el 0% y el 100%");
		}else {
			this.interes = interes2;
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
