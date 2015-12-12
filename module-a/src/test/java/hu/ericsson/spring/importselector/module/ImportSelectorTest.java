package hu.ericsson.spring.importselector.module;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import hu.ericsson.spring.importselector.module.config.AppConfig;
import hu.ericsson.spring.importselector.module.config.AppConfigB;
import hu.ericsson.spring.importselector.module.config.AppConfigC;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AppConfig.class, AppConfigB.class, AppConfigC.class })
public class ImportSelectorTest {

	
	@Value("${welcome.message}")
	private String welcomeMessageFromModuleC;
	
	
	
	@Test
	public void testResourceValue() {

		assertEquals("Hello World-c", welcomeMessageFromModuleC);
	}

}
