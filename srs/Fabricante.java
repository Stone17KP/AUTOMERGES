package automerges;

import java.util.LinkedList;

import javax.swing.JOptionPane;

public class Fabricante {
	private int id_frabicante;
	private String claveF;
	
	public Fabricante(int id_frabicante, String claveF) {
		super();
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
	
}
