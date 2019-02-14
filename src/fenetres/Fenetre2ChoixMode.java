package fenetres;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import aaplication.App25RetroOrion;
import foundation.MapCreator;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JCheckBox;
/**
 * Classe de la fenetre choix de mode de jeu
 * @author Imad Idrissi
 *
 */

public class Fenetre2ChoixMode extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	//Les fenetres relie a cette interface
	private App25RetroOrion fenetre1; 
	private Fenetre7Option fenetre7;
	private Fenetre9Orions fenetre3;
	private Background background;
	

	
	public Fenetre2ChoixMode() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setBounds(0, 0,  1280, 787);
		setUndecorated(true);
		double width = screenSize.getWidth();
		double height = screenSize.getHeight();
		setBounds((int)(width/2-1366/2), (int)(height/2-768/2), 1366, 768);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(102, 51, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		

		
		// Boutton retour dans la fenetre précedente
		JButton btnRetour = new JButton("Retour.png");
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Debut
				fenetre1 = new App25RetroOrion();
				fenetre1.setVisible(true);
				setVisible(false);
				//Fin
				
				
			}
		});
		btnRetour.setFont(new Font("Times", Font.BOLD, 13));
		btnRetour.setBounds(10, 721, 117, 37);
		associerBoutonAvecImage(btnRetour,"Retour.png");
		contentPane.add(btnRetour);
		
		background = new Background();
		background.setBounds(-12, 0, 1378, 768);
		background.ImageUrl("ChoixMode.jpg");
		contentPane.add(background);
		background.setLayout(null);
		//Bouton pour aller sur la fenetre personnalisé
		JButton buttonPersonnalise = new JButton("Personnalisé");
		buttonPersonnalise.setBounds(364, 537, 690, 61);
		background.add(buttonPersonnalise);
		buttonPersonnalise.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MapCreator creator = new MapCreator();
				dispose();
				creator.setVisible(true);
			}
		});
		buttonPersonnalise.setFont(new Font("Times", Font.BOLD, 30));
		associerBoutonAvecImage(buttonPersonnalise,"Creation.png");
		//Bouton pour aller sur la fenetre des choix d'univers
		JButton btnUnivers = new JButton("Orions");
		btnUnivers.setBounds(364, 457, 691, 61);
		background.add(btnUnivers);
		btnUnivers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Debut
				fenetre3 = new Fenetre9Orions();
				fenetre3.setVisible(true);
				setVisible(false);
				// Fin
			}
		});
		btnUnivers.setFont(new Font("Times", Font.BOLD, 30));
		associerBoutonAvecImage(btnUnivers,"Orions.png");
	}
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
		//au cas o  le fond de l'image serait transparent
		leBouton.setOpaque(false);
		leBouton.setContentAreaFilled(false);
		leBouton.setBorderPainted(false);
		//associer l'image au bouton
		leBouton.setText("");
		leBouton.setIcon( new ImageIcon(imgRedim) );
		//on se d�barrasse des images interm�diaires
		imgLue.flush();
		imgRedim.flush();
	}
}
