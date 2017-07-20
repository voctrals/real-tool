<!DOCTYPE html>

<html xmlns:th="https://www.thymeleaf.org" xmlns:layout="http://www.ultraq.net.nz/thymeleaf/layout"
      layout:decorator="fragments/sbadmin_template">
<head>

</head>
<body>
<!-- Main Content -->
<div class="container-fluid" layout:fragment="content">
    <div class="row">
        <div class="col-lg-12">
            <h1 class="page-header">${bussinessName}</h1>
        </div>

        <form th:action="${"@{/"}${bussinessName}/save${"}"}" th:object="${r'${form}'}" method="post">
            <div th:if="${r"${#fields.hasErrors('global')}"}" class="alert alert-danger" role="alert">
                <ul>
                    <li th:each="err : ${r"${#fields.hasErrors('global')}"}" th:text="${r'${err}'}">Input is incorrect</li>
                </ul>
            </div>
    <#list allColumnList as field>
        <#assign currentField="${name?uncap_first}Vo.${field['propertyName']}">
        <#if field['identity'] == '1'>
            <th:block th:if="${r'*{updatePageFlag}'}">
                <div th:class="${r"${#fields.hasErrors('"}${currentField}${r"')}"} ? 'form-group has-error' : 'form-group' ">
                    <label for="${field['propertyName']}">${field['propertyName']}</label>
                    <input type="text" id="${field['propertyName']}" th:field="${"*{"}${currentField}${"}"}" class="form-control" th:readonly="true" />
                </div>
                <span class="help-block" th:if="${r"${#fields.hasErrors('"}${currentField}${r"')}"}" th:errors="${"*{"}${currentField}${"}"}"></span>
            </th:block>
        <#else>
            <div th:class="${r"${#fields.hasErrors('"}${currentField}${r"')}"} ? 'form-group has-error' : 'form-group' ">
                <label for="${field['propertyName']}">${field['propertyName']}</label>
                <#if field['javaType'] == 'Date'>
                    <div class="input-group date col-md-3">
                        <input type="text" id="${field['propertyName']}" th:field="${"*{"}${currentField}${"}"}" class="form-control" /><span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
                    </div>
                <#else>
                    <#if field['primaryKeyFlag']>
                        <input type="text" id="${field['propertyName']}" th:field="${"*{"}${currentField}${"}"}" class="form-control" th:readonly="${r'*{updatePageFlag}'}" />
                    <#else>
                        <input type="text" id="${field['propertyName']}" th:field="${"*{"}${currentField}${"}"}" class="form-control" />
                    </#if>
                </#if>
                <span class="help-block" th:if="${r"${#fields.hasErrors('"}${currentField}${r"')}"}" th:errors="${"*{"}${currentField}${"}"}"></span>
            </div>
        </#if>
    </#list>
            <input type="hidden" th:field="*{updatePageFlag}" />
            <button type="submit" class="btn btn-default">save</button>
        </form>
    </div>

</div>
</body>

</html>
