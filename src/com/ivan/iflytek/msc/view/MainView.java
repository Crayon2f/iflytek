package com.ivan.iflytek.msc.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.iflytek.cloud.speech.SpeechUtility;
import com.ivan.iflytek.common.DrawableUtils;
import com.ivan.iflytek.msc.util.Version;

/**
 * MscDemo It's a Sample using MSC SDK, include tts, isr. you can just press
 * button to use it.
 *
 * @author cyhu 2012-06-14
 */
@SuppressWarnings("serial")
public class MainView extends JFrame implements ActionListener {
    private JPanel mMainJpanel;
    private JPanel mContentPanel;
    private static JFrame mJframe;

    private JButton jbtnRecognize;
    private JButton jbtnGrammar;
    private JButton jbtnUnderstander;
    private JButton jbtnSynthesize;

    /**
     * 界面初始化.
     */
    public MainView() {
        // 初始化
        StringBuffer param = new StringBuffer();
        param.append("appid=" + Version.getAppid());
//		param.append( ","+SpeechConstant.LIB_NAME_32+"=myMscName" );
        SpeechUtility.createUtility(param.toString());
        // 设置界面大小，背景图片
        ImageIcon background = new ImageIcon("res/msc/index_bg.png");
        JLabel label = new JLabel(background);
        label.setBounds(0, 0, background.getIconWidth(),
                background.getIconHeight());
        getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));

        int frameWidth = background.getIconWidth();
        int frameHeight = background.getIconHeight();

        setSize(frameWidth, frameHeight);
        setResizable(false);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ImageIcon imgRecognize = new ImageIcon("res/msc/btn_recognize.png");
        jbtnRecognize = this.createImageButton(imgRecognize);
        jbtnRecognize.setBounds(10, 150, imgRecognize.getIconWidth(),
                imgRecognize.getIconHeight());
        DrawableUtils.setMouseListener(jbtnRecognize, "res/msc/btn_recognize");

        ImageIcon imgGrammar = new ImageIcon("res/msc/btn_grammar.png");
        jbtnGrammar = this.createImageButton(imgGrammar);
        jbtnGrammar.setBounds(160, 150, imgGrammar.getIconWidth(),
                imgGrammar.getIconHeight());
        DrawableUtils.setMouseListener(jbtnGrammar, "res/msc/btn_grammar");

        ImageIcon imgUnderstander = new ImageIcon("res/msc/btn_understander.png");
        jbtnUnderstander = this.createImageButton(imgUnderstander);
        jbtnUnderstander.setBounds(310, 150, imgUnderstander.getIconWidth(),
                imgUnderstander.getIconHeight());
        DrawableUtils
                .setMouseListener(jbtnUnderstander, "res/msc/btn_understander");

        ImageIcon imgSynthesize = new ImageIcon("res/msc/btn_synthesize.png");
        jbtnSynthesize = this.createImageButton(imgSynthesize);
        jbtnSynthesize.setBounds(460, 150, imgSynthesize.getIconWidth(),
                imgSynthesize.getIconHeight());
        DrawableUtils.setMouseListener(jbtnSynthesize, "res/msc/btn_synthesize");

        GridLayout gridlayout = new GridLayout(0, 4);
        gridlayout.setHgap(10);
        mMainJpanel = new JPanel(gridlayout);
        mMainJpanel.setOpaque(false);

        mMainJpanel.add(jbtnRecognize);
        mMainJpanel.add(jbtnGrammar);
        mMainJpanel.add(jbtnUnderstander);
        mMainJpanel.add(jbtnSynthesize);

        jbtnRecognize.addActionListener(this);
        jbtnGrammar.addActionListener(this);
        jbtnUnderstander.addActionListener(this);
        jbtnSynthesize.addActionListener(this);

        mContentPanel = new JPanel(new BorderLayout());
        mContentPanel.setOpaque(false);
        mContentPanel.add(mMainJpanel, BorderLayout.CENTER);

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

    public static JFrame getFrame() {
        return mJframe;
    }

    public JButton createImageButton(ImageIcon img) {
        JButton button = new JButton("");
        button.setIcon(img);
        button.setSize(img.getIconWidth(), img.getIconHeight());
        button.setBackground(null);

        button.setBorder(null);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);

        return button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jbtnRecognize) {
            mContentPanel.remove(mMainJpanel);
            mContentPanel.add(new IatSpeechView());
            mContentPanel.revalidate();
            mContentPanel.repaint();
        } else if (e.getSource() == jbtnGrammar) {
            mContentPanel.remove(mMainJpanel);
            mContentPanel.add(new AsrSpeechView());
            mContentPanel.revalidate();
            mContentPanel.repaint();
        } else if (e.getSource() == jbtnUnderstander) {
            mContentPanel.remove(mMainJpanel);
            mContentPanel.add(new UnderstanderView());
            mContentPanel.revalidate();
            mContentPanel.repaint();
        } else if (e.getSource() == jbtnSynthesize) {
            mContentPanel.remove(mMainJpanel);
            mContentPanel.add(new TtsSpeechView());
            mContentPanel.revalidate();
            mContentPanel.repaint();
        }
    }

    public JPanel getMainJpanel() {
        return mMainJpanel;
    }

    public JPanel getContePanel() {
        return mContentPanel;
    }
}