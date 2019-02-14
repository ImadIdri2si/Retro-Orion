package fenetres;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.border.TitledBorder;

import aaplication.App25RetroOrion;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FenetreGuideUti extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FenetreGuideUti frame = new FenetreGuideUti();
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
	public FenetreGuideUti() {
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
		button.setFont(new Font("Times", Font.BOLD, 18));
		button.setBounds(0, 731, 98, 37);
		contentPane.add(button);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 1366, 730);
		contentPane.add(tabbedPane);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.BLACK);
		panel_2.setForeground(Color.BLACK);
		tabbedPane.addTab("But général", null, panel_2, null);
		panel_2.setLayout(null);
		
		ImageAvecDefilement imageAvecDefilement = new ImageAvecDefilement();
		imageAvecDefilement.setBounds(0, 0, 1361, 702);
		imageAvecDefilement.setFichierImage("GU1.jpg");
		
		panel_2.add(imageAvecDefilement);
			
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Paramètre", null, panel_1, null);
		panel_1.setLayout(null);
		
		ImageAvecDefilement imageAvecDefilement_1 = new ImageAvecDefilement();
		imageAvecDefilement_1.setBounds(6, 6, 1333, 682);
		imageAvecDefilement_1.setFichierImage("GU2.jpg");
		panel_1.add(imageAvecDefilement_1);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Utilisation", null, panel, null);
		panel.setLayout(null);
		
		ImageAvecDefilement imageAvecDefilement_2 = new ImageAvecDefilement();
		imageAvecDefilement_2.setBounds(6, 6, 1333, 682);
		imageAvecDefilement_2.setFichierImage("GU3.jpg");
		panel.add(imageAvecDefilement_2);
		
		
		
		
	}
}
