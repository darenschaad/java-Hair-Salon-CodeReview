import java.util.Map;
import java.util.HashMap;
import static spark.Spark.*;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class App {

  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("allStylists", Stylist.all());
      model.put("allClients", Client.all());
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      String firstName = request.queryParams("firstName");
      String lastName = request.queryParams("lastName");
      Stylist newStylist = new Stylist(firstName, lastName);
      newStylist.save();
      model.put("allClients", Client.all());
      model.put("allStylists", Stylist.all());
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/new-stylist", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("allStylists", Stylist.all());
      model.put("template", "templates/newstylist.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/new-client", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("allStylists", Stylist.all());
      model.put("allClients", Client.all());
      model.put("template", "templates/newclient.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/new-client", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      String firstName = request.queryParams("firstName");
      String lastName = request.queryParams("lastName");
      int stylistId = Integer.parseInt(request.queryParams("selectStylist"));
      Client newClient = new Client(firstName, lastName, stylistId);
      newClient.save();
      model.put("allStylists", Stylist.all());
      model.put("allClients", Client.all());
      model.put("template", "templates/newclient.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());




  }
}
