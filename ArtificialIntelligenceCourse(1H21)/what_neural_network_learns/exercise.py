import numpy as np
import matplotlib.pyplot as plt
from sklearn.neural_network import MLPClassifier

X = np.genfromtxt("happysadpixels.csv", delimiter=",", dtype=int)
y = np.genfromtxt("happysadlabels.csv", delimiter=",", dtype=int)

X.shape
y.shape
plt.imshow(X[0].reshape(6, 6))

nn = MLPClassifier(hidden_layer_sizes=(3,), max_iter=2000)
nn = nn.fit(X, y)
len(nn.coefs_)
len(nn.coefs_[0])
len(nn.coefs_[0][0])

print("Iterations", nn.n_iter_, "Final loss", nn.loss_)
print(len(nn.coefs_))
print(len(nn.coefs_[0]))
print(len(nn.coefs_[0][0]))

print(nn.coefs_)

for j in range(3):
    my_weights = []
    for i in range(len(nn.coefs_[0])):
        my_weights.append(nn.coefs_[0][i][j])
    plt.imshow(np.array(my_weights).reshape(6, 6))
    plt.show()
    print(my_weights)

for j in range(2):
    out_weights = []
    for i in range(len(nn.coefs_[1])):
        out_weights.append(nn.coefs_[1][i][j])
    plt.imshow(np.array(out_weights).reshape(3, 1))
    plt.show()
    print(out_weights)