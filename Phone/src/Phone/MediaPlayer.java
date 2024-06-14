package Phone;

import java.util.ArrayList;

public class MediaPlayer extends App{
	ArrayList<Media> mediaArray = new ArrayList<Media>();
	MediaPlayer(){
		this.appId = "1";
		this.appName = "Media Player";
		
		super.addMenuOption("add new media");
		super.addMenuOption("play media by name");
		super.addMenuOption("play all");
		super.addMenuOption("Exit");
	}
	
	@Override
	public int waitForInputAndRun() {
		//get the user decision in super
		int decision = super.waitForInputAndRun();
		switch(decision){
		case 0:
			AddMedia();
			break;
		case 1:
			if(mediaArray.isEmpty()) {
				System.out.println("No media files yet. try to add a new media file!");
				break;
			}
			System.out.println("enter media name:");
			s.nextLine();
			PlayByName(s.nextLine());
			break;
		case 2:
			if(mediaArray.isEmpty()) {
				System.out.println("No media files yet. try to add a new media file!");
				break;
			}
			for(Media media : mediaArray) {
				media.Play();
			}
		}
		return decision;
	}
	
	void AddMedia() {
		mediaArray.add(new Media());
	}
	
	void PlayByName(String name) {
		if(mediaArray.isEmpty()) {
			System.out.println("No media files yet. try to add a new media file!");
			return;
			
		}
		for (Media media : mediaArray) {
			if(media.name.equals(name)) {
				media.Play();
				return;
			}
		}
		System.out.println("couldnt find media with that name.");
	}
}
