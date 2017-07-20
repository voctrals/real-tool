package cn.tradewin.reach.tool.core;

import cn.tradewin.reach.tool.model.ConstraintModel;
import cn.tradewin.reach.tool.model.TemplateModel;
import cn.tradewin.reach.tool.pattern.PagePattern;
import cn.tradewin.reach.tool.util.CamelCaseUtils;
import cn.tradewin.reach.tool.util.Constant;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import freemarker.template.Version;
import org.apache.commons.lang3.StringUtils;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;

import java.io.File;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.sql.Types;
import java.util.*;

public class TemplateGenerator {

    private PagePattern pattern;

    private static List<ConstraintModel> allConstraintList = new ArrayList<>();

	private List<ConstraintModel> importConstraintList = new ArrayList<>();

	public TemplateGenerator(PagePattern pattern) {
        this.pattern = pattern;
		if (allConstraintList.isEmpty()) {
			allConstraintList.add(new ConstraintModel("NotEmpty", "org.hibernate.validator.constraints"));
			allConstraintList.add(new ConstraintModel("NotNull", "javax.validation.constraints"));
			//allConstraintList.add(new ConstraintModel("MaxByteLength", "cn.tradewin.reach.web.validator.constraints"));
            allConstraintList.add(new ConstraintModel("MaxByteLength", "cn.internnet.dagger.core.validator.constraints"));
		}
	}

	public void execute() {
		try {
			Configuration cfg = new Configuration(new Version(2, 3, 20));
			cfg.setDefaultEncoding("UTF-8");
			cfg.setLocale(Locale.US);
			cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

			Map<String, Object> input = new HashMap<>();
			// common field
            input.put("package", pattern.packageModel);
			input.put("name", pattern.domainObjectName);

            List<Map<String,Object>> allColumnList = convert(pattern.table.getAllColumns(), true, false);
            List<String> importList = new ArrayList<>();
            HashSet<String> h = new HashSet<>();
            for (Map<String,Object> map : allColumnList) {
                String javaPackageName = map.get("javaPackageName").toString();
                if (!"java.lang".equals(javaPackageName)) {
                    h.add(javaPackageName + "." + map.get("javaType").toString());
                }
            }
            importList.addAll(h);

            input.put("importList", importList);
            input.put("allColumnList", allColumnList);
			input.put("primaryKeyColumnList", convert(pattern.table.getPrimaryKeyColumns(), false, false));
			input.put("nonPrimaryKeyColumnList", convert(pattern.table.getNonPrimaryKeyColumns(), false, false));
			input.put("allColumnWithSysFieldList", convert(pattern.table.getAllColumns(), false, true));
			input.put("primaryKeyColumnWithSysFieldList", convert(pattern.table.getPrimaryKeyColumns(), false, true));
			input.put("nonPrimaryKeyColumnWithSysFieldList", convert(pattern.table.getNonPrimaryKeyColumns(), false, true));
			input.put("tableName", pattern.tableName);
			input.put("importConstraintList", importConstraintList);

            IntrospectedColumn identityColumn = getIdentityColumn(pattern.table);
            if (identityColumn == null) {
                input.put("hasIdentity", "0");
            } else {
                input.put("hasIdentity", "1");
                input.put("identityColumn", identityColumn.getActualColumnName());
                input.put("identityPropertyName", CamelCaseUtils.toCamelCase(identityColumn.getActualColumnName()));
            }

			// extral field
			pattern.additional(input);

			Writer consoleWriter = new OutputStreamWriter(System.out);
			for (TemplateModel templateModel : pattern.templates()) {
			    if (!pattern.check(templateModel)) {
                    continue;
                }

                if (pattern.outputSqlPartOnly) {
					if (templateModel.getTemplateType() == Constant.TemplateType.DTO ||
							templateModel.getTemplateType() == Constant.TemplateType.DAO ||
							templateModel.getTemplateType() == Constant.TemplateType.MODEL ||
							templateModel.getTemplateType() == Constant.TemplateType.MAPPER ||
							templateModel.getTemplateType() == Constant.TemplateType.SERVICE
							) {

					} else {
						continue;
					}
				}

                String bussinessName;
                if (StringUtils.isEmpty(templateModel.getBussinessName())) {
                    bussinessName = pattern.bussinessName;
                } else {
                    bussinessName = templateModel.getBussinessName();
                }

                String patternName;
                if (StringUtils.isEmpty(templateModel.getPatternName())) {
                    patternName = pattern.patternName();
                } else {
                    patternName = templateModel.getPatternName();
                }

                String templateName;
				if (templateModel.isCommonTemplateFlag()) {
					cfg.setClassForTemplateLoading(TemplateGenerator.class, "/ftl/common");
					templateName = templateModel.getTemplateName().toLowerCase() ;
				} else {
					if (StringUtils.isEmpty(pattern.ftlPath)) {
						cfg.setClassForTemplateLoading(TemplateGenerator.class, "/ftl/" + pattern.patternName().toLowerCase());
					} else {
						cfg.setClassForTemplateLoading(TemplateGenerator.class, "/ftl/" + pattern.ftlPath.toLowerCase());
					}
					templateName = CamelCaseUtils.toUnderlineName(patternName) + "_" + templateModel.getTemplateName().toLowerCase();
				}

                input.put("htmlName", CamelCaseUtils.toUnderlineName(bussinessName + patternName));
                input.put("bussinessShortName", bussinessName);
                input.put("bussinessName", bussinessName + patternName);

				Template template = cfg.getTemplate(templateName + ".ftl");
				template.process(input, consoleWriter);

                String tempName;
                switch (templateModel.getTemplateType()) {
                    case Constant.TemplateType.JSP:
                        tempName = CamelCaseUtils.toUnderlineName(bussinessName + patternName);
                        break;
                    case Constant.TemplateType.VO:
                        tempName = pattern.domainObjectName;
                        break;
                    case Constant.TemplateType.DTO:
                        tempName = pattern.domainObjectName;
                        break;
                    case Constant.TemplateType.DAO:
                        tempName = pattern.domainObjectName;
                        break;
                    case Constant.TemplateType.MODEL:
                        tempName = pattern.domainObjectName;
                        break;
                    case Constant.TemplateType.MAPPER:
                        tempName = pattern.domainObjectName;
                        break;
                    default:
                        tempName = bussinessName + patternName;
                        break;
                }

                if (pattern.writeFileFlag) {
					String path = "";
					if (StringUtils.isEmpty(pattern.fixPath)) {
						path = templateModel.getOutputPath() + tempName + templateModel.getSuffix() + templateModel.getExtension();
					} else {
						path = pattern.fixPath + tempName + templateModel.getSuffix() + templateModel.getExtension();
					}
                    //String path = templateModel.getOutputPath() + tempName + templateModel.getSuffix() + templateModel.getExtension();
                    Writer fileWriter = new FileWriter(new File(path));
                    try {
                        template.process(input, fileWriter);
                    } finally {
                        fileWriter.close();
                    }
                }
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private List<Map<String,Object>> convert(List<IntrospectedColumn> list, boolean isAllColumn, boolean hasSysField) {
		List<Map<String,Object>> fieldList = new ArrayList<>();
        Map<String, Field> fieldMap = pattern.fieldMap;
		for (IntrospectedColumn column : list) {
			if (!hasSysField && column.getActualColumnName().startsWith("sys_")) {
				break;
			}
			Map<String,Object> map = new HashMap<>();
			String propertyName = CamelCaseUtils.toCamelCase(column.getActualColumnName());
			map.put("columnName", column.getActualColumnName());
            map.put("sysFieldFlag", isSysField(column.getActualColumnName()));
            map.put("columnComment", column.getRemarks());
			map.put("propertyName", propertyName);
			map.put("jdbcType", String.valueOf(column.getJdbcType()));
			map.put("nullable", column.isNullable() ? "1" : "0");
			map.put("identity", column.isIdentity() ? "1" : "0");
			map.put("length", String.valueOf(column.getLength()));
			map.put("scale", String.valueOf(column.getScale()));

			FullyQualifiedJavaType type = fieldMap.get(propertyName).getType();
			map.put("javaType", type.getShortName());
			map.put("javaPackageName", type.getPackageName());
			map.put("primaryKeyFlag", isPrimaryKey(column.getActualColumnName(), pattern.table));
			List<ConstraintModel> lst = switchConstraint(column);
			if (isAllColumn) {
			    for (ConstraintModel c : lst) {
	                ConstraintModel importConstraint = this.findConstraint(c.getClassName(), importConstraintList);
	                if (importConstraint == null) {
	                    ConstraintModel cm = this.findConstraint(c.getClassName(), allConstraintList);
	                    importConstraintList.add(cm);
	                }
	            }
			}
			map.put("constraintList", lst);
			fieldList.add(map);
		}
		return fieldList;
	}
	
	private boolean isPrimaryKey(String columnName, IntrospectedTable table) {
	    boolean ret = false;
	    for (IntrospectedColumn column : table.getPrimaryKeyColumns()) {
	        if (column.getActualColumnName().equals(columnName)) {
	            ret = true;
	            break;
	        }
	    }
	    return ret;
	}

	private ConstraintModel findConstraint(String name, List<ConstraintModel> list) {
	    ConstraintModel ret = null;
	    for (ConstraintModel c : list) {
            if (name.equals(c.getClassName())) {
                ret = c;
                break;
            }
        }
	    return ret;
	}

    private IntrospectedColumn getIdentityColumn(IntrospectedTable table) {
        IntrospectedColumn ret = null;
        for (IntrospectedColumn column : table.getAllColumns()) {
            if (column.isIdentity()) {
                ret = column;
                break;
            }
        }
        return ret;
    }

	private List<ConstraintModel> switchConstraint(IntrospectedColumn column) {
		List<ConstraintModel> list = new ArrayList<>();
		if (!column.isNullable()) {
			if (column.getJdbcType() == Types.VARCHAR) {
				list.add(new ConstraintModel("NotEmpty", "", "@NotEmpty"));
			} else {
				list.add(new ConstraintModel("NotNull", "", "@NotNull"));
			}
		}
		if (column.getJdbcType() == Types.VARCHAR) {
			list.add(new ConstraintModel("MaxByteLength", "", "@MaxByteLength(value=" + column.getLength() + ")"));
		}
		return list;
	}

	private static final String[] SYS_FIELD_LIST = new String[]{"id", "createBy", "createDate", "updateBy", "updateDate", "remarks", "delFlag"};

	private boolean isSysField(String name) {
		boolean ret = false;
		for (String field : SYS_FIELD_LIST) {
			if (field.equals(name)) {
				ret = true;
				break;
			}
		}
		return ret;
	}

}