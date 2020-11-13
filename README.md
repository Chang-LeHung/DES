# DES 加密详解
DES explanation in Java
改项目是使用java语言实现DES，3DES加密算法，使用CBC模式实现加密文件，并做成了软件mybox，并提供了源码实现，如果你对它感兴趣也可以那它进行加密测试，或者仔细查看源码文件
如果你对DES, 3DES, CBC模式不熟悉请先阅读<https://github.com/Chang-LeHung/DES/blob/main/DES_explanation.md>，在这里提供了对DES, 3DES, CBC模式详细解释，大家可以参考
`mybox 使用方法如下`
- 查看帮助文档
  ```
  .\mymybox-64-bit.exe
  或者
  .\mymybox-64-bit.exe -help
  ```
- 加密
  ```
  .\mybox-64-bit.exe -input .\README.md -key1 0123456789abcdef -key2 0123456789abcdef -key3 0123456789abcdef -iv 0123456789abcdef -mode encrypt -output result
  ```
- 解密
  ```
  .\mybox-64-bit.exe -input .\result.mybox -key1 0123456789abcdef -key2 0123456789abcdef -key3 0123456789abcdef -iv 0123456789abcdef -mode decrypt -output ans.md
  ```
