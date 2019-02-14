package foundation;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.event.EventListenerList;

import mapListeners.InfoBarListener;
import mapListeners.OmniMapListener;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
/**
 * la classe qui s'occupe de la barre d'information au dessous du jeu
 * @author XU WEI DUO
 *
 */
public class InfoBar extends JPanel {
	private Image map = null;
	private int width, height;
	private boolean first = true;
	private Rectangle2D.Double beta,zeta, stop;
	private int betaLastDim=0, zetaLastDim=0;
	private String betaName,zetaName;
	private Color betaColor,zetaColor,stopColor=Color.GREEN;
	//private MenuPause menuPause;
	private AffineTransform mat = new AffineTransform();
	private String timer="00 : 00";
	private final EventListenerList OBJS = new EventListenerList();
	
	/**
	 * initilialise le Jpanel et les ecouteurs pour le clavier
	 */
	public InfoBar() {
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				//begin
				if(containStop(e.getX(),e.getY()))pause();
				//fin
			}

		});
		setBounds(0,0,1366,50);
		setBackground(Color.DARK_GRAY);
		repaint();
	}
	
	/**
	 * cette methode permet elever un evenement pause pour le jeu
	 */
	public void pause() {
		stopColor = Color.RED; 
		pauseMode();
		repaint();
	}
	
	/**
	 * cette methode permet elver un evenement retoure apres pause
	 */
	public void resume() {
		stopColor = Color.GREEN; 
		repaint();			
		
	}
	
	/**
	 * cette methode permet a recevoir le nom et le couleur du vehicule beta
	 * @param name, le nom du vehicule beta
	 * @param entry, le couleur du vehicule beta
	 */
	public void setBeta(String name, Color entry) {
		betaName = name;
		betaColor = entry;
	}
	
	/**
	 * cette methode permet a recevoir le nom et le couleur du vehicule zeta
	 * @param name, le nom du vehicule zeta
	 * @param entry, le couleur du vehicule zeta
	 */
	public void setZeta(String name,Color entry) {
		zetaName = name;
		zetaColor = entry;
	}
	
	/**
	 * cette methode permet a recevoir le nombre de queue en ce moment et le nombre de queue max du vehicule beta
	 * @param dim, le nombre de queue en ce moment du vehicule beta
	 * @param limit, le nombre de queue max du vehicule beta
	 */
	public void setBetaDim(int dim, int limit) {
		if(betaLastDim!=dim) {
			betaLastDim = dim;
			beta = new Rectangle2D.Double(0,5,(double)dim*(620/(double)limit),40);
		}
		repaint();
	
	}
	
	/**
	 * cette methode permet a recevoir le nombre de queue en ce moment et le nombre de queue max du vehicule zeta
	 * @param dim, le nombre de queue en ce moment du vehicule zeta
	 * @param limit, le nombre de queue max du vehicule zeta
	 */
	public void setZetaDim(int dim, int limit) {
		if(zetaLastDim!=dim) {
			zetaLastDim = dim;
			zeta = new Rectangle2D.Double(width-620,5,(double)dim*(620/(double)limit),40);
		}
	
		repaint();
	}
	
	/**
	 * cette methode ajoute un ecouteur InfoBarListener
	 * @param objEcout, ecouteur InfoBarListener
	 */
	public void addInfoBarListener(InfoBarListener objEcout) {
		OBJS.add(InfoBarListener.class, objEcout);
	}
	
	/**
	 * cette methode permet a lever un evenement pause
	 */
	public void pauseMode() {
		for(InfoBarListener ecout: OBJS.getListeners(InfoBarListener.class)) {
			ecout.pauseMode();
		}
	}
	
	
	/**
	 * dessiner et initialiser tous les objets et vehicules dans la sceene 
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		if(first) {
			width = getWidth();
			height = getHeight();
			try {
				map = ImageIO.read(getClass().getClassLoader().getResource("InfoBar.PNG"));
			} catch (IOException e1) {
				JOptionPane.showMessageDialog(null, "image of map not found");
				e1.printStackTrace();
			}
			mat.rotate(Math.toRadians(180), 620/2, 5+40/2);;
			beta = new Rectangle2D.Double(0,5,620,40);
			zeta = new Rectangle2D.Double(width-620,5,620,40);
			stop = new Rectangle2D.Double(width/2-80/2, height/2-40/2+20, 80, 40);
			//betaName = "PlayerL : "+betaName;
			//zetaName = "PlayerR : "+zetaName;
			betaName = "Joueur Gauche : "+betaName;
			zetaName = "Joueur Droite : "+zetaName;
			
			first = false;
		}
		
		g2d.setColor(betaColor);
		g2d.fill(mat.createTransformedShape(beta));
		g2d.setColor(zetaColor);
		g2d.fill(zeta);
		g2d.setColor(stopColor);
		g2d.fill(stop);
		g2d.drawImage(map, 0,0,(int)width,(int)height,null);
		g2d.setColor(Color.RED);
		g2d.setFont(new Font("Tahoma", Font.PLAIN, 15)); 
		int timerLength = g2d.getFontMetrics().stringWidth(timer);
		g2d.drawString(timer,width/2-timerLength/2, height/2-4);
		
		g2d.setColor(Color.WHITE);
		g2d.setFont(new Font("OCR A Extended", Font.PLAIN, 13)); 
		int betaLength = g2d.getFontMetrics().stringWidth(betaName);
		g2d.drawString(betaName, width/2+80-betaLength*2, height/2-8);
		int zetaLength = g2d.getFontMetrics().stringWidth(zetaName);
		g2d.drawString(zetaName, (width/2)-80+zetaLength, height/2-8);
		
	}
	
	/**
	 * cette methode permet a savoir si la souris a cliquer sur le bouton stop
	 * @param x, coordonne x de la souris
	 * @param y, coordonne y de la souris
	 * @return, retourne vrai si la souris a cliquer sur le bouton stop
	 */
	private boolean containStop(int x, int y) {
		return (stop.contains(new Point(x,y)));
	}
	
	/**
	 * cette methode permet a revenoir le temps ecoule du jeu
	 * @param timer, le temps ecoule du jeu
	 */
	public void setTimer(String timer) {
		this.timer = timer;
		repaint();
	}
}
