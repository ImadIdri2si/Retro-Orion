package fenetreModeSci;

import java.util.EventListener;
/**
 *Interface d'ecouteur pour la fenetre scientifique 
 * @author Imad Idrissi
 *
 */
public interface FenetreModeSciListener extends EventListener{
	/**
	 * Releve un �vevement de commencer 
	 */
	public void begin();
	/**
	 * Releve un �vevement d'arret 
	 */
	public void stop();

	/**
	 * Releve un eevement de ralentir
	 * @param factor, facteuur du temps
	 */
	public void slowMo(int factor);
	/**
	 * Releve un �vevement fermer la fenetre 
	 */
	public void winClose();
	/**
	 * Releve un �vevement pour le mode scientifique
	 */
	public void modeSci();
}

