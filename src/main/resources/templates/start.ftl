<#import "modules/head.ftl" as c>
<#include "modules/security.ftl">
<@c.page>
    <#if name=='unknown'><h3 class="mb-5">Добро пожаловать, дорогой Гость!</h3>
        <p>
            Для начала работы с библиотекой, пожалуйста, зарегистрируйтесь
        <br>
            <a href="/registration">Создать нового пользователя</a>
        </p>
    <#else>
        <h3 class="mb-5">Добро пожаловать, дорогой ${name}!</h3>
    </#if>
    <p class="mt-3"><i>В нашей библиотеке собраны самые популярные книги мира</i></p>
</@c.page>
