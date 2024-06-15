package Phone;

public class non_meeting extends Event {
	String description;
	public non_meeting(String name, String description,
						int eventLen, int year, int month, int date, int hour, int minute) {
		super(name, eventLen, year, month, date, hour, minute);
		// TODO Auto-generated constructor stub
		this.description = description;
	}
	
	@Override
	public void print() {
		super.print();
		System.out.println("  	Description: " + description);
	}

}
