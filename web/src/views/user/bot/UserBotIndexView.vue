
<template>
  <!-- 个人中心页面 -->
  <div class="container">
    <div class="row">
      <!-- 个人头像部分 我画了三份  left-->
      <div class="col-3">
        <div class="card" style="margin-top: 20px">
          <div class="card-body">
            <img :src="$store.state.user.photo" alt="" style="width: 100%" />

          </div>

          <div class="card-body" style="text-align: center">

            <span class="record-user-username">
              Hi~<H3>{{$store.state.user.username}}</H3>
              快来bot一下吧~
            </span>

          </div>
        </div>
      </div>

      <!-- 主体部分我画9份  right-->
      <div class="col-9">
        <div class="card" style="margin-top: 20px">
          <div class="card-header">
            <span class="title">我的Bot仓库</span>
            <button type="button" class="btn btn-warning float-end" data-bs-toggle="modal" data-bs-target="#add_bot_btn">
              新建一个Bot
            </button>

            <!-- Modal - 点击新建后弹出 -->
            <div class="modal fade" id="add_bot_btn" tabindex="-1">
              <div class="modal-dialog modal-xl">
                <div class="modal-content">
                  <div class="modal-header">
                    <h5 class="modal-title">新建一个Bot</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                  </div>

                  <!-- 主体 表单-->
                  <div class="modal-body">
                    <div class="mb-3">
                      <!-- bot标题 -->
                      <label for="add-bot-title" class="form-label">Bot名称</label>
                      <input v-model="botadd.name" type="text" class="form-control" id="add-bot-title" placeholder="为您的bot取一个名字吧" />
                    </div>
                    <!-- 描述部分 -->
                    <div class="mb-3">
                      <label for="add-bot-description" class="form-label">描述</label>
                      <textarea v-model="botadd.description" class="form-control" id="add-bot-description" rows="3" placeholder="描述一下你的bot吧"></textarea>
                    </div>
                    <!-- 代码块 -->
                    <div class="mb-3">
                      <label for="add-bot-code" class="form-label">代码</label>

                      <VAceEditor v-model:value="botadd.content" @init="editorInit" lang="c_cpp" theme="textmate" style="height: 300px" :options="{ fontSize: 16 }" />
                      <!-- <textarea
                        v-model="botadd.content"
                        class="form-control"
                        id="add-bot-code"
                        rows="9"
                        placeholder="这里写下你的对战代码"
                      ></textarea> -->
                    </div>
                  </div>

                  <!-- 按钮 -->
                  <div class="modal-footer">
                    <!-- 报错信息 -->
                    <div class="error_message">
                      {{ botadd.error_msg }}
                    </div>
                    <button type="button" class="btn btn-warning" @click="add_bot">
                      新建
                    </button>
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">
                      取消
                    </button>
                  </div>
                </div>
              </div>
            </div>

            <!-- end -->
          </div>
          <div class="card-body">
            <table class="table table-striped table-hover">
              <thead>
                <tr>
                  <th>对战Bot名称</th>
                  <th>描述</th>
                  <th>创建时间</th>
                  <th>修改时间</th>
                  <th>操作</th>
                </tr>
              </thead>
              <tbody>
                <tr v-for="bot in bots" :key="bot.id">
                  <td>{{ bot.name }}</td>
                  <td>{{ bot.description }}</td>
                  <td>{{ bot.createtime }}</td>
                  <td>{{ bot.modifytime }}</td>
                  <td>
                    <button type="button" class="btn btn-success" style="margin-right: 10px" data-bs-toggle="modal" :data-bs-target="'#update-bot-modal-' + bot.id">
                      修改
                    </button>
                    <button type="button" class="btn btn-danger" @click="remove_bot(bot)">
                      删除
                    </button>

                    <!-- Modal - 点击新建后弹出 -->
                    <div class="modal fade" :id="'update-bot-modal-' + bot.id" tabindex="-1">
                      <div class="modal-dialog modal-xl">
                        <div class="modal-content">
                          <div class="modal-header">
                            <h5 class="modal-title">修改你的Bot</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                          </div>

                          <!-- 主体 表单-->
                          <div class="modal-body">
                            <div class="mb-3">
                              <!-- bot标题 -->
                              <label for="add-bot-title" class="form-label">Bot名称</label>
                              <input v-model="bot.name" type="text" class="form-control" id="add-bot-title" placeholder="为您的bot取一个名字吧" />
                            </div>
                            <!-- 描述部分 -->
                            <div class="mb-3">
                              <label for="add-bot-description" class="form-label">描述</label>
                              <textarea v-model="bot.description" class="form-control" id="add-bot-description" rows="3" placeholder="描述一下你的bot吧"></textarea>
                            </div>
                            <!-- 代码块 -->
                            <div class="mb-3">
                              <label for="add-bot-code" class="form-label">代码</label>
                              <VAceEditor v-model:value="bot.content" @init="editorInit" lang="c_cpp" theme="textmate" style="height: 300px" :options="{ fontSize: 20 }" />
                              <!-- <textarea
                                v-model="bot.content"
                                class="form-control"
                                id="add-bot-code"
                                rows="9"
                                placeholder="这里写下你的对战代码"
                              ></textarea> -->
                            </div>
                          </div>

                          <!-- 按钮 -->
                          <div class="modal-footer">
                            <!-- 报错信息 -->
                            <div class="error_message">
                              {{ bot.error_msg }}
                            </div>
                            <button type="button" class="btn btn-warning" @click="update_bot(bot)">
                              保存
                            </button>
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">
                              取消
                            </button>
                          </div>
                        </div>
                      </div>
                    </div>

                    <!-- end -->
                  </td>
                </tr>
              </tbody>
            </table>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { ref, reactive } from "vue";
import $ from "jquery";
import { useStore } from "vuex";
import { Modal } from "bootstrap/dist/js/bootstrap";

// 这两个引入的是代码输入框
import { VAceEditor } from "vue3-ace-editor";
import ace from "ace-builds";

export default {
  components: {
    VAceEditor,
  },
  setup() {
    // 这个是集成代码插件
    ace.config.set(
      "basePath",
      "https://cdn.jsdelivr.net/npm/ace-builds@" +
      require("ace-builds").version +
      "/src-noconflict/"
    );

    const store = useStore();
    let bots = ref([]);

    // 绑定数据
    const botadd = reactive({
      name: "",
      description: "",
      content: "",
      error_msg: "",
    });

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

    // 新增
    const add_bot = () => {
      (botadd.error_msg = ""),
        $.ajax({
          url: "http://127.0.0.1:4000/user/bot/add/",
          type: "post",
          data: {
            name: botadd.name,
            description: botadd.description,
            content: botadd.content,
          },
          headers: {
            Authorization: "Bearer " + store.state.user.token,
          },
          success(resp) {
            if (resp.error_msg === "success") {
              botadd.name = "";
              botadd.description = "";
              botadd.content = "";
              Modal.getInstance("#add_bot_btn").hide(); // 关闭
              refresh_bots(); // 刷新列表
            } else {
              botadd.error_msg = resp.error_msg;
            }
          },
        });
    };

    // 删除 - 传参
    const remove_bot = (bot) => {
      $.ajax({
        url: "http://127.0.0.1:4000/user/del/bot/",
        type: "post",
        data: {
          bot_id: bot.id,
        },
        headers: {
          Authorization: "Bearer " + store.state.user.token,
        },
        success(resp) {
          //console.log(resp);
          if (resp.error_msg === "删除成功") {
            refresh_bots(); // 刷新列表
          }
        },
      });
    };

    // 修改 - 传参
    const update_bot = (bot) => {
      (botadd.error_msg = ""),
        $.ajax({
          url: "http://127.0.0.1:4000/user/update/bot/",
          type: "post",
          data: {
            bot_id: bot.id,
            name: bot.name,
            description: bot.description,
            content: bot.content,
          },
          headers: {
            Authorization: "Bearer " + store.state.user.token,
          },
          success(resp) {
            // console.log(resp);
            if (resp.error_msg === "修改成功") {
              Modal.getInstance("#update-bot-modal-" + bot.id).hide(); // 关闭
              refresh_bots(); // 刷新列表
            } else {
              botadd.error_msg = resp.error_msg;
            }
          },
        });
    };
    return {
      bots,
      botadd,
      add_bot,
      remove_bot,
      update_bot,
    };
  },
};
</script>

<style scoped lang="less">
tr {
  border-color: white;
}

.title {
  font-size: 130%;
}

.error_message {
  color: rgb(252, 0, 0);
}

.card {
  border: 0;
  background-color: rgb(238, 238, 238);
  color: rgb(108, 108, 108);
}

.card-header {
  background-color: rgb(218, 218, 218);
  border-bottom: 0;
}

th {
  color: rgb(100, 100, 100);
}

.btn {
  background-color: rgba(255, 255, 255, 1);
  border: 0px solid black;
  transition: all 0.2s ease-in-out;
  color: black;

  &:active {
    background-color: rgb(0, 0, 0);
    color: white;
  }

  &:hover {
    background-color: rgb(0, 0, 0);
    color: white;
  }
}
</style>

