# excel-use的使用说明

## 1. 引入jar包
引入apache的2个包
```mvn
        <dependency>
            <groupId>org.apache.poi</groupId>
            <artifactId>poi</artifactId>
            <version>4.0.1</version>
        </dependency>
        <dependency>
            <groupId>org.apache.poi</groupId>
            <artifactId>poi-ooxml</artifactId>
            <version>4.0.1</version>
        </dependency>           
```

## 2. 具体示例
**说明: WriteUrlTest要放在各自服务的模块下的test目录下, 否则无法执行.**
```java
public class WriteUrlTest {

    @Test
    public void testWriteUrl() {
        // controller的目录地址
        String controllerPath="D:\\workspace\\gitlab\\switch\\lgm-cardserver\\src\\main\\java\\com\\zcs\\cardmaker\\business\\controller\\";
        // 编译后的class文件地址
        String targetPath = "D:\\workspace\\gitlab\\switch\\lgm-cardserver\\target\\classes";
        // 记录url的表格地址
        String excelPath = "C:\\Users\\xql\\Desktop\\20200720接口url统一更改\\url统计表.xlsx";
        //proto文件的地址
        String protoPath ="D:\\workspace\\gitlab\\switch\\lgm-cardserver\\src\\main\\proto";
        // sheetName: 工作薄名称
        String sheetName = "lgm-cardserver";
        // 读取controller的老url, 并设置新url到表格中, 工作薄是追加到sheet表后面的
        ZcsUrlUtil.readAndWriteOldExcel(controllerPath, targetPath, excelPath, sheetName, "cardserver");
        // 读取controller的老url,并设置新url到表格中,会创建新的工作薄并覆盖之前的内容和sheet表
//        ZcsUrlUtil.readAndWriteNewExcel(controllerPath, targetPath, excelPath, sheetName, "cardserver");
        // 读取表格中的数据, 更新controller和proto文件中的url
        ZcsUrlUtil.readExcelAndModifyFile(excelPath, controllerPath, protoPath, sheetName);
    }
}
```

## 3. 自动生成新的url
* 老的url是从Controller类的tag中读取;
* 新的url的生成规则在WriteExcel的getNewUrl(String oldUrl)方法中, 可根据自己模块的url规则进行更改:
```java
public class WriteExcel {
    
        public static String getNewUrl(String oldUrl) {
            if (oldUrl.isEmpty()) {
                return "";
            }
            // 未更新的url以"i/s"开头, 已更新的url以v1开头
            if (oldUrl.startsWith("i/s")) {
                return oldUrl.replace("v1/", "").replace("i/s", "v1");
            } else {
                return oldUrl;
            }
        }
}
```

## 4. ZcsUrlUtil方法说明
```mvn
1. readAndWriteOldExcel: 读取controller文件夹下的方法注释和tag中的url, 程序自动生成新的url, 写入到指定的Excel表格中.
2. readAndWriteNewExcel: 功能同上, 区别是会在原工作簿的基础上新建指定名称的工作簿, 不会覆盖原表内容.
3. readExcelAndModifyFile: 根据工作簿名称读取工作簿的oldUrl和newUrl, 更新controller文件夹和proto文件夹下的url.
```

## 5. 报错说明
1. 错误1: 此错误说明Controller的方法中有重名方法. 将方法名改为不同的即可
```
java.lang.IllegalStateException: Duplicate key v1/recharge/quotaRechApply

	at java.util.stream.Collectors.lambda$throwingMerger$0(Collectors.java:133)
	at java.util.HashMap.merge(HashMap.java:1253)
	at java.util.stream.Collectors.lambda$toMap$58(Collectors.java:1320)
	at java.util.stream.ReduceOps$3ReducingSink.accept(ReduceOps.java:169)

```
2. 错误2: 此错误不影响程序运行和更改url, 可忽略. 
```
D:\workspace\gitlab\switch\lgm-cardserver\src\main\java\com\zcs\cardmaker\business\controller\SoftcardController.java:189: 错误: 找不到符号
    @ActorProvider(tag = "i/s/card/v1/ewalletFind", model = Ewallet.EwalletCardFindRequest.class)
     ^
  符号:   类 ActorProvider
  位置: 类 SoftcardController
D:\workspace\gitlab\switch\lgm-cardserver\src\main\java\com\zcs\cardmaker\business\controller\SoftcardController.java:213: 错误: 找不到符号
    @ActorProvider(tag = "v1/card/cbx/makeTtxCard", model = Softcard.TtxCardMakeRequest.class)
     ^
  符号:   类 ActorProvider
  位置: 类 SoftcardController
```

## 6. 收尾
url更新完毕后, 请删除导入的jar文件和单元测试文件.