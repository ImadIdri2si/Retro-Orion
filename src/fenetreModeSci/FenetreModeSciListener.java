package fenetreModeSci;

import java.util.EventListener;
/**
 *Interface d'ecouteur pour la fenetre scientifique 
 * @author Imad Idrissi
 *
 */
public interface FenetreModeSciListener extends EventListener{
	/**
	 * Releve un Úvevement de commencer 
	 */
	public void begin();
	/**
	 * Releve un Úvevement d'arret 
	 */
	public void stop();

	/**
	 * Releve un eevement de ralentir
	 * @param factor, facteuur du temps
	 */
	public void slowMo(int factor);
	/**
	 * Releve un Úvevement fermer la fenetre 
	 */
	public void winClose();
	/**
	 * Releve un Úvevement pour le mode scientifique
	 */
	public void modeSci();
}

