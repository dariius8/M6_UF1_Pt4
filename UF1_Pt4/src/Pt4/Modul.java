package Pt4;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class Modul implements Serializable {
	// atributs
	private String nom;
	private String profe;
	private ArrayList ufs;

	// metodes que demana el enunciat
	public void printUFs() {
		if (!ufs.isEmpty()) {
			for (int i = 0; i < ufs.size(); i++)
				System.out.println(ufs.get(i));
		} else
			System.out.println("No hi ha cap uf.");
	}

	public void printModul() {
		System.out.println("Modul: " + nom);
		System.out.println("Professor: " + profe);
		System.out.println("Ufs:");
		printUFs();
	}

	public void setModul(String id) {
		int i = 0;
		while (i != 6) {
			Scanner lector = new Scanner(System.in);
			System.out.println("\nQue vols fer amb el modul?");
			System.out.println("   1. Canviar el nom de id");
			System.out.println("   2. Canviar de professor");
			System.out.println("   3. Afegir UFs a id");
			System.out.println("   4. Canviar una UF de id");
			System.out.println("   5. Treure una UF");
			System.out.println("   6. TORNAR");
			System.out.print("Escull una opcio: ");
			if (lector.hasNextInt()) {
				i = lector.nextInt();
				String var = "";
				if (i > 0 && i < 7) {
					switch (i) {
					case 1:
						System.out.println("Escriu el nou nom del modul:");
						var = lector.next();
						setNom(var);
						break;
					case 2:
						System.out.println("Escriu el nou professor:");
						var = lector.next();
						setProfe(var);
						break;
					case 3:
						omplirUFs(lector);
						break;
					case 4:
						System.out.println("Escriu la uf que vols canviar:");
						int ufCanviar = lector.nextInt();
						System.out.println("Escriu el nou nom de la uf:");
						var = lector.next();
						if (!ufs.isEmpty())
							ufs.set(ufCanviar - 1, var);
						else
							System.out.println("No hi ha cap uf.");
						break;
					case 5:
						System.out.println("Escriu la uf que vols treure:");
						int ufTreure = lector.nextInt();
						ufs.remove(ufTreure - 1);
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
	public Modul() {
		this.nom = nom;
		this.profe = profe;
		this.ufs = new ArrayList<>();
	}

	// getters y setters
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public void setProfe(String profe) {
		this.profe = profe;
	}

	public ArrayList getUfs() {
		return ufs;
	}

	public void omplirUFs(Scanner lector) {
		System.out.println("Escriu els noms de les ufs separades per comes:");
		String uf = lector.next();
		String[] parts = uf.split(",");
		for (int i = 0; i < parts.length; i++)
			ufs.add(parts[i]);
	}

	public void omplirModul(Scanner lector) {
		System.out.println("Escriu el nom del modul:");
		setNom(lector.next());
		System.out.println("Escriu el nom del profesor per al modul:");
		setProfe(lector.next());
		omplirUFs(lector);
	}
}
