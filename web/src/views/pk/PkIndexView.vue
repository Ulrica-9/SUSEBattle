<template>
  <!-- playing 是对战界面 
       matching 是匹配
   -->
  <div class="animate__animated animate__fadeInUp">
    <router-view></router-view>
  </div>
</template>

<script>
export default {
  name: 'PkIndexView',
}
</script>

<style scoped lang="less"></style>
