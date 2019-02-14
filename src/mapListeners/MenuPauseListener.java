package mapListeners;

import java.util.EventListener;
/**
 * cette classe d'interface contient tous les ecouteurs necessaires pour gerer un MenuPause
 * @author Xu Wei Duo
 */
public interface MenuPauseListener extends EventListener{
	/**
	 * cette methode leve un evenement de retoure
	 */
	public void resume();
	/**
	 * cette methode leve un evenement de retoure vers la fenetre precedant
	 */
	public void returnTo();
	/**
	 * cette methode leve un evenement d'ouvrir la fenetre mode scitifique
	 */
	public void sciWin();
}
