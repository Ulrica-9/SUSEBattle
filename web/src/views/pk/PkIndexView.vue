<template>
  <!-- playing 是对战界面 
       matching 是匹配
   -->
  <div class="animate__animated animate__fadeInUp">
    <GameList v-if="showGameList" @GobangSinglePlaying="GobangSinglePlaying"></GameList>
    <Snake v-if="showSnake"></Snake>
    <Gobang v-if="showGobang"></Gobang>
  </div>
</template>

<script>
//游戏列表
import GameList from './GameList.vue'
//五子棋
import Gobang from './Gobang.vue'
//贪吃蛇
import Snake from './Snake.vue'
import { toRefs, reactive } from 'vue'
export default {
  name: 'PkIndexView',
  components: {
    Snake,
    Gobang,
    GameList
  },
  setup() {
    let isShow = reactive({
      showGameList: true,
      showSnake: false,
      showGobang: false
    })
    function GobangSinglePlaying(isGobangSinglePlaying) {
      if (isGobangSinglePlaying) {
        isShow.showGameList = false
        isShow.showGobang = true
      }
    }
    return {
      ...toRefs(isShow),
      GobangSinglePlaying
    }
  }
}
</script>

<style scoped lang="less"></style>
