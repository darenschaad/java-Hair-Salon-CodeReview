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

  @Test
  public void updateLastName_updatesTheLastNameOfAClient_true() {
    Client testClient = new Client("Daren", "Schaad", 1);
    testClient.save();
    testClient.updateLastName("Brown");
    Client savedClient = Client.all().get(0);
    assertEquals(savedClient.getLastName(), "Brown");
  }

  @Test
  public void updateStylist_updatesTheStylistIdOfAClient_true() {
    Client testClient = new Client("Daren", "Schaad", 1);
    testClient.save();
    testClient.updateStylist(2);
    Client savedClient = Client.all().get(0);
    assertEquals(savedClient.getStylistId(), 2);
  }

  @Test
  public void find_findsTheSpecificClientBasedOnId() {
  Client testClient = new Client("Daren", "Schaad", 1);
  Client testClient2 = new Client("Nevin", "Brown", 2);
  testClient.save();
  testClient2.save();
  assertEquals(Client.find(testClient.getId()).getName(), "Daren Schaad");
  assertTrue(Client.find(testClient2.getId()).equals(testClient2));
}

@Test
public void delete_deletesTheSpecifiedClientBasedOnId() {
  Client testClient = new Client("Daren", "Schaad", 1);
  Client testClient2 = new Client("Nevin", "Brown", 2);
  testClient.save();
  testClient2.save();
  testClient.delete();
  assertFalse(Client.all().contains(testClient));
  assertTrue(Client.all().contains(testClient2));
}

@Test
public void getStylist_findsTheStylistForASpecificClient() {
  Stylist testStylist = new Stylist("Nevin", "Brown");
  testStylist.save();
  Client testClient = new Client("Daren", "Schaad", testStylist.getId());
  testClient.save();
  assertEquals(testClient.getStylist(), "Nevin");
}






}
