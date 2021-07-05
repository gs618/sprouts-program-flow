package test.com.github.gs618.sprouts.programflow;

import com.github.gs618.sprouts.programflow.Input;
import com.github.gs618.sprouts.programflow.Output;
import org.junit.Test;
import test.com.github.gs618.sprouts.programflow.process.GenerateVideoProcess;
import test.com.github.gs618.sprouts.programflow.process.GenerateVideoProcessWithoutFirstStep;

/**
 * @author sgao
 */
public class AppTest {

	@Test
	public void exceptionProcessTest() {
		GenerateVideoProcessWithoutFirstStep generateVideoProcessWithoutFirstStep = new GenerateVideoProcessWithoutFirstStep();
		Input input = new Input();
		input.putData("FILE_SIZE", 2000L);
		input.putData("TYPE", "AVI");
		Output output = new Output();
		generateVideoProcessWithoutFirstStep.start(input, output);
		generateVideoProcessWithoutFirstStep.printStepTrace(output);
	}

	@Test
	public void badFileTypeExceptionProcessTest() {
		GenerateVideoProcess generateVideoProcess = new GenerateVideoProcess();
		Input input = new Input();
		input.putData("FILE_SIZE", 2000L);
		input.putData("TYPE", "MP3");
		Output output = new Output();
		generateVideoProcess.start(input, output);
		generateVideoProcess.printStepTrace(output);
	}

	@Test
	public void normalAviProcessTest() {
		GenerateVideoProcess generateVideoProcess = new GenerateVideoProcess();
		Input input = new Input();
		input.putData("FILE_SIZE", 50L);
		input.putData("TYPE", "AVI");
		Output output = new Output();
		generateVideoProcess.start(input, output);
		generateVideoProcess.printStepTrace(output);
	}

	@Test
	public void normalMp4ProcessTest() {
		GenerateVideoProcess generateVideoProcess = new GenerateVideoProcess();
		Input input = new Input();
		input.putData("FILE_SIZE", 2000L);
		input.putData("TYPE", "MP4");
		Output output = new Output();
		generateVideoProcess.start(input, output);
		generateVideoProcess.printStepTrace(output);
	}

	@Test
	public void normalMkvProcessTest() {
		GenerateVideoProcess generateVideoProcess = new GenerateVideoProcess();
		Input input = new Input();
		input.putData("FILE_SIZE", 9000L);
		input.putData("TYPE", "MKV");
		Output output = new Output();
		generateVideoProcess.start(input, output);
		generateVideoProcess.printStepTrace(output);
	}

}
