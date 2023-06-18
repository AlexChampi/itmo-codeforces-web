<#import "./common.ftlh" as c>

<@c.page>
    <#list c.sortPostsByNew(posts) as p>
        <@c.post p true/>
    </#list>
</@c.page>
