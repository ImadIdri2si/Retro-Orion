package JPanelButton;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.event.EventListenerList;

import foundation.OmniMap;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
/**
 * la classe qui s'occupe de du bouton map
 * @author XU WEI DUO
 *
 */
public class panelMap extends JPanel implements Runnable{
	private Image img = null;
	private int imgWidth, imgHeight,width,height;
	private boolean first = true;
	private String mapName;
	private final EventListenerList OBJS = new EventListenerList();
	
	private AffineTransform mat;
	private boolean dimUp = false;
	private boolean inAnimation = false;
	private double imgScale=1;
	private int scaleFactor = 6;
	private Boolean drawBorder = false;
	
	/**
	 * initilialise le Jpanel et les ecouteurs pour la souris
	 * @param mapName, le nom du map
	 * @param scaleFactor, le scale
	 */
	public panelMap(String mapName, int scaleFactor) {
		setBackground(Color.DARK_GRAY);
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//begin
				mapChosen();
				//fin
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				//begin
				mat = new AffineTransform();
				begin();
				dimUp = false;
				
				//fin
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				//begin
				mat = new AffineTransform();
				mat.translate((img.getWidth(null)*(1-0.75))/2,(img.getHeight(null)*(1-0.75))/2);
				mat.scale(0.75,0.75);
				begin();
				dimUp = true;
				
				//fin
			}
			/**
			@Override
			public void mousePressed(MouseEvent arg0) {
				//begin
				drawBorder = true;
				repaint();
				//fin
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				//begin
				drawBorder = false;
				repaint();
				//fin
			}
			**/
		});
		OmniMap omniMap = new OmniMap();
		this.scaleFactor = scaleFactor;
		imgWidth = omniMap.getWidth()/scaleFactor;
		imgHeight = omniMap.getHeight()/scaleFactor;
		setSize(imgWidth,imgHeight);
		this.mapName = mapName;
		linkImage(mapName);
	}
	

	/**
	 * initilialise le Jpanel et les ecouteurs pour la souris
	 * @param mapName, le nom du map
	 */
	public panelMap(String mapName) {
		this(mapName,6);
	}
	
	/**
	 * cette methode dessiner le border du panel
	 * @param entry, si dessiner ou non le border
	 */
	public void drawBorder(boolean entry) {
		drawBorder = entry;
		repaint();
	}
	
	/**
	 * cette methode permet a charger une image avec cette bouton map
	 * @param type, le nom du map
	 */
	private void linkImage(String type) {
		try {
			System.out.println("choice: "+type);
			switch(type) {
				case "Abandon": img = ImageIO.read(getClass().getClassLoader().getResource("Abandon.jpg"));			 
				break;
				case "Cloud": img = ImageIO.read(getClass().getClassLoader().getResource("Cloud.jpg"));		
				break;
				case "Terra": img = ImageIO.read(getClass().getClassLoader().getResource("Terra.jpg"));
				break;
				case "Centaurus": img = ImageIO.read(getClass().getClassLoader().getResource("Centaurus.jpg"));
				break;
				
			}
			this.img = img.getScaledInstance(imgWidth,imgHeight, Image.SCALE_SMOOTH);
		} catch (IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Image Load unsuccessful");
		}
		repaint();
	}
	
	/**
	 * cette methode ajoute un ecouteur PanelMapListener
	 * @param objEcout, l'ecouteur PanelMapListener
	 */
	public void addPanelMapListener(PanelMapListener objEcout) {
		OBJS.add(PanelMapListener.class, objEcout);
	}
	
	/**
	 * cette methode permet a lever un evenement
	 */
	public void mapChosen() {
		for(PanelMapListener ecout: OBJS.getListeners(PanelMapListener.class)) {
			ecout.choiceMap(mapName);
		}
	}
	
	/**
	 * dessiner et initialiser un objet dans la scene
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		if(first) {
			width = getWidth();
			height = getHeight();
			first=false;
		
		}
		if(!dimUp) {
			if(inAnimation && imgScale>0.8) {
				imgScale = imgScale-0.1;
				mat.translate((img.getWidth(null)*(1-imgScale))/2,(img.getHeight(null)*(1-imgScale))/2);
				mat.scale(imgScale,imgScale);
			}else if(inAnimation){
				stop();
				//mat = new AffineTransform();
				imgScale = 1;
				//dimUp = true;
			}
		}else {
			if(inAnimation && imgScale<1.2) {
				imgScale = imgScale+0.1;
				mat.translate((img.getWidth(null)*(1-imgScale))/2,(img.getHeight(null)*(1-imgScale))/2);
				mat.scale(imgScale,imgScale);
			}else if(inAnimation){
				stop();
				
				imgScale = 1;
				//dimUp=false;
			}
		}
		g2d.drawImage(img, mat ,null);
		if(mat!=null && drawBorder) {
			Rectangle2D.Double border = new Rectangle2D.Double(mat.getTranslateX(),mat.getTranslateY(),mat.getScaleX()*img.getWidth(null),mat.getScaleY()*img.getHeight(null));
			g2d.setColor(Color.WHITE);
			g2d.setStroke(new BasicStroke(5));
			g2d.draw(border);
		}
		
	}
	
	/**
	 *  Demarre le thread s'il n'est pas deja demarre et dire `a l'ecouteur que l'animation est commencer
	 */
	public void begin() {
		if (!inAnimation) { 
			Thread test = new Thread(this);
			test.start();
			inAnimation = true;
		}
	}
	
	/**
	 *  Demande l'arret du thread (prochain tour de boucle) et dire `a l'ecouteur que l'animation est arreter
	 */
	public void stop() {
		inAnimation=false;
	}
	
	/**
	 *  animation de la simulation du jeu
	 */
	@Override
	public void run() {
		while (inAnimation) {	
			
			repaint();
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}//fin while
		System.out.println("button thread was killed =-;_(OwO_)");
	}
}
