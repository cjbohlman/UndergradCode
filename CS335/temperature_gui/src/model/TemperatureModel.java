package model;

public class TemperatureModel {
	private double faren;
	private double cels;
	
	public TemperatureModel() {
		faren = 0;
		cels = 0;
	}
	
	public void setT(double f, double c) {
		faren = f;
		cels = c;
	}
	
	public double convertFtoC() {
		cels = (faren - 32) * 5/9;
		return cels;
	}
	
	public double convertCtoF() {
		faren = (cels * 9/5) + 32;
		return faren;
	}
}
