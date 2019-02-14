package foundation;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.EventListenerList;

import fenetres.Fenetre2ChoixMode;
import mapListeners.MenuEndListener;
import mapListeners.MenuPauseListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import JPanelButton.PanelVehicule;
import javax.swing.SwingConstants;
import JPanelButton.panelImages;
import java.awt.Color;
import javax.swing.border.LineBorder;
import java.awt.Font;
/**
 * la classe qui s'occupe d'une fenetre fin du jeu
 * @author XU WEI DUO
 *
 */
public class MenuEnd extends JFrame {

	private JPanel contentPane;
	private JLabel lbWinner;
	private JButton btnExit;
	private JLabel lblPL;
	private JLabel lblPR;
	private final EventListenerList OBJS = new EventListenerList();
	private panelImages pnlWinner;
	private JPanel panel;
	private JButton btnCredit;
	private Color tailColor;
	/**
	 * execute l'application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuEnd frame = new MenuEnd();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * creation de l'interface avec tous ses fonctions necessaires
	 */
	public MenuEnd() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setUndecorated(true);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double width = screenSize.getWidth();
		double height = screenSize.getHeight();
		setBounds((int)(width/2-400/2), (int)(height/2-600/2), 400, 600);
		
		contentPane = new JPanel();
		contentPane.setBackground(Color.GRAY);
		contentPane.setBorder(new LineBorder(Color.PINK, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panel = new JPanel();
		panel.setBackground(Color.GRAY);
		panel.setBounds(10, 471, 380, 56);
		contentPane.add(panel);
		panel.setLayout(null);
		
		lbWinner = new JLabel("VAINQUEUR");
		lbWinner.setForeground(Color.WHITE);
		lbWinner.setBackground(Color.GRAY);
		lbWinner.setOpaque(true);
		lbWinner.setBounds(0, 0, 380, 23);
		panel.add(lbWinner);
		lbWinner.setFont(new Font("OCR A Extended", Font.PLAIN, 20));
		lbWinner.setHorizontalAlignment(SwingConstants.CENTER);
		
		lblPL = new JLabel("PL: ");
		lblPL.setForeground(Color.WHITE);
		lblPL.setBackground(Color.GRAY);
		lblPL.setOpaque(true);
		lblPL.setBounds(0, 35, 190, 15);
		panel.add(lblPL);
		lblPL.setFont(new Font("OCR A Extended", Font.PLAIN, 20));
		lblPL.setHorizontalAlignment(SwingConstants.LEFT);
		
		lblPR = new JLabel("PR:");
		lblPR.setForeground(Color.WHITE);
		lblPR.setBackground(Color.GRAY);
		lblPR.setOpaque(true);
		lblPR.setBounds(190, 35, 190, 15);
		panel.add(lblPR);
		lblPR.setFont(new Font("OCR A Extended", Font.PLAIN, 20));
		lblPR.setHorizontalAlignment(SwingConstants.RIGHT);
		
		//JButton btnReturn = new JButton("RETURN");
		JButton btnReturn = new JButton("RETOURNE");
		btnReturn.setForeground(Color.DARK_GRAY);
		btnReturn.setFont(new Font("OCR A Extended", Font.PLAIN, 20));
		btnReturn.setBackground(Color.LIGHT_GRAY);
		btnReturn.setFocusable(false);
		btnReturn.setFocusTraversalKeysEnabled(false);
		btnReturn.setFocusPainted(false);
		btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//BEGIN
				returnTo();
				dispose();
				// JOptionPane.showMessageDialog(null, "Are you sure?");
				//FIN
			}
		});
		btnReturn.setBounds(10, 532, 380, 32);
		contentPane.add(btnReturn);
		
		//btnExit = new JButton("EXIT");
		btnExit = new JButton("QUITTER");
		btnExit.setForeground(Color.DARK_GRAY);
		btnExit.setFont(new Font("OCR A Extended", Font.PLAIN, 20));
		btnExit.setBackground(Color.LIGHT_GRAY);
		btnExit.setFocusable(false);
		btnExit.setFocusTraversalKeysEnabled(false);
		btnExit.setFocusPainted(false);
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//begin
				System.exit(0);
				//fin
			}
		});
		btnExit.setBounds(10, 566, 380, 23);
		contentPane.add(btnExit);
		
		btnCredit = new JButton("CREDIT");
		btnCredit.setForeground(Color.WHITE);
		btnCredit.setFont(new Font("OCR A Extended", Font.PLAIN, 20));
		btnCredit.setBackground(Color.PINK);
		btnCredit.setFocusable(false);
		btnCredit.setFocusTraversalKeysEnabled(false);
		btnCredit.setFocusPainted(false);
		btnCredit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//BEGIN
				//JOptionPane.showMessageDialog(null, "Objets & vehicules & maps artist: XU WEI DUO \nbackground image artist: IMAD IDRISSI \nbackground music composer...");
				EndCredit credit = new EndCredit();
				if(tailColor!=null)credit.setBorderColor(tailColor);
				credit.setVisible(true);
				credit.setAlwaysOnTop (true);
				//FIN
			}
		});
		btnCredit.setBounds(10, 11, 380, 41);
		contentPane.add(btnCredit);
		
		pnlWinner = new panelImages("Unknow", 1);
		pnlWinner.setBackground(Color.GRAY);
		pnlWinner.setLocation(getWidth()/2-pnlWinner.getWidth()/2, 60);
		contentPane.add(pnlWinner);
	}
	
	/**
	 * cette methode permet a savoir qui est le gagnant
	 * @param winner, le nom du gagnant
	 * @param tailPL, la longueur du queue du joueur beta
	 * @param tailPR, la longueur du queue du joueur zeta
	 * @param tailColor, la couleur de la queue de la gagnante
	 */
	public void setWinner(String winner, int tailPL, int tailPR, Color tailColor) {
		this.tailColor=tailColor;
		pnlWinner.linkImage(winner);
		if(tailPL>tailPR) {
			winner = "VAINQUEUR PL ["+winner+"]";
			lblPL.setBackground(tailColor);
		}else {
			winner = "VAINQUEUR PR ["+winner+"]";
			lblPR.setBackground(tailColor);
		}
		lbWinner.setText(winner);
		lbWinner.setBackground(tailColor);
		lblPR.setText("PR ["+tailPR+"] Dwarfs");
		lblPL.setText("PL ["+tailPL+"] Dwarfs");
		contentPane.setBorder(new LineBorder(tailColor, 5));
		btnCredit.setBackground(tailColor);
		pnlWinner.setLocation(getWidth()/2-pnlWinner.getWidth()/2, 60);
	}
	
	/**
	 * cette methode indique qu'il n'y a aucune gagnat
	* @param tailPL, la longueur du queue du joueur beta
	 * @param tailPR, la longueur du queue du joueur zeta
	 */
	public void setWinner(int tailPL, int tailPR) {
		lbWinner.setText("none");
		lblPL.setText("PL ["+tailPL+"] Dwarfs");
		lblPR.setText("PR ["+tailPR+"] Dwarfs");
	}
	
	/**
	 * cette methode ajoute un ecouteur MenuEndListener
	 * @param objEcout, ecouteur MenuEndListener
	 */
	public void addMenuEndListener(MenuEndListener objEcout) {
		OBJS.add(MenuEndListener.class, objEcout);
	}
	
	/**
	 * cette methode permet a lever un evenement retoure
	 */
	public void returnTo() {
		for(MenuEndListener ecout: OBJS.getListeners(MenuEndListener.class)) {
			System.out.println("-----------------transmitting-------------------------");
			ecout.returnTo();
		}
	}
}
