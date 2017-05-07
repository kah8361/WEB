import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class UTestAudioFileFactory {

	@Test
	public void test_getInstance_01() throws Exception {
		
		try{
			AudioFileFactory.getInstance("unknown.xxx");
			fail("Unknown suffix; expecting exeption");
		}catch (RuntimeException e){
			throw new RuntimeException("Unknown suffix:" + e.getMessage());
		}
	}
	
	@Test
	public void test_getInstance_02() throws Exception{
		try {
			AudioFileFactory.getInstance("nonexistent.mp3");
			fail("File is not readable; expecting exception");
		}catch (RuntimeException e){
			throw new RuntimeException("No readable file:" + e.getMessage());
		}
	}
	
	@Test
	public void test_getInstance_03() throws Exception{
		AudioFile af1 = AudioFileFactory.getInstance("audiofiles/Eisbach Deep Snow.ogg");
		assertTrue("Expecting object of type TaggedFile", (af1 instanceof TaggedFile));
		
		AudioFile af2 = AudioFileFactory.getInstance("audiofiles/wellenmeister - tranquility.wav");
		assertTrue("Expecting object of type WavFile", (af2 instanceof WavFile));
		
		AudioFile af3 = AudioFileFactory.getInstance("audiofiles/special.oGg");
		assertTrue("Expecting object of type TaggedFile", (af3 instanceof TaggedFile));
		
	}
}
