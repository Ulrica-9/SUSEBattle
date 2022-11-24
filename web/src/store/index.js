import { createStore } from "vuex"
import ModulePk from './pk'
import ModuleUser from './user'

export default createStore({
    state: {},
    getters: {},
    mutations: {},
    actions: {},
    modules: {
        user: ModuleUser,
        pk: ModulePk,
    }
})