<#import "modules/head.ftl" as c>
<#include "modules/security.ftl">
<@c.page>
    <div class="container my-3">
                    <div class="row">
                        <div class="col">
                <div class="media">
                    <div class="media-left">
                        <#if book.filename??>
                            <img src="/img/${book.filename}" class="mr-3 rounded media-object" style="width: 200px; height: 150px;">
                        </#if>
                    </div>
                    <div class="media-body">
                        <h4 class="media-heading">${book.caption}</h4>
                        <label><input type="checkbox" name="published" ${book.published?string("checked", "")} disabled> Опубликовано</label>
                        <p><b>${book.authorName}</b></p>
                        <p><b>Жанры:</b> <#list bookGenres as genre>${genre.caption}<#sep>, </#list></p>
                        <p>${book.description}</p>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col mt-3">
                <#if book.content??>
                    <h4>Отрывок из книги</h4>
                    <p>${book.content}</p>
                </#if>
            </div>
        </div>
        <div class="row">
            <div class="col mt-20">
                <hr>
                <h4 class="mt-0">Комментарии</h4>
                <#if currentUserId??>
                    <#include "modules/commentEdit.ftl"/>
                </#if>
            </div>
        </div>
        <div class="row">
            <div class="col">
                <#list comments as comment>
                    <div class="media">
                        <#if comment.filename??>
                        <a href="/user-comments/${comment.author.id}"><img src="/img/${comment.filename}" class="mr-3 rounded-circle" style="width: 50px; height: 50px;"></a>
                        </#if>
                        <div class="media-body">
                            <h4 class="mt-0">#${comment.tag}</h4>
                            <span>${comment.text}</span><br/>
                            <span>От: <a href="/user-comments/${comment.author.id}">${comment.author.username}</a></span>
                            <#if comment.author.id == currentUserId>
                                <a href="/user-comments/${comment.author.id}?comment=${comment.id}">Правка</a>
                            </#if>
                        </div>
                    </div>
                    <hr>
                <#else>
                    Комментариев к книге еще нет, оставьте первым!
                </#list>
            </div>
        </div>
    </div>
</@c.page>
