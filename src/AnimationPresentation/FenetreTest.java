package AnimationPresentation;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FenetreTest extends JFrame {

	private JPanel contentPane;
	private JLabel lbltat, lblCots, lblCoin;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FenetreTest frame = new FenetreTest();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FenetreTest() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 940, 772);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblDmoPrsentation = new JLabel("Démos présentation");
		lblDmoPrsentation.setForeground(new Color(255, 255, 255));
		lblDmoPrsentation.setFont(new Font("Tahoma", Font.BOLD, 28));
		lblDmoPrsentation.setBounds(321, 6, 298, 65);
		contentPane.add(lblDmoPrsentation);
		
		ForceElectrique2 forceElectrique = new ForceElectrique2();
		forceElectrique.demarrer();
		forceElectrique.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				forceElectrique.setTirer(true);
			}
		});
		forceElectrique.setBounds(6, 128, 459, 276);
		contentPane.add(forceElectrique);
		
		Missile missile = new Missile();
		missile.demarrer();
		missile.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				missile.setTirer(true);
			}
		});
		missile.setBounds(6, 468, 930, 276);
		contentPane.add(missile);
		
		
		
		Obsatcles obsatcles = new Obsatcles();
		obsatcles.setBounds(477, 127, 459, 276);
		contentPane.add(obsatcles);
		obsatcles.setLayout(null);
		obsatcles.addResultatsListener(new ResultatsListener() {

			@Override
			public void coin(String etat) {
				// TODO Auto-generated method stub
				lblCoin.setText("Coin : "+etat);
			}

			@Override
			public void zone(String etat) {
				// TODO Auto-generated method stub
				lbltat.setText("état : "+(etat));
			}

			@Override
			public void cote(String etat) {
				// TODO Auto-generated method stub
				lblCots.setText("cotés : "+etat);
			}
			
		});
		
		lbltat = new JLabel("État : ");
		lbltat.setBounds(248, 18, 126, 16);
		obsatcles.add(lbltat);
		
		lblCots = new JLabel("cotés :");
		lblCots.setBounds(248, 254, 205, 16);
		obsatcles.add(lblCots);
		
		lblCoin = new JLabel("Coin :");
		lblCoin.setBounds(248, 226, 205, 16);
		obsatcles.add(lblCoin);
		
		JLabel lblForcelectrique = new JLabel("Force électrique");
		lblForcelectrique.setForeground(new Color(255, 255, 255));
		lblForcelectrique.setBounds(183, 85, 105, 32);
		contentPane.add(lblForcelectrique);
		
		JLabel lblTrimissiles = new JLabel("TriMissiles");
		lblTrimissiles.setForeground(Color.WHITE);
		lblTrimissiles.setBounds(437, 424, 68, 32);
		contentPane.add(lblTrimissiles);
		
		JLabel lblObstacles = new JLabel("Obstacles");
		lblObstacles.setForeground(Color.WHITE);
		lblObstacles.setBounds(672, 83, 68, 32);
		contentPane.add(lblObstacles);
	}
}
