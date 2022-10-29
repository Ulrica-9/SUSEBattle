const AC_GAME_OBJECT = [];

export class AcGameObject {
    // 构造函数
    constructor() {
        AC_GAME_OBJECT.push(this);
        this.timedelta = 0;
        this.has_called_start = false;
    }

    start() { // 只执行一次 (第一帧执行)

    }

    update() { // 每一帧执行一次, 第一帧除外

    }

    on_destroy() { // 删除之前执行


    }


    destroy() { // 删除
        this.on_destroy(); // 删除之前执行
        for (let i in AC_GAME_OBJECT) {
            const obj = AC_GAME_OBJECT[i];
            if (obj == this) {
                AC_GAME_OBJECT.splice(i); // 移除当前元素
                break;
            }
        }
    }
}

// 上一次执行的时刻
let last_timestamp;
const step = timestamp => {
    for (let obj of AC_GAME_OBJECT) {
        //  如果没有执行过 -> start() 函数
        if (!obj.has_called_start) {
            obj.has_called_start = true;
            obj.start();
        } else {
            obj.timedelta = timestamp - last_timestamp;
            obj.update();
        }
    }
    last_timestamp = timestamp;
    requestAnimationFrame(step)
}

requestAnimationFrame(step);