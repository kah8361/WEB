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
import studiplayer.audio.PlayList;
import studiplayer.audio.SampledFile;

@SuppressWarnings("serial")
public class Player extends JFrame implements ActionListener{
	
	private JLabel songDescription;
	private JLabel playTime;
	private String windowTitle;
	public String songDescriptionS;
	public String playTimeS;
	
	public static String DEFAULT_PLAYLIST = "playlists/DefaultPlayList.m3u";
	
	public Player(PlayList playlist){
		//Initialize the main frame
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		try{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}catch(Exception e){
			e.printStackTrace();
		}
		
		//Create GUI components
		songDescription = new JLabel(songDescriptionS);
		playTime = new JLabel(playTimeS);
		JPanel panel = new JPanel();
		
		JButton bplay = new JButton(new ImageIcon("icons/play.png"));
		bplay.setActionCommand("AC_PLAY");
		bplay.addActionListener(this);
		
		JButton bpause = new JButton(new ImageIcon("icons/pause.png"));
		bpause.setActionCommand("AC_PAUSE");
		bpause.addActionListener(this);
		
		JButton bstop = new JButton(new ImageIcon("icons/stop.png"));
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
	
		
		add(songDescription, BorderLayout.NORTH);
		add(playTime, BorderLayout.WEST);
		add(panel, BorderLayout.CENTER);

		setTitle(windowTitle);
		
		//Acitvate GUI
		this.pack();
		this.setVisible(true);
		
		if(playlist.isEmpty()){
			windowTitle = "Studiplayer: empty playlist";
			songDescriptionS = "no current Title";
			playTimeS = "--:--";
		}else{
			windowTitle = "Current song: " + playlist.getCurrentAudioFile().toString();
			songDescriptionS = playlist.getCurrentAudioFile().toString();
			playTimeS = "00:00";
		}
	}
	
	public void actionPerformed(ActionEvent ae){
		AudioFile af;
		PlayList playList = new PlayList();
		String cmd = ae.getActionCommand();
		
		if(cmd.equals("AC_PLAY")){
			af = playList.getCurrentAudioFile();
			System.out.println("Playing " + af.toString());
			System.out.println("Filename is " + af.getFilename());
			System.out.println("Current index is " + playList.getCurrent());
			
			songDescriptionS = af.getTitle();
			windowTitle = af.getTitle();
			playTimeS = "00:00";
			af.play();
			
		}else if(cmd.equals("AC_PAUSE")){
			af = playList.getCurrentAudioFile();
			System.out.println("Pausing " + af.toString());
			System.out.println("Filename is " + af.getFilename());
			System.out.println("Current index is " + playList.getCurrent());
			
			//TODO
			if(playList.g){
				af.togglePause();
			}else{
				af.play();
			}
			
		}else if(cmd.equals("AC_STOP")){
			af = playList.getCurrentAudioFile();
			System.out.println("Stoping " + af.toString());
			System.out.println("Filename is " + af.getFilename());
			System.out.println("Current index is " + playList.getCurrent());
		}else if(cmd.equals("AC_NEXT")){
			playList.changeCurrent();
			af = playList.getCurrentAudioFile();
			System.out.println("Playing " + af.toString());
			System.out.println("Filename is " + af.getFilename());
			System.out.println("Current index is " + playList.getCurrent());
		}else if(cmd.equals("AC_PLEDITOR")){
			//TODO
		}
	}
	
	public static void main(String[] args){
		
		PlayList playlist = new PlayList();
		
		if(args.length == 2){
			playlist.loadFromM3U(args[1]);
		}else{
			playlist.loadFromM3U(DEFAULT_PLAYLIST);
		}
		
		new Player(playlist);
	}
	
}
