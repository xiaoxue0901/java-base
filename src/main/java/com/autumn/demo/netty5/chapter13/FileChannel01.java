package com.autumn.demo.netty5.chapter13;

import lombok.extern.slf4j.Slf4j;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author xql132@zcsmart.com
 * @date 2019/7/12 17:44
 * @description
 */
@Slf4j
public class FileChannel01 {

    /**
     * 通过RandomAccessFile打开FileChannel
     * @param absFile
     * @return
     * @throws FileNotFoundException
     */
    private FileChannel openFileChannel(String absFile) {
        RandomAccessFile billFile = null;
        try {
            billFile = new RandomAccessFile(absFile, "rw");
        } catch (FileNotFoundException e) {
            log.error("文件不存在, 地址:{}", absFile);
            log.error("异常:", e);
        }
        FileChannel fileChannel = billFile.getChannel();
        return fileChannel;
    }

    private void readFile() {
        ByteBuffer readBuffer = ByteBuffer.allocate(128);

        FileChannel fileChannel = openFileChannel("");

        try {
            // 将channel中信息存到buffer中
            if (fileChannel.read(readBuffer) != -1) {

            }
        } catch (IOException e) {
            log.info("读文件异常", e);
        }
    }

    private void writeFile() {
        // 打开一个文件channel
        FileChannel fileChannel = openFileChannel("/home/");
        // 要写入的内容
        String content = "a|b|c|d";
        // 分配一个容量128字节的缓冲区
        ByteBuffer writeBuffer = ByteBuffer.allocate(128);
        // 将要写入的内容转为byte放入缓冲区中
        writeBuffer.put(content.getBytes());

        writeBuffer.flip();
        try {
            // channel将content输出到文件中.
            fileChannel.write(writeBuffer);
        } catch (IOException e) {
            log.error("写文件异常", e);
        }
    }


}
