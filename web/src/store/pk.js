//  用来存储全局信息
export default {
    state: {
        status: "matching", // matching 是匹配 playing是对战界面
        // 前后端的链接是什么
        socket: null,
        opponent_username: "",
        opponent_photo: "",
        gamemap: null,
    },
    getters: {},
    //  修改数据
    mutations: {
        updateSocket(state, socket) {
            state.socket = socket;
        },
        updateOpponent(state, opponent) {
            state.opponent_username = opponent.username;
            state.opponent_photo = opponent.photo;
        },
        updateStatus(state, status) {
            state.status = status;
        },
        updateGamemap(state, gamemap) {
            state.gamemap = gamemap;
        }

    },
    //  接口对接
    actions: {},
    modules: {}
}