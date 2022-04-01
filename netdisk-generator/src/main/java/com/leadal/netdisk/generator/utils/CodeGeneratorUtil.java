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
    private static final String DISK_MODULE_PATH = "D:\\System\\work\\netdisk-server\\netdisk-disk";
    private static final String PERSONAL_MODULE_PATH = "D:\\System\\work\\netdisk-server\\netdisk-personal";


    public static void main(String[] args) {
        FastAutoGenerator.create(URL, USERNAME, PASSWORD)
                .globalConfig((scanner, builder) -> builder.author(scanner.apply("请输入作者名称？"))
                        .enableSwagger()
                        .fileOverride()
                        .outputDir(DISK_MODULE_PATH + "/src/main/java")
                )
                .packageConfig((scanner,builder) -> builder.parent(scanner.apply("请输入包名？"))
                        .entity("model.entity")
                        .mapper("dao")
                        .xml("dao.xml")
                )
                .strategyConfig((scanner, builder) -> builder.addInclude(getTables(scanner.apply("请输入表名，多个英文逗号分隔？所有输入 all")))
                        .controllerBuilder()
                        .enableRestStyle()
                        .enableHyphenStyle()
                        .entityBuilder().idType(IdType.ASSIGN_ID)
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
