<#import "modules/head.ftl" as c>
<@c.page>
<div class="container">
    <div class="form-row">
        <div class="form-group col-md-6">
            <form method="get" action="/books" class="form-inline">
                <input type="text" name="filter" class="form-control" value="${filter?ifExists}" placeholder="Искать по названию">
                <button type="submit" class="btn btn-success ml-2">Найти</button>
            </form>
        </div>
    </div>
    <div class="row">
        <div class="col">
            <#include "modules/booksList.ftl"/>
        </div>
    </div>
    <div class="row">
        <div class="col mt-3">
            <#include "modules/booksEdit.ftl"/>
        </div>
    </div>
</div>
</@c.page>
