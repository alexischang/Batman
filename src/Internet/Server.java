package Internet;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Vector;

public class Server {

    private static Server servers;
    private ServerSocket server;

    public static Server getInstance() {
        if (servers == null) {
            servers = new Server();
        }
        return servers;
    }

    //將接收到的socket變成一個集合
    protected static   List<Socket> sockets = new Vector<>();

    //開跑伺服器
    public void start() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    exec();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    private   void exec() throws IOException {
        //建立服務端
        server = new ServerSocket(5200);
        boolean flag = true;
        //接受客戶端請求
        while (flag){
            try {
                System.out.println("Server start to run");
                //阻塞等待客戶端的連線
                Socket accept = server.accept();
                synchronized (sockets){
                    sockets.add(accept);
                }
                //多個伺服器執行緒進行對客戶端的響應
                Thread thread = new Thread(new ServerThead(accept));
                thread.start();
                //捕獲異常。
            }catch (Exception e){
                flag = false;
                e.printStackTrace();
            }
        }

    }
    //關閉伺服器
    public void disconnect() throws IOException {
        server.close();
    }



}
