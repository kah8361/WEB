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

		// more than one '/': something with regex following ...

		regexMultiSlashes(path);

		// don't forget backslashes \\\

		regexBackslashes(path);

		// and hard-drive letters D:, C: ...

		if (path.charAt(1) == ':') {

			path.replace(path.charAt(1), '/');
			String slash = "/";
			path = slash + path;

			int i;
			for (i = 0; i < path.length(); i++) {

				if (path.charAt(i) == '/') {

					pathname = path;

					if (path.charAt(path.length()) == '/') {
						filename = "";
					} else {
						filename = path.substring((path.lastIndexOf('/') + 1), path.length());
					}

				} else if (path.charAt(i) != '/') {
					pathname = path;
					filename = path;
				}
			}
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

	public static void regexMultiSlashes(String origPathname) {

		Pattern replace = Pattern.compile("\\/+"); // Here we put the regex pattern for one or more slashes / // ///
		Matcher regexMatcher = replace.matcher(origPathname);

		String regexSlashPathname = regexMatcher.replaceAll("/");

	}

	public static void regexBackslashes(String origPathname) {

		Pattern replace = Pattern.compile("\\+"); // Here we put the regex pattern for backslashes \\\
		Matcher regexMatcher = replace.matcher(origPathname);

		String regexBackslashPathname = regexMatcher.replaceAll("/");

	}

	public void parseFilename(String filename) {

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
			author = afterSplit[0].trim();
			title = afterSplit[1];
			title = title.substring(0, title.lastIndexOf('.')).trim();
		}

	}

	public String getAuthor() {

		return author;
	}

	public String getTitle() {

		return title;
	}

	public static boolean regexNA(String origFilename) {

		Pattern replace = Pattern.compile("(\\w+|\\s+|\\w-\\w+)+\\.+\\w+"); // Here we put the regex pattern for audiofile.mp3
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
