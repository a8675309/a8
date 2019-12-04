package ec2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeListener;

//import lec19.v09.CalculatorViewListener;

@SuppressWarnings("serial")
public class GameOfLifeView extends JPanel{
	
	
	private JLabel display;
	//private JPanel subPanel;
	private JPanel board;
	private JPanel buttonPanel;
	private JPanel sliderPanel;
	//private List<GameOfLifeViewListeners> listeners;
	
	public GameOfLifeView() {
		
		setLayout(new BorderLayout());
		JPanel subPanel = new JPanel();
		 Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int height = screenSize.height/3;
		int width = screenSize.width/3;
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(5, 1));
		buttonPanel.add(new JButton("Next"));
		buttonPanel.add(new JButton("Clear"));
		buttonPanel.add(new JButton("Random Fill"));
		buttonPanel.add(new JButton("Torus Mode OFF"));
		buttonPanel.add(new JButton("Start"));
		
		for (Component c : buttonPanel.getComponents()) {
			JButton j = (JButton)c;
			c.setPreferredSize(new Dimension(500, 15));
		}
		sliderPanel = new JPanel();
		sliderPanel.setLayout(new GridLayout(6,1));
		
		JSlider sizeSlider = new JSlider(10,500);
		sizeSlider.setName("SizeSlider");
		sizeSlider.setValue(10);
		
		JSlider lowBirthThresholdSlider = new JSlider(0,8);
		lowBirthThresholdSlider.setName("lowBirthThresholdSlider");
		lowBirthThresholdSlider.setValue(3);
		//lowBirthThresholdSlider.setSize(new Dimension(125, 10));
		
		JSlider highBirthThresholdSlider = new JSlider(0, 8);
		highBirthThresholdSlider.setName("highBirthThresholdSlider");
		highBirthThresholdSlider.setValue(3);
		//highBirthThresholdSlider.setSize(new Dimension(125, 10));
		
		JSlider lowSurvivalThresholdSlider = new JSlider(0, 8);
		lowSurvivalThresholdSlider.setName("lowSurvivalThresholdSlider");
		lowSurvivalThresholdSlider.setValue(2);
		//lowSurvivalThresholdSlider.setSize(new Dimension(125, 10));
		
		JSlider highSurvivalThresholdSlider = new JSlider(0, 8);
		highSurvivalThresholdSlider.setName("highBirthThresholdSlider");
		highSurvivalThresholdSlider.setValue(3);
		//highSurvivalThresholdSlider.setSize(new Dimension(125, 10));
		
		JSlider timerSlider = new JSlider(10, 1000);
		timerSlider.setName("timerSlider");
		timerSlider.setValue(250);
		
		//sliderPanel.setPreferredSize(new Dimension(500, 200));
		
		sliderPanel.add(sizeSlider);
		sliderPanel.add(lowBirthThresholdSlider);
		sliderPanel.add(highBirthThresholdSlider);
		sliderPanel.add(lowSurvivalThresholdSlider);
		sliderPanel.add(highSurvivalThresholdSlider);
		sliderPanel.add(timerSlider);
		
		
		
		JPanel labelPanel = new JPanel();
		labelPanel.setLayout(new GridLayout(6,1));
		labelPanel.add(new JLabel("Size Slider (10 to 500)"));
		labelPanel.add(new JLabel("Low Birth Threshold (0 to 8):"));
		labelPanel.add(new JLabel("High Birth Threshold (0 to 8): "));
		labelPanel.add(new JLabel("Low Survival Threshold (0 to 8): "));
		labelPanel.add(new JLabel("High Survival Threshold (0 to 8): "));
		labelPanel.add(new JLabel("Timer (10 to 1000 ms)"));
		
		
		
		JPanel groupPanel = new JPanel();
		groupPanel.setLayout(new GridLayout(1,2));
		groupPanel.add(labelPanel);
		groupPanel.add(sliderPanel);
		//subPanel.add(buttonPanel, BorderLayout.NORTH);
		//GridView gridView = new GridView(75);
		//GridView gridView = new GridView(25);
		//JPanel myView = new LargeMouseView(50);
		
		
		board = new LargeMouseView(10);
		add(board, BorderLayout.CENTER);
		
		add(buttonPanel, BorderLayout.SOUTH);
		add(groupPanel, BorderLayout.NORTH);
		//add(labelPanel, BorderLayout.);
		//subPanel.add(buttonPanel, BorderLayout.EAST);
		
		//add(subPanel);
		
		//gridView.changeColor(5, 5);
		
		
		
	
		
	}
	public JButton getStartButton() {
		for (Component c : buttonPanel.getComponents()) {
			JButton j = (JButton)c;
			if (j.getName().charAt(0) == 'S')
				return j;
		}
		return null;
	}
	public LargeMouseView getLargeMouseView() {
		return (LargeMouseView)board;
	}
	public void addActionListener(ActionListener l) {
		for (Component c : buttonPanel.getComponents()) {
			try {
				JButton j = (JButton)c;
				j.addActionListener(l);
			}
			catch (Exception e){
				
			}
		}
		
	}
	

	
	public void addChangeListener(ChangeListener l) {
		for (Component c : sliderPanel.getComponents()) {
			try {
				JSlider s = (JSlider)c;
				s.addChangeListener(l);
			}
			catch(Exception e) {
				
			}
		}
	}
	//@Override
	
}
