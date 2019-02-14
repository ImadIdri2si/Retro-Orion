package fenetres;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Window.Type;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;

import aaplication.App25RetroOrion;

import javax.swing.JTabbedPane;
import javax.swing.JToolBar;
/**
 * Fenetre qui affiche les informations sur l'application, les concepts scientifiques, les auteurs
 * @author Imad Idrissi
 *
 */
public class Fenetre5APropos extends JFrame {

	private JPanel contentPane;
	private int width = 1440, height = 810;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		Fenetre5APropos frame = new Fenetre5APropos();
		frame.setVisible(false);		
	}

	/**
	 * Création de la fenetre avec ses composants
	 */
	public Fenetre5APropos() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setUndecorated(true);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double width = screenSize.getWidth();
		double height = screenSize.getHeight();
		setBounds((int)(width/2-1366/2), (int)(height/2-768/2), 1366, 768);
		//setBounds(0, 0, width, height);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		// Bouton qui permet de retourner a la fenetre précedente 
		JButton btnRetour = new JButton("Retour");
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Debut
				App25RetroOrion FenetrePrincipale = new App25RetroOrion();
				FenetrePrincipale.setVisible(true);
				setVisible(false);
				//Fin
			}
		});
		btnRetour.setFont(new Font("Times", Font.BOLD, 18));
		btnRetour.setBounds(0, 732, 98, 37);
		contentPane.add(btnRetour);
		// L'image des informations scientifique 
		ImageAvecDefilement imageAvecDefilement1 = new ImageAvecDefilement();
		imageAvecDefilement1.setBounds(0, 0, 1366, 732);
		contentPane.add(imageAvecDefilement1);
		imageAvecDefilement1.setFichierImage("Apropos.jpg");
		
	}
}
