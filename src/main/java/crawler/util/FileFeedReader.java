package crawler.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileFeedReader {

	public static List<String> readFeed(String file) throws IOException {
		List<String> result = new ArrayList<String>();
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		String line;
		while ((line = br.readLine()) != null) {
			if (line.trim().length() > 1) {
				result.add(line.trim());
			}
		}

		br.close();
		fr.close();
		return result;
	}
}
