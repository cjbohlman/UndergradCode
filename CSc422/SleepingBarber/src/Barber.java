public class Barber implements Runnable{
	
	int haircuts = 0;
	int barber = 0, chair = 0, open = 0;
	BarberMonitor shop;
	
	static int numCustomers;
	static float haircutTime;
	static float timeBetweenCustomers;
	static int waitingCustomerChairs;
	
	public static void main(String[] args) {
		if (args.length == 4) {
			numCustomers = Integer.parseInt(args[0]);
			haircutTime = Float.parseFloat(args[1]);
			timeBetweenCustomers = Float.parseFloat(args[2]);
			waitingCustomerChairs = Integer.parseInt(args[3]);
		} else {
			numCustomers = 25;
			haircutTime = 0.0f;
			timeBetweenCustomers = 0.0f;
			waitingCustomerChairs = 3;
		}
		System.out.println("=================================================================");
		System.out.println("Parameters:");
		System.out.println("Number of customers = "+numCustomers);
		System.out.println("Haircut time = "+haircutTime+" seconds");
		System.out.println("Time between customers = "+timeBetweenCustomers+" seconds");
		System.out.println("Number of waiting chairs = "+waitingCustomerChairs);
		System.out.println("=================================================================");
		System.out.println();
		Barber barber = new Barber();
		barber.shop = new BarberMonitor(haircutTime, waitingCustomerChairs);
		Thread t1 = new Thread(barber);
		t1.start();
		CustomerGenerator cg = new CustomerGenerator(barber.shop, numCustomers, timeBetweenCustomers);
		Thread t2 = new Thread(cg);
		t2.start();
	}

	@Override
	public void run() {
		System.out.println("Barber is open for business!");
		while (true) {
			if (shop.iters == numCustomers) break;
			shop.get_next_customer();
			if (shop.iters == numCustomers) break;
			shop.finished_cut();
			if (shop.iters == numCustomers) break;
		}
		System.out.println("Barber is all done with hair cutting!");
	}
	

}
