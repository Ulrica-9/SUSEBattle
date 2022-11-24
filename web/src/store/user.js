import $ from 'jquery'

//  用来存储全局信息
export default {
    state: {
        id: "",
        username: "",
        photo: "",
        token: "",
        //  是否登录
        is_login: false,
    },
    getters: {},
    //  修改数据
    mutations: {
        updateUser(state, user) {
            state.id = user.id;
            state.username = user.username;
            state.photo = user.photo;
            state.is_login = user.is_login;
        },
        //  更新token
        updateToken(state, token) {
            state.token = token;
        },
        // 退出操作
        logout(state) {
            state.id = "";
            state.username = "";
            state.photo = "";
            state.token = "";
            state.is_login = false;
        }
    },
    //  接口对接
    actions: {
        //  第一个向后端的请求函数 - 登录
        login(context, data) {
            $.ajax({
                url: "http://localhost:4000/user/msg/token/",
                type: "post",
                data: {
                    username: data.username,
                    password: data.password,
                },
                success(resp) {
                    if (resp.error_message === "success") {
                        localStorage.setItem("jwt_token", resp.token)
                        context.commit("updateToken", resp.token);
                        data.success(resp);
                    } else {
                        data.error(resp);
                    }
                },
                error(resp) {
                    data.error(resp);
                }
            });
        },

        //  第二个向后端的请求函数
        getinfo(context, data) {
            $.ajax({
                url: "http://localhost:4000/user/msg/info/",
                type: "get",
                headers: {
                    // 授权
                    Authorization: "Bearer " + context.state.token,
                },
                success(resp) {
                    // console.log(resp.error_message);
                    if (resp.error_message === "success") {
                        context.commit("updateUser", {
                            ...resp,
                            is_login: true,
                        });
                        data.success(resp);
                    } else {
                        data.error(resp);
                    }
                },
                error(resp) {
                    data.error(resp);
                }

            });
        },
        // 调用上方的退出操作
        logout(context) {
            localStorage.removeItem("jwt_token")
            context.commit("logout");
        }
    },
    modules: {}
}