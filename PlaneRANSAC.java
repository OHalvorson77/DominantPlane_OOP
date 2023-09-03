//Owen Halvorson 0300251644
import java.util.ArrayList;
import java.util.Iterator;

public class PlaneRANSAC {
	//Declaring class variables
	private double eps;
	private PointCloud pointCloud;
	
	//Declaring constructor method which takes a point cloud and intializes the class variable 'pointCloud'
	public PlaneRANSAC(PointCloud pc) {
		this.pointCloud=pc;
	}
	
	//Setter method for eps variable
	public void setEps(double eps) {
		this.eps=eps;
	}
	
	//Getter method for eps variable
	public double getEps() {
		return this.eps;
	}
	
	//Method to calculate number of iterations
	public int getNumberOfIterations(double confidence, double percentageOfPointsOnPlane) {
		//Formula to calculate number of iterations using confidence and percentage of points on plane, rounds up to nearest int
		return (int)(Math.ceil(Math.log(1-confidence)/Math.log(1-(percentageOfPointsOnPlane*percentageOfPointsOnPlane*percentageOfPointsOnPlane))));
	}

	//Method that runs the PlaneRANSAC Algorithm, takes numberOfIterations and a filename as parameter
	public void run(int numberOfIterations, String filename) {
		//Declaring variables for the method
		Point3D point1; 
		Point3D point2;
		Point3D point3;
		Point3D point;
		Plane3D plane;
		Plane3D dominantPlane=null;
		int count;
		int bestSupport=0; 
		//Loops number of iterations times
		for (int i=0; i<numberOfIterations; i++) {
			//Initializes count to 0
				count=0;
				//Randomly gets three points from point cloud
				point1=pointCloud.getPoint();
				point2=pointCloud.getPoint();
				point3=pointCloud.getPoint();
				//Creating a plane with the three random points
				plane=new Plane3D(point1, point2, point3); 
				
				//Iterating through point cloud to see how many points this plane supports
				Iterator<Point3D> iterator= pointCloud.iterator();
				while (iterator.hasNext()) {
					point=iterator.next();
					if (plane.getDistance(point)< this.eps) {
						count++;	
			}
				}
				//If the plane supports more points then the dominant plane, it becomes the dominant plane
				if (count>bestSupport) {
					bestSupport=count;
					dominantPlane=plane;
				}
				
			
		}
		
		//Loops through the point cloud and removes every point belonging to the dominant plane from the original point cloud and adding it to a new one
		Iterator<Point3D> iterator2=pointCloud.iterator(); 
		PointCloud pointCloud2=new PointCloud();
		while (iterator2.hasNext()) {
			Point3D testPoint=iterator2.next();
			if (dominantPlane.getDistance(testPoint)<this.eps) {
			pointCloud2.addPoint(testPoint);
			iterator2.remove();
			}
		}
		//Printing size of new point Cloud
		System.out.println(pointCloud2.getSize());
		//Saving the new point cloud
		pointCloud2.save(filename);
		
		
	}
	//Main method for program
	public static void main (String[] args) {
		//Getting file name
		String file= args[0];
		String[] file2=file.split("\\.");
		
		//Declaring number of iterations variable
		int numberOfIterations;
		
		//Making a new pointcloud with using the filename
		PointCloud pointCloud=new PointCloud(file);
		
		//Making a new PlaneRANSAC using pointcloud just creates
		PlaneRANSAC pr=new PlaneRANSAC(pointCloud);
		
		//Setting maximum distance from plane to 1
		pr.setEps(1);
		
		//Saving the original pointcloud
		pointCloud.save(file2[0]+"_p0"+".xyz");
		//Displaying size of original pointcloud
		System.out.println("Original PointCloud size: " +pointCloud.getSize());
		//Looping three times
		
		for (int i=0; i<3; i++) {
			//Finding number of iterations with 99 percent confidence and 60 percent of points on plane
			numberOfIterations=pr.getNumberOfIterations(0.99,0.6);
			//Statement to display size of plane
			System.out.println("Dominant plane "+(i+1) +" Size:");
			//Running run method
			pr.run(numberOfIterations, file2[0] +"_p"+String.valueOf(i+1)+ ".xyz");
			
		}
	}
	
	
}
