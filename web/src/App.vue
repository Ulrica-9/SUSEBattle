<template>
  <NavBar />
  <router-view></router-view>
</template>

<script>
import NavBar from './components/NavBar.vue'
import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap/dist/js/bootstrap'
//import $ from "jquery";
//  调试

export default {
  // 接口函数
  components: {
    NavBar
  },
  setup() {
    /* //  实现接口
    $.ajax({
      url: "http://127.0.0.1:4000/user/msg/token/",
      type: "post",
      data: {
        username: "小xn",
        password: "123456",
      },
      success(resp) {
        console.log(resp);
      },
      error(resp) {
        console.log(resp);
      },
    });
     获取信息接口
    $.ajax({
      url: "http://127.0.0.1:4000/user/msg/info/",
      type: "get",
      headers: {
        // 授权
        Authorization:
          "Bearer " +
          "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI4OTI4Y2VlMTA5ODk0MGY3OWJlYjA0YWEzOWE2NGQ5ZiIsInN1YiI6IjEiLCJpc3MiOiJzZyIsImlhdCI6MTY2NzkwMzEyMSwiZXhwIjoxNjY5MTEyNzIxfQ.zE0BqrM8aO79Bmb4IDhf6R1_4cH_0mODJSVIKYj-Sog",
      },
      success(resp) {
        console.log(resp);
      },
      error(resp) {
        console.log(resp);
      },
    });
     注册接口
    $.ajax({
      url: "http://127.0.0.1:4000/user/msg/register/",
      type: "post",
      data: {
        username: "小luoxin",
        password: "123456",
        pwd2: "123456",
      },
      success(resp) {
        console.log(resp);
      },
      error(resp) {
        console.log(resp);
      },
    }); */
  }
}
</script>

<style lang="less">
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}

em,
i {
  font-style: normal;
}

li {
  list-style: none;
}

img {
  border: 0px;
  vertical-align: middle;
}

button {
  cursor: pointer;
}

a {
  text-decoration: none;
}

body {
  /* background: url('@/assets/images/background.jpg') no-repeat;
  background-size: cover; */
  font-family: "阿里巴巴普惠体 2.0 45 Light";
  font-weight: 600;
  src: url("//at.alicdn.com/wf/webfont/wjQKl7ew3cIC/aujwhMUvRuxG89KTBmkOf.woff2")
      format("woff2"),
    url("//at.alicdn.com/wf/webfont/wjQKl7ew3cIC/mLg_CoshO20IoG6QeE4oQ.woff")
      format("woff");
  font-display: swap;
  background: rgb(250, 250, 250);
}

@font-face {
  font-family: "iconfont";
  src: url("./font/iconfont.woff2?t=1670394315375") format("woff2"),
    url("./font/iconfont.woff?t=1670394315375") format("woff"),
    url("./font/iconfont.ttf?t=1670394315375") format("truetype");
}

.iconfont {
  font-family: "iconfont" !important;
  font-size: 16px;
  font-style: normal;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
}
</style>
