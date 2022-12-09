
<template>
  <!-- 注册页面 -->
  <ContentField class="animate__animated animate__bounceInRight">
    <div class="row justify-content-md-center">
      <div class="col-3">
        <!--  表单样式 -->
        <form @submit.prevent="register" enctype="multipart/form-data">

          <!-- 头像上传 -->
          <div>
            <!--  文件上传 -->

            <!--  会显示 -->
            <input type="file" name="file" id="file" @change="previewFile"><br>
            <img src="" height="200" alt="Image preview..." class="img_">
            <button type="button" @click="button_images" class="btn btn-warning">上传头像</button>
          </div>

          <!-- 个人信息页面的加载 -->
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
          <div class="btns">
            <button type="submit" class="btn btn-primary" @click="button_images">注册</button>
            &nbsp;&nbsp;&nbsp;
            <button class="btn btn-primary" @click="close">
              取消
            </button>
          </div>
        </form>
      </div>
    </div>
  </ContentField>
</template>

<script>
// 引用公共部分（白色边框）
import ContentField from "../../../components/ContentField.vue";
import { useStore } from "vuex";
import { ref } from "vue";
import router from "../../../router/index";
import $ from "jquery";
export default {
  components: {
    ContentField,
  },
  setup() {

    function previewFile() {
      var preview = document.querySelector('img');
      var file = document.querySelector('input[type=file]').files[0];
      var reader = new FileReader();

      reader.addEventListener("load", function () {
        preview.src = reader.result;
      }, false);

      if (file) {
        reader.readAsDataURL(file);
      }
    }
    const store = useStore();
    // 用户信息
    // 实现双向绑定
    let username = ref("");
    let password = ref("");
    let pwd2 = ref("");
    let error_msg = ref("");
    let email_msg = ref("");
    let email = ref("");
    let text = ref("");
    // 接收后端传的code验证
    let code_t = 0;
    let code_input = ref(""); // 接收前端传入的验证
    text.value = "获取验证码"
    $("#email").attr("disabled", false); // 解绑
    const close = () => {
      router.push({ name: "home" }); // 返回登录界面
    }

    //  上传头像




    const button_images = () => {
      //  进行文件上传
      let dd = document.querySelector("#file");
      let files = dd.files;
      let file = files[0];
      var formData = new FormData();
      formData.append("file", file);
      console.log(formData);
      $.ajax({
        url: "http://127.0.0.1:4000/set/images/", // 进行交换数据
        type: "post",
        data: formData,
        // 设置属性为false
        contentType: false,
        processData: false,
        success(resp) {
          console.log(resp);
        }
      });
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
            $("#email").attr("disabled", false);
            text.value = "获取验证码"
            clearTimeout(timer);
            error_msg.value = resp.error_msg;
          }
        },
      });

    }


    //  提交表单注册进行判断
    const register = () => {
      // console.log(school.value);
      console.log(code_t);
      if (code_input.value === "") {
        error_msg.value = "验证码不能为空哦~"
        return;
      }
      //  双向比对 - 前后台同时比对
      if (code_t === code_input.value) {
        console.log("你好呀");
      } else {
        error_msg.value = "验证码有问题哦~"
        return
      }


      //  进行文件上传
      let dd = document.querySelector("#file");
      let files = dd.files;
      let file = files[0];
      var formData = new FormData();
      formData.append("file", file);
      formData.append("username", username.value);
      formData.append("password", password.value);
      formData.append("pwd2", pwd2.value);
      formData.append("email", email.value);
      console.log(formData);
      //  注册接口
      $.ajax({
        url: "http://127.0.0.1:4000/user/msg/register/",
        type: "post",
        dataType: 'json',
        data: formData,
        contentType: false, //必须
        processData: false, //必须
        success(resp) {
          // console.log(1);
          if (resp.error_msg === "success") {
            store.dispatch("login", {
              username: username.value,
              password: password.value,
              success() {
                store.dispatch("getinfo", {
                  success() {
                    router.push({ name: "home" });
                  },
                });
              }
            });
            // router.push({ name: "user_msg_login" });

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
      error_msg,
      email,
      register,
      button_email,
      close,
      email_msg,
      text,
      code_input,
      button_images,
      previewFile
      // school
    };


  },
};

</script>

<style scoped lang="less">
.error-message,
span {
  color: black !important;
}

#email {
  width: auto !important;
  background-color: rgb(78, 78, 78);
  border: 0;
  color: white;
}

.col-3 {
  width: 100%;

  .form {
    display: flex;
    flex-direction: column;
    align-items: center;
    width: 100%;

    .form-control {
      border: 0px solid rgb(255, 255, 255, 0) !important;
    }

    label {
      color: rgb(61, 61, 61);
    }

    input {
      width: 16vw;
    }

    button.btn {
      background-color: rgba(0, 201, 184, 0) !important;
      border: 2px solid rgba(0, 0, 0, 0.989) !important;
      color: black !important;
      border-radius: 0 !important;
      transition: all 0.6s !important;

      &:hover {
        background-color: rgb(10, 10, 10) !important;
        color: white !important;
      }
    }
  }
}
</style>
