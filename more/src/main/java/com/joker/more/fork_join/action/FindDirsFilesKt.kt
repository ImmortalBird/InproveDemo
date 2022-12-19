package com.joker.more.fork_join.action

import java.io.File
import java.util.concurrent.ForkJoinPool
import java.util.concurrent.RecursiveAction

/**
 * 使用ForJoin找出文件 异步提交 Kotlin版
 * @property path File
 * @property end String
 * @constructor
 */
class FindDirsFilesKt(val path: File) : RecursiveAction() {
    val end = "wzd"
    override fun compute() {
//        if (path.absolutePath.endsWith(end)) println("")
        val subTasks = mutableListOf<FindDirsFilesKt>()
        if (path.isDirectory) {
            val listFiles = path.listFiles() ?: return
            for (listFile in listFiles) {
                if (path.isDirectory) {
                    subTasks.add(FindDirsFilesKt(listFile))
                } else {
                    if (path.absolutePath.endsWith(end)) println("文件：${path.absolutePath}")
                }
            }
        } else {
            if (path.absolutePath.endsWith(end)) println("文件：${path.absolutePath}")
        }
        if (subTasks.isNotEmpty()) {
            for (subTask in invokeAll(subTasks)) {
                subTask.join()
            }
        }
    }
}

// 异步提交
fun main() {
    println("main is Running ...")
    var start = System.currentTimeMillis()
    /**单线程********************/
    println("pool invoke task ...")
    find(File("D:/"))
    println("main is Running ... ${System.currentTimeMillis() - start} ")
    /**********************/
    start = System.currentTimeMillis()
    val pool = ForkJoinPool()
    val task = FindDirsFilesKt(File("D:/"))


    /**同步提交********************/
    println("pool invoke task ...")
    pool.invoke(task)
    println("main is Running ... ${System.currentTimeMillis() - start}")
    /**********************/

    /**异步提交********************/
//    println("pool execute task ...")
//    pool.execute(task)
//    println("main is Running ...")
//    println("task join ...")
//    task.join()
    /**********************/
    println("main is Running ...")
    println("main is End")
}

fun find(path :File ){
    val end = "wzd"
    if (path.isDirectory) {
        val listFiles = path.listFiles() ?: return
        for (file in listFiles) {
            if (file.isDirectory) {
                find(file)
            } else {
                if (file.absolutePath.endsWith(end)) println("文件：${file.absolutePath}")
            }
        }
    } else {
        if (path.absolutePath.endsWith(end)) println("文件：${path.absolutePath}")
    }
}