package firework.hyl.running.common.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.hibernate.tool.hbm2ddl.SchemaExport;

@SuppressWarnings("deprecation")
public class HibernateUtil {
	private static SessionFactory sessionFactory;

	private HibernateUtil() {
	}

	public static SessionFactory getSessionFactoryInstance() {
		if (sessionFactory == null) {
			buildSessionFactory();
		}
		return sessionFactory;
	}

	private static void buildSessionFactory() {
		Configuration configuration = new Configuration().configure();
		ServiceRegistry registry = new ServiceRegistryBuilder().applySettings(
				configuration.getProperties()).build();

		sessionFactory = configuration.buildSessionFactory(registry);
	}

	public Session getCurrentSession() {
		return getSessionFactoryInstance().getCurrentSession();
	}

	public static void reCreateTables() {
		try {
			Configuration configuration = new Configuration();
			configuration.configure();// hibernate.cfg.xml
			// auto generate tables...
			SchemaExport export = new SchemaExport(configuration);

			export.create(true, true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
