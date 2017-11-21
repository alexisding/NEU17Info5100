package assignment8;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;

/**
 * Created by alexis on 11/19/17.
 */
public class LyricAnalyzer {

	private static final int NEGATIVE = -1;
	private HashMap<String, ArrayList<Integer>> map;
	private int wordCount;

	public LyricAnalyzer() {
		map = new HashMap<String, ArrayList<Integer>>();
		wordCount = 0;
	}

	public void read(File file) throws IOException {
		FileReader reader = new FileReader(file);
		Scanner fileScanner = new Scanner(reader);

		while (fileScanner.hasNextLine()) {
			String line = fileScanner.nextLine();

			Scanner lineScanner = new Scanner(line);
			while (lineScanner.hasNext()) {
				String word = lineScanner.next();
				wordCount++;

				if (!lineScanner.hasNext()) {
					add(word.toUpperCase(), wordCount * NEGATIVE);
				} else {
					add(word.toUpperCase(), wordCount);
				}
			}
			lineScanner.close();
		}
		fileScanner.close();
		reader.close();
	}

	private void add(String lyricWord, int wordPosition) {
		if (!map.containsKey(lyricWord)) {
			ArrayList<Integer> positions = new ArrayList<Integer>();
			positions.add(wordPosition);
			map.put(lyricWord, positions);
		} else {
			map.get(lyricWord).add(wordPosition);
		}
	}

	public void displayWords() {
		Object[] words = map.keySet().toArray();
		Arrays.sort(words);

		for (Object word : words) {
			System.out.println(word + ": " + map.get(word));
		}
	}

	public void writeLyrics() throws IOException {
		String[] words = new String[wordCount + 1];
		for (int i = 0; i < words.length; i++) {
			words[i] = "";
		}
		for (Map.Entry<String, ArrayList<Integer>> entry : map.entrySet()) {
			ArrayList<Integer> positions = entry.getValue();
			for (int pos : positions) {
				if (pos < 0) {
					pos = Math.abs(pos);
					words[pos] = entry.getKey() + "\n";
				} else {
					words[pos] = entry.getKey();
				}
			}
		}

		for (String word : words) {
			System.out.print(word + " ");
		}
	}

	public int count() {
		return map.size();
	}

	public String mostFrequentWord() {
		int max = 0;
		ArrayList<String> maxWords = new ArrayList<String>();
		Object[] words = map.keySet().toArray();
		Arrays.sort(words);

		for (Object word : words) {
			if (map.get(word).size() > max) {
				max = map.get(word).size();
			}
		}

		for (Object word : words) {
			if (map.get(word).size() == max) {
				maxWords.add((String) word);
			}
		}

		return (String) maxWords.get(0);
	}

	public HashMap<String, ArrayList<Integer>> getMap() {
		return map;
	}

	public int getWordCount() {
		return wordCount;
	}

	public static void main(String[] args) throws IOException {
		LyricAnalyzer analyzer = new LyricAnalyzer();
		File file = new File("src/assignment8/Test2.txt");
		analyzer.read(file);
		analyzer.displayWords();
		System.out.println();
		analyzer.writeLyrics();
		System.out.println("\n Number of unique words: " + analyzer.count());
		System.out.println("\n Most frequent word: " + analyzer.mostFrequentWord());
	}
}
