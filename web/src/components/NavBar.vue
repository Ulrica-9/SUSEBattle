
<template>
  <!-- 
      navbar-light   bg-light - 浅色
      navbar-dark   bg-dark  - 深色
   -->
  <!--  导航栏 -->
  <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container">
      <!-- <a class="navbar-brand" href="/">King of Bots</a> -->
      <!-- 注释掉的内容进行与router-link平行替换 就不会刷新页面进行跳转了 -->
      <router-link class="navbar-brand" :to="{ name: 'home' }">
        King of Bots
      </router-link>
      <!--  左边选项 -->
      <div class="collapse navbar-collapse" id="navbarText">
        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
          <li class="nav-item">
            <!-- class中如果有active是默认选项 -->
            <!-- <a class="nav-link" aria-current="page" href="/pk/">对战</a> -->
            <!-- :calss(v-bind:class) 代表写的是表达式 -->
            <router-link
              :class="route_name == 'pk_index' ? 'active nav-link' : 'nav-link'"
              :to="{ name: 'pk_index' }"
            >
              对战
            </router-link>
          </li>
          <li class="nav-item">
            <!-- <a class="nav-link" href="/record/">对局列表</a> -->
            <router-link
              :class="
                route_name == 'record_index' ? 'active nav-link' : 'nav-link'
              "
              :to="{ name: 'record_index' }"
            >
              对局列表
            </router-link>
          </li>
          <li class="nav-item">
            <!-- <a class="nav-link" href="/ranklist/">排行榜</a> -->
            <router-link
              :class="
                route_name == 'ranklist_index' ? 'active nav-link' : 'nav-link'
              "
              :to="{ name: 'ranklist_index' }"
            >
              排行榜
            </router-link>
          </li>
        </ul>

        <!-- 右边 -->
        <ul class="navbar-nav">
          <li class="nav-item dropdown">
            <a
              class="nav-link dropdown-toggle"
              href="#"
              id="navbarDropdown"
              role="button"
              data-bs-toggle="dropdown"
              aria-expanded="false"
            >
              luotianyu
            </a>
            <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
              <li>
                <!-- <a class="dropdown-item" href="/user/bot/">我的Bot</a> -->
                <router-link
                  class="dropdown-item"
                  :to="{ name: 'user_bot_index' }"
                >
                  排行榜
                </router-link>
              </li>
              <li><hr class="dropdown-divider" /></li>
              <li><a class="dropdown-item" href="#">退出</a></li>
            </ul>
          </li>
        </ul>
      </div>
    </div>
  </nav>
</template>

<script>
// 取得当前在哪个页面 并对其标签进行高亮显示
import { useRoute } from "vue-router";
import { computed } from "vue"; // 实时计算

export default {
  setup() {
    const route = useRoute();
    let route_name = computed(() => route.name);

    return {
      route_name,
    };
  },
};
</script>

<style scope>
</style>
