package fourthDim;

/**
 * cette classe permet `a definir les matricielles de rotaiton en R3
 * @author XU WEI DUO
 *
 */
public class Matrix_Rotation_3D {
	/**
	 * cette methode permet `a creer une matrix de rotation en R3 selon l'axe des x
	 * @param angle, angle de rotation
	 * @return, retourne la matrix de rotation en R3 selon l'axe des x
	 */
	public double[][] rotAxeX(double angle){
		angle = (angle/360)*(2*Math.PI);
		double[][] rotMat3D = { {1,0,0}, 
								{0,Math.cos(angle),-Math.sin(angle)}, 
								{0,Math.sin(angle),Math.cos(angle)}};
		return rotMat3D;
	}
	
	/**
	 * cette methode permet `a creer une matrix de rotation en R3 selon l'axe des y
	 * @param angle, angle de rotation
	 * @return, retourne la matrix de rotation en R3 selon l'axe des y
	 */
	public double[][] rotAxeY(double angle){
		angle = (angle/360)*(2*Math.PI);
		double[][] rotMat3D = { {Math.cos(angle),0,Math.sin(angle)}, 
								{0,1, 0}, 
								{-Math.sin(angle),0,Math.cos(angle)}};
		return rotMat3D;
	}
	
	/**
	 * cette methode permet `a creer une matrix de rotation en R3 selon l'axe des z
	 * @param angle, angle de rotation
	 * @return, retourne la matrix de rotation en R3 selon l'axe des z
	 */
	public double[][] rotAxeZ(double angle){
		angle = (angle/360)*(2*Math.PI);
		double[][] rotMat3D = { {Math.cos(angle),-Math.sin(angle),0}, 
								{Math.sin(angle),Math.cos(angle),0}, 
								{0,0,1}};
		return rotMat3D;
	}
}
