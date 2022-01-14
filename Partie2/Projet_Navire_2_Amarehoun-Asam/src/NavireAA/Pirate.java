package NavireAA;

import java.util.ArrayList;


/**
 * Classe qui correspond aux pirates de l'équipage, 
 * et permet ainsi la manipulation d'un pirate.
 * 
 * @author Wissam Amarehoun et Enes Asam
 * 
 * @version 2.0
 */
public class Pirate implements Cloneable{
	
	/**
	 * Attribut privé étant une chaîne de caractères (String) correspondant au nom du pirate.
	 * 
	 * @since 1.0
	 */
	private String nom;
	
	/**
	 * Attribut privé étant une liste de chaîne de caractères (ArrayList<String>) représentant pour le pirate, le nom des pirates avec lesquelles il possède une relation "ne s'aiment pas".
	 * Cette liste sera vide si le pirate possède aucune relation "ne s'aiment pas" avec les autres pirates.
	 * 
	 * @since 2.0
	 */
	private ArrayList<String> ListeRelationPirates;
	
	/**
	 * Attribut privé étant une liste d'objets (ArrayList<Objet>) représentant la liste de préférences d'objets du pirate (0 pour le préféré et dernier indice pour le moins préféré).
	 * 
	 * @since 1.0
	 */
	private ArrayList<Objet> ListePreferenceObjet;
	
	/**
	 * Attribut privé de type objet correspondant à l'objet que le pirate à reçu (null si aucun objet reçu).
	 * 
	 * @since 1.0
	 */
	private Objet objetRecu;
	
	/**
	 * Constructeur créant un pirate à partir de son nom. Son objetRecu vaut 'null' et ses listes 'ListeRelationPirates' et 'ListePreferenceObjet' sont créées et vide.
	 * Le pirate sera crée et les listes seront remplis lors de l'appel de la méthode 'creation(String cheminFichier)' dans la classe EquipagePirate, si le fichier est conforme.
	 * 
	 * @param nom Le nom du pirate
	 * 
	 * @since 2.0
	 */
	public Pirate(String nom) {
		this.nom = nom;
		objetRecu = null;
		ListePreferenceObjet = new ArrayList<Objet>();
		ListeRelationPirates = new ArrayList<String>();
	}
	
	
	/**
	 * Méthode qui permet de recupérer l'attribut ListeRelationPirates.
	 * 
	 * @return une liste de chaînes de caractères indiquant la liste des relations d'un pirate avec les autres.
	 * 
	 * @since 2.0
	 */
	public ArrayList<String> getListeRelationPirates() {
		return ListeRelationPirates;
	}

	
	/**
	 * Méthode qui permet de récuperer l'attribut nom d'un pirate.
	 * 
	 * @return nom le nom d'un pirate.
	 * 
	 * @since 1.0
	 */
	public String getNom() {
		return this.nom;
	}

	
	/**
	 * Méthode qui permet d'attribuer un objet à un pirate.
	 * 
	 * @param o Objet qu'on va attribuer à l'attribut "objetRecu".
	 * 
	 * @since 1.0
	 */
	public void attribuerObjet(Objet o) {
		objetRecu = o;
	}
	
	
	/**
	 * Méthode qui permet de récuperer l'attribut objetRecu d'un pirate.
	 * 
	 * @return objetRecu L'objet qu'un pirate a reçu.
	 * 
	 * @since 1.0
	 */
	public Objet getObjetRecu() {
		return objetRecu;
	}

	
	/**
	 * Méthode qui permet d'ajouter une relation "ne s'aiment pas" avec un autre pirate en ajoutant son nom dans la liste de relation de pirates.
	 * 
	 * @param pirate Nom du pirate avec lequel on souhaite ajouter un relation "ne s'aiment pas".
	 * 
	 * @since 2.0
	 */
	public void ajoutRelation(String pirate) {
		ListeRelationPirates.add(pirate);
	}

	
	/**
	 * Méthode permettant de récuperer l'attribut ListePreferenceObjet.
	 * 
	 * @return une liste d'objets indiquant la liste de préférences d'objets du pirate.
	 * 
	 * @since 1.0
	 */
	public ArrayList<Objet> getListePreferenceObjet(){
		return ListePreferenceObjet;
	}

	
	/**
	 * Méthode qui ajoute un objet dans l'attribut ListePreferenceObjet étant la liste de préférence du pirate.
	 * 
	 * @param o L'objet que l'on souhaite ajouter dans la liste de préférences.
	 * 
	 * @since 1.0
	 */
	public void ajoutPreference(Objet o) {
		ListePreferenceObjet.add(o);
	}
	
	
	/**
	 * Méthode qui vérifie si le pirate est jaloux (un pirate est jaloux si un pirate avec lequel il possède une relation 
	 * "ne s'aiment pas" obtient un objet se situant avant l'objet qu'il a reçu dans sa liste de préférence d'objets).
	 * 
	 * @param equipage l'équipage sur lequel on souhaite vérifier cela.
	 * 
	 * @return true si et seulement un pirate de ListeRelationPirate possède l'objet préféré du pirate.
	 * 
	 * @since 2.0
	 */
	public boolean estJaloux(ArrayList<Pirate> equipage) {
		if(this.objetRecu.getNom().equals(ListePreferenceObjet.get(0).getNom()))
	    	return false;
		for(Objet o : ListePreferenceObjet) {
			if(o.getNom().equals(objetRecu.getNom())) {
				return false;
			}else {
				for(String nom : ListeRelationPirates)
					for(Pirate p : equipage)
						if(p.getNom().equals(nom))
							if(p.getObjetRecu().getNom().equals(o.getNom()))
								return true;
			}
		}
		return false;
	}
	
	
	/**
	 * Méthode permettant de savoir si le pirate a un objet reçu.
	 * 
	 * @return true si l'attribut objetRecu n'est pas de valeur "null".
	 * 
	 * @since 2.0
	 */
	public boolean possedeObjet() {
		if(objetRecu == null)
			return false;
		return true;
	}
	
	
	/**
	 * Méthode permettant de cloner le pirate afin de pouvoir manipuler le clone sans modifier l'instance de base.
	 * En cas de problème, le programme s'arrête et un message s'affiche.
	 * 
	 * @return p Le pirate clone.
	 * 
	 * @throws CloneNotSupportedException Si le clonage à échoué
	 * 
	 * @since 2.0
	 */
	public Object clone() throws CloneNotSupportedException {
		Pirate p = null;
		try {
			p = (Pirate) super.clone();
		}catch(CloneNotSupportedException cnse) {
			System.out.println("Il y a un problème au niveau du clonage de pirate.\nVeuillez réessayer.");
		}
		return p;
	}
}