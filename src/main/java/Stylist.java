import org.sql2o.*;
import java.util.List;

public class Stylist {
  private int id;
  private String first_name;
  private String last_name;
  private int specialty_id;
  private String notes;

  public Stylist (String first_name, String last_name) {
    this.first_name = first_name;
    this.last_name = last_name;
  }

  public int getId() {
    return id;
  }

  public String getFirstName () {
    return first_name;
  }

  public String getLastName() {
    return last_name;
  }

  public String getName() {
    return (first_name + " " + last_name);
  }

  public static List<Stylist> all() {
    String sql = "SELECT * FROM stylists";
    try (Connection con = DB.sql2o.open()) {
      return con.createQuery(sql)
        .executeAndFetch(Stylist.class);
    }
  }





}
