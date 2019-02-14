package foundation;

import java.io.Serializable;
import java.util.ArrayList;

import fourthDim.Point4D;
import obstacle.GererObstacle;
import obstacle.Obstacle;
import physique.ForceElectrique;
import vecteur.Vecteur;
/**
 * cette classe est le fichier pour le sauvegarde
 * @author XU WEI DUO
 *
 */
public class MapSavior implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Point4D[] objsSave;
	private ArrayList<Obstacle> obsSave;
	
	private ArrayList<Vecteur> entryPos;
	private ArrayList<Double> entryCharge;
	
	private String betaSave, zetaSave, dsLoadPathSave=null, mapNameSave;
	private int min, sec;
	private int reinforceTypeSave=0,reinforceCoolDownSave=4;
	
	/**
	 * cette methode permet a entrgistrer les informations des objets dans la scene du jeu
	 * @param objs, les informations des objets dans la scene du jeu enregistrer dans une liste Point4D
	 */
	public void setObjs(Point4D[] objs) {
		this.objsSave = objs;
	}

	/**
	 * cette methode permet a renvoyer l'entrgistre les informations des objets dans la scene du jeu
	 * @return, l'entrgistre les informations des objets dans la scene du jeu
	 */
	public Point4D[] getObjs( ) {
		return objsSave;
	}
	
	/**
	 * cette methode permet a entrgisrer un arraylist d'obstacle
	 * @param obs, un arraylist d'obstacle
	 */
	public void setObs(ArrayList<Obstacle> obs) {
		this.obsSave = obs;
		
	}
	
	/**
	 * cette methode permet a retoruner un arraylist d'obstacle
	 * @return, un arraylist d'obstacle
	 */
	public ArrayList<Obstacle> getObs() {
		return obsSave;
	}

	/**
	 * cette methode permet a enregistrer les informations pour les champs electrique
	 * @param entryPos, arraylist des positions des champs ele
	 * @param entryCharge, arraylist des chrages des champs ele
	 */
	public void setEle(ArrayList<Vecteur> entryPos,ArrayList<Double> entryCharge) {
		this.entryPos = entryPos;
		this.entryCharge = entryCharge;
	}
	
	/**
	 * cette methode renvoie les informations pour les champs electrique dans une list d'arraylist
	 * @return, une liste de arraylist
	 */
	public ArrayList[] getEle() {
		if(entryCharge!=null && entryPos!=null) {
			ArrayList[] saveEle = new ArrayList[2];
			saveEle[0] = entryPos;
			saveEle[1] = entryCharge;
			return saveEle;
		}else {
			return null;
		}
		
	}
	
	/**
	 * cette methode permet a enregistrer le nom du vehicule choisi par le joueur beta
	 * @param beta,  le nom du vehicule choisi par le joueur beta
	 */
	public void setPlayer1(String beta) {
		this.betaSave = beta;
	}
	
	/**
	 * cette methode permet a renvoyer le nom du vehicule choisi par le joueur beta
	 * @return,  le nom du vehicule choisi par le joueur beta
	 */
	public String getPlayer1() {
		return betaSave;
	}

	/**
	 * cette methode permet a enregistrer le nom du vehicule choisi par le joueur zeta
	 * @param zeta,  le nom du vehicule choisi par le joueur zeta
	 */
	public void setPlayer2(String zeta) {
		this.zetaSave = zeta;
	}
	
	/**
	 * cette methode permet a renvoyer le nom du vehicule choisi par le joueur zeta
	 * @return,  le nom du vehicule choisi par le joueur zeta
	 */
	public String getPlayer2() {
		return zetaSave;
	}
	
	/**
	 * cette methode permet a enregistrer les informations pour cree un obj de type reinforce
	 * @param index, le mode de reinforce
	 * @param coolDown, le temps de coolDown
	 */
	public void setReinforce(int index, int coolDown) {
		reinforceTypeSave = index;
		reinforceCoolDownSave = coolDown;
		
	}
	
	/**
	 * cette methode permet a envoiyer les informations pour cree un obj de type reinforce dans une liste de int
	 * @return, les informations pour cree un obj de type reinforce
	 */
	public int[] getReinforce() {
		return new int[]{reinforceTypeSave,reinforceCoolDownSave};
	}
	
	/**
	 * cette methode enregistre le path pour load une forme 4D
	 * @param dsLoadPath, le path pour load une forme 4D
	 */
	public void setDSLoadPath(String dsLoadPath) {
		if(dsLoadPath==""||dsLoadPath==null) {
			this.dsLoadPathSave = null;
		}else {
			this.dsLoadPathSave = dsLoadPath;
		}
		
	}
	
	/**
	 * cette methode renvoie le path pour load une forme 4D
	 * @return, le path pour load une forme 4D
	 */
	public String getDSLoadPath() {
		return dsLoadPathSave;
	}

	/**
	 * cette methode permet a enregistrer le nom du map choisi
	 * @param mapName, le nom du map choisi
	 */
	public void setMapName(String mapName) {
		this.mapNameSave = mapName;
	}
	
	/**
	 * cette methode renvoie le nom du map choisi
	 * @return, le nom du map choisi
	 */
	public String getMapName() {
		return mapNameSave;
	}
	
	/**
	 * cette methode permet a enregistrer le temps du jeu
	 * @param min, le min du jeu
	 * @param sec, le sec du jeu
	 */
	public void setTimer(int min, int sec) {
		this.min = min;
		this.sec = sec;
	}
	
	/**
	 * cette methode renvoie le temps en minute du jeu
	 * @return, le temps en minute du jeu
	 */
	public int getTimerMin() {
		return min;
	}
	
	/**
	 * cette methode renvoie le temps en seconde du jeu
	 * @return, le temps en seconde du jeu
	 */
	public int getTimerSec() {
		return sec;
	}

}
