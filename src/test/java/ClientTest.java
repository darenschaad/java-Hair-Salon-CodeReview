import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;

public class ClientTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void all_emptyAtFirst() {
    assertEquals(Client.all().size(), 0);
  }

  @Test
  public void save_returnsTrueIfTypesAreTheSame() {
    Client testClient = new Client("Daren", "Schaad", 1);
    testClient.save();
    assertTrue(Client.all().get(0).equals(testClient));
  }

  @Test
  public void updateFirstName_updatesTheFirstNameOfAClient_true() {
    Client testClient = new Client("Daren", "Schaad", 1);
    testClient.save();
    testClient.updateFirstName("Karen");
    Client savedClient = Client.all().get(0);
    assertEquals(savedClient.getFirstName(), "Karen");
  }

  // @Test
  // public void updateLastName_updatesTheLastNameOfAStylist_true() {
  //   Stylist testStylist = new Stylist("Daren", "Schaad");
  //   testStylist.save();
  //   testStylist.updateLastName("Brown");
  //   Stylist savedStylist = Stylist.all().get(0);
  //   assertEquals(savedStylist.getLastName(), "Brown");
  // }





}
