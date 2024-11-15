package model;

import java.time.LocalDate;

public class CuentaCorriente extends Cuenta {
	private Double comisionMantenimiento;
	private TipoComisionMensual tipo;
	

	public CuentaCorriente(int numero, String titular, Double saldo, Double saldoMinimo, LocalDate fechaApertura,
			Double comisionMantenimiento, TipoComisionMensual tipo) {
		super(numero, titular, saldo, saldoMinimo, fechaApertura);
		setComisionMantenimiento(comisionMantenimiento);
		setTipo(tipo);
	}

	public Double getComisionMantenimiento() {
		return comisionMantenimiento;
	}

	public void setComisionMantenimiento(Double comisionMantenimiento) {
		if(comisionMantenimiento>0) {
			this.comisionMantenimiento = comisionMantenimiento;
		}
		
	}

	public TipoComisionMensual getTipo() {
		return tipo;
	}

	public void setTipo(TipoComisionMensual tipo) {
		this.tipo = tipo;
	}
	

	public enum TipoComisionMensual {
		COMUN, JOVEN, PLAZO_FIJO, ONLINE;
	}


	@Override
	public String toString() {
		return "CuentaCorriente [comisionMantenimiento=" + comisionMantenimiento + ", tipo=" + tipo + "]";
	}
	
	

}
