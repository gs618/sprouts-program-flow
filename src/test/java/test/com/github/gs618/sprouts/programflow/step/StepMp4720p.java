package test.com.github.gs618.sprouts.programflow.step;

import com.github.gs618.sprouts.programflow.Step;
import com.github.gs618.sprouts.programflow.Input;
import com.github.gs618.sprouts.programflow.Output;

import java.util.concurrent.TimeUnit;

public class StepMp4720p extends Step {

	@Override
	public void handle(Input input, Output output) {
		long fileSize = input.getData("FILE_SIZE");
		long sleepTime;
		if(fileSize<1000){
			sleepTime = 3000L;
		} else if(fileSize < 5000) {
			sleepTime = 4000L;
		} else {
			sleepTime = 5000L;
		}
		System.out.println("Mp4 to 720p uses " + sleepTime + " ms");
		try {
			TimeUnit.MILLISECONDS.sleep(sleepTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
