package io.yeliang;

public class Main {
    public static void main(String[] args) {
        System.out.println("1、F(n) = F(n-1) + F(n-2), where F1 =1, F2 = 2\n" +
                " F1 =1\n" +
                " F2 =1\n" +
                " F3 =2\n" +
                " F4 =3\n" +
                " F5 =5\n" +
                " F6 =8\n" +
                " F7 =13\n" +
                " F8 =21\n" +
                " F9 =34\n" +
                " F10=55\n" +
                " F11=89\n" +
                " F12=144\n" +
                " 可见index=12的F12是第一个包含3个数字的Fn，那么第一个包含1000个数字的Fn的index是多少？");
        Long result = FeiBoTest.feibo(1000);
        System.out.println("答：执行FeiBoText.java");
        System.out.println("第一个包含1000个数字的Fn的index是"+result);
        System.out.println("-------------------------------------------------");
        System.out.println("2、将1，2，3，4， .....，99，100的顺序数列，排序成 100，1，99，2，98，3，......，51，50这样的非等差数列。\n" +
                "    要求:空间复杂度o(1)，时间复杂度o(n)。");
        System.out.println("答：执行LinkedListSortTest.java");
        LinkedListSortTest.exec();
        System.out.println("-------------------------------------------------");
        System.out.println("3、素数是自然数中大于1，且只能被正整数中的1和自身整除的自然数。\n" +
                " 比如10以下的素数和为 ：2 + 3 + 5 + 7 = 17\n" +
                " 请计算2,000,000以下的所有素数和");
        System.out.println("答：执行PrimeText.java");
        System.out.println("2,000,000以下的所有素数和142913828922  详细代码运行情况请执行PrimeText.java");
        System.out.println("-------------------------------------------------");
        System.out.println("4、排序算法的平均时间复杂度的下限是多少？请尝试证明您的观点");
        System.out.println("答：");
        System.out.println("一个数组在未排列之前排列方式有n!种，\n" +
                "        当经过一次比较，确定其中两个元素的位置后，剩余的排列方式还有n!/2种，\n" +
                "        依次类推，经过m次比较，剩余的排列方式为n!/(2^m)种。\n" +
                "        当n!/(2^m)<1时，即满足了排序完成，目前即要证明m为多少时能满足这个条件。\n" +
                "        对n!/(2^m)<1 转换得：\n" +
                "            k>log(n!);\n" +
                "            根据斯特林公式知道lg(n!)与nlogn是等价无穷大的。\n" +
                "            所以得出k>nlogn;\n" +
                "            所以基于排序的比较算法的的最低时间复杂度是O(nlogn)");
        System.out.println("-------------------------------------------------");
        System.out.println("5、目前有线索池系统的需求，需要记录线索来源和线索信息，线索信息的内容除了客户名和多种（至少）一种联系方式外，\n" +
                " 并不明确信息的数量和内容。\n" +
                " 另有下单系统，系统中有客户名和一种联系方式，包含供应商信息等确定的60个不同类型字段。\n" +
                " 线索池的客户名和下单的客户名不一定相同。\n" +
                " 此外还有代理商网咯系统，会得到线索池分发的信息。\n" +
                " 请尝试设计线索池系统，其中下单系统和代理商网络是旧有系统，有一定改造难度。\n" +
                " 总体目标是实现线索有效性的评价，尽可能提高线索的利用率（供应商对线索的有效利用）。");
        System.out.println("答：");
        System.out.println("单从这上面的需求，我没法从业务层面去进行设计。所以我的回答就从系统架构角度去回答。\n" +
                "    线索池做为一个独立的业务模块，在系统架构层面来说也是一个独立的微服务模块。\n" +
                "    此模块需要解决三个问题：数据来源、数据存储、数据交互。\n" +
                "    1、数据来源。做为一个独立模块，要充分与其它系统进行解耦且不影响其它系统的正常运行，\n" +
                "        使用异步消息中件间MQ做为数据收集工具。其它系统只需做简单的切面(使用自定义注解)就可以实现，不侵入原系统。\n" +
                "    2、存储问题。因为线索信息存在多种联系方式及不明确的信息数量和内容，\n" +
                "        所以不能用明确的关系型数据库字段来标识。这里有几种方式来实现线索池的数据存储：\n" +
                "            1> 如果数据量未超过1000W以上，且线索池的检索以客户为主的情况下，可以使用关系性数据存储，\n" +
                "                主字段“客户信息”是varchar类型，\"不明确字段\"用text格式的json字符串。\n" +
                "                如果对响应要求高，可以把数据加载到redis中。\n" +
                "            2> 如果数据量很大，达到TB级里，使用大数据平台hbase数据库。\n" +
                "     2、交互问题。第一、主动推送线索池信息，我们还是利用MQ来实现消息广播。\n" +
                "                  第二、提供其它系统访问的接口，进行接口调用，为保证检索的速度，我们可以使用redis缓存数据，\n" +
                "                           或根据业务有效的设计hbase的key和列族，使用查询效率达到最优。\n" +
                "                  第三、通信方式的选择，如果其它系统的整体架构是SOA面向服务的体系统架构或微服务(springcloud)\n" +
                "                    的体系架构，我们依旧使用老的体系构架通信即可。但如果其它应用是独立的单体结构，\n" +
                "                    我们对线索池系统的服务体系可以选择SOA或springcloud，我个人倾向于使用dubbo，\n" +
                "                    一在原系统改动最少的情况下可对源所有系统提供服务(原系统只需要加载dubbo服务、线索池服国接口及配置dubbo服务注册址)。\n" +
                "                      对老系统的侵入性最少。\n" +
                "                    二dubbo使用netty做为底层通信框架，使用序列化的二进制传输，传输的效率高。");
        System.out.println("-------------------------------------------------");
        System.out.println("6、请简述jvm模型，包括并发模型，请至少说明两项不涉及garbage的内容");
        System.out.println("答");
        System.out.println("1、先说说volatile。\n" +
                "        volatile修改的共享变量有两个特性：1、保证不同线程对该变量操作在内存中的可见性。2、禁止指令重排。\n" +
                "        内存可见性：在JMM中，每个java线程都存在着自己的工作内存，线程对变量的读写操作都是在工作内存中完成，不会操作主内存。\n" +
                "                    就是把volatile修饰的变量强制刷新到主内存中。\n" +
                "        指令重排：JMM本身是允许指令重排的，但是规定重排不影响程序执行结果。但在并发环境中，JMM的重排序对导致线程执行出问题。\n" +
                "                  使用volatile确保程序的顺序性。\n" +
                "        2、JMM处理并发过程中如何保存原子性、可见性和有序性的。\n" +
                "           原子性：就是一系列操作要么全执行完，要么不执行。jmm中，基本数据类型的读取和赋值是原子性的。\n" +
                "           可见性：java使用volatile来实现对变量的可见性，就是把变量刷新到主内存中，以便其它线程读取。\n" +
                "           有序性：jmm在单线程中，是会进行指令重排序的，其原则是先行发生原则(happens-before)。\n" +
                "                    以达到最优化的运行效率，但在多线程中我们要使用volatile或其它锁的方式保证程序的有序性。\n" +
                "        3、再说说java线程这块。在多核CPU的服务器中，线程会映射到不同的CPU中进行执行。那我们怎么保证线程安全呢？\n" +
                "           有三种方式\n" +
                "           1、互斥同步：使用synchronized关键字修饰代码块、方法或变量。达到互斥的目地。另外使用JDK中ReentrantLock类来实现同步。\n" +
                "           2、非阴塞同步：使用CAS原则，先比较再替换，先比较“内存中的值”与 拿出来的\"旧值\"比较，看是否相等，如果相同，则把“旧值”与”新值“交换。\n" +
                "                          不相同，就返回false。\n" +
                "           3、使用ThreadLocal：使用ThreadLocal创建每个线程自己的变量空间，以空间换取时间的方式，解决多线程中相同变量的访问冲突问题。");
    }
}
