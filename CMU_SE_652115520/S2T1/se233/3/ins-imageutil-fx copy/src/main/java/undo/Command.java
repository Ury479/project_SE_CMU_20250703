package com.image.undo;

public interface Command {
    void execute();   // 执行操作
    void undo();      // 撤销操作
}
