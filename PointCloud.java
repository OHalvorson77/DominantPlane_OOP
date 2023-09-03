//Owen Halvorson 0300251644

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class PointCloud {
	
	//Declaring a pointcloud variable which is an array list
	private ArrayList<Point3D>pointCloud;
	
	//Constructor method that takes a filename as a parameter and reads it, turning it into a point cloud list
	public PointCloud (String filename) {
		String line ="";//Creating an empty line
		
		BufferedReader bufferReader=null;//Initializing reader to null
		
		pointCloud=new ArrayList<>();//Creating a new array list for the points that are read
		
		double x;//Initializing x y and z values as doubles
		
		double y;
		double z;
		
		
		
		
		try {
			bufferReader=new BufferedReader(new FileReader(filename));//using bufferreader to read file and access by filename
			bufferReader.readLine();//Reading the first line
			while((line=bufferReader.readLine())!=null) {//While there is still information left in the file
				String[] point=line.split("\t");//Removing the commas and getting the x y and z values from the file
				try {
					x=Double.parseDouble(point[0]);
					y=Double.parseDouble(point[1]);
					z=Double.parseDouble(point[2]);
					Point3D p=new Point3D(x,y,z);//Intializing a new point with the x y and z values
					pointCloud.add(p);//Adding the point to the list
					
					
					
				}
				catch(NumberFormatException e) {//Checking for exceptions
					
				}
			}
			
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}finally {//Closing buffer reader
			if (bufferReader!=null) {
				try {
					bufferReader.close();
				}catch(IOException e) {
					e.printStackTrace();
				}
				
			}
		}
	
	}
	
	
	//Pointcloud constructor method that creates an empty pointcloud list
	public PointCloud () {
		pointCloud=new ArrayList<Point3D>();
	}
	
	//Method that adds a point to the pointCloud list
	public void addPoint(Point3D pt) {
		pointCloud.add(pt);
		
	}
	
	//Method returning size of point cloud list
	public int getSize() {
		return pointCloud.size();
	}
	//Method that gets a random point from pointCloud list
	public Point3D getPoint() {
		Random rand=new Random();
		//Getting a random integer less then pointCloud list size
		int i=rand.nextInt(pointCloud.size());
		//Returning the random pointCloud point
		return pointCloud.get(i);
	}
	
	//Method that saves a pointCloud into a file, given the name of the new file as a parameter
	public void save(String filename) {
		//Making save method that takes a file name as the parameter
			String[] fn= filename.split("\\.");
			
			
			String fileWithUpdateName=(fn[0]);//Changing the filename to display the proper values
			File file=new File(fileWithUpdateName);//Creating the file with the new name
			
			
			try {
				
				
				FileWriter fileWriter=new FileWriter(file);//Initializing a new instance of FileWriter
				fileWriter.write("X Y Z\n");//Writing the header values
				for(int i=0; i<pointCloud.size(); i++) {//Looping through the point list and getting the values
					Point3D point=pointCloud.get(i); 
					fileWriter.write(point.getX() + " " + point.getY() + " " +point.getZ()+"\n");//Writing the values into the new file
				}
				fileWriter.close();//Stop writing
			}catch (IOException e) {//Check for exceptions
				throw new RuntimeException(e);
			}
		}
		
	//Method that returns an iterator for the pointCloud list
	public Iterator<Point3D> iterator(){
		Iterator<Point3D> a= pointCloud.iterator();
		return a;
	
	}
}
