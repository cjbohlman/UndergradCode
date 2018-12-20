import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

public class BarberMonitor {
	
	int iters = 0;
	private int barber = 0, chair = 0, open = 0;
	Object barber_available = new Object();
	Object door_opened = new Object();
	Object chair_occupied = new Object();
	Object customer_left = new Object();
	float haircutTime;
	int waitingCustomerChairs;
	Queue<Customer> CustomerList = new LinkedList<Customer>(); 
	Customer curCustomer;
	
	public BarberMonitor(float haircutTime, int waitingCustomerChairs) {
		this.haircutTime = haircutTime;
		this.waitingCustomerChairs = waitingCustomerChairs;
	}

	public synchronized void get_haircut(Customer newCustomer) {
		while (barber == 0) {
			try {
				System.out.println(newCustomer.name+" is waiting to recieve a haircut. Total customers waiting: "+CustomerList.size());
				synchronized (barber_available) {
					barber_available.wait();
				}
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		Customer customer = CustomerList.poll();
		System.out.println(customer.name+" is in the barber chair.");
		curCustomer = customer;
		barber--;
		chair++;
		synchronized (chair_occupied) {
			chair_occupied.notify();
		}
		try {
			TimeUnit.SECONDS.sleep((long) haircutTime);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		System.out.println(curCustomer.name+"'s haircut is finished.");
		while (open == 0) {
			try {
				System.out.println(curCustomer.name+" is waiting to for the door to open.");
				door_opened.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		open--;
		synchronized(customer_left) {
			customer_left.notify();
			iters++;
		}		
	}
	
	public void get_next_customer() {
		
		barber++;
		synchronized(barber_available) {
			barber_available.notify();
		}	
		while (chair == 0) {
			try {
				synchronized(chair_occupied) {
					chair_occupied.wait();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		chair--;
	}

	public void finished_cut() {
		open++;
		synchronized (door_opened) {
			door_opened.notify();
		}
		while (open > 0) {
			try {
				synchronized(customer_left) {
					customer_left.wait();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public boolean addCustomer(Customer customer) {
		synchronized (CustomerList)
        {		
			if (CustomerList.size() >= waitingCustomerChairs) {
				System.out.println(customer.name+" has left, the line was too long!");
				iters++;
				return false;
			} else {
				CustomerList.add(customer);
				System.out.println(customer.name+" has entered the building.");
				return true;
			}	
        }
	}
}
