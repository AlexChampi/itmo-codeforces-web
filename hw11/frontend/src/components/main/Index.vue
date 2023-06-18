<template>
    <div>
        <Post v-for="post in Object.values(posts).sort((a, b) => b.id - a.id)"
              :post="post"
              :key="post.id"/>
    </div>
</template>

<script>
import Post from "@/components/main/Post.vue";

export default {
    name: "Index",
    components: {
        Post
    },
    props: ["posts"]
}
</script>

<style scoped>

</style>