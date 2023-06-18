<template>
    <div>
        <Post v-for="post in Object.values(posts).sort((a, b) => b.id - a.id)"
              :post="post"
              :user-login="users[post.userId].login"
              :comments-counter="Object.values(comments).filter(c => c.postId === post.id).length"
              :key="post.id"/>
    </div>
</template>

<script>
import Post from "@/components/page/PostArticle.vue";

export default {
    name: "Index",
    components: {
        Post
    },
    props: ["posts", "users", "comments"],
}
</script>

<style scoped>

</style>
