<template>
  <!-- 开始匹配的界面 -->
  <div class="matchground">
    <div class="row">
      <div class="col-4">
        <div class="user-photo">
          <img v-bind:src="$store.state.user.photo" alt="" />
        </div>
        <div class="user-name">
          {{ $store.state.user.username }}
        </div>
      </div>

      <div class="col-4">
        <div class="user-select-bot">
          <select v-model="select_bot" class="form-select" aria-label="Default select example">
            <option value="-1" selected>亲自出马</option>
            <option v-for="bot in bots" :key="bot.id" :value="bot.id">
              {{ bot.name  }}
            </option>
          </select>
        </div>
      </div>

      <div class="col-4">
        <div class="user-photo">
          <img v-bind:src="$store.state.pk.opponent_photo" alt="" />
        </div>
        <div class="user-name">
          {{ $store.state.pk.opponent_username }}
        </div>
      </div>

      <div class="col-12 text-position">
        <button type="button" class="btn btn-primary btn-lg" @click="click_match_btn">
          {{ match_btn_info }}
        </button>
      </div>

    </div>
  </div>
</template>

<script>
import { ref } from "vue";
import { useStore } from "vuex";
import $ from "jquery"

export default {
  setup() {
    let match_btn_info = ref("开始匹配");
    const store = useStore();

    let bots = ref([]);
    let select_bot = ref("-1");

    const click_match_btn = () => {
      if (match_btn_info.value === "开始匹配") {
        match_btn_info.value = "取消";
        // console.log(select_bot.value);
        //  向后端发送请求
        store.state.pk.socket.send(
          JSON.stringify({
            event: "start-matching",
            bot_id: select_bot.value
          })
        );
      } else {
        match_btn_info.value = "开始匹配";
        store.state.pk.socket.send(
          JSON.stringify({
            event: "stop-matching",
          })
        );
      }
    };


    //  刷新列表 得到用户所有bot
    const refresh_bots = () => {
      $.ajax({
        url: "http://127.0.0.1:4000/user/get/bot/",
        type: "get",
        headers: {
          Authorization: "Bearer " + store.state.user.token,
        },
        success(resp) {
          // 成功的话就会返回列表 存入到bots 列表中
          bots.value = resp;
        },
      });
    };
    refresh_bots();

    return {
      match_btn_info,
      click_match_btn,
      bots,
      select_bot
    };
  },
};
</script>


<style scope>
.matchground {
  width: 60vw;
  height: 70vh;
  margin: 40px auto;
  background-color: pink;
}
.user-photo {
  margin-top: 15vh;
  text-align: center;
}
.user-photo img {
  border-radius: 50%;
  width: 20vh;
}
.user-name {
  padding-top: 2vh;
  text-align: center;
  font-weight: 500;
  font-size: 20px;
}
.text-position {
  text-align: center;
  margin-top: 10vh;
}

.user-select-bot {
  padding-top: 40vh;
}
.user-select-bot > select {
  width: 60%;
  margin: 0 auto;
}
</style>