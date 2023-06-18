<#-- @ftlvariable name="user_id" type="java.lang.Long" -->
<#-- @ftlvariable name="handle" type="java.lang.String" -->
<#import "./common.ftlh" as c>

<@c.page>
    <#if handle?has_content>
        <#assign key = "handle">
        <#assign value = handle>
    <#elseif user_id?has_content>
        <#assign key = "id">
        <#assign value = user_id>
    </#if>

    <#assign u = c.findBy(users, key, value)!/>

    <#if u?has_content>
        <div class="user-profile">
            <@profile_data/>
        </div>
        <div class="profile-switcher">
            <@prev_next_profile u/>
        </div>
    <#else>
        No such user
    </#if>
</@c.page>

<#macro profile_data>
    <div class="info">
        <div class="name-link">
            <@c.userlink u false/>
        </div>
        <ul>
            <li>id: ${u.id}</li>
            <li>handle: ${u.handle}</li>
            <li>name: ${u.name}</li>
            <li>posts: <@c.userpostLink u.id/></li>
        </ul>
    </div>
    <div class="photo">
        <img alt="user pic" title="user pic" src="/img/user-pic-default.jpg"/>
    </div>
</#macro>

<#macro prev_next_profile current_user>
    <#assign current_index = users?seq_index_of(current_user)/>
    <@show_arrow "←" current_index 0 current_index-1/>
    <@show_arrow "→ " current_index users?size-1 current_index+1/>
</#macro>

<#macro show_arrow arrow index last_index next_index>
    <#if index == last_index>
        <span class="arrow-empty">${arrow}</span>
    <#else>
        <div class="arrow">
            <a href="/user?handle=${users[next_index].handle}">${arrow}</a>
        </div>
    </#if>
</#macro>