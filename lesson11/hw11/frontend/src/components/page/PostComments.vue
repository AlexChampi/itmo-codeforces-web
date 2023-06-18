<template>
  <div class="datatable">
    <div class="caption">Comments</div>
    <table>
      <thead>
      <tr>
        <th>Login</th>
        <th>Text</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="comment in viewComments(comments)" :key="comment.id">
        <td>{{ comment.user.login }}</td>
        <td>{{ comment.text }}</td>
      </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
export default {
  name: "PostComments",
  props: ["comments"],
  methods: {
    viewComments: function (comments) {
      return comments.reverse();
    }
  },
}
</script>

<style scoped>

</style>