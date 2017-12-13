/* Good work
 * Total score 10
 */
package assignment8;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Year;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by alexis on 11/19/17.
 */
public class MyJson {

	private static ArrayList<Vehicle> readAndGetVehicles(File file) throws IOException {
		ArrayList<Vehicle> vehicles = new ArrayList<Vehicle>();
		FileReader reader = new FileReader(file);
		Scanner in = new Scanner(reader);
		in.nextLine();
		while(in.hasNextLine()) {
			String line = in.nextLine();
			String[] arr = line.split("~");
			vehicles.add(new Vehicle(arr));
		}
		reader.close();
		in.close();
		return vehicles;
	}

	public static String getJsonString(ArrayList<Vehicle> vehicles) {
		StringBuilder sb = new StringBuilder();
		sb.append("{\n\"" + vehicles.get(0).webId + "\" : [\n");
		for (Vehicle vehicle : vehicles) {
			sb.append("{\n");
			sb.append("\"id\" : \"" + vehicle.id + "\",\n");
			sb.append("\"category\" : \"" + vehicle.category + "\",\n");
			sb.append("\"year\" : \"" + vehicle.year + "\",\n");
			sb.append("\"make\" : \"" + vehicle.make + "\",\n");
			sb.append("\"model\" : \"" + vehicle.model + "\",\n");
			sb.append("\"trim\" : \"" + vehicle.trim + "\",\n");
			sb.append("\"type\" : \"" + vehicle.type + "\",\n");
			sb.append("\"price\" : " + vehicle.price + ",\n");
			sb.append("\"photo\" : \"" + vehicle.photo + "\",\n");

			if (vehicle == vehicles.get(vehicles.size() - 1)) {
				sb.append("}\n");
			} else {
				sb.append("},\n");
			}
		}
		sb.append("]\n");
		sb.append("}\n");

		return sb.toString();
	}

	public static void writeToFile(String inputToWrite, String filePath) throws IOException{
		File file = new File(filePath + "/MyJson.txt");
		BufferedWriter bw = new BufferedWriter(new FileWriter(file));
		bw.write(inputToWrite);
		bw.close();
	}

	// Extra Credit
	public static String createOriginalFile(File file) throws IOException{
		StringBuilder sb = new StringBuilder();
		sb.append("id~webId~category~year~make~model~trim~type~price~photo\n");
		FileReader reader = new FileReader(file);
		Scanner in = new Scanner(reader);

		in.nextLine();
		String webIdLine = in. nextLine();
		String[] parts = webIdLine.split("\"");
		String webId = parts[1];

		while (in.hasNextLine()) {
			if (in.nextLine().equals("{")) {
				String line1 = in.nextLine();
				String[] arr1 = line1.split("\"");
				String id = arr1[3];

				String line2 = in.nextLine();
				String[] arr2 = line2.split("\"");
				String category = arr2[3].toLowerCase();

				String line3 = in.nextLine();
				String[] arr3 = line3.split("\"");
				String year = arr3[3];

				String line4 = in.nextLine();
				String[] arr4 = line4.split("\"");
				String make = arr4[3];

				String line5 = in.nextLine();
				String[] arr5 = line5.split("\"");
				String model = arr5[3];

				String line6 = in.nextLine();
				String[] arr6 = line6.split("\"");
				String trim = arr6[3];

				String line7 = in.nextLine();
				String[] arr7 = line7.split("\"");
				String type = arr7[3].toLowerCase();

				String line8 = in.nextLine();
				String[] arr8 = line8.split("\"");
				String tempPrice = arr8[2];
				String[] temp = tempPrice.split(" ");
				String price = temp[2];

				String line9 = in.nextLine();
				String[] arr9 = line9.split("\"");
				String photo = arr9[3];

				sb.append(id + "~" + webId + "~" + category + "~" + year + "~" + make + "~" + model + "~" + trim + "~"
							+ type + "~" + price);
				sb.deleteCharAt(sb.length() - 1);
				sb.append("~" + photo +"\n");
			}


			if (in.nextLine().equals("},")) {
				continue;
			}
		}
		reader.close();
		in.close();
		return sb.toString();
	}

	public static void writeOriginal(String inputToWrite, String filePath) throws IOException{
		File file = new File(filePath + "/MyOriginalFile.txt");
		BufferedWriter bw = new BufferedWriter(new FileWriter(file));
		bw.write(inputToWrite);
		bw.close();
	}

	public static void main(String[] args) throws IOException {
		File file = new File("src/assignment8/Camino.txt");
		ArrayList<Vehicle> vehicles = readAndGetVehicles(file);
		String s = getJsonString(vehicles);
		writeToFile(s, file.getParent());

		File file2 = new File("src/assignment8/MyJson.txt");
		String ss = createOriginalFile(file2);
		writeOriginal(ss, file2.getParent());
	}
}

class Vehicle {

	String id;
	String webId;
	Category category;
	Year year;
	String make;
	String model;
	String trim;
	String type;
	double price;
	URL photo;

	Vehicle(String[] arr) {
		this.id = arr[0];
		this.webId = arr[1];
		this.category = Category.getCategory(arr[2].toLowerCase());
		this.year = Year.parse(arr[3]);
		this.make = arr[4];
		this.model = arr[5];
		this.trim = arr[6];
		this.type = arr[7];
		this.price = Double.parseDouble(arr[8]);
		try {
			this.photo = new URL(arr[9]);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

}

enum Category {
	NEW, USED, CERTIFIED;

	public static Category getCategory(String cat) {
		switch (cat) {
			case "used":
				return USED;
			case "new":
				return NEW;
			case "certified":
				return CERTIFIED;
			default:
				throw new IllegalArgumentException();
		}
	}

	@Override
	public String toString() {
		switch (this) {
			case NEW:
				return "NEW";
			case USED:
				return "USED";
			case CERTIFIED:
				return "CERTIFIED";
			default:
				throw new IllegalArgumentException();
		}
	}
}


