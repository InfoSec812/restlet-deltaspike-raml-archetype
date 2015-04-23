#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package};

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import java.util.Calendar;
import static java.util.Calendar.APRIL;
import static java.util.Calendar.DATE;
import static java.util.Calendar.HOUR_OF_DAY;
import static java.util.Calendar.MINUTE;
import static java.util.Calendar.MONTH;
import static java.util.Calendar.SECOND;
import static java.util.Calendar.YEAR;
import java.util.Date;
import java.util.TimeZone;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class DateTimeDeserializeConverterTest {
  
  private DateTimeDeserializeConverter conv;
  
  @Before
  public void setup() {
    conv = new DateTimeDeserializeConverter();
  }

  /**
   * Test of convert method, of class DateTimeDeserializeConverter.
   */
  @Test
  public void testConvert() {
    String badDate = "njasduohAKJBjdf";
    Date result = conv.convert(badDate);
    assertNull("Invalid formatted date MUST return null when parsed.", result);
    
    String testDate = "2015-04-22T11:55:30-0400";
    result = conv.convert(testDate);
    assertNotNull("Resulting Date object MUST NOT be null", result);
    Calendar cal = Calendar.getInstance();
    cal.setTime(result);
    cal.setTimeZone(TimeZone.getTimeZone("GMT-0400"));
    int monthPart = cal.get(MONTH);
    assertEquals(String.format("Month part of date MUST be '4', but is actually '%d'", monthPart), APRIL, monthPart);
    int yearPart = cal.get(YEAR);
    assertEquals(String.format("Year part of date MUST be '2015', but is actually '%d'", yearPart), 2015, yearPart);
    int dayPart = cal.get(DATE);
    assertEquals(String.format("Date part of date MUST be '22', but is actually '%d'", yearPart), 22, dayPart);
    int hourPart = cal.get(HOUR_OF_DAY);
    assertEquals(String.format("Hour part of date MUST be '11', but is actually '%d'", hourPart), 11, hourPart);
    int minPart = cal.get(MINUTE);
    assertEquals(String.format("Minute part of date MUST be '55', but is actually '%d'", minPart), 55, minPart);
    int secPart = cal.get(SECOND);
    assertEquals(String.format("Second part of date MUST be '30', but is actually '%d'", secPart), 30, secPart);
  }

  /**
   * Test of getInputType method, of class DateTimeDeserializeConverter.
   */
  @Test
  public void testGetInputType() {
    JavaType type = conv.getInputType(TypeFactory.defaultInstance());
    assertNotNull("Input type MUST NOT be null", type);
    assertTrue(String.format("Type of type MUST be String, but is '%s'", type.getRawClass().getSimpleName()), String.class.equals(type.getRawClass()));
  }

  /**
   * Test of getOutputType method, of class DateTimeDeserializeConverter.
   */
  @Test
  public void testGetOutputType() {
    JavaType type = conv.getOutputType(TypeFactory.defaultInstance());
    assertNotNull("Input type MUST NOT be null", type);
    assertTrue(String.format("Type of type MUST be Date, but is '%s'", type.getRawClass().getSimpleName()), Date.class.equals(type.getRawClass()));
  }
  
}
