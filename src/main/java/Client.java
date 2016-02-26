import org.sql2o.*;
import java.util.List;

public class Client {
  private int id;
  private String first_name;
  private String last_name;
  private int stylist_id;
  private String notes;

  public Client (String first_name, String last_name, int stylist_id) {
    this.first_name = first_name;
    this.last_name = last_name;
    this.stylist_id = stylist_id;
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

  @Override
  public boolean equals(Object otherClient){
    if (!(otherClient instanceof Client)) {
      return false;
    } else {
      Client newClient = (Client) otherClient;
      return this.getName().equals(newClient.getName()) &&
        this.getId() == newClient.getId();
    }
  }

  public static List<Client> all() {
    String sql = "SELECT * FROM clients ORDER BY last_name, first_name";
    try (Connection con = DB.sql2o.open()) {
      return con.createQuery(sql)
        .executeAndFetch(Client.class);
    }
  }






}
