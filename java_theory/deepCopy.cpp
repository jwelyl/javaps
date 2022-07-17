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

    v2 = v1;    //  v2에 v1 복사(deep copy)
    cout << "v2에 v1 복사 후 v1, v2 출력\n";
    printVector(v1);
    printVector(v2);

    for(int i = 0; i < 10; i += 2)  //  v2 수정
        v2[i] *= 2;

    cout << "v2 수정 후 v1, v2 출력\n";
    printVector(v1);
    printVector(v2);

    return 0;
}