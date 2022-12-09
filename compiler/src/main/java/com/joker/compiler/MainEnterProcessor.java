package com.joker.compiler;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedOptions;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.Name;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;

import com.google.auto.service.AutoService;
import com.joker.annotation.MainEnter;

import java.io.Writer;
import java.util.Set;

@AutoService(Processor.class)
@SupportedAnnotationTypes({"com.joker.annotation.MainEnter"})
@SupportedOptions("content")
@SupportedSourceVersion(SourceVersion.RELEASE_7)
public class MainEnterProcessor extends AbstractProcessor {
    // 操作Element工具类 (类、函数、属性都是Element)
    private Elements elementUtils;

    // type(类信息)工具类，包含用于操作TypeMirror的工具方法
    private Types typeUtils;

    // Messager 用来报告错误，警告和其他提示信息
    private Messager messager;

    // 文件生成器 类/资源，Filter用来创建新的源文件，class文件以及辅助文件
    private Filer filer;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        // 父类受保护属性，可以直接拿来使用。
        // 其实就是init方法的参数ProcessingEnvironment
        // processingEnv.getMessager(); //参考源码64行
        elementUtils = processingEnvironment.getElementUtils();
        messager = processingEnvironment.getMessager();
        filer = processingEnvironment.getFiler();

        // 通过ProcessingEnvironment去获取build.gradle传过来的参数
        String content = processingEnvironment.getOptions().get("content");
        String kotlinContent = processingEnvironment.getOptions().get("kotlinContent");
        // 有坑：Diagnostic.Kind.ERROR，异常会自动结束，不像安卓中Log.e那么好使
        messager.printMessage(Diagnostic.Kind.NOTE, content + " kotlin " + kotlinContent);
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        if (annotations.isEmpty()) return false;
        // 获取所有带ARouter注解的 类节点
        Set<? extends Element> elements = roundEnv.getElementsAnnotatedWith(MainEnter.class);
        String clazzName = "LinkUtil";
        try {
            JavaFileObject sourceFile = filer.createSourceFile("com.xiaobing.improvedemo.link." + clazzName + "");
            Writer writer = sourceFile.openWriter();
            writer.write("package com.xiaobing.improvedemo.link;\n");
            writer.write("import com.xiaobing.improvedemo.main.bean.MainBean;\n");
            writer.write("import java.util.ArrayList;\n");

            writer.write("public class " + clazzName + " {\n");
            writer.write("public static final String SCHEME = \"xiaobing\";\n");
            writer.write("public ArrayList<MainBean> data = new ArrayList<>();\n");
            writer.write("public ArrayList<MainBean> getMainBeans() {\n");
            writer.write("if (data.size() > 0) return data;\n");


            for (Element element : elements) {
                String packageName = elementUtils.getPackageOf(element).getQualifiedName().toString();
                messager.printMessage(Diagnostic.Kind.NOTE, "QualifiedName= " + packageName);
                Name className = element.getSimpleName();
                messager.printMessage(Diagnostic.Kind.NOTE, "sName= " + className);

                MainEnter annotation = element.getAnnotation(MainEnter.class);
                String name = annotation.name();
                messager.printMessage(Diagnostic.Kind.NOTE, "name= " + name);

                writer.write("data.add(new MainBean.Builder().name(\"" + name + "\").packageName(\"" + packageName + "\").className(\"" + className + "\").build());\n");
            }
            writer.write("return data;\n");
            writer.write("    }\n");
            writer.write("}");
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return true;
    }
}