<template>
    <div class="middle">
        <Sidebar :posts="viewPosts"/>
        <main>
            <Index v-if="page === 'Index'" :posts="posts" :comments="comments" :sortLambda="sortLambda"/>
            <Users v-if="page === 'Users'" :users="users"/>
            <WritePost v-if="page === 'WritePost'"/>
            <Enter v-if="page === 'Enter'"/>
            <Register v-if="page === 'Register'"/>
            <ViewPost v-if="page === 'ViewPost' && post" :post="post" :comments="comments" :sortLambda="sortLambda"/>
        </main>
    </div>
</template>

<script>
import Sidebar from "./sidebar/Sidebar";
import Index from "./main/Index";
import Enter from "./main/Enter";
import Register from "./main/Register";
import Users from "@/components/main/Users.vue";
import WritePost from "@/components/page/WritePost.vue";
import ViewPost from "@/components/page/ViewPost.vue";

export default {
    name: "Middle",
    data: function () {
        return {
            page: "Index",
            post: null,
            sortLambda: (a, b) => a.id - b.id,
        }
    },
    components: {
      ViewPost,
      WritePost,
        Users,
        Register,
        Enter,
        Index,
        Sidebar
    },
    props: ["posts", "users", "comments"],
    computed: {
        viewPosts: function () {
            return Object.values(this.posts).sort((a, b) => b.id - a.id).slice(0, 2);
        }
    }, beforeCreate() {
        this.$root.$on("onChangePage", (page) => this.page = page)
        this.$root.$on("onViewPost", (post) => {this.post = post; this.page = "ViewPost"})
    }
}
</script>

<style scoped>

</style>
