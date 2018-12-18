package com.lin.os;

import java.util.List;
import java.util.Map;

public class CommandUtils {

    public static String executeCommand(String command, List<FileModel> fileIndex, Map<String, String> indexMap) {

        String[] split = command.split(" ");

        switch (split[0]) {
            case "vi": // 创建文件
                if (split.length == 1) {
                    // vi
                    return "Try 'vi --help' for more information.";
                }
                if (split.length == 2) {
                    // vi ITAEM.txt
                    return FileService.createFile(fileIndex, indexMap.get("fullIndex"), split[1], 0, 10);
                }
                if (split.length == 3) {
                    return FileService.createFile(fileIndex, indexMap.get("fullIndex"), split[1], 0, Integer.valueOf(split[2]));
                }
            case "mkdir": // 创建文件夹
                if (split.length == 1) {
                    // mkdir
                    return "Try 'mkdir --help' for more information.";
                }
                if (split.length == 2) {
                    // mkdir ITAEM
                    return FileService.createFile(fileIndex, indexMap.get("fullIndex"), split[1], 1, 10);
                }
                if (split.length == 3) {
                    // mkdir ITAEM 20
                    return FileService.createFile(fileIndex, indexMap.get("fullIndex"), split[1], 1, Integer.valueOf(split[2]));
                }
            case "ls":
                // ls
                return FileService.ls(fileIndex, indexMap.get("fullIndex"));
            case "help":
                // help
                return FileService.help();
            case "ll":
                if (split.length == 1) {
                    // ll
                    return FileService.ll(fileIndex, indexMap.get("fullIndex"));
                }
            case "rm":
                return FileService.rm(fileIndex, indexMap.get("fullIndex"), split[1]);
            case "cd":
                if (split.length == 1) {
                    return FileService.cd(indexMap);
                }
                if (split.length == 2) {
                    if (split[1].equals("..") || split[1].equals("../")) {
                        return FileService.cdBack(indexMap);
                    }
                    return FileService.cd(fileIndex, indexMap, split[1]);
                }
            case "cat":
                return FileService.cat(fileIndex, indexMap, split[1]);
            case "echo":
                if (split.length == 2) {
                    return FileService.createFile(fileIndex, indexMap.get("fullIndex"), split[1], 0, 10);
                }
                if (split.length == 4) {
                    return FileService.echo(fileIndex, indexMap, split[3], split[1]);
                }
            default:
                return "command not found";
        }
    }

}
