package studiplayer.audio;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AudioFileFactory {
	
	public AudioFileFactory(){
		
	}

	public static AudioFile getInstance(String pathname) throws NotPlayableException{

			Matcher mp3 = Pattern.compile("(?i).mp3$").matcher(pathname);
			Matcher ogg = Pattern.compile("(?i).ogg$").matcher(pathname);
			Matcher wav = Pattern.compile("(?i).wav$").matcher(pathname);
			
			if(mp3.find() || ogg.find()){
				return new TaggedFile(pathname);
			}else if(wav.find()){
				return new WavFile(pathname);
			}else{
			throw new NotPlayableException(pathname, "Unknow suffix for AudioFile: \""+ pathname + "\"");
		}
	}	
}
