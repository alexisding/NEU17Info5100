package assignment4;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexis on 10/3/17.
 */

// Question 3
public class IpAddress {
	private String dottedDecimal;
	private int firstOctet;
	private int secondOctet;
	private int thirdOctet;
	private int fourthOctet;

	public IpAddress(String dottedDecimal) {
		this.dottedDecimal = dottedDecimal;
	}

	public String getDottedDecimal() {
		return dottedDecimal;
	}

	public int getOctet(int position) {
		List<String> octets = splitDecimal(dottedDecimal);
		String octet = octets.get(position - 1);
		return Integer.parseInt(octet);
	}

	public List<String> splitDecimal(String dottedDecimal) {

		int start = 0;
		int end = 0;
		List<String> octets = new ArrayList<String>();

		while (start >= 0 && end >= 0) {
			end = dottedDecimal.indexOf(".", start);
			if (end >= 0) {
				octets.add(dottedDecimal.substring(start, end));
			} else {
				octets.add(dottedDecimal.substring(start, dottedDecimal.length()));
			}
			start = end + 1;
		}
		return octets;
	}
}

class TestIpAddress {

	public static void main(String[] args) {

		IpAddress ip = new IpAddress("216.27.6.136");
		System.out.println(ip.getDottedDecimal());
		System.out.println(ip.getOctet(4));
		System.out.println(ip.getOctet(1));
		System.out.println(ip.getOctet(3));
		System.out.println(ip.getOctet(2));
	}
}
