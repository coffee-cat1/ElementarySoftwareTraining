package com.crazybubble.show;

import javax.swing.*;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * @author Magic Gunner
 * @˵�� ��Ϸ���� ��Ҫʵ�ֹ��ܣ��رգ���ʾ�������С��
 * @����˵���� ��ҪǶ����壬�������̵߳ȵ�
 * @����˵�� swing awt �����С����¼�û��ϴ�ʹ������Ĵ�����ʽ��
 * @���� 1.���󶨵�����
 * 2.������
 * 3.��Ϸ���߳�����
 * 4.��ʾ����
 */
public class GameJFrame extends JFrame {
    public static int GameX = 600;//GAMEX(java�����շ�ԭ��)
    public static int GameY = 400;
    private JPanel jPanel = null;//������ʾ�����
    private KeyListener keyListener = null;//���̼���
    private Thread thread = null;//��Ϸ���߳�
    private MouseMotionListener mouseMotionListener = null;//������
    private MouseListener mouseListener = null;

    public GameJFrame() {
        init();
    }

    public void init() {
        this.setSize(GameX, GameY);//���ô����С
        this.setTitle("test");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//�����˳����ر�
        this.setLocationRelativeTo(null);//��Ļ����
    }

    /**
     * ����չ��
     * ���岼�֣����Խ��������ݣ�����etc
     */
    public void addButton() {
//		this.setLayout(manager);//���ָ�ʽ��������ӿؼ�
    }

    /**
     * ��������
     */
    public void start() {
        if (jPanel != null) {
            this.add(jPanel);
        }
        if (keyListener != null) {
            this.addKeyListener(keyListener);
        }
        if (thread != null) {
            thread.start();
        }
        //�����ˢ��
        this.setVisible(true);
        if (this.jPanel instanceof Runnable) {
            //�Ѿ����������жϣ�ǿ������ת���������
            new Thread((Runnable) this.jPanel).start();
        }
    }

    /*setע�룺ssm ͨ��set����ע�������ļ��ж�ȡ�����ݣ�
     * �������ļ��е����ݸ�ֵ���������
     * ����ע�룺��Ҫ��Ϲ��췽��
     * Դ��spring�е�ioc���ж�����Զ����ɣ�����*/
    public void setjPanel(JPanel jPanel) {
        this.jPanel = jPanel;
    }

    public void setKeyListener(KeyListener keyListener) {
        this.keyListener = keyListener;
    }

    public void setThread(Thread thread) {
        this.thread = thread;
    }

    public void setMouseMotionListener(MouseMotionListener mouseMotionListener) {
        this.mouseMotionListener = mouseMotionListener;
    }

    public void setMouseListener(MouseListener mouseListener) {
        this.mouseListener = mouseListener;
    }

}
