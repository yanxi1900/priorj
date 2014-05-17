package suites;

import integration.AppIntegrationTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import controller.DataManagerTest;
import controller.PriorJTest;

@RunWith(Suite.class)
@SuiteClasses({ DataManagerTest.class,
	PriorJTest.class,
	AppIntegrationTest.class
})
public class AllTests {

}
