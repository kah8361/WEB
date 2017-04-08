import java.util.regex.*; 
import java.io.*;

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
					filename = path.substring((path.lastIndexOf('/') + 1), path.length());
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
		}
		
		
		//deletes all whitespaces after and infront of the text
		filename.trim();
		
		
	}

	public static String getAuthor(){
		return "Hallo von getAuthor";
	}

	public static String getTitle(){
		return "Hallo von getTitle";
	}
	
}





	