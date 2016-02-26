import org.sql2o.*;
import java.util.List;

public class Client {
  private int id;
  private String first_name;
  private String last_name;
  private int stylist_id;
  private String notes;

  public Client (String first_name, String last_name) {
    this.first_name = first_name;
    this.last_name = last_name;
  }
}
