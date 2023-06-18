<template>
  <div>
    <PostPreview v-for="post in viewPosts" :post="post" :key="post.id" :commentsCount="commentsCountByPost(post)"/>
  </div>
</template>

<script>
import PostPreview from "@/components/page/PostPreview.vue";

export default {
  name: "Index",
  components: {
    PostPreview,
  },
  props: ["posts", "comments", "sortLambda"],
  computed: {
    viewPosts: function () {
      return Object.values(this.posts).sort(this.sortLambda);
    },
  },
  methods: {
    commentsCountByPost: function (post) {
      return this.comments.filter(x => x.post.id === post.id).length;
    }
  }
}
</script>

<style scoped>

</style>