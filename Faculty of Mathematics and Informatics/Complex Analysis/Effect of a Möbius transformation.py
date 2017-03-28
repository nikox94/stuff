#!/usr/bin/python

import matplotlib.pyplot as mpl

def argand(a):
    import matplotlib.pyplot as plt
    import numpy as np
    for x in range(len(a)):
        plt.plot([0,a[x].real],[0,a[x].imag],'ro-',label='python')
    limit=np.max(np.ceil(np.absolute(a))) # set limits for axis
    plt.xlim((-limit,limit))
    plt.ylim((-limit,limit))
    plt.ylabel('Imaginary')
    plt.xlabel('Real')
    plt.show()

def generate_data():
    result = []
    for i in xrange(100):
        for j in xrange(100):
            result.append(complex(i, j))
    return result


argand(generate_data())
