

<template>
  <!-- 这里是pk界面的地图部分 -->
  <div ref="parent" class="gamemap">
    <!-- canvas - 画布 -->
    <canvas ref="canvas" tabindex="0"></canvas>
  </div>
</template>

<script>
// 引用 js脚本进来

import { GameMap } from "@/assets/scripts/GameMap";
// onMounted 当组件挂载完之后，需要创建对象
import { ref, onMounted } from "vue";
import { useStore } from "vuex";

export default {
  setup() {
    const store = useStore();
    let parent = ref(null);
    let canvas = ref(null);

    // 挂载完毕之后
    onMounted(() => {
      store.commit(
        "updateGameObject",
        new GameMap(canvas.value.getContext("2d"), parent.value, store)
      );
    });

    return {
      parent,
      canvas,
    };
  },
};
</script>

<style scoped>
div.gamemap {
  width: 100%;
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
}
</style>
