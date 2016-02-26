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

  @Test
  public void updateFirstName_updatesTheLastNameOfAStylist_true() {
    Stylist testStylist = new Stylist("Daren", "Schaad");
    testStylist.save();
    testStylist.updateLastName("Brown");
    Stylist savedStylist = Stylist.all().get(0);
    assertEquals(savedStylist.getLastName(), "Brown");
  }

  @Test
  public void find_findsTheSpecificStylistBasedOnId() {
    Stylist testStylist = new Stylist("Daren", "Schaad");
    Stylist testStylist2 = new Stylist("Nevin", "Brown");
    testStylist.save();
    testStylist2.save();
    assertEquals(Stylist.find(testStylist.getId()).getName(), "Daren Schaad");
    assertTrue(Stylist.find(testStylist2.getId()).equals(testStylist2));
  }

  @Test
  public void delete_deletesTheSpecifiedStylistBasedOnId() {
    Stylist testStylist = new Stylist("Daren", "Schaad");
    Stylist testStylist2 = new Stylist("Nevin", "Brown");
    testStylist.save();
    testStylist2.save();
    testStylist.delete();
    assertFalse(Stylist.all().contains(testStylist));
    assertTrue(Stylist.all().contains(testStylist2));
  }

  @Test
  public void setters_setAndGetOptionalAttributes_true() {
    Stylist testStylist = new Stylist("Daren", "Schaad");
    testStylist.save();
    testStylist.setSpecialtyId(1);
    testStylist.setNotes("Good stylist");
    assertTrue(Stylist.all().get(0).equals(testStylist));
    assertEquals(testStylist.getSpecialtyId(), 1);
    assertEquals(testStylist.getNotes(), "Good stylist");
  }




}
