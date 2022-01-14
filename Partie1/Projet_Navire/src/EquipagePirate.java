import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


/**
 * Classe qui correspond à un equipage de pirates et permet ainsi la manipulation d'un équipage
 * de pirate.
 * 
 * @author Wissam Amarehoun et Enes Asam
 * @version 1.0
 *
 */
public class EquipagePirate {
	private ArrayList<Pirate> equipage;
	private ArrayList<Objet> butin;
	private int nbPirate;
	private int cout;
	private Scanner sc;
	
	public EquipagePirate(int n) {
		nbPirate = n;
		equipage = new ArrayList<Pirate>();
		butin = new ArrayList<Objet>();
		cout = 0;
		sc = new Scanner(System.in);
	}
	
	
	/**
	 * Méthode qui retourne le nombre de pirate dans l'equipage.
	 * 
	 * @return l'attribut nbPirate
	 */
	public int nbPirate() {
		return nbPirate;
	}

	
	/**
	 * Méthode permettant de récuperer l'attribut cout étant le cout d'une solution.
	 * 
	 * @return cout L'attribut cout
	 */
	public int getCout() {
		return cout;
	}
	
	
	/**
	 * Méthode qui permet d'ajouter un pirate dans la liste equipage.
	 * 
	 * @param p Le pirate que l'on souhaite ajouter dans l'équipage
	 */
	public void ajoutPirate(Pirate p) {
		equipage.add(p);
	}
	
	
	/**
	 * Méthode qui remplit la liste butin de tous les objets en fonctions du nombre de pirate.
	 */
	public void creationButin() {
		for(int i=1; i<=nbPirate;i++) {
			Objet o = new Objet(i);
			butin.add(o);
		}
	}
	
	
	/**
	 * Méthode qui affiche tous les objets de la liste butin.
	 */
	public void afficheButin() {
		for(Objet o : butin)
			System.out.println("o"+o.getNumero());
	}
	
	
	/**
	 * Méthode permettant de récuperer l'attribut butin.
	 * 
	 * @return butin La liste des objets
	 */
	public ArrayList<Objet> getButin(){
		return butin;
	}
	
	
	/**
	 * Méthode qui affiche l'ensemble des pirates de la liste equipage.
	 */
	public void afficheEquipage() {
		for(Pirate p : equipage)
			System.out.println(p.getNom());
	}
	
	
	/**
	 * Méthode permettant de récuperer l'attribut equipage.
	 * 
	 * @return equipage La liste des pirates
	 */
	public ArrayList<Pirate> getEquipage(){
		return equipage;
	}
	
	
	/**
	 * Méthode qui affiche pour chaque pirate son objet reçu.
	 */
	public void afficheAttributionObjet() {
		for(Pirate a : this.equipage)
			System.out.println(a.getNom()+" : o"+a.getObjetRecu().getNumero());
	}
	
	
	/**
	 * Méthode qui permet d'attribuer des objets à chaque pirates en fonction de leurs listes de préférences d'objets
	 * Pour un pirate, si son objet préféré est déjà attribué, on lui attribut son 2ème préféré, si il est déjà attribué, etc ...
	 */
	public void attributionObjet() {
		for(Pirate x : equipage) {
			for(int i = 0; i<nbPirate;i++) {
				if(! verifPossedeObjet(x.getListePreferenceObjet().get(i))) {
					x.attribuerObjet(x.getListePreferenceObjet().get(i));
					break;
			    }
			}
		}
	}
	
	
	/**
	 * Méthode qui permet de savoir si un objet a déjà été attribué.
	 * 
	 * @param o L'objet dont on souhaite savoir s'il a déjà été attribué
	 * @return true si et seulement si l'objet a déjà été attribué
	 */
	public boolean verifPossedeObjet(Objet o) {
		for(Pirate x : equipage)
			if(x.getObjetRecu().getNumero() == o.getNumero())
				return true;
		return false;
	}
	
	
	/**
	 * Méthode qui intéragit avec l'utilisateur dans le but de créer la liste des préférences d'un pirate.
	 * Elle vérifie aussi la saisie de l'utilisateur, dans le cas où cette dernière serai mal faite, 
	 * un message s'affichera et la demande ne sera pas prise en compte
	 */
	public void ajouterPreferences() {
		boolean pirate = true;
		boolean doublon = false;
		boolean nbSaisie = false;
		int j = 0;
		System.out.println("Entrez le nom du pirate suivi de la liste de ses préférences (à séparer avec au moins un espace !) : ");
		String texte = sc.nextLine();
		final String SEPARATEUR = " ";
		String mots[] = texte.split(SEPARATEUR);
		for(Pirate p : equipage){
			if(p.getNom().contains(mots[0])){
				pirate = false;
				if(p.getListePreferenceObjet().size() != 0) {
					for(int i=1; i<mots.length; i++) {
						int a = Integer.parseInt(mots[i]);
						for(Objet o : butin) {
							if(a == o.getNumero()) {
								p.ajoutPreference2(j, o);
								j++;
							}
						}
					}
					p.afficherPreference();
				}else {
					for(int i=1; i<mots.length; i++) {
						int a = Integer.parseInt(mots[i]);
						for(Objet o : butin) {
							if(a == o.getNumero()) {
								p.ajoutPreference(o);
							}
						}
					}
					p.afficherPreference();
				}
			}
		}
		
		if(pirate)
			System.out.println("Le pirate \""+mots[0]+"\" n'est pas dans l'équipage.");
		
		int[] temp = new int[mots.length-1];
		for(int i=0; i<temp.length; i++)
			temp[i] = Integer.parseInt(mots[i+1]);
		Arrays.sort(temp);
		
		for(int i=0; i<temp.length-1; i++)
			if(temp[i] == temp[i+1])
				doublon = true;
		
		if(doublon)
			System.out.println("Vous avez saisi plusieurs fois le même objet.");
		
		if(temp.length > butin.size()) {
			System.out.println("Vous avez saisi trop d'objets.");
			nbSaisie = true;
		}
		
		if(temp.length < butin.size()) {
			System.out.println("Vous avez pas assez saisi d'objets.");
			nbSaisie = true;
		}
		
		if(nbSaisie || doublon) {
			for(Pirate p : equipage)
			    if(p.getNom().contains(mots[0]))
			    	p.setListePreferenceObjet();
			System.out.println("Par conséquent votre demande n'est pas prise en compte.");
		}
	}
	
	
	/**
	 * Méthode qui intéragit avec l'utilisateur afin d'ajouter une relation "ne s'aiment pas" entre deux pirates.
	 * Dans le cas où une relation est déjà ajoutée entre les deux pirates, un message s'affiche.
	 * Dans le cas où le nom de un ou deux pirates seraient pas présents dans l'équipage, un message s'affiche.
	 */
	public void ajouterRelationPirates() {
		boolean pirate1 = true;
		boolean pirate2 = true;
		System.out.println("Entrez le nom des deux pirates pour lesquels vous souhaitez ajouter une relation 'ne s'aiment pas' : ");
		String pirates = sc.nextLine();
		String pirate[] = pirates.split(" ");
		for(Pirate x : equipage) {
			if(x.getNom().contains(pirate[0])) {
				pirate1 = false;
				for(Pirate y : equipage) {
					if(y.getNom().contains(pirate[1])) {
						if(x.verifRelation(y)) {
							System.out.println("Une relation \"ne s'aiment pas\" entre les pirates \""+x.getNom()+"\" et \""+y.getNom()+"\" existe déjà.");
						}else {
							x.ajoutRelation(y.getNom());
							y.ajoutRelation(x.getNom());
							System.out.println("Une relation \"ne s'aiment pas\" entre les pirates \""+x.getNom()+"\" et \""+y.getNom()+"\" a bien été ajoutée.");
						}
						break;
					}
				}
				break;
			}
		}
		
		for(Pirate y : equipage) {
			if(y.getNom().contains(pirate[1])) {
				pirate2 = false;
				break;
			}
		}

		if(pirate1 && pirate2) {
			System.out.println("Les pirates ne sont pas dans l'équipage.");
		}else if(pirate1) {
			System.out.println("Le pirate \""+pirate[0]+"\" n'est pas dans l'équipage.");
		}else if(pirate2) {
			System.out.println("Le pirate \""+pirate[1]+"\" n'est pas dans l'équipage.");
		}
	}
	
	
	
	/**
	 * Méthode permettant de savoir si pour chaque pirate, sa liste de préférences d'objets n'est pas vide
	 * 
	 * @return true si et seulement si la liste de préférence d'objets d'un pirate est vide
	 */
	public boolean verifListePreferences() {
		boolean c = true;
		for(int i=0; i<nbPirate;i++)
			if(equipage.get(i).getListePreferenceObjet().isEmpty())
				c = false;
		return c;
	}
	
	
	/**
	 * Méthode qui permet d'échanger les objets recu entre deux pirates, les noms des pirates devront 
	 * être entrés par l'utilisateur.
	 * Dans le cas ou l'utilisateur entre des noms de pirates qui ne sont pas dans l'équipage,
	 * un message d'erreur s'affiche.
	 */
	public void echangeObjet() {
		boolean pirate1 = true;
		boolean pirate2 = true;
		System.out.println("Entrez le nom des deux pirates dont vous souhaitez échanger les objets (à séparer avec au moins un espace !) : ");
		String texte = sc.nextLine();
		String pirates[] = texte.split(" ");
		for(Pirate x : equipage) {
			if(x.getNom().contains(pirates[0])) {
				pirate1 = false;
				for(Pirate y : equipage) {
					if(y.getNom().contains(pirates[1])) {
						Objet temp = new Objet(x.getObjetRecu().getNumero());
						x.attribuerObjet(y.getObjetRecu());
						y.attribuerObjet(temp);
						System.out.println("Les objets des pirates \""+x.getNom()+"\" et \""+y.getNom()+"\" ont été échangés.");
						break;
					}
				}
				break;
			}
		}
		for(Pirate z : equipage) {
			if(z.getNom().contains(pirates[1])) {
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
	}

	
	/**
	 * Méthode qui calcule et affiche le cout pour la solution actuelle en regardant pour chaque pirate s'il est jaloux.
	 */
	public void calculCout() {
		for(Pirate x : equipage)
			if(estJaloux(x))
				cout += 1;
		System.out.println("Le coût de la solution actuelle vaut : "+cout+".");
	}
	
	
	/**
	 * Méthode qui permet de reinitialiser la valeur de l'attribut cout à 0.
	 */
	public void reinitialiserCout() {
		cout = 0;
	}
	
	
	/**
	 * Méthode qui vérifie si un pirate est jaloux (un pirate est jaloux si un pirate avec lequel il possède une relation 
	 * "ne s'aiment pas" obtient l'objet préféré dans sa liste de préférence d'objets.
	 * 
	 * @param p Le pirate pour lequel on va vérifier s'il est jaloux
	 * @return true si et seulement le pirate en paramètre et un autre pirate de l'équipage qui ont une relation "s'aiment pas"
	 * possède l'objet préféré du pirate en paramètre
	 */
	public boolean estJaloux(Pirate p) {
		if(p.getObjetRecu().getNumero() == p.getListePreferenceObjet().get(0).getNumero())
			return false;
		int[] tab = p.getListeRelationPirates();
		for(int a=0; a<tab.length; a++)
			if(tab[a] == 1)
				if(p.getListePreferenceObjet().get(0).getNumero() == equipage.get(a).getObjetRecu().getNumero())
			        return true;
		return false;
	}

	
	/**
	 * Méthode qui créer la liste de relation d'un pirate, pour tous les pirates.
	 */
	public void creationListesRelationPirates() {
		for(Pirate p : equipage)
			p.initListeRelationPirates(nbPirate);
	}
	
}