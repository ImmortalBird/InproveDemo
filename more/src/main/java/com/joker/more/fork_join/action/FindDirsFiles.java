package com.joker.more.fork_join.action;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

/**
 * 使用 ForkJoin 实现一个查找某个路径下的 复合条件的文件
 * <p>
 * 当前条件为 以txt结尾的文件
 */
public class FindDirsFiles extends RecursiveAction {

    public File path;

    public FindDirsFiles(File path) {
        this.path = path;
    }

    @Override
    protected void compute() {
        // 有效性校验，无效的直接return
        if (path == null) return;
        String matcher = "txt";
        if (path.isFile()) {
            if (path.getAbsolutePath().endsWith(matcher)) {
                System.out.println("文件:" + path.getAbsolutePath());
            }
            return;
        }
        File[] files = path.listFiles();
        if (files == null) return;

        List<FindDirsFiles> subTasks = new ArrayList<>();
        // 到这里说明这是一个文件夹，并且路径下的子目录不为空
        for (File file : files) {
            if (file.isDirectory()) {
                // 如果是一个文件夹，就创建一个新的任务
                subTasks.add(new FindDirsFiles(file));
            } else {
                if (file.getAbsolutePath().endsWith(matcher)) {
                    System.out.println("文件:" + file.getAbsolutePath());
                }
            }
        }
        if (subTasks.isEmpty()) return;

        // 在当前 ForkJoinPool调度所有子任务
        for (FindDirsFiles subTask : invokeAll(subTasks)) {
            subTask.join();
        }

    }


    public static void main(String[] args) {
        /*主线程做自己的业务工作*/
        System.out.println("main is Running......");
        ForkJoinPool pool = new ForkJoinPool();

        System.out.println("ForkJoinPool execute task");
        // 异步提交，任务并没有开始执行
        FindDirsFiles task = new FindDirsFiles(new File("D:/"));
        pool.execute(task);
        /*主线程做自己的业务工作*/
        System.out.println("main is Running......");

        System.out.println("task join");
        task.join();

        System.out.println("main is Running......");

        System.out.println("main is End");
    }
}
