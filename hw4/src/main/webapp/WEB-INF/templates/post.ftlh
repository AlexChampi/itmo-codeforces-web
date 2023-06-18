<#-- @ftlvariable name="post_id" type="java.lang.Long" -->
<#import "./common.ftlh" as c>

<@c.page>
    <#assign post = c.findBy(posts, "id", post_id)!/>
    <#if post?has_content>
        <@c.post post false/>
    <#else>
        No such post
    </#if>
</@c.page>