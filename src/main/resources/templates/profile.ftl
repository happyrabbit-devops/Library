<#import "modules/head.ftl" as c>

<@c.page>
    <h4>Профиль пользователя ${username}</h4>
    ${message?ifExists}
    <form class="mt-4" method="post">
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">Пароль:</label>
            <div class="col-sm-4">
                <input type="password" name="password" class="form-control" placeholder="Пароль"/>
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">Email:</label>
            <div class="col-sm-4">
                <input type="email" name="email" class="form-control" placeholder="some@some.com" value="${email!''}"/>
            </div>
        </div>
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <div class="form-group row">
            <label class="col-sm-2 col-form-label"></label>
            <div class="col-sm-4">
                <button class="btn btn-success" type="submit">Сохранить</button>
            </div>
        </div>
    </form>
</@c.page>
