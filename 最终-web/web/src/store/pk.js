//  用来存储全局信息
export default {
    state: {
        status: "matching", // matching 是匹配 playing是对战界面
        // 前后端的链接是什么
        socket: null,
        opponent_username: "",
        opponent_photo: "",
        gamemap: null,
        a_id: 0,
        a_sx: 0,
        a_sy: 0,
        b_id: 0,
        b_sx: 0,
        b_sy: 0,
        gameObject: null,
        loser: "none", // none(没人输)、all、A(坐下)、B（右上）
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
        // updateGamemap(state, gamemap) {
        //     state.gamemap = gamemap;
        // },
        updateGame(state, game) {
            state.gamemap = game.map;
            state.a_id = game.a_id;
            state.a_sx = game.a_sx;
            state.a_sy = game.a_sy;
            state.b_id = game.b_id;
            state.b_sx = game.b_sx;
            state.b_sy = game.b_sy;

        },
        updateGameObject(state, gameObject) {
            state.gameObject = gameObject;
        },

        updateLoser(state, loser) {
            state.loser = loser;
        }

    },
    //  接口对接
    actions: {},
    modules: {}
}