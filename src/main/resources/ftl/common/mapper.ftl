<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${package.daoPackage}.${name}Dao">
    <resultMap id="BaseResultMap" type="${name}">
        <#list allColumnWithSysFieldList as field>
            <#if field['columnName'] == 'id'>
                <id column="${field['columnName']}" property="${field['propertyName']}" />
            <#else>
                <result column="${field['columnName']}" property="${field['propertyName']}" />
            </#if>
        </#list>
    </resultMap>
    <sql id="Example_Where_Clause">
        <where>
            <#list allColumnList as field>
            <if test="${field['propertyName']} != null and ${field['propertyName']} != '' ">
                AND ${field['columnName']} = ${r"#{"}${field['propertyName']}${"}"}
            </if>
            </#list>
        </where>
    </sql>
    <sql id="Base_Column_List">
        <#list allColumnWithSysFieldList as field>
            ${field['columnName']}<#if field_has_next>,</#if>
        </#list>
    </sql>
    <select id="selectAllList" parameterType="${name}" resultMap="BaseResultMap">
        select
        <include refid="Base_Column_List" />
        from ${tableName}
        <if test="_parameter != null">
            <include refid="Example_Where_Clause" />
        </if>
    </select>
    
    <select id="selectByPrimaryKey" parameterType="${name}" resultMap="BaseResultMap">
        select 
        <include refid="Base_Column_List" />
        from ${tableName}
        <where>
            <#list primaryKeyColumnList as field>
            <if test="${field['propertyName']} != null and ${field['propertyName']} != ''">
                AND ${field['columnName']} = ${r"#{"}${field['propertyName']}${"}"}
            </if>
            </#list>
        </where>
    </select>

    <#if hasIdentity == '1'>
    <insert id="insert" parameterType="${name}" useGeneratedKeys="true" keyProperty="${name}.${identityPropertyName}">
    <#else>
    <insert id="insert" parameterType="${name}">
    </#if>
        insert into ${tableName}
        (
        <#list allColumnWithSysFieldList as field>
            <#if field['identity'] != '1'>
            ${field['columnName']}<#if field_has_next>,</#if>
            </#if>
        </#list>
        )
        values (
        <#list allColumnWithSysFieldList as field>
            <#if field['identity'] != '1'>
            ${r"#{"}${field['propertyName']}${"}"}<#if field_has_next>,</#if>
            </#if>
        </#list>
        )
    </insert>

    <update id="updateByPrimaryKey" parameterType="${name}">
        update ${tableName}
        <set>
        <#list nonPrimaryKeyColumnList as field>
            ${field['columnName']} = ${r"#{"}${field['propertyName']}${"}"}<#if field_has_next>,</#if>
        </#list>
        </set>
        <where>
            <#list primaryKeyColumnList as field>
            <if test="${field['propertyName']} != null and ${field['propertyName']} != ''">
                AND ${field['columnName']} = ${r"#{"}${field['propertyName']}${"}"}
            </if>
            </#list>
        </where>
    </update>

    <update id="updateByPrimaryKeySelective" parameterType="${name}">
        update ${tableName}
        <set>
        <#list nonPrimaryKeyColumnWithSysFieldList as field>
            <if test="${field['propertyName']} != null and ${field['propertyName']} != ''">
                ${field['columnName']} = ${r"#{"}${field['propertyName']}${"}"},
            </if>
        </#list>       
        </set>
        <where>
            <#list primaryKeyColumnWithSysFieldList as field>
            <if test="${field['propertyName']} != null and ${field['propertyName']} != ''">
                AND ${field['columnName']} = ${r"#{"}${field['propertyName']}${"}"}
            </if>
            </#list>
        </where>
    </update>

    <delete id="deleteByPrimaryKey" parameterType="${name}">
        delete from ${tableName}
        <where>
            <#list primaryKeyColumnWithSysFieldList as field>
            <if test="${field['propertyName']} != null and ${field['propertyName']} != ''">
                AND ${field['columnName']} = ${r"#{"}${field['propertyName']}${"}"}
            </if>
            </#list>
        </where>
    </delete>

    <insert id="insertList" parameterType="java.util.List">
        insert into ${tableName}
        (
    <#list allColumnWithSysFieldList as field>
        <#if field['identity'] != '1'>
        ${field['columnName']}<#if field_has_next>,</#if>
        </#if>
    </#list>
        )
        values
        <foreach collection="list" item="item" index="index" separator=",">
        (
    <#list allColumnWithSysFieldList as field>
        <#if field['identity'] != '1'>
        ${r"#{item."}${field['propertyName']}${"}"}<#if field_has_next>,</#if>
        </#if>
    </#list>
        )
        </foreach>

    </insert>
</mapper>