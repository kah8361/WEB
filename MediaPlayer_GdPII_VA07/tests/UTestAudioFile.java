import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class UTestAudioFile {

    @Test
    public void test_parsePathname_03() throws Exception {
        AudioFile af = new AudioFile();
        af.parsePathname("c:\\\\my/var\\\\\\test.mp3");
        char sepchar = java.io.File.separatorChar;
        assertEquals("Pathname stored incorrectly", "/c/my/var/test.mp3", af.getPathname());
        assertEquals("Returned filename is incorrect", "test.mp3", af.getFilename());
    }

    private boolean isWindows(){
        return System.getProperty("os.name").toLowerCase().indexOf("win") >= 0;
    }


}

