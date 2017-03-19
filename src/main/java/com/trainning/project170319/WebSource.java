package com.trainning.project170319;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 网页抓取
 * 
 * @author JiangChao
 *
 */
public class WebSource {

    /**
     * 简单的抓取网页
     * 
     * @param 网站地址
     * @return 网页内容
     */
    public static String downloadPage(String path) {
        StringBuilder builder = new StringBuilder();
        try {
            URL pageUrl = new URL(path);
            // 创建流
            BufferedReader reader = new BufferedReader(new InputStreamReader(pageUrl.openStream()));
            String line;
            // 读取内容
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
        }
        return builder.toString();

    }

    /**
     * 使用Scanner实现的抓取网页
     * 
     * @param 网站地址
     * @return 网页内容
     */
    public static String downloadPage2(String path) {
        StringBuilder builder = new StringBuilder();
        try {
            URL pageUrl = new URL(path);
            // 使用Reader对象容易因网络原因发生异常，可以使用Scanner.
            java.util.Scanner scanner = new java.util.Scanner(new InputStreamReader(pageUrl.openStream(), "UTF-8"));
            while (scanner.hasNext()) {
                builder.append(scanner.next());
            }
        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
        }
        return builder.toString();
    }

    /**
     * 使用NIO方式实现的抓取网页
     * 
     * @param 网站地址
     * @return 网页内容
     */
    public static void downloadPage3() {
        Map<SocketChannel, String> map = new HashMap<SocketChannel, String>();
        try {
            Selector sel = Selector.open();
            // 选择一个网页
            openConnection(map, sel, "hao123.com", "/book.htm", 80);
            openConnection(map, sel, "www.lietu.com", "/index.jsp", 80);
            traverseKey(map, sel);
        } catch (IOException e) {
            System.out.println("Error : " + e.getMessage());
        }
    }

    private static void openConnection(Map<SocketChannel, String> map, Selector sel, String ip, String path, int port) {
        try {
            // Selector类通过select()方法，将注册的Chabbel中有事件发生的KEY拿出来处理
            SocketChannel channel;
            // SocketChannel用于和Web服务器建立连接
            channel = SocketChannel.open();
            channel.configureBlocking(false);
            channel.connect(new InetSocketAddress(ip, port));
            channel.register(sel, SelectionKey.OP_CONNECT | SelectionKey.OP_READ | SelectionKey.OP_WRITE);
            map.put(channel, path);
        } catch (IOException e) {
            System.out.println("Error : " + e.getMessage());
        }
    }

    private static void traverseKey(Map<SocketChannel, String> map, Selector sel) throws IOException {
        while (!sel.keys().isEmpty()) {
            if (sel.select(100) > 0) {
                Iterator<SelectionKey> iterator = sel.selectedKeys().iterator();
                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();
                    iterator.remove();
                    try {
                        processKey(map, key);
                    } catch (Exception e) {
                        key.cancel();
                    }
                }
            }
        }
    }

    private static void processKey(Map<SocketChannel, String> map, SelectionKey key) throws IOException {
        SocketChannel channel = (SocketChannel) key.channel();
        if (key.isValid() && key.isConnectable()) {
            boolean flag = channel.finishConnect();
            if (flag) {
                key.cancel();
            }
            sendMessages(channel, "GET" + map.get(channel) + "HTTP/1.0\r\nAccept: */*\r\n\r\n");
        } else if (key.isReadable()) {
            String msString = readMessage(channel);
            if (msString != null && msString.length() > 0) {
                System.out.println(msString);
            } else {
                key.cancel();
            }
        }
    }

    private static String readMessage(SocketChannel channel) throws IOException {
        String result = null;
        ByteBuffer buf = ByteBuffer.allocate(1024);
        int i = channel.read(buf);
        buf.flip();
        if (i != -1) {
            result = new String(buf.array(), 0, i);
        }
        return result;
    }

    private static boolean sendMessages(SocketChannel channel, String msString) {
        ByteBuffer buf = ByteBuffer.allocate(1024);
        buf = ByteBuffer.wrap(msString.getBytes());
        try {
            channel.write(buf);
        } catch (IOException e) {
            return true;
        }
        return false;

    }

}
