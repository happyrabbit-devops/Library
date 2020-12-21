<a class="btn btn-success mb-3" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
    Предложить публикацию
</a>
<div class="collapse <#if book??>show</#if>" id="collapseExample">
    <div class="form-group mt-3">
        <form method="post" enctype="multipart/form-data">
            <div class="form-group col-sm-6">
                <label class="caption-label" for="caption">Название книги:</label>
                <input type="text" name="caption" id="caption" class="form-control ${(captionError??)?string('is-invalid', '')}"
                       value="<#if book??>${book.caption}</#if>" placeholder="Введите название книги" />
                <#if captionError??>
                    <div class="invalid-feedback">
                        ${captionError}
                    </div>
                </#if>
            </div>
            <div class="form-group col-sm-6">
                <label class="genre-label">Жанр:</label>
                <#list genres as genre>
                    <#if book??>
                    <#else>
                        <div>
                            <label><input type="checkbox" name="${genre}"> ${genre.caption}</label>
                        </div>
                    </#if>
                </#list>
            </div>
            <div class="form-group col-sm-6">
                <label class="description-label" for="description">Аннотация:</label>
                <input type="text" id="description"  name="description" class="form-control ${(descriptionError??)?string('is-invalid', '')}"
                       value="<#if book??>${book.description}</#if>" placeholder="Описаниие">
                <#if descriptionError??>
                    <div class="invalid-feedback">
                        ${descriptionError}
                    </div>
                </#if>
            </div>
            <div class="form-group col-sm-6">
                <label class="content-label" for="content">Отрывок из книги:</label>
                <textarea name="content" id="content" class="form-control ${(contentError??)?string('is-invalid', '')}"
                          value="<#if book??>${book.content}</#if>" placeholder="Отрывок" rows="3"></textarea>
                <#if contentError??>
                    <div class="invalid-feedback">
                        ${contentError}
                    </div>
                </#if>
            </div>
            <div class="form-group col-sm-6">
                <div class="custom-file">
                    <input type="file" name="file" id="customFile">
                    <label class="custom-file-label" for="customFile">Выберите обложку</label>
                </div>
            </div>
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <input type="hidden" name="id" value="<#if book??>${book.id}<#else>-1</#if>" />
            <input type="hidden" name="published" value="false" />
            <div class="form-group col-sm-6">
                <button type="submit" class="btn btn-success">Сохранить</button>
            </div>
        </form>
    </div>
</div>
