package controllers;

import be.objectify.deadbolt.java.actions.Group;
import be.objectify.deadbolt.java.actions.Restrict;
import be.objectify.deadbolt.java.actions.SubjectPresent;
import models.Company;
import models.Reply;
import models.ReplyStatus;
import play.Logger;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import service.CompanyService;
import service.ServiceException;

import java.util.List;

/**
 * Created by Anton Chernov on 12/30/2015.
 */
@SubjectPresent
public class CompanyController extends Controller {
    private static final Logger.ALogger LOGGER = Logger.of(CompanyController.class);


    @Restrict({@Group("SYS_ADMIN")})
    public Result companies() throws ControllerException {
        LOGGER.debug("API Get company list for user = {}", Http.Context.current().args.get("user").toString());

        List<Company> companyList = null;
        try {
            companyList = CompanyService.getList();
        } catch (ServiceException e) {
            LOGGER.error("error = {}", e);
            throw new ControllerException(e.getMessage(), e);
        }

        return ok(Json.toJson(new Reply<>(ReplyStatus.SUCCESS, companyList)));
    }
}
