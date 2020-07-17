package com.crazybubble.element;

import com.crazybubble.element.ElementObj;
import com.crazybubble.manager.GameLoad;

import javax.swing.*;
import java.awt.*;

/**
 * 1.�̳и���
 * 2.��д���ַ�����ʵ��ҵ���߼�
 * 3.��д���̵߳���ײ
 * 4.��������ļ���ʽ�иı䣬����дGameLoad����ļ��ط���
 */
public class PaoPao extends ElementObj {
    private int imgx = 0;
    //�ɷ���������
    private int imgy = 0;
    private int imgtime = 0;

    @Override
    public ElementObj createElement(String str) {
        String[] split = str.split(",");
        this.setX(Integer.parseInt(split[0]));
        this.setX(Integer.parseInt(split[1]));
        ImageIcon icon = GameLoad.imgMap.get(split[2]);
        this.setIcon(icon);
        this.setW(icon.getIconWidth());
        this.setH(icon.getIconHeight());

        return this;
    }

    @Override
    protected void move() {
        super.move();
    }

    @Override
    public void showElement(Graphics g) {
        //��ͼƬ�ķָ�
        g.drawImage(this.getIcon().getImage(), this.getX(),
                this.getY(), this.getX() + this.getW() / 4,
                this.getY() + this.getH() / 4,
                26 + (imgx * 100), 42,
                72 + (imgx * 100), 99, null);
        //g.drawImage(this.getIcon().getImage(), this.getX(), this.getY(), this.getW(), this.getH(), null);
    }

//    @Override
//    protected void updateImage(long... gameTime) {
//        if (gameTime[0] - imgtime > 3) {
//            imgtime = (int) gameTime[0];
//            imgx++;
//            if (imgx > 3) {
//                imgx = 0;
//            }
//        }
//    }
}
