import studiplayer.basic.WavParamReader;

public class WavFile extends SampledFile{

	public WavFile(){
		super();
	}
	
	public WavFile(String path){
		super(path);
		String pathname = getPathname();
		readAndSetDurationFromFile(pathname);
	}
	
	public static long computeDuration(long numberOfFrames, float frameRate){
		
		long timeMS = (long)(numberOfFrames * frameRate);
		return timeMS;
	}

	public void readAndSetDurationFromFile(String pathname){
		
		WavParamReader.readParams(pathname);
		frameRate = WavParamReader.getFrameRate();
		numberOfFrames = WavParamReader.getNumberOfFrames();
		
		duration = computeDuration(numberOfFrames, frameRate);
		
	}
	
	public String toString(){
		
		return toString() + " - " + getFormattedDuration();
		
	}
	
	public String[] fields(){
		String[] field = {getAuthor(), getTitle(), "", getFormattedDuration()};
		return field;
	}
}
