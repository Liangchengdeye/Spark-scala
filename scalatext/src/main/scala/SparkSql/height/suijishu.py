#!/usr/bin/env python  
# encoding: utf-8  
""" 
@version: v1.0 
@author: W_H_J
@license: Apache Licence  
@contact: 415900617@qq.com 
@site:  
@software: PyCharm 
@file: suijishu.py 
@time: 2017/12/26 13:59 
@describe:生成身高数据
F女 M男

"""
import random

def func():

    list1 =['F','M']
    f = open("light.txt",'a')
    for i in range(1, 100000):
        light = random.randint(150, 220)
        sex = random.sample(list1, 1)[0]
        print i, sex, light
        f.write(str(i)+" "+sex+" "+str(light)+'\n')
    f.close()


class Main():
    def __init__(self):
        func()


if __name__ == "__main__":
    Main() 