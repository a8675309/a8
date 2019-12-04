package ec2;



public class Timer extends Thread{
	
	private boolean done;
	private GameOfLifeModel _model;
	public Timer(GameOfLifeModel model) {
		_model = model;
		done = false;
	}
	
	public void halt() {
		done = true;
	}
	
	public void run() {
		_model.toggleIsRunning();
		while (!done) {
			try {
				_model.advance();
				Thread.sleep(_model.getTime());
				
			} catch (InterruptedException e) {
			}



		}
		


		
		
	}

}

