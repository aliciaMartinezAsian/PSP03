package view;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class PanelMenuPrincipal extends JPanel {
	public PanelMenuPrincipal() {
		
		
		JButton btnNewButton_2 = new JButton("Guardar");
		btnNewButton_2.setBounds(172, 56, 89, 23);
		btnNewButton_2.setVerticalAlignment(SwingConstants.TOP);
		add(btnNewButton_2);
		
		JButton btnNewButton_1 = new JButton("Borrar");
		btnNewButton_1.setBounds(172, 87, 89, 23);
		add(btnNewButton_1);
		
		JButton btnNewButton = new JButton("Cargar");
		btnNewButton.setBounds(172, 23, 89, 23);
		add(btnNewButton);
	}

	
  
}