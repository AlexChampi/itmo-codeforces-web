<template>
    <footer>
      <div>Registered users: {{userCount}}</div>
      <div>Post written: {{postCount}}</div>
        <a href="/">Codeforces</a> &copy; 2022 by Mike Mirzayanov
    </footer>
</template>

<script>
export default {
    name: "Footer",
    props: ["userCount", "postCount"],
}
</script>

<style scoped>

</style>