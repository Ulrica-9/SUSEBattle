// 定义蛇

export class Cell {
    constructor(r, c) {
        this.r = r;
        this.c = c;
        // 蛇就是画圆 想象为中心点坐标
        this.x = c + 0.5;
        this.y = r + 0.5; // 纵坐标

    }


}