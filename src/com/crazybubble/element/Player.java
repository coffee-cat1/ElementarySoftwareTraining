package com.crazybubble.element;

import com.crazybubble.manager.ElementManager;
import com.crazybubble.manager.GameElement;
import com.crazybubble.manager.GameLoad;

import javax.swing.*;
import java.awt.*;

/**
 * @author Magic Gunner
 * @description 玩家类
 */
public class Player extends ElementObj {
    //玩家类型：0代表玩家A，1代表玩家B
    private int playerType;

    //键盘监听
    private boolean left = false;
    private boolean up = false;
    private boolean right = false;
    private boolean down = false;

    //切割图片坐标
    private int imgX = 0;
    private int imgY = 0;
    //控制图片刷新时间
    private int imgTime = 0;

    //人物方向
    private String fx = "";
    //攻击状态
    private boolean attackType = false;
    //血量
    private int hp = 5;
    //移动速度
    private int speed = 2;
    //玩家已释放泡泡数量
    private int bubbleNum = 3;
    //可释放泡泡总数
    private int bubbleTotal = 3;
    //泡泡威力
    private int bubblePower = 1;
    //无敌状态
    private boolean isSuper = false;
    //暂停状态
    private boolean isStop = false;
    //跑动状态
    private boolean isRun = false;

    @Override
    public void showElement(Graphics g) {
        g.drawImage(this.getIcon().getImage(), this.getX(),
                this.getY(), this.getX() + this.getW() / 4,
                this.getY() + this.getH() / 4,
                24 + (imgX * 100), 42 + (imgY * 100),
                72 + (imgX * 100), 99 + (imgY * 100), null);
    }

    @Override
    public ElementObj createElement(String str) {
        String[] split = str.split(",");
        this.setX(Integer.parseInt(split[0]));
        this.setY(Integer.parseInt(split[1]));
        ImageIcon icon = GameLoad.imgMap.get(split[2]);
        this.setW(icon.getIconWidth());
        this.setH(icon.getIconHeight());
        this.setIcon(icon);
        return this;
    }


    protected void addBubble() {
        if (attackType) {
            ElementObj obj = GameLoad.getObj("bubble");
            ElementObj element = obj.createElement(this.toStr());
            ElementManager.getManager().addElement(element, GameElement.BUBBLE);
        }
//        try {
//            //配置文件创建对象
//            Class<?> forName = Class.forName("com.crazybubble.element");
//            ElementObj element = PlayFile.class.newInstance().createElement("");
//        } catch (InstantiationException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
    }

    /**
     * @return
     * @description 将当前对象的信息转换为字符串
     */
    public String toStr() {
        int x = this.getX();
        int y = this.getY();
        return "x:" + x + ",y:" + y + ",f:" + this.fx;
    }

    /**
     * @param time
     * @description 跑动时更新图片
     */
    @Override
    protected void updateImage(long time) {
        if (time - imgTime > 3 && this.isRun) {
            imgTime = (int) time;
            switch (this.fx) {
                case "up":
                    imgY = 3;
                    break;
                case "down":
                    imgY = 0;
                    break;
                case "left":
                    imgY = 1;
                    break;
                case "right":
                    imgY = 2;
                    break;
            }
            imgX++;
            if (imgX > 3) {
                imgX = 0;
            }
        }
    }

    protected void move() {
        if (this.left && this.getX() > 0)
            this.setX(this.getX() - 10);
        if (this.up && this.getY() > 0)
            this.setY(this.getY() - 10);
        if (this.right && this.getX() < 800 - this.getW())
            this.setX(this.getX() + 10);
        if (this.down && this.getY() < 800 - this.getH())
            this.setY(this.getY() + 10);
    }

    /**
     * @description 模板方法，封装所有操作
     */
    @Override
    public final void model(long time) {
        updateImage(time);
        move();
        addBubble();
    }

    /**
     * @param bindType 点击的类型 true代表按下 false代表松开
     * @param key      代表触发键盘的code值
     * @description 键盘监听
     */
    public void keyClick(boolean bindType, int key) {
        if (bindType) {
            switch (key) {
                case 65:
                case 37:
                    this.left = true;
                    this.right = false;
                    this.up = false;
                    this.down = false;
                    this.fx = "left";
                    break;
                case 87:
                case 38:
                    this.up = true;
                    this.down = false;
                    this.left = false;
                    this.right = false;
                    this.fx = "up";
                    break;
                case 68:
                case 39:
                    this.right = true;
                    this.left = false;
                    this.up = false;
                    this.down = false;
                    this.fx = "right";
                    break;
                case 83:
                case 40:
                    this.down = true;
                    this.up = false;
                    this.left = false;
                    this.right = false;
                    this.fx = "down";
                    break;
                //开启攻击状态
                case 32:
                case 108:
                    this.attackType = true;
                    break;
            }
            this.isRun = true;
        } else {
            switch (key) {
                case 37:
                    this.left = false;
                    break;
                case 38:
                    this.up = false;
                    break;
                case 39:
                    this.right = false;
                    break;
                case 40:
                    this.down = false;
                    break;
                //关闭攻击状态
                case 32:
                    this.attackType = false;
                    break;
            }
            this.isRun = false;
        }
    }

    @Override
    public void destroy() {
        ElementManager em = ElementManager.getManager();
        em.addElement(this, GameElement.DIE);

    }
}
