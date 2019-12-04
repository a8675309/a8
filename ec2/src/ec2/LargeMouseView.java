package ec2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;

public class LargeMouseView extends JPanel implements MouseListener{
	
	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	//private int height = screenSize.height;
	//private int width = screenSize.width;
	private int gridLength = 500;
	/*
	 * Do cellLength as double :P
	 */
	private int cellLength;
	//private double cellLength;
	private int cellCount;
	private List<MouseClickEvent> events;
	public LargeMouseView(int cellCount) {
		this.cellCount = cellCount;
		cellLength = 500/cellCount;
		
		addMouseListener(this);
		//add(new JButton("What up"), BorderLayout.EAST);
		events = new ArrayList<>();
	}
	public void paintComponent(Graphics g) {
		boolean [][] boolArray = new boolean[cellCount][cellCount];
		for (int j = 0; j < cellCount; j++) {
			for (int i = 0; i < cellCount; i++) {
				g.setColor(Color.LIGHT_GRAY);
				if (boolArray[i][j]) {
					g.setColor(Color.BLACK);
				}
				g.fillRect(i * (int)cellLength, j * (int)cellLength,  (int)cellLength, (int)cellLength);
				
			}
		}
		
		
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		//System.out.println("X is:  " + e.getX() +  " and Y is: " + e.getY());
		if (e.getX() > cellCount * cellLength || e.getY() > cellCount * cellLength)
			return;
		Graphics g = this.getGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(e.getX() - e.getX() % (int)cellLength, e.getY() - e.getY() % (int)cellLength, (int)cellLength, (int)cellLength);
		
		for (MouseClickEvent m : events) {
			m.mouseClicked(e.getX() - e.getX() % (int)cellLength, e.getY() - e.getY() % (int)cellLength, e);
		}
		
	}
	public void addMouseClickEvent(MouseClickEvent event) {
		events.add(event);
	}
	public void repaint(boolean[][] changedArray) {
		Graphics g = this.getGraphics();
		cellCount = changedArray.length;
		cellLength = 500/cellCount;
		for (int j = 0; j < cellCount; j++) {
			for (int i = 0; i < cellCount; i++) {
				g.setColor(Color.WHITE);
				if (changedArray[i][j]) {
					g.setColor(Color.BLACK);
				}
				g.fillRect(i * (int)cellLength, j * (int)cellLength,  (int)cellLength, (int)cellLength);
				
			}
		}
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		//System.out.println("X is:  " + e.getX() +  " and Y is: " + e.getY());
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
