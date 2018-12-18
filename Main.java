package com.lin.os;


import java.util.*;

public class Main {

    // 数据并没有持久化
    private static List<FileModel> fileIndex = new ArrayList<>();

    // 登录 Linux 服务器默认目录
    private static Map<String, String> indexMap = new HashMap();

    public static void main(String[] args) {

        indexMap.put("indexName", "~");
        indexMap.put("fullIndex", "/root");
//        indexMap.put("memory", "1000");

        Scanner scanner = new Scanner(System.in);
        while (true) {
            // "linhongcun" 多用户版本再续、伪终端
            System.out.print("[" + "linhongcun " + indexMap.get("indexName") + "]# ");
//            System.out.println();
//            System.out.println("fullIndex: " + indexMap.get("fullIndex"));
//            System.out.println("indexName: " + indexMap.get("indexName"));
            String command = scanner.nextLine();
            String info = CommandUtils.executeCommand(command, fileIndex, indexMap);
            System.out.println(info);
        }
    }

}
