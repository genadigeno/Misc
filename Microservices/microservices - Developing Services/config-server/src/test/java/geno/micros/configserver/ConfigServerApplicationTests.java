package geno.micros.configserver;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.security.Provider;
import java.security.Security;
import java.util.Arrays;

//@SpringBootTest
class ConfigServerApplicationTests {
	private static final String EOL = System.getProperty("line.separator");

	//@Test
	void contextLoads() {
		final Provider[] providers = Security.getProviders();
		final Boolean verbose = Arrays.asList("test").contains("-v");
		for (final Provider p : providers)
		{
			System.out.format("%s %s%s", p.getName(), p.getVersion(), EOL);
			for (final Object o : p.keySet())
			{
				if (verbose)
				{
					System.out.format("\t%s : %s%s", o, p.getProperty((String)o), EOL);
				}
			}
		}
	}

}
