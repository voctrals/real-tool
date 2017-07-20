package cn.tradewin.reach.tool.pattern;

import cn.tradewin.reach.tool.enums.RecordLayout;
import cn.tradewin.reach.tool.model.TemplateModel;

import java.util.ArrayList;
import java.util.List;

public class ViewAndEditDefaultPattern extends PagePattern {
	
	public RecordLayout recordLayout;
	
	public boolean hasSearchCondition;
	
	public boolean hasPagination;
	
	@Override
	public List<TemplateModel> templates() {
		List<TemplateModel> templateList = new ArrayList<TemplateModel>();
//		templateList.add(new TemplateModel("controller_view.ftl", getControllerTemplatePath(), "MasterViewController", ".java"));
//		templateList.add(new TemplateModel("form_view.ftl", getFormTemplatePath(), "MasterViewForm", ".java"));
//		templateList.add(new TemplateModel("jsp_view.ftl", getJspTemplatePath(), "_master_view", ".html"));
//
//		templateList.add(new TemplateModel("controller_edit.ftl", getControllerTemplatePath(), "MasterEditController", ".java"));
//		templateList.add(new TemplateModel("form_edit.ftl", getFormTemplatePath(), "MasterEditForm", ".java"));
//		templateList.add(new TemplateModel("validator.ftl", getValidatorTemplatePath(), "MasterEditValidator", ".java"));
//		templateList.add(new TemplateModel("jsp_edit.ftl", getJspTemplatePath(), "_master_edit", ".html"));
//
//		templateList.add(new TemplateModel("vo.ftl", getVoTemplatePath(), "Vo", ".java"));
//		templateList.add(new TemplateModel("service.ftl", getServiceTemplatePath(), "Service", ".java"));
//		templateList.add(new TemplateModel("dto.ftl", getDtoTemplatePath(), "Dto", ".java"));
//
//		templateList.add(new TemplateModel("dao.ftl", getDaoTemplatePath(), "Dao", ".java"));
//		templateList.add(new TemplateModel("model.ftl", getModelTemplatePath(), "", ".java"));
//		templateList.add(new TemplateModel("mapper.ftl", getSqlTemplatePath(), "Mapper", ".xml"));
		return templateList;
	}

	@Override
	public String patternName() {
		return null;
	}


}
