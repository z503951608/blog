线程池里面到底该设置多少个线程
IO密集型=2Ncpu（可以测试后自己控制大小，2Ncpu一般没问题）（常出现于线程中：数据库数据交互、文件上传下载、网络数据传输等等）

计算密集型=Ncpu（常出现于线程中：复杂算法）

java中：Ncpu=Runtime.getRuntime().availableProcessors()


数据库连接池到底应该设多大？
连接数 = ((核心数 * 2) + 有效磁盘数)  （通常不会高于2*CPU核心数）。
https://github.com/brettwooldridge/HikariCP/wiki/About-Pool-Sizing