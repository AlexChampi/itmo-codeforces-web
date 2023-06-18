<#import "commons.ftlh" as c>

<@c.page>
    <@c.post post=post/>
    <#if user??>
        <div class="form">
            <div class="header">Write comment</div>
            <div class="body">
                <form method="post" action="">
                    <input type="hidden" name="action" value="comment"/>
                    <div class="field comment">
                        <div class="value">
                            <textarea id="comment" name="comment"></textarea>
                        </div>
                        <@c.error "commentForm.comment"/>
                    </div>
                    <div class="button-field">
                        <input type="submit" value="Post">
                    </div>
                </form>
            </div>
        </div>
    </#if>
    <div class="comments datatable" id="comments">
        <div class="caption">Comments</div>
        <table>
            <thead>
            <tr>
                <th>Id</th>
                <th>Author</th>
                <th>Text</th>
                <th>Creation time</th>
            </tr>
            </thead>
            <tbody>
            <#list post.commentList as comment>
                <tr>
                    <td class="id">${comment.id}</td>
                    <td class="login">${comment.user.login}</td>
                    <td class="text">${comment.text}</td>
                    <td class="creationTime">${comment.creationTime}</td>
                </tr>
            </#list>
            </tbody>
        </table>
    </div>
</@c.page>