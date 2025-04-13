package vue;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Dimension;

import controleur.Controle;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import controleur.Controle;

/**
 * Frame du choix du joueur
 * @author emds
 *
 */
public class ChoixJoueur extends JFrame {
	
	private Controle controle;
    private JLabel lblPersonnage;
	/**
	 * Panel général
	 */
	private JPanel contentPane;
	/**
	 * Zone de saisie du pseudo
	 */
	private JTextField txtPseudo;


	/**
	 * Clic sur la flèche "précédent" pour afficher le personnage précédent
	 */
	private void lblPrecedent_clic() {
		System.out.println("Clic sur precedent");
		if (num_perso == 1) {
			num_perso = 3;
		}
		else
		{
			num_perso -= 1;
		}
		new afficherPerso().afficherPersoMethode(num_perso);
	}
	
	/**
	 * Clic sur la flèche "suivant" pour afficher le personnage suivant
	 */
	private void lblSuivant_clic() {
		System.out.println("Clic sur suivant");
		if (num_perso == 3) {
			num_perso = 1;
		}
		else
		{
			num_perso += 1;
		}
		new afficherPerso().afficherPersoMethode(num_perso);
	}
	
	/**
	 * Clic sur GO pour envoyer les informations
	 */
	private void lblGo_clic() {
		//(new Arene()).setVisible(true);
		//this.dispose();
		if (!txtPseudo.getText().trim().isEmpty()) {
			this.dispose();
			controle.evenementEntreeJeu(txtPseudo.getText(), num_perso);
			
		}
		else
		{
			JOptionPane.showMessageDialog(null, "La saisie du pseudo est obligatoire");
			txtPseudo.requestFocusInWindow();
		}
	}
	
	/*
	 * Méthode pour afficher le perso
	 */
	
	private int num_perso = 1;
	
	public class afficherPerso{
		public void afficherPersoMethode(int numPerso) {
			String chemin = "personnages/perso"+ num_perso + "marche1d1.GIF";
			URL resource = getClass().getClassLoader().getResource(chemin);
			ImageIcon icon = new ImageIcon(resource);
	        
	        // Redimensionnement de l’image
	        Image img = icon.getImage().getScaledInstance(120, 135, Image.SCALE_SMOOTH);
	        lblPersonnage.setIcon(new ImageIcon(img));
		}
		
	}
	/**
	 * Create the frame.
	 */
	public ChoixJoueur(Controle controle) {
		this.controle = controle;
		
		setTitle("Choice");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblPersonnage = new JLabel("New label");
		lblPersonnage.setBounds(142, 116, 120, 118);
		lblPersonnage.setHorizontalAlignment(SwingConstants.CENTER); // positionnement sur la zone de saisie
		contentPane.add(lblPersonnage);
		
		// Dimension de la frame en fonction de son contenu
		this.getContentPane().setPreferredSize(new Dimension(400, 275));
	    this.pack();
	    // interdiction de changer la taille
		this.setResizable(false);
		 
		
		JLabel lblPrecedent = new JLabel("");
		lblPrecedent.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				lblPrecedent_clic();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				contentPane.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				contentPane.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		});
		
		JLabel lblSuivant = new JLabel("");
		lblSuivant.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lblSuivant_clic();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				contentPane.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				contentPane.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		});
		
		JLabel lblGo = new JLabel("");
		lblGo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lblGo_clic();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				contentPane.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				contentPane.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
		});
		
		txtPseudo = new JTextField();
		txtPseudo.setBounds(142, 245, 120, 20);
		contentPane.add(txtPseudo);
		txtPseudo.setColumns(10);
		txtPseudo.requestFocus();
		
		lblGo.setBounds(311, 202, 65, 61);
		contentPane.add(lblGo);
		lblSuivant.setBounds(301, 145, 25, 46);
		contentPane.add(lblSuivant);
		lblPrecedent.setBounds(65, 146, 31, 45);
		contentPane.add(lblPrecedent);
		
		JLabel lblFond = new JLabel("");
		lblFond.setBounds(0, 0, 400, 275);
		String chemin = "fonds/fondchoix.jpg";
		URL resource = getClass().getClassLoader().getResource(chemin);
		lblFond.setIcon(new ImageIcon(resource));		
		contentPane.add(lblFond);
		
		num_perso = 1;
		new afficherPerso().afficherPersoMethode(num_perso);

	}
}
