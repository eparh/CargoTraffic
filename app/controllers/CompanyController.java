package controllers;

import Service.CompanyService;
import models.Company;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.List;

/**
 * Created by Anton Chernov on 12/30/2015.
 */
public class CompanyController extends Controller {
    private final static Logger LOGGER = LoggerFactory.getLogger(CompanyController.class);

    @Transactional
    public Result getList() {
        List<Company> companyList = CompanyService.getList();
        LOGGER.debug("JSON = {}", Json.toJson(companyList).toString());
        return ok(Json.toJson(companyList));
    }
}
