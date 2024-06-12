package Phone;

import java.util.Date;

public abstract class Event {
	Date date = new Date();
	int eventLength;
	String name;
	
	public Event(String name, int eventLen, int month, int date, int hour, int minute) {
		this.name = name;
		this.eventLength = eventLen;
		SetDate(month, date, hour, minute);
	}
	
	@SuppressWarnings("deprecation")
	public void SetDate(int month, int date, int hour, int minute) {
		this.date.setMonth(month);
		this.date.setDate(date);
		this.date.setHours(hour);
		this.date.setMinutes(minute);
	}
	
	public boolean removeDate() {
		Date currentDate = new Date();
		
		if(currentDate.after(date)) {
			return true;
		}
		return false;
	}
}
