import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.regex.Pattern;


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
	
	public PlayList(String pathname){
		super();
		loadFromM3U(pathname);
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
		
		Scanner scanner = null;
		
		try{
			scanner = new Scanner(new File(pathname));

			while(scanner.hasNextLine()){
				
				//clear PlayList 
				this.clear();
				
				//ignore empty lines and comments
				scanner.skip(Pattern.compile("\\^#.*\n$"));       //Line looks like this: #comment starts with "#" and ends with newline
				scanner.skip(Pattern.compile("\\s+"));

				scanner.nextLine();
				
				//add a Tagged or WavFile to PlayList
				this.add(AudioFileFactory.getInstance(pathname));
				
			}
		}catch(IOException e){
			throw new RuntimeException(e);
		}finally{
			try{
				scanner.close();
			}catch(Exception e){
				throw new RuntimeException(e);
			}
				
		}
	
	}
}
