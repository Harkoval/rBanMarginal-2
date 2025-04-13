package controleur;

import vue.Arene;
import vue.EntreeJeu;
import vue.ChoixJoueur;
import outils.connexion.ServeurSocket;
import outils.connexion.AsyncResponse;
import outils.connexion.Connection;
import outils.connexion.ClientSocket;

/**
 * Contrôleur et point d'entrée de l'applicaton
 * 
 * @author emds
 *
 */
public class Controle implements AsyncResponse {
	private Arene frmArene;
	private ChoixJoueur frmChoixJoueur;
	private EntreeJeu frmEntreeJeu;
	private String typeJeu; ///Le type de jeu: Serveur ou Connexion
	final String PORT = "";
	
	
	@Override
	public void reception(Connection connection, String ordre, Object info) {
		switch(ordre) {
		case "connexion":
			if (typeJeu == "client") {
				frmEntreeJeu.dispose();
				this.frmArene = new Arene();
				(new ChoixJoueur()).setVisible(true);
			}
			else
			{
				
			}
		case "reception":
			
		case "deconnexion":
	}
			
	}

	/**
	 * Méthode de démarrage
	 * 
	 * @param args non utilisé
	 */
	public static void main(String[] args) {
		new Controle();
	}

	/**
	 * Constructeur
	 */
	private Controle() {
		
		this.frmEntreeJeu = new EntreeJeu(this);
		this.frmEntreeJeu.setVisible(true);
	}

	public void evenementEntreeJeu(String info) {
		System.out.println(info);
		if (info.equals("serveur")) {
			frmEntreeJeu.dispose();
			this.frmArene = new Arene();
			this.frmArene.setVisible(true);
			typeJeu = "serveur";
			ServeurSocket servSock = new ServeurSocket(this, 6666);
			
		} else {
			frmEntreeJeu.dispose();
			(new ChoixJoueur()).setVisible(true);
			typeJeu = "client";
			ClientSocket servSock = new ClientSocket(this, info, 6666);
			
			
		}
	}


}
