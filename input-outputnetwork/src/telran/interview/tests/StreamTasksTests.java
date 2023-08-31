package telran.interview.tests;

import static org.junit.jupiter.api.Assertions.*;
import static telran.interview.StreamTasks.*;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class StreamTasksTests {

	@Test
	@Disabled
	void displayOccurrencesTest() {
		String [] str = {"lmn", "ab", "lmn", "c", "ab", "a", "lmn"};
		displayOccurences(str);
	}
	@Test
	@Disabled
	void displayOddEvenTest() {
		
		displayOddEvenGrouping(20);
	}
	
	@Test
	void displayStatisticTest() {
		
		displayStatisticNumbers(100);
	}
}
