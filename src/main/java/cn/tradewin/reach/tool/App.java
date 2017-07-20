package cn.tradewin.reach.tool;

import cn.tradewin.reach.tool.core.AutoGenerator;
import cn.tradewin.reach.tool.core.AutoGeneratorOption;
import cn.tradewin.reach.tool.enums.DbType;
import cn.tradewin.reach.tool.pattern.ApiPattern;

public class App {

    public static void main(String[] args) {
        AutoGeneratorOption option = new AutoGeneratorOption();

        String osName = System.getProperties().getProperty("os.name");
        option.projectRootPackage = "com.mz.mycc";
        option.jdbcDriverJarPath = "/home/voctrals/.m2/repository/mysql/mysql-connector-java/5.1.40/mysql-connector-java-5.1.40.jar";
        option.rootPath = "/home/voctrals/IntelliJWorkSpace/oetcm/oetcm-service";
        option.dbType = DbType.MYSQL;
        // mysql
        option.jdbc.setDriverClass("com.mysql.jdbc.Driver");
        option.jdbc.setConnectionURL("jdbc:mysql://123.56.128.78/oetcm");
        option.jdbc.setUserId("xucheng");
        option.jdbc.setPassword("liunazihan1982");

//        option.jdbc.setDriverClass("com.mysql.jdbc.Driver");
//        option.jdbc.setConnectionURL("jdbc:mysql://101.201.147.35/dagger");
//        option.jdbc.setUserId("dagger");
//        option.jdbc.setPassword("dagger_password");

        //
        // oracle
        /*
        option.jdbc.setDriverClass("oracle.jdbc.driver.OracleDriver");
        option.jdbc.setConnectionURL("jdbc:oracle:thin:@192.168.0.49:1521:COMET1");
        option.jdbc.setUserId("hudc");
        option.jdbc.setPassword("hudc");
        */
        // postgreSQL
        /*
        option.jdbc.setDriverClass("org.postgresql.Driver");
        option.jdbc.setConnectionURL("jdbc:postgresql://192.168.1.118:5432/postgres");
        option.jdbc.setUserId("postgres");
        option.jdbc.setPassword("postgres");
        */
        AutoGenerator generator = new AutoGenerator(option);
//
//        ViewPattern viewPattern = new ViewPattern();
//        viewPattern.bussinessName = "Book";
//        viewPattern.tableName = "book";
//        viewPattern.domainObjectName = "Book";
//        viewPattern.writeFileFlag = false;
//        viewPattern.outputSqlPartOnly = true;
//        viewPattern.mapperUsePatternNameFlag = false;
//        viewPattern.hasPagination = true;
//        viewPattern.createCommonTemplateFlag = true;
//        viewPattern.hasEditOperation = true;
//        generator.addPattern(viewPattern);

        ApiPattern pattern = new ApiPattern();
        //pattern.fixPath = "/home/voctrals/work/create/";
        //pattern.disablePatternName = true;
        pattern.bussinessName = "PublicCourseCollection";
        pattern.tableName = "biz_public_course_collection";
        pattern.domainObjectName = "PublicCourseCollection";
        pattern.writeFileFlag = true;
        //pattern.autoColumnName = "id";

        pattern.outputSqlPartOnly = true;
        pattern.createCommonTemplateFlag = true;
        generator.addPattern(pattern);

        generator.execute();
    }
}
