
class MeasuringCup
{
    Fraction capacity;

    public MeasuringCup (int numerator, int denominator)
    {
        capacity = new Fraction(numerator, denominator);
    }

    public int getNumerator()
    {
        return capacity.getNumerator(); // Stub
    }

    public int getDenominator()
    {
        return capacity.getDenominator(); // Stub
    }

} // MeasuringCup