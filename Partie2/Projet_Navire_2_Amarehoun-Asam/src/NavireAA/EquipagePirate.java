package NavireAA;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;


/**
 * Classe qui correspond à un equipage de pirates et permet ainsi la manipulation de ce dernier.
 * 
 * 
 * @author Wissam Amarehoun et Enes Asam
 * @version 2.0
 */

public class EquipagePirate implements Cloneable{ // Implementation de la classe Cloneable qui va permettre le clonage de l'équipage.


	/**
	 * Attribut privé étant une liste de Pirate représentant l'équipage du navire.
	 * 
	 * @since 1.0
	 */
	private ArrayList<Pirate> equipage;  
	
	/**
	 * Attribut privé étant une liste d'Objet représentant le butin du navire.
	 * 
	 * @since 1.0
	 */
	private  ArrayList<Objet> butin; 
	
	/**
	 * Attribut privé de type int (entier) correspondant au cout de la solution de l'équipage.
	 * 
	 * @since 1.0
	 */
	private int cout;
	
	/**
	 * Attribut privé de type Scanner permettant l'interaction avec l'utilisateur.
	 * 
	 * @since 1.0
	 */
	private Scanner sc;      
	
	/**
	 * Attribut privé correspondant à une liste de Pirate représentant l'équipage sous forme bis
	 * (c'est à dire qu'elle contiendra les mêmes pirates en version cloné afin de pouvoir les manipuler sans modifier l'instance de base).
	 * 
	 * @since 2.0
	 */
	private ArrayList<Pirate> equipageBis; 
	
	/**
	 * Attribut privé de type int (entier) correspondant au cout de la solution de l'équipageBis.
	 * 
	 * @since 2.0
	 */
	private int coutBis;                    

	/**
	 * Le constructeur va permettre la construction d'un équipage de Pirate et une liste d'Objet vide.
	 * L'équipage ainsi que les objets vont être instanciés à l'aide d'un fichier contenant les pirates et les objets.
	 * Le cout et coutbis sont initialisés à 0 tant qu'il n'y a pas eu de résolution de cout.
	 * Le scanner est instancié directement ici afin qu'il soit utilisable dans les prochaines méthodes.
	 * 
	 * @since 2.0
	 * 
	 */
	public EquipagePirate() {
		equipage = new ArrayList<Pirate>();       
		butin = new ArrayList<Objet>();           
		cout = 0;                                 
		sc = new Scanner(System.in);              
		equipageBis = new ArrayList<Pirate>();    
		coutBis = 0;                                         
	}
	
	
	/**
	 * Méthode qui vérifie si dans le fichier, le nombre de pirates de d'objets sont égaux.
	 * 
	 * @return true et seulement si le nombre de pirates et le nombre d'objets sont différents.
	 * 
	 * @since 2.0
	 */
	public boolean verifEquipageObjet() {
		if(equipage.size()!=butin.size()) {
			if(equipage.size()>butin.size()) {
				System.out.println("Il y a plus de pirates que d'objets dans votre fichier.");
				return true;
			}else {
				System.out.println("Il y a plus d'objets que de pirates dans votre fichier.");
				return true;
			}
		}
		return false;
	}
	
	
	/**
	 * Méthode permettant de savoir si pour chaque pirate, sa liste de préférences d'objets possède le même nombre d'objets que dans le butin.
	 * En cas de retour true, un message s'affiche aussi.
	 * 
	 * @return true si et seulement si la liste de préférence d'au moins un pirate n'est pas de bonne taille.
	 * 
	 * @since 2.0
	 */
	public boolean verifListesPreferences() {
		boolean verif = false;
		for(Pirate p : equipage) {
			if(p.getListePreferenceObjet().size() != butin.size()) {
				System.out.println("La liste de préférences de \""+p.getNom()+"\" est mal déclarée.");
				verif = true;
			}
		}
		return verif;
	}


	/**
	 * Méthode retournant le nombre de pirate dans l'equipage.
	 * 
	 * @return la taille de la liste equipage.
	 * 
	 * @since 1.0
	 */
	public int nbPirate() {
		return equipage.size();
	}
	
	
	/**
	 * Méthode créant l'équipage à partir d'un fichier.
	 * Dans le cas où il y aurait une erreur dans la saisie ou l'ouverture du fichier, le programme s'arrête et un message s'affiche.
	 * 
	 * @param cheminFichier Le chemin permettant d'accéder au fichier dans la machine.
	 * 
	 * @throws IOException Si il y a une erreur avec le fichier.
	 * 
	 * @since 2.0
	 */
	public void creation(String cheminFichier) throws IOException{
		try {
			File f = new File(cheminFichier);
			FileReader fReader = new FileReader(f);
			BufferedReader bReader = new BufferedReader(fReader);
			String ligne = null;
			while ((ligne = bReader.readLine()) != null) {
				if(ligne.length() != 0) {
					if(ligne.charAt(ligne.length()-2) != ')') {
						System.out.println("Une des lignes de votre fichier n'a pas la bonne syntaxe.\nVérifiez les saisies dans votre fichier.");
						System.exit(1);
					}else if(ligne.charAt(ligne.length()-1) != '.') {
						System.out.println("Votre ligne ne finit pas par un point.\nVérifiez votre fichier.");
						System.exit(1);
					}else if (ligne.startsWith("pirate(")) {
						boolean verifPirate = true;
						String nomPirate = ligne.substring(7, ligne.length() - 2);
						Pirate p = new Pirate(nomPirate);
						for(Pirate t : equipage)
							if(t.getNom().equals(nomPirate))
								verifPirate = false;
						if(verifPirate) {
							equipage.add(p);
						}else {
							System.out.println("Dans votre fichier il y a plusieurs fois le même nom pour un pirate.\nVeuillez différencier leurs noms.");
							System.exit(1);
						}
					}else if (ligne.startsWith("objet(")) {
						boolean verifObjet = true;
						String nomObjet = ligne.substring(6, ligne.length() - 2);
						Objet o = new Objet(nomObjet);
						for(Objet t : butin)
							if(t.getNom().equals(nomObjet))
								verifObjet = false;
						if(verifObjet) {
							butin.add(o);
						}else {
							System.out.println("Dans votre fichier il y a plusieurs fois le même nom pour un objet.\nVeuillez différencier leurs noms.");
							System.exit(1);
						}
					}else if (ligne.startsWith("deteste(")) {
						boolean presence1 = true;
						boolean presence2 = true;
						String noms = ligne.substring(8, ligne.length() - 2);
						String[] nomP = noms.split(",");
						if(nomP.length != 2) {
							System.out.println("La ligne \"deteste\" est mal déclarée.\nVérifiez votre fichier.");
							System.exit(1);
						}
						String nomP1 = nomP[0];
						String nomP2 = nomP[1];
						for(Pirate p : equipage) {
							if(p.getNom().equals(nomP1))
								presence1 = false;
							if(p.getNom().equals(nomP2))
								presence2 = false;
						}
						if(nomP1.equals(nomP2)) {
							System.out.println("Vous avez mit 2 fois le même nom de pirate dans \"deteste\".\nVeuillez changer les noms des pirates");
							System.exit(1);
						}
						if(presence1 && presence2) {
							System.out.println("Les pirates \""+nomP1+"\" et \""+nomP2+"\" dans \"deteste\" ne sont pas dans l'équipage.\nVérifiez les saisies dans votre fichier.");
							System.exit(1);
						}
						if(presence1) {
							System.out.println("Le pirate \""+nomP1+"\" dans \"deteste\" n'est pas dans l'équipage.\nVérifiez les saisies dans votre fichier.");
							System.exit(1);
						}
						if(presence2) {
							System.out.println("Le pirate \""+nomP2+"\" dans \"deteste\" n'est pas dans l'équipage.\nVérifiez les saisies dans votre fichier.");
							System.exit(1);
						}
						ajouterRelationPirates(nomP1, nomP2);
					}else if (ligne.startsWith("preferences(")) {
						String mots = ligne.substring(12, ligne.length()-2);
						String[] lsMots = mots.split(",");
						if(lsMots.length-1 != butin.size()) {
							System.out.println("Une liste de préférences est mal déclarée.\nVérifiez votre fichier.");
							System.exit(1);
						}
						ajouterPreferences(lsMots);
					}else {
						System.out.println("Dans votre fichier il y a des lignes inutiles à l'équipage.\nVérifiez votre fichier et supprimez les lignes en question. ");
					}
				}
			}
			bReader.close();
			if(verifPreferences()) {
				System.out.println("Vérifiez les saisies des préférences dans votre fichier.");
				System.exit(1);
			}
		}catch (FileNotFoundException ffe) {
			System.out.println("Il y a une erreur au niveau de l'ouverture de votre fichier.\nVérifiez le chemin que vous avez mit.");
			System.exit(1);
		}catch (ArrayIndexOutOfBoundsException a) {
			System.out.println("Il y a une erreur au niveau du fichier.\nVérifiez la saisie des noms des pirates.");
			System.exit(1);
		}
	}
	
	
	/**
	 * Méthode vérifiant pour tous les pirates de l'équipage, si la liste de préférences d'objets est de la même taille que le nombre de pirates.
	 * 
	 * @return true si et seulement si la liste de préférences d'objets d'au moins un pirate n'est pas de bonne taille.
	 * 
	 * @since 2.0
	 */
	public boolean verifPreferences() {
		boolean verif = false;
		for(Pirate p : equipage) {
			if(p.getListePreferenceObjet().size() == 0) {
				verif = true;
				System.out.println("Vous n'avez pas saisi la liste de préférence du pirate \""+p.getNom()+"\".");
				continue;
			}
			if(p.getListePreferenceObjet().size() != equipage.size()) {
				verif = true;
				System.out.println("Vous avez mal saisi la liste de préférence du pirate \""+p.getNom()+"\".");
			}
		}	
		return verif;
	}
	
	
	/**
	 * Méthode qui affiche pour chaque pirate son objet reçu.
	 * Si aucune résolution est faite, un message s'affiche.
	 * 
	 * @since 2.0
	 */
	public void afficheAttributionObjet() {
		boolean c = true;
		for (Pirate a : this.equipage)
			if(a.possedeObjet() == true) {
				c = false;
				System.out.println(a.getNom()+" : "+a.getObjetRecu().getNom());
			}
		if(c) {
			System.out.println("Vous n'avez fait aucune resolution");
		}
	}
	
	
	/**
	 * Méthode qui permet d'attribuer des objets à chaque pirate (aléatoirement) en fonction de leurs listes de préférences d'objets
	 * Pour un pirate, si son objet préféré est déjà attribué, on lui attribut son 2ème préféré, si il est déjà attribué, etc ...
	 * 
	 * @since 2.0
	 */
	public void attributionObjetAleatoire() {
		for (Pirate x : equipage) {
			for (int i = 0; i < nbPirate(); i++) {
				if (!verifPossedeObjet(x.getListePreferenceObjet().get(i), equipage)) {
					x.attribuerObjet(x.getListePreferenceObjet().get(i));
					break;
				}
			}
		}
	}
	

	/**
	 * Méthode qui permet de savoir si un objet a déjà été attribué pour un equipage.
	 * 
	 * @param o L'objet dont on souhaite savoir s'il a déjà été attribué
	 * @param eq L'équipage pour lequel on souhaite vérifier cela
	 * 
	 * @return true si et seulement si l'objet a déjà été attribué
	 * 
	 * @since 2.0
	 */
	public boolean verifPossedeObjet(Objet o, ArrayList<Pirate> eq) {
		for (Pirate x : eq)
			if(x.possedeObjet() == true)
			    if (o.getNom().equals(x.getObjetRecu().getNom()))
				    return true;
		return false;
	}
	

	/**
	 * Méthode permettant pour chaque pirate de saisir sa liste de préférences d'objets à partir d'un tableau de chaînes de caractères.
	 * En cas de problème, le programme s'arrête et un message d'erreur s'affiche.
	 * 
	 * @param ls Le tableau de chaînes de caractères.
	 * 
	 * @since 2.0
	 */
	public void ajouterPreferences(String[] ls) {
		for (Pirate p : equipage)
			if (p.getNom().equals(ls[0]))
				for (int i=1; i<ls.length; i++)
					for (Objet o : butin)
						if (ls[i].equals(o.getNom()))
							p.ajoutPreference(o);
		
		for(int j=1; j<equipage.size(); j++) {
			if(!verifObjetButin(ls[j])) {
				System.out.println("Un objet des préférences n'est pas présent dans le butin.\nVeuillez verifier votre fichier.");
				System.exit(1);
			}
		}
		
		Arrays.sort(ls);
		for(int i=0; i<ls.length-1; i++) {
			if(ls[i].equals(ls[i+1])) {
				System.out.println("Dans vos liste de préférences, il y a plusieurs fois le même objet.\nVeuillez corriger votre fichier");
				System.exit(1);
			}
		}		
	}
	
	
	/**
	 * Méthode vérifiant si une chaîne de caractères est bien un nom d'objet dans le butin
	 * 
	 * @param nom La chaîne de caractères à vérifier
	 * 
	 * @return true si et seulement s'il existe un objet ayant le nom égal à la chaine de caractères.
	 * 
	 * @since 2.0
	 */
	public boolean verifObjetButin(String nom) {
		boolean verif = false;
		for(Objet o : butin) {
			if(nom.equals(o.getNom())) {
				verif = true;
				break;
			}
		}
		return verif;
	}


	/**
	 * Méthode permettant d'ajouter une relation "ne s'aiment pas" entre deux pirates.
	 * 
	 * @param p1 Le nom du premier pirate.
	 * @param p2 Le nom du second pirate.
	 * 
	 * @since 2.0
	 */
	public void ajouterRelationPirates(String p1, String p2) {
		for (Pirate x : equipage) {
			if (x.getNom().equals(p1)) {
				for (Pirate y : equipage) {
					if (y.getNom().equals(p2)) {
						x.ajoutRelation(y.getNom());
						y.ajoutRelation(x.getNom());
						break;
					}
				}
				break;
			}
		}
	}

	
	/**
	 * Méthode qui permet d'échanger les objets recu entre deux pirates, les noms des pirates devront 
	 * être entrés par l'utilisateur.
	 * Dans le cas ou l'utilisateur entre des noms de pirates qui ne sont pas dans l'équipage,
	 * un message d'erreur s'affiche.
	 * 
	 * @since 2.0
	 */
	public void echangeObjet() {
		boolean pirate1 = true;
		boolean pirate2 = true;
		try {
			System.out.println("Entrez le nom des deux pirates dont vous souhaitez échanger les objets (à séparer avec un espace !) : ");
			String texte = sc.nextLine();
			String pirates[] = texte.split(" ");
			for (Pirate x : equipage) {
			    if (x.getNom().equals(pirates[0])) {
			        pirate1 = false;
			        for (Pirate y : equipage) {
			            if (y.getNom().equals(pirates[1])) {
			                Objet temp = new Objet(x.getObjetRecu().getNom());
			                x.attribuerObjet(y.getObjetRecu());
			                y.attribuerObjet(temp);
			                break;
			            }
			        }
			        break;
			    }
			}
			for(Pirate y : equipage) {
				if(y.getNom().equals(pirates[1])) {
					pirate2 = false;
					break;
				}
			}
			if(pirate1 && pirate2) {
			    System.out.println("Les pirates ne sont pas dans l'équipage.");
			}else if(pirate1) {
			    System.out.println("Le pirate \""+pirates[0]+"\" n'est pas dans l'équipage.");
			}else if(pirate2) {
			    System.out.println("Le pirate \""+pirates[1]+"\" n'est pas dans l'équipage.");
			}
		}catch(ArrayIndexOutOfBoundsException aioobe) {
			System.out.println("Vous avez mal effectué la saisie, par conséquent aucun échange est fait.");
		}
	}
	

	/**
	 * Méthode permettant d'échanger les objets reçus entre deux pirates étant des clones.
	 * 
	 * @param p Le premier pirate.
	 * @param p2 Le second pirate.
	 * 
	 * @since 2.0
	 */
	public void echangeObjetClone(Pirate p, Pirate p2) {
		Objet temp = new Objet(p.getObjetRecu().getNom());
		p.attribuerObjet(p2.getObjetRecu());
		p2.attribuerObjet(temp);
	}


	/**
	 * Méthode qui calcule et affiche le cout pour la solution actuelle en réinitialisant le cout d'abord puis en regardant pour chaque pirate s'il est jaloux.
	 * 
	 * @return cout Le cout de la solution actuelle.
	 * 
	 * @since 2.0
	 */
	public int calculCout() {
		reinitialiserCout();
		for(Pirate x : equipage)
			if(x.estJaloux(equipage)) 
				cout += 1;
		return cout;
	}
	
	
	/**
	 * Méthode affichant le coût d'une solution.
	 * 
	 * @since 2.0
	 */
	public void afficheCout() {
		System.out.println("Le coût de la solution actuelle de l'équipage actuelle vaut : "+calculCout()+"."); 
	}
	
	
	/**
	 * Méthode qui calcule et affiche le coutBis pour une solution clone en réinitialisant le coutBis d'abord puis en regardant pour chaque pirate de l'equipageBis s'il est jaloux.
	 * 
	 * @return coutBis Le cout de la solution actuelle dans l'équipage clone..
	 * 
	 * @since 2.0
	 */
	public int calculCoutBis() {
		reinitialiserCoutBis();
		for(Pirate x : equipageBis)
			if(x.estJaloux(equipageBis)) 
				coutBis += 1;
		return coutBis;
	}

	
	/**
	 * Méthode qui permet de reinitialiser la valeur de l'attribut cout à 0.
	 * 
	 * @since 1.0
	 */
	public void reinitialiserCout() {
		cout = 0;
	}
	
	
	/**
	 * Méthode qui permet de reinitialiser la valeur de l'attribut coutBis à 0.
	 * 
	 * @since 2.0
	 */
	public void reinitialiserCoutBis() {
		coutBis = 0;
	}
	
	
	/**
	 * Méthode permettant de cloner l'équipage afin de pouvoir manipuler chaque pirate sans modifier l'instance de base.
	 * 
	 * @throws CloneNotSupportedException Si le clonage à échoué.
	 * 
	 * @since 2.0
	 */
	public void cloneEquipage() throws CloneNotSupportedException {
		try {
			ArrayList<Pirate> nv = new ArrayList<Pirate>();
			equipageBis = nv;
			for(Pirate p : equipage) {
				Pirate clone = null;
				clone = (Pirate) p.clone();
				equipageBis.add(clone);
			}
		}catch(CloneNotSupportedException cnse) {
			System.out.println("Il y a un problème au niveau du clonage de pirate.\nVeuillez réessayer.");
		}
	}
	
	
	/**
	 * Méthode permettant d'échanger la solution d'attribution d'objet si le coût de la solution bis est inférieur à celui de la solution de base.
	 * 
	 * @throws CloneNotSupportedException Si le clonage à échoué.
	 * 
	 * @since 2.0
	 */
	public void echangeSolution() throws CloneNotSupportedException {
		try {
			if(calculCout() > calculCoutBis()) {
				equipage = equipageBis;
				cloneEquipage();
			}
		}catch(CloneNotSupportedException cnse) {
			System.out.println("Il y a un problème au niveau du clonage de pirate.\nVeuillez réessayer.");
		}
	}
	
	
	/**
	 * Méthode permettant de calculer la meilleure solution (avec un coût minimal) en faisant plusieurs fois des échanges entre des pirates.
	 * 
	 * @param k Le nombre de fois où on échangera les objets entre deux pirates.
	 * 
	 * @throws CloneNotSupportedException Si le clonage à échoué.
	 * 
	 * @since 2.0
	 */
	public void calculMeilleureSolution(int k) throws CloneNotSupportedException {
		try {
			int i;
			attributionObjetAleatoire();
			cloneEquipage();
			System.out.println("Calcul d'une solution en cours ...\n");
			for(i=0; i<k; i++) {
				if(calculCout() == 0) {
					break;
				}else {
					boolean ok = true;
					boolean ok2 = true;
					Random random = new Random();
					int alea = 0;
					int alea2 = 0;
					do {
						alea = random.nextInt(equipageBis.size());
						if(!equipageBis.get(alea).getListeRelationPirates().isEmpty()) {
							ok = false;
							do {
								alea2 = random.nextInt(equipageBis.get(alea).getListeRelationPirates().size());
								equipageBis.get(alea).getListeRelationPirates().get(alea2);
								ok2 = false;
							}while(ok2);
						}
					}while(ok);
					for(Pirate p : equipageBis) {
						if(equipageBis.get(alea).getListeRelationPirates().get(alea2).equals(p.getNom())) {
							echangeObjetClone(equipageBis.get(alea), p);
						}
					}
					if(calculCoutBis() < calculCout())
						echangeSolution();
				}
				
			}
			System.out.println("La meilleure solution obtenu à partir de l'algorithme de résolution est : ");
			afficheAttributionObjet();
			System.out.println();
			afficheCout();
			if(calculCout() == 0) {
				System.out.println("La solution ayant pour valeur 0 a été trouvée au bout de k="+i);
			}
			System.out.println();
		}catch(CloneNotSupportedException cnse) {
			System.out.println("Il y a un problème au niveau du clonage de pirate.\nVeuillez réessayer.");
		}
	}
	
	
	/**
	 * Méthode permettant d'enregistrer la solution dans un fichier en créant un fichier puis en écrivant dedans le coût et l'attribution d'objets.
	 * 
	 * @throws IOException Si il y a une erreur avec le fichier.
	 * 
	 * @since 2.0
	 */
	public void enregistrerFichier() throws IOException {
		try {
			boolean condition = false;
			String cheminRepertoire;
			String nomFichier;
			do {
				System.out.println("Entrez le chemin du répertoire dans lequel vous souhaitez sauvegarder votre fichier : ");
				cheminRepertoire = sc.nextLine();
				System.out.println("Entrez le nom du fichier : ");
				nomFichier = sc.nextLine();
				File f = new File(cheminRepertoire + nomFichier + ".txt");
				if(f.createNewFile()) {
					condition = false;
					System.out.println("Fichier créé et enregistré dans le repertoire ayant le chemin suivant : "+cheminRepertoire);
					System.out.println("Nom du fichier :"+nomFichier);
					FileWriter fw = new FileWriter(f.getAbsoluteFile());
					BufferedWriter bw = new BufferedWriter(fw);   
					bw.write("Cout="+calculCout()+"\n");
					for(Pirate p:equipage) {
						bw.write(p.getNom()+ ":"+ p.getObjetRecu().getNom()+"\n");
					}
					bw.close();
				}else {
					condition = true;
					System.out.println("Fichier déjà existant");
				}
			}while(condition);
		}catch(IOException ioe) {
			System.out.println("Erreur rencontrée avec le chemin du répertoire.\nRefaites votre sauvegarde.");
		}
	}
}