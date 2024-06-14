//Class: FileManager.Java
//Group Id: 12
//Authors: Ido Lublin, Binyamin Alony, Dana Bakshe, Roni Weiss, Yael Tsabari, Daniel Maya

package final_project;

import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class FileManager {
	public static void printToFile(EntryNode node) {
		try {
			new File("myPhoneBook.txt");
			Path fileName = Path.of("myPhoneBook.txt");
			String toWrite = "";
			while (node != null) {
				toWrite += (node.name + ":" + node.number + "\n");
				node = node.next;
			}
			Files.writeString(fileName, toWrite);
		} catch (IOException ioEx) {
			System.out.println("error while saving to file"); // or what ever you want to do with it
		}
	}

	public static List<String> readFromFile(String Path) {
		List<String> myList = new ArrayList<String>();
		try {
			File file = new File(Path); // creates a new file instance
			FileReader fr = new FileReader(file); // reads the file
			BufferedReader br = new BufferedReader(fr); // creates a buffering character input stream
			StringBuffer sb = new StringBuffer(); // constructs a string buffer with no characters
			String line;
			while ((line = br.readLine()) != null) {
				sb.append(line); // appends line to string buffer
				myList.add(sb.toString());
				System.out.println("read:"+sb.toString());
				sb.delete(0, sb.length());
			}
			fr.close(); // closes the stream and release the resources
			return myList;
		} catch (IOException ioEx) {
			System.out.println("error while readong from file");
			return myList;
		}
	}
}