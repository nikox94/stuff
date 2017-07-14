#!/usr/bin/env python3

h = 0.025
x_start = 0
x_end = 0.5
y0 = 1

n = int(x_end/h)
y = []    
x = []
for i in range(n+1):
    x.append(0)
    y.append(0)
y[0] = y0
x[0] = 0
for i in range(n):
    y[i+1] = y[i] + (h * (y[i] + x[i]))/(y[i]-x[i])
    x[i+1] = x[i] + h
    
print(x[n])
print(y[n])
