package studiplayer.ui;
import javax.swing.JFrame;
import javax.swing.UIManager;

@SuppressWarnings("serial")
public class Player extends JFrame {

	public Player(){
		//Initialize the main frame
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		try{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}catch(Exception e){
			e.printStackTrace();
		}
		
		//Create GUI components
		//Acitvate GUI
		this.pack();
		this.setVisible(true);
	}
	
	public static void main(String[] args){
		new Player();
	}
	
}
