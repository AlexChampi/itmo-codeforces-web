<template>
    <div id="app">
        <Header :user="user"/>
        <Middle :posts="posts" :users="users" :comments="comments"/>
        <Footer/>
    </div>
</template>

<script>
import Header from "./components/Header";
import Middle from "./components/Middle";
import Footer from "./components/Footer";
import axios from "axios"

export default {
    name: 'App',
    components: {
        Footer,
        Middle,
        Header
    },
    data: function () {
        return {
            user: null,
            posts: [],
            users: [],
            comments: [],
        }
    },
    beforeMount() {
        if (localStorage.getItem("jwt") && !this.user) {
            this.$root.$emit("onJwt", localStorage.getItem("jwt"));
        }

        axios.get("/api/1/posts").then(response => {
            this.posts = response.data;
        });

        axios.get("/api/1/users").then(response => {
          this.users = response.data;
        });

        axios.get("/api/1/comments").then(response => {
          this.comments = response.data;
        });
    },
    beforeCreate() {
        this.$root.$on("onEnter", (login, password) => {
            if (password === "") {
                this.$root.$emit("onEnterValidationError", "Password is required");
                return;
            }

            axios.post("/api/1/jwt", {
                    login, password
            }).then(response => {
                this.$root.$emit("onJwt", response.data);
            }).catch(error => {
                this.$root.$emit("onEnterValidationError", error.response.data);
            });
        });

      this.$root.$on("onRegister", (login, name, password) => {
        if (login === "") {
          this.$root.$emit("onRegisterValidationError", "Login is required");
          return;
        }

        if (name === "") {
          this.$root.$emit("onRegisterValidationError", "Name is required");
          return;
        }

        if (password === "") {
          this.$root.$emit("onRegisterValidationError", "Password is required");
          return;
        }

        axios.post("/api/1/users", {
          login, name, password
        }).then(response => {
          this.$root.$emit("onJwt", response.data);
          axios.get("/api/1/users").then(response => {
            this.users = response.data;
          });
        }).catch(error => {
          this.$root.$emit("onRegisterValidationError", error.response.data);
        });
      });

        this.$root.$on("onJwt", (jwt) => {
            localStorage.setItem("jwt", jwt);

            axios.get("/api/1/users/auth", {
                params: {
                    jwt
                }
            }).then(response => {
                this.user = response.data;
                this.$root.$emit("onChangePage", "Index");
            }).catch(() => this.$root.$emit("onLogout"))
        });

        this.$root.$on("onLogout", () => {
            localStorage.removeItem("jwt");
            this.user = null;
        });

      this.$root.$on("onWritePost", (title, text, jwt) => {
        if (!jwt) {
          this.$root.$emit("onWritePostValidationError", "Only registered users can write posts");
          return;
        }

        if (title === "") {
          this.$root.$emit("onWritePostValidationError", "Login is required");
          return;
        }

        if (text === "") {
          this.$root.$emit("onWritePostValidationError", "Text is required");
          return;
        }

        axios.post("/api/1/posts", {
          title, text, jwt
        }).then(response => {
          this.$root.$set(this.posts, response.data.id, response.data);
          this.$root.$emit("onWritePostSuccess");
        }).catch(error => {
          this.$root.$emit("onWritePostValidationError", error.response.data);
        });
      });

      this.$root.$on("onWriteComment", (post, text, jwt) => {
        if (!jwt) {
          this.$root.$emit("onWriteCommentValidationError", "Only registered users can write comments");
          return;
        }

        if (!post) {
          this.$root.$emit("onWriteCommentValidationError", "Post is required");
          return;
        }

        if (text === "") {
          this.$root.$emit("onWriteCommentValidationError", "Text is required");
          return;
        }

        axios.post("/api/1/comments", {
          text, post, jwt
        }).then(response => {
          this.$root.$set(this.comments, response.data.id, response.data);
          this.$root.$emit("onWriteCommentSuccess");
        }).catch(error => {
          this.$root.$emit("onWriteCommentValidationError", error.response.data);
        });
      });
    }
}
</script>

<style>
#app {

}
</style>
