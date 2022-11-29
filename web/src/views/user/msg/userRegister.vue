
<template>
  <!-- 注册页面 -->
  <ContentField>
    <div class="row justify-content-md-center">
      <div class="col-3">
        <!--  表单样式 -->
        <form @submit.prevent="register">

          <div class="mb-3">
            <label for="photo" class="form-label">上传头像</label>
            <input v-model="photo" type="text" class="form-control" id="photo" placeholder="请输入头像URL" />
          </div>

          <div class="mb-3">
            <label for="username" class="form-label">用户名</label>
            <input v-model="username" type="text" class="form-control" id="username" placeholder="请输入用户名" />
          </div>
          <div class="mb-3">
            <label for="password" class="form-label">密码</label>
            <input v-model="password" type="password" class="form-control" id="password" placeholder="请输入密码" />

          </div>
          <div class="mb-3">
            <label for="password" class="form-label">确认密码</label>
            <input v-model="pwd2" type="password" class="form-control" id="pwd2" placeholder="请再次输入密码" />
          </div>
          <div class="error-message">{{ error_msg }}</div>
          <button type="submit" class="btn btn-primary" style="width: 100%">
            注册
          </button>

        </form>
      </div>
    </div>
  </ContentField>
</template>

<script>
// 引用公共部分（白色边框）
import ContentField from "../../../components/ContentField.vue";
import { ref } from "vue";

import router from "../../../router/index";
import $ from "jquery";


export default {
  components: {
    ContentField,
  },
  setup() {
    // 用户信息
    let username = ref("");
    let password = ref("");
    let pwd2 = ref("");
    let photo = ref("");
    var error_msg = ref("");

    const register = () => {
      //  注册接口

      $.ajax({
        url: "http://127.0.0.1:4000/user/msg/register/",
        type: "post",
        data: {
          username: username.value,
          password: password.value,
          pwd2: pwd2.value,
          pic: photo.value,
        },
        success(resp) {
          if (resp.error_msg === "success") {
            router.push({ name: "user_msg_login" });
          } else {
            error_msg.value = resp.error_msg;
          }
        },
      });
    };
    return {
      username,
      password,
      pwd2,
      photo,
      error_msg,
      register,
    };
  },
};


</script>

<style scope>
div .error-message {
  color: red;
}
</style>
