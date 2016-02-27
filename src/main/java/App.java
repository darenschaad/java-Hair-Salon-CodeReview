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

    get("/client/:id", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      int id = Integer.parseInt(request.params("id"));
      Client client = Client.find(id);
      model.put("allStylists", Stylist.all());
      model.put("client", client);
      model.put("template", "templates/oneclient.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/client/:id", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      int id = Integer.parseInt(request.params("id"));
      Client client = Client.find(id);
      String firstName = request.queryParams("firstName");
      if (firstName.length() > 0){
        client.updateFirstName(firstName);
      }
      String lastName = request.queryParams("lastName");
      if (lastName.length() > 0) {
        client.updateLastName(lastName);
      }
      int stylistId = Integer.parseInt(request.queryParams("selectStylist"));
      if (stylistId > 0){
        client.updateStylist(stylistId);
      }
      String notes = request.queryParams("notes");
      if (notes.length() > 0) {
        client.setNotes(notes);
      }
      model.put("allStylists", Stylist.all());
      model.put("client", client);
      model.put("template", "templates/oneclient.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/stylist/:id", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      int id = Integer.parseInt(request.params("id"));
      Stylist stylist = Stylist.find(id);
      model.put("stylist", stylist);
      model.put("clients", stylist.getClients());
      model.put("template", "templates/onestylist.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/client/:id", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      int id = Integer.parseInt(request.params("id"));
      Client client = Client.find(id);
      String firstName = request.queryParams("firstName");
      if (firstName.length() > 0){
        client.updateFirstName(firstName);
      }
      String lastName = request.queryParams("lastName");
      if (lastName.length() > 0) {
        client.updateLastName(lastName);
      }
      int stylistId = Integer.parseInt(request.queryParams("selectStylist"));
      if (stylistId > 0){
        client.updateStylist(stylistId);
      }
      String notes = request.queryParams("notes");
      if (notes.length() > 0) {
        client.setNotes(notes);
      }
      model.put("allStylists", Stylist.all());
      model.put("client", client);
      model.put("template", "templates/onestylist.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/confirm/client/:id", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      int id = Integer.parseInt(request.params("id"));
      Client client = Client.find(id);

      model.put("client", client);
      model.put("template", "templates/deleteclient.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/delete/client/:id", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      int id = Integer.parseInt(request.params("id"));
      Client client = Client.find(id);
      client.delete();
      model.put("allClients", Client.all());
      model.put("allStylists", Stylist.all());
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/confirm/stylist/:id", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      int id = Integer.parseInt(request.params("id"));
      Stylist stylist = Stylist.find(id);

      model.put("stylist", stylist);
      model.put("template", "templates/deletestylist.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/delete/stylist/:id", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      int id = Integer.parseInt(request.params("id"));
      Stylist stylist = Stylist.find(id);
      stylist.delete();
      model.put("allClients", Client.all());
      model.put("allStylists", Stylist.all());
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());





  }
}
