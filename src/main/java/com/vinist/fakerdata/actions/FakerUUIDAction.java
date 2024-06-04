package com.vinist.fakerdata.actions;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

/**
 * @title: FakerUUIDAction
 * @description: <TODO Description>
 * @author: hd
 * @date: 2024/6/4 09:12
 */
public class FakerUUIDAction extends AnAction {
    @Override
    public void actionPerformed(AnActionEvent e) {
        Project project = e.getProject();
        Editor editor = e.getData(CommonDataKeys.EDITOR);

        if (project != null && editor != null){
            //获取当前光标位置
            int offset = editor.getCaretModel().getOffset();
            //UUID
            UUID uuid = UUID.randomUUID();

            //插入UUID
            Document document = editor.getDocument();
            try{
                WriteCommandAction.runWriteCommandAction(project, () -> {
                    document.insertString(offset, uuid.toString());
                });
            } catch (Exception ex){
                ex.printStackTrace();
            }
        }
    }
}
