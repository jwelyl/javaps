#include <iostream>
#include <vector>
using namespace std;

void printVector(vector<int> v) {
    for(int i = 0; i < v.size(); i++)
        cout << v[i] << " ";
    cout << '\n';
}

int main(void) {
    vector<int> v1, v2;

    for(int i = 0; i < 10; i++)
        v1.push_back(i);

    v2 = v1;
    printVector(v1);
    printVector(v2);

    for(int i = 0; i < 10; i += 2)
        v2[i] *= 2;

    printVector(v1);
    printVector(v2);

    return 0;
}