package Phone;

import java.util.Scanner;

public abstract class App implements IPrintable{
	Scanner s = new Scanner(System.in);
	String appId;
	String appName;
	String[] menuOptions;
	boolean endFlag = false;
	
	
	public void print() {
		System.out.println("Welcome to " + appName + " (AppId "+appId+")");
		System.out.println("Please Select which action you wish to take:");
		for (int i = 0; i < menuOptions.length; i++){
			System.out.println(i+") " + menuOptions[i]);
		}
		
	}
	
	public void waitForInputAndRun() {
		int decision = -1;
		do {
		decision = s.nextInt();
		}while(decision > menuOptions.length || decision < 0);
		
		if (decision == menuOptions.length -1){
			System.out.println("Thank you for using "+ appName);
			System.out.println("Bye Bye");
			endFlag = true;
		}
		
		//The rest will be managed inside the individual classes
	}
	
	public void startApp() {
		while(!endFlag) {
			print();
			waitForInputAndRun();
		}	
	}
	
	//Using this, major actions for each class include:
		//1. switch statement for decision
		//2. deciding menu options
		//3. declaring/initializing app name
		//4. logic specific to app inside the switch statements
	
}
