#include <iostream>
#include <vector>
#include <queue>
#include <algorithm>
#include <windows.h>
#include <GL/glut.h>
#include <cmath>
#include <assert.h>

using namespace std;

const double MAX = 100.0;
const double EPS = 1e-7;
const int N = 100;
const int SEG = 1;
const int PNT = 2;
const int TRD = 3;


struct point {
    double x, y;
    point() {}
    point(int a, int b) {
        x = a;
        y = b;
    }
    const bool operator==(const point& p) const {
        return (x == p.x) && (y == p.y);
    }
};

struct segment {
    point st;
    point en;
};

struct trapezoid {
    segment top;
    segment bottom;
    point left;
    point right;
    vector<int> next;
    vector<int> prev;
    trapezoid() {}
    trapezoid(segment top, segment bottom, point left, point right) {
        this->top = top;
        this->bottom = bottom;
        this->left = left;
        this->right = right;
    }
};


struct tree {
    int type;
    int pos;
    tree* left;
    tree* right;
    tree() {
        type = 0;
        left = NULL;
        right = NULL;
    }
    tree(int type, int pos, tree* left, tree* right) {
        this->type = type;
        this->pos = pos;
        this->left = left;
        this->right = right;
    }
};


double vect(point a, point b) {
    return a.x * b.y - a.y * b.x;
}

double scalar(point a, point b) {
    return a.x * b.x + a.y * b.y;
}

double dist(point a, point b) {
    return sqrt((b.x - a.x) * (b.x - a.x) + (b.y - a.y) * (b.y - a.y));
}

// where c? 1 = on the left side ab, -1 = on the right side, 0 = on one line
int left_turn(const point& a, const point& b, const point& c) {
    double l = (b.x - a.x) * (c.y - a.y);
    double r = (c.x - a.x) * (b.y - a.y);
    double s = l - r;
    if (s < 0)
        return -1;
    if (s > 0)
        return 1;
    return 0;
}

// d in trangle abc or not
bool inTriangle(const point& a, const point& b, const point& c, const point& d) {
    int turn1 = left_turn(a, b, d);
    int turn2 = left_turn(c, a, d);
    int turn3 = left_turn(b, c, d);
    return (turn1 <= 0 && turn2 <= 0 && turn3 <= 0) || (turn1 >= 0 && turn2 >= 0 && turn3 >= 0);
}

//dist between point a and line bc
double pointToLine(point a, point b, point c) {
    double v = abs(vect(point(a.x - b.x, a.y - b.y), point(c.x - b.x, c.y - b.y)));
    return v / dist(b, c);
}

//dist between point a and seqment bc
double pointToSegment(point a, point b, point c) {
    if (scalar(point(a.x - b.x, a.y - b.y), point(c.x - b.x, c.y - b.y)) < 0) {
        return dist(a, b);
    }
    if (scalar(point(a.x - c.x, a.y - c.y), point(b.x - c.x, b.y - c.y)) < 0) {
        return dist(a, c);
    }
    return pointToLine(a, b, c);
}

bool intersect(point a, point b, point c, point d) {
    return (max(min(a.x, b.x), min(c.x, d.x)) <= min(max(a.x, b.x), max(c.x, d.x))) &&
           (max(min(a.y, b.y), min(c.y, d.y)) <= min(max(a.y, b.y), max(c.y, d.y)));
}

bool intersection(point a, point b, point c, point d) {
    return intersect(a, b, c, d) && (left_turn(a, b, c) * left_turn(a, b, d) <= 0) && (left_turn(c, d, a) * left_turn(c, d, b) <= 0);
}

tree* my_map;
vector<trapezoid> trapezoids;
vector<segment> segments;
vector<point> points;


void init() {
    point top_left = point(-MAX, MAX);
    point top_right = point(MAX, MAX);
    point bot_left = point(-MAX, -MAX);
    point bot_right = point(MAX, -MAX);
    segments.push_back({top_left, top_right});
    segments.push_back({bot_left, bot_right});
    trapezoid area = trapezoid(segments[0], segments[1], top_left, bot_right);
    trapezoids.push_back(area);
    my_map = new tree(TRD, 0, NULL, NULL);
}


// -1 = out, 0 = border, 1 = in
int inTrd(point p, trapezoid trd) {
    double x_min = trd.left.x;
    double x_max = trd.right.x;
    int turn11 = left_turn(trd.top.st, trd.top.en, p);
    int turn12 = left_turn(trd.bottom.st, trd.bottom.en, p);

    //outside
    if (turn11 == turn12 && turn11 != 0) return -1;

    int turn21 = left_turn(point(x_min, -MAX), point(x_min, MAX), p);
    int turn22 = left_turn(point(x_max, -MAX), point(x_max, MAX), p);
    //outside
    if (turn21 == turn22 && turn21 != 0) return -1;

    //on border
    if (turn11 == 0 || turn12 == 0 || turn21 == 0 || turn22 == 0) return 0;

    //inside
    return 1;
}


void addIn(segment s, tree* t) {
    assert(t->type == 3);
    int cur_pos = t->pos;
    trapezoid cur = trapezoids[cur_pos];
    int s_pos = segments.size();
    segments.push_back(s);
    int p_pos = points.size();
    points.push_back(s.st);
    int q_pos = points.size();
    points.push_back(s.en);
    // next, up, down, end, top, botton, right, left
    trapezoid a = trapezoid(cur.top, cur.bottom, cur.left, s.st);
    trapezoid b = trapezoid(cur.top, cur.bottom, s.en, cur.right);
    trapezoid c = trapezoid(cur.top, s, s.st, s.en);
    trapezoid d = trapezoid(s, cur.bottom, s.st, s.en);

    // have A B or not????
    int a_pos = -1;
    if (a.left.x != a.right.x) {
        a_pos = trapezoids.size();
        trapezoids.push_back(a);
    }
    int b_pos = -1;
    if (b.left.x != b.right.x) {
        b_pos = trapezoids.size();
        trapezoids.push_back(b);
    }
    cerr << "A B " << a_pos << " " << b_pos << endl;
    int c_pos = trapezoids.size();
    trapezoids.push_back(c);
    int d_pos = trapezoids.size();
    trapezoids.push_back(d);

    for (int num: cur.prev) {
        for (int i = 0; i < trapezoids[num].next.size(); i++) {
            if (trapezoids[num].next[i] == cur_pos) {
                if (a_pos != -1) {
                    trapezoids[num].next[i] = a_pos;
                    trapezoids[a_pos].prev.push_back(num);
                } else {
                    if (trapezoids[num].top.en.y > trapezoids[c_pos].bottom.st.y) {
                        trapezoids[num].next[i] = c_pos;
                        trapezoids[c_pos].prev.push_back(num);
                    }
                    if (trapezoids[num].bottom.en.y < trapezoids[d_pos].top.st.y) {
                        if (trapezoids[num].next[i] != c_pos) {
                            trapezoids[num].next[i] = d_pos;
                        } else {
                            trapezoids[num].next.push_back(d_pos);
                        }
                        trapezoids[d_pos].prev.push_back(num);
                    }
                }
                break;
            }
        }
    }
    //dont sure
    for (int num: cur.next) {
        for (int i = 0; i < trapezoids[num].prev.size(); i++) {
            if (trapezoids[num].prev[i] == cur_pos) {
                if (b_pos != -1) {
                    trapezoids[num].prev[i] = b_pos;
                    trapezoids[b_pos].next.push_back(num);
                } else {
                    if (trapezoids[num].top.st.y >= trapezoids[c_pos].bottom.en.y) {
                        trapezoids[num].prev[i] = c_pos;
                        trapezoids[c_pos].next.push_back(num);
                    }
                    if (trapezoids[num].bottom.st.y <= trapezoids[d_pos].top.en.y) {
                        if (trapezoids[num].prev[i] != c_pos) {
                            trapezoids[num].prev[i] = d_pos;
                        } else {
                            trapezoids[num].prev.push_back(d_pos);
                        }
                        trapezoids[d_pos].next.push_back(num);
                    }
                }
                break;
            }
        }
    }
    if (a_pos != -1) {
        trapezoids[a_pos].next.push_back(c_pos);
        trapezoids[a_pos].next.push_back(d_pos);
        trapezoids[c_pos].prev.push_back(a_pos);
        trapezoids[d_pos].prev.push_back(a_pos);
    }
    if (b_pos != -1) {
        trapezoids[c_pos].next.push_back(b_pos);
        trapezoids[d_pos].next.push_back(b_pos);
        trapezoids[b_pos].prev.push_back(c_pos);
        trapezoids[b_pos].prev.push_back(d_pos);
    }

    tree* tt =  new tree(SEG, s_pos, new tree(TRD, c_pos, NULL, NULL), new tree(TRD, d_pos, NULL, NULL));
    if (a_pos != -1 && b_pos != -1) {
        t->type = PNT;
        t->pos = p_pos;
        t->left = new tree(TRD, a_pos, NULL, NULL);
        t->right = new tree(PNT, q_pos, tt, new tree(TRD, b_pos, NULL, NULL));
    } else if (a_pos != -1 && b_pos == -1) {
        t->type = PNT;
        t->pos = p_pos;
        t->left = new tree(TRD, a_pos, NULL, NULL);
        t->right = tt;
    } else if (a_pos == -1 && b_pos != -1) {
        t->type = PNT;
        t->pos = q_pos;
        t->left = tt;
        t->right = new tree(TRD, b_pos, NULL, NULL);
    } else {
        t->pos = tt->pos;
        t->type = tt->type;
        t->left = tt->left;
        t->right = tt->right;
    }
}

pair<int, int> modifyMap(tree* t, const vector<int>& v, segment s, pair<int, int> prev) {
    if (t->type != TRD) {
        pair<int, int> prev2 = modifyMap(t->left, v, s, prev);
        return modifyMap(t->right, v, s, prev2);
    }
    int pos = -1;
    for (int i = 0; i < v.size(); i++) {
        if (v[i] == t->pos) {
            pos = i;
            break;
        }
    }
    if (pos == -1) return prev;
    trapezoid cur = trapezoids[t->pos];
    trapezoid tt = trapezoid(cur.top, s, cur.left, cur.right);
    trapezoid bb = trapezoid(s, cur.bottom, cur.left, cur.right);
    if (pos == 0) {
        int topp = trapezoids.size();
        trapezoids.push_back(tt);
        int bott = trapezoids.size();
        trapezoids.push_back(bb);
        if (s.st.x > cur.left.x) {
            trapezoid a = trapezoid(cur.top, cur.bottom, cur.left, s.st);
            int num = trapezoids.size();
            trapezoids.push_back(a);
            t->type = PNT;
            t->pos = points.size() - 2; //position of s.st
            t->left = new tree(TRD, num, NULL, NULL);
            t->right = new tree(SEG, segments.size() - 1, new tree(TRD, topp, NULL, NULL), new tree(TRD, bott, NULL, NULL));
        } else {
            t->type = SEG;
            t->pos = segments.size() - 1; //position of s
            t->left = new tree(TRD, topp, NULL, NULL);
            t->right = new tree(TRD, bott, NULL, NULL);
        }
        return make_pair(topp, bott);
    }

    int tpos = -1;
    int bpos = -1;
    if (cur.top.st == cur.left) {
        tpos = trapezoids.size();
        trapezoids.push_back(tt);
    } else {
        tpos = prev.first;
        trapezoids[tpos].right = cur.right;
    }
    if (cur.bottom.st == cur.left) {
        bpos = trapezoids.size();
        trapezoids.push_back(bb);
    } else {
        bpos = prev.second;
        trapezoids[bpos].right = cur.right;
    }

    if ((pos == v.size() - 1) && (s.en.x < cur.right.x)) {
        trapezoid a = trapezoid(cur.top, cur.bottom, s.en, cur.right);
        int num = trapezoids.size();
        trapezoids.push_back(a);
        t->type = PNT;
        t->pos = points.size() - 2; //position of s.st
        t->left = new tree(SEG, segments.size() - 1, new tree(TRD, tpos, NULL, NULL), new tree(TRD, bpos, NULL, NULL));
        t->right = new tree(TRD, num, NULL, NULL);
        return make_pair(tpos, bpos);
    }
    t->type = SEG;
    t->pos = segments.size() - 1; //position of s
    t->left = new tree(TRD, tpos, NULL, NULL);
    t->right = new tree(TRD, bpos, NULL, NULL);
    return make_pair(tpos, bpos);
}

void addOut(segment s, tree* t) {
    assert(t->type == 3);
    int cur_pos = t->pos;
    trapezoid cur = trapezoids[cur_pos];
    int s_pos = segments.size();
    segments.push_back(s);
    int p_pos = points.size();
    points.push_back(s.st);
    int q_pos = points.size();
    points.push_back(s.en);

    vector<int> group;
    group.push_back(cur_pos);
    int pos = cur_pos;
    while (inTrd(s.en, trapezoids[pos]) < 0) {
        for (int num: trapezoids[pos].next) {
            if (left_turn(s.st, s.en, trapezoids[num].bottom.st) < 0 ||
                (left_turn(s.st, s.en, trapezoids[num].bottom.st) == 0 && left_turn(s.st, s.en, trapezoids[num].right) <= 0)) {
                group.push_back(num);
                pos = num;
                break;
            }
        }
    }
    modifyMap(my_map, group, s, make_pair(-1, -1));

}


tree* find(point p, tree* t) {
    if (t->type == TRD) {
        return t;
    }
    if (t->type == PNT) {
        point cur = points[t->pos];
        if (p.x >= cur.x) {
            return find(p, t->right);
        }
        return find(p, t->left);
    } else if (t->type == SEG) {
        segment cur = segments[t->pos];
        if (left_turn(cur.st, cur.en, p) > 0) {
            return find(p, t->left);
        }
        return find(p, t->right);
    }
    cerr << "MDA" << endl;
}

//type SEG = 1 = segment, PNT = 2 = point, TRD = 3 = trapezoid
void add(segment a, tree* t) {
    tree* tt = find(a.st, t);
    // if cur-type != 3 ???
    assert(tt->type == 3);
    trapezoid cur = trapezoids[tt->pos];
    cerr << "(" << cur.left.x << ", " << cur.left.y << ") (" << cur.right.x << ", " << cur.right.y << ")" << endl;
    int where_st = inTrd(a.st, cur);
    int where_en = inTrd(a.en, cur);
    if (where_st + where_en > 0 || (where_st == 0 && where_en == 0)) {
        addIn(a, tt);
    } else {
        addOut(a, tt);
    }
}


void printTrd(tree* t) {
    if (t->left != NULL) printTrd(t->left);
    if (t->type == TRD) {
        trapezoid tt = trapezoids[t->pos];
        cerr << "TT " << t->pos << " " << tt.left.x << " " << tt.right.x << endl;
        glLineWidth(1);
        glBegin(GL_LINES);
        glColor3d(1, 0, 0);
        glVertex2f(tt.left.x / MAX, tt.bottom.st.y / MAX);
        glVertex2f(tt.left.x / MAX, tt.top.st.y / MAX);
        glVertex2f(tt.right.x / MAX, tt.bottom.en.y / MAX);
        glVertex2f(tt.right.x / MAX, tt.top.en.y / MAX);
        glEnd();

        double x = (tt.left.x + tt.right.x) / 2;
        double y = (min(tt.top.st.y, tt.top.en.y) + max(tt.bottom.st.y, tt.bottom.en.y)) / 2;
        glPointSize(10);
        glBegin(GL_POINTS);
        glColor3d(0, 0, 1);
        glVertex2f(x / MAX, y / MAX);
        glEnd();

        return;
    }
    if (t->right != NULL) printTrd(t->right);
}

vector<int> path;
vector<point> vertex;
vector<int> g[4 * N];
point start, finish;

void printPoint(point p) {
    glPointSize(30);
    glBegin(GL_POINTS);
    glColor3d(1, 0, 0);
    glVertex2f(p.x / MAX, p.y / MAX);
    glEnd();
}


void printPath(vector<int> pp) {
    glEnable(GL_LINE_STIPPLE);
    glLineStipple(2, 58360);
    glLineWidth(2);
    glBegin(GL_LINE_STRIP);
    glColor3d(0, 1, 0);
    for (int num: pp) {
        point cur = vertex[num];
        cerr << "path " << cur.x << " " << cur.y << endl;
        glVertex2f(cur.x / MAX, cur.y / MAX);
    }
    glEnd();
    glDisable(GL_LINE_STIPPLE);
}

void display() {
    glClear(GL_COLOR_BUFFER_BIT);
    glEnable(GL_POINT_SMOOTH);
    glEnable(GL_LINE_SMOOTH);
    glLineWidth(5);
    for (segment s: segments) {
        cerr << s.st.x << " " << s.st.y << " " << s.en.x << " " << s.en.y << endl;
        glBegin(GL_LINES);
        glColor3d(0, 0, 0);
        glVertex2f(s.st.x / MAX, s.st.y / MAX);
        glVertex2f(s.en.x / MAX, s.en.y / MAX);
        glEnd();
    }
    printTrd(my_map);
    printPoint(start);
    printPoint(finish);
    printPath(path);
    glutSwapBuffers();
    glFlush();
    glDisable(GL_LINE_SMOOTH);
    glDisable(GL_POINT_SMOOTH);
}


void reshape(int w, int h) {
    glViewport(0, 0, w, h);
    glMatrixMode(GL_PROJECTION);
    glLoadIdentity();
    gluOrtho2D(0, 0, w, h);
    glMatrixMode(GL_MODELVIEW);
    glLoadIdentity();
}


int addV(point p) {
    int pos = -1;
    for (int i = 0; i < vertex.size(); i++) {
        if (fabs(vertex[i].x - p.x) < EPS && fabs(vertex[i].y - p.y) < EPS) {
            pos = i;
            break;
        }
    }
    if (pos == -1) {
        pos = vertex.size();
        vertex.push_back(p);
    }
    return pos;
}

void createGraph(tree* t) {
    if (t->left != NULL) createGraph(t->left);
    if (t->type == TRD) {
        trapezoid tt = trapezoids[t->pos];
        cerr << "GR TT " << t->pos << " " << tt.left.x << " " << tt.right.x << endl;
        double x = (tt.left.x + tt.right.x) / 2;
        double y = (min(tt.top.st.y, tt.top.en.y) + max(tt.bottom.st.y, tt.bottom.en.y)) / 2;
        int pos = addV(point(x, y));
        for (int nn: tt.next) {
            trapezoid next = trapezoids[nn];
            double nx = (next.left.x + next.right.x) / 2;
            double ny = (min(next.top.st.y, next.top.en.y) + max(next.bottom.st.y, next.bottom.en.y)) / 2;
            point np = point(nx, ny);
            double bx = next.left.x;
            double by = (min(tt.top.en.y, next.top.st.y) + max(tt.bottom.en.y, next.bottom.st.y)) / 2;
            point bp = point(bx, by);
            int pos_n = addV(np);
            int pos_b = addV(bp);
            g[pos].push_back(pos_b);
            g[pos_b].push_back(pos);
            g[pos_b].push_back(pos_n);
            g[pos_n].push_back(pos_b);
        }
        return;
    }
    if (t->right != NULL) createGraph(t->right);
}

bool used[N];
int prevv[N];

void addVertex(int p) {
    //join current vertex to current trapezoid
    tree* t = find(vertex[p], my_map);
    assert(t->type == 3);
    trapezoid tt = trapezoids[t->pos];
    double x = (tt.left.x + tt.right.x) / 2;
    double y = (min(tt.top.st.y, tt.top.en.y) + max(tt.bottom.st.y, tt.bottom.en.y)) / 2;
    int pos = addV(point(x, y));
    g[p].push_back(pos);
    g[pos].push_back(p);
}

void bfs(int start) {
    int n = vertex.size();
    for (int i = 0; i < n; i++) {
        used[i] = false;
        prevv[i] = -1;
    }
    queue<int> q;
    q.push(start);
    used[start] = true;
    while (!q.empty()) {
        int v = q.front();
        q.pop();
        for (int to: g[v]) {
            if (!used[to]) {
                used[to] = true;
                prevv[to] = v;
                q.push(to);
            }
        }
    }

    int cur = 1;
    while (cur != -1) {
        path.push_back(cur);
        cur = prevv[cur];
    }
    reverse(path.begin(), path.end());
}


int main(int argc, char * argv[])
{
    glutInit(&argc, argv);
    glutInitDisplayMode(GLUT_DOUBLE|GLUT_RGBA);
    glutInitWindowSize(500, 500);
    glutCreateWindow("Motion planning");
    glClearColor(1, 1, 1, 1);
    glutReshapeFunc(reshape);
    init();
//    segment s1 = {point(10, 10), point(90, 10)};
    segment s2 = {point(10, 50), point(40, 60)};
    segment s3 = {point(40, 60), point(70, 60)};
    segment s4 = {point(70, 60), point(90, 50)};
    segment s22 = {point(10, 30), point(30, 40)};
    segment s33 = {point(30, 40), point(80, 40)};
    segment s44 = {point(80, 40), point(90, 30)};
//    segment s5 = {point(10, 80), point(90, 80)};
    segment t1 = {point(30, 30), point(50, 50)};
    segment t2 = {point(50, 50), point(30, 50)};
    segment t3 = {point(30, 50), point(30, 30)};
//    add(s1, my_map);
    add(t1, my_map);
    add(t2, my_map);
//    add(t3, my_map);
//    add(s22, my_map);
//    add(s33, my_map);
//    add(s44, my_map);

//    add(s5, my_map);

    start = point(-MAX, -MAX);
    finish = point(MAX, MAX);
    vertex.push_back(start);
    vertex.push_back(finish);
    createGraph(my_map);
    addVertex(0);
    addVertex(1);
    bfs(0);
    glutDisplayFunc(display);
    glutMainLoop();
    return 0;
}
