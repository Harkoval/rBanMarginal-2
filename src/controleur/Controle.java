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
	private String ip;
	private Arene frmArene;
	private ChoixJoueur frmChoixJoueur;
	private EntreeJeu frmEntreeJeu;
	private String typeJeu; /// Le type de jeu: Serveur ou Connexion
	final String PORT = "";

	@Override
	public void reception(Connection connection, String ordre, Object info) {
		switch (ordre) {
		case "connexion":
			if (typeJeu == "client") {
				frmEntreeJeu.dispose();
				this.frmArene = new Arene();
				(new ChoixJoueur(this)).setVisible(true);
			} else {

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

		//new ChoixJoueur(this);
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
			ip = info;

		} else {
			frmEntreeJeu.dispose();
			//(new ChoixJoueur(this)).setVisible(true);
			typeJeu = "client";
			ClientSocket servSock = new ClientSocket(this, info, 6666);

		}
	}

	public void evenementEntreeJeu(String pseudo, int numPerso) {
		this.frmChoixJoueur = new ChoixJoueur(this);
		this.frmChoixJoueur.setVisible(true);
		typeJeu = "client";
		frmEntreeJeu.dispose();
		this.frmArene = new Arene();
		this.frmArene.setVisible(true);
		frmChoixJoueur.dispose();

	}
}
