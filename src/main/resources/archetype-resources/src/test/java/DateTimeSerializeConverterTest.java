#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package};

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import java.util.Calendar;
import static java.util.Calendar.DATE;
import static java.util.Calendar.HOUR_OF_DAY;
import static java.util.Calendar.MINUTE;
import static java.util.Calendar.MONTH;
import static java.util.Calendar.SECOND;
import static java.util.Calendar.YEAR;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class DateTimeSerializeConverterTest {
  
  private DateTimeSerializeConverter conv;
  
  @Before
  public void setup() {
    conv = new DateTimeSerializeConverter();
  }

  /**
   * Test of convert method, of class DateTimeSerializeConverter.
   */
  @Test
  public void testConvert() {
    Calendar cal = GregorianCalendar.getInstance();
    cal.setTimeZone(TimeZone.getTimeZone("GMT"));
//    cal.setTime(Date.from(Instant.now(Clock.systemUTC())));
    String result = conv.convert(cal.getTime());
    assertNotNull("Result MUST NOT be null", result);
    String expected = String.format("%4d-%02d-%02dT%02d:%02d:%02d+0000", 
                                    cal.get(YEAR), cal.get(MONTH)+1, cal.get(DATE), 
                                    cal.get(HOUR_OF_DAY), cal.get(MINUTE), cal.get(SECOND));
    assertEquals(String.format("Result '%2${symbol_dollar}s' MUST match '%1${symbol_dollar}s'", expected, result), expected, result);
  }

  /**
   * Test of getInputType method, of class DateTimeSerializeConverter.
   */
  @Test
  public void testGetInputType() {
    JavaType type = conv.getOutputType(TypeFactory.defaultInstance());
    assertNotNull("Input type MUST NOT be null", type);
    assertTrue(String.format("Type of type MUST be Date, but is '%s'", type.getRawClass().getSimpleName()), String.class.equals(type.getRawClass()));
  }

  /**
   * Test of getOutputType method, of class DateTimeSerializeConverter.
   */
  @Test
  public void testGetOutputType() {
    JavaType type = conv.getInputType(TypeFactory.defaultInstance());
    assertNotNull("Input type MUST NOT be null", type);
    assertTrue(String.format("Type of type MUST be String, but is '%s'", type.getRawClass().getSimpleName()), Date.class.equals(type.getRawClass()));
  }
  
}