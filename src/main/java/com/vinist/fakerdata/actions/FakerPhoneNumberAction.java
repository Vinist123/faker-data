package com.vinist.fakerdata.actions;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.vinist.fakerdata.DataUtil;

/**
 * @title: FakerPhoneNumberAction
 * @description: <TODO Description>
 * @author: hd
 * @date: 2024/6/4 09:12
 */
public class FakerPhoneNumberAction extends AnAction {
    @Override
    public void actionPerformed(AnActionEvent e) {
        // 获取当前项目和编辑器
        Project project = e.getProject();
        Editor editor = e.getData(CommonDataKeys.EDITOR);

        if (project != null && editor != null) {
            // 获取当前光标位置
            int offset = editor.getCaretModel().getOffset();

            // 随机生成手机号码
            String phoneNumber = this.getTel();

            // 插入手机号码到代码编辑区
            Document document = editor.getDocument();
            WriteCommandAction.runWriteCommandAction(project ,() ->{
                document.insertString(offset, phoneNumber);
            });
        }
    }

    private static String[] telFirst="134,135,136,137,138,139,150,151,152,157,158,159,130,131,132,155,156,133,153".split(",");
    private static String getTel() {
        int index= DataUtil.getNum(0,telFirst.length-1);
        String first=telFirst[index];
        String second=String.valueOf(DataUtil.getNum(1,888)+10000).substring(1);
        String thrid=String.valueOf(DataUtil.getNum(1,9100)+10000).substring(1);
        return first+second+thrid;
    }
}
