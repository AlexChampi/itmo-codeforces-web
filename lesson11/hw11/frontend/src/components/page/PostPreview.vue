<template>
  <article>
    <a href="#" @click.prevent="viewPost(post)"><div class="title">{{ post.title }}</div></a>
    <div class="information">By {{ post.user.login }} </div>
    <div class="body">{{ post.text }}</div>
    <div class="footer">
      <div class="left">
        <img src="../../assets/img/voteup.png" title="Vote Up" alt="Vote Up"/>
        <span class="positive-score">+173</span>
        <img src="../../assets/img/votedown.png" title="Vote Down" alt="Vote Down"/>
      </div>
      <div class="right">
        <img src="../../assets/img/date_16x16.png" title="Publish Time" alt="Publish Time"/>
        2 days ago
        <img src="../../assets/img/comments_16x16.png" title="Comments" alt="Comments"/>
        <a href="#">{{ commentsCount }}</a>
      </div>
    </div>
  </article>
</template>

<script>
export default {
  name: "PostPreview",
  props: ["post", "commentsCount"],
  methods: {
    viewPost: function (post) {
      this.$root.$emit("onViewPost", post);
    },
  }
}
</script>

<style scoped>
a {
  text-decoration: none;
}
</style>