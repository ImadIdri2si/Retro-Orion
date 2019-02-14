package fourthDim;
/**
 * cette classe permet `a definir les matricielles de rotaiton en R4
 * @author XU WEI DUO
 *
 */
public class Matrix_Rotation_4D {
	
	/**
	public double[][] set4DRot(int entry, double angle){
		switch(entry) {
		case 0 : return this.rotYZ(angle);
		case 1 : return this.rotXZ(angle);
		case 2 : return this.rotXY(angle);
		case 3 : return this.rotXW(angle);
		case 4 : return this.rotYW(angle);
		case 5 : return this.rotZW(angle);
		default : return null;
		}
	}
	**/
	
	/**
	 * cette methode permet `a creer une matrix de rotation en R4 selon le plan yz
	 * @param angle, angle de rotation
	 * @return, retourne la matrix de rotation en R4 selon le plan yz
	 */
	public double[][] rotYZ(double angle){
		angle = (angle/360)*(2*Math.PI);
		double[][] rotMat4D = { {1,0,0,0}, 
								{0,Math.cos(angle),-Math.sin(angle),0}, 
								{0,Math.sin(angle),Math.cos(angle),0}, 
								{0,0,0,1}};
		return rotMat4D;
	}
	
	/**
	 * cette methode permet `a creer une matrix de rotation en R4 selon le plan xz
	 * @param angle, angle de rotation
	 * @return, retourne la matrix de rotation en R4 selon le plan xz
	 */
	public double[][] rotXZ(double angle){
		angle = (angle/360)*(2*Math.PI);
		double[][] rotMat4D = { {Math.cos(angle),0,Math.sin(angle),0}, 
								{0,1, 0, 0}, 
								{-Math.sin(angle),0,Math.cos(angle),0}, 
								{0,0,0,1}};
		return rotMat4D;
	}
	
	/**
	 * cette methode permet `a creer une matrix de rotation en R4 selon le plan xy
	 * @param angle, angle de rotation
	 * @return, retourne la matrix de rotation en R4 selon le plan xy
	 */
	public double[][] rotXY(double angle){
		angle = (angle/360)*(2*Math.PI);
		double[][] rotMat4D = { {Math.cos(angle),-Math.sin(angle),0,0}, 
								{Math.sin(angle),Math.cos(angle),0,0}, 
								{0,0,1,0}, 
								{0,0,0,1}};
		return rotMat4D;
	}
	
	/**
	 * cette methode permet `a creer une matrix de rotation en R4 selon le plan xw
	 * @param angle, angle de rotation
	 * @return, retourne la matrix de rotation en R4 selon le plan xw
	 */
	public double[][] rotXW(double angle){
		angle = (angle/360)*(2*Math.PI);
		double[][] rotMat4D = { {Math.cos(angle),0,0,Math.sin(angle)}, 
								{0,1,0,0}, 
								{0,0,1,0}, 
								{-Math.sin(angle),0,0,Math.cos(angle)}};
		return rotMat4D;
	}
	
	/**
	 * cette methode permet `a creer une matrix de rotation en R4 selon le plan yw
	 * @param angle, angle de rotation
	 * @return, retourne la matrix de rotation en R4 selon le plan yw
	 */
	public double[][] rotYW(double angle){
		angle = (angle/360)*(2*Math.PI);
		double[][] rotMat4D = { {1,0,0,0}, 
								{0,Math.cos(angle),0,-Math.sin(angle)}, 
								{0,0,1,0}, 
								{0,Math.sin(angle),0,Math.cos(angle)}};
		return rotMat4D;
	}
	
	/**
	 * cette methode permet `a creer une matrix de rotation en R4 selon le plan zw
	 * @param angle, angle de rotation
	 * @return, retourne la matrix de rotation en R4 selon le plan zw
	 */
	public double[][] rotZW(double angle){
		angle = (angle/360)*(2*Math.PI);
		double[][] rotMat4D = { {1,0,0,0}, 
								{0,1,0,0}, 
								{0,0,Math.cos(angle),-Math.sin(angle)}, 
								{0,0,Math.sin(angle),Math.cos(angle)}};
		return rotMat4D;
	}
}
