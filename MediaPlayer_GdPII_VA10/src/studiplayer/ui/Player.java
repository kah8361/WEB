package studiplayer.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

import studiplayer.audio.AudioFile;
import studiplayer.audio.NotPlayableException;
import studiplayer.audio.PlayList;

@SuppressWarnings("serial")
public class Player extends JFrame implements ActionListener {

	private JLabel songDescription;
	private JLabel playTime;
	private String windowTitle;
	public String songDescriptionS;
	public String playTimeS;
	private String actionCMD;
	private PlayListEditor playListEditor;

	private JButton bplay = new JButton(new ImageIcon("icons/play.png"));
	private JButton bpause = new JButton(new ImageIcon("icons/pause.png"));
	private JButton bstop = new JButton(new ImageIcon("icons/stop.png"));

	private volatile boolean stopped;
	private boolean editorVisible;

	public static String DEFAULT_PLAYLIST = "playlists/DefaultPlayList.m3u";

	public Player(PlayList playlist) {
		// Initialize the main frame
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}

		JPanel panel = new JPanel();

		bplay.setActionCommand("AC_PLAY");
		bplay.addActionListener(this);

		bpause.setActionCommand("AC_PAUSE");
		bpause.addActionListener(this);

		bstop.setActionCommand("AC_STOP");
		bstop.addActionListener(this);

		JButton bnextTitel = new JButton(new ImageIcon("icons/next.png"));
		bnextTitel.setActionCommand("AC_NEXT");
		bnextTitel.addActionListener(this);

		JButton bplaylistEditor = new JButton(new ImageIcon("icons/pl_editor.png"));
		bplaylistEditor.setActionCommand("AC_PLEDITOR");
		bplaylistEditor.addActionListener(this);

		panel.add(bplay);
		panel.add(bpause);
		panel.add(bnextTitel);
		panel.add(bstop);
		panel.add(bplaylistEditor);

		
		PlayList playList = new PlayList();
		playListEditor = new PlayListEditor(this, playList);
		editorVisible = false;

		// Acitvate GUI
		this.pack();
		this.setVisible(true);

		// Enable buttons
		bplay.setEnabled(true);
		bpause.setEnabled(false);
		bstop.setEnabled(false);
		bnextTitel.setEnabled(true);
		bplaylistEditor.setEnabled(true);

		if (playlist.isEmpty()) {
			windowTitle = "Studiplayer: empty playlist";
			songDescriptionS = "no current Title";
			playTimeS = "--:--";
		} else {
			windowTitle = "Current song: " + playlist.getCurrentAudioFile().toString();
			songDescriptionS = playlist.getCurrentAudioFile().toString();
			playTimeS = "00:00";
		}
		
		songDescription = new JLabel(songDescriptionS);
		playTime = new JLabel(playTimeS);
		
		this.add(songDescription, BorderLayout.NORTH);
		this.add(playTime, BorderLayout.WEST);
		this.add(panel, BorderLayout.CENTER);

		setTitle(windowTitle);
		
	}

	public void actionPerformed(ActionEvent ae) {
		AudioFile af;
		PlayList playList = new PlayList();
		String cmd = ae.getActionCommand();

		if (cmd.equals("AC_PLAY")) {
			bplay.setEnabled(false);
			bpause.setEnabled(true);
			bstop.setEnabled(true);
			playCurrentSong();

		} else if (cmd.equals("AC_PAUSE")) {
			actionCMD = "AC_PAUSE";
			bpause.setEnabled(true);
			bstop.setEnabled(true);
			bplay.setEnabled(false);
			af = playList.getCurrentAudioFile();
			System.out.println("Pausing " + af.toString());
			System.out.println("Filename is " + af.getFilename());
			System.out.println("Current index is " + playList.getCurrent());

			if (playList.isEmpty()) {
				windowTitle = "no current song";
				songDescriptionS = "no current song";
				playTimeS = "--:--";
			} else {
				songDescriptionS = af.getTitle();
				windowTitle = af.getTitle();
				playTimeS = af.getFormattedPosition();
			}
			if (getLastAC() == "AC_PAUSE") {
				try {
					af.play();
				} catch (NotPlayableException e) {
					System.out.println("NotPlayableException after clicking PauseButton again" + e.toString());
				}
			} else {
				if(!playList.isEmpty()){
					af.togglePause();
				}
			}

		} else if (cmd.equals("AC_STOP")) {
			bplay.setEnabled(true);
			bstop.setEnabled(false);
			bpause.setEnabled(false);
			stopCurrentSong();

		} else if (cmd.equals("AC_NEXT")) {
			actionCMD = "AC_NEXT";
			System.out.println("Switching to next audio file ");
			bplay.setEnabled(false);
			bpause.setEnabled(true);
			bstop.setEnabled(true);
			if (!stopped) {
				stopCurrentSong();
			}

			playList.changeCurrent();
			playCurrentSong();

			af = playList.getCurrentAudioFile();

			if (af != null) {
				System.out.println("Switched to next audio file ");
				updateSongInfo(af);
			} else {
				System.out.println("PlayList is empty");
			}

		} else if (cmd.equals("AC_PLEDITOR")) {
			if(editorVisible){
				editorVisible = false;
			}else {
				editorVisible = true;
			}
			playListEditor.setVisible(editorVisible);
		}
	}

	public String getLastAC() {
		return actionCMD;
	}

	private void updateSongInfo(AudioFile af) {

		PlayList playList = new PlayList();
		if (af == null || playList.isEmpty()) {
			windowTitle = "no current song";
			songDescriptionS = "no current song";
			playTimeS = "--:--";
		} else {
			songDescriptionS = af.getTitle();
			windowTitle = af.getTitle();
			playTimeS = "00:00";
		}
	}

	private void playCurrentSong() { 

		PlayList playList = new PlayList();
		AudioFile af;

		actionCMD = "AC_PLAY";
		stopped = false;
		af = playList.getCurrentAudioFile();
		System.out.println("Playing " + af.toString());
		System.out.println("Filename is " + af.getFilename());
		System.out.println("Current index is " + playList.getCurrent());

		updateSongInfo(af);
		
		if(!playList.isEmpty()){
			if (af != null) {
				// Start threads
				(new TimerThread()).start();
				(new PlayerThread()).start();
				}
		}
		try {
			af.play();
		} catch (NotPlayableException e) {
			System.out.println("NotPlayableException after clicking PlayButton" + e.toString());
		}
	}

	private void stopCurrentSong() {

		PlayList playList = new PlayList();
		AudioFile af;

		actionCMD = "AC_STOP";
		af = playList.getCurrentAudioFile();
		System.out.println("Stoping " + af.toString());
		System.out.println("Filename is " + af.getFilename());
		System.out.println("Current index is " + playList.getCurrent());

		if(!playList.isEmpty()){
			af.stop();
			stopped = true;
		}
		
		updateSongInfo(af);

	}

	public static void main(String[] args) {

		PlayList playlist = new PlayList();

		if (args.length == 2) {
			playlist.loadFromM3U(args[1]);
		} else {
			playlist.loadFromM3U(DEFAULT_PLAYLIST);
		}

		new Player(playlist);
	}

	private class TimerThread extends Thread {

		public void run() {

			PlayList playList = new PlayList();
			AudioFile af;
			af = playList.getCurrentAudioFile();
			while (stopped == false && !playList.isEmpty()) {
				playTime.setText(af.getFormattedPosition());
				try {
					sleep(100);
				} catch (InterruptedException e) {
					System.out.println("Interrupted Exception in method sleep: " + e);
				}
			}
		}
	}

		private class PlayerThread extends Thread {

			public void run() {

				PlayList playList = new PlayList();
				AudioFile af;
				af = playList.getCurrentAudioFile();
				while (stopped == false && !playList.isEmpty()) {
					try{
						af.play();
					}catch(NotPlayableException e){
						System.out.println("Not PlayableException: " + e + getStackTrace());
					}
				}
				if(stopped == false){
					playList.changeCurrent();
					updateSongInfo(af);
				}
			}
		}

	}

