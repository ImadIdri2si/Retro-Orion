package JPanelButton;

import java.util.EventListener;
/**
 * cette classe d'interface contient tous les ecouteurs necessaires pour gerer le JPanel vehicule 
 * @author Xu Wei Duo
 */
public interface PanelVehiculeListener extends EventListener{
	/**
	 *cette methode envoie le nom du vehicule qui a ete choisi 
	 * @param type, le nom du vehicule choisi
	 */
	public void choiceVehicule(String type);
}
