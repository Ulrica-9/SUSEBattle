import { AcGameObject } from "./AcGameObject";
import { Wall } from "./Wall";

//  创建的地图视图 *  地图会进行一个改变 计算最大矩形 (脚本算法 -- 不要动)
export class GameMap extends AcGameObject {

    // 构造函数 parent - 动态修改画布长和宽 ctx - 画布标签美化
    constructor(ctx, parent) {
        super();

        this.ctx = ctx;
        this.parent = parent;

        this.L = 0; // 绝对距离


        this.cols = 13;
        this.rows = 13;

        this.inner_walls_count = 10; // 在墙的内部 有多少个障碍物 (动态进行调整)
        this.walls = []; // 存储所有wall - 墙 [障碍物]

    }

    // 判断两个蛇之间是否连同 (bfs算法判断)
    check_connectivity(g, sx, sy, tx, ty) {

            if (sx == tx && sy == ty) return true;

            g[sx][sy] = true;

            let dx = [-1, 0, 1, 0]
            let dy = [0, 1, 0, -1];
            for (let i = 0; i < 4; i++) {
                let x = sx + dx[i],
                    y = sy + dy[i];
                if (!g[x][y] && this.check_connectivity(g, x, y, tx, ty)) return true;
            }
            return false;

        }
        // 创建障碍物 
    create_walls() {
        // new Wall(0, 0, this) - 测试
        const g = [];
        // 初始化
        for (let r = 0; r < this.rows; r++) {
            g[r] = [];
            for (let c = 0; c < this.cols; c++) {
                g[r][c] = false;
            }
        }

        //  给四周加上障碍物
        // 两边加墙
        for (let r = 0; r < this.rows; r++) {
            g[r][0] = g[r][this.cols - 1] = true;
        }
        // 上下加墙
        for (let c = 0; c < this.cols; c++) {
            g[0][c] = g[this.rows - 1][c] = true;
        }


        // 创建随机障碍物  （如果遇到重复 则继续随机找 知道为空）
        for (let i = 0; i < this.inner_walls_count; i++) {
            // 假想1024次结束但是其实可以根据格子大小来进行一个判断  
            for (let j = 0; j < 1024; j++) {
                let r = parseInt(Math.random() * this.rows)
                let c = parseInt(Math.random() * this.cols)
                    // 如果已经有了就跳过
                if (g[r][c] || g[c][r]) continue;
                // 因为左下和右上是蛇的诞生 所以也要跳过
                if (r == this.rows - 2 && c == 1 || r == 1 && c == this.cols - 2) continue;
                g[r][c] = g[c][r] = true;
                break; // 当前搜索就退出
            }
        }

        // 转为json
        const copy_g = JSON.parse(JSON.stringify(g));

        // 看看两者是否连通 -> bfs走迷宫算法
        if (!this.check_connectivity(copy_g, this.rows - 2, 1, 1, this.cols - 2)) return false;



        for (let r = 0; r < this.rows; r++) {
            for (let c = 0; c < this.cols; c++) {
                if (g[r][c]) {
                    // 创建墙
                    this.walls.push(new Wall(r, c, this))
                }
            }
        }
        // 成功创建 （连通）
        return true;
    }
    start() { // 只执行一次
        for (let i = 0; i < 1024; i++) {
            if (this.create_walls()) break; // 成功找到就退出 没有则继续寻找
        }
    }

    // 这个函数不要动 这里是算法
    update_size() {
        // 最短宽度
        this.L = parseInt(Math.min(this.parent.clientWidth / this.cols, this.parent.clientHeight / this.rows));
        this.ctx.canvas.width = this.L * this.cols;
        this.ctx.canvas.height = this.L * this.rows;
    }
    update() { // 以后每一帧都执行一次
        this.update_size(); // 每一帧都更新我们的长度
        this.render();

    }

    render() {
        // this.ctx.fillStyle = "green";
        // // 第一 和 第二个 代表左上角起始坐标
        // this.ctx.fillRect(0, 0, this.ctx.canvas.width, this.ctx.canvas.height);

        //  这里是棋盘
        const color_even = "#AAD751",
            color_odd = "#A2D149";
        for (let r = 0; r < this.rows; r++) {
            for (let c = 0; c < this.cols; c++) {
                if ((r + c) % 2 == 0) {
                    this.ctx.fillStyle = color_even; // 棋盘颜色
                } else {
                    this.ctx.fillStyle = color_odd;
                }
                this.ctx.fillRect(c * this.L, r * this.L, this.L, this.L); // 棋盘中矩形的具体摆放位置
            }
        }
    }
}