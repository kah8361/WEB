package studiplayer.audio;
import java.util.Comparator;

public class TitleComparator implements Comparator<AudioFile>{

	public int compare(AudioFile af1, AudioFile af2){
		
		try{
			if(af1.getTitle() != null && af2.getTitle() !=null){
				int comp = af1.getTitle().compareTo(af2.getTitle());
				return comp;
			}else{
				if((af1.getTitle() == null && af2.getTitle() == null) || af1.getTitle().equals(af2.getTitle())){
					return 0;
				}else if(af1.getTitle() == null){
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
