package aaplication;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import fenetres.Background;
import fenetres.Fenetre2ChoixMode;
import fenetres.Fenetre5APropos;
import fenetres.Fenetre7Option;
import fenetres.FenetreConceptScientifique;
import fenetres.FenetreGuideUti;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import fenetres.boutton;
import javax.swing.JCheckBox;
/**
 * Executeur du jeu
 * @author Imad Imad
 * 
 */
public class App25RetroOrion extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Fenetre2ChoixMode fenetre2;
	private Fenetre7Option fenetre7;
	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private double widthScreen = screenSize.getWidth();
	private double heightScreen = screenSize.getHeight();
	private int width = 1200, height = 700;
	private Background background ;
	private JButton btnCommencer;
	static AudioClip monClip;
	
	/**
	 * execute l'application.
	 */
	public static void main(String[] args) {
		App25RetroOrion FenetrePrincipale = new App25RetroOrion() ;
		FenetrePrincipale.setVisible(true);
	}
		

	/**
	 * creation de l'interface avec tous ses fonctions necessaires
	 */
	public App25RetroOrion() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setBounds((int)(widthScreen/2)-(width/2), (int)(heightScreen/2)-(height/2), width, height);
		setUndecorated(true);
		double width = screenSize.getWidth();
		double height = screenSize.getHeight();
		setBounds((int)(width/2-1366/2), (int)(height/2-768/2), 1366, 768);
		lireLesSons();
		

		contentPane = new JPanel();
		contentPane.setBackground(new Color(102, 51, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		Background background_1 = new Background();
		background_1.setBounds(0, 0, 1366, 768);
		background_1.ImageUrl("Fenetre1.jpg");
		contentPane.add(background_1);
		background_1.setLayout(null);
		
		
	
		
		btnCommencer = new JButton("");
		btnCommencer.setBounds(257, 534, 851, 84);
		background_1.add(btnCommencer);
		btnCommencer.setFont(new Font("Times", Font.BOLD, 30));
		btnCommencer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Debut
				fenetre2 = new Fenetre2ChoixMode();
				fenetre2.setVisible(true);
				setVisible(false);
				//Fin
			}
		});
		associerBoutonAvecImage(btnCommencer, "Commencer.png" ) ;
		
		JButton btnPropos = new JButton("\u00C0 propos");
		btnPropos.setBounds(257, 630, 269, 84);
		background_1.add(btnPropos);
		btnPropos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Debut
				Fenetre5APropos propos = new Fenetre5APropos();
				propos.setVisible(true);
				dispose();
				//Fin
			}
		});
		btnPropos.setFont(new Font("Times", Font.BOLD, 25));
		associerBoutonAvecImage(btnPropos, "Aprop.png" ) ;
		
		JButton button_1 = new JButton("Concept Scientifique");
		button_1.setBounds(548, 630, 269, 84);
		background_1.add(button_1);
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Debut
				FenetreConceptScientifique conScien = new FenetreConceptScientifique();
				conScien.setVisible(true);
				dispose();	
				//Fin
			}
		});
		button_1.setFont(new Font("Dialog", Font.BOLD, 20));
		associerBoutonAvecImage(button_1, "Science.png" ) ;
		
		JButton button_2 = new JButton("Guide d'utilisation");
		button_2.setBounds(839, 630, 269, 84);
		background_1.add(button_2);
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Debut
				FenetreGuideUti guide = new FenetreGuideUti();
				guide.setVisible(true);
				dispose();
				//Fin
			}
		});
		button_2.setFont(new Font("Times", Font.BOLD, 25));
		associerBoutonAvecImage(button_2, "Guide.png" ) ;
		
		JCheckBox chckbxMusic = new JCheckBox("SONG");
		chckbxMusic.setFocusable(false);
		chckbxMusic.setBackground(new Color(0, 0, 0,0));
		repaint();
		chckbxMusic.setForeground(new Color(255, 0, 255));
		chckbxMusic.setSelected(true);
		if(chckbxMusic.isSelected()) {
			monClip.loop();
		}else {
			monClip.stop();
		}
		chckbxMusic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Debut
				if(chckbxMusic.isSelected()) {
					monClip.loop();
				}else {
					monClip.stop();
				}
				//Fin
			}
		});
		chckbxMusic.setFont(new Font("STIXIntegralsUp", Font.BOLD, 15));
		chckbxMusic.setBounds(1284, 6, 76, 30);
		background_1.add(chckbxMusic);
		
		JButton btnQuitter = new JButton("Quitter");
		btnQuitter.setBounds(6, 725, 98, 37);
		background_1.add(btnQuitter);
		btnQuitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnQuitter.setFont(new Font("Times", Font.BOLD, 18));
		associerBoutonAvecImage(btnQuitter, "Quitter.png" ) ;
		
		
	}
	/**
	 * Author Caroline
	 * Methode qui associe un bouton avec une image.
	 * @param leBouton Le bouton qui sera associee avec l'image.
	 * @param fichierImage L'image qui sera associe avec le bouton.
	 */
	public void associerBoutonAvecImage(JButton leBouton, String fichierImage ) {
		Image imgLue=null;
		URL urlImage = getClass().getClassLoader().getResource(fichierImage);
		if (urlImage == null) {
			JOptionPane.showMessageDialog(null , "Fichier " + fichierImage + " introuvable");
		}
		try {
			imgLue = ImageIO.read(urlImage);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null , "Erreur pendant la lecture du fichier d'image");
		}
		//redimensionner l'image de la m�me grandeur que le bouton
		Image imgRedim = imgLue.getScaledInstance( leBouton.getWidth(), leBouton.getHeight(), Image.SCALE_SMOOTH);
		//au cas o le fond de l'image serait transparent
		leBouton.setOpaque(false);
		leBouton.setContentAreaFilled(false);
		leBouton.setBorderPainted(false);
		//associer l'image au bouton
		leBouton.setText("");
		leBouton.setIcon( new ImageIcon(imgRedim) );
		//on se demarrasse des images interm�diaires
		imgLue.flush();
		imgRedim.flush();
	}
	/**
	 * Methode qui va chercher le song
	 */
	private void lireLesSons() {
		URL urlFichier = getClass().getClassLoader().getResource( "beat.wav" );
		monClip =Applet.newAudioClip(urlFichier);

	}
}

