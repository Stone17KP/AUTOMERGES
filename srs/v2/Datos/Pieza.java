package Datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.LinkedList;

import javax.swing.JOptionPane;

public class Pieza {
	private int numero_de_pieza;
	private String nombre;
	private double precio;
	private String importada;
	
	private LinkedList<Pieza> Piezas = new LinkedList<Pieza>();
	
    Conexion con = new Conexion();
	
	Connection conexion = con.conectar();
	
	PreparedStatement stmt;
	
	
	public Pieza(int numero_de_pieza, String nombre, double precio, String importada) {
		super();
		this.numero_de_pieza = numero_de_pieza;
		this.nombre = nombre;
		this.precio = precio;
		this.importada = importada;
	}
	
	public int getNumero_de_pieza() {
		return numero_de_pieza;
	}

	public void setNumero_de_pieza(int numero_de_pieza) {
		this.numero_de_pieza = numero_de_pieza;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public String getImportada() {
		return importada;
	}

	public void setImportada(String importada) {
		this.importada = importada;
	}
	public Pieza() {
		this.Piezas = new LinkedList<Pieza>();    
	}
	
	public LinkedList<Pieza> getPiezas() {
		return Piezas;
	}

	@Override
	public String toString() {
		return "Numero de Pieza: "+numero_de_pieza+"\nNombre: "+nombre+"\nPrecio:"+precio+"\nImportado: "+importada;
	}
	private LinkedList<Pieza> PiezasPausadas = new LinkedList<Pieza>();
	
	public boolean guardarPieza() {
		
		String sql ="INSERT INTO `pieza`(`Numero_de_pieza`, `nombre_Pieza`, `Pieza_precio`,'Importada','fabricante_idfabricante','Estado_proceso') VALUES (?,?,?,?,?,?)";
		
		try {
			stmt = conexion.prepareStatement(sql);
			stmt.setString(1,String.valueOf(this.getNumero_de_pieza()));
			stmt.setString(2, this.getNombre());
			stmt.setString(3,String.valueOf(this.getPrecio()));
			stmt.setString(4,String.valueOf(this.getImportada()));
			stmt.setString(5,String.valueOf(this.getPrecio()));
			stmt.setString(6,String.valueOf(this.getPrecio()));
			stmt.executeUpdate();
			return true;
			
		}catch(Exception excepcion){
			System.out.println(excepcion.getMessage());
			return false;
		}
	}
	public String IniciarProceso(){
		int sel;
		int num=+1;
		do {
		double precio=Double.parseDouble(JOptionPane.showInputDialog("Ingrese el precio"));
		String nom = JOptionPane.showInputDialog("Ingrese el nombre de la pieza");
		String imp="no";
		
		 Pieza pieza1 = new Pieza (num++,nom,precio,imp);
		 Piezas.add(pieza1);
		
		sel = Integer.parseInt(JOptionPane.showInputDialog("Ingrese \n1Si quiere agregar otra pieza \n2 Voler al menu"));
		} while (sel==1);
		return "";
	}
	public boolean PararProceso() {
	    if (Piezas.isEmpty()) {
	        JOptionPane.showMessageDialog(null, "La lista está vacía");
	        return false;
	    } else {
	    	JOptionPane.showMessageDialog(null, Piezas);
	        int piez = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el número de pieza del cual desea parar el proceso"));
	        Pieza piezaEncontrada = null;
	        
	        for (Pieza elemento : Piezas) {
	            if (elemento.getNumero_de_pieza() == piez) {
	                piezaEncontrada = elemento;
	                break;
	            }
	        }
	        
	        if (piezaEncontrada != null) {
	            JOptionPane.showMessageDialog(null, "Paro el proceso de la pieza: " + piezaEncontrada.getNumero_de_pieza());
	            PiezasPausadas.add(piezaEncontrada);
	            Piezas.remove(piezaEncontrada);
	        } else {
	            JOptionPane.showMessageDialog(null, "No se encontró la pieza con el número especificado");
	        }
	    }
	    
	    return true;
	}
	/*public boolean PararProceso(){
		JOptionPane.showMessageDialog(null, Piezas);
		int piez=Integer.parseInt(JOptionPane.showInputDialog("Ingrese el numero de pieza, del cual desea parar el proceso"));
		if (Piezas.isEmpty()) {
			JOptionPane.showMessageDialog(null,"La lista esta vacia");
			return false;
		}else {
			 for (Pieza elemento : Piezas)
			        if(elemento.getNumero_de_pieza()==piez) {
			        	JOptionPane.showMessageDialog(null, "Paro el proceso de la pieza: "+elemento.getNumero_de_pieza()); 
			        	PiezasPausadas.add(Piezas.get(piez));
			    		Piezas.remove(piez);
			    		
		}
		}
		
			return true;
	}*/
	/*public boolean ReanudarProceso(){
		JOptionPane.showMessageDialog(null, PiezasPausadas);
		int pie=Integer.parseInt(JOptionPane.showInputDialog("Ingrese el numero de pieza, del cual desea reanudar el proceso"));
		if (PiezasPausadas.isEmpty()) {
			JOptionPane.showMessageDialog(null,"La lista esta vacia");
			return false;
		}else {
			 for (Pieza elemento : PiezasPausadas)
			        if(elemento.getNumero_de_pieza()==pie) {
			        	System.out.println("Reanudo la pieza: "+elemento.getNumero_de_pieza()); 
			        	Piezas.add(PiezasPausadas.get(pie));
			        	PiezasPausadas.remove(pie);
		}
		}
			return true;
	}*/ 
	public boolean ReanudarProceso() {
	    JOptionPane.showMessageDialog(null, PiezasPausadas);
	    int pie = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el número de pieza que desea reanudar"));
	    
	    if (PiezasPausadas.isEmpty()) {
	        JOptionPane.showMessageDialog(null, "La lista está vacía");
	        return false;
	    } else {
	        boolean piezaEncontrada = false;
	        
	        for (Pieza elemento : PiezasPausadas) {
	            if (elemento.getNumero_de_pieza() == pie) {
	                piezaEncontrada = true;
	                JOptionPane.showMessageDialog(null, "Reanudando la pieza: " + elemento.getNumero_de_pieza()); 
	                Piezas.add(elemento);
	                PiezasPausadas.remove(elemento);
	                break;
	            }
	        }
	        
	        if (!piezaEncontrada) {
	        	JOptionPane.showMessageDialog(null, "No se encontró la pieza con el número especificado");
	            return false;
	        }
	    }
	    
	    return true;
	}
        
	public boolean BorrarProceso(){
		int piee=Integer.parseInt(JOptionPane.showInputDialog("Ingrese el numero de pieza para borrar el proceso"));
		if (Piezas.isEmpty()) {
			JOptionPane.showMessageDialog(null,"La lista esta vacia");
			return false;
		}else {
			 for (Pieza elemento : Piezas)
			        if(elemento.getNumero_de_pieza()==piee) {
			        	System.out.println("removio el elemento: "+elemento.getNumero_de_pieza()); 
			        	Piezas.remove(piee);
		}
			 return true;
		}
	}
	public String Lista() {
		if (Piezas.isEmpty()) {
			JOptionPane.showMessageDialog(null,"La lista esta vacia");
		}else {
		JOptionPane.showMessageDialog(null, Piezas);
		}
		return "";
		
	}

	}