package ch.hevs.test;

import junit.framework.TestCase;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.Test;

public class CreateSchemaTest extends TestCase {

	@Test
	public void test() {

		Configuration cfg = new Configuration();
		cfg.addAnnotatedClass(ch.hevs.businessobject.User.class);
		cfg.addAnnotatedClass(ch.hevs.businessobject.Device.class);
		cfg.addAnnotatedClass(ch.hevs.businessobject.Computer.class);
		cfg.addAnnotatedClass(ch.hevs.businessobject.MobilePhone.class);
		cfg.addAnnotatedClass(ch.hevs.businessobject.Tablet.class);
		cfg.addAnnotatedClass(ch.hevs.businessobject.OperationalSystem.class);
		
		cfg.setProperty("hibernate.dialect",
				"org.hibernate.dialect.HSQLDialect");
		cfg.setProperty("hibernate.connection.driver_class",
				"org.hsqldb.jdbcDriver");
		cfg.setProperty("hibernate.connection.driver_class",
				"org.hsqldb.jdbcDriver");
		cfg.setProperty("hibernate.connection.url",
				"jdbc:hsqldb:hsql://localhost:10002/DB");
		cfg.setProperty("hibernate.connection.username", "sa");
		cfg.setProperty("hibernate.connection.password", "");

		new SchemaExport(cfg).setOutputFile("schema.ddl").create(false, true);
	}
}