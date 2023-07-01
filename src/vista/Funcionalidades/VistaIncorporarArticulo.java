package vista.Funcionalidades;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import modelo.productos.Articulo;
import modelo.sedes.Clase;
import modelo.supertlon.GimnasioSingleton;

public class VistaIncorporarArticulo extends JFrame{
	private JTextField cantidadArticulos;
	private DefaultTableModel tablaModelo = new DefaultTableModel();
	private JTable tablaArticulos = new JTable(tablaModelo);
	private JButton aceptarButton;
	private JButton cancelarButton;
	private ControladorIncorporarArticulo controlador;
	
	public VistaIncorporarArticulo(ControladorIncorporarArticulo controlador) {
		this.controlador=controlador;
		setTitle("Incorporar articulo.");
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	    JPanel contentPanel = new JPanel(new GridBagLayout());
	    GridBagConstraints gbc = new GridBagConstraints();
	    gbc.insets = new Insets(5, 5, 5, 5);
	
	    // Tabla de Sedes
	    tablaModelo.addColumn("Articulo");
	    tablaModelo.addColumn("Marca");
		tablaModelo.addColumn("Atributos");
		tablaModelo.addColumn("Amortizacion");
		tablaModelo.addColumn("Precio");
		
		
		JScrollPane tablaScroll = new JScrollPane(tablaArticulos, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		tablaArticulos.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	    gbc.gridx = 0;
	    gbc.gridy = 0;
	    gbc.gridwidth = 2;
	    gbc.anchor = GridBagConstraints.NORTH;
	    contentPanel.add(tablaScroll, gbc);
	
	    // Estado Clase
	    cantidadArticulos = new JTextField();
	    gbc.gridx = 1;
	    gbc.gridy = 1;
	    gbc.gridwidth = 1;
	    gbc.fill=GridBagConstraints.HORIZONTAL;
	    gbc.anchor = GridBagConstraints.LINE_START;
	    contentPanel.add(cantidadArticulos, gbc);
	
	    gbc.gridx = 0;
	    contentPanel.add(new JLabel("Cantidad: "), gbc);
	
	    // Botones Aceptar y Cancelar
	    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 0));
	    cancelarButton=new JButton("Cancelar");
	    buttonPanel.add(cancelarButton);
	    aceptarButton=new JButton("Aceptar");
	    buttonPanel.add(aceptarButton);
	    
	    cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	JFrame frame = (JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource());
			    frame.dispose();
                
            }
        });
	    
	    aceptarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	
                // Obtener el valor seleccionado
            	try {
					Articulo articulo=controlador.getArticuloSeleccionado();
					String estado=(String) cantidadArticulos.getText();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            	
                // Realizar la acción deseada con los datos

                
               
                // cierra la ventana
                
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource());
			    frame.dispose();
                
                
            }
            
        });
	
	    gbc.gridx = 0;
	    gbc.gridy = 2;
	    gbc.gridwidth = 2;
	    gbc.anchor = GridBagConstraints.CENTER;
	    contentPanel.add(buttonPanel, gbc);
	
	    setContentPane(contentPanel);
	    pack();
	    setLocationRelativeTo(null); // Centrar la ventana en la pantalla
	}
	public void configurarTabla(ArrayList<Articulo> catalogo) {
		for(Articulo articulo: catalogo) {
			Object[] rowData = {articulo.getArticulo(),articulo.getMarca(),articulo.getAtributos(), articulo.getTipoAmortizacion(),articulo.getPrecio() };
			tablaModelo.addRow(rowData);
		}
	}
	
	public int getArticuloSeleccionado() {
		return tablaArticulos.getSelectedRow();
	}
}