package studiplayer.audio;
import studiplayer.basic.WavParamReader;

public class WavFile extends SampledFile{

	public WavFile(){
		super();
	}
	
	public WavFile(String path) throws NotPlayableException{
		super(path);
		String pathname = getPathname();
		readAndSetDurationFromFile(pathname);
	}
	
	public static long computeDuration(long numberOfFrames, float frameRate){
		
		long timeMS = (long)((numberOfFrames / frameRate)*1000000);
		return timeMS;
	}

	public void readAndSetDurationFromFile(String pathname) throws NotPlayableException{
		
		try{
		WavParamReader.readParams(pathname);
		}catch(RuntimeException e){
			throw new NotPlayableException(pathname, "RuntimeException in WavParamReader.readParams", e);
		}
		
		frameRate = WavParamReader.getFrameRate();
		numberOfFrames = WavParamReader.getNumberOfFrames();
		
		duration = computeDuration(numberOfFrames, frameRate);
		
	}
	
	public String toString(){
		
		return super.toString() + " - " + getFormattedDuration();
		
	}
	
	public String[] fields(){
		String[] field = {getAuthor(), getTitle(), "", getFormattedDuration()};
		return field;
	}
}
