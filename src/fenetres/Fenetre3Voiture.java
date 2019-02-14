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
import javax.swing.JFileChooser;

import java.awt.event.MouseMotionAdapter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.URL;
import java.awt.event.MouseEvent;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import foundation.MapSavior;
import foundation.OmniMap;
import foundation.SiteOrion;

import java.awt.event.MouseAdapter;
import java.awt.event.ContainerAdapter;
import java.awt.event.ContainerEvent;
import javax.swing.SwingConstants;
import javax.swing.JToggleButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import vehicle.PanelVehiculeInfo;
import JPanelButton.PanelVehicule;
import JPanelButton.PanelVehiculeListener;
import JPanelButton.PanelButton;
/**
 * Classe permet de choisir les véhicules pour les joueurs 
 * @author Xu Wei Duo
 * @author alphascred
 *
 */
public class Fenetre3Voiture extends JFrame {

	
	private JPanel contentPane;
	private String[] players = new String[2];
	private int playerChoiceCounter = 1;
	private PanelVehicule pnlGenessis,pnlSinger, pnlMeditator, pnlDecoder, pnlCovenent, pnlReaper, pnlDrope;
	private PanelVehiculeInfo pnlBeta,pnlZeta;
	private InputStream loadPath;
	
    // Les noms de vehicules choisie 
	private String ChoixVehiculeBeta = null;
	private String ChoixVehiculeZeta = null;
	private String ChoixVehicule = null;

	// Les labels pour afficher les images 
	private JLabel picLabel;
	private JLabel picVehi= new JLabel();
	//Si le joueur 2 peut choisir
	private boolean player2 = false;
	private PanelButton panelButton;
	private JButton button;

	/**
	 * execute l'applications
	 */
	public static void main(String[] args) {
		Fenetre3Voiture frame = new Fenetre3Voiture();
		frame.setVisible(true);
				
	}

	/**
	 * creation de l'interface avec tous ses fonctions necessaires
	 */
	public Fenetre3Voiture() {
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

		// Creation de la map
		SiteOrion orion = new SiteOrion();
		
		JPanel panel_5 = new JPanel();
		panel_5.setBackground(Color.DARK_GRAY);
		panel_5.setBorder(new LineBorder(Color.WHITE, 5));
		panel_5.setBounds(getWidth()/2-683/2, 555, 683, 123);
		contentPane.add(panel_5);
		panel_5.setLayout(null);
		
		pnlGenessis = new PanelVehicule("Genessis");
		pnlGenessis.setBounds(220, 11, 64, 100);
		panel_5.add(pnlGenessis);
		
		pnlSinger = new PanelVehicule("Singer");
		pnlSinger.setBounds(435, 11, 47, 100);
		panel_5.add(pnlSinger);
		
		pnlCovenent = new PanelVehicule("Covenent");
		pnlCovenent.setBounds(527, 11, 63, 100);
		panel_5.add(pnlCovenent);
		
		pnlReaper = new PanelVehicule("Reaper");
		pnlReaper.setBounds(115, 11, 55, 100);
		panel_5.add(pnlReaper);
		
		pnlMeditator = new PanelVehicule("Meditator");
		pnlMeditator.setBounds(10, 11, 56, 100);
		panel_5.add(pnlMeditator);
		
		pnlDecoder = new PanelVehicule("Decoder");
		pnlDecoder.setBounds(619, 11, 54, 100);
		panel_5.add(pnlDecoder);
		
		pnlDrope = new PanelVehicule("Drope");
		pnlDrope.setBounds(329, 11, 56, 100);
		panel_5.add(pnlDrope);
		pnlDrope.addPanelVehiculeListener(new PanelVehiculeListener() {
			public void choiceVehicule(String type) {
				//begin
				setplayers(type);
				//fin
			}
		});
		pnlDecoder.addPanelVehiculeListener(new PanelVehiculeListener() {
			public void choiceVehicule(String type) {
				//begin
				setplayers(type);
				//fin
			}
		});
		pnlMeditator.addPanelVehiculeListener(new PanelVehiculeListener() {
			public void choiceVehicule(String type) {
				//begin
				setplayers(type);
				//fin
			}
		});
		pnlReaper.addPanelVehiculeListener(new PanelVehiculeListener() {
			public void choiceVehicule(String type) {
				//begin
				setplayers(type);
				//fin
			}
		});
		pnlCovenent.addPanelVehiculeListener(new PanelVehiculeListener() {
			public void choiceVehicule(String type) {
				//begin
				setplayers(type);
				//fin
			}
		});
		pnlSinger.addPanelVehiculeListener(new PanelVehiculeListener() {
			public void choiceVehicule(String type) {
				//begin
				setplayers(type);
				//fin
			}
		});
		pnlGenessis.addPanelVehiculeListener(new PanelVehiculeListener() {
			public void choiceVehicule(String type) {
				//begin
				setplayers(type);
				//fin
			}
		});
		
		pnlBeta = new PanelVehiculeInfo("JG",1,500,40,20,312, 746);
		pnlBeta.setType("Unknow");
		pnlBeta.setSize(312, 746);
		pnlBeta.setLocation(16, 11);
		contentPane.add(pnlBeta);
		
		pnlZeta = new PanelVehiculeInfo("JD", 1, 500, 40,20,312, 746);
		pnlZeta.setType("Unknow");
		pnlZeta.setSize(312, 746);
		pnlZeta.setLocation(1044, 11);
		contentPane.add(pnlZeta);
		
		//JButton button = new JButton("Begin");
		button = new JButton("VEUILLEZ CHOISIR DEUX VEHICULES");
		button.setEnabled(false);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//begin
				//if((players[0]==null || players[0].length()==0) || (players[1]==null || players[1].length()==0)) {
					//JOptionPane.showMessageDialog(null, "Please choose two vehicle"); 
					
					//button.setFont(new Font("OCR A Extended", Font.BOLD, 20));
				//}else {
					load(loadPath);
				//}
				//fin
			}
		});
		button.setFont(new Font("OCR A Extended", Font.BOLD, 20));
		button.setBackground(Color.PINK);
		button.setBounds(getWidth()/2-683/2, 700, 683, 57);
		contentPane.add(button);
		
		PanelButton pnCreatDS = new PanelButton();
		pnCreatDS.setBounds(342, 11, 683, 538);
		pnCreatDS.setBackground(Color.DARK_GRAY);
		contentPane.add(pnCreatDS);
		
		
		pnCreatDS.setType("DimStrike",1);
		pnCreatDS.setDSdim(180, 200);
		pnCreatDS.disabledDragged(true);
		pnCreatDS.setTextMode(true, "CHOISIR DEUX VEHICULES", "EBUC(/OuO`)>REPYH");
		
	}
	
	public void setLink(InputStream path) {
		loadPath = path;
	}
	
	/**
	 * By Xu Wei Duo
	 * cette methoder permet a choisir un panel de vehicule
	 * @param type, le nom du vehicule choisi
	 */
	public void selectVehiucle(String type) {
		if(type!=null) {
			switch(type) {
			case "Singer":	pnlSinger.select();
			break;
			case "Genessis": pnlGenessis.select();
			break;
			case "Drope": pnlDrope.select();
			break;
			case "Zeroinger": pnlDrope.select();
			break;
			case "Reaper": pnlReaper.select();
			break;
			case "Covenent": pnlCovenent.select();
			break;
			case "Meditator":  pnlMeditator.select();
			break;
			case "Decoder": pnlDecoder.select();
			break;
			
			}
		}
	}
	
	/**
	 * By Xu Wei Duo
	 * cette methode permet a savoir quel joueur a choisi quel vehicule
	 * @param player, le nom du joueur
	 */
	public void setplayers(String player) {
		if(playerChoiceCounter==3) {
			pnlSinger.deselect();
			pnlGenessis.deselect();
			pnlDrope.deselect();
			pnlReaper.deselect();
			pnlCovenent.deselect();
			pnlMeditator.deselect();
			pnlDecoder.deselect();
			
			players = new String[2];
			players[0] = player;
			pnlBeta.setType("");
			pnlZeta.setType("");
			playerChoiceCounter = 1;
			
			button.setText("VEUILLEZ CHOISIR DEUX VEHICULES");
			button.setEnabled(false);
			button.setFont(new Font("OCR A Extended", Font.BOLD, 20));
		}
		if(playerChoiceCounter == 1) {
			players[0] = player;
			pnlBeta.setType(player);
			playerChoiceCounter = 2;
			selectVehiucle(players[0]);
		}else if(playerChoiceCounter == 2){
			players[1]= player;
			pnlZeta.setType(player);
			playerChoiceCounter = 3;
			selectVehiucle(players[1]);
			
			button.setText("COMMENCER");
			button.setEnabled(true);
			button.setFont(new Font("OCR A Extended", Font.BOLD, 40));
		}
	}
	
	

	/**
	 * By Xu Wei Duo
	 * cette methode permet a chrager un fichier save pour jouer ensuite
	 * @param input,, le load path
	 */
	public void load(InputStream input) {
		try {
			
			 //FileInputStream save4D = new FileInputStream(path);
				ObjectInputStream fluxObj = new ObjectInputStream(input);
				MapSavior save = (MapSavior)fluxObj.readObject();
				fluxObj.close();
				//JOptionPane.showMessageDialog(null,"load");
				//JOptionPane.showMessageDialog(null,"["+save.getDSLoadPath()!=null+"]");
				SiteOrion test = new SiteOrion();
				
				dispose();	
				
				test.cubesMap.setObjs(save.getObjs());
				test.cubesMap.setObs(save.getObs());
				test.cubesMap.setPlayer1(players[0]);
				test.cubesMap.setPlayer2(players[1]);
				test.cubesMap.setTimer(save.getTimerMin(), save.getTimerSec());
				test.cubesMap.setReinforce(save.getReinforce()[0], save.getReinforce()[1]);
				if(save.getEle()!=null)test.cubesMap.setEle(save.getEle()[0], save.getEle()[1]);
				if(save.getDSLoadPath()!=null) test.cubesMap.setDSLoadPath(save.getDSLoadPath());
				if(save.getMapName()!=null)test.cubesMap.setMap(save.getMapName());
				
				test.setVisible(true);
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
