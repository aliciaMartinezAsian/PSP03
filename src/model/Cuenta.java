package model;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Cuenta {
	
	private int numero;
	private transient String titular;
	private Double saldo;
	private Double saldoMinimo;
	private LocalDate fechaApertura;
	
	
	
	public Cuenta(int numero, String titular, Double saldo, Double saldoMinimo, LocalDate fechaApertura) {
		setNumero(numero);
		setTitular(titular);
		setSaldo(saldo);
		setSaldoMinimo(saldoMinimo);
		setFechaApertura(fechaApertura);
	}
	
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		if(numero>0 && numero<1001) {
			this.numero = numero;
		}
		
	}
	public String getTitular() {
		return titular;
	}
	public void setTitular(String titular) {
		String alfanumerico = "\\w+"; 

        Pattern pattern = Pattern.compile(alfanumerico);
        Matcher matcher = pattern.matcher(titular);

        if (matcher.matches()) {
        	this.titular = titular;
        }
		
	}
	public Double getSaldo() {
		return saldo;
	}
	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}
	public Double getSaldoMinimo() {
		return saldoMinimo;
	}
	public void setSaldoMinimo(Double saldoMinimo) {
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
