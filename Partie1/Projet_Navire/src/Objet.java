/**
 * Classe qui correspond aux objets des butins de l'équipage de pirates, 
 * et permet ainsi la manipulation de l'objet.
 * 
 * @author Wissam Amarehoun et Enes Asam
 * @version 1.0
 */
public class Objet {
	
	private int numero;
	
	public Objet(int numero) {
		this.numero = numero;
	}
	
	
	/**
	 * Méthode qui permet de recupérer la valeur de l'attribut numero.
	 * 
	 * @return le numéro de l'objet
	 */
	public int getNumero() {
		return numero;
	}
}