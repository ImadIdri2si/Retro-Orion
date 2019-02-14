package fenetres;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 * Classe de la fenetre personnaliser la au le joueur modifier la map comme il le percoit en lui ajoutant des obstacles un champs magnétique, etc
 * @author Imad Idrissi
 *
 */
public class Fenetre8Personnalise extends JFrame {

	private JPanel contentPane;

	/**
	 * Lancement de l'application
	 */
	
	/**
	 * création de la fenetre avec ses composants.
	 */
	public Fenetre8Personnalise() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}

}
