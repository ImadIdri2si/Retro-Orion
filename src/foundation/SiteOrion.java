package foundation;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.EventListenerList;

import JPanelButton.PanelButton;
import fenetreModeSci.FenetreScientifique;
import fenetreModeSci.FenetreModeSciListener;
import fenetres.Fenetre2ChoixMode;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import mapListeners.OmniMapListener;
import vecteur.Vecteur;
import mapListeners.InfoBarListener;
import mapListeners.MenuEndListener;
import mapListeners.MenuPauseListener;
import mapListeners.MissileListener;

/**
 * la classe qui cree l'interface
 * @author XU WEI DUO
 *
 */
public class SiteOrion extends JFrame {

	private JPanel contentPane;
	public OmniMap cubesMap;
	private FenetreScientifique scfWindows;
	private boolean slowMo = false;
	private MenuEnd end;
	/**
	 * execute l'application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SiteOrion frame = new SiteOrion();
					frame.setVisible(true);
					frame.cubesMap.requestFocusInWindow();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * creation de l'interface avec tous ses fonctions necessaires
	 */
	public SiteOrion() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setUndecorated(true);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double width = screenSize.getWidth();
		double height = screenSize.getHeight();
		setBounds((int)(width/2-1366/2), (int)(height/2-768/2), 1366, 768);
		//setSize(getMaximumSize());
		end = new MenuEnd();
		//setBackground(new Color(1.0f,1.0f,1.0f,0.5f));
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		cubesMap = new OmniMap();
		scfWindows = new FenetreScientifique();
		scfWindows.addFenetreModeSciListener(new FenetreModeSciListener() {

			public void winClose() {
				cubesMap.dataTransmits(false);
				scfWindows.dispose();
			}

			public void begin() {
				cubesMap.begin();
				
			}

			public void stop() {
				cubesMap.stop();
			}

			public void slowMo(int factor) {
				if(slowMo) {
					slowMo = false;
				}else {
					slowMo = true;
				}
				cubesMap.slowMotion(slowMo, factor);
			}

			@Override
			public void modeSci() {
				cubesMap.sciMode();
			}
			
		 });
		
		//cubesMap.setBounds(0, 0, 1366, 718);
		cubesMap.requestFocusInWindow();
		contentPane.setLayout(null);
		
		InfoBar infoBar = new InfoBar();
		
		MenuPause menuPause = new MenuPause();
		menuPause.addMenuPauseListener(new MenuPauseListener() {
			 public void resume() {
				 cubesMap.slowMotion(false);
				 infoBar.resume();
			 }
			 public void returnTo() {
				 dispose();
				// JOptionPane.showMessageDialog(null, "Are you sure?");
				 Fenetre2ChoixMode choixMode = new Fenetre2ChoixMode();
				 choixMode.setVisible(true);
					
			 }
			
			public void sciWin() {
				//begin
				cubesMap.dataTransmits(true);
				scfWindows.setVisible(true);
				scfWindows.setFocusable(false);
				scfWindows.setAlwaysOnTop (true);
				//fin
				
			}
		 });
		
		
		infoBar.addInfoBarListener(new InfoBarListener() {
			public void pauseMode() {
				//begin
				cubesMap.slowMotion(true);
				menuPause.setVisible(true);
				menuPause.setAlwaysOnTop (true);
				//fin
			}
		});
		infoBar.setBounds(0, 718, 1366, 50);
		contentPane.add(infoBar);
		//cubesMap.setBounds(0, 0, 1366, 600);
		contentPane.add(cubesMap);
		cubesMap.setLayout(null);
		cubesMap.addOmniMapListener(new OmniMapListener() {
			public void betaInfo(String name, Color tailColor) {
				//begin
				infoBar.setBeta(name,tailColor);
				
				//fin
			}
			public void betaTailDim(int tailDim, int tailLimit) {
				//begin
				infoBar.setBetaDim(tailDim,tailLimit);
				//fin
			}
			public void zetaInfo(String name, Color tailColor) {
				//begin
				infoBar.setZeta(name,tailColor);
				//fin
			}
			public void zetaTailDim(int tailDim,int tailLimit) {
				//begin
				infoBar.setZetaDim(tailDim,tailLimit);
				//fin
			}
			
			public void timer(String timer) {
				//begin
				infoBar.setTimer(timer);
				//fin
				
			}
			public void pause() {
				infoBar.pause();
			}
			@Override
			public void end(String winner, int tailPL, int tailPR, Color tailColor) {
				
				end.setWinner(winner, tailPL, tailPR,tailColor);
				end.setVisible(true);
				//cubesMap.slowMotion(true);
				cubesMap.stop();
				cubesMap.paintText(false);
				cubesMap.setFocusable(false);
				end.setAlwaysOnTop (true);
				//dispose();
			}
			@Override
			public void end(int tailPL, int tailPR) {
				
				end.setWinner(tailPL, tailPR);
				end.setVisible(true);
				//cubesMap.slowMotion(true);
				cubesMap.stop();
				cubesMap.paintText(false);
				cubesMap.setFocusable(false);
				end.setAlwaysOnTop (true);
				//dispose();
			}
			@Override
			public void sciModeInfo(Object[] beta, Object[] zeta) {
				//begin
				scfWindows.setVehiculeInfo(beta, zeta);
				//fin
			}
			@Override
			public void sciModeBD(double module, Vecteur vec) {
				//begin
				scfWindows.setBDInfo(module, vec);
				//fin
			}
			@Override
			public void sciModeCE(Object[] CEInfo) {
				//begin
				scfWindows.setCEInfo(CEInfo);
				//fin
			}
			@Override
			public void sciModeObs(int nb) {
				//begin
				scfWindows.setObsInfo(nb);
				//fin
			}
		});
		cubesMap.addMissileListener(new MissileListener() {
			public void MissleInfo(Object[] infos) {
				//begin
				//System.out.println("-----receiving--------");
				//if(!scfWindows.isVisible())scfWindows.setVisible(true);
				scfWindows.setMissleInfo(infos);
				//fin
			}
		});
		
		end.addMenuEndListener(new MenuEndListener() {
			public void returnTo() {
				System.out.println("-------------------------receiving----------------------");
				dispose();
				Fenetre2ChoixMode choixMode = new Fenetre2ChoixMode();
				choixMode.setVisible(true);
			}
			
		});
		
	}
	
	
}
