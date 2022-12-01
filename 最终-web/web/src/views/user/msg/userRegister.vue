
<template>
  <!-- 注册页面 -->
  <ContentField>
    <div class="row justify-content-md-center">
      <div class="col-3">
        <!--  表单样式 -->
        <form @submit.prevent="register">

          <div class="mb-3">
            <label for="username" class="form-label">用户名</label>
            <input v-model="username" type="text" class="form-control" id="username" placeholder="请输入用户名" />
          </div>
          <div class="mb-3">
            <label for="school" class="form-label">学校</label>
            <input v-model="school" type="text" class="form-control" id="school" placeholder="请输入你的学校" />
          </div>
          <div class="mb-3">
            <label for="password" class="form-label">密码</label>
            <input v-model="password" type="password" class="form-control" id="password" placeholder="请输入密码" />
          </div>
          <div class="mb-3">
            <label for="password" class="form-label">确认密码</label>
            <input v-model="pwd2" type="password" class="form-control" id="pwd2" placeholder="请再次输入密码" />
          </div>
          <div class="mb-3">
            <label for="email" class="form-label">邮箱</label>
            <input v-model="email" type="email" class="form-control" placeholder="请输入您的邮箱方便找回密码" />
            <button type="button" class="btn btn-warning" style="width:60%;margin-top: 5px" @click="button_email" id="email">
              {{
                text
              }}
            </button>
            &nbsp;&nbsp;&nbsp;
            <span style="color: red">
              {{
                email_msg
              }}
            </span>
          </div>

          <div class="mb-3">
            <label for="code_input" class="form-label">验证码</label>
            <input v-model="code_input" type="text" class="form-control" id="code_input" placeholder="请输入获取的验证码" />
          </div>
          <!--  错误信息提示 -->
          <div class="error-message">
            {{ error_msg }}
          </div>
          <button type="submit" class="btn btn-primary" style="width: 40%;">
            注册
          </button>

          <button type="button" class="btn btn-primary" style="width: 40%;margin-left: 4vh" @click="close">
            取消
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
    // 实现双向绑定
    let username = ref("");
    let school = ref("");
    let password = ref("");
    let pwd2 = ref("");
    let photo = ref("");
    let error_msg = ref("");
    let email_msg = ref("");
    let email = ref("");
    let text = ref("");
    let code_t = 0; // 接收后端传的code验证
    let code_input = ref(""); // 接收前端传入的验证
    text.value = "获取验证码"

    $("#email").attr("disabled", false); // 解绑

    const close = () => {
      router.push({ name: "home" }); // 返回登录界面
    }
    //  邮箱验证 专门接口
    const button_email = () => {
      if (email.value === "") {
        email_msg.value = "邮件不能为空";
        return
      }

      //  60s 
      let i = 59;
      let timer = setInterval(function () {
        text.value = "剩余" + i + " s 可以重新发送"
        $("#email").attr("disabled", true);
        if (--i < 0) {
          $("#email").attr("disabled", false);
          text.value = "获取验证码"
          clearTimeout(timer);
        }
      }, 1000);


      // 点击了之后
      // 邮箱接口 
      //  测试接口
      console.log(1);
      $.ajax({
        url: "http://127.0.0.1:4000/user/email/",
        type: "post",
        data: {
          email: email.value
        },
        success(resp) {
          if (resp.error_msg === "success") {
            // 发送成功
            console.log(resp.code);
            if (resp.code === "") {
              email_msg.value = "网络波动~";
              return;
            }
            code_t = resp.code; // 直接通过前端进行判断验证码
          } else {
            error_msg.value = resp.error_msg;
          }
        },
      });

    }


    //  提交表单注册进行判断
    const register = () => {
      console.log(school.value);
      console.log(code_t);
      if (code_input.value === "") {
        error_msg.value = "验证码不能为空哦~"
        return;
      }
      if (code_t === code_input.value) {
        console.log("你好呀");
      } else {
        error_msg.value = "验证码有问题哦~"
        return
      }
      //  注册接口
      $.ajax({
        url: "http://127.0.0.1:4000/user/msg/register/",
        type: "post",
        data: {
          username: username.value,
          school: school.value,
          password: password.value,
          pwd2: pwd2.value,
          pic: photo.value,
          email: email.value
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
      email,
      register,
      button_email,
      close,
      email_msg,
      text,
      code_input,
      school
    };


  },
};


</script>

<style scope>
div .error-message {
  color: red;
}
</style>
