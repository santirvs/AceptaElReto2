#include <bits/stdc++.h>
using namespace std;


// Máxima optimización hasta conseguir ser el #1 (03/07/2025) con 0.46s
// El siguiente envío más rápido es de 0.928s  (+101,7% !!!)

const int MAXN = 300005;
int BIT[MAXN];
int digits[MAXN];
int N;

// Suma de elementos desde 1 hasta i
inline int sum(int i) {
    int res = 0;
    while (i > 0) {
        res += BIT[i];
        i -= i & -i;
    }
    return res;
}

// Añade val a la posición i
inline void add(int i, int val) {
    while (i <= N) {
        BIT[i] += val;
        i += i & -i;
    }
}

// Encuentra el k-ésimo número disponible (0-indexado)
inline int findKth(int k) {
    int idx = 0;
    for (int d = 1 << 19; d > 0; d >>= 1) {
        if (idx + d <= N && BIT[idx + d] <= k) {
            k -= BIT[idx + d];
            idx += d;
        }
    }
    return idx + 1;
}


// Entrada rápida
inline void fastReadInt(int &x) {
    char c;
    x = 0;
    while ((c = getchar_unlocked()) < '0'); // ignora no-dígitos
    do {
        x = x * 10 + c - '0';
    } while ((c = getchar_unlocked()) >= '0');
}

// Salida rápida con espacio o salto
inline void fastWriteIntWithSpace(int x, bool last) {
    char buf[12];
    int i = 10;
    buf[11] = last ? '\n' : ' ';
    do {
        buf[i--] = (x % 10) + '0';
        x /= 10;
    } while (x);
    while (++i < 12) putchar_unlocked(buf[i]);
}

int main() {
    int T;
    fastReadInt(T);

    while (T--) {
        fastReadInt(N);
        for (int i = 0; i < N; ++i) {
            fastReadInt(digits[i]);
        }

        memset(BIT, 0, (N + 2) * sizeof(int));
        for (int i = 1; i <= N; ++i) {
            add(i, 1);
        }

        for (int i = 0; i < N; ++i) {
            int k = digits[i];
            int num = findKth(k);
            add(num, -1); // Marcar como usado
            fastWriteIntWithSpace(num, i == N - 1);
        }
    }

    return 0;
}