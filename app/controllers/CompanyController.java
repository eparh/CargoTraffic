package controllers;

import be.objectify.deadbolt.java.actions.Group;
import be.objectify.deadbolt.java.actions.Restrict;
import be.objectify.deadbolt.java.actions.SubjectPresent;
import com.fasterxml.jackson.databind.JsonNode;
import models.Company;
import models.Reply;
import models.ReplyStatus;
import play.Logger;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import service.CompanyService;
import service.ServiceException;

import java.util.List;
import java.util.Map;


@SubjectPresent
public class CompanyController extends Controller {
    private static final Logger.ALogger LOGGER = Logger.of(CompanyController.class);


    @Restrict({@Group("SYS_ADMIN")})
    public Result companies() throws ControllerException {
        /*JsonNode json = request().body().asJson();
        Long id = json.findPath("id").asLong();
        Integer companiesCount = json.findPath("companies").asInt();
        Boolean isAscOrder = json.findPath("ascOrder").asBoolean();*/
        final Map<String, String[]> stringMap = request().queryString();
        Long id = Long.parseLong(stringMap.get("id")[0]);
        Integer companiesCount = Integer.parseInt(stringMap.get("companies")[0]);
        Boolean isAscOrder = Boolean.parseBoolean(stringMap.get("ascOrder")[0]);
                LOGGER.debug("id, companies, ascOrder: {}, {}, {}", id, companiesCount, isAscOrder);
        LOGGER.debug("API Get company list for user: {}", Http.Context.current().args.get("user").toString());

        List<Company> companyList = null;
        try {
            companyList = CompanyService.getCompanies(id, companiesCount, isAscOrder);
        } catch (ServiceException e) {
            LOGGER.error("error: {}", e);
            return ok(Json.toJson(new Reply<>(ReplyStatus.ERROR, companyList)));
        }
        return ok(Json.toJson(new Reply<>(ReplyStatus.SUCCESS, companyList)));
    }
}
