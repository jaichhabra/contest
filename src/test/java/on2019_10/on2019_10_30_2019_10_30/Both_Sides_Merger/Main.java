package on2019_10.on2019_10_30_2019_10_30.Both_Sides_Merger;

import net.egork.chelper.tester.NewTester;

import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!NewTester.test("src/test/java/on2019_10/on2019_10_30_2019_10_30/Both_Sides_Merger/Both Sides Merger.json"))
			Assert.fail();
	}
}