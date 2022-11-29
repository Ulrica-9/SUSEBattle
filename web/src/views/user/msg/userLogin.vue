
<template>
  <ContentField>
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
          <button type="submit" class="btn btn-primary" style="width: 100%">
            登录
          </button>
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
    };
  },
};
</script>

<style scope>
.error-message {
  color: red;
}
</style>
