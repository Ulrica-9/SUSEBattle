<template>
  <!-- 游戏的主界面(pk页面) -->
  <div class="playground">
    <GameMap />
  </div>
</template>

<script>
import GameMap from "./GameMap.vue";

export default {
  components: {
    GameMap,
  },
};
</script>


<style scope>
.playground {
  width: 60vw;
  height: 70vh;
  margin: 40px auto;
}
</style>