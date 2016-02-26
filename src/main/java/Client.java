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

  public void save() {
    String sql = "INSERT INTO clients (first_name, last_name, stylist_id) VALUES (:first_name, :last_name, :stylist_id)";
    try (Connection con = DB.sql2o.open()) {
      this.id = (int) con.createQuery(sql, true)
        .addParameter("first_name", first_name)
        .addParameter("last_name", last_name)
        .addParameter("stylist_id", stylist_id)
        .executeUpdate()
        .getKey();
    }
  }

  public void updateFirstName(String newFirstName) {
    this.first_name = newFirstName;
    String sql = "UPDATE clients SET first_name = :first_name WHERE id = :id";
    try(Connection con = DB.sql2o.open()) {
      con.createQuery(sql)
        .addParameter("first_name", first_name)
        .addParameter("id", this.id)
        .executeUpdate();
    }
  }
  // public void updateLastName(String newLastName) {
  //   this.last_name = newLastName;
  //   String sql = "UPDATE clients SET last_name = :last_name WHERE id = :id";
  //   try(Connection con = DB.sql2o.open()) {
  //     con.createQuery(sql)
  //       .addParameter("last_name", last_name)
  //       .addParameter("id", this.id)
  //       .executeUpdate();
  //   }
  // }






}
