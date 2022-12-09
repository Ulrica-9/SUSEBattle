<template>
  <div class="game">
    <section class="Snake">
      <img src="../../assets/images/Snake.png" alt="贪吃蛇" />
      <div class="entrance">
        <span class="title">贪吃蛇</span>
        <div class="btn-group">
          <button @click="singlePlaying('snakeSinglePlaying')">单人闯关</button>
          <button>多人排位赛</button>
          <button>双人单机对战</button>
        </div>
      </div>
    </section>
    <section class="Gobang">
      <img src="../../assets/images/Gobang.jpg" alt="五子棋" />
      <div class="entrance">
        <span class="title">五子棋</span>
        <div class="btn-group">
          <!-- <router-link class="nav-link" :to="{ name: 'pk' }" role="button">
            登录
          </router-link> -->
          <button @click="singlePlaying('gobangSinglePlaying')">单人闯关</button>
          <button>多人排位赛</button>
          <button>双人单机对战</button>
        </div>
      </div>
    </section>
  </div>
</template>
<script>
import router from "../../router/index";
export default {
  name: 'GameList',
  setup(props, context) {
    function singlePlaying(gameType) {
      if (gameType === "snakeSinglePlaying") {
        context.emit('snakeSinglePlaying', "isSnakeSinglePlaying", true)
      } else if (gameType === "gobangSinglePlaying") {
        context.emit('gobangSinglePlaying', "isGobangSinglePlaying", true)
      }
      // router.push({ name: "user_msg_login" });
    }
    return {
      singlePlaying
    }
  }
}
</script>
<style lang="less" scoped>
.game {
  display: flex;
  margin: 50px;
  padding: 50px;
  background-color: rgb(230, 229, 229);

  section:nth-child(2n - 1) {
    margin-right: 50px;
  }

  .Snake,
  .Gobang {
    display: flex;
    flex-direction: column;
    background-color: rgb(255, 255, 255);
    width: 350px;
    box-shadow: 0px 0px 6px 4px rgba(0, 0, 0, 0.2);
    border-radius: 6px;
    overflow: hidden;
    transition: all 0.3s;

    &:hover {
      transform: scale(1.05);
    }

    img {
      width: 100%;
      flex-basis: 80%;
      display: block;
      object-fit: cover;
      overflow: hidden;
    }

    .entrance {
      flex-basis: 20%;
      margin: 10px 0;
      display: flex;
      flex-direction: column;
      justify-content: center;
      align-items: center;

      .title {
        margin-bottom: 20px;
        color: black;
      }

      .btn-group {
        display: flex;
        justify-content: center;

        button {
          display: block;
          background-color: rgba(255, 255, 255, 0);
          border: 2px solid rgb(0, 0, 0);
          transition: all 0.15s;
          padding: 5px 5px 5px 5px;

          &:hover {
            background-color: rgb(10, 10, 10);
            color: white;
          }

          &:nth-child(-n + 2) {
            margin-right: 10px;
          }
        }
      }
    }
  }
}
</style>
