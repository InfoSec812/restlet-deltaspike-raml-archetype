#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package};

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Converter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class DateTimeSerializeConverter implements Converter<Date, String> {

  private final SimpleDateFormat formatter;
  
  public DateTimeSerializeConverter() {
    formatter = (SimpleDateFormat)SimpleDateFormat.getDateTimeInstance();
    formatter.applyPattern("yyyy-MM-dd'T'HH:mm:ssZ");
    formatter.setTimeZone(TimeZone.getTimeZone("GMT"));
  }
  
  @Override
  public String convert(Date value) {
    return formatter.format(value);
  }

  @Override
  public JavaType getInputType(TypeFactory typeFactory) {
    return typeFactory.constructType(Date.class);
  }

  @Override
  public JavaType getOutputType(TypeFactory typeFactory) {
    return typeFactory.constructType(String.class);
  }

}
