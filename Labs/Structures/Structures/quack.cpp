#include <iostream>
#include <cstdio>
#include <cmath>
#include <string>


using namespace std;

const int N = 100010, INF = 1000000000;

int *queue = new int[1];
int head = 0, tail = 0, s = 1;

struct my
{
    int pos;
    string name;
    my(){}
    my(string s, int a)
    {
        name = s;
        pos = a;
    }
};

void bigger()
{
    int *a = new int[2 * s];
    for (int i = head; i < tail; i++)
        a[i - head] = queue[i];
    queue = a;
    s = 2 * s;
    tail -= head;
    head = 0;
}

void less1()
{
    int *a = new int[s / 2];
    for (int i = head; i < tail; i++)
        a[i - head] = queue[i];
    queue = a;
    s = s / 2;
    tail -= head;
    head = 0;
}
void pushq(int x)
{
    if (tail + 1 == s)
        bigger();
    queue[tail] = x;
    tail++;
}

int popq()
{
    int t = queue[head];
    head++;
    if (tail - head <= s / 4)
        less1();
    return t;
}

int strtox(string a)
{
    int res = 0, p = 0;
    if (a[0] == '-')
    {
        p = 1;
    }
    for (int i = p; i < a.size(); i++)
        res = res * 10 + (a[i] - '0');
    if (p == 1)
        return -res;
    return res;
}

string comands[N];
int n = 0, registers[30], l = 0;
my labels[N];


void init()
{
    for (int i = 0; i < 30; i++)
        registers[i] = INF;
}
int main()
{
    freopen("quack.in", "r", stdin);
    freopen("quack.out", "w", stdout);
    init();
    char c;
    comands[0] = "";
    while (scanf("%c", &c) != EOF)
    {
     //   if (c == '!')
          //  break;
        if (c == '\n')
        {
            n++;
            comands[n] = "";
        }
        else
        {
            comands[n] += c;
        }
    }
	for (int k = 0; k < n; k++)
	{
		if (comands[k][0] == ':')
        {
            string a = "";
            for (int j = 1; j < comands[k].size(); j++)
                a += comands[k][j];
            labels[l] = my(a, k);
            l++;
        } 
	}
    int i = 0;
    while (i < n)
    {
		int q = i + 1;
        if (comands[i] == "+")
        {
			int a = popq(), b = popq();
            pushq(((a + b) % 65536 + 65536) % 65536);
        } else
            if (comands[i] == "-")
            {
                int a = popq(), b = popq();
                pushq(((a - b) % 65536 + 65536) % 65536);
            } else
                if (comands[i] == "*")
                {
                    int a = popq(), b = popq();
                    pushq(((a * b) % 65536 + 65536) % 65536);
                } else
                    if (comands[i] == "/")
                    {
                        int a = popq(), b = popq();
                        if ((a == b) && (b == 0))
                            pushq(0);
                        else
                            pushq(a / b);
                    } else
                        if (comands[i] == "%")
                        {
                            int a = popq(), b = popq();
                            if ((a == b) && (b == 0))
                                pushq(0);
                            else
                                pushq(((a % b) + b) % b);
                        } else
                            if (comands[i][0] == '>')
                            {
                                int a = popq();
                                registers[comands[i][1] - 'a'] = a;
                                
                            } else
                                if (comands[i][0] == '<')
                                {
                                    pushq(registers[comands[i][1] - 'a']);
                                    
                                } else
                                    if (comands[i][0] == 'P')
                                    {
                                        if (comands[i].size() == 1)
                                        {
                                            cout << popq() << endl;
                                        }
                                        else
                                        {
                                            cout << registers[comands[i][1] - 'a'] << endl;
                                        }
                                        
                                    } else
                                        if (comands[i][0] == 'C')
                                        {
                                            if (comands[i].size() == 1)
                                            {
                                                cout << (char)((popq() % 256 + 256) % 256);
                                            }
                                            else
                                            {
                                                cout << (char)((registers[comands[i][1] - 'a'] % 256 + 256) % 256);
                                            }
                                            
                                        } else
                                            if (comands[i][0] == ':')
                                            {
                                                
                                            } else
                                                if (comands[i][0] == 'J')
                                                {
                                                    string a = "";
                                                    for (int j = 1; j < comands[i].size(); j++)
                                                        a += comands[i][j];
                                                    for (int j = 0; j < l; j++)
                                                        if (labels[j].name == a)
                                                        {
                                                            q = labels[j].pos;
                                                            break;
                                                        }
                                                } else
                                                    if (comands[i][0] == 'Z')
                                                    {
                                                        string a = "";
                                                        for (int j = 2; j < comands[i].size(); j++)
                                                            a += comands[i][j];
                                                        if (registers[comands[i][1] - 'a'] == 0)
                                                        {
                                                            for (int j = 0; j < l; j++)
                                                                if (labels[j].name == a)
                                                                {
                                                                    q = labels[j].pos;
                                                                    break;
                                                                }
                                                        }
                                                    } else
                                                        if (comands[i][0] == 'E')
                                                        {
                                                            string a = "";
                                                            for (int j = 3; j < comands[i].size(); j++)
                                                                a += comands[i][j];
                                                            if (registers[comands[i][1] - 'a'] == registers[comands[i][2] - 'a'])
                                                            {
                                                                for (int j = 0; j < l; j++)
                                                                    if (labels[j].name == a)
                                                                    {
                                                                        q = labels[j].pos;
                                                                        break;
                                                                    }
                                                            }
                                                        } else
                                                            if (comands[i][0] == 'G')
                                                            {
                                                                string a = "";
                                                                for (int j = 3; j < comands[i].size(); j++)
                                                                    a += comands[i][j];
                                                                if (registers[comands[i][1] - 'a'] > registers[comands[i][2] - 'a'])
                                                                {
                                                                    for (int j = 0; j < l; j++)
                                                                        if (labels[j].name == a)
                                                                        {
                                                                            q = labels[j].pos;
                                                                            break;
                                                                        }
                                                                }
                                                            } else
                                                                if (comands[i][0] == 'Q')
                                                                {
                                                                    break;
                                                                } else
                                                                {
                                                                    int a = strtox(comands[i]);
                                                                    pushq(a);
                                                                }  
		i = q;
    }
    return 0;
}           