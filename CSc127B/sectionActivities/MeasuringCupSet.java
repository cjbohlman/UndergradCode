import java.io.*;
  
class MeasuringCupSet implements Serializable
{
    final int MAX_CUPS = 10;
    int numberOfCups;
    MeasuringCup[] set;

    public MeasuringCupSet ()
    {
       numberOfCups = 0;
       set = new MeasuringCup[MAX_CUPS];
    }

    public int getOccupancy()
    {
        return numberOfCups;
    }

    public MeasuringCup getCup(int index)
    {
        return set[index];
    }

    public void addACup(MeasuringCup cup)
    {
        set[numberOfCups + 1] = cup;
    }

} // MeasuringCupSet