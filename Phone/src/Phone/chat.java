package Phone;
import java.util.ArrayList;

public class chat {
	ArrayList<String> message_list = new ArrayList<String>();
	String contactName;
	public chat(String contactName,String firstMessage) {
		this.contactName = contactName;
		message_list.add(firstMessage);
	}
	public void findSentence(String find) {
		for (int i = 0; i < message_list.size(); i++) {
			if(message_list.get(i).equals(find)) {
				System.out.println(this.contactName);
				return;
			}
			
			
		}
	}
}
