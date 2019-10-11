package Pt4;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Scanner;

public class Pt4 implements Serializable {

	private static final File fichero = new File("..\\UF1_Pt4\\src\\Pt4\\cursos.dat");
	private static final Scanner lector = new Scanner(System.in);
	private HashMap cursos;

	// main
	public static void main(String[] args) {
		menu();
	}

	// constructor de la classe (buit)
	public Pt4() {
		this.cursos = new HashMap<>();
	}

	// metode amb el menu
	public static void menu() {
		Pt4 pt = new Pt4();
		Curs c = new Curs();
		pt.dadesOut(fichero);
		int i = 0;
		while (i != 5) {
			System.out.println("\nPROPOSTA DE RESOLUCIO PT2 (STREAMS D'OBJECTES)");
			System.out.println("===============================================");
			System.out.println("   1. AFEGIR UN NOU CURS");
			System.out.println("   2. MODIFICAR UN CURS");
			System.out.println("   3. MOSTRAR DADES CURS");
			System.out.println("   4. ELIMINAR UN CURS");
			System.out.println("   5. GUARDAR I SORTIR");
			System.out.print("Escull una opcio: ");
			if (lector.hasNextInt()) {
				i = lector.nextInt();
				if (i > 0 && i < 6) {
					switch (i) {
					case 1:
						afegirCurs(pt, c);
						break;
					case 2:
						modificarCurs(pt, c);
						break;
					case 3:
						mostrarCurs(pt, c);
						break;
					case 4:
						eliminarCurs(pt, c);
						break;
					default:
						pt.dadesIn(fichero);
						System.out.println("Adeu!");
						break;
					}
				} else
					System.out.println("Error! Valor incorrecte.");
			} else
				System.out.println("Error! Ha de ser numeros.");
		}
	}

	// metode per guardar les dades
	public void dadesIn(File ruta) {
		try {
			FileOutputStream fichero = new FileOutputStream(ruta);
			ObjectOutputStream salida = new ObjectOutputStream(fichero);
			salida.writeObject(cursos);
			System.out.println("\nDades guardades correctament.");
			salida.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// metode per carregar les dades
	public void dadesOut(File ruta) {
		cursos.clear();
		try {
			FileInputStream fichero = new FileInputStream(ruta);
			ObjectInputStream entrada = new ObjectInputStream(fichero);
			cursos = (HashMap) entrada.readObject();
			System.out.println("Dades carregades correctament.");
			entrada.close();
		} catch (Exception e) {
			System.out.println("No hi ha cap fitxer amb dades encara.");
		}
	}

	// metode per afegir un curs
	public static void afegirCurs(Pt4 pt, Curs c) {
		String id = c.omplirCurs(lector);
		System.out.println("\nCurs afegit amb id " + id);
		pt.cursos.put(id, c);
	}

	// metode per modificar un curs
	public static void modificarCurs(Pt4 pt, Curs c) {
		System.out.println("\nEscriu el id del curs a modificar:");
		String id = lector.next();
		if (pt.cursos.containsKey(id)) {
			c = (Curs) pt.cursos.get(id);
			c.setCurs(id);
		} else
			System.out.println("\nNo existeix cap curs amb id " + id);
	}

	// metode per mostrar un curs
	public static void mostrarCurs(Pt4 pt, Curs c) {
		System.out.println("\nEscriu el id del curs a mostrar:");
		String id = lector.next();
		if (pt.cursos.containsKey(id)) {
			System.out.println("\n---Mostrant les dades del curs amb id " + id + "---");
			c = (Curs) pt.cursos.get(id);
			c.printCurs();
		} else
			System.out.println("\nNo existeix cap curs amb id " + id);
	}

	// metode per eliminar un curs
	public static void eliminarCurs(Pt4 pt, Curs c) {
		System.out.println("\nEscriu el id del curs a eliminar:");
		String id = lector.next();
		if (pt.cursos.containsKey(id)) {
			pt.cursos.remove(id);
			System.out.println("Curs eliminat amb id " + id);
		} else
			System.out.println("\nNo existeix cap curs amb id " + id);
	}
}
