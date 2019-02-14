package mapListeners;

import java.util.EventListener;

import vecteur.Vecteur;
/**
 * cette classe d'interface contient tous les ecouteurs necessaires pour gerer un MenuPause
 * @author Xu Wei Duo
 */
public interface MissileListener extends EventListener{
	/**
	 * cette methoder permet a transferer les informations des missiles
	 * @param infos, la liste d'objet pour les informations des missiles
	 */
	public void MissleInfo(Object[] infos);
	 
}
