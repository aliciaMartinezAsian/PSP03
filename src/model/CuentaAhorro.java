package model;

import java.time.LocalDate;

public class CuentaAhorro extends Cuenta {
	private Double interes;
	private TipoCuentaAhorro tipo;
	

	public CuentaAhorro(Long numero, String titular, Double saldo, Double saldoMinimo, LocalDate fechaApertura,Double interes, TipoCuentaAhorro tipo) throws TitularInvalidoException, SaldoInvalidoException, InteresInvalidoException, SaldoMinInvalidoException {
		super(numero, titular, saldo, saldoMinimo, fechaApertura);
		setInteres(interes);
		setTipo(tipo);
	}
	
	public CuentaAhorro(String titular, Double saldo, Double saldoMinimo, LocalDate fechaApertura,Double interes, TipoCuentaAhorro tipo) throws TitularInvalidoException, SaldoInvalidoException, InteresInvalidoException, SaldoMinInvalidoException {
		super(titular, saldo, saldoMinimo, fechaApertura);
		setInteres(interes);
		setTipo(tipo);
	}


	public Double getInteres() {
		return interes;
	}

	public void setInteres(Double interes2) throws InteresInvalidoException {	
		if(interes2>=0.0 && interes2<=100.0) {
			this.interes = interes2;
		}else {
			throw new InteresInvalidoException ("El interes anual debe estar entre el 0% y el 100%");
			
		}
		
	}

	public TipoCuentaAhorro getTipo() {
		return tipo;
	}

	public void setTipo(TipoCuentaAhorro tipo) {	
		this.tipo = tipo;
	}


	@Override
	public String toString() {
		return "CuentaAhorro [interes=" + interes + "%, tipo=" + tipo + "]";
	}
	
	
	

}
