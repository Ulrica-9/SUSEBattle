<template>
  <!--  导航栏 -->
  <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container">
      <!-- <a class="navbar-brand" href="/">King of Bots</a> -->
      <!-- 注释掉的内容进行与router-link平行替换 就不会刷新页面进行跳转了 -->
      <router-link class="navbar-brand" :to="{ name: 'home' }">
        对战平台
      </router-link>
      <!--  左边选项 -->
      <div class="collapse navbar-collapse" id="navbarText">
        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
          <li class="nav-item">
            <!-- class中如果有active是默认选项 -->
            <!-- <a class="nav-link" aria-current="page" href="/pk/">对战</a> -->
            <!-- :calss(v-bind:class) 代表写的是表达式 -->
            <router-link :class="route_name == 'pk_index' ? 'active nav-link' : 'nav-link'" :to="{ name: 'pk_index' }">
              对战
            </router-link>
          </li>
          <li class="nav-item">
            <!-- <a class="nav-link" href="/record/">对局列表</a> -->
            <router-link :class="
                route_name == 'record_index' ? 'active nav-link' : 'nav-link'
              " :to="{ name: 'record_index' }">
              对局列表
            </router-link>
          </li>
          <li class="nav-item">
            <!-- <a class="nav-link" href="/ranklist/">排行榜</a> -->
            <router-link :class="
                route_name == 'ranklist_index' ? 'active nav-link' : 'nav-link'
              " :to="{ name: 'ranklist_index' }">
              排行榜
            </router-link>
          </li>
        </ul>

        <!-- 右边 -->
        <ul class="navbar-nav" v-if="$store.state.user.is_login">
          <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
              <img :src="$store.state.user.photo" alt="" srcset="" class="img_photo"> &nbsp;
              {{ $store.state.user.username }}
            </a>

            <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
              <li>
                <router-link class="dropdown-item" :to="{ name: 'user_bot_index' }">
                  个人仓库
                </router-link>
              </li>
              <li>
                <hr class="dropdown-divider" />
              </li>
              <li>
                <a class="dropdown-item" href="#" @click="logout">退出</a>
              </li>
            </ul>
          </li>
        </ul>

        <!-- 右边 -->
        <ul class="navbar-nav" v-else>
          <!-- 登录 -->
          <li class="nav-item">
            <router-link class="nav-link" :to="{ name: 'user_msg_login' }" role="button">
              登录
            </router-link>
          </li>

          <!-- 注册 -->
          <li class="nav-item">
            <router-link class="nav-link" :to="{ name: 'user_msg_register' }" role="button">
              注册
            </router-link>
          </li>
        </ul>
      </div>
    </div>
  </nav>
</template>

<script>
// 取得当前在哪个页面 并对其标签进行高亮显示
import { useRoute } from "vue-router";
import { computed } from "vue";
import { useStore } from "vuex";

export default {
  setup() {
    const store = useStore();
    const route = useRoute();
    let route_name = computed(() => route.name);

    //  触发函数
    const logout = () => {
      store.dispatch("logout");
    };

    return {
      route_name,
      logout,
    };
  },
};
</script>


<style scoped lang="less">
.img_photo {
  border-radius: 50%;
  width: 2vw;
}

.navbar {
  padding: 0;
  display: flex;
  height: 8vh;
  background-color: rgba(240, 255, 255, 0);

  a {
    display: inline-block;
    padding-top: 0;
    padding-bottom: 0;
    margin-top: 0;
    margin-bottom: 0;
    height: 8vh;
    line-height: 8vh;
    height: 8vh;
    transition: all 0.3s;

    &.navbar-brand,
    &.nav-link {
      padding: 0 10px;
    }

    &:hover {
      background-color: white;
      color: black;
    }
  }
}

.bg-dark {
  background-color: rgb(65, 62, 62) !important;
}
</style>

