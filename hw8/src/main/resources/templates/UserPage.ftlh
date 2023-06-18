<#import "commons.ftlh" as c>

<@c.page>
    <#if user_profile?has_content>
    <div class="user-profile">
            <div class="info">
                <ul>
                    <li>id: ${user_profile.id}</li>
                    <li>login: ${user_profile.login}</li>
                    <li>creation: ${user_profile.creationTime}</li>
                </ul>
            </div>
            <div class="photo">
                <img alt="user pic" title="user pic" src="<@c.spring.url '/img/user-pic-default.jpg'/>"/>
            </div>
    </div>
    <#else>
        No such user
    </#if>
</@c.page>