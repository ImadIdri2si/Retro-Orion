package JPanelButton;

import java.util.EventListener;
/**
 * cette classe d'interface contient tous les ecouteurs necessaires pour gerer le JPanel bouton 
 * @author Xu Wei Duo
 */
public interface PanelButtonListener extends EventListener {
	/**

	 */
	/**
	 * cette methode gere quel object a ete choisi et quel et son indice
	 * @param type, le type de l'objet choisi
	 * @param choice, l'indice de l'objet choisi
	 * @param extra, extra comme extra 
	 */
	public void selected(String type,int choice,String extra);
}
