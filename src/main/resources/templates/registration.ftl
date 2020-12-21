<#import "modules/head.ftl" as c>
<#import "modules/login.ftl" as l>

<@c.page>
    <h4 class="mb-5">Регитрационные данные:</h4>
    ${message?ifExists}
    <@l.login "/registration" true/>
</@c.page>
