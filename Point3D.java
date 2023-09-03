//Owen Halvorson 0300251644
public class Point3D {
	//Declaring the three coordinate variables as doubles
	private double x; 
	private double y; 
	private double z; 
	
	//The constructor for the Point3D class which takes 3 coordinate integers and sets the coordinate variables to those values
	public Point3D(double x, double y, double z) {
		this.x=x;
		this.y=y;
		this.z=z;
	}
	
	//Method to get the z coordinate
	public double getZ() { 
		return z;
	}
	
	//Method to get the y coordinate
	public double getY() {
		return y;
	}
	
	//Method to get the x coordinate
	public double getX() {
		return x;
	}
}
