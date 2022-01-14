package NavireAA;

/**
 * Classe qui correspond aux objets des butins de l'équipage de pirates, 
 * et permet ainsi la manipulation de l'objet.
 * 
 * @author Wissam Amarehoun et Enes Asam
 * 
 * @version 2.0
 */
public class Objet{
	
	/**
	 * Attribut privé représentant le nom de l'objet sous forme de chaîne de caractères (String).
	 * 
	 * @since 2.0
	 */
	private String nom;
	
	/**
	 * Constructeur permettant de créer un objet en lui attribuant un nom.
	 * L'objet sera possiblement crée lors de l'appel de la méthode 'creation(String cheminFichier)' dans la classe EquipagePirate, si le fichier est conforme.
	 * 
	 * @param nom Le nom de l'objet sous forme de chaîne de caractères (String).
	 * 
	 * @since 2.0
	 */
	public Objet(String nom) {
		this.nom = nom;
	}

	
	/**
	 * Méthode qui permet de recupérer la valeur de l'attribut nom.
	 * 
	 * @return nom Le nom de l'objet.
	 * 
	 * @since 2.0
	 */
	public String getNom() {
		return nom;
	}
}