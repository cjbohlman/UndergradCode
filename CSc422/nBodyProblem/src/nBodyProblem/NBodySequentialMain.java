package nBodyProblem;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Random;

public class NBodySequentialMain {
	
	static Point[] bodies;
	static Point[] velocities;
	static Point[] forces;
	static double[] masses;
	static double G = 6.67e-2;
	static int n;
	static double dt = 0.05;
	static int iters;
	static double loops;
	static int maxDim = 500;
	static int numWorkers;
	static int sizeBody;
	static long startTime;
	static long endTime;
	static Random random = new Random();
	static int velRange = 10;
	static boolean collArray[][];
	static int collisionsCounter = 0;
//	static ArrayList<Long> averageColTime = new ArrayList<Long>();
//	static ArrayList<Long> averageForceTime = new ArrayList<Long>();
//	static ArrayList<Long> averageMoveTime = new ArrayList<Long>();

    public static void main(String[] args) { 
    	//command line arguments
    	if (args.length == 4) {
    		numWorkers = Integer.parseInt(args[0]);
        	n = Integer.parseInt(args[1]);
        	sizeBody = Integer.parseInt(args[2]);
        	iters = Integer.parseInt(args[3]);
    	} else {
    		numWorkers = 1;
        	n = 2000;
        	sizeBody = 1; 
        	iters = 5000;
    	}
        StdDraw.setScale(0, maxDim);
        StdDraw.setPenColor(StdDraw.BLUE);
        StdDraw.enableDoubleBuffering();
        init();
        startTime = System.nanoTime();
        for (double i = 0; i < loops; i += dt) {
        	callLoop();
    		StdDraw.clear();
    		for (int j = 0; j < n; j++) {
    			StdDraw.filledCircle(bodies[j].x, bodies[j].y, sizeBody);
    		}
    		StdDraw.show();
            StdDraw.pause(10);
        }
        endTime = System.nanoTime();
		long difference = endTime - startTime;
		int seconds = (int) (difference / 1e+9);
		
		int milliseconds = (int) ((difference - (seconds*1e9))/(1e+6));
		
		System.out.println("computation time: "+seconds+" seconds "+milliseconds+" milliseconds");
		System.out.println("number of collisions: "+collisionsCounter);
		
		PrintWriter writer;
		try {
			writer = new PrintWriter("velocities.txt", "UTF-8");
			for (int i = 0; i < velocities.length; i++) {
				writer.println(velocities[i].x+"    "+velocities[i].y);
			}
			writer.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		PrintWriter writer2;
		try {
			writer2 = new PrintWriter("positions.txt", "UTF-8");
			for (int i = 0; i < bodies.length; i++) {
				writer2.println(bodies[i].x+"    "+bodies[i].y);
			}
			writer2.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		Long sum = (long) 0;
//		for (Long mark : averageColTime) {
//		        sum += mark;
//		    }
//		
//		System.out.println("Average time for checkCollisions: "+sum/averageColTime.size());
//		
//		sum = (long) 0;
//		for (Long mark : averageForceTime) {
//		        sum += mark;
//		    }
//		System.out.println("Average time for calculateForces: "+sum/averageForceTime.size());
//		
//		sum = (long) 0;
//		for (Long mark : averageMoveTime) {
//		        sum += mark;
//		    }
//		System.out.println("Average time for moveBodies: "+sum/averageMoveTime.size());
    }
    	
	public static void init() {
		bodies = new Point[n];
		velocities = new Point[n];
		forces = new Point[n];
		masses = new double[n];
		for (int i = 0; i < n; i++) {
			bodies[i] = new Point((Math.random() * (maxDim-10) + 10), (Math.random() * (maxDim-10) + 10));
			velocities[i] = new Point(random.nextDouble() * (velRange- (-1 * velRange)) + (-1*velRange), random.nextDouble() * (velRange- (-1 * velRange)) + (-1*velRange));
			forces[i] = new Point(0, 0);
			masses[i] = 5.0;
		}
		collArray = new boolean[n][n];
		loops = (iters * dt);
	}
	
	public static void callLoop() {
		calculateForces();
		moveBodies();
		checkForCollisions();
	}
	
	private static void checkForCollisions() {
//		long startTime2 = System.nanoTime();
		
		for(int i = 0; i < n - 1; i++) {
			for (int j = i+1; j < n; j++) {
				//Check collision
				double distance = Math.sqrt(Math.pow(bodies[i].x - bodies[j].x,2) + Math.pow(bodies[i].y - bodies[j].y,2));
				if (distance > 2*sizeBody) collArray[i][j] = false;
				if (distance <= 2*sizeBody && !collArray[i][j]) {
					collisionsCounter++;
					collArray[i][j] = true;
					double xDistance = (bodies[j].x - bodies[i].x);
					double yDistance = (bodies[j].y - bodies[i].y);
					
					double oldV1X = velocities[i].x;
					double oldV1Y = velocities[i].y;
					double oldV2X = velocities[j].x;
					double oldV2Y = velocities[j].y;
					
					velocities[i].x = 
							((oldV2X * xDistance * xDistance) + 
							(oldV2Y * xDistance * yDistance) +
							(oldV1X * yDistance * yDistance) - 
							(oldV1Y * xDistance * yDistance)) /
							((xDistance * xDistance) + (yDistance * yDistance));
					velocities[i].y = 
							((oldV2X * xDistance * yDistance) + 
							(oldV2Y * yDistance * yDistance) -
							(oldV1X * yDistance * xDistance) + 
							(oldV1Y * xDistance * xDistance)) /
							((xDistance * xDistance) + (yDistance * yDistance));
					velocities[j].x = 
							((oldV1X * xDistance * xDistance) + 
							(oldV1Y * xDistance * yDistance) +
							(oldV2X * yDistance * yDistance) - 
							(oldV2Y * xDistance * yDistance)) /
							((xDistance * xDistance) + (yDistance * yDistance));
					velocities[j].y = 
							((oldV1X * xDistance * yDistance) + 
							(oldV1Y * yDistance * yDistance) -
							(oldV2X * yDistance * xDistance) + 
							(oldV2Y * xDistance * xDistance)) /
							((xDistance * xDistance) + (yDistance * yDistance));
				}
			}
		}
//		long endTime2 = System.nanoTime();
//		long difference = endTime2 - startTime2;
//		averageColTime.add(difference);
	}

	private static void calculateForces() {
//		long startTime2 = System.nanoTime();
		double distance, magnitude;
		Point direction;
		for(int i = 0; i < n - 1; i++) {
			for (int j = i+1; j < n; j++) {
				distance = Math.sqrt(Math.pow((bodies[i].x - bodies[j].x),2) + Math.pow((bodies[i].y - bodies[j].y),2));
				magnitude = (G*masses[i]*masses[j])/Math.pow(distance, 2);
				direction = new Point(bodies[j].x - bodies[i].x, bodies[j].y - bodies[i].y);
				double forceX = magnitude*direction.x/distance;
				double forceY = magnitude*direction.y/distance;
				forces[i].x = forces[i].x + forceX;
				forces[i].y = forces[i].y + forceY;
				forces[j].x = forces[j].x - forceX;
				forces[j].y = forces[j].y - forceY;	
			}
		}
//		long endTime2 = System.nanoTime();
//		long difference = endTime2 - startTime2;
//		averageForceTime.add(difference);
	}

	private static void moveBodies() {
//		long startTime2 = System.nanoTime();
		Point deltav; //dv = f/m * DT
		Point deltap; //dp = (v + dv/2) * dt)
		for (int i = 0; i < n; i++) {
			deltav = new Point((forces[i].x/masses[i]) * dt, (forces[i].y/masses[i])* dt);
			deltap = new Point((velocities[i].x + (deltav.x*.5)) * dt, (velocities[i].y + (deltav.y*.5)) * dt);
			velocities[i].x = velocities[i].x + deltav.x;
			velocities[i].y = velocities[i].y + deltav.y;
			bodies[i].x = bodies[i].x + deltap.x;
			bodies[i].y = bodies[i].y + deltap.y;
			if (bodies[i].x < 0 ) {
				velocities[i].x = velocities[i].x * -1;
				bodies[i].x = 0;
			}
			if (bodies[i].y < 0) {
				velocities[i].y = velocities[i].y * -1;
				bodies[i].y = 0;
			}
			if (bodies[i].x > maxDim) {
				velocities[i].x = velocities[i].x * -1;
				bodies[i].x = maxDim;
			}
			if (bodies[i].y > maxDim) {
				velocities[i].y = velocities[i].y * -1;
				bodies[i].y = maxDim;
			}
			forces[i].x = 0;
			forces[i].y = 0;
		}
//		long endTime2 = System.nanoTime();
//		long difference = endTime2 - startTime2;
//		averageMoveTime.add(difference);
	}
}
