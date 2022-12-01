<template>
  <!-- 游戏的主界面(pk页面) -->
  <div class="playground">
    <GameMap />
    <div v-if="$store.state.pk.a_id === parseInt($store.state.user.id)">
      出生在蓝色方
    </div>
    <div v-else>
      出生在红色方
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


<style scope>
.playground {
  width: 60vw;
  height: 70vh;
  margin: 40px auto;
}
</style>