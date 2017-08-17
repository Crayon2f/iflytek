package com.ivan.iflytek.isv.view;

import java.awt.BorderLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.iflytek.cloud.speech.SpeechUtility;
import com.ivan.iflytek.common.DrawableUtils;
import com.ivan.iflytek.isv.util.Utils;

/**
 * MscDemo It's a Sample using MSC SDK, include tts, isr. you can just press
 * button to use it.
 */
public class MainView extends JFrame {
    private static JPanel mContentPanel;
    private static JFrame mJframe;
    private static IsvDemoView isvDemoView;
    private static LoginView loginview;

    private static final long serialVersionUID = 1L;

    /**
     * 界面初始化.
     */
    public MainView() {

        // 初始化
        SpeechUtility.createUtility("appid=" + Utils.getAppid());
        // 设置界面大小，背景图片
        ImageIcon background = new ImageIcon("res/isv/index_bg.png");
        JLabel label = new JLabel(background);
        label.setBounds(0, 0, background.getIconWidth(), background.getIconHeight());
        getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));

        int frameWidth = background.getIconWidth();
        int frameHeight = background.getIconHeight();
        setSize(frameWidth, frameHeight);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        isvDemoView = new IsvDemoView();
        loginview = new LoginView();

        mContentPanel = new JPanel(new BorderLayout());
        setLayout(null);
        mContentPanel.add(loginview);
        mContentPanel.setOpaque(false);

        setLocationRelativeTo(null);
        setContentPane(mContentPanel);
        setVisible(true);

    }

    /**
     * Demo入口函数.
     *
     * @param args
     */
    public static void main(String args[]) {
        mJframe = new MainView();
    }

    public static void SwitchView(boolean backToLogin) {
        if (backToLogin) {
            IsvDemoView.setmAuthId("");
            loginview.setTxtUserId("");
        } else
            isvDemoView.refreshText();
        mContentPanel.remove(backToLogin ? isvDemoView : loginview);
        mContentPanel.add(backToLogin ? loginview : isvDemoView);
        mContentPanel.setOpaque(false);
        mContentPanel.validate();
        mContentPanel.repaint();
    }

    public static void setAuthId(String mAuthId) {
        if (null != isvDemoView) {
            IsvDemoView.setmAuthId(mAuthId);
        } else
            isvDemoView = new IsvDemoView();

    }

    public JPanel getContentJpanel() {
        return mContentPanel;
    }

}