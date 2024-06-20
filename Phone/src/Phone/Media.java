//test
package Phone;

import java.util.Scanner;

enum MediaType {
	  MUSIC,
	  VIDEO
	}

public class Media {
	String name;
	MediaType type;
	String length;
	
	Media(String name, MediaType type, String length) {
		this.name = name;
		this.type = type;
		this.length = length;
	}
	
	
	Media(){
		Scanner s = new Scanner(System.in);
		
		///type
		Boolean doneflag = false;
		do {
			System.out.println("enter media type: \n	1 - video\n	2 - music");
			switch (s.nextInt()) {
			case 1:
				this.type = MediaType.VIDEO;
				doneflag = true;
				break;
			case 2:
				this.type = MediaType.VIDEO;
				doneflag = true;
				break;
			default:
				System.out.println("couldnt proccess input.");
				break;
			}
		}while(!doneflag);
		
		//name
		System.out.println("enter media name:");
		s.nextLine();
		this.name = s.nextLine();
		//length
		String[] length_test;
		while(true){
			System.out.println("enter media length: <mm:ss>");
			this.length = s.nextLine();
			length_test = length.split(":");
			if(length_test.length == 2 && length_test[0].length() <= 2 && length_test[1].length() <= 2) {
				System.out.println("new "+ this.type+ " file saved: " + this.name+ " - "+ this.length);
				break;
			}
			else {
				System.out.println("media length doesnt match format: <mm:ss>");
			}
		}
	}
	
	Boolean isVideo() { return type == MediaType.VIDEO;}

	void Play() {
		System.out.println(this.type+ this.name + " is now playing for " + this.length + " minutes.");
	}
	
}

