package foundation;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Shape;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.event.EventListenerList;

import JPanelButton.PanelButtonListener;
import canon.Canon;
import explosionAnim.Explosion;
import fenetreModeSci.FenetreScientifique;
import fourthDim.FormeCreator;
import fourthDim.Point4D;
import gestion.GestionCollision;
import gestion.GestionPhysique;
import mapListeners.MissileListener;
import mapListeners.OmniMapListener;
import modelaffichage.ModeleAffichage;
import objects.BlackDomain;
import objects.DimStrike;
import objects.Reinforce;
import objects.WDwarf;
import obstacle.GererObstacle;
import obstacle.Obstacle;
import physique.ForceElectrique;
import vecteur.Vecteur;
import vecteur.VecteurGraphique;
import vehicle.Vehicule;
import java.awt.event.MouseMotionAdapter;
import java.awt.font.GlyphVector;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
/**
 * la classe qui s'occupe de la simulation du jeu
 * @author XU WEI DUO
 *
 */
public class OmniMap extends JPanel implements Runnable, Serializable{
	
	boolean ifFirst = true;
	double width, height, side=20;
	
	private final double WORLD_SIZE = 1;
	private ModeleAffichage modele;
	private Image map = null;
	
	//science mode
	private boolean scfMode =false;
	//Vehicules
	private Vehicule beta,zeta;
	private boolean betaCollision = false, zetaCollision = false;
	private boolean betaCaptat=false, zetaCaptat=false;
	private boolean smoothRotation = true;
	//private Vecteur vecDistance;
	private Object[] betaInfo  = new Object[6], zetaInfo = new Object[6];
	
	//objects
	private WDwarf dwarf;
	private BlackDomain domain;
	private Reinforce reinforce;
	private int reinforceType = 0;
	private int reinforceCoolDown=400, reinforceCoolDownCounter = 0;
	//Keys
	private boolean[] keys = new boolean[255];
	
	//animation
	private boolean inAnimation = false, printText = true;
	private int sleep = 10;
	//private double timeFactor = 0;
	
	//canon
	private Canon canon;
	private boolean betaTrack, zetaTrack;
	
	//collision
	private GestionCollision collision;
	private boolean colActive = false;
	private Explosion  explosion ;
	
	
	//obstacle
	private GererObstacle obstacles;
	
	//Dim
	private DimStrike dimStrike;
	private String dimSLoadPath = "";
	
	//ElectricFiled
	private ForceElectrique forceEl;
	private GestionPhysique force;
	
	private Point4D[] objs;
	
	private String player1 = "";
	private String player2 = "";
	//private Fenetre6Scientifique sciWindow;
	private MenuPause menuPasue;
	private boolean menuClosed = false;
	
	private int timeCounter,sec,min=2;
	private String timer ="";
	private boolean end = false;
	//OmniMapListener
	private final EventListenerList OBJS = new EventListenerList();
	private final EventListenerList OBJETS_ENREGISTRES = new EventListenerList();
	//sciWin
	private boolean dataTransmits = false;
	//
	
	/**
	 * initilialise le Jpanel et les ecouteurs pour le clavier
	 */
	public OmniMap() {
		explosion = new Explosion();
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				//begin

				if( canon.getMissileList() != null || canon.getMissileChercheurList() != null) {
					canon.clickOnMissile(e);
				}
				if( force != null) {
					force.clickOnCharge(e);

			    }
				//fin
			}
		 });
		
		
		setBounds(0, 0, 1366, 718);
		//setBackground(Color.DARK_GRAY);
		
		
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				//begin
				Toolkit toolkit = Toolkit.getDefaultToolkit();
				//toolkit.setLockingKeyState(KeyEvent.VK_CAPS_LOCK, false);
				if(e.getKeyCode()==KeyEvent.VK_ESCAPE || e.getKeyCode()==KeyEvent.VK_DELETE) {
					pause();
					
					//System.exit(0);
				}
				
				if((e.getKeyChar() >='a' && e.getKeyChar() <='z')||(e.getKeyChar() >='A' && e.getKeyChar() <='Z')) {
				//if(e.getKeyChar()=='w'||e.getKeyChar()=='a'||e.getKeyChar()=='s'||e.getKeyChar()=='d'||e.getKeyChar()=='q'||e.getKeyChar()=='e'||e.getKeyChar()=='j'||e.getKeyChar()=='k'||e.getKeyChar()=='l'||e.getKeyChar()=='i'||e.getKeyChar()=='u'||e.getKeyChar()=='o') {
					keys[e.getKeyChar()] = true;
				}else {
					e.consume();
					getToolkit().beep();
				}
				
				if (!inAnimation && keys['w'] && keys['i'] && !keys['a'] && !keys['d']  && !keys['j']  && !keys['l'] ) { 
					begin();
				}
				
				if(keys['g']) {
					if(sleep==10) {
						slowMotion(true);
					}else {
						slowMotion(false);
					}
					
				}
				/**
				if(keys['b']) {
					if(sciWindow==null) {
						sciWindow = new Fenetre6Scientifique();
						sciWindow.setVisible(true);
					}
					
				}
				**/
				if(keys['t']) {
					sciMode();
					
				}
				
				if(keys['e'] && reinforce!=null && reinforce.getBetaGotRein()) {
					canon = reinforce.trigger(canon, beta, true);
					colActive = true;
					if(forceEl!=null)force = new GestionPhysique(canon, forceEl);
					if(reinforce.getType()==2)zetaTrack = true;
					//reinforce.selfDes();
					//System.out.println("tirer");
				}
				if(keys['u'] && reinforce!=null && reinforce.getZetaGotRein()) {
					canon = reinforce.trigger(canon, zeta, false);
					colActive = true;
					if(forceEl!=null)force = new GestionPhysique(canon, forceEl);
					if(reinforce.getType()==2)betaTrack = true;
					//reinforce.selfDes();
					//System.out.println("tirer");
				}
				
				if(betaCaptat && domain.getRadius()<1000 && (keys['w']||keys['s']||keys['a']||keys['d'])) {
					beta.setCaptation(false);
					betaCaptat = false;
					if(domain!=null)domain.setInRot(false);
					if(domain!=null)domain.setInObj(false);
				}
				
				if(zetaCaptat && domain.getRadius()<1000 && (keys['i']||keys['k']||keys['j']||keys['l'])) {
					zeta.setCaptation(false);
					zetaCaptat = false;
					if(domain!=null)domain.setInRot(false);
					if(domain!=null)domain.setInObj(false);
				}
				
				//repaint();
				//fin
			}

			@Override
			public void keyReleased(KeyEvent e) {
				//bgin
				if((e.getKeyChar() >='a' && e.getKeyChar() <='z')||(e.getKeyChar() >='A' && e.getKeyChar() <='Z')) {
					keys[e.getKeyChar()] = false;
				}else {
					e.consume();
					getToolkit().beep();
				}
				
				
				
				//fin
			}
		});
	}

	/**
	public void setDimStrwsike(int x,int y,int scale) {
		dimStrike = new DimStrike(x,y,0,scale);
	}
	**/
	
	/**
	 * cette methdoe permet a ouvrir ou fermer le mode scitifique
	 */
	public void sciMode() {
		if(scfMode) {
			scfMode = false;
			zeta.setScfMode(false);
			beta.setScfMode(false);
			dimStrike.setSciMode(false);
			if(domain!=null)domain.setScfMode(false);
			if(reinforce!=null)reinforce.setSciMode(false);
		}else {
			scfMode = true;
			zeta.setScfMode(true);
			beta.setScfMode(true);
			dimStrike.setSciMode(true);
			if(domain!=null)domain.setScfMode(true);
			if(reinforce!=null)reinforce.setSciMode(true);
			
		}
	}
	
	/**
	 * cette methode ajoute un ecouteur OmniMapListener
	 * @param objEcout, ecouteur OmniMapListener
	 */
	public void addOmniMapListener(OmniMapListener objEcout) {
		OBJS.add(OmniMapListener.class, objEcout);
	}
	
	/**
	 * cette methode permet a lever un evenement qui renvoie le nom et la couleur des vehicules
	 */
	public void vehiculeInfo() {
		for(OmniMapListener ecout: OBJS.getListeners(OmniMapListener.class)) {
			ecout.betaInfo(beta.getVehiculeName(),beta.getHeadColor());
			ecout.zetaInfo(zeta.getVehiculeName(),zeta.getHeadColor());
		}
	}
	
	/**
	 * cette methode permet a lever un evenement qui renvoie la dimention de la  queue et la dimention max de la queue
	 */
	public void tailInfo() {
		for(OmniMapListener ecout: OBJS.getListeners(OmniMapListener.class)) {
			ecout.betaTailDim(beta.getTailDim(), beta.getTailLimit());
			ecout.zetaTailDim(zeta.getTailDim(), zeta.getTailLimit());
		}
	}
	
	/**
	 * cette methode permet a lever un evenement qui renvoie le temps du jeu 
	 */
	public void setTimer() {
		for(OmniMapListener ecout: OBJS.getListeners(OmniMapListener.class)) {
			ecout.timer(timer);
		}
	}
	
	/**
	 * cette methode permet a lever un evenement pause
	 */
	public void pause() {
		for(OmniMapListener ecout: OBJS.getListeners(OmniMapListener.class)) {
			ecout.pause();
		}
	}
	
	/**
	
	 */
	/**
	 * cette methode permet a lever un evenement de fin du jeu avec un joueur gagnant
	 * @param winner, le joueur gagnant
	 * @param tailPL, la dimentison de la queue du joueur beta
	 * @param tailPR, la dimentison de la queue du joueur zeta
	 * @param tailcolor, la couleur de la queue
	 */
	public void end(String winner, int tailPL, int tailPR, Color tailcolor) {
		for(OmniMapListener ecout: OBJS.getListeners(OmniMapListener.class)) {
			ecout.end(winner,tailPL,tailPR, tailcolor);
		}
	}
	
	/**
	 * cette methode permet a lever un evenement de fin du jeu sans joueur gagnant
	 * @param tailPL, la dimentison de la queue du joueur beta
	 * @param tailPR, la dimentison de la queue du joueur zeta
	 */
	public void end(int tailPL, int tailPR) {
		for(OmniMapListener ecout: OBJS.getListeners(OmniMapListener.class)) {
			ecout.end(tailPL,tailPR);
		}
	}
	
	/**
	 * cette methode permet a recevoir un boolean qui indique si on transmet le data pour le mode scitifique ou non
	 * @param entry, si on transmet le data pour le mode scitifique ou non
	 */
	public void dataTransmits(boolean entry) {
		dataTransmits = entry;
	}
	
	/**
	 * cette methode permet a lever un evenement qui envoie les informations des vehicules
	 * @param betaInfo, les informations du vehicule beta
	 * @param zetaInfo, les informations du vehicule zeta
	 */
	public void sciModeInfo(Object[] betaInfo, Object[] zetaInfo) {
		for(OmniMapListener ecout: OBJS.getListeners(OmniMapListener.class)) {
			ecout.sciModeInfo(betaInfo, zetaInfo);
		}
	}
	
	/**
	 * cette methode permet a envoier les informations des vehicules a l'ecouteur
	 */
	public void setSciModeWindow() {
		betaInfo = setSciModeInfo(betaInfo, beta);
		zetaInfo = setSciModeInfo(zetaInfo, zeta);
		sciModeInfo(betaInfo, zetaInfo);
	}
	
	/**
	 * ette methode permet a entrer les informations d'un vehicule dans une liste d'objet
	 * @param info, la liste d'informations d'un vehicule
	 * @param veh, le vehicule
	 * @return, retourne la liste d'objet qui contient les informations d'un vehicule
	 */
	public Object[] setSciModeInfo(Object[] info, Vehicule veh) {
		//Object[] betaInfo = new Object[5];
		info[0] = veh.getVehiculeName();
		info[1] = veh.getSpeed();
		info[2] = veh.getAcc();
		info[3] = veh.getTailDim();
		info[4] = veh.getVecMov().module();
		info[5] = new Point.Double(veh.getVecMov().getX(), veh.getVecMov().getY());
		return info;
	}
	
	/**
	 * cette methode permet a lever un evenement qui envoie les informations d'une BlackDomain
	 * @param module, la module de champ magntqieu
	 * @param vec, le vecteur de force de champ magnetique
	 */
	public void sciModeBD(double module, Vecteur vec) {
		for(OmniMapListener ecout: OBJS.getListeners(OmniMapListener.class)) {
			ecout.sciModeBD(module,vec);
		}
	}
	
	/**
	 * cette methode permet a entrer les informations d'une BlackDomain
	 */
	public void BlackDomainInfo() {
		double module = domain.getB();
		Vecteur vecF = domain.getVecForce();
		sciModeBD(module, vecF);
	}
	
	/**
	 * cette methode permet a lever un evenement qui envoie les informations pour les champs electrique
	 * @param infoCE, la liste objet  d'information des champs electrique
	 */
	public void sciModeCE(Object[] infoCE) {
		for(OmniMapListener ecout: OBJS.getListeners(OmniMapListener.class)) {
			ecout.sciModeCE(infoCE);
		}
	}
	
	/**
	 * cette methode permet a entrer les informations pour les champs electrique dans une liste d'objet
	 */
	public void ChampEleInfo() {
		Object[] CEInfo = new Object[6];
		CEInfo[0] = force.getChampMax();
		CEInfo[1] = force.getCharge();
		
		sciModeCE(CEInfo);
	}
	
	/**
	 * cette methode permet a lever un evenement du nombre d'obstacle dans la scene
	 * @param entry, entree des obs
	 */
	public void sciModeObs(int entry) {
		for(OmniMapListener ecout: OBJS.getListeners(OmniMapListener.class)) {
			ecout.sciModeObs(entry);
		}
	}
	
	/**
	 * cette methode renvoie le  nombre d'obstacle dans la scene a l'evouteur
	 */
	public void ObsInfo() {
		sciModeObs(obstacles.getSize());
	}
	
	/**
	 * cette methode ajoute un ecouteur MissileListener
	 * @param objEcout, ecouteur MissileListener
	 */
	public void addMissileListener(MissileListener objEcout) {
		OBJETS_ENREGISTRES.add(MissileListener.class, objEcout);
	}
	
	/**
	 * cette methode permet a lever un evenement qui renvoie tous les informations des missiles
	 */
	private void MissileInfo() {
		for(MissileListener ecout : OBJETS_ENREGISTRES.getListeners(MissileListener.class) ) {
			//System.out.println("--------transmetting-------------");
			Object[] missleInfo = new Object[9];
			missleInfo[0] = canon.getAcc();
			missleInfo[1] = canon.getAccVec();
			missleInfo[2] = canon.getVitesseModule();
			missleInfo[3] = canon.getVitesseVec();
			missleInfo[4] = canon.getSubitUneForceElectrique();
			missleInfo[5] = canon.getDeltaT();
			missleInfo[6] = canon.getMissileList().size()+canon.getMissileChercheurList().size();
			missleInfo[7] = canon.getForce();
			missleInfo[8] = canon.getCharge();
			ecout.MissleInfo(missleInfo);
		}
	}
	
	public void setFirst(boolean entry) {
		ifFirst=entry;
		repaint();
	}
	
	/**
	 * recoit les objets qui ont ete cree dans un createur de map
	 * @param objs,une liste de Point4D qui contient les informations des objets a placer dans la scene
	 */
	public void setObjs(Point4D[] objs) {
		this.objs = objs;
	}
	
	
	
	/**
	 * recoit les obstacles qui ont ete cree dans un createur de map
	 * @param entry,les obstacles
	 */
	public void setObs(ArrayList<Obstacle> entry) {
		if(entry!=null) {
			GererObstacle temp = new GererObstacle(entry);
			temp.refactorAll(2,2);
			//entry.setScaleAll(8);
			//entry.recenter();
			this.obstacles = new GererObstacle(temp.getObsList());
		}else {
			this.obstacles = null;
		}
		
	}
	
	/**
	 * cette methode permet a charger des nouveaux champs electrique
	 * @param entryPos, les positions des nouveaux champs electrique
	 * @param entryCharge, les chrages des nouveaux champs electrique
	 */
	public void setEle(ArrayList<Vecteur> entryPos,ArrayList<Double> entryCharge) {
		forceEl = new ForceElectrique(entryPos, entryCharge);
		forceEl.scalePosition(2);
		forceEl.setRayon(2);
		System.out.println("drawwing---------------------------------");
	}
	
	/**
	 * cette methode permet a charger le load path pour une forme4D
	 * @param path, le load path pour une forme4D
	 */
	public void setDSLoadPath(String path) {
		dimSLoadPath = path;
	}
	
	/**
	 * cette methode permet a precier le mode et le temps de coolDown pour un objet reinforce
	 * @param index, le mode pour un objet reinforce
	 * @param coolDown, le temps de coolDown pour un objet reinforce
	 */
	public void setReinforce(int index, int coolDown) {
		reinforceType = index;
		reinforceCoolDown = 100*coolDown;
		
	}
	
	/**
	 * cette methode permet a charger un map selon son nom
	 * @param type, le nom du map 
	 */
	public void setMap(String type) {
		try {
			System.out.println("choice: "+type);
			switch(type) {
				case "Abandon": map = ImageIO.read(getClass().getClassLoader().getResource("Abandon.jpg"));			 
				break;
				case "Cloud": map = ImageIO.read(getClass().getClassLoader().getResource("Cloud.jpg"));		
				break;
				case "Terra": map = ImageIO.read(getClass().getClassLoader().getResource("Terra.jpg"));
				break;
				case "Centaurus": map = ImageIO.read(getClass().getClassLoader().getResource("Centaurus.jpg"));
				break;
				default: map = ImageIO.read(getClass().getClassLoader().getResource("Centaurus.jpg"));
					break;
			}
			
			//repaint();
		} catch (IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Image Load unsuccessful");
		}
	}
	
	/**
	 * dessiner et initialiser tous les objets et vehicules dans la sceene 
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		
	
		
		if(ifFirst) {
			requestFocus();
			//requestFocusInWindow();
			
			//timeFactor = 1000/sleep;
			
			width = getWidth();
			height = getHeight();
			if(map==null) {
				try {
					map = ImageIO.read(getClass().getClassLoader().getResource("Abandon.jpg"));
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null, "image of map not found");
					e1.printStackTrace();
				}
			}
			
			int bx = (int)(0+side*2);
			int by = (int)(height*WORLD_SIZE-side);
			int zx = (int)(width*WORLD_SIZE-side*2);
			int zy = (int)(height*WORLD_SIZE-side);
			modele = new ModeleAffichage(width, height, 0, 0, width*WORLD_SIZE,height*WORLD_SIZE);
			System.out.println("real width "+width*WORLD_SIZE+"km and real height "+height*WORLD_SIZE+"km");
			//Changement


			if(objs!=null && objs[2]!=null) {
				dwarf = new WDwarf(modele.getMatMC(),width*WORLD_SIZE,height*WORLD_SIZE,10,(int)objs[2].getZ());//width*modele.getMatMC().getScaleX(), height*modele.getMatMC().getScaleY(), 10, 400);	
			}else {
				dwarf = new WDwarf(modele.getMatMC(),width*WORLD_SIZE,height*WORLD_SIZE,10,400);
			}
			
			reinforce = new Reinforce(modele.getMatMC(),(width)*WORLD_SIZE,(height)*WORLD_SIZE,20,true, reinforceType);
			//beta = new Vehicule(bx,by,10, 200,modele.getMatMC(),"l", "Genessis",width*WORLD_SIZE,height*WORLD_SIZE);
			//zeta = new Vehicule(zx,zy,10, 200,modele.getMatMC(),"r", "Reaper",width*WORLD_SIZE,height*WORLD_SIZE);
			
			if(player1==null || player1=="" ) player1 = "Unknow";
			beta = new Vehicule(bx,by,10, dwarf.getQntMax()/2,modele.getMatMC(),"l", player1,width*WORLD_SIZE,height*WORLD_SIZE);
			if(player2==null ||  player2 =="") player2 = "Unknow";
			zeta = new Vehicule(zx,zy,10, dwarf.getQntMax()/2,modele.getMatMC(),"r", player2,width*WORLD_SIZE,height*WORLD_SIZE);

			
			vehiculeInfo();

			domain = new BlackDomain(modele.getMatMC(), width*WORLD_SIZE, height*WORLD_SIZE,20, true);
			
			if(objs!=null && objs[0]!=null) domain.deplace(objs[0].getX()*2,objs[0].getY()*2);
			
			
			/**
			if(obstacles==null) {
				obstacles = new GererObstacle();
				obstacles.ajouterObs((int)(width*WORLD_SIZE/2), (int)(height*WORLD_SIZE/2));
			}
			
			if(forceEl==null) {
				forceEl = new ForceElectrique();
				forceEl.ajouterChargeFixe(new Vecteur((int)(width*WORLD_SIZE/2), (int)(height*WORLD_SIZE/2)),10);
			}
			*/
			//forceEl.ajouterChargeFixe(new Vecteur(400,400),- 40);
			canon = new Canon((int)(width*WORLD_SIZE/2), (int)(height*WORLD_SIZE/2));
			if(forceEl!=null)force = new GestionPhysique(canon, forceEl);

			dimStrike = new DimStrike((int)(width*WORLD_SIZE/2), (int)(height*WORLD_SIZE/2),0,400, dwarf.getQntMax()/20);
			if(objs!=null && objs[1]!=null) dimStrike.deplace((int)objs[1].getX()*2,(int)objs[1].getY()*2); 
			if(dimSLoadPath!="")dimStrike.load(dimSLoadPath);
			ifFirst = false;
			
			//min = 2;
			//sec = 60;
		}
		
		
		//AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.0f);
		//g2d.setComposite(ac);
		g2d.drawImage(map, 0,0,(int)width,(int)height,null);
		
		tailInfo();
		
		//if(dwarf.getQnt()<=0) {
			
		//}
		
		
		if(force!=null) {
			force.appliquerForceElectrique(g2d);
			force.dessiner(g2d, width,height);	
		}
	
		
		
		//if(zeta.getVecMov()!=null)domain.calRadius(zeta.getVecMov());
		if(domain==null) {
			domain = new BlackDomain(modele.getMatMC(), width*WORLD_SIZE, height*WORLD_SIZE,20, false);
			if(scfMode)domain.setScfMode(true);
		}
		if(domain!=null && domain.getDestroyed()) domain = null;
		if(domain!=null)domain.drawDomain(g2d);
		
		//if(inAnimation) {
			dwarf.draw(g2d,Color.WHITE);
			
		//}
		beta.drawTail(g2d);
		zeta.drawTail(g2d);
		zeta.drawLmg(g2d);
		beta.drawLmg(g2d);

		if(dataTransmits && inAnimation) {
			setSciModeWindow();
			MissileInfo();
			if(domain!=null)BlackDomainInfo();
			if(obstacles!=null)ObsInfo();
			if(forceEl!=null)ChampEleInfo();
		}
		

		if(colActive) {

			//collision = new GestionCollision(canon, obstacles);
			//if(obstacles!=null) {
			
			collision = new GestionCollision(canon, obstacles, beta, zeta, explosion);
			//}

			collision.setTaille(new Vecteur(width*WORLD_SIZE,height*WORLD_SIZE));
			
		}
		
		if(betaTrack) {
			canon.target(beta.getTailHeadCoordinate().getX(), beta.getTailHeadCoordinate().getY());
		}else if(zetaTrack) {
			canon.target(zeta.getTailHeadCoordinate().getX(), zeta.getTailHeadCoordinate().getY());
		}
		if(scfMode) {
			canon.setModeSci(true);
		}else {
			canon.setModeSci(false);
		}
		explosion.dessinerList(g2d);
		canon.dessiner(g2d);
		
		if(colActive)collision.collisionDetected(dwarf);
		
		if(inAnimation && (domain!=null)) {
			//System.out.println("zeta: "+domain.measure(zeta.getTailHeadCoordinate().getX(),zeta.getTailHeadCoordinate().getY()));
			///System.out.println("beta: "+domain.measure(beta.getTailHeadCoordinate().getX(),beta.getTailHeadCoordinate().getY()));
			zetaCaptat = domain.vehInteract(zeta, zetaCaptat, g2d);
			betaCaptat = domain.vehInteract(beta, betaCaptat, g2d);
			
			if(!zetaCaptat&&!betaCaptat&&!domain.getInRot()) {
				domain.consumedSpray(dwarf);
			}
		}
		
		
		if(reinforce!=null) {
			reinforce.draw(g2d, Color.GREEN);
			if(beta!=null && zeta!=null)reinforce.combine(beta, zeta, g2d);
			if(reinforce.destroyed())reinforce=null;

		}else if(reinforce==null) {
			reinforceCoolDownCounter++;
			if(reinforceCoolDownCounter==reinforceCoolDown) {
				reinforce = new Reinforce(modele.getMatMC(),(width)*WORLD_SIZE,(height)*WORLD_SIZE,20, false, reinforceType);
				if(scfMode)reinforce.setSciMode(true);
				betaTrack = false;
				zetaTrack = false;
				reinforceCoolDownCounter = 0;
			}
		}
		
		if(obstacles!=null) {
			obstacles.dessiner(g2d);
			if(scfMode)obstacles.drawCenter(g2d);
		}
		

		if(objs!=null && objs[1]!=null) dimStrike.strike(g2d, beta, zeta,(int)objs[1].getZ());
		
	
		if(inAnimation) {
			
			dwarf.collision(beta, zeta);
			
			if(domain!=null) {
				if(domain.contain(beta) && !beta.getCaptation() && beta.getTailDim()>1) {
					beta.tailDestroy();
					domain.consume();
				}
				if(domain.contain(zeta) && !zeta.getCaptation() && zeta.getTailDim()>1) {
					zeta.tailDestroy();
					domain.consume();
				}
			}
			//vecDistance = new Vecteur(beta.getTailHeadCoordinate().getX()-zeta.getTailHeadCoordinate().getX(),beta.getTailHeadCoordinate().getY()-zeta.getTailHeadCoordinate().getY());
			//if(vecDistance.module()<200) {
			betaCollision = beta.simpleCollision(zeta);
			zetaCollision = zeta.simpleCollision(beta);
			//}else {
			//	betaCollision = false;
			//	zetaCollision = false;
			//}
		
			//simpleCollision();
			beta.tailCollision(zeta);
			zeta.tailCollision(beta);
			
		}
		
		if(!inAnimation && printText) {
			//String BeginIntro = "PRESS W AND I AT SAME TIME TO LAUCH";
			String BeginIntro = "APPUYEZ SUR W ET I EN MEME TEMPS POUR DEBUTER";
			if(player1=="Unknow" && player2=="Unknow")  BeginIntro = "Well~ A nice choice indeed (OuO )";
			drawString(BeginIntro,43, g2d,Color.BLACK, (int)(width*WORLD_SIZE/2),(int)(height*WORLD_SIZE/2));
		}


		if(scfMode)drawString("Echelle: 1 pixel = 1Km",20,g2d,Color.BLACK,(int)width/2,(int)height-2);
	
	}
	
	/**
	 * cette methode permet a faire un drawString de facon que la phrase ou la lettre soit toujours centrer
	 * @param entry, le mot ou la phrase de String
	 * @param size, la grandeur des mots 
	 * @param g2d, le contexe graphique
	 * @param color, la couleur des mots
	 * @param dx, la position en x du centre de la phrase ou de mot
	 * @param dy, la position en y du centre de la phrase ou de mot
	 */
	public void drawString(String entry, int size, Graphics2D g2d, Color color, int dx, int dy) {
		Color back = g2d.getColor();
		g2d.setFont(new Font("OCR A Extended", Font.PLAIN, size)); 
		String reminder = entry;
		int reminderLength = g2d.getFontMetrics().stringWidth(reminder);
		g2d.setColor(color);
		g2d.drawString(reminder,dx-reminderLength/2, dy);
		g2d.setColor(back);
	}

	/**
	 * cette methode permet a specifier si on affiche les instructions au debbut du jeu
	 * @param entry, si on affiche les instructions au debbut du jeu
	 */
	public void paintText(boolean entry) {
		printText = entry;
	}
	
	/**
	 * cette mtehode retourne le vehicule beta dans le map
	 * @return, le vehicule beta
	 */
	public Vehicule getBeta() {
		return beta;
	}
	/**
	 * cette mtehode retourne le vehicule zeta dans le map
	 * @return, le vehicule zet
	 */
	public Vehicule getZeta() {
		return zeta;
	}
	
	/**
	 * cette methode permet a renvoyer la couleur de la queue du vehicule beta
	 * @return, la couleur de la queue du vehicule beta
	 */
	public Color getBetaColor() {
		if(beta!=null) {
			return beta.getHeadColor();
		}else {
			return Color.BLACK;
		}
		
	}
	
	/**
	 * Cette methode permet a renvoyer la couleur de la queue du vehicule zeta
	 * @return, la couleur de la queue du vehicule zeta
	 */
	public Color getZetaColor() {
		if(zeta!=null) {
			return zeta.getHeadColor();
		}else {
			return Color.BLACK;
		}
		
	}
	
	/**
	 * cette methode renvoie la dimention du queue du vehicule beta
	 * @return, la dimention du queue du vehicule beta
	 */
	public int getBetaTail() {
		return beta.getTailDim();
	}
	
	/**
	 * cette methode renvoie la dimention du queue du vehicule zeta
	 * @return, la dimention du queue du vehicule zeta
	 */
	public int getZetaTail() {
		return zeta.getTailDim();
	}
	
	/**
	 *  Demarre le thread s'il n'est pas deja demarre et dire `a l'ecouteur que l'animation est commencer
	 */
	public void begin() {
		if (!inAnimation) { 
			Thread test = new Thread(this);
			test.start();
			inAnimation = true;
		}
	}
	
	/**
	public double newSpeed(Vehicule v1, Vehicule v2) {
		double J = ((1+0.8)*v1.getSpeed())/(1/v1.getMass()+1/v2.getMass());
		double v2Speed = J/v2.getMass();
		return v2Speed;
	}
	**/
	
	/**
	 *  Demande l'arret du thread (prochain tour de boucle) et dire `a l'ecouteur que l'animation est arreter
	 */
	public void stop() {
		inAnimation=false;
	}
	
	/**
	 * cette methode permet `a voir l'animation plus lentement
	 * @param entry, si le mode slowMotion est active ou non
	 */
	public void slowMotion(boolean entry) {
		 slowMotion(entry,1);
	}
	
	/**
	 * cette methode permet `a voir l'animation plus lentement
	 * @param entry, si le mode slowMotion est active ou non
	 * @param factor, le facteur pour le sleep
	 */
	public void slowMotion(boolean entry, int factor) {
		if(entry) {
			sleep = 100*factor;
		}else {
			sleep = 10;
		}
	}
	
	
	
	/**
	 *  animation de la simulation du jeu
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (inAnimation) {	
			if(!betaCaptat) {
				beta.keysUpdate(keys,betaCollision,smoothRotation, dwarf);
			}
			if(!zetaCaptat) {
				zeta.keysUpdate(keys,zetaCollision,smoothRotation, dwarf);
			}
			
			if(menuPasue!=null && !menuPasue.isVisible() && !menuClosed) {
				slowMotion(false);
				menuClosed = true;
			}
			
			timerDown();
			repaint();
			
			
			
			try {
				Thread.sleep(sleep);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}//fin while
		System.out.println("thread was killed =-;_(OwO_)");
	}
	
	/**
	 * cette methode permet `a modifier le vehicule choisi par le joueur beta
	 * @param player1,le vehicule choisi par le joueur beta
	 */
	public void setPlayer1(String player1) {
		this.player1 = player1;
		System.out.println("in scene:"+player1);
	}
	/**
	 * cette methode permet `a modifier le vehicule choisi par le joueur zeta
	 * @param player2,le vehicule choisi par le joueur zeta
	 */
	public void setPlayer2(String player2) {
		this.player2 = player2;
		System.out.println("in scene:"+player2);
	}

	/**
	 * cette methode permet a faire un count de temps de 0 a un nombre max
	 */
	public void timerUp() {
		timeCounter++;
		if(timeCounter==100) {
			sec++;
			timeCounter=0;
			if(sec==60) {
				sec=0;
				min++;
			}
		}
		timertoString();
	}
	
	/**
	 * cette methode permet a faire un compte de temps d'un nombre max a 0
	 */
	public void timerDown() {
		timeCounter++;
		if(timeCounter==100) {
			if(min>=0) {
				if(sec==0) {
					min--;
					sec=60;
				}
				sec--;
			}
			timeCounter=0;
		}
		
		timertoString();
	}
	
	/**
	 * cette methode permet a recevoir le temps initiale du jeu
	 * @param min, le temps en minute initial
	 * @param sec, le temps en seconde initial
	 */
	public void setTimer(int min, int sec) {
		this.min = min;
		this.sec = sec;
	}
	
	/**
	 * cette methode permet a mettre le temps du jeu dans un String
	 */
	public void timertoString() {

		timer = "";
		if(min<10) {
			timer = timer+"0"+min;
		}else {
			timer = timer+""+min;
		}
		timer = timer+" : ";
		if(sec<10){
			timer = timer+"0"+sec;
		}else {
			timer = timer+""+sec;
		}
		
		if(!end) {
			if(beta.ifTailFull())tailFullWinner("beta");
			if(zeta.ifTailFull())tailFullWinner("zeta");
			if(min==0 && sec==0) {
				checkWinner();
				slowMotion(true);
				end = true;
				
			}
			setTimer();
		}
		
	}
	
	/**
	 * cette methode permet a savoir lorsqu'un vehicuel a accumuler son queue a la dimention max
	 * @param winner, le nom du gagnant
	 */
	public void tailFullWinner(String winner) {
		if(winner == "beta") {
			winner = beta.getVehiculeName();
			end(winner,beta.getTailDim(),zeta.getTailDim(), beta.getTailColor());
			slowMotion(true);
			end = true;
		}else if(winner == "zeta") {
			winner = zeta.getVehiculeName();
			end(winner,beta.getTailDim(),zeta.getTailDim(), zeta.getTailColor());
			slowMotion(true);
			end = true;
		}
		
	}
	
	/**
	 * cette methode permet a savoir le gagnant du jeu
	 */
	public void checkWinner() {
		int zetaTailnb = zeta.getTailDim();
		int betaTailnb = beta.getTailDim();
		String winner;
		if(zetaTailnb==betaTailnb) {
			end(zeta.getTailDim(),beta.getTailDim());
		}else if(zetaTailnb>betaTailnb) {
			winner = zeta.getVehiculeName();
			end(winner,beta.getTailDim(),zeta.getTailDim(), zeta.getTailColor());
		}else if(betaTailnb>zetaTailnb) {
			winner = beta.getVehiculeName();
			end(winner,beta.getTailDim(),zeta.getTailDim(), beta.getTailColor());
		}
	}


}
