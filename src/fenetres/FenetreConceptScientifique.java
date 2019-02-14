package fenetres;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import aaplication.App25RetroOrion;

import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JTabbedPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
/**
 * Cette fenetre est la fenetre du mode scientifique au les résultat s'affichent 
 * @author Imad Idrissi 
 *
 */
public class FenetreConceptScientifique extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FenetreConceptScientifique frame = new FenetreConceptScientifique();
					frame.setVisible(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FenetreConceptScientifique() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setUndecorated(true);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double width = screenSize.getWidth();
		double height = screenSize.getHeight();
		setBounds((int)(width/2-1366/2), (int)(height/2-768/2), 1366, 768);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton button = new JButton("Retour");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Debut
				App25RetroOrion FenetrePrincipale = new App25RetroOrion();
				FenetrePrincipale.setVisible(true);
				dispose();
				//Fin
			}
		});
		button.setFont(new Font("Dialog", Font.BOLD, 18));
		button.setBounds(0, 731, 98, 37);
		contentPane.add(button);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBorder(null);
		tabbedPane.setBackground(Color.WHITE);
		tabbedPane.setBounds(0, 0, 1366, 731);
		contentPane.add(tabbedPane);
		
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.BLACK);
		tabbedPane.addTab("Force électrique", null, panel, null);
		panel.setLayout(null);
		
		ImageAvecDefilement imageAvecDefilement = new ImageAvecDefilement();
		imageAvecDefilement.setBounds(0, 0, 1361, 703);
		 imageAvecDefilement.setFichierImage("CS1FE.jpg");
		panel.add(imageAvecDefilement);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.BLACK);
		tabbedPane.addTab("Force magnétique", null, panel_1, null);
		panel_1.setLayout(null);
		
		ImageAvecDefilement imageAvecDefilement_1 = new ImageAvecDefilement();
		imageAvecDefilement_1.setBounds(0, 0, 1361, 703);
		 imageAvecDefilement_1.setFichierImage("CS1FM.jpg");
		panel_1.add(imageAvecDefilement_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.BLACK);
		tabbedPane.addTab("Forme 4D", null, panel_2, null);
		panel_2.setLayout(null);
		
		ImageAvecDefilement imageAvecDefilement_2 = new ImageAvecDefilement();
		imageAvecDefilement_2.setBounds(0, 0, 1361, 703);
		 imageAvecDefilement_2.setFichierImage("CS14D.jpg");
		panel_2.add(imageAvecDefilement_2);
	}
}
