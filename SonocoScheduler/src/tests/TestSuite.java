package tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)

@Suite.SuiteClasses({
   ExtruderTests.class,
   PressTests.class,
   AlgorithmTests.class
})

public class TestSuite {

}
