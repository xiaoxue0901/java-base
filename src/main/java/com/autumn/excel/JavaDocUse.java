package com.autumn.excel;

import com.autumn.excel.bean.ExcelDataVO;
import com.sun.javadoc.ClassDoc;
import com.sun.javadoc.MethodDoc;
import com.sun.javadoc.RootDoc;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;
import java.util.*;

/**
 * @author xql132@zcsmart.com
 * @date 2020/8/10 17:03
 * @description
 */
@Slf4j
public class JavaDocUse {
    private static RootDoc rootDoc;

    public static class Doclet {
        public static boolean start(RootDoc rootDoc) {
            JavaDocUse.rootDoc = rootDoc;
            return true;
        }
    }

    /**
     * 显示DocRoot中的基本信息
     */
    public static List<ExcelDataVO> show(String fileName, String path, String groupId) {
        ClassDoc[] classes = rootDoc.classes();
        List<ExcelDataVO> data = new ArrayList<>();
        for (ClassDoc classDoc : classes) {
            log.info("类的注释:{}", classDoc.commentText());
            Map<String, String> result = getTag(path, fileName);
            if (!result.isEmpty()) {
                MethodDoc[] methodDocs = classDoc.methods();
                for (MethodDoc methodDoc : methodDocs) {
                    // 打印出方法上的注释
                    String tag = result.get(methodDoc.name());
                    log.info("类名:{}, 方法名:{}, 方法注释:{}, 方法url: {}", classDoc.name(), methodDoc.name(), methodDoc.commentText(), tag);
                    ExcelDataVO vo = new ExcelDataVO();
                    vo.setName(methodDoc.commentText());
                    vo.setGroupId(groupId);
                    vo.setOldUrl(tag);
                    data.add(vo);
                }
            }
        }
        return data;

    }

    public static Map<String, String> getTag(String path, String sourceName) {
        Class source = null;
        // 得到包名
        int start = path.indexOf("com");
        String packname = path.substring(start).replace("\\", ".");
        try {
            source = Class.forName(packname.concat(sourceName.replace("/", ".").replace(".java", "")));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Map<String, String> result = new HashMap<>();
        Method[] methods = source.getDeclaredMethods();
        List<Method> ms = Arrays.asList(methods);
        ms.forEach(e -> {
            // ActorProvider an = e.getAnnotation(ActorProvider.class);
            // if (null != an) {
            //     log.info("方法:{}, 打印出tag:{}", e.getName(), an.tag());
            //     result.put(e.getName(), an.tag());
            // }
        });
        return result;
    }


}
