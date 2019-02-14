package fenetreModeSci;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.EventListenerList;

import foundation.OmniMap;
import foundation.SiteOrion;
import mapListeners.MissileListener;
import mapListeners.OmniMapListener;
import vecteur.Vecteur;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JTabbedPane;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JRootPane;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
/**
 * Classe de la fenetre scientique qui permet d'afficher les resusltats des forces, des collisions, des informations sur les vehicules
 * @author Imad Idrissi
 *
 */
public class FenetreScientifique extends JFrame {

	private JPanel contentPane;
	private int width = 300, height = 810;
	private JPanel pnlForces;
	private SiteOrion fenetreMap;
	private OmniMap panelMap;
	private JLabel lblVaKms;
	private JLabel lblAccVa;
	private JLabel lblObjetVa;
	private JLabel lblObjetVb;
	private JLabel lblAccVb;
	private JLabel lblVbKms;
	private JLabel vecteurV;
	private JLabel lblAccVec;
	private JLabel lbVecAcc;
	private JLabel lblVhiculeA;
	private JLabel lblVhiculeB;
	private JLabel lblModuleAForceMV;
	private JLabel lblVecteurAForceMa;
	private JLabel lblMissiles;
	private JLabel moduleV;
	private JLabel lbBlackDomainB;
	private JLabel lblObstacles;
	private JLabel lblModuleAForceE;
	private JLabel lblVecteurAForceE;
	private JLabel lblModuleChmpsResul;
	private JLabel chargeMissile;
	private JLabel lblDeltaT;
	private JLabel lblOuiNon;
	private JLabel charge;
	
	private final EventListenerList OBJS = new EventListenerList();
	private JSpinner spnRalenti;
	private boolean slowMo = false;
	private JLabel lblScientifique;
	private JButton btnPause;
	private JButton btnRalenti;
	//private label  label_4;

	/**
	 * Lancement de l'application 
	 */
	public static void main(String[] args) {
		FenetreScientifique frame = new FenetreScientifique();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(0);
	}

	/**
	 * Création de lafenetre avec ses composantes.
	 */
	public FenetreScientifique() {
		
		//setUndecorated(true);
		//getRootPane().setWindowDecorationStyle(JRootPane.NONE);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setResizable(false);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double width = screenSize.getWidth();
		double height = screenSize.getHeight();
		setBounds((int)(width/2-360/2), (int)(height/2-760/2), 360, 760);
		
		contentPane = new JPanel();
		contentPane.setBackground(new Color(204, 0, 51));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
		JLabel lblMode = new JLabel("Mode");
		lblMode.setForeground(new Color(255, 0, 51));
		lblMode.setFont(new Font("Times New Roman", Font.PLAIN, 35));
		lblMode.setBounds(82, 6, 189, 42);
		contentPane.add(lblMode);
		
		lblScientifique = new JLabel("SCIENTIFIQUE");
		lblScientifique.setForeground(Color.WHITE);
		lblScientifique.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblScientifique.setBounds(82, 14, 189, 77);
		contentPane.add(lblScientifique);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBackground(new Color(0, 51, 255));
		tabbedPane.setBounds(0, 170, 354, 514);
		contentPane.add(tabbedPane);
		
		// Les Objets sur la maps
		JPanel pnlObjet = new JPanel();
		pnlObjet.setBackground(new Color(255, 0, 51));
		tabbedPane.addTab("Objet", null, pnlObjet, null);
		pnlObjet.setLayout(null);
		
		lblVhiculeA = new JLabel("V\\u00E9hicule A");
		lblVhiculeA.setFont(new Font("Lucida Grande", Font.BOLD, 20));
		lblVhiculeA.setBounds(106, 8, 121, 16);
		pnlObjet.add(lblVhiculeA);
		
		lblVhiculeB = new JLabel("V\u00E9hicule B");
		lblVhiculeB.setFont(new Font("Lucida Grande", Font.BOLD, 20));
		lblVhiculeB.setBounds(106, 128, 121, 16);
		pnlObjet.add(lblVhiculeB);
		
		JLabel lblVitesse = new JLabel("Vitesse :");
		lblVitesse.setBounds(6, 32, 61, 16);
		pnlObjet.add(lblVitesse);
		
		JLabel lblAcceleration = new JLabel("Acceleration :");
		lblAcceleration.setBounds(6, 56, 86, 16);
		pnlObjet.add(lblAcceleration);
		
		JLabel lblObjetAccumul = new JLabel("Objet accumul\u00E9 :");
		lblObjetAccumul.setBounds(6, 80, 121, 16);
		pnlObjet.add(lblObjetAccumul);
		
		JLabel label_1 = new JLabel("Vitesse :");
		label_1.setBounds(6, 152, 61, 16);
		pnlObjet.add(label_1);
		
		JLabel label_2 = new JLabel("Acceleration :");
		label_2.setBounds(6, 176, 86, 16);
		pnlObjet.add(label_2);
		
		JLabel lblObjetAccumul_1 = new JLabel("Objet accumul\u00E9 :");
		lblObjetAccumul_1.setBounds(6, 200, 106, 16);
		pnlObjet.add(lblObjetAccumul_1);
		
		JLabel lblObstacle = new JLabel("Obstacle");
		lblObstacle.setFont(new Font("Lucida Grande", Font.BOLD, 20));
		lblObstacle.setBounds(106, 248, 121, 16);
		pnlObjet.add(lblObstacle);
		
		JLabel lblMissile_1 = new JLabel("Missile");
		lblMissile_1.setFont(new Font("Lucida Grande", Font.BOLD, 20));
		lblMissile_1.setBounds(106, 299, 121, 16);
		pnlObjet.add(lblMissile_1);
		
		JLabel lblNombreDobstacle = new JLabel("Nombre d'obstacle :\r\n\r\n");
		lblNombreDobstacle.setBounds(6, 272, 135, 16);
		pnlObjet.add(lblNombreDobstacle);
		
		JLabel lblNombreDeMissile = new JLabel("Nombre de missile :\n\n");
		lblNombreDeMissile.setBounds(6, 346, 142, 16);
		pnlObjet.add(lblNombreDeMissile);
		
		lblVaKms = new JLabel("0 Km/s");
		lblVaKms.setBounds(90, 32, 247, 16);
		pnlObjet.add(lblVaKms);
		
		lblVbKms = new JLabel("0 Km/s");
		lblVbKms.setBounds(90, 156, 247, 16);
		pnlObjet.add(lblVbKms);
		
		lblAccVa = new JLabel("0 Km/s*s");
		lblAccVa.setBounds(120, 58, 217, 16);
		pnlObjet.add(lblAccVa);
		
		lblAccVb = new JLabel("0 Km/s*s");
		lblAccVb.setBounds(120, 176, 217, 16);
		pnlObjet.add(lblAccVb);
		
		lblObjetVa = new JLabel("0 WDwarfs");
		lblObjetVa.setBounds(144, 84, 193, 16);
		pnlObjet.add(lblObjetVa);
		
		lblObjetVb = new JLabel("0 WDwarfs");
		lblObjetVb.setBounds(124, 200, 213, 16);
		pnlObjet.add(lblObjetVb);
		
		lblObstacles = new JLabel("0 Obstacles");
		
		lblObstacles.setBounds(144, 275, 120, 16);
		pnlObjet.add(lblObstacles);
		
		lblMissiles = new JLabel("0 Missiles");
		lblMissiles.setBounds(134, 346, 203, 16);
		pnlObjet.add(lblMissiles);
		
		JLabel vitesseMissle = new JLabel("Module vitesse :");
		vitesseMissle.setBounds(6, 375, 106, 16);
		pnlObjet.add(vitesseMissle);
		
		moduleV = new JLabel("0 Km/s");
		moduleV.setBounds(120, 375, 207, 16);
		pnlObjet.add(moduleV);
		
		JLabel lblModuleAcceleration = new JLabel("Vecteur acceleration :");
		lblModuleAcceleration.setBounds(6, 416, 135, 16);
		pnlObjet.add(lblModuleAcceleration);
		
		lbVecAcc = new JLabel("0 Km/s*s");
		lbVecAcc.setBounds(144, 419, 183, 16);
		pnlObjet.add(lbVecAcc);
		
		JLabel vecteurVitesse = new JLabel("Vecteur vitesse :");
		vecteurVitesse.setBounds(6, 388, 106, 16);
		pnlObjet.add(vecteurVitesse);
		
		vecteurV = new JLabel("[0,0] Km/s");
		vecteurV.setBounds(130, 388, 207, 16);
		pnlObjet.add(vecteurV);
		
		JLabel lblModuleAcc = new JLabel("Module acceleration :");
		lblModuleAcc.setBounds(6, 403, 135, 16);
		pnlObjet.add(lblModuleAcc);
		
		lblAccVec = new JLabel("[0,0] Km/s*s");
		lblAccVec.setBounds(144, 403, 193, 16);
		pnlObjet.add(lblAccVec);
		
		JTextPane textPane = new JTextPane();
		textPane.setBounds(229, 272, -35, 16);
		pnlObjet.add(textPane);
		
		JLabel lblTemps = new JLabel("Delta temps :");
		lblTemps.setBounds(6, 360, 86, 16);
		pnlObjet.add(lblTemps);
		
		lblDeltaT = new JLabel("0.0 Secondes");
		lblDeltaT.setBounds(97, 360, 240, 16);
		pnlObjet.add(lblDeltaT);
		
		JLabel lblNewLabel = new JLabel("Subit force :");
		lblNewLabel.setBounds(6, 431, 86, 16);
		pnlObjet.add(lblNewLabel);
		
		lblOuiNon = new JLabel("Non");
		lblOuiNon.setBounds(97, 431, 230, 16);
		pnlObjet.add(lblOuiNon);
		
		JLabel lblNewLabel_2 = new JLabel("Vesteur vitesse est en noir sur le missile");
		lblNewLabel_2.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblNewLabel_2.setBounds(6, 332, 299, 16);
		pnlObjet.add(lblNewLabel_2);
		
		pnlForces = new JPanel();
		pnlForces.setBackground(new Color(255, 0, 51));
		tabbedPane.addTab("Forces", null, pnlForces, null);
		pnlForces.setLayout(null);
		// Force
		JLabel lblForceMagntique = new JLabel("Force magn\u00E9tique");
		lblForceMagntique.setBounds(6, 6, 197, 25);
		lblForceMagntique.setFont(new Font("Lucida Grande", Font.BOLD, 20));
		pnlForces.add(lblForceMagntique);
		
		JLabel lblForcelectrique = new JLabel("Force \u00E9lectrique sur missile");
		lblForcelectrique.setFont(new Font("Lucida Grande", Font.BOLD, 20));
		lblForcelectrique.setBounds(6, 169, 302, 25);
		pnlForces.add(lblForcelectrique);
		
		JLabel lblModuleForceMVehiculeA = new JLabel("Module :");
		lblModuleForceMVehiculeA.setBounds(26, 81, 61, 16);
		pnlForces.add(lblModuleForceMVehiculeA);
		
		JLabel lblVecteurForceMVehiculeA = new JLabel("Vecteur :");
		lblVecteurForceMVehiculeA.setBounds(26, 125, 61, 16);
		pnlForces.add(lblVecteurForceMVehiculeA);
		
		JLabel lblModuleForceE = new JLabel("Module force :");
		lblModuleForceE.setBounds(26, 200, 94, 16);
		pnlForces.add(lblModuleForceE);
		
		JLabel lblVecteurForceE = new JLabel("Vecteur force:");
		lblVecteurForceE.setBounds(26, 244, 113, 16);
		pnlForces.add(lblVecteurForceE);
		
		JLabel lblModuleChamp = new JLabel("Module champ :");
		lblModuleChamp.setBounds(26, 385, 113, 16);
		pnlForces.add(lblModuleChamp);
		
		JLabel lblChargeMissile = new JLabel("Charge missile :");
		lblChargeMissile.setBounds(26, 288, 113, 16);
		pnlForces.add(lblChargeMissile);
		
		lblModuleAForceMV = new JLabel("0 N");
		lblModuleAForceMV.setBounds(86, 81, 222, 16);
		pnlForces.add(lblModuleAForceMV);
		
		lblVecteurAForceMa = new JLabel("(0,0) N");
		lblVecteurAForceMa.setBounds(86, 125, 222, 16);
		pnlForces.add(lblVecteurAForceMa);
		
		lblModuleAForceE = new JLabel("0 N");
		lblModuleAForceE.setBounds(120, 200, 213, 16);
		pnlForces.add(lblModuleAForceE);
		
		lblVecteurAForceE = new JLabel("(0,0) N");
		lblVecteurAForceE.setBounds(120, 244, 197, 16);
		pnlForces.add(lblVecteurAForceE);
		
		chargeMissile = new JLabel("0 uC");
		chargeMissile.setBounds(131, 288, 222, 16);
		pnlForces.add(chargeMissile);
		
		lblModuleChmpsResul = new JLabel("0 N/C");
		lblModuleChmpsResul.setBounds(156, 385, 177, 16);
		pnlForces.add(lblModuleChmpsResul);
		
		JLabel lbB = new JLabel("Intensite (B):");
		lbB.setBounds(26, 37, 94, 16);
		pnlForces.add(lbB);
		
		lbBlackDomainB = new JLabel("0 Tesla");
		lbBlackDomainB.setBounds(120, 37, 188, 16);
		pnlForces.add(lbBlackDomainB);
		
		JLabel lblVecteurForceEst = new JLabel("Vecteur force r\u00E9sultant est en rouge sur le missile");
		lblVecteurForceEst.setFont(new Font("Lucida Grande", Font.BOLD, 12));
		lblVecteurForceEst.setBounds(6, 315, 323, 16);
		pnlForces.add(lblVecteurForceEst);
		
		JLabel lblNewLabel_1 = new JLabel("Charge particule :");
		lblNewLabel_1.setBounds(26, 429, 113, 16);
		pnlForces.add(lblNewLabel_1);
		
		charge = new JLabel("0 uC");
		charge.setBounds(156, 429, 177, 16);
		pnlForces.add(charge);
		
		JLabel lblChamplectrique = new JLabel("Champ \u00E9lectrique total");
		lblChamplectrique.setFont(new Font("Lucida Grande", Font.BOLD, 20));
		lblChamplectrique.setBounds(6, 354, 302, 25);
		pnlForces.add(lblChamplectrique);
		
		JButton btnCommencer = new JButton("Reprendre");
		btnCommencer.setEnabled(false);
		btnCommencer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//begin
				begin();
				btnCommencer.setEnabled(false);
				btnPause.setEnabled(true);
				btnRalenti.setEnabled(true);
				spnRalenti.setEnabled(true);
				//fin
			}
		});
		btnCommencer.setFont(new Font("Dialog", Font.BOLD, 12));
		btnCommencer.setBounds(39, 72, 276, 23);
		contentPane.add(btnCommencer);
		
		btnPause = new JButton("Pause");
		btnPause.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//begin
				stop();
				btnCommencer.setEnabled(true);
				btnPause.setEnabled(false);
				btnRalenti.setEnabled(false);
				spnRalenti.setEnabled(false);
				//fin
			}
		});
		btnPause.setFont(new Font("Dialog", Font.BOLD, 12));
		btnPause.setBounds(39, 102, 114, 28);
		contentPane.add(btnPause);
		
		JPanel panel = new JPanel();
		panel.setBounds(165, 102, 150, 28);
		contentPane.add(panel);
		panel.setLayout(null);
		
		btnRalenti = new JButton("Ralentir");
		btnRalenti.setBounds(0, 0, 114, 28);
		panel.add(btnRalenti);
		btnRalenti.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//begin
				if(slowMo) {
					slowMo = false;
					btnRalenti.setText("Ralentir");
					spnRalenti.setEnabled(true);
				}else {
					slowMo = true;
					btnRalenti.setText("Reprendre");
					spnRalenti.setEnabled(false);
					
				}
				slowMo();
				//fin
			}
		});
		btnRalenti.setFont(new Font("Dialog", Font.BOLD, 12));
		
		spnRalenti = new JSpinner();
		spnRalenti.setBounds(116, 3, 29, 22);
		panel.add(spnRalenti);
		spnRalenti.setModel(new SpinnerNumberModel(1, 1, 4, 1));
		
		JButton btnExit = new JButton("Fermer");
		btnExit.setFont(new Font("Dialog", Font.BOLD, 12));
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//begin
				closeWin();
				//fin
			}
		});
		btnExit.setBounds(10, 695, 334, 26);
		contentPane.add(btnExit);
		
		JButton btnSciMode = new JButton("Information sur sc\u00E8ne ");
		btnSciMode.setFont(new Font("Dialog", Font.BOLD, 12));
		btnSciMode.setBounds(39, 136, 276, 23);
		contentPane.add(btnSciMode);
		btnSciMode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//begin
				modeSci();
				//fin
			}
		});
		
		
	}
	/**
	 * Liste d'�couteur
	 * @param objEcout
	 */
	public void addFenetreModeSciListener(FenetreModeSciListener objEcout) {
		OBJS.add(FenetreModeSciListener.class, objEcout);
	}
	/**
	 * Ecouteur pour fermer la fenetre
	 */
	public void closeWin() {
		for(FenetreModeSciListener ecout: OBJS.getListeners(FenetreModeSciListener.class)) {
			ecout.winClose();
		}
	}
	/**
	 * Ecouteur pour commencer 
	 */
	
	public void begin() {
		for(FenetreModeSciListener ecout: OBJS.getListeners(FenetreModeSciListener.class)) {
			ecout.begin();
		}
	}
	/**
	 * Ecouteur pour arreter le jeu
	 */
	public void stop() {
		for(FenetreModeSciListener ecout: OBJS.getListeners(FenetreModeSciListener.class)) {
			ecout.stop();
		}
	}
	/**
	 * Ecouteur pour mettre le jeu en slow motions
	 */
	
	public void slowMo() {
		for(FenetreModeSciListener ecout: OBJS.getListeners(FenetreModeSciListener.class)) {
			ecout.slowMo( (int)spnRalenti.getValue());
		}
	}
	/*
	 * Ecouteur pour avoir les donnes 
	 */
	public void modeSci() {
		
		for(FenetreModeSciListener ecout: OBJS.getListeners(FenetreModeSciListener.class)) {
			ecout.modeSci();
		}
	}
	/**
	 * M�thode qui montre les informations des v�hicules
	 * @param beta Prend la voiture Beta
	 * @param zeta Prend la voiture Zeta
	 */
	public void setVehiculeInfo(Object[] beta, Object[] zeta) {
		lblVhiculeA.setText("PL: "+(String)beta[0]);
		lblVaKms.setText(String.format("%.3f",(double)beta[1]/1000)+"km/s");
		lblAccVa.setText(String.format("%.3f",(double)beta[2])+"km/s^2");
		lblObjetVa.setText((int)beta[3]+" WDwarfs");
		//lblVehiculeA.setText("PL: "+(String)beta[0]);
		//lblModuleAForceMVa.setText((double)beta[4]+"N");
		//Point.Double vecBeta = (Point.Double)beta[5];
		//lblVecteurAForceMa.setText("("+vecBeta.getX()+","+vecBeta.getY()+") N");
		
		lblVhiculeB.setText("PR: "+(String)zeta[0]);
		lblVbKms.setText(String.format("%.3f",(double)zeta[1]/1000)+"km/s");
		lblAccVb.setText(String.format("%.3f",(double)zeta[2])+"km/s^2");
		lblObjetVb.setText((int)zeta[3]+" WDwarfs");
		//lblVehiculeB.setText("PL: "+(String)zeta[0]);
		//lblModuleAForceMVb.setText((double)zeta[4]+"N");
		//Point.Double vecZeta = (Point.Double)zeta[5];
		//lblVecteurAForceMb.setText("("+vecZeta.getX()+","+vecZeta.getY()+") N");
		
		
		/**
		info[0] = veh.getVehiculeName();
		info[1] = veh.getSpeed();
		info[2] = veh.getAcc();
		info[3] = veh.getTailDim();
		info[4] = veh.getVecMov().module();
		info[5] = new Point.Double(veh.getVecMov().getX(), veh.getVecMov().getY());
		**/
	}
	/**
	 * M�thode qui permet d'avoir les informations sur les missiles 
	 * @param infos Information sur les missiles en forme de liste
	 */
	public void setMissleInfo(Object[] infos) {
		if((double)infos[2] != 0 ) {
			moduleV.setText(String.format("%.3f",infos[2])+"km/s");
			Vecteur vecVit = (Vecteur)infos[3];
			vecteurV.setText(String.format("%.3f",vecVit.getX())+","+ String.format("%.3f",vecVit.getY()) + " Km/s");
			Vecteur vecAcc = (Vecteur)infos[1];
			lblAccVec.setText(String.format("%.3f",vecAcc.getX())+","+String.format("%.3f",vecAcc.getY())+ "kml/s^2 ");
			lbVecAcc.setText(String.format("%.3f",infos[0])+"kml/s^2");
			lblDeltaT.setText(String.format("%.3f",(double)infos[5])+ " Secondes");
			if((Boolean)infos[4]) {
				lblOuiNon.setText("Oui");
			}else {
				lblOuiNon.setText("Non");
			}
			Vecteur vec = (Vecteur)infos[7];
			if(vec!=null) {
				lblModuleAForceE.setText(String.format("%.3f",vec.module())+" N");
				lblVecteurAForceE.setText(String.format("%.3f",vec.getX())+","+String.format("%.3f",vec.getY()) + " N");
			}
			chargeMissile.setText(String.format("%.3f",(double)infos[8])+" uC");
			
		}else {
			lblDeltaT.setText("Aucun missile selectionn\u00E9");
			lblAccVec.setText("Aucun missile selectionn\u00E9");
			lbVecAcc.setText("Aucun missile selectionn\u00E9");
			vecteurV.setText("Aucun missile selectionn\u00E9");
			moduleV.setText("Aucun missile selectionn\u00E9");
			lblOuiNon.setText("Aucun missile selectionn\u00E9");
			lblModuleAForceE.setText("Aucun missile selectionn\u00E9");
			lblVecteurAForceE.setText("Aucun missile selectionn\u00E9");
			chargeMissile.setText("Aucun missile selectionn\u00E9");
		}
		if((int)infos[6] != 0) {
			lblMissiles.setText(infos[6]+" Missiles");
		}else {
			lblMissiles.setText(0+" Missiles");
		}
		
		
		/**
		Object[] missleInfo = new Object[6];
			missleInfo[0] = canon.getAcc();
			missleInfo[1] = canon.getAccVec();
			missleInfo[2] = canon.getVitesseModule();
			missleInfo[3] = canon.getVitesseVec();
			missleInfo[4] = canon.getSubitUneForceElectrique();
			missleInfo[5] = canon.getDeltaT();
		 */
	}
	
	/**
	 * M�thode qui permet d'avoir les informations sur le champs magn�tique 
	 * @param module
	 * @param vec
	 */
	public void setBDInfo(double module, Vecteur vec) {
		
		if(vec!=null) {
			lbBlackDomainB.setText(String.format("%.3f",module)+" Tesla");
			lblModuleAForceMV.setText(String.format("%.3f",vec.module())+"N");
			lblVecteurAForceMa.setText("("+String.format("%.3f",vec.getX())+","+String.format("%.3f",vec.getY())+") N"); 
		}else {
			lbBlackDomainB.setText(0+" Tesla");
			lblModuleAForceMV.setText(0+"n");
			lblVecteurAForceMa.setText("("+0+","+0+") N");
		}
		
	}
	/**
	 * M�thode qui permet de savoir le nombre d'obstacle pr�sent 
	 * @param nb Nombre d'obsatcle
	 */

	public void setObsInfo(int nb) {
		lblObstacles.setText(nb+" Obstacles");
	}
	/**
	 * M�thode qui permet d'avoir les informations sur le champs �lectrique 
	 * @param CEInfo
	 */
	public void setCEInfo(Object[] CEInfo) {
		lblModuleChmpsResul.setText(String.format("%.6f",Math.abs((double)CEInfo[0]))+"N/C");
		charge.setText((double)CEInfo[1]+"uC");
	}
}
