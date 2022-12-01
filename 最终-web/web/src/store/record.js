//  用来存储全局信息
export default {
    state: {
        is_record: false,
        a_steps: "",
        b_steps: "",
        record_loser: "",

    },
    getters: {},
    //  修改数据
    mutations: {
        updateIsRecord(state, is_record) {
            state.is_record = is_record;
        },
        updateSteps(state, data) {
            state.a_steps = data.a_steps;
            state.b_steps = data.b_steps;
        },
        updateRecordLoser(state, loser) {
            state.record_loser = loser;
        }

    },
    //  接口对接  
    actions: {},
    modules: {}
}