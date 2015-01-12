package firework.hyl.running.dao;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class IOCBaseTest {
	protected ApplicationContext context;
	{
		this.context = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
	}
}
