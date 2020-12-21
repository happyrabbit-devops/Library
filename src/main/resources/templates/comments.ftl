<#import "modules/head.ftl" as c>
<@c.page>
    <div class="container my-3">
        <div class="row">
            <div class="col mb-3">
            <h3>Профиль автора ${userChannel.username}</h3>
                <#if !isCurrentUser>
                    <#if isSubscriber>
                        <a class="btn btn-info" href="/user/unsubscribe/${userChannel.id}">Отписаться от автора</a>
                    <#else>
                        <a class="btn btn-info" href="/user/subscribe/${userChannel.id}">Подписаться на автора</a>
                    </#if>
                </#if>
            </div>
        </div>
        <div class="row">
            <div class="col">
                <div class="card">
                    <div class="card-body">
                        <div class="card-title">Подписки автора</div>
                        <h3 class="card-text">
                            <a href="/user/subscriptions/${userChannel.id}/list">${subscriptionsCount}</a>
                        </h3>
                    </div>
                </div>
            </div>
            <div class="col">
                <div class="card">
                    <div class="card-body">
                        <div class="card-title">Читатели автора</div>
                        <h3 class="card-text">
                            <a href="/user/subscribers/${userChannel.id}/list">${subscribersCount}</a>
                        </h3>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col mt-3">
                <#if isCurrentUser>
                    <#include "modules/commentEdit.ftl"/>
                </#if>
                <#include "modules/commentList.ftl"/>
            </div>
        </div>
    </div>

</@c.page>
