<template>
  <!-- playing 是对战界面 
       matching 是匹配
   -->
  <div class="animate__animated animate__fadeInDown">
    <PlayGround v-if="$store.state.pk.status === 'playing'" />
    <MatchGround v-if="$store.state.pk.status === 'matching'" />
    <ResultBoard v-if="$store.state.pk.loser != 'none'" />
  </div>
</template>

<script>
import PlayGround from '../../components/PlayGround.vue'
// 匹配界面
import MatchGround from '../../components/MatchGround.vue'
// 胜负界面
import ResultBoard from '../../components/ResultBoard.vue'

import { onMounted, onUnmounted } from 'vue'
import { useStore } from 'vuex'

export default {
  name: 'Gobang',
  components: {
    PlayGround,
    MatchGround,
    ResultBoard
  },
  setup() {
    const store = useStore()
    const socketUrl = `ws://127.0.0.1:4000/websocket/${store.state.user.token}/`

    //  当前页面打开的时候执行
    let socket = null
    onMounted(() => {
      // 自行先设计一个头像
      store.commit('updateLoser', 'none') // 更新状态
      store.commit('updateOpponent', {
        username: '???',
        photo:
          'https://cdn.acwing.com/media/article/image/2022/08/09/1_1db2488f17-anonymous.png'
      })

      socket = new WebSocket(socketUrl)

      //  打开的时候
      socket.onopen = () => {
        console.log('connected!')
        // 更新到全局
        store.commit('updateSocket', socket)
      }

      // 从客户端接收消息的时候
      socket.onmessage = msg => {
        const data = JSON.parse(msg.data)
        // console.log(data);
        if (data.event === 'start-matching') {
          //  匹配成功
          store.commit('updateOpponent', {
            username: data.opponent_username,
            photo: data.opponent_photo
          })

          // 成功之后跳转到成功页面 (这里加一个延迟 体现更好的交互性)
          setTimeout(() => {
            store.commit('updateStatus', 'playing')
          }, 200)
          // 更新一下后端传过来的地图u
          store.commit('updateGame', data.game)
        } else if (data.event === 'move') {
          console.log(data)
          const game = store.state.pk.gameObject
          const [snake0, snake1] = game.snakes
          snake0.set_direction(data.a_direction)
          snake1.set_direction(data.b_direction)
        } else if (data.event === 'result') {
          //  这里是取出两条蛇出来 用来进行反馈一个结果
          console.log(data)
          const game = store.state.pk.gameObject
          const [snake0, snake1] = game.snakes

          //  判断两条蛇去世的状态
          if (data.loser === 'all' || data.loser === 'A') {
            snake0.status = 'die' //死
          }
          if (data.loser === 'all' || data.loser === 'B') {
            snake1.status = 'die' // 死
          }

          //  去世之后更新失败者
          store.commit('updateLoser', data.loser)
        }
      }

      // 关闭时候执行
      socket.onclose = () => {
        console.log('disconnected!')
        store.commit('updateStatus', 'matching')
      }
    })

    onUnmounted(() => {
      socket.close() // 当前页面被取消挂载的时候  断开连接
    })
  }
}
</script>

<style scoped lang="less">
</style>
