package com.crazybubble.element;

import com.crazybubble.element.ElementObj;

import javax.swing.*;
import java.awt.*;

/**
 * @author Magic Gunner
 * �������裺
 * 1.�̳���Ԫ�ػ��ࣻ��дshow����
 * 2.��������ѡ������д�������������磺move��
 * 3.˼���������������е�����
 * @˵�� ����ӵ��࣬�����ʵ���������Ҷ�����úʹ���
 */
public class PlayFile extends ElementObj {
    //������
    private int attack = 1;
    //�ƶ��ٶ�ֵ
    private int moveNum = 3;
    //���䷽��
    private String fx;


    /**
     * @param x    ���Ͻ�x����
     * @param y    ���Ͻ�y����
     * @param w    w���
     * @param h    h�߶�
     * @param icon ͼƬ
     * @˵�����������Ĺ��췽�������������ഫ�����ݵ�����
     */
    private PlayFile(int x, int y, int w, int h, ImageIcon icon) {
        super(x, y, w, h, icon);
        //һǹһ������
        this.attack = 1;
        //�ƶ��ٶ�
        this.moveNum = 3;
        //���䷽��
        this.fx = "";
    }

    public PlayFile() {
        super();
    }


    //�Դ����������Ĺ��̽��з�װ�����ֻ��Ҫ�����Ҫ��Լ������������ֵ���Ƕ���ʵ��
    //��̬����������д
    @Override
    public ElementObj createElement(String str) {
        String[] split = str.split(",");
        for (String str1 : split) {
            String[] split2 = str1.split(":");
            switch (split2[0]) {
                case "x":
                    this.setX(Integer.parseInt(split2[1]));
                    break;
                case "y":
                    this.setY(Integer.parseInt(split2[1]));
                    break;
                case "f":
                    this.fx = split2[1];
                    break;
            }
        }
        this.setW(10);
        this.setH(10);
        return this;
    }

    @Override
    public void showElement(Graphics g) {
        g.setColor(Color.red);
        g.fillOval(this.getX(), this.getY(), this.getW(), this.getH());

    }

//    @Override
//    protected void add(long gameTime) {
//
//    }

    @Override
    protected void move() {
        //�ӵ�ֹͣ
        //���߳������ӵ�
        if (this.getX() < 0 || this.getX() > 500 || this.getY() < 0 || this.getY() > 300) {
            this.setLive(false);
            return;
        }
        switch (this.fx) {
            case "up":
                this.setY(this.getY() - this.moveNum);
                break;
            case "down":
                this.setY(this.getY() + this.moveNum);
                break;
            case "left":
                this.setX(this.getX() - this.moveNum);
                break;
            case "right":
                this.setX(this.getX() + this.moveNum);
                break;
        }
    }

    /**
     * �����ӵ���˵��
     * 1.���߽�
     * 2.��ײ
     * 3.��ҷű���
     * ����ʽ�����ﵽ����������ʱ��ֻ�����޸�����״̬�Ĳ���
     */

    private long time = 0;

//    @Override
//    protected void updateImage(long... gameTime) {
//        //�ӵ���װ
//
////        if (gameTime != null) {
////            if (gameTime[0] - time > 20) {
////                time = gameTime[0];
////                this.setW(this.getW() + 2);
////                this.setH(this.getH() + 2);
////            }
////        }
//
//    }
}
