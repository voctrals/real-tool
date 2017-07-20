package cn.tradewin.reach.tool.pattern;

import cn.tradewin.reach.tool.model.TemplateModel;
import cn.tradewin.reach.tool.util.Constant;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hudingchen on 25/11/2016.
 */
public class ApiPattern extends PagePattern {

    public ApiPattern() {
        this.ftlPath = "api";
    }

    @Override
    public String patternName() {
        return "";
    }

    @Override
    public List<TemplateModel> templates() {
        List<TemplateModel> lst = new ArrayList<>();
        lst.add(createTemplateModel(Constant.TemplateType.SERVICE, "Service", getServiceTemplatePath(), true, ".java", false));
        lst.add(createTemplateModel(Constant.TemplateType.DTO, "Dto", getDtoTemplatePath(), true, ".java", true));
        lst.add(createTemplateModel(Constant.TemplateType.DAO, "Dao", getDaoTemplatePath(), true, ".java", true));
        lst.add(createTemplateModel(Constant.TemplateType.MODEL, "Model", getModelTemplatePath(), false, ".java", true));
        lst.add(createTemplateModel(Constant.TemplateType.MAPPER, "Mapper", getSqlTemplatePath(), true, ".xml", true));
        return lst;
    }
}
