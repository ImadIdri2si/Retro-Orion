package JPanelButton;

import java.util.EventListener;
/**
 * cette classe d'interface contient tous les ecouteurs necessaires pour gerer le JPanel map 
 * @author Xu Wei Duo
 */
public interface PanelMapListener extends EventListener{
	/**
	 *cette methode envoie le nom du map qui a ete choisi 
	 * @param type, le nom du map choisi
	 */
	public void choiceMap(String type);

}
