package mapListeners;

import java.awt.Color;
import java.util.EventListener;

import vecteur.Vecteur;
/**
 * cette classe d'interface contient tous les ecouteurs necessaires pour gerer un OmniMap
 * @author Xu Wei Duo
 */
public interface OmniMapListener extends EventListener{
	/**
	 * cette methode permet a savoir le nom et la couleur d'un vehicule beta
	 * @param name, le nom du vehicule beta
	 * @param tailColor, la coulelur du vehicule beta
	 */
	public void betaInfo(String name, Color tailColor);
	/**
	 * cette methode permet a savoir le nom et la couleur d'un vehicule zeta
	 * @param name, le nom du vehicule zeta
	 * @param tailColor, la coulelur du vehicule zeta
	 */
	public void zetaInfo(String name, Color tailColor);
	/**
	 * cette methode permet a savoir la dimention du queue du vehicule beta
	 * @param tailDim,  la dimention du queue du vehicule beta
	 * @param tailLimit,  la dimention max du queue du vehicule beta
	 */
	public void betaTailDim(int tailDim,int tailLimit);
	/**
	 * cette methode permet a savoir la dimention du queue du vehicule zeta
	 * @param tailDim,  la dimention du queue du vehicule zeta
	 * @param tailLimit,  la dimention max du queue du vehicule zeta
	 */
	public void zetaTailDim(int tailDim,int tailLimit);
	/**
	 * cette methode permet a transmettre le temps du jeu dans un String
	 * @param timer, le temps du jeu dans unString
	 */
	public void timer(String timer);
	/**
	 * cette methode permet a lever un evenement pause
	 */
	public void pause();

	/**
	 * 
	 * cette methode permet a savoir qui est le gagnant
	 * @param winner, le nom du gagnant
	 * @param tailPL, la longueur du queue du joueur beta
	 * @param tailPR, la longueur du queue du joueur zeta
	 * @param tailColor, la couleur de la queue de la gagnante
	 */
	public void end(String winner, int tailPL, int tailPR,Color tailColor);
	/**
	 * cette methode indique qu'il n'y a aucune gagnat
	* @param tailPL, la longueur du queue du joueur beta
	 * @param tailPR, la longueur du queue du joueur zeta
	 */
	public void end(int tailPL, int tailPR);
	/**
	 * cette methode renvoie les informations des vehicules dans la scene du jeu
	 * @param beta, les informations du vehicule beta
	 * @param zeta, les informations du vehicule zeta
	 */
	public void sciModeInfo(Object[] beta, Object[] zeta);
	/**
	 * cette methode renvoie les informations d'une BlackDomain dans la scene du jeu
	 * @param module, la module du champ magnetique
	 * @param vec, le vecteur de force du champ magnetique
	 */
	public void sciModeBD(double module, Vecteur vec);
	/**
	 * cette methode renvoie les informations des champ electriques dans la scene du jeu
	 * @param CEInfo, les informations des champ electriques dans la scene du jeu
	 */
	public void sciModeCE(Object[] CEInfo);
	/**
	 * cette methode renvoie le nombre des obstacles dans la scene du jeu
	 * @param nb, le nombre des obstacles dans la scene du jeu
	 */
	public void sciModeObs(int nb);
}
