package controller;

public class Nodo<E>{
    private Nodo<E> siguiente;
    E principal;
    
    public Nodo(E p){
        this.siguiente = null;
        this.principal = p;                   
    }
    
    public Nodo<E> getSiguiente() {
        return this.siguiente;
    }

    public void setSiguiente(Nodo<E> siguiente) {
        this.siguiente = siguiente;
    }

    public E getPricipal() {
        return principal;
    }

    public void setPrincipal(E p) {
        this.principal = p;
    }
}


