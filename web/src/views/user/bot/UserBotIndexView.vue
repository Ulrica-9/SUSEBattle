
<template>
  <ContentField> 我的Bot </ContentField>
</template>

<script>
// 引用公共部分（白色边框）
import ContentField from "../../../components/ContentField.vue";
import $ from "jquery";
import { useStore } from "vuex";

export default {
  components: {
    ContentField,
  },
  setup() {
    // 创建bot
    const store = useStore();
    // $.ajax({
    //   url: "http://127.0.0.1:4000/user/bot/add/",
    //   type: "post",
    //   data: {
    //     name: "Bot测试",
    //     description: "Bot描述",
    //     content: "Bot测试",
    //   },
    //   headers: {
    //     Authorization: "Bearer " + store.state.user.token,
    //   },
    //   success(resp) {
    //     console.log(resp);
    //   },
    //   error(resp) {
    //     console.log(resp);
    //   },
    // });

    // 删除bot
    // $.ajax({
    //   url: "http://127.0.0.1:4000/user/del/bot/",
    //   type: "post",
    //   data: {
    //     bot_id: 14,
    //   },
    //   headers: {
    //     // 当前对象
    //     Authorization: "Bearer " + store.state.user.token,
    //   },
    //   success(resp) {
    //     console.log(resp);
    //   },
    //   error(resp) {
    //     console.log(resp);
    //   },
    // });

    // 修改
    // $.ajax({
    //   url: "http://127.0.0.1:4000/user/update/bot/",
    //   type: "post",
    //   data: {
    //     bot_id: 16,
    //     name: "Bot测试12",
    //     description: "Bot描述12",
    //     content: "Bot测试12",
    //   },
    //   headers: {
    //     // 当前对象
    //     Authorization: "Bearer " + store.state.user.token,
    //   },
    //   success(resp) {
    //     console.log(resp);
    //   },
    //   error(resp) {
    //     console.log(resp);
    //   },
    // });

    // 查询
    $.ajax({
      url: "http://127.0.0.1:4000/user/get/bot/",
      type: "get",
      headers: {
        // 当前对象
        Authorization: "Bearer " + store.state.user.token,
      },
      success(resp) {
        console.log(resp);
      },
      error(resp) {
        console.log(resp);
      },
    });
  },
};
</script>

<style scope>
</style>
