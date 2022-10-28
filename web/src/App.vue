
//  写html 
<template>
<div>
  <div>bot名称: {{ bot_name }}</div>
  <div>bot战力: {{ bot_rating }}</div>
</div>
  <router-view/>
</template>


// 写后端给前端的代码
<script>
import $ from 'jquery'
import { ref } from 'vue'

export default {
  name: "App",
  // 这个是执行入口
  setup: () => {
    let bot_name = ref("");
    let bot_rating = ref("");

    $.ajax({
      url: "http://127.0.0.1:3000/pk/getbotinfo/",
      type: "get",
      success: resp=> {
        bot_name.value = resp.name;
        bot_rating.value = resp.rating;
      }
    });

    return{
      bot_name,
      bot_rating
    }
  },
}
</script>

<style>
body{
  /* @ 可以替换 . */
  background: url("./assets/background.png") no-repeat;
  background-size: cover;
}
</style>
