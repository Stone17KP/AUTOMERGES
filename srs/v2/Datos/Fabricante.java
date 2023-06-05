package Datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.LinkedList;
import javax.swing.JOptionPane;

public class Fabricante extends Persona{
	private int id_frabicante;
	private String claveF;
	
	Conexion con = new Conexion();
	
	Connection conexion = con.conectar();
	
	PreparedStatement stmt;
	
	public Fabricante(int dni, String nombre, String apellido, String direccion, int telefono, int id_frabicante,
			String claveF) {
		super(dni, nombre, apellido, direccion, telefono);
		this.id_frabicante = id_frabicante;
		this.claveF = claveF;
	}

	public int getId_frabicante() {
		return id_frabicante;
	}

	public void setId_frabicante(int id_frabicante) {
		this.id_frabicante = id_frabicante;
	}

	public String getClaveF() {
		return claveF;
	}

	public void setClaveF(String claveF) {
		this.claveF = claveF;
	}

	@Override
	public String toString() {
		return "Fabricante [id_frabicante=" + id_frabicante + ", claveF=" + claveF + "]";
	}
	public static void FabricarPiezaa() {
		Pieza p=new Pieza();
		String [] opciones={"Iniciar Proceso","Parar Proceso","Reanudar Proceso","Borrar Proceso","Ver Procesos","Salir"};
		String opcion="";
		do {
		       opcion = (String)JOptionPane.showInputDialog(null, "Opciones del Fabricante","",JOptionPane.DEFAULT_OPTION,null, opciones,opciones[0]);
		switch (opcion) {
		case "Iniciar Proceso":
			p.IniciarProceso();
			break;	
		case "Parar Proceso":
			p.PararProceso();
			break;
		case "Reanudar Proceso":
			p.ReanudarProceso();
			break;
		case "Borrar Proceso":
			p.BorrarProceso();
			break;
		case "Ver Procesos":
			p.Lista();
			break;
		case "Salir":
			break;
		default:
			break;
		}
	} while(!opcion.equals("Salir" ));
	}
	
	public static void FabricarPieza() {
		int sele;
		Pieza p=new Pieza();
		JOptionPane.showMessageDialog(null, "Bienvenido fabricante...");
		do {
			int selec=Integer.parseInt(JOptionPane.showInputDialog("Elija una de estas opciones de fabricacion \n1.Iniciar Proceso de fabricacion \n2.Parar Proceso \n3.Reanudar Proceso\n4.Borrar proceso \n5.Ver lista de procesos \nOtro numero para Salir"));
			
			switch (selec) {
			case 1:
				p.IniciarProceso();
				break;	
			case 2:
				p.PararProceso();
				break;
			case 3:
				p.ReanudarProceso();
				break;
			case 4:
				p.BorrarProceso();
				break;
			case 5:
				p.Lista();
				break;
			default:
				break;
			}
			sele = Integer.parseInt(JOptionPane.showInputDialog("Ingrese \n1Si quiere volver al menu \nOtro numero, salir"));
		}while(sele==1);
	}

	public boolean guardarPieza() {
		
		String sql ="INSERT INTO `persona`(`dni`, `nombre`, `apellido`) VALUES (?,?,?)";
		
		try {
			
			stmt = conexion.prepareStatement(sql);
			//stmt.setString(1, this.getDni());
			//stmt.setString(2, this.getNombre());
			//stmt.setString(3, this.getApellido());
			stmt.executeUpdate();
			return true;
			
		}catch(Exception excepcion){
			System.out.println(excepcion.getMessage());
			return false;
		}
	}
}
