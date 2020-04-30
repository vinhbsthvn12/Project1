package Project1;

import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.lang.*;

public class Frame extends JFrame {

    private JTextField tfInsertN;
    private PaintPanel pnlSimulation;

    public Frame() {
        initFrame();
    }

    //-----------create frame--------------//
    public void initFrame() {
        this.setTitle("Project1");
        this.setSize(1000, 700);
//        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        addIngredient();

    }

    //------------add content, layout----------------//
    public void addIngredient() {

        JPanel a = new JPanel();
        add(a);
        a.setLayout(new BorderLayout());

        //------------Panel title-----------------------//
        JPanel pnlTitle = new JPanel();
        pnlTitle.setBackground(new Color(75, 0, 130));

        // Tạo tiêu đề
        JLabel title = new JLabel();
        title.setText("Sự phân tán của giọt mực trên mặt nước");
        Font ftl = new Font("arial", Font.BOLD, 25);
        title.setFont(ftl);
        title.setForeground(Color.WHITE);
        pnlTitle.add(title);

        // Tạo viền(border) cho panel
        Border emptyBorder = BorderFactory.createEmptyBorder();
        Border pnlBorder = BorderFactory.createRaisedBevelBorder();
        pnlTitle.setBorder(pnlBorder);
        a.add(pnlTitle, BorderLayout.PAGE_START);

        //---------------Panel mô phỏng phân tán----------//
        pnlSimulation = new PaintPanel();
        Border borderPnlSimulation = BorderFactory.createLoweredBevelBorder();
        pnlSimulation.setBorder(borderPnlSimulation);
        pnlSimulation.setBackground(new Color(185, 222, 240));
        a.add(pnlSimulation, BorderLayout.CENTER);
        Timer timer = new Timer(100, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                draw();
            }
        });
        //---------------Panel menu--------------//
        //Panel chứa
        JPanel pnlWEST = new JPanel();
        pnlWEST.setLayout(new BorderLayout());
        pnlWEST.setBackground(new Color(75, 0, 130));
        a.add(pnlWEST, BorderLayout.WEST);

        //Panel menu chính
        JPanel pnlMenu = new JPanel();
        pnlMenu.setLayout(new BorderLayout());
        pnlMenu.setBorder(emptyBorder);
        pnlWEST.add(pnlMenu, BorderLayout.NORTH);

        //---------------Panel form nhập------------//
        JPanel pnlForm = new JPanel();
        pnlForm.setLayout(new BorderLayout());

        // Panel nhập n
        JPanel pnlInsertN = new JPanel();
        pnlInsertN.setLayout(new BoxLayout(pnlInsertN, BoxLayout.Y_AXIS));
        JLabel insertN = new JLabel("Nhập số giọt mực: n");
        pnlInsertN.add(insertN);
        final JTextField tfInsertN = new JTextField();
        pnlInsertN.add(tfInsertN);
        pnlForm.add(pnlInsertN, BorderLayout.NORTH);
        pnlMenu.add(pnlForm);

        //-----------------Panel tùy chọn----------//
        JPanel pnlOption = new JPanel();
        pnlOption.setLayout(new GridLayout(2, 2, 0, 0));
//        JPanel pnlOption_1 = new JPanel();
//        JPanel pnlOption_2 = new JPanel();
//        pnlOption.add(pnlOption_1);
//        pnlOption.add(pnlOption_2);

        //Button bắt đầu
        Button btnStart = new Button(" Bắt đầu ");

        //
        tfInsertN.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent key) {
                if (key.getKeyCode() == KeyEvent.VK_ENTER) {
                    btnStart.doClick();
                }
            }
        });

        //Sự kiện khi nhấn vào button bắt đầu
        btnStart.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (tfInsertN.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Chưa nhập n", "Error", JOptionPane.WARNING_MESSAGE);
                } else {
                    int n = 0;
                    double t = 0;
                    //Lấy dữ liệu
                    try {
                        n = Integer.parseInt(tfInsertN.getText());
                        if (n <= 0) {
                            JOptionPane.showMessageDialog(null, "Vui lòng nhập số nguyên dương", "Error", JOptionPane.WARNING_MESSAGE);
                        } else {
//                            tfInsertN.setText("");
                            tfInsertN.setEditable(false);
                            pnlSimulation.setNullList();
                            pnlSimulation.setD(n);
                            pnlSimulation.setList();
                            pnlSimulation.repaint();
                            pnlSimulation.revalidate();
                            timer.start();
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Vui lòng nhập số nguyên dương", "Error", JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        });
        pnlOption.add(btnStart);

        //Button nhập lại
        Button btnRetype = new Button("Nhập lại");

        //Sự kiện khi ấn vào button nhập lại
        btnRetype.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                tfInsertN.setEditable(true);
                pnlSimulation.setD(0);
                pnlSimulation.repaint();
                tfInsertN.setText("");
                tfInsertN.requestFocus();
                pnlSimulation.setNullList();
            }
        });
        pnlOption.add(btnRetype);

        //Button tạm dừng
        Button btnPause = new Button("Tạm dừng");
        pnlOption.add(btnPause);

        //Sự kiện khi ấn vào button pause
        btnPause.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                timer.stop();
            }
        });

        //Button tiếp tục
        Button btnContinue = new Button("Tiếp tục");
        pnlOption.add(btnContinue);

        //Sự kiện khi nhấn vào button tiếp tục
        btnContinue.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                timer.start();
            }
        });
        pnlMenu.add(pnlOption, BorderLayout.SOUTH);

    }

    private void draw() {
        this.revalidate();
        pnlSimulation.repaint();
    }

    public static void main(String args[]) {
        Frame surface = new Frame();
        surface.setVisible(true);
    }
}
