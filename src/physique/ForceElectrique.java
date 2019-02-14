package physique;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.RadialGradientPaint;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import obstacle.Obstacle;
import vecteur.Vecteur;
import vecteur.VecteurGraphique;
/**
 * Classe qui calcul le champs électirque et la force électrique en 2 objets, en lui donnant une charge et les positions
 * @author Imad Idrissi
 */
public class ForceElectrique{
	//Constante de la formule
	private final double CONSTANTE_K = 9e9;
	private final double CONSTANTE_E = 1e-6;
	private final double CHAMPLIMITE = 0.1;
	//Variable
	private Vecteur distance;
	private double chargeQ1;
	private Vecteur positionChargeq1;
	//Zone Champs
	private Ellipse2D zoneChamp, zoneCharge;
	private double diametreCharge = 16;
	private Vecteur champs;
	//Forces
	private Vecteur forceElectrique, forceResultant;
	private double forceRx = 0;
	private double forceRy = 0;
	// Acceleration
	private Vecteur acceleration;
	//ColorGradient
	private RadialGradientPaint color = null;
	//Liste de charges stable
	private ArrayList<Vecteur> listPositionChargeQ2  ;
	private ArrayList<Double> listChargeQ2 ;
	//Image Electron et Proton
	private Image electron, proton;
	private boolean first = true;
	private int scale = 1;
	//Selected
	private  ArrayList<Rectangle2D> listColiderChargeQ2;
	//Mode scientifique
	private boolean modeSci = false;


	
	/**
	 * Constructeur qui initialise les charges des deux particules
	 */
	public ForceElectrique() { 
		this.chargeQ1 = 0;
		forceResultant = new Vecteur(0,0);
		listPositionChargeQ2  = new ArrayList<Vecteur>();
		listChargeQ2 = new ArrayList<Double>();
		listColiderChargeQ2 = new ArrayList<Rectangle2D>();		
	}
	/**
	 * Constructeur qui permet d'initialisé les 
	 * @param entryPos Les positions de la charge électirque 
	 * @param entryCharge La charge des particules 
	 */
	public ForceElectrique( ArrayList<Vecteur> entryPos,ArrayList<Double> entryCharge ) {
		this.chargeQ1 = 0;
		forceResultant = new Vecteur(0,0);
		listPositionChargeQ2  = entryPos;
		listChargeQ2 = entryCharge;
		listColiderChargeQ2 = new ArrayList<Rectangle2D>();
	}
	/**
	 * Méthode qui permet de calculer le champs électique 
	 * @return Le champs electrique
	 */
	public Vecteur champElectrique(int index) {
			if(distance(index).module() <= diametreCharge/2 + 10) {
				champs = new Vecteur(0,0);
			}else {
				champs = (distance(index).multiplie((CONSTANTE_K * listChargeQ2.get(index) * CONSTANTE_E *Math.pow(distance.module(), -3))));
			}
			return champs;
		}
	/**
	 * Methode qui permet de calculer le module du champ
	 * @param index
	 * @return
	 */
	public double moduleChamp(int index) {
		double champMax = 0;
		champMax = (CONSTANTE_K * listChargeQ2.get(index) * CONSTANTE_E *Math.pow(rayonLimiteChamp(index,scale), -3));
		
		return champMax;
	}
		
	/**
	 * Méthode qui calcule le module de la force électrique
	 * @return le module de force
	 */
	public Vecteur forceElectrique() {
		forceRx = 0;
		forceRy = 0;
		for(int i = 0; i < listChargeQ2.size();i++) {
			forceElectrique = champElectrique(i).multiplie(CONSTANTE_E*chargeQ1);
			if(listChargeQ2.size() == 1) {
				forceRx = forceElectrique.getX();
				forceRy = forceElectrique.getY();
			}else {
				forceRx = forceRx + forceElectrique.getX();
				forceRy = forceRy + forceElectrique.getY();
			}
		}
		forceResultant.setXY(forceRx,forceRy);
		return (forceResultant);
	}
	/**
	 * Méthode qui permet de retourner la distance entre l'objet et la particule
	 * @return Vecteur de la distance
	 */
	public Vecteur distance(int index) {
		distance = positionChargeq1.soustrait(listPositionChargeQ2.get(index));
		return (distance);
	}
	public void dessinerVecteurDirection(Graphics2D g2d, double positionX, double positionY) {
		VecteurGraphique vecteurForce = new VecteurGraphique((forceElectrique().getX()),forceElectrique().getY(), positionX, positionY );
		g2d.setColor(Color.black);
		//vecteurForce.dessiner(g2d);
		
	}
	/**
	 * Méthode qui permet de dessiner la zone du champs électrique
	 * @param g2d 
	 */
	public void dessinerZoneChamp(Graphics2D g2d, double width, double heigth) {
		if(first) {
			try {
				electron = ImageIO.read(getClass().getClassLoader().getResource("Electron.png"));
				proton = ImageIO.read(getClass().getClassLoader().getResource("Proton.png"));
				int rayon = 40;
				this.electron = electron.getScaledInstance(rayon,rayon , Image.SCALE_SMOOTH);
				this.proton = proton.getScaledInstance(rayon,rayon , Image.SCALE_SMOOTH);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			first = false;
		}
		for(int i = 0; i < listPositionChargeQ2.size(); i++) {
			zoneChamp = new Ellipse2D.Double(listPositionChargeQ2.get(i).getX()-rayonLimiteChamp(i,scale), listPositionChargeQ2.get(i).getY()-rayonLimiteChamp(i,scale), rayonLimiteChamp(i,scale)*2, rayonLimiteChamp(i,scale)*2);
			zoneCharge = new Ellipse2D.Double(listPositionChargeQ2.get(i).getX()-(diametreCharge/2), listPositionChargeQ2.get(i).getY()-(diametreCharge/2), diametreCharge, diametreCharge);
			listColiderChargeQ2.add(new Rectangle2D.Double(listPositionChargeQ2.get(i).getX()-(diametreCharge/2), listPositionChargeQ2.get(i).getY()-(diametreCharge/2), diametreCharge*3, diametreCharge*3));
			//Image Scale 
			
		    float[] dist = {0.0f,1f};
		    if(rayonLimiteChamp(i,scale) != 0 ) {
		    	if(listChargeQ2.get(i) > 0) {
		    		Color[] colors = {new Color(225,0,0,200), new Color(255,178,178,10)};
			    	color = new RadialGradientPaint((float)(listPositionChargeQ2.get(i).getX()-(diametreCharge/2)),(float)(listPositionChargeQ2.get(i).getY()-diametreCharge/2), (float)rayonLimiteChamp(i,scale), dist, colors );
			    	g2d.setPaint(color);
				g2d.fill(zoneChamp);
			    	g2d.drawImage(proton,(int)(listPositionChargeQ2.get(i).getX()-(electron.getWidth(null)/2)),(int)(listPositionChargeQ2.get(i).getY()-(electron.getHeight(null)/2)),null);
		    	}else {
		    		Color[] colors = {new Color(8,0,255,200), new Color(208,206,250,10)};
			    	color = new RadialGradientPaint((float)(listPositionChargeQ2.get(i).getX()-(diametreCharge/2)),(float)(listPositionChargeQ2.get(i).getY()-diametreCharge/2), (float)rayonLimiteChamp(i,scale), dist, colors );
			    	g2d.setPaint(color);
				g2d.fill(zoneChamp);
				g2d.drawImage(electron,(int)(listPositionChargeQ2.get(i).getX()-(proton.getWidth(null)/2)),(int)(listPositionChargeQ2.get(i).getY()-(proton.getHeight(null)/2)),null);

		    	}
		    	
		    }
			//g2d.fill(zoneCharge);
			//g2d.dispose();
			g2d.setColor(Color.black);
			//g2d.fill(zoneCharge);
			
		}
		
	}
	/**
	 * Méthode qui permet de modifier la position de l'objet 1
	 * @param position La nouveau vecteur position 
	 */
	public void setChargeq1(Vecteur position, double charge) {
		positionChargeq1 = position;
		chargeQ1 = charge;
	}
	/**
	 * Méthode qui permet de modifier la position de l'objet 2
	 * @param position La nouveau vecteur position 
	 */
	public void ajouterChargeFixe(Vecteur position, double charge) {
	//	positionChargeQ2 = position ;
	//	this.chargeQ2 = charge;
		listPositionChargeQ2.add(position);
		listChargeQ2.add(charge);
	}
	public void setPositionChargeFixe(int index, double positionX, double positionY) {
		listPositionChargeQ2.get(index).setXY(positionX-(diametreCharge/2), positionY-(diametreCharge/2));
	}
	/**
	 * M�thode qui retourne la liste d'objet avce leur position 
	 * @return listPositionChargeQ2
	 */
	public ArrayList<Vecteur> listObjet(){
		return listPositionChargeQ2;
	}
	/**
	 * M�thode qui retourne la liste d'objet avec leur charge
	 * @return  listChargeQ2
	 */
	public ArrayList<Double> listCharge(){
		return listChargeQ2;
	}
	/**
	 * M�thode qui permet d'agrandir les charges
	 * @param scale
	 */
	public void scalePosition(int scale) {
		for(int count = 0; count< listPositionChargeQ2.size();count++) {
			listPositionChargeQ2.get(count).setXY(listPositionChargeQ2.get(count).getX()*scale, listPositionChargeQ2.get(count).getY()*scale);
			listPositionChargeQ2.get(count).setX(listPositionChargeQ2.get(count).getX());
			listPositionChargeQ2.get(count).setY(listPositionChargeQ2.get(count).getY());
		}
	}
	
	/**
	 * Méthode calcul la distance au le champs et minimale
	 * @return retroune la distance
	 */
	public double rayonLimiteChamp(int index, int scale) {
		//En Km 
		return (Math.sqrt(((CONSTANTE_K*Math.abs(listChargeQ2.get(index)))/CHAMPLIMITE))/10000)*scale;
	}
	/**
	 * Méthode qui permet de calculer directement l'acceleration selon la masse de l'objet
	 * @param masse
	 * @return
	 */
	public Vecteur acceleration(double masse) {
		acceleration = forceElectrique().multiplie(1/masse);
		return acceleration;
	}	
	
	/**
	 * M�thode qui permet d'avoir le rayon de la charge 
	 * @return rayon
	 */
	public double getRayon() {
		return diametreCharge/2;
	}
	/**
	 * M�thode qui permet de modifier la taille de la particule 
	 * @param factor
	 */
	public void setRayon(int factor) {
		//rayonLimiteChamp(index) * factor;
		scale = factor;
	}
	/**
	 * Methode qui permet d'avoir les formes de la charge
	 * @return une List de collider
	 */
	public ArrayList<Rectangle2D> getListCollider(){
		return listColiderChargeQ2;
	}
	/**
	 * Methode qui permet d'avoir le module de champs selon un index
	 * @param index l'index rechercher de la charge 
	 * @return Retourne le module du champs 
	 */
	public double getChampMax(int index) {
		return moduleChamp(index);
	}
	/**
	 * Methode qui permet d'afficher le vecteur force
	 * @param modeSci
	 */
	public void setModeSci(boolean modeSci) {
		this.modeSci = modeSci;
	}
}
