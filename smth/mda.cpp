#include <iostream>
#include <vector>
using namespace std;

vector<int>g[110];
bool used[110];
int color[110];

bool dfs(int v,int col)
{   color[v]=col;
    used[v]=true;
    for(int i=0;i<g[v].size();i++)
    {
        if(!used[g[v][i]])
        {
            if(!dfs(g[v][i],1-col))
                return false;

        }
        else
            if(color[g[v][i]]==col)
                return false;
    }
    return true;
}

int main()
{
    int n,zn,m,x,y;
    cin>>n>>m;
    for(int i=0;i<m;i++)
    {

            cin>>x>>y;
            g[x-1].push_back(y-1);
            g[y-1].push_back(x-1);


    }


    dfs(0,0)?cout<<"YES":cout<<"NO";
    return 0;
}
