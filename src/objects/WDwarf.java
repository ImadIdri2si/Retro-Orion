package objects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;

import vecteur.Vecteur;
import vehicle.Vehicule;

/**
 * cette classe g¨¦n¨¨re des particules que les v¨¦hicules pourrons avaler pour allonger leures queues
 * @author XU WEI DUO
 *
 */
public class WDwarf {

	private Ellipse2D.Double[] dwarfs;
	private int qntMax, qntCounter;
	private double scaleX, scaleY;
	private double radius;
	private double limitX, limitY;
	
	/**
	 * constructeur qui specifie tous les valeurs necessaires pour g¨¦n¨¨re les particules
	 * @param mat, matrice de transformation
	 * @param width, largeur de la sc¨¨ne
	 * @param height, hauteur de la sc¨¨ne
	 * @param radiusEntry, le rayon des particules
	 * @param qntEntry, la quantit¨¦ des particules ¨¤ g¨¦n¨¦rer
	 */
	public WDwarf (AffineTransform mat, double width, double height, int radiusEntry, int qntEntry) {
		qntMax = qntEntry;
		qntCounter = qntMax;
		scaleX = mat.getScaleX();
		scaleY = mat.getScaleY();
		dwarfs = new Ellipse2D.Double[qntMax];
		radius = radiusEntry;
		limitX = width;
		limitY = height;
		
		
		for(int count = 0; count<qntMax; count++) {
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
			dx = (int)(limitX/2+factorX*(Math.random()*10)*(Math.random()*30)*(Math.random()*10));
			dy = (int)(limitY/2+factorY*(Math.random()*10)*(Math.random()*30)*(Math.random()*10));
			if(dx<0||dx>limitX||dy<0||dy>limitY) {
				int bound = (int)(Math.random()*10);
				if(dx<0)dx = 0+bound;
				if(dx>limitX)dx = (int)limitX-bound;
				if(dy<0)dy = 0+bound;
				if(dy>limitY)dy = (int)limitY-bound;
			}
			dwarfs[count]= new Ellipse2D.Double(dx-scaleX*radius/2,dy-scaleY*radius/2,scaleX*radius,scaleY*radius);
		}
		
		if(dwarfs[qntMax-1]!=null)System.out.println("WDwarf all created");
	}
	
	/**
	 * cette methode gere l`eruption des wDwrafs
	 * @param centerX, le centre en x de l'eruption
	 * @param centerY, le centre en y de l'eruption
	 * @param coreRadius, le rayonde l'eruption
	 */
	public void spray( double centerX, double centerY, double coreRadius) {
		//for(int alpha = 0; alpha<qntEntry; alpha++) {
			for(int count = 0; count<dwarfs.length; count++) {
				if(dwarfs[count]==null) {
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
					
					dx = (int)(centerX+factorX*coreRadius*(Math.random()/10));
					dy = (int)(centerY+factorY*coreRadius*(Math.random()/10));
					
					if(dx<0||dx>limitX||dy<0||dy>limitY) {
						int bound = (int)(Math.random()*10);
						if(dx<0)dx = 0+bound;
						if(dx>limitX)dx = (int)limitX-bound;
						if(dy<0)dy = 0+bound;
						if(dy>limitY)dy = (int)limitY-bound;
					}
					
					
					
					dwarfs[count]= new Ellipse2D.Double(dx-scaleX*radius/2,dy-scaleY*radius/2,scaleX*radius,scaleY*radius);

					return;
				}
			}
		//}
	}
	

	/**
	 * cette m¨¦thode permet ¨¤ desinner les particules
	 * @param g2d, contexte graphique
	 * @param color, la couleur des dwarfs
	 */
	public void draw(Graphics2D g2d, Color color) {
		Color colorBack = g2d.getColor();
		for(int count = 0; count<qntMax; count++) {
			if(dwarfs[count]!=null) {
				g2d.setColor(color);
				g2d.fill(dwarfs[count]);
			}
		}
		g2d.setColor(colorBack);
	}
	
	/**
	 * cette methode retourn le rayon de chaque white Dwarfs 
	 * @return, le rayon de chaque white Dwarfs 
	 */
	public double getRadius() {
		return radius;
	}
	
	/**
	 * cette methode renvoie la quantite de WDwarfs actuel
	 * @return, retourne la quantite de WDwarfs actuel
	 */
	public int getQnt() {
		return qntCounter;
	}
	
	/**
	 * cette methode renvoie la quantite de WDwarfs max
	 * @return, retourne la quantite de WDwarfs max
	 */
	public int getQntMax() {
		return qntMax;
	}
	/**
	public Boolean collision(double x, double y, double width) {
		for(int count = 0; count<qnt; count++) {
			if(dwarfs[count]!=null) {
				//Area dwarfCol = new Area(dwarfs[count]);
				//dwarfCol.intersect(collider);
				
				if(new Vecteur(dwarfs[count].getCenterY()-y,dwarfs[count].getCenterX()-x).module()<=width) {
				//if(!dwarfCol.isEmpty()) {
					dwarfs[count]= null;
					return true;
				}
				
			}
		}
		return false;
	}
	**/
	
	/**
	 * cette methode permet `a detecter la collision entre deux vehicules et les WDwarfs
	 * @param beta, le vehicule beta
	 * @param zeta, le vehicule zeta
	 */
	public void collision(Vehicule beta, Vehicule zeta) {
		for(int count = 0; count<qntMax; count++) {
			if(dwarfs[count]!=null) {
				if(beta.getCollider().contains(dwarfs[count].getCenterX(),dwarfs[count].getCenterY())) {
					if(beta.getTailDim()<beta.getTailLimit()) {
						dwarfs[count]= null;
						qntCounter--;
						beta.tailExtend();
					}
					
				}else if(zeta.getCollider().contains(dwarfs[count].getCenterX(),dwarfs[count].getCenterY())) {
					if(zeta.getTailDim()<zeta.getTailLimit()) {
						dwarfs[count]= null;
						qntCounter--;
						zeta.tailExtend();
					}
					
				}
			}
		}
	}
	
	/**
	 * cette methode permet a savoir si une particule a ete avalee
	 * @param collider, le collider du v¨¦hicule
	 * @return, retourne vrai si une particule a ete avalee
	 */
	public Boolean collision(Area collider) {
		for(int count = 0; count<qntMax; count++) {
			if(dwarfs[count]!=null) {
				//Area dwarfCol = new Area(dwarfs[count]);
				//dwarfCol.intersect(collider);
				if(collider.contains(dwarfs[count].getCenterX(),dwarfs[count].getCenterY())) {
				//if(!dwarfCol.isEmpty()) {
					dwarfs[count]= null;
					qntCounter--;
					return true;
				}
				
			}
		}
		return false;
	}
}
