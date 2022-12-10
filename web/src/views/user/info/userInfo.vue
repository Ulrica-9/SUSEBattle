
<template>
  <div class="impression">
    <!--  会显示 -->

    <input type="file" name="file" id="file" @change="previewFile"><br>
    <!-- <img src="http://localhost:4000/img/123.png" height="200" alt="Image preview..." class="img_"> -->
    <img :src="$store.state.user.photo" class="avatar" alt="哎呀，出错啦...">

    <button type="button" @click="button_images" class="btn btn-warning">更改头像</button>
  </div>
  <div class="pannel">
    <div class="personInfo">
      <h1>个人信息</h1>
      <section class="items">
        <div><span class="iconfont">&#xe60a;</span></div>
        <div><input type="text" name="name" id="name" placeholder="姓名" class="outline"></div>
        <div><span class="iconfont">&#xe659;</span></div>
        <div>
          <input type="text" name="sex" id="sex" placeholder="性别" class="outline">
        </div>
        <div><span class="iconfont">&#xe624;</span></div>
        <div><input type="email" name="email" id="email" placeholder="电子邮件" class="outline" disabled></div>
        <div><span class="iconfont">&#xe78b;</span></div>
        <div><textarea cols="30" rows="4" name="content" id="content" placeholder="简介..." class="outline"></textarea></div>
        <div><span class="iconfont">&#xe659;</span></div>
        <div><input for="school" type="text" id="school" name="school" placeholder="学校" class="outline school" disabled></div>
      </section>
      <span>
        {{error_msg}}
      </span>
      <div class="submit"><button @click="update_data">更新信息</button></div>
    </div>
  </div>
</template>

<script>
import { useStore } from "vuex";
import { onMounted } from 'vue';
import $ from "jquery";
import { ref } from "vue";
export default {
  name: "userInfo",
  setup() {
    let error_msg = ref("");
    const store = useStore();
    //  拿到用户数据
    function pp() {
      let username = document.querySelector("#name");
      let sex = document.querySelector("#sex");
      let email = document.querySelector("#email");
      let content = document.querySelector("#content");
      let school = document.querySelector(".school");
      $.ajax({
        url: "http://localhost:4000/user/msg/info/",
        type: "get",
        headers: {
          Authorization: "Bearer " + store.state.user.token, // 当前Token
        },
        success(resp) {
          if (resp.error_message === "success") {
            if (resp.username === null) {
              username.placeholder = "未知"
            } else {
              username.value = resp.username
            }
            if (resp.sex === null) {
              sex.placeholder = "未知"
            } else {
              sex.value = resp.sex
            }
            if (resp.email === null) {
              email.placeholder = "电子邮箱"
            } else {
              email.placeholder = resp.email
            }
            if (resp.personal === null) {
              content.placeholder = "该用户很懒,什么都没写!"
            } else {
              content.placeholder = resp.personal
            }
            if (resp.school === null) {
              school.placeholder = "学校"
            } else {
              school.value = resp.school
            }
          }
        }
      });
    }
    onMounted(() => {
      pp();
    })


    // 图片回显
    function previewFile() {
      var preview = document.querySelector('.avatar');
      var file = document.querySelector('input[type=file]').files[0];
      var reader = new FileReader();
      reader.addEventListener("load", function () {
        preview.src = reader.result;
      }, false);

      if (file) {
        reader.readAsDataURL(file);
      }
    }
    // 更新用户信息
    const update_data = () => {
      error_msg.value = ""
      let username = document.querySelector("#name");
      let sex = document.querySelector("#sex");
      let email = document.querySelector("#email");
      let content = document.querySelector("#content");
      let school = document.querySelector(".school");
      $.ajax({
        url: "http://127.0.0.1:4000/update/user/data/", // 进行交换数据
        type: "post",
        data: {
          username: username.value,
          sex: sex.value,
          email: email.value,
          content: content.value,
          school: school.value
        },
        headers: {
          Authorization: "Bearer " + store.state.user.token,
        },
        success(resp) {
          // console.log(resp);
          if (resp.msg === "success") {
            alert("更改成功");
            window.location.reload()
          } else {
            error_msg.value = resp.msg
          }
        }
      });
    }
    //  上传头像
    const button_images = () => {
      //  判断文件是否为空
      let xx = document.querySelector("#file");
      if (xx.value == "") {
        alert("没有选择任何图片");
        return
      }
      //  进行文件上传
      let dd = document.querySelector("#file");
      let files = dd.files;
      let file = files[0];
      var formData = new FormData();
      // 简单格式判断
      var index = file.name.lastIndexOf('.')
      var str = file.name.substr(index + 1)
      if (!(['png', 'jpg', 'jpeg', 'bmp', 'gif', 'webp', 'psd', 'svg', 'tiff'].indexOf(str.toLowerCase()) !== -1)) {
        alert("格式非法!");
        return
      }
      formData.append("file", file);
      console.log(formData);
      $.ajax({
        url: "http://127.0.0.1:4000/set/images/", // 进行交换数据
        type: "post",
        data: formData,
        headers: {
          Authorization: "Bearer " + store.state.user.token,
        },
        // 设置属性为false
        contentType: false,
        processData: false,
        success(resp) {
          // console.log(resp);
          if (resp.msg === "success") {
            alert("更改成功");
            window.location.reload()
          }
        }
      });
    }
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

    return {
      button_images,
      previewFile,
      update_data,
      error_msg
    }
  }
}
</script>

<style scoped lang="less">
.error-message {
  color: red;
}
.iconfont {
  font-size: 26px;
}
button,
input,
textarea {
  display: block;
  outline: none;
  background-color: rgba(224, 230, 229, 0);
  border: 1px solid rgba(216, 220, 219, 0);
  border-bottom: 1px solid rgb(194, 200, 199);
  transition: all 0.15s;
  padding: 5px 5px 5px 5px;
  border-radius: 0.375rem;
  resize: none;
  scrollbar-width: none;
  font-size: 16px;
  color: black;
  padding: 10px 0px 10px 10px;

  &::-webkit-scrollbar {
    display: none;
  }
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
