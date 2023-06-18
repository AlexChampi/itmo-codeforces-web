<template>
  <PostArticle :post="post"/>
</template>

<script>
import PostArticle from "@/components/main/PostArticle.vue";

export default {
  name: "Post",
  components: {PostArticle},
  props: ["post"],
}
</script>

<style scoped>

</style>