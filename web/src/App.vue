
//  写html 
<template>
  <NavBar />
  <router-view></router-view>
</template>


// 写后端给前端的代码
<script>
import NavBar from "./components/NavBar.vue";
import "bootstrap/dist/css/bootstrap.min.css";
import "bootstrap/dist/js/bootstrap";

export default {
  // 接口函数
  components: {
    NavBar,
  },
};
</script>

// CSS 样式
<style>
body {
  /* @ 可以替换 . */
  background: url("./assets/images/background.png") no-repeat;
  background-size: cover;
}
</style>
