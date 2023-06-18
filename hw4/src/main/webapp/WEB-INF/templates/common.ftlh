<#-- @ftlvariable name="uri" type="java.lang.String" -->
<#macro page>
    <!DOCTYPE html>
    <html lang="ru">
    <head>
        <meta charset="UTF-8">
        <title>Codeforces</title>
        <link rel="stylesheet" href="/css/normalize.css">
        <link rel="stylesheet" href="/css/style.css">
        <link rel="icon" href="/favicon.ico">
    </head>
    <body>
    <@header/>
    <div class="middle">
        <@sidebar/>
        <main>
                <#nested/>
        </main>
    </div>
    <@footer/>
    </body>
    </html>
</#macro>

<#macro header>
    <header>
        <a href="#" class="logo"><img alt="Codeforces" title="Codeforces"
                                      src="/img/codeforces-logo-with-telegram.png"></a>
        <div class="languages">
            <a href="#"><img alt="In English" title="In English"
                             src="/img/gb.png"></a>
            <a href="#"><img alt="In Russian" title="In Russian"
                             src="/img/ru.png"></a>
        </div>
        <div class="enter-or-register-box">
            <#if user??>
                <@userlink user true/>
                |
                <a href="/logout">Logout</a>
            <#else>
                <a href="/enter">Enter</a>
                |
                <a href="/register">Register</a>
            </#if>
        </div>
        <nav>
            <#assign items=[
            {"href": "/index", "name": "Home"},
            {"href": "/misc/help", "name": "Help"},
            {"href": "/contests", "name": "Contests"},
            {"href": "/users", "name": "Users"}
            ]/>
            <ul>
                <#list items as item>
                    <#if uri == item.href>
                        <#assign active_class = "current">
                    <#else>
                        <#assign active_class = "">
                    </#if>
                    <li>
                        <a class="${active_class}" href="${item.href}">${item.name}</a>
                    </li>
                </#list>
            </ul>
        </nav>
    </header>
</#macro>

<#macro sidebar>
    <aside>
        <#list sortPostsByNew(posts) as post>
            <section>
                <div class="header">Post ${post.id}</div>
                <div class="body"> <@postBody post true/>
                </div>
                <div class="footer">
                    <a href="/post?post_id=${post.id}">View all</a>
                </div>
            </section>
        </#list>
    </aside>
</#macro>

<#macro post p isShort>
<#-- @ftlvariable name="isShort" type="java.lang.Boolean" -->
<#-- @ftlvariable name="p" type="ru.itmo.web.hw4.model.Post" -->
    <#assign u=findBy(users, "id", p.userId)/>
    <article>
        <@postHeader p u/>
        <@postBody p isShort/>
        <@postFooter />
    </article>
</#macro>

<#macro postHeader post user>
<#-- @ftlvariable name="post" type="ru.itmo.web.hw4.model.Post" -->
    <div class="title">${post.title}</div>
    <div class="information">By <@userlink user false/>, 2 days ago, translation</div>
</#macro>

<#macro postBody post isShort>
<#-- @ftlvariable name="isShort" type="java.lang.Boolean" -->
<#-- @ftlvariable name="post" type="ru.itmo.web.hw4.model.Post" -->
    <div class="body">
        <#if isShort>
            ${post.text?truncate(350, '...')}
        <#else>
            ${post.text}
        </#if>
    </div>
</#macro>

<#macro postFooter>
    <div class="footer">
        <div class="left">
            <img src="img/voteup.png" title="Vote Up" alt="Vote Up"/>
            <span class="positive-score">+173</span>
            <img src="img/votedown.png" title="Vote Down" alt="Vote Down"/>
        </div>
        <div class="right">
            <img src="img/date_16x16.png" title="Publish Time" alt="Publish Time"/>
            2 days ago
            <img src="img/comments_16x16.png" title="Comments" alt="Comments"/>
            <a href="#">68</a>
        </div>
    </div>
</#macro>

<#macro footer>
    <footer>
        2010-2021 &copy; by Mike Mirzayanov
    </footer>
</#macro>

<#macro postpath post>
<#-- @ftlvariable name="post" type="ru.itmo.web.hw4.model.Post" -->

</#macro>

<#macro userlink user nameOnly>
<#-- @ftlvariable name="nameOnly" type="java.lang.Boolean" -->
<#-- @ftlvariable name="user" type="ru.itmo.web.hw4.model.User" -->
    <#if nameOnly>
        <a href="/user?handle=${user.handle}">${user.name}</a>
    <#else >
        <a href="/user?handle=${user.handle}"
           class="colored"
           style="color: ${user.color.code};">${user.name}</a>
    </#if>
</#macro>

<#macro userpostLink id>
<#-- @ftlvariable name="id" type="java.lang.Long" -->
    <#assign postCount = posts?filter(x -> x.userId == id)?size/>
    <#if (postCount > 0)>
        <a href="/posts?user_id=${id}">${postCount}</a>
    <#else >
        <span>0</span>
    </#if>
</#macro>

<#function findBy items key value>
    <#list items as item>
        <#if ((item[key]?is_number && value?is_number) ||
        (item[key]?is_string && value?is_string)) && item[key] == value>
            <#return item/>
        </#if>
    </#list>
</#function>

<#function sortPostsByNew posts>
<#-- @ftlvariable name="posts" type="ru.itmo.web.hw4.model.Post[]" -->
    <#return posts?sort_by("id")?reverse/>
</#function>
