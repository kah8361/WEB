import java.util.*;
import java.util.Collections;
import java.io.FileWriter;
import java.io.IOException;
import java.text.*;


@SuppressWarnings("serial") 
public class PlayList extends LinkedList<AudioFile> {

	private int curPosition;
	private boolean randomOrder;
	
	public PlayList(){
		setRandomOrder(false);
	}
	
	public PlayList(boolean order){
		curPosition = getCurrent();
		setRandomOrder(order);
	}
	
	public void setCurrent(int position){
		curPosition = position;
	}
	
	public int getCurrent(){
		return this.curPosition;
	}
	
	public AudioFile getCurrentAudioFile(){
		
		if(this.isEmpty()){
			return null;
		}else if(curPosition >= size()){
			return null;
		}else{
			return this.get(curPosition);
		}
	}
	
	public void changeCurrent(){
		
		if(this.get(curPosition) == this.getLast() || getCurrentAudioFile() == null){
			curPosition = 0;
			if(randomOrder == true){
				Collections.shuffle(this);
			}
		}else{
			curPosition+= curPosition;			
		}
	}
	
	public void setRandomOrder(boolean order){
		
		if(order == true){
			randomOrder = true;
			Collections.shuffle(this);
		}else{
			randomOrder = false;
		}
		
	}
	
	public void saveAsM3U(String pathname){
		
		FileWriter writer = null;
		String linesep = System.getProperty("line.separator");
		
		try{
			writer = new FileWriter(pathname);
			 
			Date date = new Date( );
		      SimpleDateFormat sFormat = new SimpleDateFormat ("E yyyy.MM.dd 'at' hh:mm:ss a");
			
			writer.write("#Katharina Hädere" + linesep);
			writer.write("#Current Date: " + sFormat.format(date) + linesep); 
			writer.write(linesep);
			
			for(AudioFile af : this){
			writer.write(af.getPathname() + linesep);
			}
			
		}catch (IOException e){
			throw new RuntimeException("Unable to write to file " + pathname + ": " + e.getMessage());
		}finally{
			try{
				writer.close();
			}catch(Exception e){
				throw new RuntimeException("Unable to close writer:" + e.getMessage());
			}
		}
			
	}
	
	public void loadFromM3U(String pathname){
		
	}
	
}
