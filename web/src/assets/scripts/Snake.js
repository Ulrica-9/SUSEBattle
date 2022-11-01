//  每个蛇单独定义为单独的类


import { AcGameObject } from "./AcGameObject";


import { Cell } from "./Cell";
export class Snake extends AcGameObject {
    // info : 蛇的一些信息 - id 颜色 等
    constructor(info, gamemap) {
        // 传送过来蛇的信息 和  地图

        super();


        // 每个每个蛇的id和颜色 取来 区分两条蛇
        this.id = info.id;
        this.color = info.color;

        // 地图拿过来
        this.gamemap = gamemap;


        this.cells = [new Cell(info.r, info.c)]; // cells[0] 存放蛇头

        this.speed = 5; //蛇的速度

        // 记录两条蛇的下一步指令 -1 无指令  0 1 2 3 上 右 下 左
        this.direction = -1;
        // 当前蛇的状态  idle - 静止 move - 运动 die - 死亡
        this.status = 'idle';
        // 下一步目标位置
        this.next_cell = null;
        // 偏移量
        this.dr = [-1, 0, 1, 0];
        this.dc = [0, 1, 0, -1];


        // 回合数
        this.step = 0;
        this.eps = 1e-2; // 允许误差


        // 蛇的眼睛
        this.eye_direction = 0;
        if (this.id === 1) this.eye_direction = 2;
        this.eye_dx = [
            [-1, 1],
            [1, 1],
            [1, -1],
            [-1, -1],
        ];
        this.eye_dy = [
            [-1, -1],
            [-1, 1],
            [1, 1],
            [1, -1],
        ];


    }


    // 每帧画一次
    start() {

    }

    // 键盘操作的按键接口
    set_direction(d) {
        this.direction = d;
    }

    // 将蛇的状态 
    next_step() {

        const d = this.direction;
        this.next_cell = new Cell(this.cells[0].r + this.dr[d], this.cells[0].c + this.dc[d]);
        this.direction = -1; // 清空操作
        this.status = "move";
        this.step++;

        const k = this.cells.length;
        for (let i = k; i > 0; i--) {
            this.cells[i] = JSON.parse(JSON.stringify(this.cells[i - 1]))
        }


        if (!this.gamemap.check_vaild(this.next_cell)) {
            // 如果下一步不合法 - > 蛇去世
            this.status = "die";
        }


        this.eye_direction = d;
    }

    // 蛇动
    update_move() {


        const dx = this.next_cell.x - this.cells[0].x;
        const dy = this.next_cell.y - this.cells[0].y;

        const distance = Math.sqrt(dx * dx + dy * dy);

        if (distance < this.eps) {
            this.cells[0] = this.next_cell; // 添加一个新的蛇头
            this.next_cell = null;
            this.status = 'idle'; // 走完了 停下来


            if (!this.check_tail_increasing()) {
                this.cells.pop();
            }
        } else {
            // 不重合就移动
            const move_distance = this.speed * this.timedelta / 1000;
            this.cells[0].x += move_distance * dx / distance;
            this.cells[0].y += move_distance * dy / distance;



            if (!this.check_tail_increasing()) {
                const k = this.cells.length;
                const tail = this.cells[k - 1]
                const tail_target = this.cells[k - 2];
                const tail_dx = tail_target.x - tail.x;
                const tail_dy = tail_target.y - tail.y;
                tail.x += move_distance * tail_dx / distance;
                tail.y += move_distance * tail_dy / distance;
            }
        }

    }

    // 每帧调用一次
    update() {
        // 移动时才移动
        if (this.status === 'move') this.update_move();

        this.render();
    }

    render() {
        // 开始画
        // 取出单元格长度
        const L = this.gamemap.L;
        // 画布
        const ctx = this.gamemap.ctx;
        // 画圆
        ctx.fillStyle = this.color;

        // 蛇去世的颜色
        if (this.status === "die") {
            ctx.fillStyle = "white";
        }


        for (const cell of this.cells) {
            ctx.beginPath() // posiotion
            ctx.arc(cell.x * L, cell.y * L, L / 2 * 0.8, 0, Math.PI * 2) // size
            ctx.fill() // color 
        }


        for (let i = 1; i < this.cells.length; i++) {
            const a = this.cells[i - 1];
            const b = this.cells[i];
            if (Math.abs(a.x - b.x) < this.eps && Math.abs(a.y - b.y) < this.eps) continue;
            if (Math.abs(a.x - b.x) < this.eps) {
                ctx.fillRect((a.x - 0.4) * L, Math.min(a.y, b.y) * L, L * 0.8, Math.abs(a.y - b.y) * L);
            } else {
                ctx.fillRect(Math.min(a.x, b.x) * L, (a.y - 0.4) * L, Math.abs(a.x - b.x) * L, L * 0.8);
            }
        }



        // 眼睛添加 
        ctx.fillStyle = "black"; // 眼睛色
        for (let i = 0; i < 2; i++) {
            const eye_x = (this.cells[0].x + this.eye_dx[this.eye_direction][i] * 0.15) * L;
            const eye_y = (this.cells[0].y + this.eye_dy[this.eye_direction][i] * 0.15) * L;
            ctx.beginPath();
            ctx.arc(eye_x, eye_y, L * 0.1, 0, Math.PI * 2)
            ctx.fill(); // 生成
        }






    }



    check_tail_increasing() {
        // 检测当前回合蛇长度
        if (this.step <= 10) return true;
        if (this.step % 3 === 1) return true;
        return false;

    }
}