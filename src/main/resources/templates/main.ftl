<#import "modules/head.ftl" as c>
<@c.page>
    <div class="form-row">
        <div class="form-group col-md-6">
            <form method="get" action="/main" class="form-inline">
                <input type="text" name="filter" class="form-control" value="${filter?ifExists}" placeholder="Искать по хэштэгу">
                <button type="submit" class="btn btn-success ml-2">Найти</button>
            </form>
        </div>
    </div>
    <#include "modules/commentEdit.ftl"/>
    <#include "modules/commentList.ftl"/>
</@c.page>
