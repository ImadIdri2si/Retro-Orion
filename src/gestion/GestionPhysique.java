package gestion;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

import canon.Canon;
import physique.ForceElectrique;
import vecteur.Vecteur;
/**
 * Classe qui permet d'appliquer les forces sur un objets 
 * @author Imad Idrissi
 */
public class GestionPhysique {
	private ForceElectrique forceElectrique;
	private Canon canon;
	private boolean trouver = false;
	private int compteur = 0;
	private int indexRechercher;
	private boolean chargeFind = false;
	private boolean aucunElement = false;
	/**
	 * Constructeur qui prend la force et le canon afin de creer
	 * @param canon 
	 * @param forceElectrique
	 */
	public GestionPhysique(Canon canon, ForceElectrique forceElectrique) {
		this.forceElectrique = forceElectrique;
		this.canon = canon;
	}
	/**
	 * M�thode qui permet de dessiner la zone de champs
	 * @param g2d
	 * @param width grandeur du panel en x
	 * @param height grandeur du panel en y
	 */
	public void dessiner(Graphics2D g2d, double width, double height) {
		forceElectrique.dessinerZoneChamp(g2d, width , height);
	}
	/**
	 * M�thode qui permet de dessiner le missile sous l'effet du champ
	 * @param g2d
	 */
	public void appliquerForceElectrique(Graphics2D g2d){
		for(int i = 0; i < canon.getMissileList().size(); i++) {
			forceElectrique.setChargeq1(canon.getMissileList().get(i).getPosition(),canon.getMissileList().get(i).getChargeQ());
			canon.getMissileList().get(i).setAccelerationX(forceElectrique.acceleration(1).getX());
			canon.getMissileList().get(i).setAccelerationY(forceElectrique.acceleration(1).getY());
			canon.getMissileList().get(i).setForce(forceElectrique.forceElectrique());
			forceElectrique.dessinerVecteurDirection(g2d, canon.getMissileList().get(i).getPosition().getX()+canon.getMissileList().get(i).getRayon(), canon.getMissileList().get(i).getPosition().getY()+canon.getMissileList().get(i).getRayon());
		}
	}
	/**
	 * Methode qui permet d'avoir l'index sur la particule rechercher
	 * @param e
	 */
	 public void clickOnCharge(MouseEvent e) {
		trouver = false;
		compteur = 0;
		while(!trouver){
			if(!forceElectrique.getListCollider().isEmpty()) {
			
				
				if(forceElectrique.getListCollider().get(compteur).contains(e.getX(), e.getY())){
					//trouver = true;
					chargeFind = true;
					indexRechercher = compteur;
					break;
				}else {
					chargeFind = false;
					trouver = false;
				}
				
			}
			compteur = compteur + 1;
			if((compteur == forceElectrique.getListCollider().size()|| (forceElectrique.getListCollider().size()== 0))) {
				aucunElement = true;
				break;
			}
			
		}
	}
	 /**
	  * Methode qui permet d'avoir la charge selon l'index recherché
	  * @return
	  */
	public double getCharge() {
		 double charge = 0;
			if(chargeFind) {
				if(indexRechercher < forceElectrique.getListCollider().size()) {
					charge = forceElectrique.listCharge().get(indexRechercher);
					
				}else {
					chargeFind = false;	
				}
			}else if(aucunElement) {
				charge = 0;
			}
			return charge;
	 }
	 /**
	  * Methode qui permet d'avoir le champ maximal selon un index 
	  * @return retourne le module du champ selon index
	  */
	 public double getChampMax(){
		 double champ = 0;
			if(chargeFind) {
				if(indexRechercher < forceElectrique.getListCollider().size()) {
					champ = forceElectrique.getChampMax(indexRechercher);
				}else {
					chargeFind = false;	
				}
			}else if(aucunElement) {
				champ  = 0;
			}
			return champ ;
	 }
}
