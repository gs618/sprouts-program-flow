package test.com.github.gs618.sprouts.programflow;

import com.github.gs618.sprouts.programflow.Input;
import com.github.gs618.sprouts.programflow.Output;
import org.junit.Test;
import test.com.github.gs618.sprouts.programflow.process.ChoiceProcess;
import test.com.github.gs618.sprouts.programflow.process.ExceptionProcess;

public class AppTest {

	@Test
	public void choiceProcessLessThan0Test() {
		ChoiceProcess choiceProcess = new ChoiceProcess();
		Input input = new Input();
		input.putData("DATA", -10);
		Output output = new Output();
		choiceProcess.run(input, output);
		choiceProcess.printStepTrace(output);
	}

	@Test
	public void choiceProcessMoreThan100Test() {
		ChoiceProcess choiceProcess = new ChoiceProcess();
		Input input = new Input();
		input.putData("DATA", 108);
		Output output = new Output();
		choiceProcess.run(input, output);
		choiceProcess.printStepTrace(output);
	}

	@Test
	public void choiceProcessMoreThan0LessThan100Test() {
		ChoiceProcess choiceProcess = new ChoiceProcess();
		Input input = new Input();
		input.putData("DATA", 76);
		Output output = new Output();
		choiceProcess.run(input, output);
		choiceProcess.printStepTrace(output);
	}

	@Test
	public void exceptionProcessTest() {
		ExceptionProcess exceptionProcess = new ExceptionProcess();
		Input input = new Input();
		input.putData("DATA", 99);
		Output output = new Output();
		exceptionProcess.run(input, output);
		exceptionProcess.printStepTrace(output);
	}

}
