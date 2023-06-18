<#-- @ftlvariable name="users" type="ru.itmo.wp.model.domain.User[]" -->
<#import "commons.ftlh" as c>

<@c.page>
    <div class="users datatable">
        <div class="caption">User</div>
        <table>
            <thead>
            <tr>
                <th>Id</th>
                <th>Login</th>
                <#if user?? && user.admin>
                    <th class="user-admin">Admin</th>
                </#if>
            </tr>
            </thead>
            <tbody>
            <template>
                <tr>
                    <td class="user-id"></td>
                    <td class="user_login"></td>
                    <#if user?? && user.admin>
                        <td class="user-admin">
                            <form method="post" action="">
                                <button type="button"></button>
                            </form>
                        </td>
                    </#if>
                </tr>
            </template>
            <tr class="noData">
                <td colspan="3">
                    No data
                </td>
            </tr>
            </tbody>
        </table>
    </div>

    <script>
        $(function () {
            const $noData = $(".users .noData");
            $noData.find("td").text("Loading...");
            const success = function (response) {
                response["users"].forEach(function (user) {
                        $noData.hide();
                        const $tr = $($(".users template").prop("content")).clone();
                        $tr.find(".user-id").text(user["id"]);
                        $tr.find(".user_login").text(user["login"]);
                        $tr.find("tr").attr("data-userId", user["id"]);
                        if (response["user"] && response["user"].admin) {
                            $tr.find("button").attr("data-userId", user["id"]);
                            if (user["admin"]) {
                                $tr.find("button").text("Disable");
                            } else {
                                $tr.find("button").text("Enable");
                            }
                        }
                        $(".users tbody").append($tr);

                        if (!response["user"] || !response["user"].admin) {
                            $(".user-admin").hide();
                        }
                    }
                );
                $(".users tr").click(function () {
                    window.ajax(
                        {
                            action: "findUser",
                            userId: $(this).attr("data-userId")
                        },
                        function (response) {
                            notify(response["user"].creationTime);
                        }
                    )
                });
                $(".users button").click(function () {
                    const $buttonField = $(this)
                    window.ajax(
                        {
                            action: "admin",
                            userId: $(this).attr("data-userId")
                        },
                        function (response) {
                            if (response["error"]) {
                                notify("You are not admin");
                                $(".user-admin").hide();
                            } else {
                                // $buttonField.text(response["userStatus"] ? "Disable" : "Enable");
                                if (response["userStatus"]) {
                                    $buttonField.text("Enable");
                                    notify("Admin disable");
                                } else {
                                    $buttonField.text("Disable");
                                    notify("Admin enable");
                                }
                                if (!response["user"] || !response["user"].admin) {
                                    $(".user-admin").hide();
                                }
                            }
                        })
                })
            }
            window.ajax({action: "findAll"}, success)
            return false;
        })
    </script>
</@c.page>
