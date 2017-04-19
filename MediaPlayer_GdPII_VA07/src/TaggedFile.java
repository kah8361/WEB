import studiplayer.basic.BasicPlayer;


public class TaggedFile extends AudioFile {
	
	public TaggedFile(){
		super();
	}
	
	public TaggedFile(String path){
		super(path);
	}
	
	public void play(){
		BasicPlayer.play(getPathname());
	}
	
	public void togglePause(){
		BasicPlayer.togglePause();
	}
	
	public void stop(){
		BasicPlayer.stop();
	}
	
	public String getFormattedDuration(){
		return "";
	}
	
	public String getFormattedPosition(){
		return "";
	}

}
