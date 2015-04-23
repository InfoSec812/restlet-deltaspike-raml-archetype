#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.types;

import org.meanbean.test.BeanTester;
import org.meanbean.test.Configuration;
import org.meanbean.test.ConfigurationBuilder;
import org.meanbean.test.EqualsMethodTester;
import org.meanbean.test.HashCodeMethodTester;

public class GenericBeanTester {

    protected BeanTester tester;
    protected EqualsMethodTester eqTester;
    protected HashCodeMethodTester hashTester;
    protected Configuration config;

    {
        config = new ConfigurationBuilder().iterations(20).build();
        tester = new BeanTester();
        tester.setIterations(20);
        eqTester = new EqualsMethodTester();
        hashTester = new HashCodeMethodTester();
    }
}
