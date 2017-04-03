import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class UTestAudioFile {

    @Test
    public void test_parsePathname_03() throws Exception {
        AudioFile af = new AudioFile();
        af.parsePathname("/my-tmp/file.mp3");
        char sepchar = java.io.File.separatorChar;
        assertEquals("Pathname stored incorrectly", sepchar + "my-tmp" + sepchar + "file.mp3", af.getPathname());
        assertEquals("Returned filename is incorrect", "file.mp3", af.getFilename());
    }

    private boolean isWindows(){
        return System.getProperty("os.name").toLowerCase().indexOf("win") >= 0;
    }


}

