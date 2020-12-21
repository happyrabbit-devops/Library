<#import "modules/head.ftl" as c>
<@c.page>
<div class="container my-3">
    <div class="row">
        <div class="col mb-3">
            <h3>Список пользователей библиотеки</h3>
        </div>
    </div>
    <div class="row">
        <div class="col">
            <table class="table">
                <thead>
                <tr>
                    <th scope="col">Имя</th>
                    <th scope="col">Роль</th>
                    <th scope="col">Операции</th>
                </tr>
                </thead>
                <tbody>
                <#list users as user>
                    <tr>
                        <td scope="row">${user.username}</td>
                        <td><#list user.roles as role>${role}<#sep>, </#list></td>
                        <td><a href="/user/${user.id}">Правка</a></td>
                    </tr>
                </#list>
                </tbody>
            </table>
        </div>
    </div>
</@c.page>
