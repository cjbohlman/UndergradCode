import java.util.concurrent.TimeUnit;

public class CustomerGenerator implements Runnable {
	
	BarberMonitor shop;
	int numCustomers;
	float timeBetweenCustomers;
	
	public CustomerGenerator(BarberMonitor shop, int numCustomers, float timeBetweenCustomers) {
		this.shop = shop;
		this.numCustomers = numCustomers;
		this.timeBetweenCustomers = timeBetweenCustomers;
	}

	@Override
	public void run() {
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for (int i = 0; i < numCustomers; i++) {
			Customer customer = new Customer(i, shop);
			Thread t1 = new Thread(customer);
			t1.start();  
			try {
				TimeUnit.SECONDS.sleep((long) timeBetweenCustomers);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
