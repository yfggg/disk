package com.leadal.netdisk.generator.utils;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CodeGeneratorUtil {

    private static final String URL = "jdbc:mysql://localhost:3306/netdisk" +
            "?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    private static final String PERSONAL_DISK_MODULE_PATH = "D:\\System\\work\\leadal-netdisk\\netdisk-disk-personal";
    private static final String PERSONAL_DISK_PACKAGE = "com.leadal.netdisk.disk.personal";

    private static final String DISK_MODULE_PATH = "D:\\System\\work\\leadal-netdisk\\netdisk-disk";
    private static final String DISK_PACKAGE = "com.leadal.netdisk.disk";

    private static final String RESOURCE_MODULE_PATH = "D:\\System\\work\\leadal-netdisk\\netdisk-resource";
    private static final String RESOURCE_PACKAGE = "com.leadal.netdisk.resource";

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
                            builder.outputDir(PERSONAL_DISK_MODULE_PATH + "/src/main/java");
                            moduleType = "netdisk-disk-personal";
                            break;
                        case "netdisk-disk" :
                            builder.outputDir(DISK_MODULE_PATH + "/src/main/java");
                            moduleType = "netdisk-disk";
                            break;
                        case "netdisk-resource" :
                            builder.outputDir(RESOURCE_MODULE_PATH + "/src/main/java");
                            moduleType = "netdisk-resource";
                            break;
                    }
                })
                .packageConfig(builder -> {
                    switch (moduleType) {
                        case "netdisk-disk-personal" :
                            builder.parent(PERSONAL_DISK_PACKAGE).entity("model").mapper("dao").xml("dao.xml");
                            break;
                        case "netdisk-disk" :
                            builder.parent(DISK_PACKAGE).entity("model").mapper("dao").xml("dao.xml");
                            break;
                        case "netdisk-resource" :
                            builder.parent(RESOURCE_PACKAGE).entity("model").mapper("dao").xml("dao.xml");
                            break;
                    }
                })
                .strategyConfig((scanner, builder) -> builder.addInclude(getTables(scanner.apply("请输入表名，多个英文逗号分隔？所有输入 all")))
                        .controllerBuilder()
                        .enableRestStyle()
                        .enableHyphenStyle()
                        .entityBuilder().idType(IdType.ASSIGN_UUID)
                        .enableLombok().addTableFills()
                        .build()
                )
                .strategyConfig( builder -> builder.addTablePrefix("ndk_"))
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
