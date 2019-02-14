package foundation;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import JPanelButton.PanelVehicule;
import java.awt.Color;
import java.awt.Dimension;

import JPanelButton.PanelButton;
import JPanelButton.panelMap;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import JPanelButton.panelImages;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import javax.swing.JTabbedPane;
/**
 * la classe qui s'occupe d'une fenetre Credit
 * @author XU WEI DUO
 *
 */
public class EndCredit extends JFrame {

	private JPanel contentPane;
	private Color borderColor;
	private JTabbedPane tabbedPane;
	/**
	 * execute l'application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EndCredit frame = new EndCredit();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void setBorderColor(Color borderColor) {
		this.borderColor = borderColor;
		contentPane.setBorder(new LineBorder(borderColor, 5));
	}
	/**
	 * creation de l'interface avec tous ses fonctions necessaires
	 */
	public EndCredit() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setUndecorated(true);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double width = screenSize.getWidth();
		double height = screenSize.getHeight();
		setBounds((int)(width/2-1300/2), (int)(height/2-700/2), 1300, 700);
		//setBounds(100, 100, 737, 509);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new LineBorder(Color.PINK, 5));
		
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setForeground(Color.WHITE);
		panel.setBackground(Color.DARK_GRAY);
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "OBSTACLE", TitledBorder.LEADING, TitledBorder.TOP, null, Color.WHITE));
		panel.setBounds(1070, 209, 207, 148);
		contentPane.add(panel);
		panel.setLayout(null);
		
		PanelButton panelButton = new PanelButton();
		panelButton.setBorder(new LineBorder(Color.PINK, 4));
		panelButton.setBounds(10, 16, 60, 121);
		panel.add(panelButton);
		panelButton.setType("Obstacle");
		
		panelImages panelImages__2 = new panelImages("Obstacle",2);
		panelImages__2.setBounds(76, 16, 125, 125);
		panel.add(panelImages__2);
		
		JPanel panel_1 = new JPanel();
		panel_1.setForeground(Color.WHITE);
		panel_1.setBackground(Color.DARK_GRAY);
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "BLACK DOMAIN", TitledBorder.LEADING, TitledBorder.TOP, null, Color.WHITE));
		panel_1.setBounds(1090, 62, 185, 123);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		PanelButton panelButton_1 = new PanelButton();
		panelButton_1.setBorder(new LineBorder(Color.PINK, 4));
		panelButton_1.setBounds(10, 16, 60, 96);
		panel_1.add(panelButton_1);
		panelButton_1.setType("BlackDomain");
		
		panelImages panelImages__3 = new panelImages("BlackDomain", 1);
		panelImages__3.setBounds(76, 16, 103, 100);
		panel_1.add(panelImages__3);
		
		JLabel lblMapsImageArtist = new JLabel("Artiste d'Image des Maps: \u00A9Xu Wei Duo");
		lblMapsImageArtist.setFont(new Font("OCR A Extended", Font.BOLD | Font.ITALIC, 20));
		lblMapsImageArtist.setHorizontalAlignment(SwingConstants.CENTER);
		lblMapsImageArtist.setForeground(Color.WHITE);
		lblMapsImageArtist.setBounds(20, 173, 701, 27);
		contentPane.add(lblMapsImageArtist);
		
		
		panelMap panelMap_ = new panelMap("Terra");
		panelMap_.setBounds(494, 238, 227, 119);
		contentPane.add(panelMap_);
		
		panelMap panelMap__1 = new panelMap("Cloud");
		panelMap__1.setBounds(20, 238, 227, 119);
		contentPane.add(panelMap__1);
		
		panelMap panelMap__2 = new panelMap("Abandon");
		panelMap__2.setBounds(257, 238, 227, 119);
		contentPane.add(panelMap__2);
		
		PanelVehicule panelVehicule = new PanelVehicule("Decoder");
		panelVehicule.setBounds(416, 63, 54, 100);
		contentPane.add(panelVehicule);
		
		PanelVehicule panelVehicule_1 = new PanelVehicule("Covenent");
		panelVehicule_1.setBounds(344, 63, 63, 100);
		contentPane.add(panelVehicule_1);
		
		PanelVehicule panelVehicule_2 = new PanelVehicule("Singer");
		panelVehicule_2.setBounds(288, 62, 47, 100);
		contentPane.add(panelVehicule_2);
		
		PanelVehicule panelVehicule_3 = new PanelVehicule("Drope");
		panelVehicule_3.setBounds(223, 63, 56, 100);
		contentPane.add(panelVehicule_3);
		
		PanelVehicule panelVehicule_4 = new PanelVehicule("Genessis");
		panelVehicule_4.setBounds(149, 62, 64, 100);
		contentPane.add(panelVehicule_4);
		
		PanelVehicule panelVehicule_5 = new PanelVehicule("Meditator");
		panelVehicule_5.setBounds(20, 62, 56, 100);
		contentPane.add(panelVehicule_5);
		
		PanelVehicule panelVehicule_6 = new PanelVehicule("Reaper");
		panelVehicule_6.setBounds(85, 63, 55, 100);
		contentPane.add(panelVehicule_6);
		
		JLabel lblObjetsImageArtist = new JLabel("Artiste d'Image des Objets: \u00A9Xu Wei Duo");
		lblObjetsImageArtist.setHorizontalAlignment(SwingConstants.CENTER);
		lblObjetsImageArtist.setForeground(Color.WHITE);
		lblObjetsImageArtist.setFont(new Font("OCR A Extended", Font.BOLD | Font.ITALIC, 20));
		lblObjetsImageArtist.setBounds(756, 24, 521, 27);
		contentPane.add(lblObjetsImageArtist);
		
		JLabel lblVehiculesImageArtist = new JLabel("Artiste d'Image des Vehicules: \u00A9Xu Wei Duo");
		lblVehiculesImageArtist.setHorizontalAlignment(SwingConstants.CENTER);
		lblVehiculesImageArtist.setForeground(Color.WHITE);
		lblVehiculesImageArtist.setFont(new Font("OCR A Extended", Font.BOLD | Font.ITALIC, 20));
		lblVehiculesImageArtist.setBounds(20, 24, 682, 27);
		contentPane.add(lblVehiculesImageArtist);
		
		//JButton btnReturn = new JButton("RETURN");
		JButton btnReturn = new JButton("RETOURNER");
		btnReturn.setFocusable(false);
		btnReturn.setBackground(Color.PINK);
		btnReturn.setForeground(Color.WHITE);
		btnReturn.setFont(new Font("OCR A Extended", Font.PLAIN, 20));
		btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//begin
				dispose();
				//din
			}
		});
		btnReturn.setBounds(20, 645, 1257, 34);
		contentPane.add(btnReturn);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setFont(new Font("Tahoma", Font.PLAIN, 11));
		tabbedPane.setFocusable(false);
		tabbedPane.setForeground(Color.WHITE);
		tabbedPane.setBackground(Color.DARK_GRAY);
		tabbedPane.setBounds(756, 234, 304, 123);
		contentPane.add(tabbedPane);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(Color.DARK_GRAY);
		tabbedPane.addTab("REINFORCE [TYPE-1 & TYPE-2]", null, panel_4, null);
		panel_4.setLayout(null);
		
		PanelButton panelButton_2 = new PanelButton();
		panelButton_2.setBounds(108, 11, 83, 73);
		panel_4.add(panelButton_2);
		panelButton_2.setBorder(new LineBorder(Color.PINK, 4));
		panelButton_2.setType("Reinforce");
		
		panelImages panelImages_ = new panelImages("ReinforceTp1",1);
		panelImages_.setBounds(10, 11, 88, 76);
		panel_4.add(panelImages_);
		
		panelImages panelImages__1 = new panelImages("ReinforceTp2",1);
		panelImages__1.setBounds(201, 11, 88, 76);
		panel_4.add(panelImages__1);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.DARK_GRAY);
		tabbedPane.addTab("Missiles", null, panel_3, null);
		panel_3.setLayout(null);
		
		panelImages panelImages__15 = new panelImages("MissileTp1", 2);
		panelImages__15.setBounds(10, 17, 69, 69);
		panel_3.add(panelImages__15);
		
		panelImages panelImages__16 = new panelImages("MissileTp2", 2);
		panelImages__16.setBounds(220, 17, 69, 69);
		panel_3.add(panelImages__16);
		
		JLabel lblTypeTrimissile = new JLabel("<-- Type 1: Tri-M");
		lblTypeTrimissile.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblTypeTrimissile.setHorizontalAlignment(SwingConstants.LEFT);
		lblTypeTrimissile.setForeground(Color.WHITE);
		lblTypeTrimissile.setBounds(89, 34, 121, 14);
		panel_3.add(lblTypeTrimissile);
		
		JLabel lblTypeTrackmissile = new JLabel("Type 2: Track-M-->");
		lblTypeTrackmissile.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblTypeTrackmissile.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTypeTrackmissile.setForeground(Color.WHITE);
		lblTypeTrackmissile.setBounds(89, 59, 121, 14);
		panel_3.add(lblTypeTrackmissile);
		
		JLabel lblBackgrounfArtistXu = new JLabel("Artist des Images du Fond: \u00A9Imad Idrissi");
		lblBackgrounfArtistXu.setHorizontalAlignment(SwingConstants.CENTER);
		lblBackgrounfArtistXu.setForeground(Color.WHITE);
		lblBackgrounfArtistXu.setFont(new Font("OCR A Extended", Font.BOLD | Font.ITALIC, 20));
		lblBackgrounfArtistXu.setBounds(20, 378, 597, 27);
		contentPane.add(lblBackgrounfArtistXu);
		
		panelImages panelImages__4 = new panelImages("Fenetre1", 6);
		panelImages__4.setLocation(20, 416);
		contentPane.add(panelImages__4);
		
		panelImages panelImages__5 = new panelImages("ChoixMode", 9);
		panelImages__5.setLocation(351, 416);
		contentPane.add(panelImages__5);
		
		panelImages panelImages__6 = new panelImages("Apropos", 13);
		panelImages__6.setLocation(734, 414);
		contentPane.add(panelImages__6);
		
		JPanel panel_2 = new JPanel();
		panel_2.setForeground(Color.WHITE);
		panel_2.setBackground(Color.DARK_GRAY);
		panel_2.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "CHAMP ELECTRIQUE [PROTON & ELECTRON]", TitledBorder.LEADING, TitledBorder.TOP, null, Color.WHITE));
		panel_2.setBounds(756, 62, 304, 165);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		panelImages panelImages__8 = new panelImages("Proton", 1);
		panelImages__8.setBounds(6, 18, 140, 140);
		panel_2.add(panelImages__8);
		
		panelImages panelImages__9 = new panelImages("Electron", 1);
		panelImages__9.setBounds(156, 16, 142, 142);
		panel_2.add(panelImages__9);
		
		JLabel lblMusicLoopArtist = new JLabel("remerciement sp\u00E9cial \u00E0 notre compositeur de musique: \u00A9Korux");
		lblMusicLoopArtist.setHorizontalAlignment(SwingConstants.CENTER);
		lblMusicLoopArtist.setForeground(Color.WHITE);
		lblMusicLoopArtist.setFont(new Font("OCR A Extended", Font.BOLD | Font.ITALIC, 20));
		lblMusicLoopArtist.setBounds(20, 607, 1257, 27);
		contentPane.add(lblMusicLoopArtist);
		
		panelImages panelImages__10 = new panelImages("CS1FM", 13);
		panelImages__10.setLocation(1064, 415);
		contentPane.add(panelImages__10);
		
		panelImages panelImages__11 = new panelImages("CS1FE", 13);
		panelImages__11.setLocation(1173, 416);
		contentPane.add(panelImages__11);
		
		panelImages panelImages__7 = new panelImages("GU1", 13);
		panelImages__7.setLocation(627, 415);
		contentPane.add(panelImages__7);
		
		panelImages panelImages__12 = new panelImages("GU2", 13);
		panelImages__12.setLocation(843, 415);
		contentPane.add(panelImages__12);
		
		panelImages panelImages__13 = new panelImages("GU3", 13);
		panelImages__13.setLocation(950, 415);
		contentPane.add(panelImages__13);
		
		JLabel lblInstructionImageArtist = new JLabel("Artist des images d'instruction: \u00A9Imad Idrissi");
		lblInstructionImageArtist.setHorizontalAlignment(SwingConstants.CENTER);
		lblInstructionImageArtist.setForeground(Color.WHITE);
		lblInstructionImageArtist.setFont(new Font("OCR A Extended", Font.BOLD | Font.ITALIC, 20));
		lblInstructionImageArtist.setBounds(627, 378, 650, 27);
		contentPane.add(lblInstructionImageArtist);
		
		panelImages panelImages__14 = new panelImages("InfoBar", 2);
		panelImages__14.setLocation(30, 203);
		contentPane.add(panelImages__14);
		
		panelMap panelMap__3 = new panelMap("Centaurus");
		panelMap__3.setLocation(494, 54);
		contentPane.add(panelMap__3);
		
		
	}
}
