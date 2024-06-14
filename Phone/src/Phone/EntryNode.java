//Class: EntryNode.Java
//Group Id: 12
//Authors: Ido Lublin, Binyamin Alony, Dana Bakshe, Roni Weiss, Yael Tsabari, Daniel Maya
package Phone;

import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Collections;

public class EntryNode {
	//default constructor
	public EntryNode() {}
	
	//basic data types
	String name, number;
	EntryNode next = null;
	EntryNode prev = null;

	//constructor to allow for definition of data
	public EntryNode(String name,String number) {
		this.name = name;
		this.number = number;
	}
	
	//Add entry to top of list (will always be displayed in reverse s.t. earliest entry is first)
	public EntryNode addEntry(String newName, String newNumber) {
		if (this.searchEntryByName(newName, 1))
		{
			System.out.println("ERROR: You enter a name that already exist in our Phone-book , please try again");
			return this;
		}
		else
		{
			this.prev = new EntryNode(newName, newNumber);
			this.prev.next = this;
			return this.prev;
		}
		
	}
	
	
	//print the list of entries
	public int printList() {
		int next_int = 1;

		//iterate to the end of the list before printing
		if(next != null) {
			next_int = next.printList();
		} else {
			//define index for lowest level node (1st)
			next_int = 1;
		}

		//print the entries in reverse order and increment the index for the next entry
		System.out.println(next_int + "." + " Name: " + this.name);
		System.out.println(" Phone number: " + this.number);
		System.out.println("");
		return next_int + 1;
	}

	// Function to implement node removal
	public EntryNode rmEntry(String name) {
		//save initial values
		EntryNode root = this;
		EntryNode current = this;
		
		//iterate through the link list to attempt to find a match to the wanted entry
		while(current != null) {
			
			//if entry found then remove it from the list dependent on its relative location
			if(current.name.equals(name)) {
				if (current.prev != null && current.next != null) {
					current.prev.next = current.next;
					current.next.prev = current.prev;
				}
				else if (prev != null) {
					current.prev.next = null;
				}
				else if (next != null) {
					current.next.prev = null;
					return next;
				}
				else {
					return null;
				}
				return root;
			}
			
			//iterate to the next value
			current = current.next;
		}
		
		//if no such entry found inform user
		System.out.println();
		System.out.println("<Entry Not Found>");
		System.out.println();
		return root;
	}

	// New function to search for an entry by name
	public boolean searchEntryByName(String name, int option) {
		boolean found = false;
		EntryNode current = this;
		int index = 1;

		while (current != null) {
			// Go through all the nodes 
			if (current.name.equals(name)) {
				// If we found the name we were looking for, stop and print
				found = true;
				if(option!=1)
				{
					System.out.println(index + "." + " Name: " + current.name);
					System.out.println(" Phone number: " + current.number);
					System.out.println("");
				}
			}
			// Keep searching until the end in case there is someone else with that name
			current = current.next;
			index++;
		}
		// if this entry isn't found
		if (!found && option!=1) {
			System.out.println();
			System.out.println("<Entry Not Found>");
			System.out.println();
		}
		return found;
	}

	/*
	// New function to sort by name from smallest to largest
	public static EntryNode sortListByNames(EntryNode head) {
		// Checks if the phonebook is empty
		if (head == null) return null;

		// Initialize variables
		ArrayList<EntryNode> entries = new ArrayList<>();
		EntryNode current = head;

		// Collect nodes into the ArrayList
		while (current != null) {
			entries.add(current);
			current = current.next;
		}

		// Sort the ArrayList by names in ascending order
		entries.sort(new Comparator<EntryNode>() {
			@Override
		    public int compare(EntryNode o1, EntryNode o2) {
		        return ((String) o2.name).compareTo((String) o1.name);
		    }
		});

		// Reconnect nodes in the sorted order
		for (int i = 0; i < entries.size(); i++) {
			EntryNode node = entries.get(i);
			if(i + 1 < entries.size())
				node.next=entries.get(i + 1);
			else
				node.next= null;
			if(i - 1 >= 0)
				node.prev = entries.get(i - 1);
			else
				node.prev=null	;
		}

		// Return the new head
		return entries.get(0);
	}
	*/
	
	// New function to sort by phone from largest to smallest  
	public static EntryNode sortListByPhone(EntryNode head) {
		// Checks if the phonebook is empty
		if (head == null) return null;

		// Initialize variables
		ArrayList<EntryNode> entries = new ArrayList<>();
		EntryNode current = head;

		// Collect nodes into the ArrayList
		while (current != null) {
			entries.add(current);
			current = current.next;
		}

		// Sort the ArrayList by phone numbers in descending order
		entries.sort(new Comparator<EntryNode>() {
			@Override
		    public int compare(EntryNode o1, EntryNode o2) {
		        return ((String) o2.number).compareTo((String) o1.number);
		    }
		});

		// Reconnect nodes in the sorted order
		for (int i = 0; i < entries.size(); i++) {
			EntryNode node = entries.get(i);
			if(i + 1 < entries.size())
				node.next=entries.get(i + 1);
			else
				node.next= null;
			if(i - 1 >= 0)
				node.prev = entries.get(i - 1);
			else
				node.prev=null	;
		}

		// Return the new head
		return entries.get(0);
	}
	
	
	// New function to remove duplicates of contacts based on both name and phone number
    public void removeDuplicateContacts() {
        HashSet<String> seenContacts = new HashSet<>();
        EntryNode current = this;
        EntryNode prevNode = null;

        while (current != null) {
            String contactKey = current.name + ":" + current.number;
            if (seenContacts.contains(contactKey)) {
                // Duplicate found, remove it
                prevNode.next = current.next;
                if (current.next != null) {
                    current.next.prev = prevNode;
                }
            } else {
                // First time seeing this contact, add to seen set
                seenContacts.add(contactKey);
                prevNode = current;
            }
            current = current.next;
        }
    }

	// New function to arrange the phone book in reverse order
    public static EntryNode ReversedOrderContacts(EntryNode head) {
		// Checks if the phonebook is empty
		if (head == null) return null;

		// Initialize variables
		ArrayList<EntryNode> entries = new ArrayList<>();
		EntryNode current = head;
		EntryNode temp = head;
		if (current != null) {
			current = current.next;
		}

		// Find the last node in the list
		while (current != null) {
			temp=current;
			current = current.next;
		}

		// Add nodes to the list in reverse order
		while (temp != null) {
			entries.add(temp);
			temp = temp.prev;
		}
		// Reconnect nodes in reverse order
		for (int i = 0; i < entries.size(); i++) {
            EntryNode node = entries.get(i);
            if (i + 1 < entries.size()) {
                node.next = entries.get(i + 1);
            } else {
                node.next = null;
            }
            if (i - 1 >= 0) {
                node.prev = entries.get(i - 1);
            } else {
                node.prev = null;
            }
        }
		//Return the new head
		 return entries.get(0);
	}
  
    public static EntryNode sortByName_Coll(EntryNode head) {
        // Check if the phone book is empty
        if (head == null) return null;

        try {
            // Initialize variables
            List<EntryNode> entries = new ArrayList<EntryNode>();
            EntryNode current = head;

            // Collect nodes into the ArrayList
            while (current != null) {
                entries.add(current);
                current = current.next;
            }

            // Sort the ArrayList by names using Collections.sort
            Collections.sort(entries,Comparator.comparing(entry -> entry.name));

            // Reconnect nodes 
            for (int i = 0; i < entries.size(); i++) {
                EntryNode node = entries.get(i);
                if (i + 1 < entries.size())
                    node.next = entries.get(i + 1);
                else
                    node.next = null;
                if (i - 1 >= 0)
                    node.prev = entries.get(i - 1);
                else
                    node.prev = null;
            }
            
            //reversing so it will be from the small to the big 
            EntryNode new_head= ReversedOrderContacts(entries.get(0));

            // Return the new head
            return new_head;
        } catch (Exception e) {
            System.out.println("Error while sorting entries by name: " + e.getMessage());
            return head; // Return the original list in case of error
        }
    }
    
    
	
}
