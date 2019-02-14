package JPanelButton;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.event.EventListenerList;
import javax.swing.filechooser.FileNameExtensionFilter;

import fourthDim.Forme4D;
import modelaffichage.ModeleAffichage;
import objects.BlackDomain;
import objects.DimStrike;
import objects.Reinforce;
import objects.WDwarf;
import obstacle.GererObstacle;
import obstacle.Obstacle;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Ellipse2D;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
/**
 * la classe qui s'occupe de du bouton JPanel
 * @author XU WEI DUO
 *
 */
public class PanelButton extends JPanel implements Runnable{
	private static final long serialVersionUID = 1L;
	private String type="";
	private int choice = 0;
	private boolean inAnimation = false;
	private double width,height;
	private boolean first = true;
	private boolean MouseeExist = false;
	private boolean deactivate = false;
	private boolean disabledDragged = false, firstDrag = false;
	private boolean textMode = false;
	private String textAlpha, textBeta, textToPrint;
	private ModeleAffichage modele;
	//DimStrike
	private boolean playForward = false;
	private DimStrike dimStrike ;
	private String loadPath = "";
	private int DSdimMin=30, DSdimMax=60;
	//BlackDomain
	private BlackDomain blackDomain;
	private boolean domainUp = false;
	//WDwarf
	private WDwarf dwarf;
	//Obstacles
	private GererObstacle obstacles;
	//reinfroce
	Reinforce reinforce;
	//Objs Liste
	private final EventListenerList OBJS = new EventListenerList();
	/**
	 * initilialise le Jpanel et les ecouteurs pour la souris
	 */
	public PanelButton() {//int x, int y, int width, int height) {
		setBackground(Color.WHITE);
		setBounds(0, 0, 100, 100);
		addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				if(!disabledDragged) {
					switch(type) {
					case("DimStrike"): dimStrike.deplace(e.getX(), e.getY());
						break;
					case("BlackDomain"):blackDomain.deplace(e.getX(), e.getY());
						break;
					case("Obstacle"):
						obstacles.deplace(0,  e.getX(), e.getY());
						break;
					}
					//System.out.println(e.getX() +" and "+e.getY()+"in to | origin: ["+x+","+y+"] to ["+width+","+height+"]");
					if(MouseeExist && firstDrag) {// && (e.getX()>=x && e.getX()<=width) && (e.getY()>=y && e.getY()<=width)) {
						//setBackground(Color.DARK_GRAY);
						objSelected();
						stop();
						//firstDrag = false;
					}
					repaint();
				}
				
			}
		});
		
		addMouseListener(new MouseAdapter() {
		
			@Override
			public void mouseClicked(MouseEvent e) {
				//begin
				System.out.println("clicked");
				switch(type) {
				case("Reinforce"): 
					switch(reinforce.getType()) {
					case 1: reinforce.setType(2);
					break;
					case 2: reinforce.setType(1);
					}
					break;
				}
				repaint();
				//fin
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				//System.out.println("begin");
				playForward = true;
				MouseeExist = false;
				firstDrag = true;
				if(!inAnimation) {
					begin();
					
					replace();
					//setBackground(Color.WHITE);
				}
				
				switch(type) {
				case("DimStrike"):
					break;
				case("BlackDomain"):domainUp = true;
					break;
				}
				textToPrint = textBeta;
			}
			@Override
			public void mouseExited(MouseEvent e) {
				//System.out.println("stop");
				playForward = false;
				MouseeExist = true;
				switch(type) {
				case("DimStrike"):dimStrike.deplace((int)(width)/2, (int)(height)/2);
					break;
				case("BlackDomain"):blackDomain.deplace((int)(width)/2, (int)(height)/2);
				domainUp = false;
					break;
				case("Obstacle"):obstacles.deplace(0,  (int)(width)/2, (int)(height)/2);
					break;
				}
				textToPrint = textAlpha;
			}
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(MouseeExist) {
					//setBackground(Color.LIGHT_GRAY);
				}
			}
		});
		
	}
	
	/**
	public void setScaleObs(int factor) {
		if(obstacles!=null)obstacles.setScaleAll(factor);
	}
	**/
	
	/**
	 * cette methode permet a disable la detection dragged des objets
	 * @param entry,  si disable la detection dragged des objets
	 */
	public void disabledDragged(boolean entry) {
		disabledDragged = entry;
	}
	
	public void setTextMode(boolean entry, String test1, String test2) {
		textMode = entry;
		textAlpha = test1;
		textBeta= test2;
		textToPrint = textAlpha;
	}
	
	/**
	 * cette methode permet a mettre un objet dans la place centrale de la scene
	 */
	public void replace() {
		switch(type) {
		case("DimStrike"):dimStrike.deplace((int)(width)/2, (int)(height)/2);
			break;
		case("BlackDomain"):blackDomain.deplace((int)(width)/2, (int)(height)/2);
			break;
		case("Obstacle"):
			obstacles.deplace(0,  (int)(width)/2, (int)(height)/2);
			break;
		}
	}
	/**
	 * pour savoir si il y eu un mouvment de dragge par la souris
	 * @param entry, oui ou non il y a eu un deagge
	 */
	public void setDragged(boolean entry) {
		firstDrag = entry;
	}
	
	/**
	 * cette methode ajoute un ecouteur ObjListener
	 * @param objEcout, l'ecouteur ObjListener
	 */
	public void addObjListener(PanelButtonListener objEcout) {
		OBJS.add(PanelButtonListener.class, objEcout);
	}
	
	/**
	 * cette methode permet `a lever un evenement
	 */
	public void objSelected() {
		for(PanelButtonListener ecout: OBJS.getListeners(PanelButtonListener.class)) {
			ecout.selected(type,choice,loadPath);
		}
	}
	
	/**
	 * cette methode permet `a determiner quel est le type et l`indice de l`objet que le JPanel bouton prend en charge
	 * @param type, le type de l`objet qu`il prend en charge
	 * @param choice, l`indice ed l`object qu`il prend en charge
	 */
	public void setType(String type,int choice) {
		this.type = type;
		this.choice = choice;
		first = true;
		if(type=="DimStrike" || type=="BlackDomain") {
			begin();
		}else {
			repaint();
		}
	}
	
	/**
	 * cette methode permet a donner une nouvelle dimention min et max a une forme4D
	 * @param DSdimMin, une nouvelle dimention min
	 * @param DSdimMax, une nouvelle dimention max
	 */
	public void setDSdim(int DSdimMin, int DSdimMax) {
		this.DSdimMin = DSdimMin;
		this.DSdimMax = DSdimMax;
		first = true;
		repaint();
	}
	
	/**
	 * cette methode permet `a determiner quel est le type de l`objet que le JPanel bouton prend en charge
	 * @param type, le type de l`objet qu`il prend en charge
	 */
	public void setType(String type) {
		this.type = type;
		if(type=="DimStrike" || type=="BlackDomain") {
			begin();
		}else {
			repaint();
		}
	}
	
	/**
	 * cette methode permet `a preciser l`indice du type de l`objet choisi
	 * @param entry, le nouveau indice du type de l`objet choisi
	 */
	public void setDSChoice(int entry) {
		this.choice = entry;
		if(dimStrike!=null) dimStrike.load("");
		loadPath="";
		if(!inAnimation) begin(); replace();
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
			modele = new ModeleAffichage(width, height, 0, 0, width,height);
			switch(type) {
			case("DimStrike"): dimStrike = new DimStrike((int)(width)/2, (int)(height)/2,DSdimMin,DSdimMax);
							dimStrike.setSciMode(true);
				break;
			case("BlackDomain"): blackDomain = new BlackDomain(modele.getMatMC(), width, height,20, true);
									blackDomain.deplace((int)(width)/2, (int)(height)/2);
									blackDomain.setInRot(false);
								
				break;
			case("WDwarf"): 
				if(choice==0) {
				dwarf = new WDwarf(modele.getMatMC(),width,height,10,400);
			}else {
				dwarf = new WDwarf(modele.getMatMC(),width,height,10,choice);
			}
				
				break;
			case("Obstacle"):
				obstacles = new GererObstacle();
			
				obstacles.ajouterObs((int)(width)/2, (int)(height)/2);
				
				//obstacles.ajouterObs(0,0);
				//double x = (int)(obstacles.GetDim().getX());
				//double y = (int)(obstacles.GetDim().getY());
				obstacles.deplace(0,  (int)(width)/2, (int)(height)/2);
				break;   
			case("Reinforce"):
				reinforce = new Reinforce(modele.getMatMC(),width,height, 10, false);
				reinforce.setRadius(10);
				reinforce.deplace((int)(width)/2, (int)(height)/2);
				//if(!inAnimation)begin();
				break;
			}
			
			
			first = false;
		}
		
		//if(inAnimation) {
			switch(type) {
			case("DimStrike"): dimStrike(g2d);
				break;
			case("BlackDomain"): blackDomain.DrawDomainSimple(g2d,domainUp);
				break;
			case("WDwarf"): dwarf.draw(g2d,Color.GRAY);
				break;
			case("Obstacle"): obstacles.dessiner(g2d);
			g2d.fill(new Ellipse2D.Double(obstacles.getCenter(0).getX()-10/2,obstacles.getCenter(0).getY()-10/2,10,10));
				break;
			case("Reinforce"):
				reinforce.draw(g2d, Color.WHITE);
		
				break;
			}
		//}
			if(textMode) {
				drawString(textToPrint,43, g2d,Color.WHITE, (int)(width/2),40);
			}
			
			
		
	}
	
	/**
	 * cette methode permet a faire un drawString de facon que la phrase ou la lettre soit toujours centrer
	 * @param entry, le mot ou la phrase de String
	 * @param size, la grandeur des mots 
	 * @param g2d, le contexe graphique
	 * @param color, la couleur des mots
	 * @param dx, la position en x du centre de la phrase ou de mot
	 * @param dy, la position en y du centre de la phrase ou de mot
	 */
	public void drawString(String entry, int size, Graphics2D g2d, Color color, int dx, int dy) {
		Color back = g2d.getColor();
		g2d.setFont(new Font("OCR A Extended", Font.PLAIN, size)); 
		String reminder = entry;
		int reminderLength = g2d.getFontMetrics().stringWidth(reminder);
		g2d.setColor(color);
		g2d.drawString(reminder,dx-reminderLength/2, dy);
		g2d.setColor(back);
	}
	
	/**
	 * cette mtehode permet `a dessiner uniquement un objet de type DimStrike
	 * @param g2d,  contexte graphique
	 */
	private void dimStrike(Graphics2D g2d) {
		
		dimStrike.setSciMode(true);
		if(playForward) {
			dimStrike.simpleDraw(g2d,Color.GRAY,true,choice,true);
		}else {
			dimStrike.simpleDraw(g2d,Color.GRAY,false,choice,true);
			if(dimStrike.getScale()<1) {		
				stop();	
			}
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
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}//fin while
		System.out.println("button thread was killed =-;_(OwO_)");
	}
	
	/**
	 * cette methode permet `a faire un load pour une forme deja cree
	 */
	public void loadForme4D() {
		loadPath = this.dimStrike.load();
		if(!inAnimation) begin(); replace();
	}

}
