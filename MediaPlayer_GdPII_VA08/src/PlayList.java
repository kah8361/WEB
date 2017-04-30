import java.util.*;
import java.util.Collections;

@SuppressWarnings("serial") 
public class PlayList extends LinkedList<AudioFile> {

	private int curPosition;
	private boolean randomOrder;
	PlayList myPlayList = new PlayList(randomOrder);
	
	public PlayList(boolean order){
		curPosition = getCurrent();
		setRandomOrder(order);
	}
	
	public void setCurrent(int position){
		curPosition = position;
	}
	
	public int getCurrent(){
		return curPosition;
	}
	
	public AudioFile getCurrentAudioFile(){
		
		if(isEmpty()){
			return null;
		}else if(curPosition > size()){
			return null;
		}else{
			return get(curPosition);
		}
	}
	
	public void changeCurrent(){
		
		if(get(curPosition) == getLast() || getCurrentAudioFile() == null){
			curPosition = 0;
			if(randomOrder == true){
				Collections.shuffle(myPlayList);
			}
		}else{
			curPosition+= curPosition;			
		}
	}
	
	public void setRandomOrder(boolean order){
		
		if(order == true){
			randomOrder = true;
			Collections.shuffle(myPlayList);
		}else{
			randomOrder = false;
		}
		
	}
	
}
