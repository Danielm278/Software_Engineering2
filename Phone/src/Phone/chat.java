package Phone;
import java.util.ArrayList;

public class chat {
	ArrayList<String> message_list = new ArrayList<String>();
	String contactName;
	public chat(String contactName,String firstMessage) {
		//contein 2 elements name of chat and array of messages
		this.contactName = contactName;
		message_list.add(firstMessage);
	}
	public void findSentence(String find) {
		//for all messages in the chat and compere to a specific string
		for (int i = 0; i < message_list.size(); i++) {
			if(message_list.get(i).equals(find)) {
				System.out.println(this.contactName);
				return;
			}
			
			
		}
	}
}
