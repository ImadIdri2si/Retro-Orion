package fenetres;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JToggleButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.JSlider;
import javax.swing.JButton;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
/**
 * Classe de la fenetre options qui permet de changer le volume de la music, d'activer ou desactiver la music et de meme pour le mode scientifique
 * @author Imad Idrissi
 *
 */
public class Fenetre7Option extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Lancement de la fenetre 
	 */
	public static void main(String[] args) {
		Fenetre7Option fenetre7 = new Fenetre7Option();
		fenetre7.setVisible(false);
				
	}

	/**
	 * Création de la fenetre avec ses composants
	 */
	public Fenetre7Option() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setUndecorated(true);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double width = screenSize.getWidth();
		double height = screenSize.getHeight();
		setBounds((int)(width/2-450/2), (int)(height/2-254/2), 450, 254);
		//setBounds(100, 100, 450, 254);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 0, 102));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setUndecorated(true);
		
		
		JLabel lblVolume = new JLabel("Volume :");
		lblVolume.setForeground(new Color(255, 255, 255));
		lblVolume.setFont(new Font("Times", Font.BOLD, 20));
		lblVolume.setBounds(6, 66, 97, 20);
		contentPane.add(lblVolume);
		
		JLabel lblModeScientifique = new JLabel("Mode Scientifique :");
		lblModeScientifique.setForeground(new Color(255, 255, 255));
		lblModeScientifique.setFont(new Font("Times", Font.BOLD, 20));
		lblModeScientifique.setBounds(6, 148, 183, 20);
		contentPane.add(lblModeScientifique);
		
		JLabel lblNewLabel = new JLabel("Music :");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Times", Font.BOLD, 20));
		lblNewLabel.setBounds(6, 109, 82, 16);
		contentPane.add(lblNewLabel);
		//Modifier le volume
		JToggleButton tglbtnMusic = new JToggleButton("Activé");
		tglbtnMusic.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				//Debut
				if(e.getStateChange() == 1) {
					tglbtnMusic.setText("Activé");
				}else {
					tglbtnMusic.setText("Désactivé");
				}
				//Fin
				
			}
		});
		
		tglbtnMusic.setSelected(true);
		
		tglbtnMusic.setBounds(191, 99, 253, 38);
		contentPane.add(tglbtnMusic);
		//Activation du mode scientifique
		JToggleButton tglbtnModeScientifique = new JToggleButton("Désactivé");
		tglbtnModeScientifique.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				//Debut
				if(e.getStateChange() == 1) {
					tglbtnModeScientifique.setText("Activé");
				}else {
					tglbtnModeScientifique.setText("Désactivé");
				}
				//Fin
			}
		});
		tglbtnModeScientifique.setBounds(191, 138, 253, 38);
		contentPane.add(tglbtnModeScientifique);
		
		JLabel lblOption = new JLabel("Option");
		lblOption.setForeground(Color.WHITE);
		lblOption.setFont(new Font("Times", Font.BOLD, 20));
		lblOption.setBounds(194, 15, 61, 20);
		contentPane.add(lblOption);
		
		JLabel lblNewPourcentageVolume = new JLabel("50%");
		lblNewPourcentageVolume.setBounds(408, 70, 36, 20);
		contentPane.add(lblNewPourcentageVolume);
		//Activation de la music

		JSlider sliderVolume = new JSlider();
		sliderVolume.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				//Debut
				lblNewPourcentageVolume.setText(sliderVolume.getValue()+"%");
				//Fin
			}
		});
		sliderVolume.setBounds(180, 66, 236, 29);
		contentPane.add(sliderVolume);
		// Permet de retourner sur la fenetre précedente 
		JButton btnRetour = new JButton("Retour");
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Debut
				setVisible(false);
				//Fin
			}
		});
		btnRetour.setBounds(6, 219, 117, 29);
		contentPane.add(btnRetour);
		
		
	}
}
