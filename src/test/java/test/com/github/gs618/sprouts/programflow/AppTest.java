package test.com.github.gs618.sprouts.programflow;

import com.github.gs618.sprouts.programflow.Input;
import com.github.gs618.sprouts.programflow.Output;
import org.junit.Test;

public class AppTest {

	@Test
	public void processTest() {
		ProcessTest processTest = new ProcessTest();
		Input input = new Input();
		input.putData("DATA", -11);
		Output output = new Output();
		processTest.start(input, output);

		output.getPassedSteps().forEach(System.out::println);
	}
}
