package foundation;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import JPanelButton.PanelButton;
import fourthDim.CREATOR;
import fourthDim.Forme4D;
import vehicle.PanelVehiculeInfo;
import JPanelButton.PanelButtonListener;
import java.awt.Color;
import java.awt.Dimension;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.event.ChangeEvent;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import java.awt.event.MouseWheelListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.awt.event.MouseWheelEvent;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import JPanelButton.PanelVehicule;
import JPanelButton.PanelVehiculeListener;
import JPanelButton.panelMap;
import JPanelButton.PanelMapListener;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.border.LineBorder;
import javax.swing.JTabbedPane;
import java.awt.Font;

/**
 * cette classe cree l'interface
 * @author XU WEI DUO
 *
 */
public class MapCreator extends JFrame {

	private JPanel contentPane;
	private String[] players = new String[2];
	private int playerChoiceCounter = 1;
	private PanelVehicule pnlGenessis,pnlSinger, pnlMeditator, pnlDecoder, pnlCovenent, pnlReaper, pnlDrope;
	private PanelVehiculeInfo pnlBeta,pnlZeta;
	private panelMap pnlAbandon, pnlCloud, pnlTerra;
	private JSpinner spnTimerMin, spnTimerSec;
	private JFormattedTextField spnEditable;
	private JButton btnBegin;
	private panelMap pnlCentaurus;
	/**
	 * execute l'application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MapCreator frame = new MapCreator();
					frame.setVisible(true);
					//frame.getContentPane().setBackground(new Color(1.0f,1.0f,1.0f,0.0f));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}

	/**
	 * creation de l'interface avec tous ses fonctions necessaires
	 */
	public MapCreator() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setUndecorated(true);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double width = screenSize.getWidth();
		double height = screenSize.getHeight();
		//setBounds((int)(width/2-713/2), (int)(height/2-583/2), 691, 595);
		int widthPanel = 1366;
		int heightPanel = 768;
		setBounds((int)(width/2-widthPanel/2), (int)(height/2-heightPanel/2), widthPanel, heightPanel);
		//setUndecorated(true);
		//setBackground(new Color(0.0f,0.0f,0.0f,0.0f));
		
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new LineBorder(Color.PINK, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		DummyMap dummyMap = new DummyMap();
		dummyMap.setLocation(getWidth()/2-dummyMap.getWidth()/2, 151);
		//dummyMap.setBounds(10, 10, 430, 205);
		contentPane.add(dummyMap);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBackground(Color.WHITE);
		panel_6.setBorder(new LineBorder(Color.PINK, 5));
		panel_6.setBounds(102, 521, 376, 152);
		contentPane.add(panel_6);
		panel_6.setLayout(null);
		
		
		JPanel pnlReinforce = new JPanel();
		pnlReinforce.setBackground(Color.WHITE);
		pnlReinforce.setBounds(6, 68, 168, 77);
		panel_6.add(pnlReinforce);
		//pnlReinforce.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "missle selection", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlReinforce.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "REINFORCE", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlReinforce.setLayout(null);
		
		JComboBox cmbReinforce = new JComboBox();
		cmbReinforce.setBounds(6, 17, 152, 21);
		pnlReinforce.add(cmbReinforce);
		//cmbReinforce.setModel(new DefaultComboBoxModel(new String[] {"random generation", "type 1: TriMissle", "type 2: TrackMissle"}));
		cmbReinforce.setModel(new DefaultComboBoxModel(new String[] {"g\u00E9n\u00E9ration al\u00E9atoire", "type 1: Tri-Missle", "type 2: Track-Missle"}));
		cmbReinforce.setSelectedIndex(0);
		
		JSpinner spnReinforce = new JSpinner();
		spnReinforce.setModel(new SpinnerNumberModel(4, 1, 20, 1));
		spnReinforce.setBounds(111, 45, 47, 22);
		spnEditable=((JSpinner.DefaultEditor)spnReinforce.getEditor()).getTextField(); 
		spnEditable.setEditable(false); 
		pnlReinforce.add(spnReinforce);
		
		//JLabel lbSec = new JLabel("CoolDown (Sec)");
		JLabel lbSec = new JLabel("COOLDOWN (Sec)");
		lbSec.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lbSec.setBounds(6, 48, 95, 15);
		pnlReinforce.add(lbSec);
		
		JPanel pnlTimer = new JPanel();
		pnlTimer.setBackground(Color.WHITE);
		pnlTimer.setBounds(6, 16, 168, 46);
		panel_6.add(pnlTimer);
		//pnlTimer.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Round Timer", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlTimer.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "La dur\u00E9e du jeu", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlTimer.setLayout(null);
		
		spnTimerMin = new JSpinner();
		spnTimerMin.setBounds(6, 17, 46, 22);
		pnlTimer.add(spnTimerMin);
		spnTimerMin.setModel(new SpinnerNumberModel(2, 0, 10, 1));
		spnEditable=((JSpinner.DefaultEditor)spnTimerMin.getEditor()).getTextField(); 
		spnEditable.setEditable(false); 
		
		spnTimerSec = new JSpinner();
		spnTimerSec.setBounds(82, 17, 47, 22);
		pnlTimer.add(spnTimerSec);
		spnTimerSec.setModel(new SpinnerNumberModel(0, 0, 60, 1));
		spnEditable=((JSpinner.DefaultEditor)spnTimerSec.getEditor()).getTextField(); 
		spnEditable.setEditable(false); 
		
		JLabel lblMinutes = new JLabel("Min");
		lblMinutes.setBounds(56, 20, 28, 15);
		pnlTimer.add(lblMinutes);
		
		JLabel lblSeconds = new JLabel("Sec");
		lblSeconds.setBounds(134, 20, 28, 15);
		pnlTimer.add(lblSeconds);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(184, 16, 180, 129);
		panel_6.add(panel_1);
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "WHITE DWARFS", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setLayout(null);
		
		PanelButton pnWDwarf = new PanelButton();//dummyMap.getX(),dummyMap.getY(),dummyMap.getWidth(),dummyMap.getHeight());
		pnWDwarf.setBounds(10, 21, 100, 100);
		panel_1.add(pnWDwarf);
		pnWDwarf.addObjListener(new PanelButtonListener() {
			public void selected(String type, int choice,String extra) {
				//begin
				if(dummyMap.mouseEntered()) {
					dummyMap.setObj(type,choice,extra);
					pnWDwarf.setDragged(false);
				}
				//fin
			}
		});
		pnWDwarf.setType("WDwarf");
		
		JSlider sldWDnbs = new JSlider();
		sldWDnbs.setBackground(Color.WHITE);
		sldWDnbs.setPaintTicks(true);
		sldWDnbs.setMinorTickSpacing(100);
		sldWDnbs.addMouseWheelListener(new MouseWheelListener() {
			public void mouseWheelMoved(MouseWheelEvent e) {
				//begin
				sldWDnbs.setValue(sldWDnbs.getValue()+10*-e.getWheelRotation());
				//sldWDnbs.setValue(sldWDnbs.getValue()+10*e.getWheelRotation());
				//fin
			}
		});
		sldWDnbs.setBounds(120, 21, 50, 100);
		panel_1.add(sldWDnbs);
		sldWDnbs.setMinimum(200);
		sldWDnbs.setPaintLabels(true);
		sldWDnbs.setMajorTickSpacing(200);
		sldWDnbs.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				//begin
				pnWDwarf.setType("WDwarf",sldWDnbs.getValue());
				//fin
			}
		});
		sldWDnbs.setMaximum(800);
		sldWDnbs.setValue(400);
		sldWDnbs.setOrientation(SwingConstants.VERTICAL);
		dummyMap.begin();
		
		//JButton btnBegin = new JButton("Begin");
		btnBegin = new JButton("VEUILLEZ CHOISIR DEUX VEHICULES");
		btnBegin.setEnabled(false);
		btnBegin.setFont(new Font("OCR A Extended", Font.BOLD, 20));
		btnBegin.setBackground(Color.PINK);
		btnBegin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//begin
				//if((players[0]==null || players[0].length()==0) || (players[1]==null || players[1].length()==0)) {
					//JOptionPane.showMessageDialog(null, "Please choose two vehicle"); 
					//btnBegin.setText("VEUILLEZ CHOISIR DEUX VEHICULES"); 
				//}else{
					dispose();
					SiteOrion test = new SiteOrion();
					
					test.cubesMap.setObjs(dummyMap.getObjs());
					test.cubesMap.setObs(dummyMap.getObs());
					test.cubesMap.setPlayer1(players[0]);
					test.cubesMap.setPlayer2(players[1]);
					test.cubesMap.setTimer((int)spnTimerMin.getValue(),(int)spnTimerSec.getValue());
					test.cubesMap.setReinforce(cmbReinforce.getSelectedIndex(), (int)spnReinforce.getValue());
					if(dummyMap.getEle()!=null)test.cubesMap.setEle(dummyMap.getEle()[0], dummyMap.getEle()[1]);
					if(dummyMap.getDSLoadPath()!=null) test.cubesMap.setDSLoadPath(dummyMap.getDSLoadPath());
					if(dummyMap.getMapName()!=null)test.cubesMap.setMap(dummyMap.getMapName());
					
					test.setVisible(true);
					//test.setFocusable(true);
					//test.requestFocusInWindow();
					
				//}
				
				//fin
			}
		});
		btnBegin.setBounds(343, 684, 683, 57);
		contentPane.add(btnBegin);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBackground(Color.DARK_GRAY);
		panel_5.setBorder(new LineBorder(Color.WHITE, 5));
		panel_5.setBounds(342, 27, 683, 123);
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
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.LEFT);
		tabbedPane.setBackground(Color.LIGHT_GRAY);
		tabbedPane.setBounds(478, 521, 795, 152);
		contentPane.add(tabbedPane);
		
		JPanel panel_7 = new JPanel();
		panel_7.setBackground(Color.WHITE);
		tabbedPane.addTab("OBJETS", null, panel_7, null);
		panel_7.setBorder(new LineBorder(Color.PINK, 5));
		panel_7.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(10, 11, 223, 129);
		panel_7.add(panel);
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "DIMENTIONALITY-REDUCTION STRIKE", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setLayout(null);
		
		
		
		PanelButton pnCreatDS = new PanelButton();//dummyMap.getX(),dummyMap.getY(),dummyMap.getWidth(),dummyMap.getHeight());
		pnCreatDS.setBounds(10, 19, 100, 100);
		panel.add(pnCreatDS);
		
		//JButton btnCreat = new JButton("CREATOR");
		JButton btnCreat = new JButton("CREATEUR");
		btnCreat.setForeground(Color.DARK_GRAY);
		btnCreat.setFont(new Font("ËÎÌו", Font.PLAIN, 11));
		btnCreat.setBackground(Color.PINK);
		btnCreat.setBounds(120, 63, 93, 23);
		panel.add(btnCreat);
		
		JSpinner spnChoiceDS = new JSpinner();
		
		spnChoiceDS.setBounds(120, 31, 93, 22);
		panel.add(spnChoiceDS);
		spnChoiceDS.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				//begin
				pnCreatDS.setDSChoice((int)spnChoiceDS.getValue());
				//fin
			}
		});
		spnChoiceDS.setModel(new SpinnerNumberModel(1, 1, 3, 1));
		spnEditable=((JSpinner.DefaultEditor)spnChoiceDS.getEditor()).getTextField(); 
		spnEditable.setEditable(false); 
		
		//JButton btnLoad = new JButton("LOAD");
		JButton btnLoad = new JButton("CHARGER");
		btnLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//JOptionPane.showMessageDialog(null, "not file found");
				pnCreatDS.loadForme4D();
			}
		});
		btnLoad.setBounds(120, 96, 93, 23);
		panel.add(btnLoad);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		panel_2.setBounds(243, 11, 112, 128);
		panel_7.add(panel_2);
		panel_2.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "BLACK DOMAIN", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_2.setLayout(null);
		
		PanelButton pnlCreatBD = new PanelButton();//dummyMap.getX(),dummyMap.getY(),dummyMap.getWidth(),dummyMap.getHeight());
		pnlCreatBD.setBounds(6, 17, 100, 100);
		panel_2.add(pnlCreatBD);
		pnlCreatBD.addObjListener(new PanelButtonListener() {
			public void selected(String type, int choice,String extra) {
				//begin
				if(dummyMap.mouseEntered()) {
					dummyMap.setObj(type,choice,extra);
					pnlCreatBD.setDragged(false);
				}
				//fin
			}
		});
		pnlCreatBD.setType("BlackDomain");
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.WHITE);
		panel_3.setBounds(601, 11, 112, 129);
		panel_7.add(panel_3);
		panel_3.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Obstacle", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_3.setLayout(null);
		
		PanelButton pnlObstacles = new PanelButton();//dummyMap.getX(),dummyMap.getY(),dummyMap.getWidth(),dummyMap.getHeight());
		pnlObstacles.setBounds(6, 17, 100, 100);
		panel_3.add(pnlObstacles);
		pnlObstacles.addObjListener(new PanelButtonListener() {
			public void selected(String type, int choice,String extra) {
				//begin
				if(dummyMap.mouseEntered()) {
					dummyMap.setObj(type,choice,extra);
					pnlObstacles.setDragged(false);
				}
				//fin
			}
		});
		pnlObstacles.setType("Obstacle");
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(Color.WHITE);
		panel_4.setBounds(365, 11, 226, 129);
		panel_7.add(panel_4);
		//panel_4.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Electric Field Creator", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_4.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Cr\u00E9ateur de champ \u00E9l\u00E9ctrique", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_4.setLayout(null);
		
		//JButton btnField = new JButton("Creat electric field");
		JButton btnField = new JButton("Cr\u00E9er un champ \u00E9l\u00E9ctrique");
		btnField.setFocusable(false);
		btnField.setFont(new Font("OCR A Extended", Font.PLAIN, 10));
		btnField.setBounds(8, 17, 208, 27);
		panel_4.add(btnField);
		
		JPanel panel_9 = new JPanel();
		panel_9.setBackground(Color.WHITE);
		panel_9.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "La charge du champ", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_9.setBounds(8, 48, 208, 70);
		panel_4.add(panel_9);
		panel_9.setLayout(null);
		
		JSlider sldField = new JSlider();
		sldField.setBounds(10, 20, 188, 46);
		panel_9.add(sldField);
		sldField.setBackground(Color.WHITE);
		sldField.setSnapToTicks(true);
		sldField.setPaintTicks(true);
		sldField.setValue(10);
		sldField.setPaintLabels(true);
		sldField.setMinorTickSpacing(5);
		sldField.setMaximum(30);
		sldField.setMinimum(-30);
		sldField.setMajorTickSpacing(10);
		sldField.addMouseWheelListener(new MouseWheelListener() {
			public void mouseWheelMoved(MouseWheelEvent e) {
				sldField.setValue(sldField.getValue()+10*e.getWheelRotation());
			}
		});
		btnField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//begin
				dummyMap.setObj("Field", sldField.getValue(), "");
				//fin
			}
		});
		btnCreat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//BEGIN
				CREATOR formeCreator = new CREATOR();
				formeCreator.setVisible(true);
				//FIN
			}
		});
		pnCreatDS.addObjListener(new PanelButtonListener() {
			public void selected(String type, int choice,String extra) {
				//begin
				if(dummyMap.mouseEntered()) {
					dummyMap.setObj(type,choice,extra);			

					pnCreatDS.setDragged(false);
				}
				//fin
			}
		});
		pnCreatDS.setType("DimStrike",1);
		
		JPanel panel_8 = new JPanel();
		panel_8.setBackground(Color.DARK_GRAY);
		tabbedPane.addTab("MAPS", null, panel_8, null);
		panel_8.setBorder(new LineBorder(Color.PINK, 5));
		panel_8.setLayout(null);
		
		pnlAbandon = new panelMap("Abandon");
		pnlAbandon.setBounds(10, 11, 227, 119);
		panel_8.add(pnlAbandon);
		
		pnlCloud = new panelMap("Cloud");
		pnlCloud.setBounds(247, 11, 227, 119);
		panel_8.add(pnlCloud);
		
		pnlTerra = new panelMap("Terra");
		pnlTerra.setBounds(484, 11, 227, 119);
		panel_8.add(pnlTerra);
		
		JPanel panel_10 = new JPanel();
		panel_10.setBackground(Color.DARK_GRAY);
		tabbedPane.addTab("EXTRA", null, panel_10, null);
		panel_10.setLayout(null);
		
		pnlCentaurus = new panelMap("Centaurus");
		pnlCentaurus.addPanelMapListener(new PanelMapListener() {
			public void choiceMap(String type) {
				//begin
				dummyMap.setMap(type);
				selectMap("Centaurus");
				//fin
			}
		});
		pnlCentaurus.setLocation(253, 13);
		panel_10.add(pnlCentaurus);
		pnlTerra.addPanelMapListener(new PanelMapListener() {
			public void choiceMap(String type) {
				//begin
				dummyMap.setMap(type);
				selectMap("Terra");
				//fin
			}
		});
		pnlCloud.addPanelMapListener(new PanelMapListener() {
			public void choiceMap(String type) {
				//begin
				dummyMap.setMap(type);
				selectMap("Cloud");
				//fin
			}
		});
		pnlAbandon.addPanelMapListener(new PanelMapListener() {
			public void choiceMap(String type) {
				//begin
				dummyMap.setMap(type);
				selectMap("Abandon");
				//fin
			}
		});
		
		//JButton btnSaveMap = new JButton("SAVE");
		JButton btnSaveMap = new JButton("Sauvegarder");
		//btnSaveMap.setFont(new Font("OCR A Extended", Font.BOLD, 40));
		btnSaveMap.setFont(new Font("OCR A Extended", Font.BOLD, 20));
		btnSaveMap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//begin
				MapSavior save = new MapSavior();
				save.setObjs(dummyMap.getObjs());
				save.setObs(dummyMap.getObs());
				save.setPlayer1(players[0]);
				save.setPlayer2(players[1]);
				save.setTimer((int)spnTimerMin.getValue(),(int)spnTimerSec.getValue());
				save.setReinforce(cmbReinforce.getSelectedIndex(), (int)spnReinforce.getValue());
				//JOptionPane.showMessageDialog(null,"["+(dummyMap.getDSLoadPath()!="")+"]");
				if(dummyMap.getEle()!=null)save.setEle(dummyMap.getEle()[0], dummyMap.getEle()[1]);
				if(dummyMap.getDSLoadPath()!=null) save.setDSLoadPath(dummyMap.getDSLoadPath());
				if(dummyMap.getMapName()!=null)save.setMapName(dummyMap.getMapName());
				save(save);
				//fin
			}
		});
		btnSaveMap.setBounds(111, 681, 222, 60);
		contentPane.add(btnSaveMap);
		
		//JButton btnLoadMap = new JButton("LOAD\r\n");
		JButton btnLoadMap = new JButton("Charger");
		//btnLoadMap.setFont(new Font("OCR A Extended", Font.BOLD, 40));
		btnLoadMap.setFont(new Font("OCR A Extended", Font.BOLD, 20));
		btnLoadMap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//begin
				load();
				//fin
			}
		});
		btnLoadMap.setBounds(1038, 683, 222, 58);
		contentPane.add(btnLoadMap);
		
		//pnlBeta = new PanelVehiculeInfo("PL",2,300,30,14,231, 484);
		pnlBeta = new PanelVehiculeInfo("JG",2,300,30,14,231, 484);
		pnlBeta.setType("Unknow");
		pnlBeta.setSize(231, 484);
		pnlBeta.setLocation(102, 26);
		contentPane.add(pnlBeta);
		
		//pnlZeta = new PanelVehiculeInfo("PR",2,300,30,14,231, 484);
		pnlZeta = new PanelVehiculeInfo("JD",2,300,30,14,231, 484);
		pnlZeta.setType("Unknow");
		pnlZeta.setSize(237, 484);
		pnlZeta.setLocation(1036, 26);
		contentPane.add(pnlZeta);
	
	
		
	}
	
	/**
	 * cette methode permet a choisir un seul map a la fois
	 * @param type, le nom du map choisi
	 */
	private void selectMap(String type) {
		pnlAbandon.drawBorder(false);
		pnlCloud.drawBorder(false);
		pnlTerra.drawBorder(false);
		pnlCentaurus.drawBorder(false);
		
		if(type!=null) {
			switch(type) {
			case "Abandon":	pnlAbandon.drawBorder(true);
			break;
			case "Cloud": pnlCloud.drawBorder(true);
			break;
			case "Terra": pnlTerra.drawBorder(true);
			break;
			case "Centaurus": pnlCentaurus.drawBorder(true);
			break;
			}
		}
	}
	
	/**
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
	 * cette methoder permet a deselectionner les vehicules choisis
	 */
	public void deselectAllVehicule() {
		pnlSinger.deselect();
		pnlGenessis.deselect();
		pnlDrope.deselect();
		pnlReaper.deselect();
		pnlCovenent.deselect();
		pnlMeditator.deselect();
		pnlDecoder.deselect();
	}
	
	/**
	 * cette methode permet a savoir quel joueur a choisi quel vehicule
	 * @param player, le nom du joueur
	 */
	public void setplayers(String player) {
		if(playerChoiceCounter==3) {
			deselectAllVehicule();
			players = new String[2];
			players[0] = player;
			pnlBeta.setType("");
			pnlZeta.setType("");
			playerChoiceCounter = 1;
			
			btnBegin.setText("VEUILLEZ CHOISIR DEUX VEHICULES");
			btnBegin.setEnabled(false);
			btnBegin.setFont(new Font("OCR A Extended", Font.BOLD, 20));
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
			
			btnBegin.setText("COMMENCER");
			btnBegin.setEnabled(true);
			btnBegin.setFont(new Font("OCR A Extended", Font.BOLD, 40));
		}
	}
	
	/**
	 * cette methoder permet a faire un sauvegarde pour un map
	 * @param save, le fichier de sauvegarde
	 */
	public void save(MapSavior save) {
		 try {
				if(save == null) {
					JOptionPane.showMessageDialog(null,"there's nothing to save");
				}else {
					JFileChooser fileChooser = new JFileChooser();
					fileChooser.setDialogTitle("Specify a file to save");  
					 FileNameExtensionFilter xmlFilter = new FileNameExtensionFilter("map files (*.map)", "map");
					fileChooser.setAcceptAllFileFilterUsed(false);
					 fileChooser.addChoosableFileFilter(xmlFilter);
					 File f = new File("saveMap/");
					//File f = new File(System.getProperty("user.home") + "/Desktop");
					fileChooser.setCurrentDirectory(f);
					
					int userSelection = fileChooser.showSaveDialog(null);
					 
					if (userSelection == JFileChooser.APPROVE_OPTION) {
					    File fileToSave = fileChooser.getSelectedFile();
					    System.out.println("Save as file: " + fileToSave.getAbsolutePath());
					    FileOutputStream save4D = new FileOutputStream(fileToSave.getAbsolutePath()+".map");
						ObjectOutputStream fluxObj = new ObjectOutputStream(save4D);
						fluxObj.writeObject(save);
						fluxObj.close();
						JOptionPane.showMessageDialog(null,"saved");
					   
					}
					
				}
			} catch (FileNotFoundException e) {
				JOptionPane.showMessageDialog(null,"File not found");
				// TODO Auto-generated catch block
				//e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	/**
	 * cette methode permet a chrager un fichier save pour jouer ensuite
	 */
	public void load() {
		try {
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setDialogTitle("Specify a file to load");  
			 FileNameExtensionFilter xmlFilter = new FileNameExtensionFilter("map files (*.map)", "map");
			fileChooser.setAcceptAllFileFilterUsed(false);
			fileChooser.addChoosableFileFilter(xmlFilter);
			File f = new File("saveMap/");
			//File f = new File(System.getProperty("user.home") + "/Desktop");
			fileChooser.setCurrentDirectory(f);
			
			int userSelection = fileChooser.showOpenDialog(null);
			 
			if (userSelection == JFileChooser.APPROVE_OPTION) {
			    File fileToSave = fileChooser.getSelectedFile();
			    System.out.println("load file: " + fileToSave.getAbsolutePath());
			    String loadPath=fileToSave.getAbsolutePath();
			    FileInputStream save4D = new FileInputStream(fileToSave.getAbsolutePath());
				ObjectInputStream fluxObj = new ObjectInputStream(save4D);
				MapSavior save = (MapSavior)fluxObj.readObject();
				fluxObj.close();
				JOptionPane.showMessageDialog(null,"load");
				//JOptionPane.showMessageDialog(null,"["+save.getDSLoadPath()!=null+"]");
				SiteOrion test = new SiteOrion();
				
				dispose();	
				
				test.cubesMap.setObjs(save.getObjs());
				test.cubesMap.setObs(save.getObs());
				test.cubesMap.setPlayer1(save.getPlayer1());
				test.cubesMap.setPlayer2(save.getPlayer2());
				test.cubesMap.setTimer(save.getTimerMin(), save.getTimerSec());
				test.cubesMap.setReinforce(save.getReinforce()[0], save.getReinforce()[1]);
				if(save.getEle()!=null)test.cubesMap.setEle(save.getEle()[0], save.getEle()[1]);
				if(save.getDSLoadPath()!=null) test.cubesMap.setDSLoadPath(save.getDSLoadPath());
				if(save.getMapName()!=null)test.cubesMap.setMap(save.getMapName());
				
				test.setVisible(true);
				//test.setFocusable(true);
				//test.requestFocusInWindow();
				
			}
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null,"File Not Found");
			// TODO Auto-generated catch block
			//e.printStackTrace();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null,"IOException");
			System.out.println(e.getMessage());
			// TODO Auto-generated catch block
			//e.printStackTrace();
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,"ClassNotFoundException");
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
	}
}
