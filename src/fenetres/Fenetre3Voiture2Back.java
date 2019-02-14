package fenetres;

import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.Dimension;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.MouseMotionAdapter;
import java.io.IOException;
import java.net.URL;
import java.awt.event.MouseEvent;
import javax.swing.border.LineBorder;

import foundation.OmniMap;
import foundation.SiteOrion;

import java.awt.event.MouseAdapter;
import java.awt.event.ContainerAdapter;
import java.awt.event.ContainerEvent;
import javax.swing.SwingConstants;
import javax.swing.JToggleButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
/**
 * Classe permet de choisir les véhicules pour les joueurs 
 * @author alphascred
 *
 */
public class Fenetre3Voiture2Back extends JFrame {

	
	private JPanel contentPane;

	
    // Les noms de vehicules choisie 
	private String ChoixVehiculeBeta = null;
	private String ChoixVehiculeZeta = null;
	private String ChoixVehicule = null;

	// Les labels pour afficher les images 
	private JLabel picLabel;
	private JLabel picVehi= new JLabel();
	//Si le joueur 2 peut choisir
	private boolean player2 = false;

	/**
	 * execute l'applications
	 */
	public static void main(String[] args) {
		Fenetre3Voiture2Back frame = new Fenetre3Voiture2Back();
		frame.setVisible(true);
				
	}

	/**
	 * creation de l'interface avec tous ses fonctions necessaires
	 */
	public Fenetre3Voiture2Back() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setUndecorated(true);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double width = screenSize.getWidth();
		double height = screenSize.getHeight();
		setBounds((int)(width/2-1366/2), (int)(height/2-768/2), 1366, 768);
		
		contentPane = new JPanel();
		contentPane.setBackground(new Color(204, 0, 51));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null); 
		
		
		
		JLabel lblSlection = new JLabel("S茅lection");
		lblSlection.setForeground(new Color(255, 0, 51));
		lblSlection.setFont(new Font("Times New Roman", Font.PLAIN, 35));
		lblSlection.setBounds(283, 6, 161, 42);
		contentPane.add(lblSlection);
		
		JLabel lblVhicule = new JLabel("V脡HICULE");
		lblVhicule.setForeground(Color.WHITE);
		lblVhicule.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lblVhicule.setBounds(283, 29, 161, 50);
		contentPane.add(lblVhicule);
		
		JPanel pnlJoueur1 = new JPanel();
		pnlJoueur1.setOpaque(false);
		pnlJoueur1.setBounds(6, 91, 161, 361);
		contentPane.add(pnlJoueur1);
	
		
		JPanel pnlJoueur2 = new JPanel();
		pnlJoueur2.setOpaque(false);
		pnlJoueur2.setBounds(561, 91, 161, 361);
		contentPane.add(pnlJoueur2);
		
		JPanel pnlVoiture1 = new JPanel();
		pnlVoiture1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//Debut
				ChoixVehicule = "Covenent";
				//Fin
			}
		});
		pnlVoiture1.setBorder(new LineBorder(new Color(0, 0, 0)));
		pnlVoiture1.setOpaque(false);
		pnlVoiture1.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				//Debut
				if(!player2) {
					associerPanelAvecImage(pnlJoueur1, "Covenent.PNG", false);
				}else {
					associerPanelAvecImage(pnlJoueur2, "Covenent.PNG", false);
				}
				//Fin
			}
		});
		pnlVoiture1.setBounds(338, 91, 51, 52);
		associerPanelAvecImage(pnlVoiture1, "Covenent.PNG", true);
		contentPane.add(pnlVoiture1);
		
		
		JPanel pnlVoiture2 = new JPanel();
		pnlVoiture2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//Debut
				ChoixVehicule = "Decoder";
				//Fin
			}
		});
		pnlVoiture2.setBorder(new LineBorder(new Color(0, 0, 0)));
		pnlVoiture2.setOpaque(false);
		pnlVoiture2.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				//Debut
				if(!player2) {
					associerPanelAvecImage(pnlJoueur1, "Decoder.PNG", false);
				}else {
					associerPanelAvecImage(pnlJoueur2, "Decoder.PNG", false);
				}
				//Fin
			}
		});
		pnlVoiture2.setBounds(338, 143, 51, 52);
		contentPane.add(pnlVoiture2);
		associerPanelAvecImage(pnlVoiture2, "Decoder.PNG", false );
		pnlVoiture2.setLayout(null);
		
		JPanel pnlVoiture3 = new JPanel();
		pnlVoiture3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//Debut
				ChoixVehicule = "Reaper";
				//Fin
			}
		});
		pnlVoiture3.setBorder(new LineBorder(new Color(0, 0, 0)));
		pnlVoiture3.setOpaque(false);
		pnlVoiture3.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				//debut
				if(!player2) {
					associerPanelAvecImage(pnlJoueur1, "Reaper.PNG", false);
				}else {
					associerPanelAvecImage(pnlJoueur2, "Reaper.PNG", false);
				}
				//Fin
			}
		});
		pnlVoiture3.setBounds(338, 195, 51, 52);
		contentPane.add(pnlVoiture3);
		associerPanelAvecImage(pnlVoiture3, "Reaper.PNG", true );
		pnlVoiture3.setLayout(null);
		
		JPanel pnlVoiture4 = new JPanel();
		pnlVoiture4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//Debut
				ChoixVehicule = "Genessis";
				//Fin
			}
		});
		pnlVoiture4.setBorder(new LineBorder(new Color(0, 0, 0)));
		pnlVoiture4.setOpaque(false);
		pnlVoiture4.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				//Debut
				if(!player2) {
					associerPanelAvecImage(pnlJoueur1, "Genessis.PNG", false);
				}else {
					associerPanelAvecImage(pnlJoueur2, "Genessis.PNG", false);
				}
				//Fin
			}
		});
		pnlVoiture4.setBounds(338, 247, 51, 52);
		contentPane.add(pnlVoiture4);
		associerPanelAvecImage(pnlVoiture4, "Genessis.PNG", true);
		pnlVoiture4.setLayout(null);
		
		JPanel pnlVoiture5 = new JPanel();
		pnlVoiture5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//Debut
				ChoixVehicule = "Meditator";
				//Fin
			}
		});
		pnlVoiture5.setBorder(new LineBorder(new Color(0, 0, 0)));
		pnlVoiture5.setOpaque(false);
		pnlVoiture5.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				//Debut
				if(!player2) {
					associerPanelAvecImage(pnlJoueur1, "Meditator.PNG", false);
				}else {
					associerPanelAvecImage(pnlJoueur2, "Meditator.PNG", false);
				}
				//Fin
			}
		});
		pnlVoiture5.setBounds(338, 299, 51, 52);
		contentPane.add(pnlVoiture5);
		associerPanelAvecImage(pnlVoiture5, "Meditator.PNG", true );
		pnlVoiture5.setLayout(null);
		
		JPanel pnlVoiture6 = new JPanel();
		pnlVoiture6.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//Debut
				ChoixVehicule = "Singer";
				//Fin
			}
		});
		pnlVoiture6.setBorder(new LineBorder(new Color(0, 0, 0)));
		pnlVoiture6.setOpaque(false);
		pnlVoiture6.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				//Debut
				if(!player2) {
					associerPanelAvecImage(pnlJoueur1, "Singer.PNG", false);
				}else {
					associerPanelAvecImage(pnlJoueur2, "Singer.PNG", false);
				}
				//Fin
			}
		});
		pnlVoiture6.setBounds(338, 349, 51, 52);
		contentPane.add(pnlVoiture6);
		associerPanelAvecImage(pnlVoiture6, "Singer.PNG", true );
		pnlVoiture6.setLayout(null);

		
		JPanel pnlRandom = new JPanel();
		pnlRandom.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//Debut
				//Fin
			}
		});
		pnlRandom.setBorder(new LineBorder(new Color(0, 0, 0)));
		pnlRandom.setOpaque(false);
		pnlRandom.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				//Debut
				//Fin
			}
		});
		pnlRandom.setBounds(338, 400, 51, 52);
		contentPane.add(pnlRandom);
		pnlRandom.setLayout(null);
		
		
		
		JPanel pnlVehiculeInformation = new JPanel();
		pnlVehiculeInformation.setBorder(null);
		pnlVehiculeInformation.setBounds(166, 91, 148, 208);
		contentPane.add(pnlVehiculeInformation);
		pnlVehiculeInformation.setLayout(null);
		
		JLabel lblNom = new JLabel("Nom :");
		lblNom.setBounds(6, 21, 61, 16);
		pnlVehiculeInformation.add(lblNom);
		
		JLabel lblVitesse = new JLabel("Vitesse min :");
		lblVitesse.setBounds(6, 58, 87, 16);
		pnlVehiculeInformation.add(lblVitesse);
		
		JLabel lblAcceleration = new JLabel("Acceleration : ");
		lblAcceleration.setBounds(6, 132, 96, 16);
		pnlVehiculeInformation.add(lblAcceleration);
		
		JLabel lblMasse = new JLabel("Masse :");
		lblMasse.setBounds(6, 169, 61, 16);
		pnlVehiculeInformation.add(lblMasse);
		
		JLabel lblVitesseMaximale = new JLabel("Vitesse max :");
		lblVitesseMaximale.setBounds(6, 95, 96, 16);
		pnlVehiculeInformation.add(lblVitesseMaximale);
		
		JPanel pnlVehiculeInformation2 = new JPanel();
		pnlVehiculeInformation2.setLayout(null);
		pnlVehiculeInformation2.setBounds(412, 91, 148, 208);
		contentPane.add(pnlVehiculeInformation2);
		
		JLabel label = new JLabel("Nom :");
		label.setBounds(6, 21, 61, 16);
		pnlVehiculeInformation2.add(label);
		
		JLabel label_1 = new JLabel("Vitesse min :");
		label_1.setBounds(6, 58, 87, 16);
		pnlVehiculeInformation2.add(label_1);
		
		JLabel label_2 = new JLabel("Acceleration : ");
		label_2.setBounds(6, 132, 96, 16);
		pnlVehiculeInformation2.add(label_2);
		
		JLabel label_3 = new JLabel("Masse :");
		label_3.setBounds(6, 169, 61, 16);
		pnlVehiculeInformation2.add(label_3);
		
		JLabel label_4 = new JLabel("Vitesse max :");
		label_4.setBounds(6, 95, 96, 16);
		pnlVehiculeInformation2.add(label_4);

		// Creation de la map
		SiteOrion orion = new SiteOrion();
		//Bouton pour confirmer le choix du joueur 1
		JButton btnPret1 = new JButton("PRET");
		btnPret1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Debut 

				//vPlayer1 = ChoixVehicule;

				ChoixVehiculeBeta = ChoixVehicule;
				orion.cubesMap.setPlayer1(ChoixVehiculeBeta);

				btnPret1.setEnabled(false);
				player2 = true;
				System.out.println(ChoixVehiculeBeta);
				//
			}
		});
		btnPret1.setBounds(166, 299, 148, 153);
		contentPane.add(btnPret1);
		//Bouton pour confirmer le choix du joueur 2
		JButton btnPret = new JButton("PRET");
		btnPret.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Debut
				ChoixVehiculeZeta = ChoixVehicule;
				System.out.println(ChoixVehiculeZeta);
				orion.cubesMap.setPlayer2(ChoixVehiculeZeta);
				dispose();
				orion.setVisible(true);
				//Fin
			}			
		});
		btnPret.setBounds(412, 299, 148, 153);
		contentPane.add(btnPret);
		
		
		
	}
	/**
	 * Méthode qui permet d'aller chercher les images et les placer sur les label du panel
	 * @param lePanel Le choix du panel
	 * @param fichierImage Le nom de l'image
	 * @param newLabel pour avoir un nouveau label
	 */
	public void associerPanelAvecImage(JPanel lePanel, String fichierImage, boolean newLabel ) {
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
		//redimensionner l'image de la meme grandeur que le bouton
		Image imgRedim = imgLue.getScaledInstance( lePanel.getWidth()-5, lePanel.getHeight()-5, Image.SCALE_SMOOTH);
		if(newLabel) {
			picLabel = new JLabel();
			picLabel.setBounds(0, 0, lePanel.getWidth(), lePanel.getHeight());
			imgLue.flush();
			ImageIcon icon = new ImageIcon(imgRedim);
			picLabel.setIcon( icon );
			lePanel.add(picLabel);
			lePanel.repaint();
			icon.getImage().flush();
		}else {
			imgLue.flush();
			ImageIcon icon = new ImageIcon(imgRedim);
			picVehi.setIcon( icon );
			lePanel.add(picVehi);
			lePanel.repaint();
			icon.getImage().flush();
		}
		
		
	}
}
