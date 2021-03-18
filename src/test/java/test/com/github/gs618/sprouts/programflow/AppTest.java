package test.com.github.gs618.sprouts.programflow;

import com.github.gs618.sprouts.programflow.Input;
import com.github.gs618.sprouts.programflow.Output;
import org.junit.Test;

import java.util.Optional;

public class AppTest {

	@Test
	public void processTest() {
		ProcessTest processTest = new ProcessTest();
		Input input = new Input();
		input.putData("DATA", -11);
		Output output = new Output();
		processTest.run(input, output);

		Optional.ofNullable(output.getException()).ifPresent(Exception::printStackTrace);
		output.getPassedSteps().stream().map(step->step.getClass().getName() + " ... succeed").forEach(System.out::println);

		if(!output.getCurrentStep().equals(output.getPassedSteps().get(output.getPassedSteps().size() - 1))) {
			System.out.println(output.getCurrentStep().getClass().getName() + " ... failure");
		}
	}
}
