package model;

public class TitularInvalidoException extends Exception{

	static String txt="El nombre debe tener como maximo 50 caracteres";
	public TitularInvalidoException() {
		super(txt);
	}

}
