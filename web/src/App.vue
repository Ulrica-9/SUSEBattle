<template>
  <NavBar />
  <router-view></router-view>
</template>


<script>
import NavBar from "./components/NavBar.vue";
import "bootstrap/dist/css/bootstrap.min.css";
import "bootstrap/dist/js/bootstrap";
//import $ from "jquery";
//  调试

export default {
  // 接口函数
  components: {
    NavBar,
  },
  setup() {
    // //  实现接口
    // $.ajax({
    //   url: "http://127.0.0.1:4000/user/msg/token/",
    //   type: "post",
    //   data: {
    //     username: "小xn",
    //     password: "123456",
    //   },
    //   success(resp) {
    //     console.log(resp);
    //   },
    //   error(resp) {
    //     console.log(resp);
    //   },
    // });
    //  获取信息接口
    // $.ajax({
    //   url: "http://127.0.0.1:4000/user/msg/info/",
    //   type: "get",
    //   headers: {
    //     // 授权
    //     Authorization:
    //       "Bearer " +
    //       "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI4OTI4Y2VlMTA5ODk0MGY3OWJlYjA0YWEzOWE2NGQ5ZiIsInN1YiI6IjEiLCJpc3MiOiJzZyIsImlhdCI6MTY2NzkwMzEyMSwiZXhwIjoxNjY5MTEyNzIxfQ.zE0BqrM8aO79Bmb4IDhf6R1_4cH_0mODJSVIKYj-Sog",
    //   },
    //   success(resp) {
    //     console.log(resp);
    //   },
    //   error(resp) {
    //     console.log(resp);
    //   },
    // });
    //  注册接口
    // $.ajax({
    //   url: "http://127.0.0.1:4000/user/msg/register/",
    //   type: "post",
    //   data: {
    //     username: "小luoxin",
    //     password: "123456",
    //     pwd2: "123456",
    //   },
    //   success(resp) {
    //     console.log(resp);
    //   },
    //   error(resp) {
    //     console.log(resp);
    //   },
    // });
  },
};
</script>


<style>
body {
  background: url("@/assets/images/background.png") no-repeat;
  background-size: cover;
}
</style>
