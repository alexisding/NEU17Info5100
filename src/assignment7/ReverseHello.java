package assignment7;

/**
 * Created by alexis on 11/12/17.
 */
public class ReverseHello extends Thread {
	private int count;

	public ReverseHello(int count) {
		this.count = count;
	}

	@Override
	public void run() {
		count++;
		if (count <= 50) {
			ReverseHello thread = new ReverseHello(count);
			thread.start();
			try {
				thread.join();
				System.out.println("Hello from Thread " + count + "!");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		ReverseHello rh = new ReverseHello(0);
		rh.start();
	}
}
