<template>
  <div class="main">
    <header class="animate__animated animate__fadeInDown">
      <p>欢迎来到 Bots ZiMei</p>
      <p>你可以创建游戏桌来进行对局。</p>
    </header>
    <section class="animate__animated animate__fadeInUp">
      <h2>Bots ZiMei 是什么？网站功能</h2>
      <ul>
        Bots ZiMei 是在线的程序对抗平台，AI
        可以根据已有的游戏规则进行比赛一决胜负。同时支持人机，人人对战
      </ul>
      <h2>我不知道怎么用 Bots ZiMei 啊？使用方法</h2>
      <ul>
        <li>1. 注册 Bots ZiMei 账户</li>
        <li>2. 点击</li>
        <li>3. 游戏规则 查看Snake游戏规则,按照游戏说明写自己的 AI 程序</li>
        <li>4. 点击右上角账户名，进入我的 Bot 页面，创建 Bot 并上传程序</li>
        <li>5. 回到对战页面，点击开始匹配，与他人或其他 Bot 开始对战</li>
      </ul>
    </section>
    <router-link
      class="start animate__animated animate__fadeInUp"
      :to="{ name: 'pk_index' }"
    >
      <span class="iconfont">开始吧!&nbsp;&#xeb08;</span>
    </router-link>
  </div>
</template>
<script>
export default {}
</script>
<style lang="less" scoped>
div.main {
  font-size: 16px;
  color: #111111;
  width: 900px;
  margin: 50px auto;
  position: relative;

  header,
  section,
  .start {
    border-radius: 18px;
    background-color: rgb(243, 243, 243);
    padding: 20px;
    // margin-top: 20px;
    box-shadow: 6px 6px 20px #b0dde830, -6px -6px 30px #a1dccd4e;
  }

  header {
    width: 50%;
    font-size: 28px;
    display: flex;
    flex-direction: column;
    justify-content: center;
    text-shadow: 2px 2px #c8f0ef;
    background: linear-gradient(
      180deg,
      rgba(255, 255, 255, 1) 0%,
      rgba(249, 249, 249, 1) 15%,
      rgba(242, 242, 242, 0) 100%
    );
  }

  section {
    h2 {
      display: inline-block;
      text-shadow: 2px 2px #c8f0ef;
      background-image: url(https://t8.baidu.com/it/u=1501552470,2690656309&fm=167&app=3000&f=PNG&fmt=auto&q=100&size=f624_21);
      background-repeat: repeat-x;
      background-position-y: bottom;
      background-size: 100% 8px;
    }

    ul {
      padding-top: 20px;

      li:nth-child(-n + 4) {
        margin-bottom: 7px;
      }
    }
  }

  a.start {
    display: inline-block;
    position: absolute;
    top: 25%;
    right: 50px;
    transition: all 0.3s;
    color: #111111;
    padding: 15px;
    box-shadow: 6px 6px 6px #98d4d766, -6px -6px 20px #a2e1d14e;
  }

  a.start:hover {
    color: #0ea7a5;
    transform: translate(5px, -5px);
  }
}
</style>
