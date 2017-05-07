import java.util.regex.*;
import java.util.regex.Pattern;

public class AudioFileFactory {
	
	public AudioFileFactory(){
		
	}

	public static AudioFile getInstance(String pathname){
		
		try{
			Matcher mp3 = Pattern.compile("\\(?i).mp3$").matcher(pathname);
			Matcher ogg = Pattern.compile("\\(?i).ogg$").matcher(pathname);
			Matcher wav = Pattern.compile("\\(?i).wav$").matcher(pathname);
			
			if(mp3.matches() || ogg.matches()){
				return new TaggedFile(pathname);
			}else if(wav.matches()){
				return new WavFile(pathname);
			}
		}catch (Exception e){
			throw new RuntimeException("Unknow suffix for AudioFile: \""+ pathname + "\"");
		}
	}
	
}
