
public class AudioFile {
    
    private String pathname;
    private String filename;
    
    
    public AudioFile(){
        
    }
    
    //Method checks pathname and saves important stuff in class attributes
    public void parsePathname(String path){
       
        
        pathname = path;
        filename = path.substring((path.lastIndexOf('/') + 1), path.length());
        
        
    }
    
    //returns path in normal form
    public String getPathname(){
        return "";
    }
    
    //return filename without path, or empty string
    public String getFilename(){
        return "";
    }
    
    

}
