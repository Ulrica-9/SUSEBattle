import { createRouter, createWebHistory } from 'vue-router'
//  引用几个网页 
import PkIndexView from "../views/pk/PkIndexView"
import RanklistIndexView from "../views/ranklist/RanklistIndexView"
import RecordIndexView from "../views/record/RecordIndexView"
import NotFound from "../views/error/NotFound"
import UserBotIndexView from "../views/user/bot/UserBotIndexView"

import userLogin from '../views/user/msg/userLogin'
import userRegister from '../views/user/msg/userRegister'
// 页面状态
import store from '../store/index'


//  可以简单实现 在地址栏进行手动切换
const routes = [{
        // 重定向到对应页面 -> 输入 / 自动到 /pk/ 页面
        path: "/",
        name: "home",
        redirect: "/pk/",
        meta: {
            // 是否需要授权
            requestAuth: true,
        }
    },

    {
        // 域名后的路径
        path: "/pk/",
        //  映射到
        name: "pk_index",
        component: PkIndexView,
        meta: {
            // 是否需要授权
            requestAuth: true,
        }

    },
    // 排行榜
    {
        path: "/ranklist/",
        name: "ranklist_index",
        component: RanklistIndexView,
        meta: {
            requestAuth: true,
        }
    },
    // 对局列表
    {
        path: "/record/",
        name: "record_index",
        component: RecordIndexView,
        meta: {
            requestAuth: true,
        }
    },
    // 个人bot页面
    {
        path: "/user/bot/",
        name: "user_bot_index",
        component: UserBotIndexView,
        meta: {
            requestAuth: true,
        }
    },
    // 登录
    {
        path: "/user/msg/login",
        name: "user_msg_login",
        component: userLogin,
        meta: {
            requestAuth: false,
        }
    },
    // 注册
    {
        path: "/user/msg/register",
        name: "user_msg_register",
        component: userRegister,
        meta: {
            requestAuth: false,
        }
    },
    // 404页面
    {
        path: "/404/",
        name: "404",
        component: NotFound,
        meta: {
            requestAuth: false,
        }
    },
    // 网址错误 自动跳转到404
    {
        // 输入其他的错误字符/链接 自动跳转跳到 404 页面
        path: "/:catchAll(.*)",
        redirect: "/404/",
    },
]

const router = createRouter({
    history: createWebHistory(),
    routes
})

// 必须授权登录，不然会重定向
router.beforeEach((to, from, next) => {
    if (to.meta.requestAuth && !store.state.user.is_login) {
        // 重定向到登录界面

        next({ name: "user_msg_login" });
    } else {
        next(); //跳转到默认页面
    }

    // let flag = 1;
    // const jwt_token = localStorage.getItem("jwt_token");

    // if (jwt_token) {
    //     store.commit("updateToken", jwt_token);
    //     store.dispatch("getInfo", {
    //         success() {},
    //         error() {
    //             alert("token无效，请重新登录！");
    //             router.push({ name: 'user_msg_login' });
    //             localStorage.removeItem('jwt_token');
    //         }
    //     })
    // } else {
    //     flag = 2;
    // }

    // if (to.meta.requestAuth && !store.state.user.is_login) {
    //     if (flag === 1) {
    //         next();
    //     } else {
    //         alert("请先进行登录！");
    //         next({ name: "user_msg_login" });
    //     }
    // } else {
    //     next();
    // }


})

export default router