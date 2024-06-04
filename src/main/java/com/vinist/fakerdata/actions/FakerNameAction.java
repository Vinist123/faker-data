package com.vinist.fakerdata.actions;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;

import java.io.UnsupportedEncodingException;
import java.util.Random;

/**
 * @title: FakerNameAction
 * @description: <TODO Description>
 * @author: hd
 * @date: 2024/6/4 13:58
 */
public class FakerNameAction extends AnAction {
    private static String firstName="赵钱孙李周吴郑王冯陈褚卫蒋沈韩杨朱秦尤许何吕施张孔曹严华金魏陶姜戚谢邹喻柏水窦章云苏潘葛奚范彭郎鲁韦昌马苗凤花方俞任袁柳酆鲍史唐费廉岑薛雷贺倪汤滕殷罗毕郝邬安常乐于时傅皮卞齐康伍余元卜顾孟平黄和穆萧尹姚邵湛汪祁毛禹狄米贝明臧计伏成戴谈宋茅庞熊纪舒屈项祝董梁杜阮蓝闵席季麻强贾路娄危江童颜郭梅盛林刁钟徐邱骆高夏蔡田樊胡凌霍虞万支柯咎管卢莫经房裘缪干解应宗宣丁贲邓郁单杭洪包诸左石崔吉钮龚程嵇邢滑裴陆荣翁荀羊於惠甄魏加封芮羿储靳汲邴糜松井段富巫乌焦巴弓牧隗山谷车侯宓蓬全郗班仰秋仲伊宫宁仇栾暴甘钭厉戎祖武符刘姜詹束龙叶幸司韶郜黎蓟薄印宿白怀蒲台从鄂索咸籍赖卓蔺屠蒙池乔阴郁胥能苍双闻莘党翟谭贡劳逄姬申扶堵冉宰郦雍却璩桑桂濮牛寿通边扈燕冀郏浦尚农温别庄晏柴瞿阎充慕连茹习宦艾鱼容向古易慎戈廖庚终暨居衡步都耿满弘匡国文寇广禄阙东殴殳沃利蔚越夔隆师巩厍聂晁勾敖融冷訾辛阚那简饶空曾毋沙乜养鞠须丰巢关蒯相查后江红游竺权逯盖益桓公万俟司马上官欧阳夏侯诸葛闻人东方赫连皇甫尉迟公羊澹台公冶宗政濮阳淳于仲孙太叔申屠公孙乐正轩辕令狐钟离闾丘长孙慕容鲜于宇文司徒司空亓官司寇仉督子车颛孙端木巫马公西漆雕乐正壤驷公良拓拔夹谷宰父谷粱晋楚阎法汝鄢涂钦段干百里东郭南门呼延归海羊舌微生岳帅缑亢况后有琴梁丘左丘东门西门商牟佘佴伯赏南宫墨哈谯笪年爱阳佟第五言福";

    @Override
    public void actionPerformed(AnActionEvent e) {
        // 获取当前项目和编辑器
        Project project = e.getProject();
        Editor editor = e.getData(CommonDataKeys.EDITOR);

        if (project != null && editor != null) {
            // 获取当前光标位置
            int offset = editor.getCaretModel().getOffset();

            // 随机生成姓名
            String name = this.getFirstName() + this.getLastName();


            // 插入姓名到代码编辑区
            Document document = editor.getDocument();
            WriteCommandAction.runWriteCommandAction(project ,() ->{
                document.insertString(offset, name);
            });
        }
    }

    /**
     * 随机获取姓
     *
     * @return
     */
    public static String getFirstName() {
        Random random = new Random();
        int i = random.nextInt(firstName.length());
        char c = firstName.charAt(i);
        return String.valueOf(c);
    }

    /**
     * 获取名
     *
     * @return
     */
    public static String getLastName() {
        StringBuilder ret = new StringBuilder();
        for (int i = 0; i < 2; i++) {
            String str = null;
            // 定义高低位
            int highPos, lowPos;
            Random random = new Random();
            //获取高位值
            highPos = (176 + Math.abs(random.nextInt(39)));
            //获取低位值
            lowPos = (161 + Math.abs(random.nextInt(93)));
            byte[] b = new byte[2];
            b[0] = (Integer.valueOf(highPos).byteValue());
            b[1] = (Integer.valueOf(lowPos).byteValue());
            try {
                //转成中文
                str = new String(b, "GBK");
            } catch (UnsupportedEncodingException ex) {
                ex.printStackTrace();
            }
            ret.append(str);
        }
        return ret.toString();
    }

}
