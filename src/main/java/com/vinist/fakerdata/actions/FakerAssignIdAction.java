package com.vinist.fakerdata.actions;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.vinist.fakerdata.AssignIdGenerator;

/**
 * @title: FakerAssignId
 * @description: <TODO Description>
 * @author: hd
 * @date: 2024/6/4 09:49
 */
public class FakerAssignIdAction extends AnAction {
    private final long workerId = 1L;
    private final long dataCenterId = 1L;
    private long sequence = 0L;
    private final long twepoch = 1288834974657L; // 用于生成时间戳的基准时间
    private final long workerIdBits = 5L;
    private final long dataCenterIdBits = 5L;
    private final long maxWorkerId = -1L ^ (-1L << workerIdBits);
    private final long maxDataCenterId = -1L ^ (-1L << dataCenterIdBits);
    private final long sequenceBits = 12L;
    private final long workerIdShift = sequenceBits;
    private final long dataCenterIdShift = sequenceBits + workerIdBits;
    private final long timestampLeftShift = sequenceBits + workerIdBits + dataCenterIdBits;
    private final long sequenceMask = -1L ^ (-1L << sequenceBits);
    private long lastTimestamp = -1L;


    public synchronized long nextId() {
        long timestamp = timeGen();

        if (timestamp < lastTimestamp) {
            throw new RuntimeException("Clock moved backwards. Refusing to generate ID.");
        }

        if (timestamp == lastTimestamp) {
            sequence = (sequence + 1) & sequenceMask;
            if (sequence == 0) {
                timestamp = tilNextMillis(lastTimestamp);
            }
        } else {
            sequence = 0L;
        }

        lastTimestamp = timestamp;

        return ((timestamp - twepoch) << timestampLeftShift) |
                (dataCenterId << dataCenterIdShift) |
                (workerId << workerIdShift) |
                sequence;
    }

    protected long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    protected long timeGen() {
        return System.currentTimeMillis();
    }

    @Override
    public void actionPerformed(AnActionEvent e) {
        // 获取当前项目和编辑器
        Project project = e.getProject();
        Editor editor = e.getData(CommonDataKeys.EDITOR);

        if (project != null && editor != null) {
            // 获取当前光标位置
            int offset = editor.getCaretModel().getOffset();

            // 随机生成雪花id
            String assignId = String.valueOf(this.nextId());


            // 插入雪花id到代码编辑区
            Document document = editor.getDocument();
            WriteCommandAction.runWriteCommandAction(project ,() ->{
                document.insertString(offset, assignId);
            });
        }
    }

}
