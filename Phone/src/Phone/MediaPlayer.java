package Phone;

import java.util.ArrayList;

public class MediaPlayer extends App{
	ArrayList<Media> mediaArray;
	MediaPlayer(){
		this.appId = "1";
		this.appName = "Media Player";
		
		super.addMenuOption("add new media");
		super.addMenuOption("play media by name");
		super.addMenuOption("play all");
		super.addMenuOption("Exit");
	}
	
	void AddMedia() {
		mediaArray.add(new Media());
	}
	
	void PlayByName(String name) {
		for (Media media : mediaArray) {
			if(media.name == name) {
				media.play();
			}
		}
		
	}
}
