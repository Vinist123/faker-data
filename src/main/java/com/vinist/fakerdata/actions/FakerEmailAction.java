package com.vinist.fakerdata.actions;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

/**
 * @title: FakerEmailAction
 * @description: <TODO Description>
 * @author: hd
 * @date: 2024/6/4 14:31
 */
public class FakerEmailAction extends AnAction {

    public static String base = "abcdefghijklmnopqrstuvwxyz0123456789";
    private static final String[] email_suffix="@gmail.com,@yahoo.com,@msn.com,@hotmail.com,@aol.com,@ask.com,@live.com,@qq.com,@0355.net,@163.com,@163.net,@263.net,@3721.net,@yeah.net,@googlemail.com,@126.com,@sina.com,@sohu.com,@yahoo.com.cn".split(",");

    /**
     * 返回Email
     * @return
     */
    public static String getEmail() {
        Random random = new Random();
        int lMin = random.nextInt(5,15);
        int lMax = lMin + 5;
        int length=(int) (Math.random() * (lMax - lMin + 1) + lMin);
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = (int)(Math.random()*base.length());
            sb.append(base.charAt(number));
        }
        sb.append(email_suffix[(int)(Math.random()*email_suffix.length)]);
        return sb.toString();
    }

    @Override
    public void actionPerformed(AnActionEvent e) {
        // 获取当前项目和编辑器
        Project project = e.getProject();
        Editor editor = e.getData(CommonDataKeys.EDITOR);

        if (project != null && editor != null) {
            // 获取当前光标位置
            int offset = editor.getCaretModel().getOffset();

            // 随机生成email
            String email = this.getEmail();


            // 插入手机号码到代码编辑区
            Document document = editor.getDocument();
            WriteCommandAction.runWriteCommandAction(project ,() ->{
                document.insertString(offset, email);
            });
        }
    }
}
