package foundation;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import fourthDim.Point4D;
import gestion.GestionPhysique;
import modelaffichage.ModeleAffichage;
import objects.BlackDomain;
import objects.DimStrike;
import objects.WDwarf;
import obstacle.GererObstacle;
import obstacle.Obstacle;
import physique.ForceElectrique;
import vecteur.Vecteur;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.util.ArrayList;
/**
 * la classe qui s'occupe de la creation d'map du jeu
 * @author XU WEI DUO
 *
 */
public class DummyMap extends JPanel implements Runnable{
	private boolean inAnimation = false;
	private double width,height;
	private boolean first = true;
	private boolean veryFirst = true;
	private ModeleAffichage modele;
	private int selected = 0;
	private String loadPath = null;
	//JPanel
	//private int panelScale = 600;
	//private boolean scaling = false;
	private boolean mouseEntered =false;
	private Image map = null;
	private String mapName;
	
	private BlackDomain domain;
	private DimStrike dimStrike;
	private WDwarf dwarf;
	private GererObstacle obstacles;
	private int obsID = 0;
	//ElectricFiled
	private ForceElectrique forceEl;
	private GestionPhysique force;
	private int fieldIntensity = 0;
	private int eleID = 0;
	
	private Point4D[] objs = new Point4D[4];
	
	private int inScene = 0, DSchoice,dwarfQnt = 0;
	
	private Image beta, zeta;
	private int scaleFactor = 4*2;
	
	private Rectangle2D.Double limit;
	private int limitCut = 40;

	/**
	 * initilialise le Jpanel et les ecouteurs pour le clavier et la souris
	 */
	public DummyMap() {
		setBackground(Color.GRAY);
		OmniMap omnimap = new OmniMap();
		setBounds(0, 0, omnimap.getWidth()/2, omnimap.getHeight()/2);
		//System.out.println("dummyMap dim: "+getWidth()+" and "+getHeight());
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				//begin
				
					selected = check(e.getX(),e.getY());
					//System.out.println("selected");
				
				//fin
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				//begin
				System.out.println("hello");
				mouseEntered = true;
				//fin
			}
			@Override
			public void mouseExited(MouseEvent e) {
				//begin
				mouseEntered = false;
				//fin
			}
		});
		addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				//begin
				
				if(selected!=0)deplace(selected,e.getX(),e.getY());
				//fin
			}
		});
	}
	
	/**
	private void linkImage(String type) {
		Image img = null;
		try {
			System.out.println("choice: "+type);
			
			switch(type) {
				case "Singer": img = ImageIO.read(getClass().getClassLoader().getResource("Singer.PNG"));			 
				break;
				case "Genessis": img = ImageIO.read(getClass().getClassLoader().getResource("Genessis.PNG"));			
				break;
				case "Drope": img = ImageIO.read(getClass().getClassLoader().getResource("Drope.PNG"));
				break;
				case "Reaper": img = ImageIO.read(getClass().getClassLoader().getResource("Reaper.PNG"));
				break;
				case "Covenent": img = ImageIO.read(getClass().getClassLoader().getResource("Covenent.PNG"));
				break;
				case "Meditator": img = ImageIO.read(getClass().getClassLoader().getResource("Meditator.PNG"));
				break;
				case "Decoder": img = ImageIO.read(getClass().getClassLoader().getResource("Decoder.PNG"));
				break;
			}
			img = img.getScaledInstance(img.getWidth(null)/scaleFactor, img.getHeight(null)/scaleFactor, Image.SCALE_SMOOTH);
		} catch (IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Image Load unsuccessful");
		}
		repaint();
	}
	**/
	
	/**
	 * classe boolean qui indique si la souris est entrer dans le JPanel ou non
	 * @return, retourne oui si la souris est enter dans le JPanel
	 */
	public boolean mouseEntered() {
		return mouseEntered;
	}
	
	/**
	 * cette methode permet `a determiner quel objet a ete choisi par le souris
	 * @param x, la position x de la souris
	 * @param y, la position y de la souris
	 * @return retourne l'indice de l'objet choisi
	 */
	private int check(int x, int y) {
		//int boundMax = 50;
		int boundMin = 40;
		
		if(domain!=null && (domain.getCore().getX()+boundMin>x && domain.getCore().getX()-boundMin<x) && (domain.getCore().getY()+boundMin>y && domain.getCore().getY()-boundMin<y)) {
			return 1;
		}

		if(dimStrike!=null && (dimStrike.getCenter().getX()+boundMin>x && dimStrike.getCenter().getX()-boundMin<x) && (dimStrike.getCenter().getY()+boundMin>y && dimStrike.getCenter().getY()-boundMin<y)) {		
					return  2;	
		}

		if(obstacles!=null)
			for(int count=0; count<obstacles.getSize(); count++) {
				if((obstacles.getCenter(count).getX()+boundMin>x && obstacles.getCenter(count).getX()-boundMin<x) && (obstacles.getCenter(count).getY()+boundMin>y && obstacles.getCenter(count).getY()-boundMin<y)) {
					obsID = count;
					return 4;
			}
		}

		if(forceEl!=null) {
			for(int count=0; count<forceEl.listObjet().size(); count++) {
				if((forceEl.listObjet().get(count).getX()+boundMin>x && forceEl.listObjet().get(count).getX()-boundMin<x) && (forceEl.listObjet().get(count).getY()+boundMin>y && forceEl.listObjet().get(count).getY()-boundMin<y)) {
					eleID = count;
					return 5;
				}
			}
				
		}
		return 0;
	}
	
	/**
	 * retourne les objets dans la scene 'a l'aide d'une liste de Point4D
	 * @return, les informations des obts dans la scenes
	 */
	public Point4D[] getObjs() {
		return objs;
	}
	
	/**
	 * retourne les obstacles qui on ete cree dans le map
	 * @return, retourne les obstacles
	 */
	public ArrayList<Obstacle> getObs() {
		if(obstacles!=null) {
			return obstacles.getObsList();
		}else {
			return null;
		}
		
	}
	
	/**
	 * cette methode permet a renvoie arrayList contenant des champs electriques, utiliser pour la sauvegarde 
	 * @return, arrayList contenant des champs electriques
	 */
	public ArrayList[] getEle() {
		if(forceEl!=null) {
			ArrayList[] saveEle = new ArrayList[2];
			saveEle[0] = forceEl.listObjet();
			saveEle[1] = forceEl.listCharge();
			return saveEle;
		}else {
			return null;
		}
		
	}
	
	/**
	 * cette methode permet a renvoie le load path pour une forme4D deja cree
	 * @return, le load path pour une forme4D deja cree
	 */
	public String getDSLoadPath() {
		if(loadPath==null) {
			return null;
		}else {
			return loadPath;
		}
	}
	
	
	/**
	 * dessiner et initialiser tous les objets dans la scene
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		
		
		if(veryFirst) {
			try {
				map = ImageIO.read(getClass().getClassLoader().getResource("Abandon.jpg"));
			} catch (IOException e1) {
				JOptionPane.showMessageDialog(null, "image of map not found");
				e1.printStackTrace();
			}
			width = getWidth();
			height = getHeight();
			veryFirst = false;
			limit = new Rectangle2D.Double(limitCut,limitCut,width-limitCut*2,height-limitCut*2);
		}
			
		
		if(inScene!=0 && first) {
			System.out.println("creat");
			
			modele = new ModeleAffichage(width, height, 0, 0, width,height);
			
			
			switch(inScene) {
			case(1):
			
				domain = new BlackDomain(modele.getMatMC(), width, height,100, true);
				domain.deplace((int)(width)/2, (int)(height)/2);
				domain.setInRot(false);
				objs[0] = new Point4D((int)(width)/2, (int)(height)/2);
				break;
			case(2):
				
				dimStrike = new DimStrike((int)(width)/2, (int)(height)/2,30,200);
				dimStrike.setSciMode(true);
				dimStrike.drawCore(true);
				dimStrike.load(loadPath);
				objs[1] = new Point4D((int)(width)/2, (int)(height)/2,DSchoice);
				break;
			
			case(3):
				dwarf = new WDwarf(modele.getMatMC(),width,height,10,dwarfQnt);
				objs[2] = new Point4D(0,0,dwarfQnt);
				//System.out.println("created");
				//System.exit(0);
				break;
			case(4):
				if(obstacles==null)obstacles = new GererObstacle();
				//obstacles = new GererObstacle();
				obstacles.ajouterObs((int)(width)/2, (int)(height)/2);
				obstacles.deplace(obstacles.getSize()-1,  (int)(width)/2, (int)(height)/2);
				break;
			case(5):
				
				if(forceEl==null)forceEl = new ForceElectrique();
				forceEl.ajouterChargeFixe(new Vecteur((int)(width)/2, (int)(height)/2),fieldIntensity);
				force = new GestionPhysique(null, forceEl);
				
				break;
			}

			if(inScene!=0) {
				first = false;
			}
			
			
		}
		
		g2d.drawImage(map, 0,0,(int)width,(int)height,null);
		g2d.setColor(Color.GREEN);
		g2d.draw(limit);
		if(dwarf!=null)dwarf.draw(g2d,Color.WHITE);
		if(dimStrike!=null)	dimStrike.simpleDraw(g2d,Color.WHITE,true,DSchoice,true);
		if(domain!=null) domain.drawDomain(g2d);
		g2d.setColor(Color.WHITE);
		if(obstacles!=null) {
			obstacles.dessiner(g2d); 
			obstacles.drawCenter(g2d);
		}
		if(force!=null&& forceEl!=null) {
			//force.appliquerForceElectrique(g2d);
			force.dessiner(g2d, width,height);	
			//g2d.setColor(Color.GREEN);
			//Ellipse2D.Double coreEle = new Ellipse2D.Double(forceEl.listObjet().get(0).getX()-10/2, forceEl.listObjet().get(0).getY()-10/2, 10, 10);
			//g2d.draw(coreEle);
		}
		
	}
	
	/**
	 * cette mtehode permet a choisir un map
	 * @param type, le nom du map choisi
	 */
	public void setMap(String type) {
		mapName = type;
		try {
			System.out.println("choice: "+type);
			switch(type) {
				case "Abandon": map = ImageIO.read(getClass().getClassLoader().getResource("Abandon.jpg"));			 
				break;
				case "Cloud": map = ImageIO.read(getClass().getClassLoader().getResource("Cloud.jpg"));		
				break;
				case "Terra": map = ImageIO.read(getClass().getClassLoader().getResource("Terra.jpg"));
				break;
				case "Centaurus": map = ImageIO.read(getClass().getClassLoader().getResource("Centaurus.jpg"));
				break;
			}
			
			//repaint();
		} catch (IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Image Load unsuccessful");
		}
	}
	
	/**
	 * cette methode permet a renvoie le nom du map choisi
	 * @return, le nom du map choisi
	 */
	public String getMapName() {
		return mapName;
	}
	
	/**
	 * cette methode permet a savoir quel objet a ete envoyer a ce JPanel `a travers d'un JPanel Bouton
	 * @param type, le type de l'objet
	 * @param entry,l'indice du choix de l'objet 
	 * @param path, le load path
	 */
	public void setObj(String type, int entry, String path) {
		switch(type) {
		case("BlackDomain"): //System.out.println(type +" in");
		inScene = 1;
			break;
			
		case("DimStrike"): //System.out.println(type +" ["+choice +"] in");
		inScene = 2;
		DSchoice = entry;
		loadPath = path;
			break;
			
		case("WDwarf"): //System.out.println(type +" in");
		inScene = 3;
		dwarfQnt = entry;
		
			break;
		case("Obstacle"):
		inScene = 4;
		//System.out.println("obsInScene");
			break;
		case("Field"):
		inScene = 5;
		fieldIntensity = entry;
			break;
		}
		
		System.out.println("setObjects");
		first=true;
	}
	
	/**
	 * cette methode permet a deplacer un objet
	 * @param selected, l'objet `a replacer
	 * @param x, la destination en x de deplacement
	 * @param y, la destination en y de deplacement
	 */
	private void deplace(int selected,int x, int y) {
		
		
		if(x<limitCut) x= limitCut;
		if(x>width-limitCut) x= (int)width-limitCut;
		if(y<limitCut) y = limitCut;
		if(y>height-limitCut) y=(int)height-limitCut;
		//System.out.println(selected);
		switch(selected) {
		
		case(1):domain.deplace(x, y);
		objs[0] = new Point4D(x,y);
		repaint();
			break;
		case(2):
			dimStrike.deplace(x, y);
		objs[1] = new Point4D(x,y,DSchoice);
		repaint();
			break;
		case(4):
			obstacles.deplace(obsID, x, y);
			break;
		case(5):
			forceEl.setPositionChargeFixe(eleID, x+forceEl.getRayon(), y+forceEl.getRayon());
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
		// TODO Auto-generated method stub
		while (inAnimation) {
			repaint();
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}//fin while
		System.out.println("thread was killed =-;_(OwO_)");
	}

}
