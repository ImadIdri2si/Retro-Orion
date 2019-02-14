package fenetreModeSci;

import java.util.EventListener;
/**
 *Interface d'ecouteur pour la fenetre scientifique 
 * @author Imad Idrissi
 *
 */
public interface FenetreModeSciListener extends EventListener{
	/**
	 * Releve un évevement de commencer 
	 */
	public void begin();
	/**
	 * Releve un évevement d'arret 
	 */
	public void stop();

	/**
	 * Releve un eevement de ralentir
	 * @param factor, facteuur du temps
	 */
	public void slowMo(int factor);
	/**
	 * Releve un évevement fermer la fenetre 
	 */
	public void winClose();
	/**
	 * Releve un évevement pour le mode scientifique
	 */
	public void modeSci();
}

