package studiplayer.audio;

import java.util.Comparator;

public class DurationComparator implements Comparator<AudioFile> {

	public int compare(AudioFile af1, AudioFile af2) {

		try {
			if (!(af1 instanceof SampledFile) && !(af2 instanceof SampledFile)) {
				return 0;
			} else if (!(af2 instanceof SampledFile)) {
				return 1;
			} else if (!(af1 instanceof SampledFile)) {
				return -1;
			} else {
				SampledFile sf1 = (SampledFile) af1;
				SampledFile sf2 = (SampledFile) af2;

				if (sf1.getDuration() == sf2.getDuration()) {
					return 0;
				} else if (sf1.getDuration() < sf2.getDuration()) {
					return -1;
				} else {
					return 1;
				}
			}

		} catch (NullPointerException e) {
			throw new NullPointerException();
		}
	}

}
