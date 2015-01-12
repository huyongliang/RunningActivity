package firework.hyl.running.common.bean;

import org.junit.Test;

import firework.hyl.running.common.util.HibernateUtil;

public class HibernetBaseTest {

	@Test
	public void testCreateTables() {
		try {
			HibernateUtil.reCreateTables();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
