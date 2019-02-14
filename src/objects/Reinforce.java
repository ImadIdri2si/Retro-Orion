package objects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.io.IOException;

import javax.imageio.ImageIO;

import canon.Canon;
import gestion.GestionPhysique;
import vecteur.Vecteur;
import vecteur.VecteurGraphique;
import vehicle.Vehicule;
/**
 * cette classe g��n��re un object de type reinforce, ui permet au vehciule de combiner avec et de pouvoir tirer des missles par la suite
 * @author XU WEI DUO
 *
 */
public class Reinforce {
	private Ellipse2D.Double reinforce;
	private int qntMax;
	private double scaleX, scaleY;
	private double radiusMax, radius;
	private double borderMin,borderX, borderY, limitX, limitY;
	private Vecteur radBeta,radZeta;
	private boolean selfDes = false, destroyed = false;
	private boolean betaGotRein=false, zetaGotRein=false;
	private boolean dimUp;
	private int scaleFactor = 4;
	private Image img = null;
	private double imgScale,imgRot=0;
	private AffineTransform mat, modele;
	private boolean sciMode = false;
	private int type = 1;
	
	/**
	 * constructeur qui specifie tous les valeurs necessaires pour g��n��re un onjet reinforce
	 * @param mat, matrice de transformation
	 * @param width, largeur de la scene
	 * @param height, longueur de la scene
	 * @param radiusEntry, le rayon de l'onjet reinforce
	 * @param first, indiquer si cest la premiere fois ou non
	 */
	public Reinforce (AffineTransform mat, double width, double height, int radiusEntry, boolean first) {
		this(mat, width, height, radiusEntry, first,0);
	}
	
	/**
	 * constructeur qui specifie tous les valeurs necessaires pour g��n��re un onjet reinforce
	 * @param mat, matrice de transformation
	 * @param width, largeur de la scene
	 * @param height, longueur de la scene
	 * @param radiusEntry, le rayon de l'onjet reinforce
	 * @param first, indiquer si cest la premiere fois ou non
	 * @param choice, le choix pour le mode de l'objet reinforce
	 */
	public Reinforce (AffineTransform mat, double width, double height, int radiusEntry, boolean first, int choice) {
		scaleX = mat.getScaleX();
		scaleY = mat.getScaleY();
		modele = new AffineTransform(mat);
		radiusMax = radiusEntry;
		radius = 1;
		limitX = width;
		limitY = height;
		borderMin = 100*scaleX;
		borderX = limitX-borderMin;
		borderY = limitY-borderMin;
		
		setType(choice);
		
		genReinforce(first);
			
	}
	
	/**
	 * cette methode permet a deplacer l'objet reinfroce
	 * @param dx, le deplacement en x
	 * @param dy, le deplacement en y
	 */
	public void deplace(double dx, double dy) {
		reinforce= new Ellipse2D.Double(dx-scaleX*radius/2,dy-scaleY*radius/2,scaleX*radius,scaleY*radius);
	}
	
	/**
	 * cette methode permet a recevoir une dimention de rayon pour l'objet reinforce
	 * @param entry,  une dimention de rayon pour l'objet reinforce
	 */
	public void setRadius(double entry) {
		radius = entry;
	}
	/**
	 * cette methode permet a choisir le type d'objet reinfroce
	 * @param choice, le type d'objet reinfroce
	 */
	public void setType(int choice) {

		switch(choice) {
		case 0:
			if((int)(Math.random()*10)>=5) {
				type = 2;
			}else {
				type = 1;
			}
			break;
		case 1: type = 1;
			break;
		case 2:  type = 2;
			break;
		}
	
		try {
			if(type == 1) {
				img = ImageIO.read(getClass().getClassLoader().getResource("ReinforceTp1.PNG"));
			}else if(type ==2) {
				img = ImageIO.read(getClass().getClassLoader().getResource("ReinforceTp2.PNG"));
			}
			
			this.img = img.getScaledInstance(img.getWidth(null)/scaleFactor, img.getHeight(null)/scaleFactor, Image.SCALE_SMOOTH);
		} catch (IOException e) {
			System.out.println("image of Reinforce is missing");
			e.printStackTrace();
		}
	}
	
	
	/**
	 * cette methode permet a creer un objet reinfroce de facon aleratoire
	 * @param first, indiquer si cest la premiere fois ou non
	 */
	public void genReinforce(boolean first) {
		int dx=0,dy=0,factorX=1,factorY=1;
		
		if((Math.random()*10)>5) {
			factorX = -1;
		}else {
			factorX = 1;
		}
		
		if((Math.random()*10)>5) {
			factorY = -1;
		}else {
			factorY = 1;
		}
		
		if(first) {
			dx = (int)limitX/2;
		}else {
			dx = (int)(borderX/2+factorX*(Math.random()*10)*(Math.random()*30)*(Math.random()*10));
		}
		
		dy = (int)(borderY/2+factorY*(Math.random()*10)*(Math.random()*30)*(Math.random()*10));
		if(dx<borderMin||dx>borderX||dy<borderMin||dy>borderY) {
			int bound = (int)(Math.random()*10);
			if(dx<borderMin)dx = (int)borderMin+bound;
			if(dx>borderX)dx = (int)borderX-bound;
			if(dy<borderMin)dy = (int)borderMin+bound;
			if(dy>borderY)dy = (int)borderY-bound;
		}
		reinforce= new Ellipse2D.Double(dx-scaleX*radius/2,dy-scaleY*radius/2,scaleX*radius,scaleY*radius);
	}
	
	
	
	/**
	 * cette methode permet a dessiner un objet reinforce
	 * @param g2d, le contexte graphique
	 * @param color, la couleur de contour de l'objet
	 */
	public void draw(Graphics2D g2d, Color color) {
		Color colorBack = g2d.getColor();
		double originX=reinforce.getCenterX();
		double originY=reinforce.getCenterY();
		if(radius<radiusMax && !selfDes) {
			radius++;
			reinforce= new Ellipse2D.Double(originX-scaleX*radius/2,originY-scaleY*radius/2,scaleX*radius,scaleY*radius);
		}else if(selfDes && dimUp) {
			zetaGotRein = false;
			betaGotRein = false;
			radius = radius+1;
			reinforce= new Ellipse2D.Double(originX-scaleX*radius/2,originY-scaleY*radius/2,scaleX*radius,scaleY*radius);
			if(radius>=radiusMax*2)dimUp = false;
		}else if(selfDes && !dimUp) {
			zetaGotRein = false;
			betaGotRein = false;
			radius = radius-1;
			reinforce= new Ellipse2D.Double(originX-scaleX*radius/2,originY-scaleY*radius/2,scaleX*radius,scaleY*radius);
			if(radius<=1) {
				destroyed = true;
			}
		}
		if(sciMode) {
			g2d.setColor(color);
			g2d.draw(reinforce);
		}
		if(reinforce!=null) {
			imgScale = radius/radiusMax;
			double coreX = reinforce.getCenterX();
			double coreY = reinforce.getCenterY();
			mat = new AffineTransform(modele);
			mat.translate(coreX-img.getWidth(null)/2, coreY-img.getHeight(null)/2);
			mat.translate((img.getWidth(null)*(1-imgScale))/2,(img.getHeight(null)*(1-imgScale))/2);
			mat.scale(imgScale,imgScale);
			if(selfDes && dimUp) {
				imgRot = (imgRot+30)%360;
			}else {
				imgRot = (imgRot+10)%360;
			}
			mat.rotate(Math.toRadians(imgRot),img.getWidth(null)/2,img.getHeight(null)/2);
			g2d.drawImage(img, mat, null);	
			if(type == 1) {
				g2d.setColor(Color.YELLOW);
			}else if(type == 2) {
				g2d.setColor(Color.RED);
			}
			
		}	
		g2d.setColor(colorBack);
	}
	
	/**
	 * cette methode renvoie le mode de l'objet reinforce
	 * @return, le mode de l'objet reinforce
	 */
	public int getType() {
		return type;
	}
	
	/**
	 * cette methode permet a l'objet reinforce de combiner avec un vehicule
	 * @param beta, le vehicule beta
	 * @param zeta, le vehicule zeta
	 * @param g2d, le contexte graphique
	 */
	public void combine(Vehicule beta, Vehicule zeta, Graphics2D g2d) {
		if(beta.getTailHeadCoordinate()!=null && zeta.getTailHeadCoordinate()!=null ) {
			double betaX, betaY, zetaX, zetaY;
			betaX = beta.getTailHeadCoordinate().getX();
			betaY = beta.getTailHeadCoordinate().getY();
			zetaX = zeta.getTailHeadCoordinate().getX();
			zetaY = zeta.getTailHeadCoordinate().getY();
			radBeta = new Vecteur(betaX-reinforce.getCenterX(), betaY-reinforce.getCenterY());
			radZeta = new Vecteur(zetaX-reinforce.getCenterX(), zetaY-reinforce.getCenterY());
			if(!selfDes && !zetaGotRein && !betaGotRein && sciMode) {
				drawLink(betaX,betaY,radBeta,g2d);
				drawLink(zetaX,zetaY,radZeta,g2d);
			}
			if(!selfDes) {
				if(!betaGotRein && radZeta.module()<radius*1.5 && checkBorder(zetaX,zetaY) && zeta.getSpeed()<zeta.getSpeedMin()+2000) {
					reinforce= new Ellipse2D.Double(zeta.getTailHeadCoordinate().getX()-scaleX*radius/2,zeta.getTailHeadCoordinate().getY()-scaleY*radius/2,scaleX*radius,scaleY*radius);
					zetaGotRein = true;
				}else if(!zetaGotRein && radBeta.module()<radius*1.5 && checkBorder(betaX,betaY) && beta.getSpeed()<beta.getSpeedMin()+2000) {
					reinforce= new Ellipse2D.Double(beta.getTailHeadCoordinate().getX()-scaleX*radius/2,beta.getTailHeadCoordinate().getY()-scaleY*radius/2,scaleX*radius,scaleY*radius);
					betaGotRein = true;
				}else {
					zetaGotRein = false;
					betaGotRein = false;
				}
			}
			
		}
		
	}
	
	/**
	 * cette methode permet a tirer
	 * @param canon, le canon qui contien des missiles
	 * @param veh, le vehicule combine avec l'objet
	 * @param PLtriggered, si e joueur beta a tire
	 * @return, retourne le canon qui a tire
	 */
	public Canon trigger(Canon canon, Vehicule veh, boolean PLtriggered) {
		canon = new Canon(veh.getX(), veh.getY());
		canon.setX(veh.getX()/1000);
		canon.setY(veh.getY()/1000);
		canon.setDirection(veh.getVecMov());
		if(type==1) {
			int speedFactor = 10;
			canon.tirer(veh.getSpeed()*speedFactor);
			
		}else if(type==2) {
			int speedFactor = 20;
			canon.tirerMissileChercheur(veh.getSpeed()*speedFactor);
		}
		if(PLtriggered) {
			canon.tireurA(true);
		}else {
			canon.tireurB(true);
		}
		selfDes();
		return canon;
	}
	
	/**
	 * cette methode permet a ouvrir ou fermer le mode scientifique
	 * @param entry, ouvrir ou fermer le mode scientifique
	 */
	public void setSciMode(boolean entry) {
		sciMode = entry;
	}
	
	/**
	 * cette methode permet a savoir si l'objet est entrain de se detruire
	 * @return, si l'objet est entrain de se detruire
	 */
	public boolean destroyed() {
		return destroyed;
	}
	
	/**
	 * cette methode permet a l'objet de se detruire
	 */
	public void selfDes() {
		selfDes = true;
		dimUp = true;
	}
	
	/**
	 * cette methode renvoie vrai si le vehicule beta a ete combine avec l'objet reinforce
	 * @return, si le vehicule beta a ete combine avec l'objet reinforce
	 */
	public boolean getZetaGotRein() {
		return zetaGotRein;
	}
	
	/**
	 * cette methode renvoie vrai si le vehicule zeta a ete combine avec l'objet reinforce
	 * @return, si le vehicule zeta a ete combine avec l'objet reinforce
	 */
	public boolean getBetaGotRein() {
		return betaGotRein;
	}
	
	/**
	 * cette methode permet a dessiner des vecteurs de liens entre les vehicules et l'objet reinfroce
	 * @param dx, la position en x ou s'en va la fleche du vecteur
	 * @param dy, la position en y ou s'en va la fleche du vecteur
	 * @param rad, le vecteur  de liens entre un vehicule et l'objet reinfroce
	 * @param g2d, le contexe graphique
	 */
	public void drawLink(double dx, double dy,Vecteur rad, Graphics2D g2d) {
		Color back = g2d.getColor();
		VecteurGraphique vecGraph = new VecteurGraphique(rad);
		vecGraph.setOrigineXY(reinforce.getCenterX(), reinforce.getCenterY());
		g2d.setColor(Color.GREEN);
		vecGraph.dessiner(g2d);
		g2d.setColor(back);
	}
	
	/**
	 * cette methode permet a savoir le contour du map, pour que l'objet reste toujours dans ce contour
	 * @param dx, la largeur de la scene
	 * @param dy, la longueur de la scene
	 * @return, retourne vrai si l'objet est dans le contour
	 */
	public boolean checkBorder(double dx, double dy) {
		return (dx>=0 && dx<=limitX && dy>=0 && dy<=limitY);
	}
	
}
