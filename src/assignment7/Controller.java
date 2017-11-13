package assignment7;

/**
 * Created by alexis on 11/12/17.
 */
public class Controller extends Thread {
	private final Device device;
	private Sensor heat;
	private Sensor pressure;

	public Controller(Device device, Sensor heat, Sensor pressure) {
		this.device = device;
		this.heat = heat;
		this.pressure = pressure;
	}

	@Override
	public void run() {
		device.startup();
		synchronized (device) {
			while (heat.getValue() <= 70 && pressure.getValue() <= 100) {
				try {
					device.wait();
					System.out.println("heat -> " + heat.getValue() + ", pressure -> " + pressure.getValue());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			device.shutdown();
		}
	}
}
