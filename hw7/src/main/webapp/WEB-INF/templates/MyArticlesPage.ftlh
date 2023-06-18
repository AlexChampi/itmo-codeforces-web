<#-- @ftlvariable name="articles" type="ru.itmo.wp.model.domain.Article[]" -->
<#import "commons.ftlh" as c>

<@c.page>
    <div class="my-article datatable">
        <div class="caption">Articles</div>
        <table>
            <thead>
            <tr>
                <th>Id</th>
                <th>Title</th>
                <th>Show/Hide</th>
            </tr>
            </thead>
            <tbody>
            <#list articles as article>
                <tr>
                    <td class="article-id">${article.id}</td>
                    <td class="article-title">${article.title}</td>
                    <td class="article-button">
                        <form method="post" action="">
                            <input type="hidden" name="article-id" value="${article.id}"/>
                            <div class="button-field">
                                <input type="submit" value="<#if article.hidden>Show<#else>Hide</#if>">
                            </div>
                        </form>
                    </td>
                </tr>
            </#list>
            </tbody>
        </table>
    </div>
    <script>
        $(function () {
            $(".my-article form").submit(function () {
                const articleId = $(this).find("input[name='article-id']").val();
                const $buttonField = $(this).find(".button-field input");
                window.ajax(
                    {
                        action: "update",
                        articleId: articleId
                    },
                    function (response) {
                        $buttonField.val(response["articleStatus"] ? "Show" : "Hide")
                        // if ($buttonField.val() === "Show") {
                        //     $buttonField.val("Hide");
                        // } else {
                        //     $buttonField.val("Show");
                        // }
                    }
                )

                return false;
            })
        })
    </script>
</@c.page>