package mapListeners;

import java.util.EventListener;
/**
 * cette classe d'interface contient tous les ecouteurs necessaires pour gerer un InfoBar
 * @author Xu Wei Duo
 */
public interface InfoBarListener extends EventListener {
	/**
	 * cette methode permet a lever un evenement pause
	 */
	public void pauseMode( );
	//public void close();
}
