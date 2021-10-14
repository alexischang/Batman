package GameObj;

import Internet.Client;
import Internet.Client.Command;
import controllers.SceneController;
import gametest9th.utils.CommandSolver;
import gametest9th.utils.Global;
import gametest9th.utils.Path;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Batman extends  GameObject implements  CommandSolver.KeyCommandListener, CommandSolver.MouseCommandListener {
    private Image img;
    private Global.Direction dir;


    public Batman (int x, int y) {
        super(x, y, 50, 50,x, y, 50, 50);
        dir = Global.Direction.RIGHT;
        //img = ImageResourceController.instance().tryGetImage(new Path().img().actors().airCraft());
        img = SceneController.instance().irc().tryGetImage(new Path().img().actors().batman());
    }




    public void move(){
        if(dir == Global.Direction.RIGHT && !touchRight()) {
            translateX(4);
        } else if ( dir == Global.Direction.LEFT && !touchLeft()){
            translateX(-4);
        }
    }
   
   

    public Global.Direction changeDir( int x){
        if (painter().left() > x) {
            dir = Global.Direction.LEFT;
        } else {
            dir = Global.Direction.RIGHT ;
        }
        return dir;
    }



    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(img, painter().left(), painter().top(), painter().width(), painter().height(),null); // 給左上角座標 和 寬高
    }

    @Override
    public void update() {
        //每秒跑UPDATE_TIMES_PER_SEC次
        // int serialNum = 0;
        // int commandCode = 0;
        // String word = "";
        // if(Client.getInstance().getData() != null){
        //     serialNum = Client.getInstance().getData().serialNum();
        //     commandCode = Client.getInstance().getData().commandCode();
        //     word = Client.getInstance().getData().list().get(0);
        // }
        // System.out.println(word);
        // if (!word.isEmpty()) {
        //     System.out.println("我有收到:" + word);
        // }
        // if(word.equals("LEFT")){
        //     changeDir(0);
        // }
        // else{
        //     changeDir(800);
        // }
        // if (word.equals("A")) {
        //     changeDir(0);
        //     System.out.println("serialNum: "+ serialNum);
        //     System.out.println("commandCode: "+ commandCode);
        // }
        // else if (word.equals("D")) {
        //     changeDir(800);
        //     System.out.println("serialNum: "+ serialNum);
        //     System.out.println("commandCode: "+ commandCode);
        // }
        move();
    }

   @Override
   public void mouseTrig(MouseEvent e, CommandSolver.MouseState state, long trigTime) {  // 抓滑鼠在動的時候鼠標的位置
    //    if (state == CommandSolver.MouseState.CLICKED) {
    //         // changeDir(e.getX() );
    //        System.out.println("CLICKED");
    //        Client.getInstance("192.168.1.25" , 5200).send("A");
    //    }
    //    else{
    //        Client.getInstance("192.168.1.25" , 5200).send("D");
    //    }
   }

    @Override
    public void keyPressed(int commandCode, long trigTime) {
        // if (commandCode == Global.D) {
        //     ArrayList<String> arr = new ArrayList<String>();
        //     arr.add("D");
        //     Client.getInstance().send(new Command(1, 2, arr));
        //    System.out.println("D");
        // } else if (commandCode == Global.A) {
        //     ArrayList<String> arr = new ArrayList<String>();
        //     arr.add("A");
        //     Client.getInstance().send(new Command(2, 2, arr));
        //    System.out.println("A");
        // }

    }

    @Override
    public void keyReleased(int commandCode, long trigTime) {
//         if (commandCode == Global.D) {
//             Client.getInstance("192.168.1.25" , 5200).send("D");
// //            System.out.println("D");
//         } else if (commandCode == Global.A) {
//             Client.getInstance("192.168.1.25" , 5200).send("A");
// //            System.out.println("A");
//         }

    }
}
