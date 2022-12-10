<template>
  <!-- 游戏的主界面(pk页面) -->
  <div class="playground">
    <GameMap />
    <div v-if="$store.state.pk.a_id === parseInt($store.state.user.id)">
      出生在黄色方
    </div>
    <div v-else>
      出生在粉色方
    </div>
  </div>
</template>

<script>
import GameMap from "./GameMap.vue";

//  触发重玩



export default {
  components: {
    GameMap,
  },
  setup() {


    const restart = () => {

    }

    return {
      restart
    };
  }

}
</script>


<style scoped>
.playground {
  width: 60vw;
  height: 70vh;
  margin: 40px auto;
}
</style>