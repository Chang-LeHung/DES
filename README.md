# DES 加密详解
DES explanation in Java
本项目是使用java语言实现DES，3DES加密算法，使用CBC模式实现加密文件，并做成了软件mybox，并提供了源码实现，如果你对它感兴趣也可以那它进行加密测试，或者仔细查看源码文件，如果想进行源码实现，请重点阅读[DES.java](https://github.com/Chang-LeHung/DES/blob/main/des/DES.java)文件。
如果你对DES, 3DES, CBC模式不熟悉请先阅读下面的文档或者它的[pdf形式](https://github.com/Chang-LeHung/DES/blob/main/DES.pdf)，在这里提供了对DES, 3DES, CBC模式详细解释，供大家参考。

`mybox 使用方法如下`
- 查看帮助文档
  ```
  .\mymybox-64-bit.exe
  或者
  .\mymybox-64-bit.exe -help
  ```
  结果如下，加密时需要提供3个密钥和初始化向量，都是十六位的十进制数，即提供了加密需要的64位01二进制数
  ```
  ############################### MyBox ##################################
  ### Description:                                                    ###
  ### Software MyBox was Written by Mr. HuChang in Java on 10/27/2020 ###
   _____________
  < @牧牛的铃铛 >
   -------------
          \   ^__^
           \  (oo)\_______
              (__)\       )\/\
                  ||---- w|
                  ||     ||
  Usage mybox:
   mybox [FILED] ... [STRING] ...
     -key1        --first secret key
                      default=0123456789abcdef
     -output      --output file path
                      default=mybox
     -input       --input file path
                      default=null
     -iv  --initialization vector of CBC encryption mode
                      default=0123456789abcdef
     -key2        --second secret key
                      default=0123456789abcdef
     -key3        --third secret key
                      default=0123456789abcdef
     -mode        --encrypt or decrypt
                      default=en
  ```
- 加密示例
  ```
  .\mybox-64-bit.exe -input .\README.md -key1 0123456789abcdef -key2 0123456789abcdef -key3 0123456789abcdef -iv 0123456789abcdef -mode encrypt -output result
  ```
- 解密示例
  ```
  .\mybox-64-bit.exe -input .\result.mybox -key1 0123456789abcdef -key2 0123456789abcdef -key3 0123456789abcdef -iv 0123456789abcdef -mode decrypt -output ans.md
  ```
# DES，CBC模式，3DES详解如下
如果查看不了下面的图片文档，请参考[Image show](https://blog.csdn.net/liu19721018/article/details/106467711/)进行解决
![00](/images/DES_00.png)
![01](/images/DES_01.png)
![02](/images/DES_02.png)
![03](/images/DES_03.png)
![04](/images/DES_04.png)
![05](/images/DES_05.png)
![06](/images/DES_06.png)
![07](/images/DES_07.png)
![08](/images/DES_08.png)
![09](/images/DES_09.png)
![10](/images/DES_10.png)

