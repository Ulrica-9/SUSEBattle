import { createRouter, createWebHistory } from 'vue-router'
//  引用几个网页 
import PkIndexView from "../views/pk/PkIndexView"
import RanklistIndexView from "../views/ranklist/RanklistIndexView"
import RecordIndexView from "../views/record/RecordIndexView"
import NotFound from "../views/error/NotFound"
import UserBotIndexView from "../views/user/bot/UserBotIndexView"

//  可以简单实现 在地址栏进行手动切换
const routes = [

    {
        // 重定向到对应页面 -> 输入 / 自动到 /pk/ 页面
        path: "/",
        name: "home",
        redirect: "/pk/",
    },
    {
        // 域名后的路径
        path: "/pk/",
        //  映射到
        name: "pk_index",
        component: PkIndexView,
    },
    {
        // 域名后的路径
        path: "/ranklist/",
        //  映射到
        name: "ranklist_index",
        component: RanklistIndexView,
    },
    {
        // 域名后的路径
        path: "/record/",
        //  映射到
        name: "record_index",
        component: RecordIndexView,
    },
    {
        // 域名后的路径
        path: "/404/",
        //  映射到
        name: "404",
        component: NotFound,
    },
    {
        // 域名后的路径
        path: "/user/bot/",
        //  映射到
        name: "user_bot_index",
        component: UserBotIndexView,
    },
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

export default router