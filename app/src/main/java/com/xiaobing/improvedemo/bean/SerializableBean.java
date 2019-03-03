package com.xiaobing.improvedemo.bean;

import java.io.Serializable;

/**
 * Serializable 序列化示例代码
 * Serializable 实现简单，但是会生成许多临时变量，可能会引起GC
 */
public class SerializableBean implements Serializable {
    private int age;
    private String name;

}
