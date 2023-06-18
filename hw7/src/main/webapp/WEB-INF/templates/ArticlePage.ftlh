<#-- @ftlvariable name="message" type="java.lang.String" -->
<#import "commons.ftlh" as c>

<@c.page>
    <div class="form-box">
        <div class="header">Create article</div>
        <div class="body">
            <form method="post" action="">
                <input type="hidden" name="action" value="createArticle"/>

                <div class="field">
                    <div class="name">
                        <label for="title">Title</label>
                    </div>
                    <div class="value">
                        <input name="title" id="title"/>
                    </div>
                </div>

                <div class="field">
                    <div class="name">
                        <label for="text">Text</label>
                    </div>
                    <div class="value">
                        <textarea id="text" name="text" rows="7" cols="20"></textarea>
                    </div>
                </div>
                <div class="error"></div>
                <div class="button-field">
                    <input type="submit" value="Create">
                </div>
            </form>
        </div>
    </div>
    <script>
        $(function () {
            $(".form-box form").submit(function () {
                const $title = $(this).find(".field input[name='title']");
                const $text = $(this).find(".field textarea[name='text']");
                const $error = $(this).find(".error");
                const success = function (response) {
                    if (!window.setError($error)(response)) {
                        $error.text("")
                        $text.val("")
                        $title.val("");
                        if (response["message"]) {
                            notify(response["message"]);
                        }
                    }
                }
                window.ajax(
                    {
                        action: "createArticle",
                        title: $title.val(),
                        text: $text.val()
                    },
                    success)
                return false;
            })
        })
    </script>
</@c.page>
