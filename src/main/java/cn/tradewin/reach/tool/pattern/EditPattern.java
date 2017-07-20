package cn.tradewin.reach.tool.pattern;

import cn.tradewin.reach.tool.model.TemplateModel;
import cn.tradewin.reach.tool.util.Constant;

/**
 * Created by hudingchen on 8/21/16.
 */
public class EditPattern extends PagePattern {

    public boolean disablePatternName = false;

    @Override
    public String patternName() {
        if (disablePatternName) {
            return "";
        } else {
            return Constant.PagePatternType.EDIT;
        }
    }

    @Override
    public boolean check(TemplateModel template) {
        if (template.getTemplateType() == Constant.TemplateType.VALIDATOR) {
            return true;
        }
        return super.check(template);
    }
}
