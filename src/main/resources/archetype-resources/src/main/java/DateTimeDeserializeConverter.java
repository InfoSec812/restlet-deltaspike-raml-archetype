#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package};

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Converter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DateTimeDeserializeConverter implements Converter<String, Date> {

  private final SimpleDateFormat formatter;
  
  public DateTimeDeserializeConverter() {
    formatter = (SimpleDateFormat)SimpleDateFormat.getDateTimeInstance();
    formatter.applyPattern("yyyy-MM-dd'T'HH:mm:ssZ");
    formatter.setTimeZone(TimeZone.getTimeZone("GMT"));
  }
  
  @Override
  public Date convert(String value) {
    try {
      return formatter.parse(value);
    } catch (ParseException ex) {
      LOG.error(String.format("Unable to parse date '%s'", value));
    }
    return null;
  }

  @Override
  public JavaType getInputType(TypeFactory typeFactory) {
    return typeFactory.constructType(String.class);
  }

  @Override
  public JavaType getOutputType(TypeFactory typeFactory) {
    return typeFactory.constructType(Date.class);
  }

}
