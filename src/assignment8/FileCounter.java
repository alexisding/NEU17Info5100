package assignment8;

import java.io.IOException;
import java.util.Scanner;

/**
 * Created by alexis on 11/19/17.
 */
public class FileCounter {
	private int characterCount, wordCount, lineCount;

	public FileCounter() {
		characterCount = 0;
		wordCount = 0;
		lineCount = 0;
	}

	public void read(Scanner in) throws IOException {
		while (in.hasNextLine()) {
			lineCount++; // count lines

			// count characters
			String line = in.nextLine();
			char[] chars = line.toCharArray();
			for (char c : chars) {
				if (c != ' ') {
					characterCount++;
				}
			}

			// count words
			Scanner lineScanner = new Scanner(line);
			while (lineScanner.hasNext()) {
				lineScanner.next();
				wordCount++;
			}
			lineScanner.close();
		}
	}

	public int getCharacterCount() {
		return characterCount;
	}

	public int getWordCount() {
		return wordCount;
	}

	public int getLineCount() {
		return lineCount;
	}
}
