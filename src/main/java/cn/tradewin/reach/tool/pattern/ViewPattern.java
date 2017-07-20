package cn.tradewin.reach.tool.pattern;

import cn.tradewin.reach.tool.enums.RecordLayout;
import cn.tradewin.reach.tool.model.TemplateModel;
import cn.tradewin.reach.tool.util.Constant;

import java.util.Map;

public class ViewPattern extends PagePattern {
	
	public RecordLayout recordLayout;
	
	public boolean hasSearchCondition;
	
	public boolean hasPagination;

    public boolean hasEditOperation;

	@Override
	public String patternName() {
		return Constant.PagePatternType.VIEW;
	}

    @Override
    public void additional(Map<String, Object> input) {
        super.additional(input);
        input.put("hasPagination", hasPagination);
        input.put("hasEditOperation", hasEditOperation);
    }

    @Override
    public boolean check(TemplateModel template) {
        if (template.getTemplateType() == Constant.TemplateType.VALIDATOR) {
            return false;
        }
        return super.check(template);
    }
}
