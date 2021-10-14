package com.company;

import gametest9th.utils.*;
import Internet.Client;
import Internet.Server;
import javax.swing.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import static gametest9th.utils.Global.*;
//import java.awt.event.KeyListener;


public class Main {



    public static void main(String[] args) {
            //System.out.println((int)Math.random() * 28);


            new Thread(new Runnable() {
                @Override
                public void run() {
                    Server.getInstance().start();
                    
                }
            }).start();

            JFrame jf = new JFrame(); //遊戲視窗本體
            jf.setTitle("Game"); //視窗標題
            jf.setSize(WINDOW_WIDTH, WINDOW_HEIGHT); //視窗大小
            jf.setDefaultCloseOperation(jf.EXIT_ON_CLOSE); //關閉視窗時關閉程式

            int[][] commands= {         //關聯鍵盤按鈕指令集
                    {KeyEvent.VK_RIGHT , RIGHT},
                    {KeyEvent.VK_LEFT , LEFT},
                    {KeyEvent.VK_SPACE , SPACE},
                    {KeyEvent.VK_A , A},
                    {KeyEvent.VK_B , B},
                    {KeyEvent.VK_D , D},
            };

            GI gi = new GI();  // 遊戲的本體 (邏輯 + 畫面處理)

            GameKernel gk = new GameKernel.Builder(gi , LIMIT_DELTA_TIME, NANOSECOND_PER_UPDATE) // (game interface,畫面更新時間,資料更新間)
                    .initListener(commands)  //鍵盤監聽這些 commend , 也可用 add 加入
//                    .enableKeyboardTrack(new KeyListener() {  // 打開鍵盤追蹤
//                        @Override
//                        public void keyPressed(int commandCode, long trigTime) {
//                            System.out.println(commandCode + " " + trigTime);
//                        }
//
//                        @Override
//                        public void keyReleased(int commandCode, long trigTime) {
//
//                        }
//
//                        @Override
//                        public void keyTyped(char c, long trigTime) {
//
//                        }
//                    })
                    .enableKeyboardTrack(gi)  // 打開鍵盤追蹤
                    .enableMouseTrack(gi) //打開滑鼠追蹤
                    .gen();

            jf.add(gk); //丟到視窗本體

            jf.setVisible(true);

            gk.run(IS_DEBUG); //核心開始跑




//        Timer t = new Timer(100, (ActionEvent e) -> {
//            jp.move();
//            jf.repaint();
//        });
//        t.start();

//            long startTIme = System.nanoTime(); //取得目前系統時間的奈秒
//            long passedUpdated = 0; // 實際應該更新的次數
//            long lastRepaintTIme = System.nanoTime(); //最後一次畫面更新的時間
//            int paintTimes = 0; //畫的次數
//            long timer = System.nanoTime();

//            while(true) {
//                long currentTime = System.nanoTime(); //系統當前時間
//                long totalTime = currentTime - startTIme; //從開始到現在經過的時間
//                long targetTotalUpdated = totalTime / NANOSECOND_PER_UPDATE; //開始到現在應該更新的次數
//                //input
//                //input end
//                while (passedUpdated < targetTotalUpdated) { //如果當前經過的次數小於實際應該要更新的次數,更新追上當前次數
//                    jp.move();
//                    passedUpdated++;
//                }
//                if (currentTime - timer >= 1000000000 ) {  //計算真實fps
//                    System.out.println("FPS: " + paintTimes);
//                    paintTimes = 0;
//                    timer =currentTime;
//                }
//                if (LIMIT_DELTA_TIME <= currentTime - lastRepaintTIme) {
//                    //前面先追上次數 然後才畫出畫面
//                    lastRepaintTIme = currentTime; //最後畫的時間更新成當前時間
//                    jf.repaint();
//                    paintTimes++;
//                }
//            }

        }
}
