package com.example.beetl;

import org.beetl.core.Configuration;
import org.beetl.core.GroupTemplate;
import org.beetl.core.Template;
import org.beetl.core.resource.ClasspathResourceLoader;
import org.beetl.core.resource.FileResourceLoader;
import org.beetl.core.resource.StringTemplateResourceLoader;

/**
 * GroupTemplate测试
 *
 * @author DINGJUN
 * @date 2019.04.03
 */
public class HelloWorld {
    public static void main(String[] args) throws Exception {
//        stringTemplateResourceLoader();
//        fileResourceLoader();
        classpathResourceLoader();
    }


    /**
     * StringTemplateResourceLoader：字符串模板加载器，用于加载字符串模板，如本例所示
     * FileResourceLoader：文件模板加载器，需要一个根目录作为参数构造，传入getTemplate方法的String是模板文件相对于Root目录的相对路径
     * ClasspathResourceLoader：文件模板加载器，模板文件位于Classpath里
     * WebAppResourceLoader：用于webapp集成，假定模板根目录就是WebRoot目录，参考web集成章
     * MapResourceLoader : 可以动态存入模板
     * CompositeResourceLoader 混合使用多种加载方式
     */
    /**
     * template.render() 返回渲染结果，如本例所示
     * template.renderTo(Writer) 渲染结果输出到Writer里
     * template.renderTo(OutputStream ) 渲染结果输出到OutputStream里
     */

    //
    public static void stringTemplateResourceLoader() throws Exception {
        StringTemplateResourceLoader resourceLoader = new StringTemplateResourceLoader();
        Configuration configuration = Configuration.defaultConfiguration();
        GroupTemplate groupTemplate = new GroupTemplate(resourceLoader, configuration);
        Template t = groupTemplate.getTemplate("hello,${name}");
        t.binding("name", "beetl");
        String str = t.render();
        System.out.println(str);
    }

    // 模板资源是以文件形式管理的，集中放在某一个文件目录下（如webapp的模板根目录就可能是WEB-INF/template里）
    public static void fileResourceLoader() throws Exception {
        FileResourceLoader resourceLoader = new FileResourceLoader("user.dir.template", "UTF-8");
        Configuration configuration = Configuration.defaultConfiguration();
        GroupTemplate groupTemplate = new GroupTemplate(resourceLoader, configuration);
        Template template = groupTemplate.getTemplate("/s01/hello.txt");
        String render = template.render();
        System.out.println(render);
    }

    // 模板资源是打包到jar文件或者同Class放在一起，因此，可以使用ClasspathResourceLoader来加载模板实例
    public static void classpathResourceLoader() throws Exception {
        ClasspathResourceLoader resourceLoader = new ClasspathResourceLoader("template");
        Configuration configuration = Configuration.defaultConfiguration();
        GroupTemplate groupTemplate = new GroupTemplate(resourceLoader, configuration);
        Template template = groupTemplate.getTemplate("/hello.txt");
        String render = template.render();
        System.out.println(render);

    }
}




























