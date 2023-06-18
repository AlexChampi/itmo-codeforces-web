<#import "commons.ftlh" as c>

<@c.page>
    <div class="articles">
        <div class="noData">
            <div>
                No data
            </div>
        </div>

        <template>
            <article>
                <div class="title"></div>
                <div class="information"></div>
                <div class="body"></div>
                <ul class="attachment"></ul>
                <div class="footer">
                    <div class="left">
                        <img src="img/voteup.png" title="Vote Up" alt="Vote Up"/>
                        <span class="positive-score">+0</span>
                        <img src="img/votedown.png" title="Vote Down" alt="Vote Down"/>
                    </div>
                    <div class="right">
                        <img src="img/date_16x16.png" title="Publish Time" alt="Publish Time"/>
                        <div class="date"></div>
                        <img src="img/comments_16x16.png" title="Comments" alt="Comments"/>
                        <a href="#">0</a>
                    </div>
                </div>
            </article>
        </template>
    </div>

    <script>
        $(function () {
            const $noData = $(".articles .noData");
            $noData.find("div").text("Loading...");
            const $articles = $(".articles");
            const success = function (response) {
                if (response["articles"]) {
                    $noData.hide();
                }
                response["articles"].forEach(function (article) {
                    if (!article.hidden) {
                        const users = response["userLoginByUserId"];
                        const $tpl = $($articles.find("template").prop("content")).clone();
                        $tpl.find(".title").text(article.title);
                        $tpl.find(".information").text("By " + users[article.userId] + ", " + article.creationTime);
                        $tpl.find(".body").text(article.text);
                        $tpl.find(".footer .right .date").text(article["creationTime"]);
                        $articles.append($tpl);
                    }
                })
            }
            window.ajax(
                {
                    action: "action"
                },
                success,
            )
            return false;
        })
    </script>
</@c.page>
