
<template>
  <div class="impression">
    <img src="./images/宜宾旅约拍.png" alt="" class="avatar">
    <div class="perInfo"><span>姓名:</span><span>段宏蔚</span></div>
    <div class="perInfo"><span>性别:</span><span>男</span></div>
  </div>
  <div class="pannel">
    <div class="personInfo">
      <h1>个人信息</h1>
      <section class="items">
        <div><span class="iconfont">&#xe60a;</span></div>
        <div><input type="text" name="" id="" placeholder="用户名" class="outline"></div>
        <div><span class="iconfont">&#xe624;</span></div>
        <div><input type="email" name="" id="" placeholder="邮箱" class="outline"></div>
        <div><span class="iconfont">&#xe78b;</span></div>
        <div><textarea cols="30" rows="4" placeholder="简介..." class="outline"></textarea></div>
        <div><span class="iconfont">&#xe659;</span></div>
        <div><input type="text" placeholder="学校" class="outline"></div>
      </section>
      <div class="submit"><button>更新信息</button></div>
    </div>
  </div>
</template>

<script>
import { onMounted } from 'vue'
export default {
  name: "userInfo",
  setup() {
    function btns() {
      let btns = document.querySelectorAll('.outline')
      for (let n = 0; n < btns.length; n++) {
        btns[n].onfocus = () => {
          btns[n].style.outline = 'RGBA(93, 214, 204, 0.2) solid thick'
          // btns[n].style.backgroundColor = 'RGBA(93,214,204,0.4)'
        }
        btns[n].onblur = () => {
          btns[n].style.outline = 'none'
          // btns[n].style.backgroundColor = 'rgba(151, 228, 223, 0.477)'
        }
      }
    }

    onMounted(() => {
      btns();
    })
  }
}
</script>

<style scoped lang="less">
.error-message {
  color: red;
}

.impression {
  display: inline-flex;
  flex-direction: column;
  align-items: center;
  margin-top: 100px;
  margin-left: 100px;
  position: fixed;
  border-radius: 12px;
  // background-color: #f3f3f3;
  padding: 20px;
  font-size: 14px;
  border-radius: 0px;
  background: rgb(254, 254, 254);
  box-shadow: 5px 5px 0px #bebebe, -5px -5px 0px #e2e2e2;

  img.avatar {
    display: block;
    box-shadow: 6px 6px 20px #e7e8e930, -6px -6px 30px #dde0df4e;
    width: 150px;
    height: 150px;
    border-radius: 50%;
    border: 2px solid white;
    margin-bottom: 20px;
  }

  .perInfo {
    display: flex;
    margin-bottom: 10px;

    span {
      display: flex;
      // width: 60%;
      background-color: rgb(240, 240, 240);
      justify-content: center;
      border-radius: 20px;
      padding: 6px 16px;
    }

    span:nth-child(1) {
      margin-right: 10px;
    }
  }
}

.pannel {
  margin: 100px auto;
  width: 400px;
  padding: 20px;
  // box-shadow: 6px 6px 20px #e8eaeb30, -6px -6px 30px #dee1e04e;
  border-radius: 0px;
  background: #fefefe;
  box-shadow: 10px 10px 0px #bebebe, -10px -10px 0px #ebebeb;

  .personInfo {
    display: flex;
    flex-direction: column;

    h1 {
      display: inline-block;
      width: 40%;
      margin: 20px;
      // padding-bottom: 20px;
      font-size: 28px;
      font-weight: 500;
      color: black;
      // border-bottom: 2px solid RGBA(0, 201, 183);
      text-shadow: 2px 2px #ebeded;
      background-image: url(https://t8.baidu.com/it/u=1501552470,2690656309&fm=167&app=3000&f=PNG&fmt=auto&q=100&size=f624_21);
      background-repeat: repeat-x;
      background-position-y: bottom;
      background-size: 100% 8px;
    }

    .items {
      display: grid;
      grid-template-columns: 1fr 3fr;
      column-gap: 5px;
      row-gap: 5px;

      div {
        margin-bottom: 10px;
      }

      div:nth-child(2n - 1) {
        display: flex;
        align-items: center;
        justify-content: flex-end;
        justify-content: center;
      }

      // div:nth-child(2n) {}

      input {
        width: 100%;
        height: 100%;
      }

      textarea {
        width: 100%;
        height: 100%;
      }
    }

    .submit {
      display: flex;
      justify-content: center;
      margin-top: 20px;

      button {
        border-radius: 4px;
        padding: 4px 4px;
        font-size: 10px;
        background-color: rgba(167, 167, 167, 0);
        border: 2px solid black;
        border-radius: 0px;
        color: black;
        width: 80px;
        font-size: 16px;
        transition: all 0.3s;

        &:hover {
          background-color: black;
          color: white;
        }
      }
    }
  }
}
</style>
