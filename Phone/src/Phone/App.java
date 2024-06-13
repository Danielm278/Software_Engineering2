
//gi
package Phone;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public abstract class App implements IPrintable{
	Scanner s = new Scanner(System.in);
	String appId;
	String appName;
	Map<Integer, String> menuOptions = new HashMap<Integer, String>();
	boolean endFlag = false;
	
	
	public void print() {
		System.out.println("Welcome to " + appName + " (AppId "+appId+")");
		System.out.println("Please Select which action you wish to take:");
		for (int i = 0; i < menuOptions.size(); i++){
			System.out.println(i+") " + menuOptions.get(i));
		}
		
	}
	
	public int waitForInputAndRun() {
		int decision = -1;
		do {
		decision = s.nextInt();
		}while(decision > menuOptions.size() || decision < 0);
		
		if (decision == menuOptions.size() -1){
			System.out.println("Thank you for using "+ appName);
			System.out.println("Bye Bye");
			endFlag = true;
		}
		//The rest will be managed inside the individual classes
		return decision;
	}
	
	public void startApp() {
		while(!endFlag) {
			print();
			waitForInputAndRun();
		}	
	}
	
	public void addMenuOption(String[] options) {
		for(int i = 0; i < options.length; i++) {
			menuOptions.put(i, options[i]);
		}
	}
	public void addMenuOption(String option) {
		menuOptions.put(menuOptions.size(), option);
	}
	//Using this, major actions for each class include:
		//1. switch statement for decision
		//2. deciding menu options
		//3. declaring/initializing app name
		//4. logic specific to app inside the switch statements
	
}
