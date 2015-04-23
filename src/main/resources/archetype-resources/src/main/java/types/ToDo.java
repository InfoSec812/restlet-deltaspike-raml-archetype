#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.types;

import ${package}.DateTimeDeserializeConverter;
import ${package}.DateTimeSerializeConverter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.Data;

@Data
@Entity
@Table(name = "todos")
@XmlRootElement(name = "todo")
@JsonTypeName("todo")
public class ToDo implements Serializable {
  
  /**
   * Unique ID of the ToDo item
   */
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @XmlAttribute(name="id", required = false)
  @JsonProperty
  @JsonPropertyDescription("Unique ID")
  private Long id;

  /**
   * Short title of the ToDo item
   */
  @XmlElement(name = "title", required = true, nillable = false)
  @JsonProperty
  @JsonPropertyDescription("Short title of the ToDo item")
  private String title;
  
  /**
   * Long description of the ToDo item
   */
  @XmlElement(name = "description", nillable = true)
  @JsonProperty
  @JsonPropertyDescription("Long description of the ToDo item")
  private String description;
  
  /**
   * The date/time at which the ToDo item was created.
   */
  @XmlElement(name = "created", required = true, nillable = false)
  @JsonProperty
  @JsonPropertyDescription("The date/time when the ToDo item was created")
  @JsonSerialize(contentConverter = DateTimeSerializeConverter.class)
  @JsonDeserialize(contentConverter = DateTimeDeserializeConverter.class)
  @Temporal(TemporalType.TIMESTAMP)
  private Date created = new Date();
  
  /**
   * The date/time at which the ToDo item is due for completion.
   */
  @XmlElement(name = "due", nillable = true)
  @JsonProperty
  @JsonPropertyDescription("The date/time when the ToDo item is due to be completed")
  @JsonSerialize(contentConverter = DateTimeSerializeConverter.class)
  @JsonDeserialize(contentConverter = DateTimeDeserializeConverter.class)
  @Temporal(TemporalType.TIMESTAMP)
  private Date dueDate;
}
