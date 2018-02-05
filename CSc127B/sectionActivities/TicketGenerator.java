public class TicketGenerator implements TicketGeneratable {
  
  public int NONE_ISSUED = -1;
  boolean anyIssued = false;
  int nextNumber;
  int number = 0;
  
  public TicketGenerator() {
    this.nextNumber = 000000;
  }
  
  public String issueTicket() {
    String ticket = "";
    ticket = ticket.format("%06d",nextNumber);
    nextNumber++;
    anyIssued = true;
    return ticket;
  }
  
  public int qtyIssued() {
    if (!anyIssued) {
      number = 0;
    }
    else {
      number++;
    }
    return number;
    
  }
  
  public int firstIssued() {
    int firstNum;
    if(!anyIssued) {
      firstNum = NONE_ISSUED;
    }
    else {
      firstNum = 0;
    }
    
    return firstNum;
  }
  
  public int lastIssued() {
    int lastNum;
    if(!anyIssued) {
      lastNum = NONE_ISSUED;
    }
    else {
      lastNum = nextNumber - 1;
    }
    
    return lastNum;
  }
}