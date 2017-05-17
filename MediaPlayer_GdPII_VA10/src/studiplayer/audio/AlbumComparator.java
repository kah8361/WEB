package studiplayer.audio;
import java.util.Comparator;

public class AlbumComparator implements Comparator<AudioFile> {
	
	public int compare(AudioFile af1, AudioFile af2){
	
		try{
		if(!(af1 instanceof TaggedFile) && !(af2 instanceof TaggedFile)){
			return 0;
		}else if(!(af2 instanceof TaggedFile)){
			return 1;
		}else if(!(af1 instanceof TaggedFile)){
			return -1;
		}else{
			TaggedFile tf1 = (TaggedFile) af1;
			TaggedFile tf2 = (TaggedFile) af2;
			
			if((tf1.getAlbum() == null && tf2.getAlbum() == null) || tf1.getAlbum().equals(tf2.getAlbum())){
				return 0;
			}else if(tf1.getAlbum() == null){
				return -1;
			}else{
				return 1;
			}
		}
		
		}catch(NullPointerException e){
			throw new NullPointerException();
		}
	}
	
	

}
