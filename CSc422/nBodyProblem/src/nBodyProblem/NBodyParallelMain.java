package nBodyProblem;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class NBodyParallelMain {
	
	static Point[] bodies;
	static Point[] velocities;
	static Point[][] forces;
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
	static int stages;
	static Semaphore[][] semArr = new Semaphore[5][32];
	public static nBodyWorker[] threadArr;
	static boolean collArray[][];
	static AtomicInteger collisionCounter = new AtomicInteger();
//	static List<Long> averageColTime = Collections.synchronizedList(new ArrayList<Long>());
//	static List<Long> averageForceTime = Collections.synchronizedList(new ArrayList<Long>());
//	static List<Long> averageMoveTime = Collections.synchronizedList(new ArrayList<Long>());
	
    public NBodyParallelMain(String[] args) { 
    	//command line arguments
    	if (args.length == 4) {
    		numWorkers = Integer.parseInt(args[0]);
        	n = Integer.parseInt(args[1]);
        	sizeBody = Integer.parseInt(args[2]);
        	iters = Integer.parseInt(args[3]);
    	} else {
    		numWorkers = 2;
        	n = 2000;
        	sizeBody = 1;
        	iters = 5000;
    	}
        StdDraw.setScale(0, maxDim);
        StdDraw.setPenColor(StdDraw.BLUE);
        StdDraw.enableDoubleBuffering();
        init();
        startTime = System.nanoTime();
        
        // make threads
     	for (int i = 0; i < numWorkers; i++) {
     		threadArr[i] = new nBodyWorker(i);
     		threadArr[i].start();
     	}

     	// join threads
     	for (int i = 0; i < numWorkers; i++) {
     		try {
     			threadArr[i].join();
     		} catch (InterruptedException e) {
     			e.printStackTrace();
     		}
     	}
        
        endTime = System.nanoTime();
		long difference = endTime - startTime;
				
		int seconds = (int) (difference / 1e+9);
		
		int milliseconds = (int) ((difference - (seconds*1e9))/(1e+6));
		
		System.out.println("computation time: "+seconds+" seconds "+milliseconds+" milliseconds");
		System.out.println("number of collisions: "+collisionCounter);
		
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
		forces = new Point[numWorkers][n];
		masses = new double[n];
		for (int i = 0; i < n; i++) {
			bodies[i] = new Point((Math.random() * (maxDim-10) + 10), (Math.random() * (maxDim-10) + 10));
			velocities[i] = new Point(random.nextDouble() * (velRange- (-1 * velRange)) + (-1*velRange),
					random.nextDouble() * (velRange- (-1 * velRange)) + (-1*velRange));
			for (int j = 0; j < numWorkers; j++) {
				forces[j][i] = new Point(0, 0);
			}
			masses[i] = 5.0;
		}
		
		stages = (int) Math.ceil(Math.log(numWorkers)/Math.log(2));
		// init semaphores
		for (int x = 0; x < stages; x++) {
			for (int y = 0; y < numWorkers; y++) {
				semArr[x][y] = new Semaphore(0);
			}
		}
				
		threadArr = new nBodyWorker[numWorkers];
		collArray = new boolean[n][n];
		loops = (iters * dt);
	}
	
	private static void checkForCollisionsParallel(int threadNum) {
//		long startTime2 = System.nanoTime();
		for(int i = threadNum; i < n; i += numWorkers) {
			for (int j = i+1; j < n; j++) {
				double distance = Math.sqrt(Math.pow(bodies[i].x - bodies[j].x,2) + Math.pow(bodies[i].y - bodies[j].y,2));
				if (distance > 2*sizeBody) collArray[i][j] = false;
				if (distance <= 2*sizeBody && !collArray[i][j]) {
					collisionCounter.incrementAndGet();
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
	
	private static void calculateForces(int threadNum) {
//		long startTime2 = System.nanoTime();
		double distance, magnitude;
		Point direction;
		for(int i = threadNum; i < n; i += numWorkers) {
			for (int j = i+1; j < n; j++) {
				distance = Math.sqrt(Math.pow((bodies[i].x - bodies[j].x),2) + Math.pow((bodies[i].y - bodies[j].y),2));
				magnitude = (G*masses[i]*masses[j])/Math.pow(distance, 2);
				direction = new Point(bodies[j].x - bodies[i].x, bodies[j].y - bodies[i].y);
				double forceX = magnitude*direction.x/distance;
				double forceY = magnitude*direction.y/distance;
				forces[threadNum][i].x = forces[threadNum][i].x + forceX;
				forces[threadNum][i].y = forces[threadNum][i].y + forceY;
				forces[threadNum][j].x = forces[threadNum][j].x - forceX;
				forces[threadNum][j].y = forces[threadNum][j].y - forceY;	
			}
		}
//		long endTime2 = System.nanoTime();
//		long difference = endTime2 - startTime2;
//		averageForceTime.add(difference);
	}

	private static void moveBodies(int threadNum) {
//		long startTime2 = System.nanoTime();
		Point deltav; //dv = f/m * DT
		Point deltap; //dp = (v + dv/2) * dt)
		Point force = new Point(0, 0);
		
		for (int i = threadNum; i < n; i+= numWorkers) {
			
			for (int k = 0; k < numWorkers; k++) {
				force.x += forces[k][i].x;
				forces[k][i].x = 0;
				force.y += forces[k][i].y;
				forces[k][i].y = 0;
			}
			
			deltav = new Point((force.x/masses[i]) * dt, (force.y/masses[i])* dt);
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
			force.x = 0;
			force.y = 0;
		}
		
		for (int i = 0; i < n; i++) {
			deltav = new Point((forces[0][i].x/masses[i]) * dt, (forces[0][i].y/masses[i])* dt);
			deltap = new Point((velocities[i].x + (deltav.x*.5)) * dt, (velocities[i].y + (deltav.y*.5)) * dt);
			
		}
//		long endTime2 = System.nanoTime();
//		long difference = endTime2 - startTime2;
//		averageMoveTime.add(difference);
	}
	
	public class nBodyWorker extends Thread {
		private int threadNum;
		
		public nBodyWorker(int threadNum) {
			this.threadNum = threadNum;
		}
		
		public void run() {
			for (double time = 0; time < loops; time += dt) {
				calculateForces(threadNum);
				//barrier
				for (int i = 0; i < stages; i++) {	
					int power = (int) Math.pow(2,i);
					// V op on other thread
					semArr[i][(power+threadNum)%(numWorkers)].release();
					// P op on self
					try {
						semArr[i][threadNum].acquire();
					} catch (InterruptedException e) {};
				}
				
				moveBodies(threadNum);
				//barrier
				for (int i = 0; i < stages; i++) {	
					int power = (int) Math.pow(2,i);
					// V op on other thread
					semArr[i][(power+threadNum)%(numWorkers)].release();
					// P op on self
					try {
						semArr[i][threadNum].acquire();
					} catch (InterruptedException e) {};
				}
				
				checkForCollisionsParallel(threadNum);
				//barrier
				for (int i = 0; i < stages; i++) {	
					int power = (int) Math.pow(2,i);
					// V op on other thread
					semArr[i][(power+threadNum)%(numWorkers)].release();
					// P op on self
					try {
						semArr[i][threadNum].acquire();
					} catch (InterruptedException e) {};
				}
				
				if (threadNum == 0) {
					StdDraw.clear();
		    		for (int j = 0; j < n; j++) {
		    			StdDraw.filledCircle(bodies[j].x, bodies[j].y, sizeBody);
		    		}
		    		StdDraw.show();
		            StdDraw.pause(10);
				}
			}		
		}
	}
	
}
