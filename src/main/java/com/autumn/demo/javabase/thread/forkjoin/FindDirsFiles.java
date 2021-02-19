package com.autumn.demo.javabase.thread.forkjoin;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

/**
 * @author xql132@zcsmart.com
 * @date 2021/2/11
 * @time 9:26 上午
 * @description
 */
@Slf4j
public class FindDirsFiles extends RecursiveAction {
    // 当前任务需要搜寻的目录
    private File path;

    public FindDirsFiles(File path) {
        this.path = path;
    }



    @Override
    protected void compute() {
        List<FindDirsFiles> subTasks = new ArrayList<>();
        File[] files = path.listFiles();
        if (files !=null) {
            for(File file : files) {
                if (file.isDirectory()) {
                    subTasks.add(new FindDirsFiles(file));
                } else {
                    // 遇到文件, 检查
                    if(file.getAbsolutePath().endsWith("txt")){
                        log.info("文件:{}", file.getAbsolutePath());
                    }
                }
            }
        }
        if (!subTasks.isEmpty()) {
            for(FindDirsFiles findDirsFiles:invokeAll(subTasks)){
                findDirsFiles.invoke();
            }
        }
    }

    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool();
        FindDirsFiles task = new FindDirsFiles(new File("/Users/xql/workspace/github/java-base/src/main/java/com/autumn/demo"));
        // 异步调用
        pool.execute(task);
        log.info("Task is running...");
        int otherWork = 0;
        for (int i=0; i<100; i++) {
            otherWork = otherWork+i;
        }
        log.info("main thread done sth...., otherWork:{}", otherWork);
        // 阻塞方法
        task.join();
        log.info("task end");
    }
}
