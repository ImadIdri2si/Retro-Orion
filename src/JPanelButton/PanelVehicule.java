package JPanelButton;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.event.EventListenerList;

import mapListeners.InfoBarListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
/**
 * la classe qui s'occupe de du bouton vehicule
 * @author XU WEI DUO
 *
 */
public class PanelVehicule extends JPanel implements Runnable{
	private Image img = null;
	private Color imgColor, baseColor = Color.DARK_GRAY;
	private int scaleFactor = 4;
	private String type;
	private final EventListenerList OBJS = new EventListenerList();
	private AffineTransform mat;
	
	private boolean dimUp = false;
	private boolean inAnimation = false;
	private boolean first = true;
	private double imgScale=1;

	/**
	 * initilialise le Jpanel et les ecouteurs pour la souris 
	 * @param entry, le nom du vehicule
	 */
	public PanelVehicule(String entry) {
		setBackground(baseColor);
		setSize(254/scaleFactor,400/scaleFactor);
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//begin
				if(e.getClickCount()==3 && type == "Drope") {
					setType("Zeroinger");
					select();
					JOptionPane optionPane = new JOptionPane("    00400 003010 0011211 04011 111310 "
							+ "\n          131001 10141 111310 "
							+ "\n    131001 10141 111310 131001 10141");
					JDialog dialog = optionPane.createDialog("NLXANIR");
					dialog.setAlwaysOnTop(true);
					dialog.setVisible(true);
					//JOptionPane.showMessageDialog(null, );
				}else {
					vehiculeChosen();
				}
				
			
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
			
		});
		this.type = entry;
		linkImage(type);
		//setBackground(new Color(0,0,0,0));
		
	}

	/**
	 * cette methode permet a initialise le type du vehicule que ce bouton represente
	 * @param entry, le nom du vehicule que ce bouton represente
	 */
	public void setType(String entry) {
		this.type = entry;
		linkImage(type);
		repaint();
	}
	
	/**
	 * cette methode precise quoi faire si ce boutton a ete selectionner
	 */
	public void select() {
		setBackground(imgColor);
		Border lineborder = BorderFactory.createLineBorder(Color.WHITE, 2);
		setBorder(lineborder);
	}
	

	/**
	 * cette methode precise quoi faire si ce boutton a ete deselectionner
	 */
	public void deselect() {
		setBackground(baseColor);
		setBorder(null);
	}
	
	/**
	 * cette methode permet a charger une image avec cette bouton vehicule
	 * @param type, le nom du vehicule
	 */
	public void linkImage(String type) {
		try {
			System.out.println("choice: "+type);
			switch(type) {
				case "Singer": img = ImageIO.read(getClass().getClassLoader().getResource("Singer.PNG"));			 
				imgColor = new Color(245,153,153);
				break;
				case "Genessis": img = ImageIO.read(getClass().getClassLoader().getResource("Genessis.PNG"));		
				imgColor = new Color(251,197,235);
				break;
				case "Drope": img = ImageIO.read(getClass().getClassLoader().getResource("Drope.PNG"));
				imgColor = new Color(204,204,204);
				break;
				case "Reaper": img = ImageIO.read(getClass().getClassLoader().getResource("Reaper.PNG"));
				imgColor = new Color(22,154,14);
				break;
				case "Covenent": img = ImageIO.read(getClass().getClassLoader().getResource("Covenent.PNG"));
				imgColor = new Color(92,0,173);
				break;
				case "Meditator": img = ImageIO.read(getClass().getClassLoader().getResource("Meditator.PNG"));
				imgColor = new Color(203,35,87);
				break;
				case "Decoder": img = ImageIO.read(getClass().getClassLoader().getResource("Decoder.PNG"));
				imgColor = new Color(0,72,203);
				break;
				case "Zeroinger": img = ImageIO.read(getClass().getClassLoader().getResource("Zeroinger.PNG"));
				imgColor = new Color(252,189,255);
				break;
				default:
				img = ImageIO.read(getClass().getClassLoader().getResource("Unknow.PNG"));
				imgColor = new Color(80,80,80);
				break;
			}
			this.img = img.getScaledInstance(img.getWidth(null)/scaleFactor, img.getHeight(null)/scaleFactor, Image.SCALE_SMOOTH);
			first = true;
		} catch (IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Image Load unsuccessful");
		}
		repaint();
	}
	
	/**
	 * cette methode ajoute un ecouteur PanelVehiculeListener
	 * @param objEcout, l'ecouteur PanelVehiculeListener
	 */
	public void addPanelVehiculeListener(PanelVehiculeListener objEcout) {
		OBJS.add(PanelVehiculeListener.class, objEcout);
	}
	
	/**
	 * cette methode permet a lever un evenement
	 */
	public void vehiculeChosen() {
		for(PanelVehiculeListener ecout: OBJS.getListeners(PanelVehiculeListener.class)) {
			ecout.choiceVehicule(type);
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
			setSize(img.getWidth(null),img.getHeight(null));
			mat = new AffineTransform();
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
		//g2d.drawImage(img, 0,0, null);
		g2d.drawImage(img, mat, null);
		//g2d.setColor(Color.WHITE);
		//g2d.drawString("P1",(int)getWidth()/2-10, (int)getHeight()/2);
		//g2d.drawString("fct: "+(imgScale), (int)getWidth()/2-10, (int)getHeight()/2);
		
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
