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
            <!-- /.col-lg-12 -->
            <form th:action="${"@{/"}${bussinessName}/search${"}"}" th:object="${r'${form}'}" method="post">
                <div class="form-inline">
                    <#list primaryKeyColumnList as field>
                    <div class="form-group">
                        <label for="${field['propertyName']}">${field['propertyName']}</label>
                        <input type="text" class="form-control" id="${field['propertyName']}" th:field="${"*{"}condition.${field['propertyName']}}" placeholder="${field['propertyName']}" />
                    </div>
                    </#list>
                    <button type="submit" class="btn btn-default">Search</button>
                    <a class="btn btn-sm btn-success" th:href="${"@{/"}${bussinessShortName}Edit${"}"}" role="button">Add</a>
                </div>

                <table class="table table-striped">
                    <thead>
                    <tr>
                    <#list allColumnList as field>
                        <th>${field['propertyName']}</th>
                    </#list>
                    <#if hasEditOperation>
                        <th></th>
                    </#if>
                    </tr>
                    </thead>
                    <tbody>
                    <tr th:each="item : ${"*{"}${name?uncap_first}List${"}"}">
                    <#list allColumnList as field>
                        <#if field['javaType'] == 'Date'>
                            <td th:text="${r'${'}item.${field['propertyName']}${"}"} ? ${r'${#'}dates.format(item.${field['propertyName']}, 'yyyy/MM/dd')${"}"}"></td>
                        <#else>
                            <td th:text="${r'${'}item.${field['propertyName']}${"}"}"></td>
                        </#if>
                    </#list>
                    <#if hasEditOperation>
                        <td>
                            <a class="btn btn-sm btn-default" th:href="${r'@{'}'/${bussinessShortName}Edit/' + <#list primaryKeyColumnWithSysFieldList as field>${r'${'}item.${field['propertyName']}${"}"}<#if field_has_next> + '/' + </#if></#list>${"}"}">
                                Edit
                            </a>
                            <a class="btn btn-sm btn-default" th:href="${r'@{'}'/${bussinessShortName}View/delete/' + <#list primaryKeyColumnWithSysFieldList as field>${r'${'}item.${field['propertyName']}${"}"}<#if field_has_next> + '/' + </#if></#list>${"}"}">
                                Delete
                            </a>
                        </td>
                    </#if>
                    </tr>
                    </tbody>
                </table>
                <#if hasPagination>
                <div th:include="fragments/pagination :: pagination(${"*{"}pager.paginator${"}"})" th:if="${"*{"}not #lists.isEmpty(${name?uncap_first}List)${"}"}"></div>
                </#if>
            </form>
        </div>

    </div>
</body>

</html>
