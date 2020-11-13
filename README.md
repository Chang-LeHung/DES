<<<<<<< HEAD
# DES
DES explanation in Java
=======
本项目使用Java语言从底层实现了DES加密算法，DES加密算法的具体流程和详情可以查看DES.pdf, DES_explanation.md 中公式显示不出来可以下载改文件再导出pdf，或直接查看DES.pdf即可。实现DES之后，在这个基础上完成了基于CBC工作模式的3DES加密算法，做成了一个软件mybox，提供了32和64位的版本，可以用来进行加解密。

查看帮助文档

```ptyhon
.\mymybox-64-bit.exe
或者
.\mymybox-64-bit.exe -help
```

加密示例

```
.\mybox-64-bit.exe -input .\README.md -key1 0123456789abcdef -key2 0123456789abcdef -key3 0123456789abcdef -iv 0123456789abcdef -mode encrypt -output result
mybox-64-bit.exe -input .\README.md -key1 0123456789abcdef -key2 0123456789abcdef -key3 0123456789abcdef -iv 0123456789abcdef -mode encrypt -output result
```

解密示例

```
.\mybox-64-bit.exe -input .\result.mybox -key1 0123456789abcdef -key2 0123456789abcdef -key3 0123456789abcdef -iv 0123456789abcdef -mode decrypt -output ans.md
.\mybox-64-bit.exe -input .\result.mybox -key1 0123456789abcdef -key2 0123456789abcdef -key3 0123456789abcdef -iv 0123456789abcdef -mode decrypt -output ans.md
```

