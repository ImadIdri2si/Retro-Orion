package foundation;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.EventListenerList;

import fenetres.Fenetre2ChoixMode;
import mapListeners.InfoBarListener;
import mapListeners.MenuPauseListener;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;
import java.awt.Font;
/**
 * cette classe cree un menu pause
 * @author XU WEI DUO
 *
 */
public class MenuPause extends JFrame {

	private JPanel contentPane;
	private final EventListenerList OBJS = new EventListenerList();

	/**
	 * execute l'application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuPause frame = new MenuPause();
					frame.setVisible(true);
					frame.setFocusable(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * creation de l'interface avec tous ses fonctions necessaires
	 */
	public MenuPause() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setUndecorated(true);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double width = screenSize.getWidth();
		double height = screenSize.getHeight();
		setBounds((int)(width/2-200/2), (int)(height/2-300/2), 200, 300);
		//setBounds(100, 100, 179, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(64, 64, 64));
		contentPane.setBorder(new LineBorder(Color.GRAY, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnExit = new JButton("QUITTER");
		btnExit.setBackground(Color.WHITE);
		btnExit.setFont(new Font("OCR A Extended", Font.PLAIN, 12));
		btnExit.setFocusable(false);
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//bgin
				System.exit(0);
				//fin
			}
		});
		btnExit.setBounds(23, 242, 154, 23);
		contentPane.add(btnExit);
		
		//JButton btnResume = new JButton("RESUME");
		JButton btnResume = new JButton("REPRENDRE");
		btnResume.setBackground(Color.WHITE);
		btnResume.setFont(new Font("OCR A Extended", Font.PLAIN, 12));
		btnResume.setFocusable(false);
		btnResume.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//begin
				dispose();
				resume();
				//fin
			}
		});
		btnResume.setBounds(23, 40, 154, 38);
		contentPane.add(btnResume);
		
		//JButton btnImAButton = new JButton("(OwO)RETURN");
		JButton btnImAButton = new JButton("RETOURNE");
		btnImAButton.setFont(new Font("OCR A Extended", Font.PLAIN, 12));
		btnImAButton.setFocusable(false);
		btnImAButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//begin
				returnTo();
				dispose();
				//fin
			}
		});
		btnImAButton.setBackground(Color.PINK);
		btnImAButton.setBounds(23, 175, 154, 56);
		contentPane.add(btnImAButton);
		
		//JButton btnModeSci = new JButton("Mode Sci");
		JButton btnModeSci = new JButton("Mode scientifique");
		btnModeSci.setBackground(Color.WHITE);
		btnModeSci.setFont(new Font("OCR A Extended", Font.PLAIN, 12));
		btnModeSci.setFocusable(false);
		btnModeSci.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//begin
				dispose();
				resume();
				sciWin();
				//fin
			}
		});
		btnModeSci.setBounds(23, 89, 154, 76);
		contentPane.add(btnModeSci);
	}
	
	/**
	 * cette methode ajoute un ecouteur MenuPauseListener
	 * @param objEcout, ecouteur MenuPauseListener
	 */
	public void addMenuPauseListener(MenuPauseListener objEcout) {
		OBJS.add(MenuPauseListener.class, objEcout);
	}
	
	/**
	 * cette methode permet a lever un evenement retoure
	 */
	public void resume() {
		for(MenuPauseListener ecout: OBJS.getListeners(MenuPauseListener.class)) {
			ecout.resume();
		}
	}
	
	/**
	 * cette methode permet a lever un evenement retorune dans la fenetre precedant
	 */
	public void returnTo() {
		for(MenuPauseListener ecout: OBJS.getListeners(MenuPauseListener.class)) {
			ecout.returnTo();
		}
	}
	
	/**
	 * cette methode permet a lever un evenement d'ouvrir une fenetre pour le mode scitifique
	 */
	public void sciWin() {
		for(MenuPauseListener ecout: OBJS.getListeners(MenuPauseListener.class)) {
			ecout.sciWin();
		}
	}
	
}
