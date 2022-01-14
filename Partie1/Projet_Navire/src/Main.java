import java.util.Scanner;


/**
 * Classe main qui permet à un utilisateur de configurer l'équipage d'un bateau de pirates.
 * 
 * @author Wissam Amarehoun et Enes Asam
 * @version 1.0
 */
public class Main {

	
	/**
	 * La méthode main qui permet à l'utilisateur de configurer l'équipage d'un bateau de pirates.
	 * 
	 * @param args les arguments de la méthode main
	 */
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int nbPirate;
		do {
			System.out.println("Entrez le nombre de pirate dans l'équipage : ");
			nbPirate = sc.nextInt();
		}while((nbPirate < 1) || (nbPirate > 26));
		
		EquipagePirate p = new EquipagePirate(nbPirate);
		
		p.creationButin();
		
		for(int i=65; i<p.nbPirate()+65;i++) {
			Pirate a = new Pirate(Character.toString(i));
			p.ajoutPirate(a);
		}
		
		p.creationListesRelationPirates();
		
		int choix, choix2;
		boolean sortMenu = true;
		
		do {
			System.out.println();
			menu();
			choix = sc.nextInt();
			
			switch(choix) {
			case 1 :
				p.ajouterRelationPirates();
				break;
			case 2 :
				p.ajouterPreferences();
				break;
			case 3 : 
				if(p.verifListePreferences()) {
					p.attributionObjet();
					p.afficheAttributionObjet();
					
					do {
						menu2();
						choix2 = sc.nextInt();
						switch(choix2) {
						case 1 :
							p.echangeObjet();
							p.afficheAttributionObjet();
							p.reinitialiserCout();
							break;
						case 2 :
							p.calculCout();
							p.afficheAttributionObjet();
							break;
						case 3 :
							p.afficheAttributionObjet();
							sortMenu = false;
							break;
						default :
							System.out.println("Le choix "+choix2+" n'est pas valide.");
					    }
					}while(sortMenu);
				}
				else {
					String nom = "";
					for(int i=0; i<nbPirate;i++) {
						if(p.getEquipage().get(i).getListePreferenceObjet().isEmpty()) {
							nom += p.getEquipage().get(i).getNom()+" ";
						}
					}
					System.out.println("Vous n'avez pas saisi de préférences pour "+splitNomPirate(nom));
				}
				break;
			default : 
				System.out.println("Le choix "+choix+" n'est pas valide.");
			}
		}while(sortMenu);
		sc.close();
	}
	
	private static String splitNomPirate(String s) {
		String texte = "";
		final String S = " ";
		String mots[] = s.split(S);
		if(mots.length == 1) {
			texte += "le pirate \""+mots[0]+"\".";
		}else if(mots.length == 2) {
			texte += "les pirates : \""+mots[0]+"\" et \""+mots[1]+"\".";
		}else {
			texte += "les pirates : \"";
			for(int i=0; i<mots.length-2;i++) {
				texte += mots[i];
				texte += "\", \"";
			}
			texte += mots[mots.length-2]+"\" et \"";
			texte += mots[mots.length-1]+"\".";
		}
		return texte;
	}
	
	private static void menu() {
		System.out.println("1) Ajouter une relation; ");
		System.out.println("2) Ajouter des préferences; ");
		System.out.println("3) Fin; ");
	}
	
	private static void menu2() {
		System.out.println("1) Échanger objets; ");
		System.out.println("2) Afficher coût; ");
		System.out.println("3) Fin; ");
	}

}