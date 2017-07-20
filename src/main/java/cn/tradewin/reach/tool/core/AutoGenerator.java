package cn.tradewin.reach.tool.core;

import cn.tradewin.reach.tool.model.PackageModel;
import cn.tradewin.reach.tool.pattern.PagePattern;
import org.apache.commons.lang3.StringUtils;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.config.*;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AutoGenerator {

	private static Map<String, IntrospectedTable> tableInfoMap = new HashMap<>();

	private static Map<String, Map<String, Field>> fieldInfoMapList = new HashMap<>();

	private List<PagePattern> patternList = new ArrayList<>();

	private AutoGeneratorOption option;

    public AutoGenerator(AutoGeneratorOption option) {
        this.option = option;
    }

	public void execute() {
		try {
			Configuration config = new Configuration();
			config.addClasspathEntry(this.option.jdbcDriverJarPath);
			Context context = new Context(null);
			context.setId("DB2Tables");
			context.setTargetRuntime("MyBatis3");

			// jdbc
			context.setJdbcConnectionConfiguration(this.option.jdbc);

			// comment
			context.setCommentGeneratorConfiguration(commentGenerator());

			// plugin
			context.addPluginConfiguration(plugin("org.mybatis.generator.plugins.SerializablePlugin"));
			context.addPluginConfiguration(plugin("org.mybatis.generator.plugins.ToStringPlugin"));
			context.addPluginConfiguration(plugin("cn.tradewin.reach.tool.plugins.CustomModelPlugin"));

			// model
			context.setJavaModelGeneratorConfiguration(javaModelGenerator());

			// sql map
			context.setSqlMapGeneratorConfiguration(sqlMapGenerator());

			// table
            for (PagePattern pattern : patternList) {
                addTable(context, pattern.tableName, pattern.domainObjectName, pattern.autoColumnName);
            }

			config.addContext(context);

			List<String> warnings = new ArrayList<>();
			boolean overwrite = true;
			DefaultShellCallback callback = new DefaultShellCallback(overwrite);
			MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
			myBatisGenerator.generate(null);

            for (PagePattern pattern : patternList) {
                if (pattern.packageModel == null) {
                    pattern.packageModel = new PackageModel(option.rootPath, option.projectRootPackage);
                }
                pattern.table = tableInfoMap.get(pattern.tableName);
                pattern.fieldMap = fieldInfoMapList.get(pattern.tableName);

                TemplateGenerator generator = new TemplateGenerator(pattern);
                generator.execute();
            }
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

    public static void add(String tableName, Field field, IntrospectedTable element) {
        tableInfoMap.put(tableName, element);
        String fieldName = field.getName();
        Map<String, Field> fieldInfoMap;
        if (fieldInfoMapList.containsKey(tableName)) {
            fieldInfoMap = fieldInfoMapList.get(tableName);
        } else {
            fieldInfoMap = new HashMap<>();
        }
        fieldInfoMap.put(fieldName, field);
        fieldInfoMapList.put(tableName, fieldInfoMap);
    }

	public void addPattern(PagePattern pattern) {
		patternList.add(pattern);
	}

	private JavaModelGeneratorConfiguration javaModelGenerator() {
		JavaModelGeneratorConfiguration javaModelGenerator = new JavaModelGeneratorConfiguration();
		javaModelGenerator.setTargetPackage("cn.tradewin.reach.tool.test");
		javaModelGenerator.setTargetProject("src/main/java");
		javaModelGenerator.addProperty("enableSubPackages", "true");
		javaModelGenerator.addProperty("trimStrings", "false");
		return javaModelGenerator;
	}

	private CommentGeneratorConfiguration commentGenerator() {
		CommentGeneratorConfiguration config = new CommentGeneratorConfiguration();
		config.addProperty("suppressDate", "true");
		config.addProperty("suppressAllComments", "true");
		return config;
	}

	private void addTable(Context context, String tableName, String domainObjectName, String autoColumnName) {
		TableConfiguration config = new TableConfiguration(context);
		config.setTableName(tableName);
		config.setDomainObjectName(domainObjectName);
		config.setCountByExampleStatementEnabled(false);
		config.setUpdateByExampleStatementEnabled(false);
		config.setDeleteByExampleStatementEnabled(false);
		config.setSelectByExampleStatementEnabled(false);
        if (!StringUtils.isEmpty(autoColumnName)) {
            GeneratedKey generatedKey = new GeneratedKey(autoColumnName, "MySql", true, "");
            config.setGeneratedKey(generatedKey);
        }

		context.addTableConfiguration(config);
	}

	private PluginConfiguration plugin(String configurationType) {
		PluginConfiguration plugin = new PluginConfiguration();
		plugin.setConfigurationType(configurationType);
		return plugin;
	}

    private JavaModelGeneratorConfiguration javaDtoGenerator() {
		JavaModelGeneratorConfiguration javaModelGenerator = new JavaModelGeneratorConfiguration();
		javaModelGenerator.setTargetPackage("com.yingootech.modeling.web.dto");
		javaModelGenerator.setTargetProject("src/main/java");
		javaModelGenerator.addProperty("enableSubPackages", "true");
		javaModelGenerator.addProperty("trimStrings", "false");
		javaModelGenerator.addProperty("rootClass", "com.yingootech.modeling.web.dto.BaseDto");
		return javaModelGenerator;
	}

    private SqlMapGeneratorConfiguration sqlMapGenerator() {
		SqlMapGeneratorConfiguration sqlMapGenerator = new SqlMapGeneratorConfiguration();
		sqlMapGenerator.setTargetPackage("sql");
		sqlMapGenerator.setTargetProject("src/main/resources");
		sqlMapGenerator.addProperty("enableSubPackages", "true");
		return sqlMapGenerator;
	}

    private JavaClientGeneratorConfiguration javaClientGenerator() {
		JavaClientGeneratorConfiguration javaClientGenerator = new JavaClientGeneratorConfiguration();
		javaClientGenerator.setConfigurationType("XMLMAPPER");
		javaClientGenerator.setTargetPackage("com.yingootech.modeling.web.test.dao");
		javaClientGenerator.setTargetProject("src/main/java");
		javaClientGenerator.addProperty("enableSubPackages", "true");
		return javaClientGenerator;
	}

}
