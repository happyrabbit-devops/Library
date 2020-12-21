<#import "modules/head.ftl" as c>
<@c.page>
    <h3>Настройки пользователя</h3>
    <div class="form-group mt-3">
        <form action="/user" method="post">
            <label class="col-form-label">Логин:</label>
            <input class="form-control mb-3" type="text" name="username" value="${user.username}">
            <#list roles as role>
                <div>
                    <label><input type="checkbox" name="${role}" ${user.roles?seq_contains(role)?string("checked", "")}> ${role}</label>
                </div>
            </#list>
            <input type="hidden" value="${user.id}" name="userId">
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <button class="btn btn-success" type="submit">Сохранить</button>
        </form>
    </div>
</@c.page>
