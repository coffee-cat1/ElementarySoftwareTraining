package com.crazybubble.element;

import com.crazybubble.manager.GameLoad;

import javax.swing.*;
import java.awt.*;

/**
 * @author Magic Gunner
 * @˵�� ����Ԫ�صĻ���
 */
public abstract class ElementObj {
    private int x;
    private int y;
    private int w;
    private int h;
    private ImageIcon icon;
    //����״̬ ���Բ���ö��ֵ������������������棬�����������޵У�
    private boolean live = true;
    //ע�⣺�����¶���һ�������ж�״̬�ı�������Ҫ˼����1.��ʼ�� 2.ֵ�ĸı� 3.ֵ���ж�


    public ElementObj() {

    }


    /**
     * @param x    ���Ͻ�x����
     * @param y    ���Ͻ�y����
     * @param w    w���
     * @param h    h�߶�
     * @param icon ͼƬ
     * @˵�����������Ĺ��췽�������������ഫ�����ݵ�����
     */
    public ElementObj(int x, int y, int w, int h, ImageIcon icon) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.icon = icon;
    }

    /**
     * ���󷽷�����ʾԪ��
     *
     * @param g ���� ���ڽ��л滭
     */
    public void showElement(Graphics g){};

    /**
     * ʹ�ø��ඨ����ռ����¼��ķ���
     * ֻ����Ҫʵ�ּ��̼��������࣬��д���������Լ����
     * ʹ�ýӿڵķ�ʽ��Ҫ�ڼ������������ת��
     * Լ�������� ���ڴ󲿷�java��ܶ���Ҫ���ã�Լ����������
     *
     * @param bl  ��������� true������ false�����ɿ�
     * @param key ���������̵�codeֵ
     * @��չ �������Ƿ���Է�Ϊ����������һ�����հ���һ�������ɿ�����չ
     */
    public void keyClick(boolean bl, int key) {
        //�����������ǿ�Ʊ���ʵ�ֵ�
    }

    public abstract ElementObj createElement(String str);

    private static long time = 0;


    /**
     * @���ģʽ ģ��ģʽ����ģ��ģʽ�ж������ִ�з������Ⱥ�˳��������ѡ������д����
     * 1.�ƶ� 2.��װ 3.�ӵ�����
     * final��������д
     */
    public final void model(long gameTime) {
        //�Ȼ�װ
        updateImage(time);
        //���ƶ�
        move();
        //�ٷ����ӵ�
        add(gameTime);
        time++;
    }

    /**
     * @˵�� �ƶ���������Ҫ�ƶ������࣬��ʵ���������
     * protected ֻ�����������д
     */
    protected void move() {
    }

    //�������п���
    // long ... a �����������飬�����������������N��long���͵�����
    protected void updateImage(long... gameTime) {
    }

    protected void add(long gameTime) {

    }

    //�������� ������̳е�
    //����Ҳ��һ������
    public void die() {

    }

    /**
     * @return
     * @˵�� ����������Ԫ�ص���ײ���ζ���ʵʱ���أ�
     */
    public Rectangle getRectangle() {
        //���Խ�������ݽ��д���
        Integer.parseInt(String.valueOf(x));
        return new Rectangle(x, y, w, h);

    }

    /**
     * @˵�� ��ײ����
     * һ����this����һ���Ǵ���ֵobj
     * @param obj
     * @return boolean ����true˵������ײ������false˵��û����ײ
     */
    public boolean pk(ElementObj obj) {
        return this.getRectangle().intersects(obj.getRectangle());
    }

    /**
     * ֻҪ��VO���ҪΪ��������get��set����
     */
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getW() {
        return w;
    }

    public void setW(int w) {
        this.w = w;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }

    public ImageIcon getIcon() {
        return icon;
    }

    public void setIcon(ImageIcon icon) {
        this.icon = icon;
    }

    public boolean isLive() {
        return live;
    }

    public void setLive(boolean live) {
        this.live = live;
    }
}
