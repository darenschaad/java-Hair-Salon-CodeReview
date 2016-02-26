import org.fluentlenium.adapter.FluentTest;
import static org.junit.Assert.*;
import org.junit.*;
import org.junit.ClassRule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import static org.fluentlenium.core.filter.FilterConstructor.*;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.By;

import static org.assertj.core.api.Assertions.assertThat;

public class AppTest extends FluentTest {
  public WebDriver webDriver = new HtmlUnitDriver();

  @Override
  public WebDriver getDefaultDriver() {
      return webDriver;
  }

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @ClassRule
  public static ServerRule server = new ServerRule();

  @Test
  public void rootTest() {
      goTo("http://localhost:4567/");
      assertThat(pageSource()).contains("Hair Salon");
  }

  @Test
  public void assertsThatAllStylistsDisplayOnHomePage() {
    Stylist testStylist = new Stylist("Daren", "Schaad");
    testStylist.save();
    goTo("http://localhost:4567/");
    assertThat(pageSource()).contains("Daren Schaad");
  }

  @Test
  public void addANewStylist() {
    
  }

}
