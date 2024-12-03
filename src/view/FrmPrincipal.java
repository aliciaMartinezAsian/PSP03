package view;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.Ctr;
import model.ComisionInvalidaException;
import model.Cuenta;
import model.InteresInvalidoException;
import model.SaldoInvalidoException;
import model.SaldoMinInvalidoException;
import model.TitularInvalidoException;

public class FrmPrincipal extends JFrame{

    private static final long serialVersionUID = 1L;
    
	public PanelVer panelVer;
    public PanelAltaAhorro panelAltaAhorro;
    public PanelAltaCorriente panelAltaCorriente;
    public PanelLista panelLista;
    private MenuBar menuBar;
    private JPanel contentPane;
    
    private Ctr ctr = Ctr.getControlador();
    

    public FrmPrincipal() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(240, 240, 450, 300);
        setResizable(false);
        
        setTitle("Gestión de Cuentas");
        setSize(550, 350);
        
        menuBar = new MenuBar();
        setJMenuBar(menuBar);
       
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout()); 
        setContentPane(contentPane); 

        // Inicializar paneles
        panelVer = new PanelVer();
        panelAltaAhorro = new PanelAltaAhorro();
        panelAltaCorriente = new PanelAltaCorriente();
        panelLista = new PanelLista();
     
        
        // Añadir el panel por defecto
        contentPane.add(panelLista, BorderLayout.CENTER);
        
        initListeners();
        
        setVisible(true);
    }

    private void initListeners() {
        menuBar.verItem.addActionListener(e -> showPanel(panelVer));
        menuBar.altaAhorroItem.addActionListener(e -> showPanel(panelAltaAhorro));
        menuBar.altaCorrienteItem.addActionListener(e -> showPanel(panelAltaCorriente));
        menuBar.listaItem.addActionListener(e -> showPanel(panelLista));
        menuBar.cargarItem.addActionListener(e -> cargarArchivo());
        menuBar.guardarItem.addActionListener(e -> ctr.guardar());
        menuBar.pruebaItem.addActionListener(e -> realizarPrueba());
        menuBar.borrarItem.addActionListener(e -> vaciarLista());
    }

    private void showPanel(JPanel panel) {
        contentPane.removeAll();
        contentPane.add(panel, BorderLayout.CENTER);
        contentPane.revalidate();
        contentPane.repaint();
    }
    
    private void cargarArchivo() {
    	
    	List<Cuenta> cuentas = ctr.getLista();
    	
    	panelLista.cargarCuentas(cuentas);
    }
    
    private void realizarPrueba() {
    	try {
			ctr.generarPrueba();
			List<Cuenta> cuentas = ctr.getLista();
			panelLista.cargarCuentas(cuentas);
			
		} catch (TitularInvalidoException | SaldoInvalidoException | InteresInvalidoException
				| SaldoMinInvalidoException | ComisionInvalidaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    private void vaciarLista() {
    	ctr.borrarTodo();
List<Cuenta> cuentas = ctr.getLista();
    	
    	panelLista.cargarCuentas(cuentas);
    }
}
