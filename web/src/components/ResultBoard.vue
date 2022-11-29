<template>
  <!--谁赢谁输  -->
  <div class="result-borad">
    <div class="result-board-text" v-if="$store.state.pk.loser === 'all'">
      平局。
    </div>
    <div class="result-board-text" v-else-if="$store.state.pk.loser === 'A' &&$store.state.pk.a_id === parseInt($store.state.user.id)">
      你输了。
    </div>
    <div class="result-board-text" v-else-if="$store.state.pk.loser === 'B' &&$store.state.pk.b_id === parseInt($store.state.user.id)">
      你输了。
    </div>

    <div class="result-board-text" v-else>
      你赢了。
    </div>
    <div class="result-board-btn">
      <button @click="restart" type="button" class="btn btn-warning btn-lg">
        重玩
      </button>
    </div>
  </div>
</template>

<script>
import { useStore } from 'vuex';

//  触发重玩
export default {
  setup() {
    const store = useStore();

    const restart = () => {
      store.commit("updateStatus", "matching"); // 界面 playing - > matching
      store.commit("updateLoser", "none"); // 积分版 : 失败者改为无
      store.commit("updateOpponent", {
        username: "???",
        photo: "https://cdn.acwing.com/media/article/image/2022/08/09/1_1db2488f17-anonymous.png",
      })
    }
    return {
      restart
    };
  }
}
</script>

<style scoped>
.result-borad {
  position: absolute;
  top: 20vh;
  left: 35vw;
  height: 40vh;
  width: 30vw;
  background-color: rgba(255, 255, 255, 0.5);
}
.result-board-text {
  text-align: center;
  color: white;
  font-size: 50px;
  font-weight: 600;
  font-style: italic;
  padding-top: 5vh;
}

.result-board-btn {
  padding-top: 7vh;
  text-align: center;
}
</style>
