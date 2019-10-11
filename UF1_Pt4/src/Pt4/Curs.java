package Pt4;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

public class Curs implements Serializable {
	// atributs
	private String tutor;
	private ArrayList alumnes;
	private HashMap moduls;

	// metodes que demana el enunciat
	public Modul getModul(String id) {
		Modul m = new Modul();
		m = (Modul) moduls.get(id);
		return m;
	}

	public void printCurs() {
		System.out.println("Tutor: " + this.getTutor());
		for (int i = 0; i < this.alumnes.size(); i++)
			System.out.println("Alumne " + (i + 1) + ": " + this.alumnes.get(i));
		Iterator it = this.moduls.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry me = (Map.Entry) it.next();
			Modul m = new Modul();
			m = (Modul) me.getValue();
			System.out.println("Modul amb id " + me.getKey() + ": " + m.getNom());
			m.getUfs();
		}
	}

	public void setCurs(String id) {
		int i = 0;
		while (i != 7) {
			Scanner lector = new Scanner(System.in);
			System.out.println("\nQue vols fer amb el curs?");
			System.out.println("   1. Canviar de tutor");
			System.out.println("   2. Afegir alumnes a " + id);
			System.out.println("   3. Treure alumnes de " + id);
			System.out.println("   4. Afegir un nou modul a " + id);
			System.out.println("   5. Modificar dades d'un modul");
			System.out.println("   6. Eliminar un modul a " + id);
			System.out.println("   7. TORNAR");
			System.out.print("Escull una opcio: ");
			if (lector.hasNextInt()) {
				i = lector.nextInt();
				String var = "";
				if (i > 0 && i < 8) {
					switch (i) {
					case 1:
						System.out.println("\nEscriu el nou tutor:");
						var = lector.next();
						setTutor(var);
						break;
					case 2:
						System.out.println("\nEscriu els noms dels alumnes separats per comes:");
						String alumne = lector.next();
						String[] parts = alumne.split(",");
						for (int j = 0; j < parts.length; j++)
							alumnes.add(parts[j]);
						break;
					case 3:
						System.out.println("\nEscriu el numero de l'alumne que vols eliminar:");
						int n = lector.nextInt();
						alumnes.remove(n - 1);
						break;
					case 4:
						System.out.println("\nEscriu el id del modul que vols afegir:");
						id = lector.next();
						Modul m = new Modul();
						m.omplirModul(lector);
						moduls.put(id, m);
						break;
					case 5:
						System.out.println("\nEscriu el id del modul que vols modificar:");
						id = lector.next();
						Modul m2 = new Modul();
						if (moduls.containsKey(id)) {
							m2 = (Modul) moduls.get(id);
							m2.setModul(id);
						}
						break;
					case 6:
						System.out.println("\nEscriu el id del modul que vols eliminar:");
						id = lector.next();
						moduls.remove(id);
						break;
					default:
						break;
					}
				} else {
					System.out.println("Error! Valor incorrecte.");
				}
			} else {
				System.out.println("Error! Ha de ser numeros.");
			}
		}
	}

	// constructor de la classe (buit)
	public Curs() {
		this.tutor = "";
		this.alumnes = new ArrayList<>();
		this.moduls = new HashMap<>();
	}

	// getters y setters (tutor)
	public String getTutor() {
		return tutor;
	}

	public void setTutor(String tutor) {
		this.tutor = tutor;
	}

	public String omplirCurs(Scanner lector) {
		System.out.println("\nEscriu el id del curs:");
		String idCurs = lector.next();
		System.out.println("Escriu el nom del tutor per al curs:");
		tutor = lector.next();
		System.out.println("Escriu els noms dels alumnes separats per comes:");
		String alumne = lector.next();
		String[] parts = alumne.split(",");
		for (int i = 0; i < parts.length; i++)
			alumnes.add(parts[i]);
		System.out.println("Escriu el id del modul:");
		String idModul = lector.next();
		Modul m = new Modul();
		m.omplirModul(lector);
		moduls.put(idModul, m);
		return idCurs;
	}
}
