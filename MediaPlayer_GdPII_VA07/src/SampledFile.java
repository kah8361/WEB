import studiplayer.basic.BasicPlayer;

public abstract class SampledFile extends AudioFile {

	protected long duration;
	protected long numberOfFrames;
	protected float frameRate;
	
	public SampledFile(){
		super();
	}
	
	public SampledFile(String path){
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
		return timeFormatter(duration);
	}
	
	public String getFormattedPosition(){
		long curPos = studiplayer.basic.BasicPlayer.getPosition();
		return timeFormatter(curPos);
	}
	
	//returns time in format mm:ss
		public static String timeFormatter(long microtime){
			
			if(microtime<0){
				//time is < 0
				throw new RuntimeException("Negative time value provided.");
			}else{
			//time in seconds
			microtime = microtime/(1000000);
			
				//possible time 59:59
				if(microtime > 5999){
					throw new RuntimeException("Time value exceeds allowed format.");
				}else{
					
					//time in minutes
					int min = (int)(microtime/60);
					
					//minutes from comma * 60 are the seconds example: 126,6 Minuten --> 0,6 * 60 = 36 sec
					int sec = (int)(microtime%60);
					
					if(min < 10){
						if(sec < 10){
							return "0" + min + ":" + "0" + sec;
						}else{
							return "0" + min + ":" + sec;
						}
					}else if(sec < 10){
						return min + ":" + "0" + sec;
					}else{
						return min + ":" + sec;
					}
				}
			}
		}
}
