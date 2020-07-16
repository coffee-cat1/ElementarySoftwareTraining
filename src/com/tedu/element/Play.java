package com.tedu.element;

import com.tedu.manager.ElementManager;
import com.tedu.manager.GameElement;
import com.tedu.manager.GameLoad;

import javax.swing.*;
import java.awt.*;
import java.nio.file.attribute.FileTime;
import java.util.HashMap;
import java.util.Map;

public class Play extends ElementObj {

    /**
     * �ƶ����ԣ�
     * 1.������ ��� ����ö������ʹ�� һ��ֻ���ƶ�һ������
     * 2.˫���� ���� �� ���� ���booleanֵʹ�� ���磺true������ false������
     * ��Ҫ����һ��������ȷ���Ƿ��·����
     * Լ�� 0������ 1�������� 2��������
     * 3.������ �������Ҷ����� boolean���ʹ�� true�����ƶ� false�����ƶ�
     * ͬʱ���Ϻ��£��󰴵Ļ������Ȱ���
     * ˵������״̬����ʹ��Map<����,boolean>��set<�ж�����>�ж���������ʱ��
     *
     * @���� 1.ͼƬҪ��ȡ���ڴ��У������� ��ʱ����ʽ���ֶ���д�洢���ڴ���
     * 2.ʲôʱ������޸�ͼƬ����ΪͼƬ���ڸ����е����Դ洢��
     * 3.ͼƬӦ��ʹ��ʲô���Ͻ��д洢
     */

    private boolean left = false;
    private boolean up = false;
    private boolean right = false;
    private boolean down = false;

    //ͼƬ���� ʹ��map�����д洢
    private Map<String, ImageIcon> imgMap;
    //����ר��������¼��ǰ�ķ���Ĭ��up
    private String fx = "up";
    //����״̬ true���� falseֹͣ
    private boolean pkType = false;

    public Play(int x, int y, int w, int h, ImageIcon icon) {
        super(x, y, w, h, icon);

    }

    public Play() {

    }

    @Override
    public void showElement(Graphics g) {
        g.drawImage(this.getIcon().getImage(), this.getX(), this.getY(), this.getW(), this.getH(), null);
    }

    //��д����
    @Override   //�������Ϊ����߷�������������ӵ�ע��
    public void keyClick(boolean bl, int key) {
        if (bl) {
            switch (key) {
                case 37:
                    this.left = true;
                    this.right = false;
                    this.up = false;
                    this.down = false;
                    this.fx = "left";
                    break;
                case 38:
                    this.up = true;
                    this.down = false;
                    this.left = false;
                    this.right = false;
                    this.fx = "up";
                    break;
                case 39:
                    this.right = true;
                    this.left = false;
                    this.up = false;
                    this.down = false;
                    this.fx = "right";
                    break;
                case 40:
                    this.down = true;
                    this.up = false;
                    this.left = false;
                    this.right = false;
                    this.fx = "down";
                    break;
                //��������״̬
                case 32:
                    this.pkType = true;
                    break;
            }
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
                //�رչ���״̬
                case 32:
                    this.pkType = false;
                    break;
            }
        }
    }

    @Override
    public ElementObj createElement(String str) {
        String[]split = str.split(",");
        this.setX(Integer.parseInt(split[0]));
        this.setY(Integer.parseInt(split[1]));
        ImageIcon icon2 = GameLoad.imgMap.get(split[2]);
        this.setW(icon2.getIconWidth());
        this.setH(icon2.getIconHeight());
        this.setIcon(icon2);
        return this;
    }

    @Override
    public void move() {
        if (this.left && this.getX() > 0)
            this.setX(this.getX() - 10);
        if (this.up && this.getY() > 0)
            this.setY(this.getY() - 10);
        if (this.right && this.getX() < 600 - this.getW())
            this.setX(this.getX() + 10);
        if (this.down && this.getY() < 400 - this.getH())
            this.setY(this.getY() + 10);
    }

    @Override
    protected void updateImage(long ... gameTime) {
        this.setIcon(GameLoad.imgMap.get(fx));
    }

    /**
     * @��д����1.��д�����ķ������ƺͷ���ֵ����͸���һ�� 2.��д�ķ����Ĵ�������������У�����͸���һ��
     * 3.��д�ķ����������η�ֻ�ܱȸ���ĸ��ӿ�
     * �����磺������protected��������Ҫ�ڷ������е���
     * ����ֱ������̳У���д��super.���෽��������public��
     * 4.��д�ķ����׳����쳣�����Աȸ�����ӿ�
     */
    private long filetime=0;
    //filetime�ʹ����ʱ��getTime���бȽϡ���ֵ�Ȳ�������
    //�����ӵ����
    @Override
    protected void add(long gameTime) {
        //����һ������Ҫ�Ƚ϶๤����ʱ�򣬿���ѡ��һ�ַ�ʽ��ʹ��С����
        //���������Ķ��������з�װ��Ϊһ������������ֱֵ�����������
        //����һ���̶���ʽ{x:3,y:5,f:up} json��ʽ
        //����㷵�ض����ʵ�壬����ʼ������
        if (pkType) {
            ElementObj element = new PlayFile().createElement(this.toString()); //�Ժ�Ŀ�ܻ�����
            ElementManager.getManager().addElement(element, GameElement.PLAYFILE);
        }

        //��һ�η���һ���ӵ�
        this.pkType = false;


//        //�������
//        try {
//            //�����ļ���������
//            Class<?> forName = Class.forName("com.tedu.element");
//            ElementObj element = PlayFile.class.newInstance().createElement("");
//        } catch (InstantiationException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
    }

    @Override
    public String toString() {
        //�����Լ�����һ������
        int x = this.getX();
        int y = this.getY();
        switch (this.fx) {
            //���ݲ�һ���ķ������ӵ�����
            //�ӵ��ڷ����ʱ����Ѿ����˹̶��켣
            //���Լ���Ŀ�꣬�޸�json��ʽ
            //һ�㲻��д������ֵ��ͼƬ��С������ʾ��С
            //ʹ��ͼƬ��С��������
            case "up":
                x += getW() / 2 - 5;
                break;
            case "down":
                x += getW() / 2 - 5;
                y += getH() - 5;
                break;
            case "left":
                y += getH() / 2 - 5;
                break;
            case "right":
                x += getX()/2-5;
                y += getH()/2-5;
                break;
        }
        return "x:" + x + ",y:" + y + ",f:" + this.fx;
    }

    public int getFileX() {

        return 0;
    }

    public int getFileY() {
        return 0;
    }

    @Override
    public void die() {
        //������������
        ElementManager em = ElementManager.getManager();
        em.addElement(this, GameElement.DIE);
    }
}
