package scene;

import GameObj.*;
import Internet.Client;
import Internet.Client.Command;
import Internet.CommandReceiver;

import java.awt.event.*;

import Internet.Server;
import controllers.SceneController;
import gametest9th.utils.CommandSolver;
import gametest9th.utils.Global;
//import sun.audio.*;

import gametest9th.utils.CommandSolver.MouseCommandListener;
import gametest9th.utils.CommandSolver.MouseState;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

import static gametest9th.utils.Global.*;

public class BatmanScene extends Scene{
    private Batman bm;
    private ArrayList<BatmanDart> bd;
    private Joker jk;
    private int count = 0;
    private long startTIme;
    private long currentTime;


    @Override
    public void sceneBegin() {
        bm = new Batman(SCREEN_X/2 , 550);
        bd = new ArrayList<>();
        jk = new Joker(SCREEN_X/2 ,50, Global.random(1,5));
        startTIme = currentTime = System.nanoTime(); //取得目前系統時間的奈秒

        Client.getInstance().start(5200, "192.168.1.16");

        // new Thread(new Runnable() {
        //     @Override
        //     public void run() {
        //         Client.getInstance().start(5200, "192.168.1.16");
                
        //     }
        // }).start();








    }
    public void move(GameObject object, int x){
        object.collider().setX(x);
    }


    public void start() {
        Server.getInstance().start();
    }

    @Override
    public void sceneEnd() {
        bm = null;
        bd = null;
        jk = null;

    }

    @Override
    public void update() {

        // updateFrame(bm, 1);
        // updateFrame(jk, 2);

        action(new CommandReceiver(){
            @Override
            public void receive(Command command) {
                // System.out.println(command.getCommandCode());
                // System.out.println(command.getStrs().get(0));
                switch(command.getCommandCode()){
                    case Global.Commands.MOVE:
                        if(command.getSerialNum() == 1){
                            
                            if (command.getStrs().get(0).equals("A") || command.getStrs().get(0).equals("a")) {
                                bm.changeDir(0);
                            }
                            if (command.getStrs().get(0).equals("D") || command.getStrs().get(0).equals("d")) {
                                bm.changeDir(800);
                            }
                        }
                        if(command.getSerialNum() == 2){
                            if (command.getStrs().get(0).equals("RIGHT") || command.getStrs().get(0).equals("RIGHT")) {
                                jk.changeDir(800);
                            }
                            if (command.getStrs().get(0).equals("LEFT") || command.getStrs().get(0).equals("LEFT")) {
                                jk.changeDir(0);
                            }
                        }
                        break;
                    case Global.Commands.CONNECT:
                        break;
                }
            }
            
        });
        // action(new CommandReceiver(){
        //     @Override
        //     public void receive(Command command) {
        //         // System.out.println(command.getCommandCode());
        //         // System.out.println(command.getStrs().get(0));
        //         switch(command.getCommandCode()){
        //             case Global.Commands.MOVE:   
        //                 break;
        //             case Global.Commands.CONNECT:
        //                 break;
        //             case Global.Commands.UPDATE_FRAME:
        //                 if(command.getSerialNum() == 1){
        //                     int x = Integer.parseInt(command.getStrs().get(0));
        //                     System.out.println(x);
        //                 }
        //                 if(command.getSerialNum() == 2){
        //                     int x = Integer.parseInt(command.getStrs().get(0));
        //                     System.out.println(x);
        //                 } 
        //                 break;
                    
        //         }
        //     }
            
        // });


        bm.update();
        jk.update();

        currentTime = System.nanoTime();

        //判斷子彈狀態
        for (int i = 0; i < bd.size(); i++) {
            BatmanDart boom = bd.get(i); // 因為我已經找到了 後面就不要再 get 一次
            boom.update();
            if (boom.isCollision(jk)) {
                bd.remove(i--);
                if (jk.getState() == Joker.State.NORMAL) {
                    jk.setHitTimes(jk.getHitTimes() + 1);
                }
                if(jk.getHitTimes() == 2) {
                    SceneController.instance().change(new GameOverScene());
                    break;
                }
                    continue;
                }

            //判斷子彈飛出畫面沒
            if (boom.touchTop()) {
                bd.remove(i--);

                continue;
            }

            //計算時間 每10秒幫joker加狀態
            long currentTime = System.nanoTime();
            gameTime = ((currentTime - startTIme) / 1000000000L);
            jk.addPassCount((int) gameTime);

        }
    }
    public void action(CommandReceiver commandReceiver){
        commandReceiver.receive(Client.getInstance().getCommand());
    }

    public void updateFrame(GameObject o, int id){
        ArrayList<String> arr = new ArrayList<String>();
        arr.add(Integer.toString(o.painter().left()));
        arr.add(Integer.toString(o.painter().top()));
        System.out.println(arr.get(0));
        System.out.println(arr.get(1));
        // arr.add(String.valueOf(o.painter().left())); // x座標
        // arr.add(String.valueOf(o.painter().top())); // y座標
        Client.getInstance().sendCommand(new Command(id, Global.Commands.UPDATE_FRAME, arr));
    }

    @Override
    public void paint(Graphics g) {
        //每秒跑FRAME_LIMIT次
        bm.paint(g);
        jk.paint(g);
//        //畫全部還在的敵機
//        for (int i = 0 ; i < arr.size(); i++) {
//            arr.get(i).paint(g);
//        }
        //畫全部還在的子彈
        for (int i = 0; i < bd.size(); i++) {
            bd.get(i).paint(g);
        }
        g.setColor(Color.red);
        gameTime = (currentTime - startTIme ) / 1000000000;
        g.drawString("Time: " + gameTime + " s", 15, 15);
    }

    @Override
    public MouseCommandListener mouseListener() {
        return (MouseEvent e, MouseState state, long trigTime) -> {
                bm.mouseTrig(e, state, trigTime);              
        };
    }
//     @Override
//     public CommandSolver.MouseCommandListener mouseListener() {
//     //    return new CommandSolver.MouseCommandListener(){
//     //        @Override
//     //        public void mouseTrig(MouseEvent e, CommandSolver.MouseState state, long trigTime){
//     //             bm.mouseTrig(e, state, trigTime);
//     //        }
//     //    };
//         return null;
//    }

    @Override
    public CommandSolver.KeyListener keyListener() {
    return new CommandSolver.KeyListener() {
        @Override
        public void keyPressed(int commandCode, long trigTime) {
            // bm.keyPressed(commandCode, trigTime);
            // if (commandCode == SPACE) {
            //     bd.add(new BatmanDart(bm.painter().left(), bm.painter().top()));//子彈準備要畫在飛機所在位置的地方
            // }
            if (commandCode == Global.D) {
                ArrayList<String> arr = new ArrayList<String>();
                arr.add("D");
                // arr.add(String.valueOf(bm.painter().left()));
                // arr.add(String.valueOf(bm.painter().top()));
                Client.getInstance().sendCommand(new Client.Command(1, Global.Commands.MOVE, arr));
            } else if (commandCode == Global.A) {
                ArrayList<String> arr = new ArrayList<String>();
                arr.add("A");
                // arr.add(String.valueOf(bm.painter().left()));
                // arr.add(String.valueOf(bm.painter().top()));
                Client.getInstance().sendCommand(new Client.Command(1, Global.Commands.MOVE, arr));
            }
            if (commandCode == Global.RIGHT) {
                ArrayList<String> arr = new ArrayList<String>();
                arr.add("RIGHT");
                // arr.add(String.valueOf(bm.painter().left()));
                // arr.add(String.valueOf(bm.painter().top()));
                Client.getInstance().sendCommand(new Client.Command(2, Global.Commands.MOVE, arr));
            } else if (commandCode == Global.LEFT) {
                ArrayList<String> arr = new ArrayList<String>();
                arr.add("LEFT");
                // arr.add(String.valueOf(bm.painter().left()));
                // arr.add(String.valueOf(bm.painter().top()));
                Client.getInstance().sendCommand(new Client.Command(2, Global.Commands.MOVE, arr));
            }

        }

        @Override
        public void keyReleased(int commandCode, long trigTime) {

        }

        @Override
        public void keyTyped(char c, long trigTime) {

        }
    };
    }
}
