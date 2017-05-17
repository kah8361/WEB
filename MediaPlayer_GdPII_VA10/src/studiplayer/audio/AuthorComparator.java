package studiplayer.audio;
import java.util.Comparator;

public class AuthorComparator implements Comparator<AudioFile>{
	
	public int compare(AudioFile af1, AudioFile af2){
		
		try{
			if(af1.getAuthor() != null && af2.getAuthor() !=null){
				int comp = af1.getAuthor().compareTo(af2.getAuthor());
				return comp;
			}else{
				if((af1.getAuthor() == null && af2.getAuthor() == null) || af1.getAuthor().equals(af2.getAuthor())){
					return 0;
				}else if(af1.getAuthor() == null){
					return -1;
				}else{
					return 1;
				}
			}
		
		}catch(NullPointerException e){
			throw new NullPointerException("Object of type AudioFile is a Null-Pointer");
		}
	}
}
