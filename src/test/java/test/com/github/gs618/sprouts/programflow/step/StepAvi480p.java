package test.com.github.gs618.sprouts.programflow.step;

import com.github.gs618.sprouts.programflow.Step;
import com.github.gs618.sprouts.programflow.Input;
import com.github.gs618.sprouts.programflow.Output;

import java.util.concurrent.TimeUnit;

public class StepAvi480p extends Step {

	@Override
	public void handle(Input input, Output output) {
		long fileSize = input.getData("FILE_SIZE");
		long sleepTime;
		if(fileSize<1000){
			sleepTime = 1000L;
		} else if(fileSize < 5000) {
			sleepTime = 2000L;
		} else {
			sleepTime = 3000L;
		}
		System.out.println("Avi to 480p uses " + sleepTime + " ms");
		try {
			TimeUnit.MILLISECONDS.sleep(sleepTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
