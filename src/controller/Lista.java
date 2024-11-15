package controller;

public class Lista <E> {
    private Nodo<E> inicio;
    
    public Lista(){
        this.inicio = null;
    }
    
    public void mostrarLista(){     // Es de prueba
        Nodo<E> aux = this.inicio;
        while (aux != null){
            System.out.println(aux.getPricipal().toString());
            aux=aux.getSiguiente();
        }
    }
    
    public void insertarNodo(E p){
        Nodo<E> nuevoNodo = new Nodo(p);
        nuevoNodo.setSiguiente(this.inicio); 
        this.inicio = nuevoNodo;
    }
}
