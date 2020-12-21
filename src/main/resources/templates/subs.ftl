<#import "modules/head.ftl" as c>
<@c.page>
<h3>${type} автора ${userChannel.username}</h3>
<br>
<ul class="list-group">
    <#list users as user>
        <li class="list-group-item">
            <a href="/user-comments/${user.id}">${user.getUsername()}</a>
        </li>
    </#list>
</ul>
</@c.page>
