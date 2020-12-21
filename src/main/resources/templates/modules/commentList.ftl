<#include "security.ftl">
<div class="row">
    <div class="col">
        <h5 class="mb-3">Комментарии:</h5>
        <#list comments as comment>
            <div class="media">
                <#if comment.filename??>
                <a href="/user-comments/${comment.author.id}"><img src="/img/${comment.filename}" class="mr-3 rounded-circle" style="width: 50px; height: 50px;"></a>
                </#if>
                <div class="media-body">
                    <h4 class="media-heading">${comment.text}</h4>
                    <i>#${comment.tag}</i>
                    <span>От: <a href="/user-comments/${comment.author.id}">${comment.author.username}</a></span>
                    <#if comment.author.id == currentUserId>
                        <a href="/user-comments/${comment.author.id}?comment=${comment.id}">Правка</a>
                    </#if>
                </div>
            </div>
            <hr>
        <#else>
            Комментарии отсутствуют
        </#list>
    </div>
</div>
