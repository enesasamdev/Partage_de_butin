package NavireAA;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;


/**
 * Classe main qui permet à un utilisateur de configurer l'équipage d'un bateau de pirates de manière aléatoire et manuelle.
 * 
 * @author Wissam Amarehoun et Enes Asam
 * 
 * @version 2.0
 * 
 * @since 2.0
 */
public class MainNavire {

	
	/**
	 * La méthode main qui permet à l'utilisateur de configurer l'équipage d'un bateau de pirates de manière aléatoire ou manuelle.
	 * 
	 * @param args les arguments de la méthode main qui doit correspondre au chemin permettant d'accéder au fichier de l'équipage.
	 * 
	 * @throws CloneNotSupportedException Si le clonage à échoué
	 * @throws IOException Si il y a une erreur avec le fichier.
	 * 
	 * @since 2.0
	 */
	public static void main(String[] args) throws IOException, CloneNotSupportedException {
		int choix, choix2;
		EquipagePirate e = new EquipagePirate();
		try {
		    e.creation(args[0]);
		}catch(ArrayIndexOutOfBoundsException aaa) {
			System.out.println("Vous n'avez pas entré de chemin pour le fichier.");
			System.exit(1);
		}
		
	    if(e.verifEquipageObjet())
	    	System.exit(1);
		
		Scanner sc = new Scanner(System.in);

		boolean sortMenu = true;
		boolean resolution = false;
		boolean resolutionAuto = false;
		boolean sortMenu2 = true;
		try {
		do {
			System.out.println();
			menu();
			System.out.print("\nQue voulez-vous faire ? ");
			choix = sc.nextInt();
			System.out.println();
			switch (choix) {
			case 1:
				resolutionAuto = true;
				e.calculMeilleureSolution(5000000);
				break;
			case 2:
				resolution = true;
				e.attributionObjetAleatoire();
            	System.out.println("L'attribution d'objets aux pirates actuelle est la suivante : ");
				e.afficheAttributionObjet();
				System.out.println();
				do {
	                if(!resolutionAuto) {
						menuResolutionManuelle();
		                choix2 = sc.nextInt();
					    switch(choix2) {
					    case 1 :
						    e.echangeObjet();
						    e.reinitialiserCout();
						    System.out.println("L'attribution d'objets aux pirates est la suivante : ");
						    e.afficheAttributionObjet();
						    System.out.println();
						    break;
						case 2 :
							e.afficheCout();
						    System.out.println();
						    break;
						case 3 :
						    e.afficheAttributionObjet();
						    System.out.println();
						    sortMenu2 = false;
						    break;
						default :
						    System.out.println("Le choix "+choix2+" n'est pas valide.");
				        }
					}else {
						System.out.println();
						menuResolutionManuelle();
		                choix2 = sc.nextInt();
					    switch(choix2) {
					    case 1 :
						    e.echangeObjet();
						    e.reinitialiserCout();
						    System.out.println("Après l'échange, l'attribution d'objets aux pirates est la suivante : ");
						    e.afficheAttributionObjet();
						    System.out.println();
						    break;
						case 2 :
						    e.afficheCout();
						    System.out.println();
						    break;
						case 3 :
						    e.afficheAttributionObjet();
						    System.out.println();
						    sortMenu2 = false;
						    break;
						default :
						    System.out.println("Le choix "+choix2+" n'est pas valide.");
				        }
					}
				}while(sortMenu2);
				break;
			case 3:
				if(resolution || resolutionAuto) {
					e.enregistrerFichier();
				}else {
					System.out.println("Vous avez réaliser aucune \"resolution de cout\".");
					break;
				}
				break;
			case 4:
				sortMenu = false;
				break;
			default:
				System.out.println("Le choix " + choix + " n'est pas valide.");
			}
		} while (sortMenu);
		}catch(InputMismatchException e1) {
			System.out.println("Erreur - Format d'entrée non conforme.\nVous devez entrer un chiffre entier compris entre 1 et 4 (inclus).");
		}catch(java.lang.NullPointerException e2) {
			System.out.println("Erreur de sauvegarde : Fichier créé vide.\nIl y a eu un problème dans la résolution que vous avez choisi");
		}
		sc.close();
	}

	
	/**
	 * Méthode permettant d'afficher le premier menu qui fera face à l'utilisateur.
	 * 
	 * @since 2.0
	 */
	private static void menu() {
		System.out.println("1) Résolution automatique; ");
		System.out.println("2) Résolution manuelle; ");
		System.out.println("3) Sauvegarde; ");
		System.out.println("4) Fin; ");
	}

	
	/**
	 * Méthode qui affiche le menu permettant à l'utilisateur de résoudre le problème manuellement.
	 * 
	 * @since 1.0
	 */
	private static void menuResolutionManuelle() {
		System.out.println("1) Échanger objets; ");
		System.out.println("2) Afficher coût; ");
		System.out.println("3) Fin; ");
	}

}