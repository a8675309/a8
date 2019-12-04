package ec2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class GameOfLifeController implements GameOfLifeObserver, ActionListener, ChangeListener, MouseClickEvent {
	
	private GameOfLifeModel _model;
	private GameOfLifeView _view;
	Timer t;
	public GameOfLifeController(GameOfLifeModel model, GameOfLifeView view) {
		_model = model;
		_view = view;
		//gets the clicks of the buttons
		_view.addActionListener(this);
		//observes the model's state
		_model.addObserver(this);
		//gets clicks of slider?
		_view.addChangeListener(this);
		_view.getLargeMouseView().addMouseClickEvent(this);
		t = new Timer(_model);
		
	}

	@Override
	public void update(GameOfLifeModel model) {
		
		_view.getLargeMouseView().repaint(model.getArray());
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		//System.out.println("Clear");
		JButton button = (JButton) e.getSource();

		char button_char = button.getText().charAt(0);
		
		switch(button_char) {
		case 'N':
			_model.advance();
			break;
		case 'C':
			_model.setIsRunning(false);
			_model.setArray(new boolean[_model.getArray().length][_model.getArray().length]);
			
			t.halt();
			_model.reset();

			break;
		case 'R':
			boolean[][] randomArray = new boolean[_model.getArray().length][_model.getArray().length];
			for (int i = 0; i < randomArray.length; i++) {
				for (int j = 0; j < randomArray.length; j++) {
					randomArray[i][j] = Math.random() > .5;
				}
			}
			t.halt();
			_model.setArray(randomArray);
			break;
		case 'T':
			_model.toggleTorusMode();
			if (_model.getIsTorusModeOn())
				button.setText("Torus Mode ON");
			else
				button.setText("Torus Mode OFF");
			break;
		case 'S':
			if (!_model.getIsRunning()) {
				
					button.setText("Stop");
				t = new Timer(_model);
				
				t.start();
				System.out.println("It has started");
			}
			else {
				_model.toggleIsRunning();
				button.setText("Start");
				t.halt();
			}
				 
			break;
			
		}
		
			
		
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		// TODO Auto-generated method stub
		//System.out.println("Slider change!");
		JSlider slider = (JSlider)e.getSource();
		//int maxSliderNum = slider.getMaximum();
		String name = slider.getName();
		if (name.equals("SizeSlider") && !slider.getValueIsAdjusting()) {
			//System.out.println(slider.getValue());
			_model.reset();
			_model.setArray(new boolean[slider.getValue()][slider.getValue()]);
			
		}
		else if (name.equals("lowBirthThresholdSlider")) {
			if (slider.getValue() > _model.getHighBirthThreshold())
			{
				slider.setValue(_model.getLowBirthThreshold());
			}
			else
				_model.setLowBirthThreshold(slider.getValue());
				
		}
		else if (name.equals("highBirthThresholdSlider")) {
			if (slider.getValue() < _model.getLowBirthThreshold()) {
				
				slider.setValue(_model.getHighBirthThreshold());
				
			}
			else
				_model.setHighBirthThreshold(slider.getValue());
		}
			
		else if (name.equals("lowSurvivalThresholdSlider")) {
			if (slider.getValue() >= _model.getHighSurvivalThreshold()) {
				slider.setValue(_model.getLowSurvivalThreshold());
			}
			else
				_model.setLowSurvivalThreshold(slider.getValue());
		}
		
		else if (name.equals("highSurvivalThresholdSlider")) {
			if (slider.getValue() <= _model.getLowSurvivalThreshold()) {
				slider.setValue(_model.getHighSurvivalThreshold());
			}
			else
				_model.setHighSurvivalThreshold(slider.getValue());
		}
		else if (name.equals("timerSlider")) {
			_model.setTime(slider.getValue());
		}
			
		
	}

	@Override
	public void mouseClicked(int x, int y, MouseEvent e) {
		// TODO Auto-generated method stub
		_model.changeSpace(x,y);
		
	}

	
}
