import { AcGameObject } from "./AcGameObject";
import { Wall } from "./Wall";
import { Snake } from './Snake';


//  创建的地图视图 *  地图会进行一个改变 计算最大矩形 (脚本算法 -- 不要动)
export class GameMap extends AcGameObject {

    // 构造函数 parent - 动态修改画布长和宽 ctx - 画布标签美化
    constructor(ctx, parent, store) {
        super();

        this.ctx = ctx;
        this.parent = parent;
        this.store = store;

        this.L = 0; // 绝对距离
        this.cols = 14;
        this.rows = 13;
        this.inner_walls_count = 10; // 在墙的内部 有多少个障碍物 (动态进行调整)
        this.walls = []; // 存储所有wall - 墙 [障碍物]

        // 创建蛇 (创建两条蛇)
        this.snakes = [
            new Snake({ id: 0, color: '#4876Ec', r: this.rows - 2, c: 1 }, this),
            new Snake({ id: 1, color: '#F94848', r: 1, c: this.cols - 2 }, this)
        ];

    }



    // 创建障碍物 
    create_walls() {
        const g = this.store.state.pk.gamemap;
        for (let r = 0; r < this.rows; r++) {
            for (let c = 0; c < this.cols; c++) {
                if (g[r][c]) {
                    this.walls.push(new Wall(r, c, this));
                }
            }
        }
    }

    // 键盘事件
    add_listening_events() {
        this.ctx.canvas.focus();
        // 键盘事件 - 获取输入
        const [snake0, snake1] = this.snakes;
        this.ctx.canvas.addEventListener('keydown', e => {
            if (e.key === "w") snake0.set_direction(0);
            else if (e.key === "d") snake0.set_direction(1);
            else if (e.key === "s") snake0.set_direction(2);
            else if (e.key === "a") snake0.set_direction(3);
            else if (e.key === "ArrowUp") snake1.set_direction(0);
            else if (e.key === "ArrowRight") snake1.set_direction(1);
            else if (e.key === "ArrowDown") snake1.set_direction(2);
            else if (e.key === "ArrowLeft") snake1.set_direction(3);
        });
    }


    start() { // 只执行一次

        this.create_walls();

        this.add_listening_events();

    }


    // 这个函数不要动 这里是算法
    update_size() {
        // 最短宽度
        this.L = parseInt(Math.min(this.parent.clientWidth / this.cols, this.parent.clientHeight / this.rows));
        this.ctx.canvas.width = this.L * this.cols;
        this.ctx.canvas.height = this.L * this.rows;
    }


    check_ready() {
        // 蛇移动(两个蛇是否准备好下一回合)
        for (const snake of this.snakes) {
            if (snake.status !== "idle") return false;
            if (snake.direction === -1) return false;
        }
        return true;
    }

    next_step() {
        // 两条蛇进入下一回合
        for (const snake of this.snakes) {
            snake.next_step();
        }
    }

    check_vaild(cell) {
        // 检测目标位置是否合法： 没有撞到 两条蛇的身体和障碍物
        // wall
        for (const wall of this.walls) {
            if (wall.r === cell.r && wall.c === cell.c)
                return false
        }


        // snake
        for (const snake of this.snakes) {
            let k = snake.cells.length;
            if (!snake.check_tail_increasing())
                k--;

            for (let i = 0; i < k; i++) {
                if (snake.cells[i].r === cell.r && snake.cells[i].c === cell.c)
                    return false;
            }
        }

        return true;
    }
    update() { // 以后每一帧都执行一次
        this.update_size(); // 每一帧都更新我们的长度
        if (this.check_ready()) {
            this.next_step();
        }
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