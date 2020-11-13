<<<<<<< HEAD
# DES
DES explanation in Java
=======
>>>>>>> c180892... initial commit
本项目使用Java语言从底层实现了DES加密算法，DES加密算法的具体流程和详情可以查看DES.pdf。实现DES之后，在这个基础上完成了基于CBC工作模式的3DES加密算法，做成了一个软件mybox，提供了32和64位的版本，可以用来进行加解密。

查看帮助文档

```ptyhon
<<<<<<< HEAD
.\mymybox-64-bit.exe
或者
.\mymybox-64-bit.exe -help
=======
mymybox-64-bit.exe
或者
mymybox-64-bit.exe -help
>>>>>>> c180892... initial commit
```

加密示例

```
<<<<<<< HEAD
.\mybox-64-bit.exe -input .\README.md -key1 0123456789abcdef -key2 0123456789abcdef -key3 0123456789abcdef -iv 0123456789abcdef -mode encrypt -output result
=======
mybox-64-bit.exe -input .\README.md -key1 0123456789abcdef -key2 0123456789abcdef -key3 0123456789abcdef -iv 0123456789abcdef -mode encrypt -output result
>>>>>>> c180892... initial commit
```

解密示例

```
<<<<<<< HEAD
.\mybox-64-bit.exe -input .\result.mybox -key1 0123456789abcdef -key2 0123456789abcdef -key3 0123456789abcdef -iv 0123456789abcdef -mode decrypt -output ans.md
=======
mybox-64-bit.exe -input .\result.mybox -key1 0123456789abcdef -key2 0123456789abcdef -key3 0123456789abcdef -iv 0123456789abcdef -mode decrypt -output ans.md
>>>>>>> c180892... initial commit
```

