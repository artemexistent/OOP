#include "Header.h"


void DFS_1(Graph_1 a, int v, vector <int> &used, vector <vector<int> > &ans, int type){
    used[v]=1;

    vector <pair <int,int> >res;
    for (int i=1;i<=a.n;i++){
        if (a.matrix[v][i]!=0  && used[i]==0){
            if (type != 2)
                DFS_1(a,i,used,ans,type);
            else res.push_back({a.matrix[v][i],i});

        }
    }
    ans[ans.size()-1].push_back(v);
    if (type == 2){
        sort(res.begin(),res.end());
        for (int i=0;i<res.size();i++){
            if (used[res[i].second]==0)
                DFS_1(a,res[i].second,used,ans,type);
        }
    }
}

void DFS_2(Graph_2 a, int v, vector <int> &used, vector <vector<int> > &ans,int type){
    used[v]=1;

    vector <pair <int,int> >res;
    for (int i=1;i<a.List[v].size();i++){
        if (used[a.List[v][i].first]==0){
            if (type != 2)
                DFS_2(a,a.List[v][i].first,used,ans,type);
            else res.push_back({a.List[v][i].second,a.List[v][i].first});;
        }
    }
    ans[ans.size()-1].push_back(v);
    if (type == 2){
        sort(res.begin(),res.end());
        for (int i=0;i<res.size();i++){
            if (used[res[i].second]==0)
                DFS_2(a,res[i].second,used,ans,type);
        }
    }
}

void Components(Graph_1 a_1, Graph_2 a_2, int type, int type_2){
    vector <int> used(a_1.n+1,0);
    vector <vector<int> > ans;
    for (int i=1;i<=a_1.n;i++){
        if (used[i]==0){
            ans.push_back(vector<int>(0));
            if (type == 1){
                DFS_1(a_1,i,used,ans,type_2);
            }else {
                DFS_2(a_2,i,used,ans,type_2);
            }
        }
    }
    cout << "Components: ";
    cout << ans.size() << endl;
    for (int i=0;i<ans.size();i++){
        for (int j=0;j<ans[i].size();j++)
            cout << ans[i][j] << " ";
        cout << endl;
    }
}

void Decstra_1(Graph_1 a, int v, Graph_1 &ans, vector <int> &used, int V ){
    used[v]=1;
    int res=-1;
    for (int i=1;i<=a.n;i++){
        if (a.matrix[v][i]!=0 && used[i]==0){
            if (res==-1)
                res=i;
            ans.matrix[V][i]=min(a.matrix[v][i]+ans.matrix[V][v],ans.matrix[V][i]);
            if (ans.matrix[V][i]<ans.matrix[V][res])
                res=i;
        }
    }
    if (res!=-1)
        Decstra_1(a,res,ans,used,V);
}

void Decstra_2(Graph_2 a, int v, Graph_1 &ans, vector <int> &used, int V ){
    used[v]=1;
    int res=-1;
    for (int i=1;i<a.List[v].size();i++){
        int j=a.List[v][i].first;
        if (used[j]==0){
            if (res==-1)
                res=j;
            ans.matrix[V][j]=min(a.List[v][i].second+ans.matrix[V][v],ans.matrix[V][j]);
            if (ans.matrix[V][j]<ans.matrix[V][res])
                res=j;
        }
    }
    if (res!=-1)
        Decstra_2(a,res,ans,used,V);
}

void Decstra(Graph_1 a_1,Graph_2 a_2,int type,int p){
    int Size=max(a_1.n,a_2.n);
    Graph_1 ans(Size);
    for (int i=1;i<=Size;i++){
        for (int j=1;j<=Size;j++){
            ans.matrix[i][j]=INT_MAX;
        }
        ans.matrix[i][i]=0;
    }


    for (int i=1;i<=Size;i++){
        vector <int> used(Size+1,0);

        if (type == 1){
            Decstra_1(a_1,i,ans,used,i);
        }else Decstra_2(a_2,i,ans,used,i);
    }



    if (p==0){
        int answer=5+1;
        while (answer!=4){
            if (answer==1){
                int x,y;
                cout << "Enter x and y:\nx=";
                cin >> x;
                cout << "y=";
                cin >> y;
                cout << "Distance = " ;
                if (ans.matrix[x][y]!=INT_MAX)
                    cout << ans.matrix[x][y];
                else cout << 0;
                cout << endl;
            }
            if (answer==2){
                int x;
                cout << "Enter x:\nx=";
                cin >> x;
                cout << "Distance:\n";
                for (int i=1;i<=ans.n;i++){
                    if (ans.matrix[x][i]!=INT_MAX)
                        cout << ans.matrix[x][i] << " ";
                    else cout << "0 ";
                }
                cout << endl;
            }
            if (answer==3){
                cout << "Distance:\n";
                for (int i=1;i<=Size;i++){
                    for (int j=1;j<=Size;j++){
                        if (ans.matrix[i][j]!=INT_MAX)
                            cout << ans.matrix[i][j] << " ";
                        else cout << "0 ";
                    }
                    cout << endl;
                }
            }
            system ("pause");
            answer=5+1;

        }
    }else {
        cout << "Distance:\n";
        for (int i=1;i<=Size;i++){
            for (int j=1;j<=Size;j++){
                if (ans.matrix[i][j]!=INT_MAX)
                    cout << ans.matrix[i][j] << " ";
                else cout << "0 ";
            }
            cout << endl;
        }
    }
}

void Topoligical(Graph_1 a_1, Graph_2 a_2, int type){

    vector <int> used(a_1.n+1,0);
    vector <vector<int> > ans;
    ans.push_back(vector<int>(0));
    for (int i=1;i<=a_1.n;i++){
        if (used[i]==0){
            if (type == 1){
                DFS_1(a_1,i,used,ans,1);
            }else {
                DFS_2(a_2,i,used,ans,1);
            }
        }
    }
    cout << "Answer: ";
    for (int j=ans[0].size()-1;j>=0;j--)
        cout << ans[0][j] << " ";

}

void DFS_1_1(Graph_1 a, int v, vector <int> &used, Graph_2 &ans, int type,int &sum){
    used[v]=1;

    vector <pair <int,int> >res;
    for (int i=1;i<=a.n;i++){
        if (a.matrix[v][i]!=0  && used[i]==0){
            ans.add_edge(v,i,a.matrix[v][i],1);
            sum+=a.matrix[v][i];
            if (type != 2)
                DFS_1_1(a,i,used,ans,type,sum);
            else res.push_back({a.matrix[v][i],i});

        }
    }
    if (type == 2){
        sort(res.begin(),res.end());
        for (int i=0;i<res.size();i++){
            if (used[res[i].second]==0)
                DFS_1_1(a,res[i].second,used,ans,type,sum);
        }
    }
}

void DFS_2_2(Graph_2 a, int v, vector <int> &used, Graph_2 &ans,int type, int &sum){
    used[v]=1;

    vector <pair <int,int> >res;
    for (int i=1;i<a.List[v].size();i++){
        if (used[a.List[v][i].first]==0){
            ans.add_edge(v,a.List[v][i].first,a.List[v][i].second,1);
            sum+=a.List[v][i].second;
            if (type != 2)
                DFS_2_2(a,a.List[v][i].first,used,ans,type,sum);
            else res.push_back({a.List[v][i].second,a.List[v][i].first});;
        }
    }
    if (type == 2){
        sort(res.begin(),res.end());
        for (int i=0;i<res.size();i++){
            if (used[res[i].second]==0)
                DFS_2_2(a,res[i].second,used,ans,type,sum);
        }
    }
}

void Tree(Graph_1 a_1, Graph_2 a_2, int type,int p){
    vector <int> used(a_1.n+1,0);
    Graph_2 ans(a_1.n);
    int sum=0;
    int type_2=0;
    if (p==0)
        type_2=5;
    for (int i=1;i<=a_1.n;i++){
        if (used[i]==0){
            if (type == 1){
                DFS_1_1(a_1,i,used,ans,type_2,sum);
            }else {
                DFS_2_2(a_2,i,used,ans,type_2,sum);
            }
        }
    }
    cout << "Summ=" << sum << endl;
    ans.write_List();
}


void minTree_1(Graph_1 a, vector <int> used, vector <int> sel_e, vector <int> min_e , Graph_2 &ans,int &sum){
    for (int i=1;i<=a.n;i++){
        int res=-1;
        for (int j=1;j<=a.n;j++){
            if (used[j]==0){
                if (res==-1)
                    res=j;
                if (min_e[j]<min_e[res])
                    res=j;
            }
        }
        used[res]=1;
        if (sel_e[res]!=-1){
            ans.add_edge(res,sel_e[res],a.matrix[res][sel_e[res]],1);
            sum+=a.matrix[res][sel_e[res]];
        }
        for (int j=1;j<=a.n;j++)
            if (a.matrix[res][j]<min_e[j] && a.matrix[res][j]!=0){
                min_e[j]=a.matrix[res][j];
                sel_e[j]=res;
            }
    }
}

void minTree_2(Graph_2 a, vector <int> used, vector <int> sel_e, vector <int> min_e , Graph_2 &ans,int &sum){
    set< pair<int,int> >q;
    q.insert({1,1});

    for (int i=1;i<=a.n;i++){
        int res=q.begin()->second;
        q.erase (q.begin());

        if (sel_e[res]!=-1){
            int d=0;
            for (int k=1;k<a.List[res].size();k++)
                if (a.List[res][k].first==sel_e[res]){
                    d=a.List[res][k].second;
                    break;
                }

            ans.add_edge(res,sel_e[res],d,1);
            sum+=d;
        }
        for (int j=1;j<a.List[res].size();j++){
            int d=a.List[res][j].first,cost=a.List[res][j].second;
            if (cost<min_e[d]){
                q.erase ({min_e[d],d});
                min_e[d]=cost;
                sel_e[d]=res;
                q.insert({min_e[d],d});
            }
        }
    }
}


void minTree(Graph_1 a_1, Graph_2 a_2, int type){
    vector <int> used(a_1.n+1,0),sel_e(a_1.n+1,-1);
    vector <int> min_e(a_1.n+1,INT_MAX);
    min_e[1]=0;
    int sum=0;
    Graph_2 ans(a_1.n);
    if (type==1)
        minTree_1(a_1,used,sel_e,min_e,ans,sum);
    else minTree_2(a_2,used,sel_e,min_e,ans,sum);
    cout << "SUMM=" << sum << endl;
    ans.write_List();
}
