package view;

import javax.swing.JMenuBar; 
import javax.swing.JMenuItem;
import javax.swing.JMenu;

public class MenuBar extends JMenuBar {
	public JMenuItem verItem;
	public JMenuItem listaItem;
	public JMenuItem menuPrincipalItem;
	private JMenu altaItem;
	public JMenuItem altaAhorroItem;
	private JMenuItem altaCorrienteItem;
	
    public MenuBar() {

    	 menuPrincipalItem = new JMenuItem("Menu Principal");
        verItem = new JMenuItem("Ver");
        
        listaItem = new JMenuItem("Lista");
        
        add(menuPrincipalItem);
        add(verItem);
        add(listaItem);
        
        altaItem = new JMenu("Alta");     
        add(altaItem);
        
        altaAhorroItem = new JMenuItem("Cuenta Ahorro");
        altaItem.add(altaAhorroItem);
        
        altaCorrienteItem = new JMenuItem("Cuenta Corriente");
        altaItem.add(altaCorrienteItem);
        
    }
}

