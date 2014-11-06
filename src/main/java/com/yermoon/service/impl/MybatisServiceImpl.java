package com.yermoon.service.impl;

import com.yermoon.dao.MybatisDao;
import com.yermoon.entity.DbSrcEntity;
import com.yermoon.service.DataBaseService;
import com.yermoon.service.MybatisService;
import com.yermoon.utils.CamelCaseUtils;
import com.yermoon.utils.ConvertUtils;
import com.yermoon.vo.DataBase;
import com.yermoon.vo.JColumn;
import com.yermoon.vo.JTable;
import org.apache.commons.lang.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Mybatis工具集的业务处理默认实现类
 *
 * @author wangqing
 * @since 14-4-16 下午2:18
 */
@Service("mybatisService")
public class MybatisServiceImpl implements MybatisService {
    private final Logger log = LoggerFactory.getLogger(MybatisServiceImpl.class);
    @Resource
    private MybatisDao mybatisDao;
    @Resource
    private DataBaseService dataBaseService;

    @Override
    public void createDbSrc(DbSrcEntity dbSrcEntity) throws Exception {
        mybatisDao.insertDbsrc(dbSrcEntity);
    }

    @Override
    public List<DbSrcEntity> findAllDbSrc() throws Exception {
        return mybatisDao.findAll();
    }

    @Override
    public void deleteDbSrc(int id) throws Exception {
        mybatisDao.deleteById(id);
    }

    @Override
    public List<String> findAllTables(int dbsrcId) throws Exception {
        DbSrcEntity dbsrc = mybatisDao.findById(dbsrcId);
        if (dbsrc == null) throw new RuntimeException("未查询到数据源");
        DataBase db = new DataBase();
        db.setDataBaseType(ConvertUtils.getDbTypeByCode(dbsrc.getDbType()));
        db.setJdbcUrl(dbsrc.getJdbcUrl());
        db.setUserName(dbsrc.getUserName());
        db.setPassword(dbsrc.getPassword());
        return dataBaseService.getTableNames(db);
    }

    @Override
    public DbSrcEntity getDbSrcEntity(int dbsrcId) throws Exception {
        return mybatisDao.findById(dbsrcId);
    }

    @Override
    public void createConfig(DbSrcEntity dataBase, String table, String path, String packagePath) throws Exception {
        DataBase db=new DataBase();
        db.setDataBaseType(ConvertUtils.getDbTypeByCode(dataBase.getDbType()));
        db.setJdbcUrl(dataBase.getJdbcUrl());
        db.setUserName(dataBase.getUserName());
        db.setPassword(dataBase.getPassword());
        JTable jTable = dataBaseService.getTable(db, table);
        String entityName = CamelCaseUtils.toCamelCase(table);
        createXmlFile(table, entityName, path, packagePath, jTable);
        createJavaFile(table, entityName, path, packagePath, jTable);
    }

    private void createJavaFile(String table, String entityName, String path,
                                String packagePath, JTable jTable) {
        Map<String, JColumn> columnMap = jTable.getjColumnMap();
        String entityBeanName = entityName + "Entity";
        entityBeanName = entityBeanName.replaceFirst(entityBeanName.substring(0, 1), entityName.substring(0, 1).toUpperCase());
        String dtoBeanName = entityBeanName.replaceFirst("Entity", "Dto");
        String path2 = path + File.separatorChar + entityBeanName + ".java";
        String path3 = path + File.separatorChar + dtoBeanName + ".java";
        File file = new File(path2);
        file.deleteOnExit();
        File file2 = new File(path3);
        file2.deleteOnExit();
        StringBuilder sb1 = new StringBuilder();
        sb1.append("package ").append(packagePath).append(";\r\n\r\n");
        List<String> importList = new ArrayList<String>();
        importList.add("import java.io.Serializable;");
        String dateImp = "import java.util.Date;";
        String arrayImp = "import java.sql.Array;";
        String blobImp = "import java.sql.Blob;";
        String clobImp = "import java.sql.Clob;";
        String timeStampImp = "import java.sql.Timestamp;";
        String bigDecimalImp = "import java.math.BigDecimal;";
        String timeImp = "import java.sql.Time;";
        StringBuilder entySb = new StringBuilder();
        StringBuilder dtoSb = new StringBuilder();
        StringBuilder entySb3 = new StringBuilder();
        StringBuilder dtoSb3 = new StringBuilder();
        entySb.append("public class ").append(entityBeanName).append(" implements Serializable {\r\n");
        dtoSb.append("public class ").append(dtoBeanName).append(" implements Serializable {\r\n");
        entySb.append("\tprivate static final long serialVersionUID = 1L;\r\n");
        dtoSb.append("\tprivate static final long serialVersionUID = 1L;\r\n");
        for (Map.Entry<String, JColumn> entry : columnMap.entrySet()) {
            String javaType = entry.getValue().getJavaType();
            String colJavaName = entry.getValue().getJavaName();
            String col = entry.getValue().getColumnName();
            entySb.append("\tprivate ").append(javaType).append(" ").append(colJavaName).append(";");
            entySb.append("//").append(col).append("\r\n");
            if (!entry.getValue().isPkColumn()) {
                dtoSb.append("\tprivate ").append(javaType).append(" ").append(colJavaName).append(";");
                dtoSb.append("//").append(col).append("\r\n");
            }
            if (javaType.equals("Date") && !importList.contains(dateImp)) {
                importList.add(dateImp);
            } else if (javaType.equals("Array") && !importList.contains(arrayImp)) {
                importList.add(arrayImp);
            } else if (javaType.equals("Blob") && !importList.contains(blobImp)) {
                importList.add(blobImp);
            } else if (javaType.equals("Clob") && !importList.contains(clobImp)) {
                importList.add(clobImp);
            } else if (javaType.equals("Timestamp") && !importList.contains(timeStampImp)) {
                importList.add(timeStampImp);
            } else if (javaType.equals("BigDecimal") && !importList.contains(bigDecimalImp)) {
                importList.add(bigDecimalImp);
            } else if (javaType.equals("Time") && !importList.contains(timeImp)) {
                importList.add(timeImp);
            }
            String upperJavaName = colJavaName.replaceFirst(colJavaName.substring(0, 1), colJavaName.substring(0, 1).toUpperCase());
            entySb3.append("\tpublic ").append(javaType).append(" get").append(upperJavaName).append("() {\r\n");
            entySb3.append("\t\treturn ").append(colJavaName).append(";\r\n");
            entySb3.append("\t}\r\n\r\n");
            entySb3.append("\tpublic void set").append(upperJavaName).append("(").append(javaType).append(" ").append(colJavaName).append(") {\r\n");
            entySb3.append("\t\tthis.").append(colJavaName).append(" = ").append(colJavaName).append(";\r\n");
            entySb3.append("\t}\r\n\r\n");
            if (!entry.getValue().isPkColumn()) {
                dtoSb3.append("\tpublic ").append(javaType).append(" get").append(upperJavaName).append("() {\r\n");
                dtoSb3.append("\t\treturn ").append(colJavaName).append(";\r\n");
                dtoSb3.append("\t}\r\n\r\n");
                dtoSb3.append("\tpublic void set").append(upperJavaName).append("(").append(javaType).append(" ").append(colJavaName).append(") {\r\n");
                dtoSb3.append("\t\tthis.").append(colJavaName).append(" = ").append(colJavaName).append(";\r\n");
                dtoSb3.append("\t}\r\n\r\n");
            }
        }
        for (String impStr : importList) {
            sb1.append(impStr).append("\r\n");
        }
        sb1.append("\r\n");
        entySb.append("\r\n");
        dtoSb.append("\r\n");
        DateFormatUtils.format(new java.util.Date(), "yyyy-MM-dd'T'HH:mm:ss");
        sb1.append("/**\r\n" + " * 对应数据库表名：").append(table).append("\r\n")
                .append(" *\r\n").append(" * @since ")
                .append(DateFormatUtils.format(new java.util.Date(), "yyyy-MM-dd HH:mm:ss"))
                .append("\r\n").append(" */\r\n");
        entySb3.append("}");
        dtoSb3.append("}");
        OutputStreamWriter fileWriter;
        OutputStreamWriter fileWriterDto;

        try {
            fileWriter = new OutputStreamWriter(new FileOutputStream(file),"UTF-8");
            fileWriterDto = new OutputStreamWriter(new FileOutputStream(file),"UTF-8");
            fileWriter.write(sb1.toString());
            fileWriter.write(entySb.toString());
            fileWriter.write(entySb3.toString());
            fileWriter.flush();
            fileWriter.close();
            fileWriterDto.write(sb1.toString());
            fileWriterDto.write(dtoSb.toString());
            fileWriterDto.write(dtoSb3.toString());
            fileWriterDto.flush();
            fileWriterDto.close();
        } catch (IOException e) {
            log.error("生成Java文件异常", e);
            throw new RuntimeException("生成Java文件异常");
        }
    }

    private void createXmlFile(String table, String entityName, String path,
                               String packagePath, JTable jTable)
            throws RuntimeException {
        Set<String> tableStructList = jTable.getjColumnMap().keySet();
        Map<String, JColumn> columnMap = jTable.getjColumnMap();
        String pkName = jTable.getPkColumnName();
        String pkJavaName = jTable.getPkJavaName();
        String mapName = entityName.replaceFirst(entityName.substring(0, 1), entityName.substring(0, 1).toUpperCase());
        String path2 = path + File.separatorChar + mapName + "Mapper.xml";
        File file = new File(path2);
        file.deleteOnExit();
        String s1 = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\r\n";
        String s2 = "<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\" >\r\n";
        String z22 = "<!-- Created with MTool.  -->\r\n";
        String s3 = "<mapper namespace=\"" + packagePath + "\">\r\n\r\n";
        String entityBeanName = entityName + "Entity";
        String resultMapName = entityName + "Map";
        String s4 = "\t<resultMap id=\"" + resultMapName + "\" type=\"" + entityBeanName + "\">\r\n";
        StringBuilder sb1 = new StringBuilder();
        for (Map.Entry<String, JColumn> entry : columnMap.entrySet()) {
            String col = entry.getValue().getColumnName();
            String javaName = entry.getValue().getJavaName();
            String jdbcType = entry.getValue().getJdbcType();
            String s5 = "\t\t<id property=\"" + javaName + "\" column=\"" + col + "\" jdbcType=\"" + jdbcType + "\"/>\r\n";
            sb1.append(s5);
        }
        String s6 = "\t</resultMap>\r\n\r\n";
        String z1 = "\t<!-- 所有字段  -->\r\n";
        String s7 = "\t<sql id=\"sqlColumns\">\r\n";
        StringBuilder sb2 = new StringBuilder();
        String lenFlag = "";

        int i = 0;
        for (String col : tableStructList) {
            if (i == 0) {
                sb2.append("\t\t");
                sb2.append(col);
            } else if("".equals(lenFlag)){
                sb2.append("\t\t");
                sb2.append(col);
            }else {
                sb2.append(col);
            }
            lenFlag += col;
            if ((i + 1) != tableStructList.size()) {
                sb2.append(", ");
            }
            if (lenFlag.length() > 70 || (i + 1) == tableStructList.size()) {
                sb2.append("\r\n");
                lenFlag = "";
            }
            i++;
        }
        String s8 = "\t</sql>\r\n\r\n";
        String z2 = "\t<!-- 单条新增数据  -->\r\n";
        StringBuilder s9 = new StringBuilder("\t<insert id=\"insert\" parameterType=\"" + entityBeanName + "\">\r\n");
        s9.append("\t\tinsert into ").append(table).append("\r\n");
        s9.append("\t\t<include refid=\"sqlColumns\"/>\r\n");
        s9.append("\t\tvalues(\r\n");
        int ii = 0;
        for (Map.Entry<String, JColumn> entry : columnMap.entrySet()) {
            JColumn col = entry.getValue();
            s9.append("\t\t");
            s9.append("#{").append(col.getColumnName()).append(",jdbcType=").append(col.getJdbcType()).append("}");
            if ((ii + 1) != tableStructList.size()) {
                s9.append(",\r\n");
            } else s9.append("\r\n");
            ii++;
        }
        s9.append("\t\t)\r\n");
        s9.append("\t</insert>\r\n\r\n");
        String z3 = "\t<!-- 根据主键删除数据  -->\r\n";
        StringBuilder s10 = new StringBuilder("\t<delete id=\"deleteByPrimaryKey\" parameterType=\"java.lang.Integer\">\r\n");
        s10.append("\t\tdelete from ").append(table).append("\r\n");
        s10.append("\t\twhere ").append(jTable.getPkColumnName()).append(" = ");
        s10.append("#{").append(jTable.getPkJavaName()).append(",jdbcType=").append(columnMap.get(jTable.getPkColumnName()).getJdbcType()).append("}\r\n");
        s10.append("\t</delete>\r\n\r\n");
        String z4 = "\t<!-- 根据主键批量删除数据  -->\r\n";
        StringBuilder s11 = new StringBuilder("\t<delete id=\"deleteBatch\" parameterType=\"list\">\r\n");
        s11.append("\t\tdelete from ").append(table).append("\r\n");
        s11.append("\t\twhere ").append(jTable.getPkColumnName()).append(" in\r\n");
        s11.append("\t\t<foreach item=\"item\" index=\"index\" collection=\"list\" open=\"(\" separator=\",\" close=\")\">\r\n");
        s11.append("\t\t\t#{item}\r\n");
        s11.append("\t\t</foreach>\r\n");
        s11.append("\t</delete>\r\n\r\n");
        String z5 = "\t<!-- 根据主键全量更新  -->\r\n";
        StringBuilder s12 = new StringBuilder("\t<update id=\"updateByPrimaryKey\" parameterType=\"" + entityBeanName + "\">\r\n");
        s12.append("\t\tupdate ").append(table).append("\r\n");
        s12.append("\t\tset ");
        int flag = 0;
        for (Map.Entry<String, JColumn> entry : columnMap.entrySet()) {
            String col = entry.getValue().getColumnName();
            String jdbcType = entry.getValue().getJdbcType();
            if (entry.getValue().isPkColumn()) {
                flag++;
                continue;
            }
            if (flag == 0) {
                s12.append(col).append(" = ").
                        append("#{").append(CamelCaseUtils.toCamelCase(col)).
                        append(",jdbcType=").append(jdbcType).append("}");
            } else {
                s12.append("\t\t").append(col).append(" = ").append("#{").
                        append(CamelCaseUtils.toCamelCase(col)).
                        append(",jdbcType=").append(jdbcType).append("}");
            }
            if ((flag + 1) != tableStructList.size()) {
                s12.append(",\r\n");
            } else {
                s12.append("\r\n");
            }
            flag++;
        }
        s12.append("\t\twhere ").append(pkName).append(" = ");
        s12.append("#{").append(CamelCaseUtils.toCamelCase(pkName)).append("}\r\n");
        s12.append("\t</update>\r\n\r\n");
        String z6 = "\t<!-- 根据主键有选择的更新  -->\r\n";
        StringBuilder s13 = new StringBuilder("\t<update id=\"updateSegmentByPrimaryKey\" parameterType=\"" + entityBeanName + "\">\r\n");
        s13.append("\t\tupdate ").append(table).append("\r\n");
        s13.append("\t\t<set>\r\n");
        String z7 = "\t<!-- 根据主键查询  -->\r\n";
        StringBuilder s14 = new StringBuilder("\t<select id=\"selectByPrimaryKey\" parameterType=\"java.lang.Integer\" resultMap=\"" + resultMapName + "\">\r\n");
        s14.append("\t\tselect\r\n");
        s14.append("\t\t<include refid=\"sqlColumns\"/>\r\n");
        s14.append("\t\tfrom ").append(table).append("\r\n");
        s14.append("\t\twhere ").append(pkName).append(" = ");
        s14.append("#{").append(pkJavaName).append("}\r\n");
        s14.append("\t</select>\r\n\r\n");
        String z8 = "\t<!-- 根据传入的字段值查询  -->\r\n";
        StringBuilder s15 = new StringBuilder("\t<select id=\"selectByCriteria\" parameterType=\"" + entityBeanName + "\" resultMap=\"" + resultMapName + "\">\r\n");
        s15.append("\t\tselect\r\n");
        s15.append("\t\t<include refid=\"sqlColumns\"/>\r\n");
        s15.append("\t\tfrom ").append(table).append("\r\n");
        s15.append("\t\t<where>\r\n");
        int count = 0;
        for (Map.Entry<String, JColumn> entry : columnMap.entrySet()) {
            count++;
            String col = entry.getValue().getColumnName();
            String javaName = entry.getValue().getJavaName();
            String jdbcType = entry.getValue().getJdbcType();
            if (entry.getValue().isPkColumn()) continue;
            s13.append("\t\t\t<if test=\"").append(javaName).append(" != null\" >\r\n");
            s13.append("\t\t\t\t").append(col).append(" = ").append("#{").
                    append(pkJavaName).
                    append(",jdbcType=").append(jdbcType).append("}");
            if (count != columnMap.size()) {
                s13.append(",");
            }
            s13.append("\r\n");
            s13.append("\t\t\t</if>\r\n");
            s15.append("\t\t\t<if test=\"").append(javaName).append(" != null\" >\r\n");
            s15.append("\t\t\t\t").append(col).append(" = ").append("#{").append(javaName).
                    append(",jdbcType=").append(jdbcType).append("}");
            if (count != columnMap.size()) {
                s15.append(",");
            }
            s15.append("\r\n");
            s15.append("\t\t\t</if>\r\n");
        }
        s13.append("\t\t</set>\r\n");
        s13.append("\t\twhere ").append(pkName).append(" = ");
        s13.append("#{").append(pkJavaName).append("}\r\n");
        s13.append("\t</update>\r\n\r\n");
        s15.append("\t\t</where>\r\n");
        s15.append("\t</select>\r\n");
        s15.append("</mapper>");
//        FileWriter fileWriter = null;
        OutputStreamWriter fileWriter;
        try {
//            fileWriter = new FileWriter(file);
            fileWriter = new OutputStreamWriter(new FileOutputStream(file),"UTF-8");
            fileWriter.write(s1);
            fileWriter.write(s2);
            fileWriter.write(z22);
            fileWriter.write(s3);
            fileWriter.write(s4);
            fileWriter.write(sb1.toString());
            fileWriter.write(s6);
            fileWriter.write(z1);
            fileWriter.write(s7);
            fileWriter.write(sb2.toString());
            fileWriter.write(s8);
            fileWriter.write(z2);
            fileWriter.write(s9.toString());
            fileWriter.write(z3);
            fileWriter.write(s10.toString());
            fileWriter.write(z4);
            fileWriter.write(s11.toString());
            fileWriter.write(z5);
            fileWriter.write(s12.toString());
            fileWriter.write(z6);
            fileWriter.write(s13.toString());
            fileWriter.write(z7);
            fileWriter.write(s14.toString());
            fileWriter.write(z8);
            fileWriter.write(s15.toString());
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException ioe) {
            log.error("创建配置文件异常", ioe);
            throw new RuntimeException("创建配置文件异常，文件操作异常");
        }
    }

}
