<template>
  <ContentField>

    <table class="table table-striped table-hover">
      <thead>
        <tr class="the_test_one">
          <th>玩家1</th>
          <th>玩家2</th>
          <th>对战时间</th>
          <th>对战结果</th>
          <th>对战回放</th>

        </tr>
      </thead>
      <tbody>
        <tr v-for="record in records" :key="record.record.id">
          <td>
            <img :src="record.a_photo" alt="" class="record_photo"> &nbsp;&nbsp;&nbsp;&nbsp;
            <span class="record_username">
              {{ record.a_username }}
            </span>
          </td>
          <td>
            <img :src="record.b_photo" alt="" class="record_photo"> &nbsp;&nbsp;&nbsp;&nbsp;
            <span class="record_username">
              {{ record.b_username }}
            </span>
          </td>
          <td>
            {{ record.record.createtime }}
          </td>

          <td>
            {{ record.result }}
          </td>

          <td>
            <button type="button" class="btn btn-success" style="margin-right: 10px" @click="open_record_content(record.record.id)">
              查看
            </button>
          </td>
        </tr>
      </tbody>
    </table>

    <nav aria-label="Page navigation example">
      <ul class="pagination text-right">
        <li class="page-item" @click="click_page(0)">
          <a class="page-link" href="#" aria-label="Previous">
            <span aria-hidden="true">&laquo;</span>
          </a>
        </li>

        <li :class="'page-item ' + page.is_active" v-for="page in pages" :key="page.number" @click="click_page(page.number)">

          <a class="page-link " href="#">
            {{ page.number }}
          </a>

        </li>

        <li class="page-item" @click="click_page(1)">
          <a class="page-link" href="#" aria-label="Next">
            <span aria-hidden="true">&raquo;</span>
          </a>
        </li>
      </ul>
    </nav>
  </ContentField>
</template>

<script>
import ContentField from "../../components/ContentField.vue";


// 查询后端数据
import { useStore } from 'vuex'
import $ from 'jquery'
import { ref } from 'vue'
//  添加路由进来
import router from '../../router/index'

export default {
  components: {
    ContentField,
  },
  setup() {
    let current_page = 1;
    const store = useStore();
    let records = ref([]);
    //  一共有多少个对局
    let pages = ref([])
    let total_records = 0;

    console.log(total_records);
    // 点击切换网页
    const click_page = page => {
      //  0 前一页
      if (page === 0) page = current_page - 1;
      //  -1 后一页
      else if (page === 1) page = current_page + 1;
      let max_pages = parseInt(Math.ceil(total_records / 10));
      //  中间就是正整数
      if (page >= 1 && page <= max_pages) {
        pull_page(page);
      }
    }


    const udpate_pages = () => {
      let max_pages = parseInt(Math.ceil(total_records / 10)); // 一共有多少页面
      let arr = [];
      for (let i = current_page - 2; i <= current_page + 2; i++) {
        if (i >= 1 && i <= max_pages) {
          // 就可以有一个对象
          arr.push({
            number: i,
            is_active: i === current_page ? "active" : "",
          });
        }
      }
      pages.value = arr;
    }


    const pull_page = page => {
      current_page = page;
      $.ajax({
        url: "http://127.0.0.1:4000/get/record/list/",
        data: {
          page,
        },
        type: "get",

        headers: {
          Authorization: "Bearer " + store.state.user.token,
        },
        success(resp) {
          records.value = resp.records;
          total_records = resp.records_count;
          udpate_pages(); // 更新页面
        },
        error(resp) {
          console.log(resp);
        }
      });
    }



    pull_page(current_page)

    //  传递一个二维数组字符串
    const stringToArray = map => {
      let arr = [];
      for (let i = 0, d = 0; i < 13; i++) {
        let brr = [];
        for (let j = 0; j < 14; j++, d++) {
          if (map[d] === '0') brr.push(0);
          else brr.push(1);
        }
        arr.push(brr);
      }
      return arr; // 字符串转为 01 的二维数组
    }



    const open_record_content = recordId => {
      for (const record of records.value) {
        if (record.record.id === recordId) {
          //  找到了 - 直接退出
          //  退出之前进行更新
          store.commit("updateIsRecord", true);

          //  console.log(record); - 输出控制信息

          store.commit("updateGame", {
            map: stringToArray(record.record.map),
            // a 的 对象信息获取
            a_id: record.record.aid,
            a_sx: record.record.asx,
            a_sy: record.record.asy,
            // b 的 对象信息获取
            b_id: record.record.bid,
            b_sx: record.record.bsx,
            b_sy: record.record.bsy,
          });
          store.commit("updateSteps", {
            //  a 的步长信息
            a_steps: record.record.asteps,
            b_steps: record.record.bsteps,
          });
          store.commit("updateRecordLoser", record.record.loser);


          router.push({
            name: "record_content",
            params: {
              recordId: record // 可以简写 recorId 
            }
          })

          break;
        }
      }
    }
    return {
      records,
      open_record_content,
      pages,
      click_page
    }
  }
};
</script>

<style scoped>
.text-right {
  /* 右对齐 */
  float: right;
}
img {
  width: 5vh;
}
.record_photo {
  width: 5vh;
  border-radius: 50%;
}
</style>