package view;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class FrmPrincipal extends JFrame{

	public PanelVer panelVer;
    public PanelAltaAhorro panelAltaAhorro;
    public PanelMenuPrincipal panelMenuPrincipal;
    private MenuBar menuBar;
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    public FrmPrincipal() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(150, 150, 450, 300);
        setResizable(false);
        
        setTitle("Gestión de Cuentas");
        setSize(500, 300);
        
        menuBar = new MenuBar();
        setJMenuBar(menuBar);

       
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout()); 
        setContentPane(contentPane); 

        // Inicializar paneles
        panelVer = new PanelVer();
        panelAltaAhorro = new PanelAltaAhorro();
        panelMenuPrincipal = new PanelMenuPrincipal();
        
        // Añadir el panel por defecto
        contentPane.add(panelMenuPrincipal, BorderLayout.CENTER);
        
        initListeners();
        
        setVisible(true);
    }

    private void initListeners() {
        menuBar.verItem.addActionListener(e -> showPanel(panelVer));
        menuBar.altaAhorroItem.addActionListener(e -> showPanel(panelAltaAhorro));
        menuBar.menuPrincipalItem.addActionListener(e -> showPanel(panelMenuPrincipal));
    }

    private void showPanel(JPanel panel) {
        contentPane.removeAll();
        contentPane.add(panel, BorderLayout.CENTER);
        contentPane.revalidate();
        contentPane.repaint();
    }
}
