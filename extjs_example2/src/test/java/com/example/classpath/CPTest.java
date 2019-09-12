package com.example.classpath;

import java.net.URL;
import java.util.Map;

/**
 * classpath测试理解
 *
 * 概述
 *
 * >>>>>>>>>>>>>>>>>>>
 *
 * classpath是JVM用到的一个环境变量,它用来指示JVM如何搜索到class.
 *
 * 因为Java是编译型语言,源码文件是.java, 而编译后的.class文件才是真正可以被JVM执行的字节码.因此,JVM需要知道,如果加载一个abc.xyz.Hello的类,应该去哪里搜索对应的Hello.class文件.
 *
 * 所以, classpath就是一组目录的集合,他设置的搜索路径与操作系统相关. 例如, 在Windows系统上,用;分隔,待空格的目录用""括起来,可能长这样:
 *
 * C:\work\project1\bin;C:\shared;"D:\My Document\project1\bin"
 *
 * 在Linux系统上,用:分隔,可能长这样:
 *
 * /usr/shared:/usr/local/bin:/home/dingjun/bin
 *
 * 假设这个classpath是.;C:\work\project1\bin;C:\shared, 当JVM在加载abc.xyz.Hello这个类时, 会依次查找:
 * > <当前目录>\abc\xyz\Hello.class
 * > C:\work\project1\bin\abc\xyz\Hello.class
 * > C:\shared\abc\xyz\Hello.class
 *
 * 注意到"."表示当前目录. 如果JVM在某个路径下找到了对应的class文件,就不再往后继续搜索. 如果所有路径下都没有找到, 就报错.
 *
 * classpath的设定方法有两种:
 * 1.在系统环境变量中设置classpath环境变量,不推荐;
 * 2.在启动JVM时设置classpath变量,推荐.
 *
 * 在启动JVM时设置classpath才是最佳实践. 实际上就是给java命令传入-classpath或-cp参数:
 * java -classpath .;C:\word\project1\bin;C:\shared abc.xyz.Hello
 *
 * 或者使用-cp简写:
 * java -cp .;C:\word\project1\bin;C:\shared abc.xyz.Hello
 *
 * 如果没有设置系统环境变量, 也没有传入-cp参数, 那么JVM默认的classpath为".", 即当前目录:
 * java abc.xyz.Hello
 *
 * 上述命令告诉JVM只在当前目录搜索Hello.class.
 *
 * 在IDE中运行Java程序,IDE自动传入的-cp参数是当前工程的bin目录和引入的jar包.
 *
 * 通常,我们在自己编写的class中,会引用Java核心库的class, 例如, String, ArrayList等. 这些class应该上哪去找?
 * 很多"如何设置classpath"的文章会告诉你把JVM自带的rt.jar放入到classpath, 但事实上, 根本不需要告诉JVM如何去Java核心库查找class,JVM怎么可能笨到连自己的核心库在哪都不知道? (呵呵, 其实在1.5之后JVM升级之后修改加载的逻辑)
 *
 * 注: 不要吧任何Java核心库添加到classpath中! JVM根本不依赖classpath加载核心库!
 *
 * 更好的做法是, 不要设置classpath! 默认的当前目录"."对于绝大多数情况都够用了.
 *
 * jar包
 * 如果有很多.class文件, 散落在各层目录中, 肯定不便于管理. 如果能把目录打一个包, 变成一个文件, 就方便多了.
 *
 * jar包就是用来干这个事的, 它可以把package组织的目录层级, 以及各个目录下的所有文件(包括.class文件和其它文件)都打成一个jar文件, 这样一来, 无论是备份, 还是发送给客户, 简单多了.
 *
 * jar包实际山是一个zip格式的压缩文件, 而jar包相当于目录. 如果我们要执行一个jar包的class, 就可以把jar包放到classpath中:
 *
 * java -cp ./hello.jar abc.xyz.Hello
 *
 * 这样JVM会自动在Hello.jar文件里面去搜索某个类.
 *
 * 如何创建jar包?
 *
 * 因为jar包就是zip包, 所以, 直接在资源管理器中, 找到正确的目录, 点击右键, 在弹出的菜单中选择发送到, 压缩文件, 就只做了一个zip文件, 然后将后缀修改为.jar即可.
 *
 * 注意: 打的压缩包,解压之后的第一层目录应该是包名的第一层目录,如果是其他的名称, 则这个jar包就是错误的.
 *
 * jar包还可以包含一个特殊的/META-INF/MANIFEST.MF文件, MANIFEST.MF是纯文本, 可以指定Main-Class和其它信息.
 * JVM会自动读取这个MANIFEST.MF文件, 如果存在Main-Class, 我们就不必在命令行指定启动的类名, 而是用更方便的命令:
 * java -jar hello.jar
 *
 * jar包还可以包含其它jar包, 这个时候, 就需要在MANIFEST.MF文件里面配置classpath了.
 *
 * 在大型项目中, 不可能手动编写MANIFEST.MF文件, 在手动创建zip包. Java社区提供了大量的开源构建工具, 例如Maven, 可以非常方便的创建jar包.
 *
 * 小结:
 *
 * >>>>>>>>>>>>>>>>>>>>>>>>>>
 *
 * JVM通过环境变量classpath决定搜索的class的路径和顺序;
 *
 * 不推荐设置环境变量classpath, 始终建议通过-cp命令传入;
 *
 * jar包相当于目录, 可以包含很多.class文件, 方便下载和使用;
 *
 * MANIFEST.MF文件可以提供jar包的信息, 如Main-Class, 这样可以执行运行jar包.
 *
 *
 *
 * 下面是两个有关classpath的api测试
 *
 *
 * Created by DINGJUN on 2019.09.11.
 */
public class CPTest {
    public static void main(String[] args) {
        /**
         * 直接运行main函数,此时的classpath表示的是".",也就是当前类所在的文件夹.类所在的文件夹
         *
         * classpath需要以/开头:表示相对于这个类的位置,如果是根目录的话也就是com的同级目录/cp.text
         *
         * 如果是这个类的所在的包中,如放在了com目录下,那就是/com/cp.text
         *
         * classpath不以/开头:则会将配置的路径拼接在这个类所在的包的后面,也就是/com/example/classpath/com/cp.text
         *
         * 注意: IDE会自动将resources目录下的文件拷贝到target/classes/目录下, 也即是编译之后的文件存放的地方, 其实也就是类的根目录
         * 所以我们使用getResource(), getResourceAsStream()等api是可以获取到配置在resources目录下的配置的.properties文件的
         *
         */
        URL resource = CPTest.class.getResource("/com/cp.text");

        System.out.println(resource.getFile()); // -_- /D:/github/FooTest/extjs_example2/target/test-classes/com/cp.text

        System.out.println("--");

        URL resource1 = CPTest.class.getClassLoader().getResource("com/cp.text");
        System.out.println(resource1.getFile()); // -_- /D:/github/FooTest/extjs_example2/target/test-classes/com/cp.text

        /**
         * 总结:
         *
         * >>>>>>>>>>>>>
         *
         * class.getResource(): 如果以/开头则是相对类的根目录(项目)来说的, 否则就会认为这个资源和类是同一个目录(在包中) -- 基准是class文件
         *
         * 而
         *
         * class.getClassLoader().getResource(): 则需要考虑到类加载器, 自定义的类通过app加载器加载, /开头则是表示根目录,资源位于app加载器的父亲父亲的加载classpath目录/lib下,
         * 如果使用相对路径,则是表示资源位于根目录(项目) -- 基准是文件的最外层包
         */

        System.out.println("--");

        /**
         * 测试这个文件在其他的文件夹,也就是顶层包目录com之外
         *
         * 通过配置-cp或者-classpath读取外部的配置文件
         *
         */
        URL resource2 = CPTest.class.getResource("/cp2.txt");
        System.out.println(resource2.getFile());

        System.out.println("--");

        URL resource3 = CPTest.class.getClassLoader().getResource("cp2.txt");
        System.out.println(resource3.getFile());


    }
}
