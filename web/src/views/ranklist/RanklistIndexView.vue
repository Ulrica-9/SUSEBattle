<template>
  <transition-group appear name="animate__animated animate__bounce"
    enter-active-class="animate__animated animate__backInDown" leave-active-class="animate__backOutUp">
    <ContentField key="1">
      <table class="table table-striped table-hover" style="text-align: center">
        <thead>
          <tr>
            <th>排名</th>
            <th>头像</th>
            <th>昵称</th>
            <th>天梯分</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="user in users" :key="user.id">
            <td>
              {{ ++idx }}
            </td>
            <td>
              <img :src="user.photo" alt="" class="record-user-photo" />
            </td>
            <td>
              <span class="record-user-username">{{ user.username }}</span>
            </td>
            <td>{{ user.rating }}</td>
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

          <li :class="'page-item ' + page.is_active" v-for="page in pages" :key="page.number"
            @click="click_page(page.number)">
            <a class="page-link" href="#">
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
  </transition-group>
</template>

<script>
import ContentField from '../../components/ContentField.vue'

// 查询后端数据
import { useStore } from 'vuex'
import $ from 'jquery'
import { ref } from 'vue'

export default {
  components: {
    ContentField
  },
  setup() {
    let current_page = 1
    const store = useStore()
    let users = ref([])
    //  一共有多少个对局
    let pages = ref([])
    let total_users = 0
    let idx = 0

    console.log(total_users)
    // 点击切换网页
    const click_page = page => {
      //  0 前一页
      if (page === 0) page = current_page - 1
      //  -1 后一页
      else if (page === 1) page = current_page + 1
      let max_pages = parseInt(Math.ceil(total_users / 10))
      //  中间就是正整数
      if (page >= 1 && page <= max_pages) {
        pull_page(page)
      }
    }

    const udpate_pages = () => {
      let max_pages = parseInt(Math.ceil(total_users / 10)) // 一共有多少页面
      let arr = []
      for (let i = current_page - 2; i <= current_page + 2; i++) {
        if (i >= 1 && i <= max_pages) {
          // 就可以有一个对象
          arr.push({
            number: i,
            is_active: i === current_page ? 'active' : ''
          })
        }
      }
      pages.value = arr
    }

    const pull_page = page => {
      current_page = page
      $.ajax({
        url: 'http://localhost:4000/ranklist/get/list/',
        data: {
          page
        },
        type: 'get',

        headers: {
          Authorization: 'Bearer ' + store.state.user.token
        },
        success(resp) {
          users.value = resp.users
          total_users = resp.users_count
          udpate_pages() // 更新页面
        },
        error(resp) {
          console.log(resp)
        }
      })
    }

    pull_page(current_page)

    return {
      users,
      pages,
      click_page,
      idx
    }
  }
}
</script>

<style scoped>
.text-right {
  /* 右对齐 */
  float: right;
}

.pagination {
  --bs-pagination-border-width: 0px;
}

img {
  width: 5vh;
}

.record_photo {
  width: 5vh;
  border-radius: 50%;
}

.page-link {
  color: rgb(84, 84, 84);
}
</style>
