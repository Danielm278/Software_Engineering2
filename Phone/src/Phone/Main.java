package Phone;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		System.out.println("Welcome to phone");
		Scanner s = new Scanner(System.in);
		App[] appArray = {new MediaPlayer(), new Calendar()};
		int decision = -1;
		try {
			while(true) {
				do {
					for(App app : appArray) {
						if(app.appId != null)
						System.out.println(app.appId+ ") "+ app.appName);
					}
					System.out.println((appArray.length + 1)+ ") Quit");
				decision = s.nextInt();
				}while(decision > appArray.length + 1 || decision < 0);
				
				if (decision == appArray.length + 1){
					System.out.println("Thank you for using phone app");
					System.out.println("Bye Bye");
					return;
				}
				String decisionString = String.valueOf(decision);
				for(App app : appArray) {
					if(decisionString.equals(app.appId)){
						app.startApp();
						break;
					}
				}
			}
		}catch(Exception e) {
			System.out.println("error: " + e.toString());
			System.out.println("goodbye.");
		}
	}

}
