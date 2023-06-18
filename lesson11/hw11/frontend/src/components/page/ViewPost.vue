<template>
  <div>
    <PostPreview :post="post" :commentsCount="viewComments.length"/>
    <WriteComment :post="post"/>
    <PostComments v-if="viewComments.length > 0" :comments="viewComments"/>
  </div>
</template>

<script>
import WriteComment from "@/components/page/WriteComment.vue";
import PostPreview from "@/components/page/PostPreview.vue";
import PostComments from "@/components/page/PostComments.vue";

export default {
  name: "ViewPost",
  data: function () {
    return {
      text: "",
      error: "",
    }
  },
  components: {
    PostComments,
    PostPreview,
    WriteComment,
  },
  props: ["post", "comments", "sortLambda"],
  computed: {
    viewComments: function () {
      return Object.values(this.comments).filter(x => x.post.id === this.post.id).sort(this.sortLambda);
    }
  },
}
</script>

<style scoped>

</style>