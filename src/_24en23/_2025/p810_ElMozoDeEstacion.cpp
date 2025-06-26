#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

vector<int> computeLISFromRight(const vector<int>& arr) {
    int n = arr.size();
    vector<int> lis(n);
    vector<int> tails;

    for (int i = n - 1; i >= 0; --i) {
        auto it = lower_bound(tails.begin(), tails.end(), arr[i]);
        if (it == tails.end()) {
            tails.push_back(arr[i]);
            it = tails.end() - 1;
        }
        else {
            *it = arr[i];
        }
        lis[i] = it - tails.begin() + 1;
    }

    return lis;
}

vector<int> computeLDSFromRight(const vector<int>& arr) {
    int n = arr.size();
    vector<int> lds(n);
    vector<int> tails;

    for (int i = n - 1; i >= 0; --i) {
        int val = -arr[i];
        auto it = lower_bound(tails.begin(), tails.end(), val);
        if (it == tails.end()) {
            tails.push_back(val);
            it = tails.end() - 1;
        }
        else {
            *it = val;
        }
        lds[i] = it - tails.begin() + 1;
    }

    return lds;
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int n;
    while (cin >> n && n != 0) {
        vector<int> arr(n);
        for (int i = 0; i < n; ++i)
            cin >> arr[i];

        vector<int> lis = computeLISFromRight(arr);
        vector<int> lds = computeLDSFromRight(arr);

        int max_total = 0;
        for (int i = 0; i < n; ++i) {
            int total = lis[i] + lds[i] - 1;
            if (total > max_total)
                max_total = total;
        }

        cout << max_total << '\n';
    }

    return 0;
}
