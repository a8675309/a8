package ec2;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;



public class Main {
	
	public static void main(String args[]) {
		
		GameOfLifeModel model = new GameOfLifeModel();
		GameOfLifeView view = new GameOfLifeView();
		GameOfLifeController controller = new GameOfLifeController(model, view);
		JFrame main_frame = new JFrame();
		main_frame.setTitle("Conway's Game of Life");
		main_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel main_panel = new JPanel();
		main_frame.setContentPane(main_panel);
		
		main_panel.setLayout(new BorderLayout());

		//GridView gridView = new GridView(10);		
		
		//main_panel.add(new LargeMouseView(500), BorderLayout.CENTER);
		//main_panel.add(new LargeMouseView(5), BorderLayout.CENTER);
		main_panel.add(view);
		
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int height = screenSize.height; //* 2 / 3;
		int width = screenSize.width; //* 2 / 3;

		// set the jframe height and width
		//main_frame.setPreferredSize(new Dimension(width, height));
		
		main_frame.pack();
		main_frame.setSize(new Dimension(500, 710));
		main_frame.setResizable(false);
		main_frame.setVisible(true);
	}
	
	//main_frame.setResizable(false);
	
	
	//gridView.changeColor(5, 5);
	//cellView.changeColor();
}
