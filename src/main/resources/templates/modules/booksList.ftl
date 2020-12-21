<#include "security.ftl">
<div class="card-columns">
    <#list books as book>
        <div class="card">
            <#if book.filename??>
                <img src="/img/${book.filename}" class="card-img-top">
            </#if>
            <div class="card-body">
                <h5 class="card-title">
                    <a href="/books/${book.id}">${book.caption}</a>
                </h5>
                <p class="card-text">
                   ${book.description}
                </p>
            </div>
        </div>
    <#else>
        В библиотеке еще нет книг
    </#list>
</div>
