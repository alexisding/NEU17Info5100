package assignment7;

/**
 * Created by alexis on 11/12/17.
 */
public class Sensor extends Thread {
	private final Device device;
	private double value;

	public Sensor(Device device) {
		this.device = device;
	}

	public double getValue() {
		//return (Math.round(value * 100.00)) / 100.00;
		return value;
	}

	public void updateValue() {
		this.value += 0.001;
	}

	@Override
	public void run() {
		while (true) {
			synchronized (device) {
				double preValue = value;
				updateValue();
				if (value != preValue) {
				device.notify();
				}
			}
		}
	}
}
