package com.vinist.fakerdata.actions;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @title: FakerAddressAction
 * @description: <TODO Description>
 * @author: hd
 * @date: 2024/6/4 14:39
 */
public class FakerAddressAction extends AnAction {
    private static final List<String> cities = new ArrayList<>();
    private static final List<String> districts = new ArrayList<>();
    private static final List<String> streets = new ArrayList<>();

    static {
        cities.add("北京市");
        cities.add("上海市");
        cities.add("广州市");
        cities.add("深圳市");
        cities.add("杭州市");
        cities.add("南京市");

        districts.add("东城区");
        districts.add("西城区");
        districts.add("崇文区");
        districts.add("宣武区");
        districts.add("朝阳区");
        districts.add("海淀区");
        districts.add("丰台区");
        districts.add("石景山区");
        districts.add("房山区");

        streets.add("长安街");
        streets.add("王府井大街");
        streets.add("西单大街");
        streets.add("东方明珠大街");
        streets.add("天安门大街");
    }

    public static String getAddress() {
        Random random = new Random();
        String city = cities.get(random.nextInt(cities.size()));
        String district = districts.get(random.nextInt(districts.size()));
        String street = streets.get(random.nextInt(streets.size()));
        return city + district + street;
    }
    @Override
    public void actionPerformed(AnActionEvent e) {
        // 获取当前项目和编辑器
        Project project = e.getProject();
        Editor editor = e.getData(CommonDataKeys.EDITOR);

        if (project != null && editor != null) {
            // 获取当前光标位置
            int offset = editor.getCaretModel().getOffset();

            // 随机生成地址
            String address = this.getAddress();


            // 插入地址到代码编辑区
            Document document = editor.getDocument();
            WriteCommandAction.runWriteCommandAction(project ,() ->{
                document.insertString(offset, address);
            });
        }
    }
}
