//Owen Halvorson 0300251644
public class Plane3D {
	//Three point variables for the class
	private Point3D pointA; 
	private Point3D pointB; 
	private Point3D pointC;
	
	
	//Declaring the a b c and d variables for the plane equation as doubles
	private double a;
	private double b; 
	private double c;
	private double d;
	
	//Plane3D constructor method that takes three points as inputs and then calculates the a, b, c and d plane equation variables
	public Plane3D (Point3D a, Point3D b, Point3D c) { 
		this.pointA=a;
		this.pointB=b;
		this.pointC=c;
		//Calculations to get the plane equation
		//Calculating the vector of b-a
		Point3D vectorA=new Point3D(b.getX()-a.getX(), b.getY()-a.getY(), b.getZ()-a.getZ()); 
		
		//Calculating the vector of c-a
		Point3D vectorB= new Point3D(c.getX()-a.getX(), c.getY()-a.getY(), c.getZ()-a.getZ());
		
		//Getting the cross value of vector a x vector b
		this.a= (vectorA.getY()*vectorB.getZ())-(vectorA.getZ()*vectorB.getY());
		this.b= (vectorA.getZ()*vectorB.getX())-(vectorA.getX()*vectorB.getZ());
		this.c= (vectorA.getX()*vectorB.getY())-(vectorA.getY()*vectorB.getX());
		
		//Plugging in point a into plane equation to get the value of d
		this.d= (this.a*pointA.getX())+(this.b*pointA.getY())+(this.c*pointA.getZ());
		
		
	}

	//Plane3D constructor that takes a,b,c and d and sets the a,b,c and d variables to those values
	public Plane3D (double a, double b, double c, double d) { 
		this.a=a; 
		this.b=b; 
		this.c=c; 
		this.d=d;
		
	}
	
	//Method that calculates distance between a given point and the plane 
	public double getDistance(Point3D pt) { 
		double topValue= Math.abs((this.a*pt.getX())+(this.b*pt.getY())+(this.c*pt.getZ()+d));
		double bottomValue=Math.sqrt((this.a*this.a)+(this.b*this.b)+(this.c*this.c));
		return topValue/bottomValue;
	}
}
