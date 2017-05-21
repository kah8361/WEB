package studiplayer.ui;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

import studiplayer.audio.PlayList;

@SuppressWarnings("serial")
public class Player extends JFrame implements ActionListener{
	
	public String songDescriptionS;
	public String playTimeS;
	public String windowTitle;
	
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
		JLabel songDescription = new JLabel(songDescriptionS);
		JLabel playTime = new JLabel(playTimeS);
		JPanel panel = new JPanel();
		JButton bplay = new JButton(new ImageIcon("icons/play.png"));
		JButton bpause = new JButton(new ImageIcon("icons/pause.png"));
		JButton bstop = new JButton(new ImageIcon("icons/stop.png"));
		JButton bnextTitel = new JButton(new ImageIcon("icons/next.png"));
		JButton bplaylistEditor = new JButton(new ImageIcon("icons/pl_editor.png"));
		
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
