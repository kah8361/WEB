import studiplayer.basic.BasicPlayer;
import studiplayer.basic.TagReader;
import java.util.Map;


public class TaggedFile extends SampledFile {
	
	protected String album;
	
	
	public TaggedFile(){
		super();
	}
	
	public TaggedFile(String path){
		super(path);
		String pathname = super.getPathname();
		readAndStoreTags(pathname);
	}
	
	
	public String getAlbum(){
		return album;
	}

	public String toString(){
		
		if(album != ""){
			return super.toString() + " - " + album + " - " + getFormattedDuration();
		}else{
			return super.toString() + " - " + getFormattedDuration();
		}
	}
	
	public void readAndStoreTags(String pathname){
		
		 Map <String,Object> tagMap = studiplayer.basic.TagReader.readTags(pathname);
		 for(String key : tagMap.keySet()){
			 if(tagMap.get(key) != null  && tagMap.get(key) != ""){
				 if(tagMap.get(key)=="author"){
					 AudioFile.author = (String)tagMap.get(key);
					 continue;
				 }
				 else if(tagMap.get(key)=="title"){
					 AudioFile.title = (String)tagMap.get(key);
					 continue;
				 }
				 else if(tagMap.get(key)=="album"){
					 album = (String)tagMap.get(key);
					 continue;
				 }
				 else if(tagMap.get(key)=="duration"){
					 duration = (long)tagMap.get(key);
					 continue;
				 }
			 }
		 }
		
	}
}
