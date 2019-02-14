package fenetres;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;

import JPanelButton.PanelMapListener;
import JPanelButton.panelMap;
import foundation.SiteOrion;

import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

import foundation.MapSavior;
import foundation.OmniMap;
/**
 * Classe de la fenetre Orions qui permet de choisir quelle planete, chacune ayant un désign different, et un frottement différend
 * @author XU WEI DUO
 * @author Imad Idrissi
 *
 */
public class Fenetre9Orions extends JFrame {

	private JPanel contentPane;
	private int width = 1440, height = 810;
	private Fenetre7Option fenetre7;
	private OmniMap omniMap;
	private int chosen=0;
	/**
	 * Lancement de l'application 
	 */

	/**
	 * Création de la fenetre avec ses composants
	 */
	public Fenetre9Orions() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setUndecorated(true);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double width = screenSize.getWidth();
		double height = screenSize.getHeight();
		setBounds((int)(width/2-1366/2), (int)(height/2-768/2), 1366, 768);
		
		
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new LineBorder(Color.PINK, 5));
		//contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUnivers = new JLabel("");
		lblUnivers.setForeground(Color.WHITE);
		lblUnivers.setFont(new Font("OCR A Extended", Font.BOLD, 94));
		lblUnivers.setBounds(getWidth()/2-760/2, 51, 760, 91);
		contentPane.add(lblUnivers);
		//Bouton permet de retourner a la fenetre précedente
		JButton btnRetour = new JButton("CHOIX ORIONS");
		btnRetour.setFocusable(false);
		btnRetour.setFocusTraversalKeysEnabled(false);
		btnRetour.setFocusPainted(false);
		btnRetour.setForeground(Color.WHITE);
		btnRetour.setBounds(getWidth()/2-260/2, 716, 260, 45);
		btnRetour.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				//begin
				btnRetour.setText("RETOURNE");
				
				//fin
			}
			@Override
			public void mouseExited(MouseEvent e) {
				//begin
				btnRetour.setText("CHOIX ORIONS");
				//fin
			}
		});
		btnRetour.setBackground(Color.GRAY);
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Debut
				Fenetre2ChoixMode fenetre3 =new Fenetre2ChoixMode();
				fenetre3.setVisible(true);
				setVisible(false);
				//Fin
			}
		});
		btnRetour.setFont(new Font("OCR A Extended", Font.BOLD, 18));
		
		contentPane.add(btnRetour);
		//Bouton pour aller a la fenetre options
		/**
		JButton button = new JButton("Option");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Debut
				fenetre7 = new Fenetre7Option();
				fenetre7.setVisible(true);
				//Fin
			}
		});
		button.setFont(new Font("Times", Font.BOLD, 18));
		button.setBounds(1258, 10, 98, 37);
		contentPane.add(button);
		**/
		omniMap = new OmniMap();
		omniMap.paintText(false);
		omniMap.setSize(1346, 705);
		omniMap.setFocusable(false);
		omniMap.setDoubleBuffered(false);
		omniMap.setEnabled(false);
		omniMap.setFocusTraversalKeysEnabled(false);
		omniMap.setLocation(10,10);
		
		
		contentPane.add(omniMap);
		
		JButton btnEtaOrionis = new JButton("ETA ORIONIS");
		btnEtaOrionis.setFocusable(false);
		btnEtaOrionis.setFocusTraversalKeysEnabled(false);
		btnEtaOrionis.setFocusPainted(false);
		btnEtaOrionis.setForeground(Color.PINK);
		btnEtaOrionis.setFont(new Font("OCR A Extended", Font.BOLD, 18));
		btnEtaOrionis.setBackground(Color.DARK_GRAY);
		btnEtaOrionis.setBounds(10, 716, 260, 45);
		contentPane.add(btnEtaOrionis);
		
		JButton btnSigmaOrionis = new JButton("SIGMA ORIONIS");
		btnSigmaOrionis.setFocusable(false);
		btnSigmaOrionis.setFocusTraversalKeysEnabled(false);
		btnSigmaOrionis.setFocusPainted(false);
		btnSigmaOrionis.setForeground(Color.PINK);
		btnSigmaOrionis.setFont(new Font("OCR A Extended", Font.BOLD, 18));
		btnSigmaOrionis.setBackground(Color.DARK_GRAY);
		btnSigmaOrionis.setBounds(1096, 716, 260, 45);
		contentPane.add(btnSigmaOrionis);
		
		JButton btnCentaurus = new JButton("CENTAURUS");
		btnCentaurus.setFocusable(false);
		btnCentaurus.setFocusTraversalKeysEnabled(false);
		btnCentaurus.setFocusPainted(false);
		btnCentaurus.setForeground(Color.PINK);
		btnCentaurus.setFont(new Font("OCR A Extended", Font.BOLD, 18));
		btnCentaurus.setBackground(Color.DARK_GRAY);
		btnCentaurus.setBounds(823, 716, 260, 45);
		contentPane.add(btnCentaurus);
		
		JButton btnKappaOrionis = new JButton("KAPPA ORIONIS");
		btnKappaOrionis.setFocusable(false);
		btnKappaOrionis.setFocusTraversalKeysEnabled(false);
		btnKappaOrionis.setFocusPainted(false);
		btnKappaOrionis.setForeground(Color.PINK);
		btnKappaOrionis.setFont(new Font("OCR A Extended", Font.BOLD, 18));
		btnKappaOrionis.setBackground(Color.DARK_GRAY);
		btnKappaOrionis.setBounds(283, 716, 260, 45);
		contentPane.add(btnKappaOrionis);
		
		setMouseListener(btnEtaOrionis,1,"ETA ORIONIS");
		setMouseListener(btnKappaOrionis,2,"KAPPA ORIONIS");
		setMouseListener(btnCentaurus,3,"CENTAURUS");
		setMouseListener(btnSigmaOrionis,4,"SIGMA ORIONIS");
	}
	
	/**
	 * By XU WEI DUO
	 * cette methode donne des ecouteurs de souris  a un boutton
	 * @param button, le boutton
	 * @param entry, le type de boutton
	 * @param title, le nom du boutton
	 */
	public void setMouseListener(JButton button, int entry, String title) {
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				//begin
				button.setFont(new Font("OCR A Extended", Font.BOLD, 12));
				button.setText("CLICKER POUR PASSER EN REVUE");
				button.setBackground(Color.PINK);
				button.setForeground(Color.DARK_GRAY);
				//fin
			}
			@Override
			public void mouseExited(MouseEvent e) {
				//begin
				button.setFont(new Font("OCR A Extended", Font.BOLD, 18));
				button.setText(title);
				button.setBackground(Color.DARK_GRAY);
				button.setForeground(Color.PINK);
				//fin
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				//begin
				button.setText("DOUBLE CLICK POUR CONFIRMER");
				if(e.getClickCount()==2)loadMap(entry,true);
				
				//fin
			}
		});
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//BEGIN
				
				if(chosen!=entry) {
					chosen = entry;
					loadMap(chosen, false);
				}
					
				//FIN
			}
		});
	}
	
	

	/**
	 * By XU WEI DUO
	 * cette methode permet a load un map deja cree
	 * @param entry, le choix du map
	 * @param proceed, si on charge un map pour vrai ou non
	 */
	public void loadMap(int entry, boolean proceed) {
		String choice="";
		chosen = entry;
		switch(chosen) {
		case 1:choice="ETA.map";
			break;
		case 2:choice="KAPPA.map";
			break;
		case 3:choice="CEN.map";
			break;
		case 4:choice="SIGMA.map";
			break;
		default:
			break;
		}
		//ClassLoader classLoader = getClass().getClassLoader();
		//File file = new File(classLoader.getResource(choice).getFile());
		ClassLoader loader = this.getClass().getClassLoader();
		InputStream inputStream = loader.getResourceAsStream(choice);
		if(proceed) {
			Fenetre3Voiture choseVehicule = new Fenetre3Voiture();
			choseVehicule.setLink(inputStream);
			dispose();
			choseVehicule.setVisible(true);
		}else {
			
			load(inputStream);
		}
		
		
	}
	
	/**
	 * By Xu Wei Duo
	 * cette methode permet a chrager un fichier save pour jouer ensuite
	 * @param input, le load path
	 */
	public void load(InputStream input) {
		try {
				ObjectInputStream fluxObj = new ObjectInputStream(input);
				MapSavior save = (MapSavior)fluxObj.readObject();
				fluxObj.close();
				//JOptionPane.showMessageDialog(null,"load");
				//JOptionPane.showMessageDialog(null,"["+save.getDSLoadPath()!=null+"]");
				//SiteOrion test = new SiteOrion();
				
				omniMap.paintText(false);
				//dispose();	
				//omniMap=new OmniMap();
				omniMap.setObjs(save.getObjs());
				omniMap.setObs(save.getObs());
				omniMap.setPlayer1(save.getPlayer1());
				omniMap.setPlayer2(save.getPlayer2());
				omniMap.setTimer(save.getTimerMin(), save.getTimerSec());
				omniMap.setReinforce(save.getReinforce()[0], save.getReinforce()[1]);
				if(save.getEle()!=null)omniMap.setEle(save.getEle()[0], save.getEle()[1]);
				if(save.getDSLoadPath()!=null) omniMap.setDSLoadPath(save.getDSLoadPath());
				if(save.getMapName()!=null) omniMap.setMap(save.getMapName());
				omniMap.paintText(false);
				omniMap.setFirst(true);
				
				omniMap.begin();
				//test.setFocusable(true);
				//test.requestFocusInWindow(); 
			
			   
				
			
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null,"File Not Found");
			// TODO Auto-generated catch block
			//e.printStackTrace();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null,"IOException");
			// TODO Auto-generated catch block
			//e.printStackTrace();
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,"ClassNotFoundException");
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
	}
	
	

}
