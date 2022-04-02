package com.leadal.netdisk.generator.utils;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CodeGeneratorUtil {

    private static final String URL = "jdbc:mysql://localhost:3306/netdisk" +
            "?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    // TODO
    private static final String PERSONAL_MODULE_PATH = "D:\\System\\work\\leadal-netdisk\\netdisk-disk-personal";
    private static final String PERSONAL_PACKAGE = "com.leadal.netdisk.disk.personal";

    private static String moduleType;

    public static void main(String[] args) {
        FastAutoGenerator.create(URL, USERNAME, PASSWORD)
                .globalConfig((scanner, builder) -> builder.author(scanner.apply("请输入作者名称？"))
                        .enableSwagger()
                        .fileOverride()
                )
                .globalConfig((scanner, builder) -> {
                    switch (scanner.apply("请输入模块名称？")) {
                        case "netdisk-disk-personal" :
                            builder.outputDir(PERSONAL_MODULE_PATH + "/src/main/java");
                            moduleType = "disk-personal";
                            break;
                    }
                })
                .packageConfig(builder -> {
                    switch (moduleType) {
                        case "disk-personal" :
                            builder.parent(PERSONAL_PACKAGE).entity("model").mapper("dao").xml("dao.xml");
                            break;
                    }
                })
                .strategyConfig((scanner, builder) -> builder.addInclude(getTables(scanner.apply("请输入表名，多个英文逗号分隔？所有输入 all")))
                        .controllerBuilder()
                        .enableRestStyle()
                        .enableHyphenStyle()
                        .entityBuilder()
                        .enableLombok().addTableFills()
                        .build()
                )
                .templateConfig(builder -> builder
                        .entity("/templates/entity.java")
                        .controller("/templates/controller.java")
                        .build())
                .strategyConfig(builder -> builder.mapperBuilder().enableMapperAnnotation().build())
                .execute();
    }

    protected static List<String> getTables(String tables) {
        return "all".equals(tables) ? Collections.emptyList() : Arrays.asList(tables.split(","));
    }

}
