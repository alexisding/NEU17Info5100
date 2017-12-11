package assignment6;
// score 1
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

import static com.sun.tools.doclint.Entity.quot;

/**
 * Created by alexis on 10/16/17.
 */

public class Question2 {

	public static void parse(File file) {
		RandomAccessFile input = null;
		String line = null;

		try {
			input = new RandomAccessFile(file, "r");
			while ((line = input.readLine()) != null) {
				System.out.println(line);
			}
			return;
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}



