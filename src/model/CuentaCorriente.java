package model;

import java.time.LocalDate;

public class CuentaCorriente extends Cuenta {
	private Double comisionMantenimiento;
	private TipoComisionMensual tipo;
	

	public CuentaCorriente(Long numero, String titular, Double saldo, Double saldoMinimo, LocalDate fechaApertura,
			Double comisionMantenimiento, TipoComisionMensual tipo) throws TitularInvalidoException, SaldoInvalidoException, ComisionInvalidaException, SaldoMinInvalidoException {
		super(numero, titular, saldo, saldoMinimo, fechaApertura);
		setComisionMantenimiento(comisionMantenimiento);
		setTipo(tipo);
	}

	public CuentaCorriente(String titular, Double saldo, Double saldoMinimo, LocalDate fechaApertura,
			Double comisionMantenimiento, TipoComisionMensual tipo) throws TitularInvalidoException, SaldoInvalidoException, ComisionInvalidaException, SaldoMinInvalidoException {
		super(titular, saldo, saldoMinimo, fechaApertura);
		setComisionMantenimiento(comisionMantenimiento);
		setTipo(tipo);
	}

	public Double getComisionMantenimiento() {
		return comisionMantenimiento;
	}

	public void setComisionMantenimiento(Double comisionMantenimiento) throws ComisionInvalidaException {
		if(comisionMantenimiento<0) {
			throw new ComisionInvalidaException("La comisión de mantenimiento no puede ser negativa");
		}else {
			this.comisionMantenimiento = comisionMantenimiento;
		}
		
	}

	public TipoComisionMensual getTipo() {
		return tipo;
	}

	public void setTipo(TipoComisionMensual tipo) {
		this.tipo = tipo;
	}
	


	@Override
	public String toString() {
		return "CuentaCorriente [comisionMantenimiento=" + comisionMantenimiento + ", tipo=" + tipo + "]";
	}
	
	

}
