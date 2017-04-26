import studiplayer.basic.TagReader;
import java.util.Map;


public class TaggedFile extends SampledFile {
	
	protected String album;
	
	
	public TaggedFile(){
		super();
	}
	
	public TaggedFile(String path){
		super(path);
		String pathname = getPathname();
		readAndStoreTags(pathname);
	}
	
	
	public String getAlbum(){
		return album;
	}

	public String toString(){
		
		if(album != null && !album.isEmpty()){
			return super.toString() + " - " + album + " - " + getFormattedDuration();
		}else{
			return super.toString() + " - " + getFormattedDuration();
		}
	}
	
	public String[] fields(){
		String[] field = {getAuthor(), getTitle(), album, getFormattedDuration()};
		return field;
	}
	
	public void readAndStoreTags(String pathname){
		
		 Map <String,Object> tagMap = TagReader.readTags(pathname);
		 for(String key : tagMap.keySet()){
			 if(tagMap.get(key) != null  && tagMap.get(key) != ""){
				 if(key == "author"){
					 author = tagMap.get(key).toString().trim();
					 continue;
				 }
				 else if(key=="title"){
					 title = tagMap.get(key).toString().trim();
					 continue;
				 }
				 else if(key=="album"){
					 album = tagMap.get(key).toString().trim();
					 continue;
				 }
				 else if(key=="duration"){
					 duration = (long)tagMap.get(key);
					 continue;
				 }
			 }
		 }
		
	}
}
