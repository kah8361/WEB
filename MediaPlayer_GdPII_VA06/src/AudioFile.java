import java.util.regex.*;
import java.lang.String;

public class AudioFile {

	private String pathname;
	private String filename;
	private String title;
	private String author;

	// default ctor
	public AudioFile() {

	}

	// better ctor
	public AudioFile(String path) {

		parsePathname(path);
		String filename = getFilename();
		parseFilename(filename);

	}

	// Method checks pathname and saves important stuff in class attributes
	public void parsePathname(String path) {
		if(!path.isEmpty()) {
			
			// more than one '/': something with regex following ...
			path = path.replaceAll("\\/+", "/");
			
	
			// don't forget backslashes \\\
			path = path.replaceAll("\\\\+", "/");
		
	
			// and hard-drive letters D:, C: 
			path = regexHardDrive(path);
	
	
			pathname = path;
	
			if (path.charAt(path.length() - 1) == '/') {
				filename = "";
			} else {
				filename = path.substring((path.lastIndexOf('/') + 1), path.length());
			}
		} else {
			pathname = "";
			filename = "";
		}
	}

	// returns path in normal form
	public String getPathname() {
		return pathname;
	}

	// return filename without path, or empty string
	public String getFilename() {
		return filename;
	}

	
	public static String regexHardDrive(String origPathname){
		
		Pattern replace = Pattern.compile("^([A-Za-z]):");
		Matcher regexMatcher = replace.matcher(origPathname);
		String regexHardDrive = origPathname;
		if(regexMatcher.find()){
			regexHardDrive = regexMatcher.replaceAll("/" + regexMatcher.group(1));
		}
		
		return regexHardDrive;
		
	}

	public void parseFilename(String filename) {
		
		if(!filename.isEmpty() && !filename.equals(" ")) {
			
			if (filename == "-") {
				author = "";
				title = filename;
			} else if (regexNAandNT(filename)) {
				author = "";
				title = "";
			} else if (regexNA(filename)) {
				author = "";
				title = filename.substring(0, filename.lastIndexOf('.'));
			} else {
				String[] afterSplit = filename.split(" - ");
				if(afterSplit.length > 1) {
					author = afterSplit[0].trim();
					title = afterSplit[1].trim();
					if(!title.isEmpty()) {
						title = title.substring(0, title.lastIndexOf('.')).trim();
					}
				}
			}
		} else {
			author = "";
			title = "";
		}
	}

	public String getAuthor() {

		return author;
	}

	public String getTitle() {

		return title;
	}

	public static boolean regexNA(String origFilename) {

		Pattern replace = Pattern.compile("(\\w+|\\s+|\\w-\\w+)?\\.+\\w+"); // Here we put the regex pattern for audiofile.mp3
		Matcher regexMatcher = replace.matcher(origFilename);
		return regexMatcher.matches();

	}

	public static boolean regexNAandNT(String origFilename) {

		Pattern replace = Pattern.compile("\\s+\\-+\\s"); // Here we put the regex pattern for >> - <<
		Matcher regexMatcher = replace.matcher(origFilename);
		return regexMatcher.matches();
	}

	public String toString() {

		String author = getAuthor();
		if (author.isEmpty()) {
			return title;
		} else {
			return author + " - " + getTitle();
		}
	}

}
