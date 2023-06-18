<template>
  <div v-if="jwt" class="writeComment form-box">
    <div class="header">Write comment</div>
    <div class="body">
      <form @submit.prevent="onWriteComment">
        <div class="field">
          <div class="name">
            <label for="login">Text</label>
          </div>
          <div class="value">
            <input autofocus id="login" name="login" v-model="text"/>
          </div>
        </div>
        <div class="field error">{{ error }}</div>
        <div class="button-field">
          <input type="submit" value="Write">
        </div>
      </form>
    </div>
  </div>
</template>

<script>
export default {
  name: "WriteComment",
  data: function () {
    return {
      text: "",
      error: "",
      jwt: localStorage.getItem("jwt"),
    }
  },
  props: ["post"],
  methods: {
    onWriteComment: function () {
      this.$root.$emit("onWriteComment", this.post, this.text, this.jwt);
    },
  },
  beforeCreate() {
    this.$root.$on("onWriteCommentValidationError", (error) => this.error = error);
    this.$root.$on("onWriteCommentSuccess", () => { this.error = ""; this.text = ""});
  },
}
</script>

<style scoped>
.writeComment {
  margin-top: 2rem;
  margin-bottom: 2rem;
}
</style>