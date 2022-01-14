import java.util.ArrayList;


/**
 * Classe qui correspond aux pirates de l'équipage de pirates, 
 * et permet ainsi la manipulation d'un pirate.
 * 
 * @author Wissam Amarehoun et Enes Asam
 * @version 1.0
 */
public class Pirate {
	
	
	private String nom;
	private int ListeRelationPirates[];
	private ArrayList<Objet> ListePreferenceObjet;
	private Objet objetRecu;
	
	
	public Pirate(String nom) {
		this.nom = nom;
		objetRecu = new Objet(0);
		ListePreferenceObjet = new ArrayList<Objet>();
	}
	
	
	/**
	 * Méthode qui permet d'initialiser la liste de relation d'un pirate à 0 aves les autres piates.
	 * 
	 * @param nbPirates le nombre de pirate dans l'équipage
	 */
	public void initListeRelationPirates(int nbPirates) {
		ListeRelationPirates = new int[nbPirates];
		for(int i=0; i<nbPirates; i++)
			ListeRelationPirates[i] = 0;
	}
	
	
	/**
	 * Méthode qui permet de recupérer l'attribut ListeRelationPirates[].
	 * 
	 * @return un tableau d'entier qui indique la liste des relations d'un pirate avec les autres
	 */
	public int[] getListeRelationPirates() {
		return ListeRelationPirates;
	}
	
	
	/**
	 * Méthode qui permet d'ajouter une relation "ne s'aiment pas" à un pirate avec un autre pirate à partir de sa position dans la liste.
	 * 
	 * @param position de l'élément dont on change la valeur à 1
	 */
	public void setListeRelationPirates(int position) {
		ListeRelationPirates[position] = 1;
	}

	/**
	 * Méthode qui permet de récuperer l'attribut nom d'un pirate.
	 * 
	 * @return nom le nom d'un pirate
	 */
	public String getNom() {
		return this.nom;
	}
	
	
	/**
	 * Méthode qui permet d'attribuer un objet à un pirate.
	 * 
	 * @param o Objet qu'on va attribuer à l'attribut "objetRecu"
	 */
	public void attribuerObjet(Objet o) {
		objetRecu = o;
	}
	
	
	/**
	 * Méthode qui permet de récuperer l'attribut objetRecu d'un pirate.
	 * 
	 * @return objetRecu L'objet qu'un pirate a reçu
	 */
	public Objet getObjetRecu() {
		return objetRecu;
	}
	
	
	/**
	 * Méthode qui permet d'ajouter une relation "ne s'aiment pas" avec un autre pirate en déduisant sa position avec son nom.
	 * 
	 * @param pirate Nom du pirate avec lequel on souhaite ajouter un relation "ne s'aiment pas"
	 */
	public void ajoutRelation(String pirate) {
		char caractere = pirate.charAt(0);
		int indice = caractere-65;
		setListeRelationPirates(indice);
	}
	
	
	/**
	 * Méthode qui vérifie pour un pirate si une relation "ne s'aiment pas" existe déjà avec le pirate prit en paramètre
	 * 
	 * @param p Le pirate avec lequel on souhaite savoir si une relation "ne s'aiment pas" existe déjà
	 * 
	 * @return true si et seulement si une relation "ne s'aiment pas" entre les deux pirates existe déjà
	 */
	public boolean verifRelation(Pirate p) {
		boolean condition = false;
		char lettre = p.getNom().charAt(0);
		int indice = lettre-65;
		if(ListeRelationPirates[indice] == 1) {
			condition = true;
		}
		return condition;
	}

	
	/**
	 * Méthode permettant de récuperer la liste de preference des objets qui est un attribut de la classe.
	 * 
	 * @return l'attribut ListePreferenceObjet
	 */
	public ArrayList<Objet> getListePreferenceObjet(){
		return ListePreferenceObjet;
	}
	
	
	/**
	 * Méthode permettant de changer la liste de preference d'objets par une nouvelle liste de preference d'objets vide.
	 */
	public void setListePreferenceObjet() {
		ArrayList<Objet> nouveau = new ArrayList<Objet>();
		ListePreferenceObjet = nouveau;
	}
	
	
	/**
	 * Méthode qui affiche le nom du pirate et ses préférences d'objets.
	 */
	public void afficherPreference() {
		System.out.print(nom+" : ");
		for(int i=0; i<ListePreferenceObjet.size()-1; i++)
			System.out.print("o"+ListePreferenceObjet.get(i).getNumero()+", ");
		System.out.println("o"+ListePreferenceObjet.get(ListePreferenceObjet.size()-1).getNumero()+".");
	}
	
	
	/**
	 * Méthode qui ajoute un objet dans la liste de préférence du pirate.
	 * 
	 * @param o L'objet que l'on souhaite ajouter dans la liste de préférences
	 */
	public void ajoutPreference(Objet o) {
		ListePreferenceObjet.add(o);
	}
	
	
	/**
	 * Méthode qui remplace un objet à une position par un nouvel objet dans la liste de préférences d'objets.
	 * 
	 * @param pos La position de l'objet dans la liste de préférences
	 * @param o L'objet que l'on souhaite positionner à la position pos dans la liste de préférences
	 */
	public void ajoutPreference2(int pos ,Objet o) {
		ListePreferenceObjet.set(pos, o);
	}
	
}