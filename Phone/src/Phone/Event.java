package Phone;

import java.util.Date;

public abstract class Event {
	Date date = new Date();
	int eventLength;
	String name;
	
	public Event(String name, int eventLen,int year, int month, int date, int hour, int minute) {
		this.name = name;
		this.eventLength = eventLen;
		SetDate(year,month, date, hour, minute);
	}
	
	@SuppressWarnings("deprecation")
	public void SetDate(int year, int month, int day, int hour, int minute) {
        date = new Date(year - 1900, month - 1, day, hour, minute);
		this.date.setSeconds(0);
		

	}
	
	public boolean removeDate() {
		Date currentDate = new Date();
		
		if(currentDate.after(date)) {
			return true;
		}
		return false;
	}
}
