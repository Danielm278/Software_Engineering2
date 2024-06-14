package Phone;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		System.out.println("Welcome to phone");
		Scanner s = new Scanner(System.in);
		App[] appArray = {new MediaPlayer(), new Calendar()};
		System.out.println(appArray.length);
		int decision = -1;
		do {
		decision = s.nextInt();
		}while(decision > appArray.length || decision < 0);
		
		if (decision == appArray.length + 1){
			System.out.println("Thank you for using phone app");
			System.out.println("Bye Bye");
			return;
		}
		String decisionString = String.valueOf(decision);
		System.out.println(decisionString);
		for(App app : appArray) {
			System.out.println("trying app: "+ app.appName+ ", app id: " + app.appId);
			System.out.println(decisionString.equals(app.appId));
			if(decisionString.equals(app.appId)){
				app.startApp();
			}
		}
	}

}
