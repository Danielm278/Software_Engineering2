package Phone;

public class non_meeting extends Event {
	String description;
	public non_meeting(String name, String description,
						int eventLen, int month, int date, int hour, int minute) {
		super(name, eventLen, month, date, hour, minute);
		// TODO Auto-generated constructor stub
		this.description = description;
	}

}
