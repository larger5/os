package com.lin.os;

import java.util.List;
import java.util.Map;

public class FileService {

    // 创建文件
    public static String createFile(List<FileModel> fileIndex, String fullIndex, String name, Integer attr, Integer size) {
        FileModel fileModel = new FileModel();
        fileModel.setAttr(attr);
        fileModel.setName(name);
        fileModel.setSize(size);
        fileModel.setFatherIndex(fullIndex);
        fileIndex.add(fileModel);
        return "file has been created successfully.";
    }

    // 显示所有文件
    public static String ls(List<FileModel> fileIndex, String fullIndex) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < fileIndex.size(); i++) {
            // 父目录
            if (fileIndex.get(i).getFatherIndex().equals(fullIndex)) {
                sb.append(fileIndex.get(i).getName()).append(" ");
            }
        }
        return sb.toString();
    }

    // 解释命令
    public static String help() {
        StringBuilder sb = new StringBuilder();
        sb.append("cd [dir]                      enter new dir\n");
        sb.append("ls                            show all files\n");
        sb.append("mkdir [name] [size]           create dir\n");
        sb.append("vi [name] [size]              create file\n");
        sb.append("rm [name]                     remove file\n");
        sb.append("help                          show command API\n");
        sb.append("ll                            show file detail\n");
        sb.append("echo [content] > [name]       add content to file\n");
        sb.append("cat [name]                    show file's content\n");
        return sb.toString();
    }

    // 显示文件详细信息
    public static String ll(List<FileModel> fileIndex, String fullIndex) {
        StringBuilder sb = new StringBuilder();
        int total = 0;
        int free = 1000;
        for (int i = 0; i < fileIndex.size(); i++) {
            // 父目录
            if (fileIndex.get(i).getFatherIndex().equals(fullIndex)) {
                total++;
            }
            free -= fileIndex.get(i).getSize();
        }
        sb.append("total ").append(total).append("\n");
        sb.append("free ").append(free).append(" k\n");
        for (int i = 0; i < fileIndex.size(); i++) {
            // 父目录
            if (fileIndex.get(i).getFatherIndex().equals(fullIndex)) {
                sb.append(fileIndex.get(i).getPermission()).append(" ")
                        .append(fileIndex.get(i).getSize()).append(" ")
                        .append(fileIndex.get(i).getDate()).append(" ")
                        .append(fileIndex.get(i).getName()).append(" ")
                        .append("\n");
            }
        }
        return sb.toString();
    }

    // 删除文件，缺陷：子目录没有删除
    public static String rm(List<FileModel> fileIndex, String fullIndex, String name) {
        for (int i = 0; i < fileIndex.size(); i++) {
            // 父目录
            if (fileIndex.get(i).getFatherIndex().equals(fullIndex)) {
                if (fileIndex.get(i).getName().equals(name)) {
                    fileIndex.remove(i);
                    break;
                }
            }
        }
        return "file has been removed successfully.";
    }

    // 改变目录
    public static String cd(List<FileModel> fileIndex, Map<String, String> indexMap, String dirName) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < fileIndex.size(); i++) {
            // 父目录
            if (fileIndex.get(i).getFatherIndex().equals(indexMap.get("fullIndex"))) {
                if (fileIndex.get(i).getName().equals(dirName)) {
                    if (fileIndex.get(i).getAttr() == 0) {
                        sb.append(fileIndex.get(i).getName()).append("is not a dir.");
                    } else {
                        String fullIndex = indexMap.get("fullIndex") + "/" + dirName;
                        indexMap.put("fullIndex", fullIndex);
                        indexMap.put("indexName", dirName);
                        sb.append("change dir successfully.");
                    }
                }
            }
        }
        return sb.toString();
    }

    // 回根目录
    public static String cd(Map<String, String> indexMap) {
        StringBuilder sb = new StringBuilder();
        indexMap.put("fullIndex", "/root");
        indexMap.put("indexName", "~");
        sb.append("change dir successfully.");
        return sb.toString();
    }

    // 回退目录
    public static String cdBack(Map<String, String> indexMap) {
        StringBuilder sb = new StringBuilder();
        StringBuilder dir = new StringBuilder();
        String fullIndex = indexMap.get("fullIndex");
        String[] split = fullIndex.split("/");
        for (int i = 0; i < split.length - 1; i++) {
            dir.append("/").append(split[i]);
        }
        indexMap.put("fullIndex", dir.substring(1)); // 总是  //root/xxx/xx
        indexMap.put("indexName", split[split.length - 2]);
        return sb.toString();
    }

    public static String cat(List<FileModel> fileIndex, Map<String, String> indexMap, String fileName) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < fileIndex.size(); i++) {
            // 父目录
            if (fileIndex.get(i).getFatherIndex().equals(indexMap.get("fullIndex"))) {
                if (fileIndex.get(i).getName().equals(fileName)) {
                    sb.append(fileIndex.get(i).getContent());
                    break;
                }
            }
        }
        return sb.toString();
    }

    // echo "It is a test" > myfile
    public static String echo(List<FileModel> fileIndex, Map<String, String> indexMap, String fileName, String addContent) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < fileIndex.size(); i++) {
            // 父目录
            if (fileIndex.get(i).getFatherIndex().equals(indexMap.get("fullIndex"))) {
                if (fileIndex.get(i).getName().equals(fileName)) {
                    fileIndex.get(i).setContent(fileIndex.get(i).getContent() + addContent);
                    break;
                }
            }
        }
        sb.append("[" + addContent + "] has been written into [" + fileName + "]");
        return sb.toString();
    }
}
