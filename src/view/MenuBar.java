package view;

import javax.swing.JMenuBar; 
import javax.swing.JMenuItem;
import javax.swing.JMenu;

public class MenuBar extends JMenuBar {
	public JMenuItem verItem;
	public JMenuItem listaItem;
	private JMenu altaItem;
	public JMenuItem altaAhorroItem;
	public JMenuItem altaCorrienteItem;
	private JMenu fileItem;
	public JMenuItem guardarItem;
	public JMenuItem borrarItem;
	public JMenuItem cargarItem;
	public JMenuItem pruebaItem;
	public JMenuItem generar1000CuentasItem;
	
    public MenuBar() {
        verItem = new JMenuItem("Ver");
        
        listaItem = new JMenuItem("Lista");
        
        add(verItem);
        add(listaItem);
        
        fileItem = new JMenu("File");
        add(fileItem);
        
        guardarItem = new JMenuItem("Guardar");
        fileItem.add(guardarItem);
        
        borrarItem = new JMenuItem("Borrar");
        fileItem.add(borrarItem);
        
        cargarItem = new JMenuItem("Cargar");
        fileItem.add(cargarItem);
        
        pruebaItem = new JMenuItem("Generar prueba");
        fileItem.add(pruebaItem);
      
        generar1000CuentasItem = new JMenuItem("Generar 1000 cuentas");
        fileItem.add(generar1000CuentasItem);
        
        altaItem = new JMenu("Alta");     
        add(altaItem);
        
        altaAhorroItem = new JMenuItem("Cuenta Ahorro");
        altaItem.add(altaAhorroItem);
        
        altaCorrienteItem = new JMenuItem("Cuenta Corriente");
        altaItem.add(altaCorrienteItem);
        
    }
}

