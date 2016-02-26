import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;

public class StylistTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void all_emptyAtFirst() {
    assertEquals(Stylist.all().size(), 0);
  }

  @Test
  public void save_returnsTrueIfNamesAndIdsAreTheSame() {
    Stylist testStylist = new Stylist("Daren", "Schaad");
    testStylist.save();
    assertTrue(Stylist.all().get(0).equals(testStylist));
  }

  @Test
  public void updateFirstName_updatesTheFirstNameOfAStylist_true() {
    Stylist testStylist = new Stylist("Daren", "Schaad");
    testStylist.save();
    testStylist.updateFirstName("Karen");
    Stylist savedStylist = Stylist.all().get(0);
    assertEquals(savedStylist.getFirstName(), "Karen");
  }



}
