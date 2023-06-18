<#-- @ftlvariable name="user_id" type="java.lang.Long" -->
<#import "./common.ftlh" as c>

<@c.page>
    <#if user_id?has_content>
        <#assign user_posts = posts?filter(x -> x.userId == user_id)!/>
        <#if user_posts?has_content>
            <#list user_posts?reverse as up>
                <@c.post up true/>
            </#list>
        <#else >
            No post from user
        </#if>
    </#if>
</@c.page>