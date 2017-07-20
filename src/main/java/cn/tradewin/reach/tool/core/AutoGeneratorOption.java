package cn.tradewin.reach.tool.core;

import cn.tradewin.reach.tool.enums.DbType;
import org.mybatis.generator.config.JDBCConnectionConfiguration;

/**
 * Created by hudc on 2016/8/16.
 */
public class AutoGeneratorOption {
    public String rootPath;

    public String jdbcDriverJarPath;

    public String projectRootPackage;

    public DbType dbType;

    public JDBCConnectionConfiguration jdbc = new JDBCConnectionConfiguration();
}
