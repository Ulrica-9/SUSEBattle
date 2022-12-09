
<template>
  <ContentField class="animate__animated animate__bounceInRight">
    <div class="row justify-content-md-center">
      <div class="col-3">
        <!--  表单样式 -->
        <form @submit.prevent="login">
          <div class="mb-3">
            <label for="username" class="form-label">用户名</label>
            <input v-model="username" type="text" class="form-control" id="username" placeholder="请输入用户名" />
          </div>
          <div class="mb-3">
            <label for="password" class="form-label">密码</label>
            <input v-model="password" type="password" class="form-control" id="password" placeholder="请输入密码" />
          </div>
          <div class="error-message">{{ error_message }}</div>
          <button type="submit" class="btn btn-primary" style="width: 40%">
            登录
          </button>

          <button type="button" class="btn btn-primary" style="width: 40%;margin-left: 3vw;" @click="cancle">
            取消
          </button>

          <div>
            不想注册？ 试试游客登录吧!
            <button type="button" class="btn btn-warning" style="width: 100%;" @click="login_player">
              游客登录
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
import $ from "jquery";
import router from "../../../router/index";
export default {
  components: {
    ContentField,
  },
  setup() {
    const store = useStore();
    let username = ref("");
    let password = ref("");
    let error_message = ref("");
    //  进入登录页面就判断本地是否有token
    const jwt_token = localStorage.getItem("jwt_token");
    if (jwt_token) {
      store.commit("updateToken", jwt_token);
      // 验证合法性 [云端获取用户信息]
      store.dispatch("getinfo", {
        // 成功就自动跳入首页
        success() {
          router.push({ name: "home" });
        },
        error() { },
      });
    }


    //  取消
    const cancle = () => {
      router.push({ name: "user_msg_register" });
    }


    //  游客登录
    const login_player = () => {
      error_message.value = ""; // 每次的提交的时候记得清空
      $.ajax({
        url: "http://127.0.0.1:4000/play/temp/",
        type: "post",
        success(resp) {
          // console.log(resp);
          if (resp.error_msg === "success") {
            store.dispatch("login", {
              username: resp.username,
              password: resp.password,
              success() {
                store.dispatch("getinfo", {
                  success() {
                    router.push({ name: "home" });
                  },
                });
              }
            });

          } else {
            error_message.value = resp.error_msg;
          }
        },
      });
    }


    // 登录
    const login = () => {
      error_message.value = ""; // 每次的提交的时候记得清空
      store.dispatch("login", {
        username: username.value,
        password: password.value,
        success() {
          store.dispatch("getinfo", {
            success() {
              router.push({ name: "home" });
            },
          });
        },
        error() {
          error_message.value = "用户名或密码错误。";
        },
      });
    };

    return {
      username,
      password,
      error_message,
      login,
      cancle,
      login_player
    };
  },
};
</script>

<style scoped lang="less">
.error-message {
  color: black;
}

.form {
  display: flex;
  flex-direction: column;
  align-items: center;
}

.form-control {
  border: 0px solid rgb(255, 255, 255, 0) !important;
}

label {
  color: rgb(61, 61, 61);
}

input {
  width: 16vw;
}

.btn {
  background-color: rgba(0, 201, 184, 0);
  border: 2px solid rgba(0, 0, 0, 0.989);
  color: black;
  border-radius: 0ch;
  transition: all 0.6s;

  &:hover {
    background-color: rgb(10, 10, 10);
    color: white;
  }
}

.btn:active {
  background-color: rgb(93, 214, 204);
}
</style>

