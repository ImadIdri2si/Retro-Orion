package mapListeners;

import java.util.EventListener;
/**
 * cette classe d'interface contient tous les ecouteurs necessaires pour gerer un MenuEnd
 * @author Xu Wei Duo
 */
public interface MenuEndListener extends EventListener{
	/**
	 * cette methode leve un evenement de retoure vers la fenetre precedant
	 */
	public void returnTo();
}
