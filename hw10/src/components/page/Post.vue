<template>
  <PostArticle :post="post"
               :user-login="userLogin"
               :comments-counter="2"/>
</template>

<script>
import PostArticle from "@/components/page/PostArticle.vue";

export default {
  name: "Post",
  components: {PostArticle},
  props: ["post", "userLogin"],
}
</script>

<style scoped>

</style>