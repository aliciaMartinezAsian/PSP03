package controller;

import java.util.ArrayList;
import java.util.List;

import model.Cuenta;

public class Lista <E> {
    private Nodo<E> primero;
    private Nodo<E> ultimo;
    

    public void agregar(E p){
        Nodo<E> nuevoNodo = new Nodo<E>();
        nuevoNodo.setPrincipal(p);
        
        if(this.primero==null) {
        	this.primero=nuevoNodo;
        	this.ultimo=this.primero;
        }else {
        	Nodo temporal=ultimo;
        	ultimo=nuevoNodo;
        	ultimo.setAnterior(temporal);
        	temporal.setSiguiente(ultimo);
        	
        }
    }
    
    public void eliminar(Cuenta c) {
    	Nodo temporal=null; //El que vamos a eliminar
    	Nodo aux=null; //Nodo que estaba antes del que se va a eliminar
    	
    	
    	if(primero!=null) {
    		if(primero.getPrincipal()==c) {
    			//Caso de eliminar el 1er elemento de la lista
    			temporal=primero;
    			primero=temporal.getSiguiente();
    			
    			if(primero==null) {
    				ultimo=null;
    			}else {
    				primero.setAnterior(null);
    			}
    			
    		}else if(ultimo.getPrincipal()==c){
				//Caso de eliminar el ultimo
    			temporal=ultimo;
    			ultimo=temporal.getAnterior();
    			ultimo.setSiguiente(null);
    			
    		}else {
    			//Caso eliminar uno de en medio
    			aux=primero;
    			temporal=primero.getSiguiente();
    			
    			while(temporal!=null) {
    				if(temporal.getPrincipal()==c) {
    					aux.setSiguiente(temporal.getSiguiente());
    					temporal.getSiguiente().setAnterior(aux);
    					break;
    				}
    				aux=temporal;
    				temporal=temporal.getSiguiente();
    			}
    		}
    		
    	}
    	
    	
    	
    	
    }
    
    public List sacarLista() {
    	Nodo temporal=primero;
    	List<Cuenta> listaTemp=new ArrayList();
    	
    	while(temporal!=null) {
    		listaTemp.add((Cuenta) temporal.getPrincipal());
    		temporal=temporal.getSiguiente();
    	}
    	return listaTemp;
    }
}
