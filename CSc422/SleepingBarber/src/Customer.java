public class Customer implements Runnable {
	
	String name;
	BarberMonitor shop;
	
	public Customer(int num, BarberMonitor shop) {
		name = "Customer "+Integer.toString(num);
		this.shop = shop;
	}
	
	public void run() {
		if (shop.addCustomer(this)) {
			shop.get_haircut(this);
			System.out.println(this.name+" has left with their hair cut.");
		}
	}
}
