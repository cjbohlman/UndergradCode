import java.lang.*;

public class RaffleTicketGenerator extends TicketGenerator implements TicketGeneratable {
  
  int startNumber;
  
  public RaffleTicketGenerator()
  {
    super();
    startNumber = 0;
  }
  
  // Place the second constructor here
  public RaffleTicketGenerator(int startNumber)
  {
    super();
    this.nextNumber = nextNumber;
    this.startNumber = startNumber;
  }
  
  
  
  
  
  
  
  public int qtyIssued()
  {
    return nextNumber-startNumber;
  }
  
  // Place firstIssued() here.
  
  public int firstIssued()
  {
    int ticketNum;
    if(!anyIssued) {
      ticketNum = NONE_ISSUED;
    }
    else {
      ticketNum = startNumber;
    }
    return ticketNum;
  }
  
  // Place reset() here
  public void reset(int sNumber) {
    anyIssued = false;
    this.startNumber = sNumber;
    this.nextNumber = sNumber;
    this.number = sNumber;
  }
  
  public int drawWinner() {
    int ticketNum;
    if(!anyIssued) {
      ticketNum = NONE_ISSUED;
    }
    else {
      ticketNum = (int) Math.random() * (number - startNumber) + startNumber;
    }
    return ticketNum;
  }
  
  
  
  
  
  
  // Place drawWinner() here
  
  
  
  
  
  
  
  
} // RaffleTicketGenerator