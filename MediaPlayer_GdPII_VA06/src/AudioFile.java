import java.util.regex.*; 
import java.io.*;
import java.lang.String;


public class AudioFile {

	private String pathname;
	private String filename;
	private static String title;
	private static String author;


	public AudioFile(){

	}

	//Method checks pathname and saves important stuff in class attributes
	public void parsePathname(String path){


		//more than one '/': something with regex following ...	

		regexMultiSlashes(path);

		//don't forget backslashes \\\

		regexBackslashes(path);


		//and hard-drive letters D:, C: ... 

		if(path.charAt(1) == ':'){

			path.replace(path.charAt(1), '/');
			String slash = "/";
			path = slash + path;
			
			
			int i;
			for(i=0; i<path.length(); i++){


				if(path.charAt(i) == '/'){

					pathname = path;
					
					if(path.charAt(path.length())== '/'){
					filename = "";	
					}else{
					filename = path.substring((path.lastIndexOf('/') + 1), path.length());
					}
					
				}else if(path.charAt(i) != '/'){
					pathname = path;
					filename = path;
				}
			}
		}
	}

	//returns path in normal form
	public String getPathname(){
		return pathname;
	}

	//return filename without path, or empty string
	public String getFilename(){
		return filename;
	}


	public static void regexMultiSlashes(String origPathname){

		Pattern replace = Pattern.compile("\\/+");  //Here we put the regex pattern for one or more slashes / // ///
		Matcher regexMatcher = replace.matcher(origPathname);

		String regexSlashPathname = regexMatcher.replaceAll("/");       

	}

	public static void regexBackslashes(String origPathname){

		Pattern replace = Pattern.compile("\\+");  //Here we put the regex pattern for backslashes \\\
		Matcher regexMatcher = replace.matcher(origPathname);

		String regexBackslashPathname = regexMatcher.replaceAll("/");       

	}

	public static void parseFilename(String filename){

		if(filename == "-"){
			author = "";
			title = filename;
		}else if(regexNAandNT(filename)){
			author = "";
			title = "";
		}
		else if(regexMultipleDashes(filename)){
			//the match = author; rest = title
		}
		else if(regexNA(filename)){
			author = "";
			title = filename.substring(0, filename.lastIndexOf('.')-1);
		}
		
		else{
		//deletes all whitespaces after and infront of the text
		filename.trim();
		int i;
		for(i=1; i<filename.length(); i++){
			if(filename.charAt(i) == '-'){
				author = filename.substring(0, i-1);
				author.trim();
				title = filename.substring(i+1, filename.lastIndexOf('.')-1);   //Example: Author - Title.mp3
				title.trim();
			}
			
		}
		if(title.endsWith(".")){
			title = title.substring(0, title.length()-1);
		}
		}
	}

	public static String getAuthor(){
		return author;
	}

	public static String getTitle(){
		return title;
	}
	
	
	public static void regexNA(String origFilename){

		Pattern replace = Pattern.compile("\\[A-Za-z0-9]\\.+\\[a-z0-9]{2,4}");  //Here we put the regex pattern for audiofile.mp3
		Matcher regexMatcher = replace.matcher(origFilename);      

	}
	
	public static void regexNAandNT(String origFilename){

		Pattern replace = Pattern.compile("\\s+\\-+\\s");  //Here we put the regex pattern for >>    -     <<
		Matcher regexMatcher = replace.matcher(origFilename);      

	}
	
	public static void regexMultipleDashes(String origFilename){

		Pattern replace = Pattern.compile("\\[A-Za-z0-9]+\\[-]+\\[A-Za-z0-9]+\\s\\-\\s");  //Here we put the regex pattern for Linkin-Park - Don't stay
		Matcher regexMatcher = replace.matcher(origFilename);      

	}
	
}






	