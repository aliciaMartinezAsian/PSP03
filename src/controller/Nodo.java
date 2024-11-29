package controller;

public class Nodo<E>{
	
	//Valores necesarios
    private Nodo<E> siguiente;
    E principal;
    private Nodo<E> anterior;
    
    public Nodo(){
    	
    }
	public Nodo<E> getSiguiente() {
		return siguiente;
	}
	public void setSiguiente(Nodo<E> siguiente) {
		this.siguiente = siguiente;
	}
	public E getPrincipal() {
		return principal;
	}
	public void setPrincipal(E principal) {
		this.principal = principal;
	}
	public Nodo<E> getAnterior() {
		return anterior;
	}
	public void setAnterior(Nodo<E> anterior) {
		this.anterior = anterior;
	}
    
    
    

}


