package model;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import controller.Ctr;

public class Cuenta {
	
	private Long numero;
	private transient String titular;
	private Double saldo;
	private Double saldoMinimo;
	private LocalDate fechaApertura;
	public static Long numCuenta;
	
	
	public Cuenta(Long numero, String titular, Double saldo, Double saldoMinimo, LocalDate fechaApertura) throws TitularInvalidoException, SaldoInvalidoException, SaldoMinInvalidoException {
			
		setNumero(numero);
		setTitular(titular);
		setSaldoMinimo(saldoMinimo);
		setSaldo(saldo);
		setFechaApertura(fechaApertura);
	}
	public Cuenta(String titular, Double saldo, Double saldoMinimo, LocalDate fechaApertura) throws TitularInvalidoException, SaldoInvalidoException, SaldoMinInvalidoException {
		
		//Numero de cuenta se pone automatico y va aumentando
		setNumero(Cuenta.numCuenta);
		numCuenta++;
		
		setTitular(titular);
		setSaldoMinimo(saldoMinimo);
		setSaldo(saldo);
		setFechaApertura(fechaApertura);
	}
	
	
	public Long getNumero() {
		return numero;
	}
	public void setNumero(Long numCuenta2) {
			this.numero = numCuenta2;
		
		
	}
	public String getTitular() {
		return titular;
	}
	public void setTitular(String titular) throws TitularInvalidoException {
		String alfanumerico = "\\w+"; 

        Pattern pattern = Pattern.compile(alfanumerico);
        Matcher matcher = pattern.matcher(titular);

        if (!matcher.matches()) {
        	throw new TitularInvalidoException("El titular debe ser alfanumérico");
        }else {
        	if(titular.length()>50){
            	throw new TitularInvalidoException("El titular debe tener como máximo 50 caracteres");
            	}else {
            		this.titular = titular;
            	}
        }
		
	}
	public Double getSaldo() {
		return saldo;
	}
	public void setSaldo(Double saldo) throws SaldoInvalidoException {
		if (saldo < this.saldoMinimo)
			throw new SaldoInvalidoException("El saldo no puede ser menor al saldo minimo");
		else {
				this.saldo = saldo;
		}
	}
	public Double getSaldoMinimo() {
		return saldoMinimo;
	}
	public void setSaldoMinimo(Double saldoMinimo) throws SaldoMinInvalidoException {
		if(saldoMinimo<0) {
			throw new SaldoMinInvalidoException ("El saldo no puede ser negativo");
		}
		this.saldoMinimo = saldoMinimo;
	}
	public LocalDate getFechaApertura() {
		return fechaApertura;
	}
	public void setFechaApertura(LocalDate fechaApertura) {
		this.fechaApertura = fechaApertura;
	}
	
	@Override
	public String toString() {
		return "Cuenta [numero=" + numero + ", saldo=" + saldo + ", saldoMinimo=" + saldoMinimo + ", fechaApertura="
				+ fechaApertura + "]";
	}
	
	

}
