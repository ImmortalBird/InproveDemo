package com.joker.more.fork_join.action

import java.io.File
import java.util.concurrent.ForkJoinPool
import java.util.concurrent.RecursiveAction

/**
 * 使用ForJoin找出文件Kotlin版
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

fun main() {
    println("main is Running ...")
    val pool = ForkJoinPool()
    val task = FindDirsFilesKt(File("D:/"))
    println("pool execute task ...")
    pool.execute(task)
    println("main is Running ...")
    println("task join ...")
    task.join()
    println("main is Running ...")
    println("main is End")
}