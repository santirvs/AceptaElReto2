#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

struct Point {
    long long x, y;
    Point(long long x = 0, long long y = 0) : x(x), y(y) {}
    bool operator<(const Point& p) const {
        return x != p.x ? x < p.x : y < p.y;
    }
};

long long cross(const Point& O, const Point& A, const Point& B) {
    return (A.x - O.x) * (B.y - O.y) - (A.y - O.y) * (B.x - O.x);
}

vector<Point> convexHull(vector<Point>& points) {
    sort(points.begin(), points.end());
    int n = (int)points.size();
    vector<Point> hull;

    // Lower hull
    for (int i = 0; i < n; i++) {
        while ((int)hull.size() >= 2 &&
            cross(hull[hull.size() - 2], hull[hull.size() - 1], points[i]) <= 0) {
            hull.pop_back();
        }
        hull.push_back(points[i]);
    }

    // Upper hull
    int t = (int)hull.size() + 1;
    for (int i = n - 2; i >= 0; i--) {
        while ((int)hull.size() >= t &&
            cross(hull[hull.size() - 2], hull[hull.size() - 1], points[i]) <= 0) {
            hull.pop_back();
        }
        hull.push_back(points[i]);
    }

    hull.pop_back(); // remove duplicate
    return hull;
}

double polygonArea(const vector<Point>& poly) {
    double area = 0.0;
    int n = (int)poly.size();
    for (int i = 0; i < n; i++) {
        const Point& p1 = poly[i];
        const Point& p2 = poly[(i + 1) % n];
        area += (double)p1.x * p2.y - (double)p2.x * p1.y;
    }
    return fabs(area) / 2.0;
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    while (true) {
        int n;
        cin >> n;
        if (n == 0) break;

        vector<Point> points(n);
        for (int i = 0; i < n; i++) {
            long long x, y;
            cin >> x >> y;
            points[i] = Point(x, y);
        }

        vector<Point> hull = convexHull(points);
        long long area2x = (long long)(polygonArea(hull) * 2.0 + 0.5); // redondeo seguro

        if (area2x % 2 == 0) {
            cout << area2x / 2 << "\n";
        }
        else {
            cout << area2x / 2 << ".5\n";
        }
    }

    return 0;
}