//Class: Main.Java
//Group Id: 12
//Authors: Ido Lublin, Binyamin Alony, Dana Bakshe, Roni Weiss, Yael Tsabari, Daniel Maya

package Phone;

import java.util.List;
import java.util.Scanner;
public class  ContactsApp{
	EntryNode rootNode;

	public ContactsApp() {
	}

	public EntryNode startApp() {
		Scanner s = new Scanner(System.in);
		int decision;

		while (true) {
			// Display menu
			System.out.println("\n\nWelcome to the phonebook!");
			System.out.println("Please decide what action you wish to perform:");
			System.out.println("1: New Entry");
			System.out.println("2: Print Phone numbers");
			System.out.println("3: Remove Entry");
			System.out.println("4: Search Entry");
			System.out.println("5: Sort Entries by Name");
			System.out.println("6: Sort Entries by Phone Number");
			System.out.println("7: Remove duplicates");
			System.out.println("8: Reversed order");
			System.out.println("9: Save to file");
			System.out.println("10: Import from file");
			System.out.println("11: Exit");

			// Error Message! need to enter a number 
			try{
				decision = Integer.parseInt(s.nextLine())-1;
			}
			catch(Exception e){
				System.out.println("please enter a number");
				continue;
			}
			System.out.print("You have selected ");

			if (rootNode == null) {
				switch (decision) {
				case 0: //New Entry
					System.out.println("<New Entry>");

					try {

						System.out.print("Please insert name of new entry: ");
						String new_name = s.nextLine();
						String number;
						while(true) {
							System.out.print("Please enter Phone Number: ");
							number = s.nextLine();

							try {
								Integer.parseInt(number);
								break;
							}
							catch(Exception e) {
								System.out.println("Please insert a valid phone number");
							}
						}
						System.out.println();
						rootNode = new EntryNode(new_name, number);

					}
					catch(Exception e) {
						System.out.println("***********************************************************************");
						System.out.println("There appears to have been an issue adding your Entry, please try again");
						System.out.println("***********************************************************************");
					}
					break;
				case 1: //Print Phone numbers
					System.out.println("<Print Phone numbers>");
					System.out.println();
					System.out.println("<No numbers in system>");
					System.out.println();
					break;
				case 2: //Remove Entry
					System.out.println("<Remove Entry>");
					System.out.println();
					System.out.println("<No numbers in system>");
					System.out.println();
					break;
				case 3: // Search Entry. No entries, so no need to search
					System.out.println("<Search Entry>");
					System.out.println();
					System.out.println("<No numbers in system>");
					System.out.println();
					break;
				case 4: // Sort Entries by Name. No entries to sort
					System.out.println("<Sort Entries by Names>");
					System.out.println();
					System.out.println("<No numbers in system>");
					System.out.println();
					break;
				case 5: // Sort Entries by Phone Number. No entries to sort
					System.out.println("<Sort Entries by Phone Number>");
					System.out.println();
					System.out.println("<No numbers in system>");
					System.out.println();
					break;
				case 6: // No entries, so no need to remove duplicates
					System.out.println("<Remove duplicates>");
					System.out.println();
					System.out.println("<No numbers in system>");
				case 7://Reversed order , no order to reverse 
					System.out.println("<Reversed order>");
					rootNode = EntryNode.ReversedOrderContacts(rootNode);//calling the removing function
					System.out.println();
					System.out.println("<Entries sorted by reversed order>");
					break;
				case 8: // Save to file. no file was saved
					System.out.println("<Save to text file>\nthe phonebook is empty, no file was saved.");
					break;
				case 9: // Import from file
					System.out.println("<read from file>");
					System.out.print("Please insert file path: ");
					String path = s.nextLine();
					//List<String> nodesToAdd = FileManager.readFromFile(path);
					try {
						//for(String line : nodesToAdd) {
						//	String[] splitString = line.split(":");
						//	if (rootNode == null) {
						//		rootNode = new EntryNode(splitString[0], splitString[1]);
						//	}else {
						//		rootNode = rootNode.addEntry(splitString[0], splitString[1]);
						//	}
						//	System.out.print("edded - "+splitString[0]+":"+ splitString[1]+"\n");
						//}
						System.out.print("phonebook imported successfully.");
					}
					catch(Error e){

						System.out.println("****************");
						System.out.print("Error reading file");
						System.out.println("****************");
					}
					break;
				case 10: //Exit
					System.out.println("Thank you for using the contact app\n");
					return null;
				}

				System.out.println();
				continue;
			}

			switch (decision) {
			case 0: //New Entry
				System.out.println("<New Entry>");
				try {

					EntryNode temp=rootNode;
					while(temp==rootNode)
					{
						System.out.print("Please insert name of new entry: ");
						String new_name = s.nextLine();
						String number;
						while(true) {
							System.out.print("Please enter Phone Number: ");
							number = s.nextLine();

							try {
								Integer.parseInt(number);
								break;
							}
							catch(Exception e) {
								System.out.println("Please insert a valid phone number");
							}
						}
						System.out.println();
						try {
							rootNode = rootNode.addEntry(new_name, number);
						}
						catch(Exception e) {
							System.out.println("We have run into some temporary issues when adding the Entry, please try again");
							temp=null;
						}
					}

				}
				catch(Exception e) {

					System.out.println("**************************************");
					System.out.println("Something went wrong, please try again");
					System.out.println("**************************************");
				}
				break;

			case 1: //Print Phone numbers
				System.out.println("<Print Phone numbers>");
				try {
					rootNode.printList();
				}
				catch(Exception e) {
					System.out.println("temporary issue displaying the entries, please try again");
				}

				break;
			case 2: //Remove Entry
				System.out.println("<Remove Entry>");
				System.out.print("Please select the Entry you wish to remove: ");
				String rm_name = s.nextLine();

				try {
					rootNode.printList();
				}
				catch(Exception e) {
					System.out.println("**************************************************************");
					System.out.println("Temporary issue occurred when removing entry, please try again");
					System.out.println("**************************************************************");

				}

				rootNode = rootNode.rmEntry(rm_name);
				break;
			case 3: //Search Entry by name
				System.out.println("<Search Entry>");

				try {
					System.out.print("Please insert name of the entry to search: ");
					String search_name = s.nextLine();
					rootNode.searchEntryByName(search_name, 2);
				}
				catch(Exception e) {
					System.out.println("*******************************************************************");
					System.out.println("Temporary issue occurred when searching for entry, please try again");
					System.out.println("*******************************************************************");

				}
				break;
			case 4: //Sort Entries by Name
				System.out.println("<Sort Entries>");
				try {
					rootNode = EntryNode.sortByName_Coll(rootNode);
					System.out.println("<Entries sorted by name>");
				}
				catch(Exception e) {
					System.out.println("*********************");
					System.out.println("Error sorting entries");
					System.out.println("*********************");
				}
				break;
			case 5: //Sort Entries by Phone Number
				System.out.println("<Sort Entries>");
				try {
					rootNode = EntryNode.sortListByPhone(rootNode);
					System.out.println("<Entries sorted by Phone Number>");
				}
				catch(Exception e) {
					System.out.println("*********************");
					System.out.println("Error sorting entries");
					System.out.println("*********************");
				}
				break;
			case 6: //remove duplicates
				System.out.println("<Remove duplicates>");

				try {
					rootNode.removeDuplicateContacts();//calling the removing function
					System.out.println();
					System.out.println("Duplicates (if there were any) were removed");
				}
				catch(Exception e) {
					System.out.println("*********************");
					System.out.println("Error removing duplicate entries");
					System.out.println("*********************");
				}
				break;
			case 7: //reverse order
				System.out.println("<Reversed order>");
				try {
					rootNode = EntryNode.ReversedOrderContacts(rootNode);//calling the removing function
					System.out.println();
					System.out.println("<Entries sorted by reversed order>");
				}
				catch(Exception e) {
					System.out.println("*********************");
					System.out.println("Error sorting entries");
					System.out.println("*********************");
				}
				break;
			case 8: // Save to file
				System.out.println("<Save to text file>");
				//FileManager.printToFile(rootNode);//calling the removing function
				System.out.println();
				System.out.println("<file \"myPhoneBook.txt\" saved.>");
				break;
			case 9: // Import from file
				System.out.println("<read from file>");
				System.out.print("Please insert file path: ");
				String path = s.nextLine();
				//List<String> nodesToAdd = FileManager.readFromFile(path);
				try {
					//for(String line : nodesToAdd) {
					//	String[] splitString = line.split(":");
					//	rootNode = rootNode.addEntry(splitString[0], splitString[1]);
					//}
					System.out.print("phonebook imported successfully.");
				}
				catch(Error e){
					System.out.print("error reading file");
				}
				break;
			case 10://Exit
				System.out.println("Thank you for using the contact app\n");			
				return rootNode;
			}
		}
	}
}
