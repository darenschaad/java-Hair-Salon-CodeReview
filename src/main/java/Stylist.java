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

  public void save() {
    String sql = "INSERT INTO stylists (first_name, last_name) VALUES (:first_name, :last_name)";
    try (Connection con = DB.sql2o.open()) {
      this.id = (int) con.createQuery(sql, true)
        .addParameter("first_name", first_name)
        .addParameter("last_name", last_name)
        .executeUpdate()
        .getKey();
    }
  }

  @Override
  public boolean equals(Object otherStylist){
    if (!(otherStylist instanceof Stylist)) {
      return false;
    } else {
      Stylist newStylist = (Stylist) otherStylist;
      return this.getFirstName().equals(newStylist.getFirstName()) &&
        this.getLastName().equals(newStylist.getLastName()) &&
        this.getId() == newStylist.getId();
    }
  }

  public void updateFirstName(String newFirstName) {
    this.first_name = newFirstName;
    String sql = "UPDATE stylists SET first_name = :first_name WHERE id = :id";
    try(Connection con = DB.sql2o.open()) {
      con.createQuery(sql)
        .addParameter("first_name", first_name)
        .addParameter("id", this.id)
        .executeUpdate();
    }
  }
  public void updateLastName(String newLastName) {
    this.last_name = newLastName;
    String sql = "UPDATE stylists SET last_name = :last_name WHERE id = :id";
    try(Connection con = DB.sql2o.open()) {
      con.createQuery(sql)
        .addParameter("last_name", last_name)
        .addParameter("id", this.id)
        .executeUpdate();
    }
  }
  public static Stylist find(int id) {
    String sql = "SELECT * FROM stylists WHERE id = :id";
    try(Connection con =DB.sql2o.open()) {
      return con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Stylist.class);
    }
  }
  public void delete() {
    String sql = "DELETE FROM stylists WHERE id = :id";
    try(Connection con = DB.sql2o.open()) {
      con.createQuery(sql)
        .addParameter("id", id)
        .executeUpdate();
    }
  }





}
