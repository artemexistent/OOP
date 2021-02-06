#pragma once
#include <iostream>
#include <fstream>
#include <algorithm>
#include <vector>
#include <windows.h>
#include <conio.h>
#include <set>
#include <time.h>
using namespace std;

struct Graph_1{

	int n;
	vector< vector<int> > matrix;

	Graph_1(){
		n = 0;
	}

	Graph_1(int k){
		n = k;
		matrix.resize(n + 1, vector<int>(n + 1, 0));
	}

	void add_edge(int x, int y, int z, int type){
		matrix[x][y] = z;
		if (type==2)
            matrix[y][x]=z;
	}

	void write_matrix(){
        cout << "Number of vertices in a graph: " << n << endl;
        cout << "Adjacency matrix:" << endl;
        for (int i = 1; i <= n; i ++){
            for (int j = 1; j <= n; j ++){
                cout << matrix[i][j] << " ";
            }
            cout << endl;
        }
	}

};

struct Graph_2{

	int n;
	vector< vector< pair<int,int> > > List;

	Graph_2(){
		n = 0;
	}

	Graph_2(int k){
		n = k;
		List.resize(n + 1, vector<  pair<int,int> >(1, {0,0}) );
	}

	void add_edge(int x, int y, int z, int type){
		List[x].push_back({y,z});
		if (type==2)
            List[y].push_back({x,z});
	}

	void write_List(){
        cout << "Number of vertices in a graph: " << n << endl;
        cout << "Adjacency List:" << endl;
        for (int i = 1; i <= n; i ++){
            for (int j = 1; j <= List[i].size()-1; j ++){
                cout << i << " " << List[i][j].first << "  " << List[i][j].second << endl;
            }
        }
	}

};

void Body(int type);
void Components(Graph_1 a_1, Graph_2 a_2, int type, int type_2);
void Decstra(Graph_1 a_1,Graph_2 a_2,int type,int p);
void Topoligical(Graph_1 a_1,Graph_2 a_2, int type);
void Tree(Graph_1 a_1, Graph_2 a_2, int type,int p);
void minTree(Graph_1 a_1, Graph_2 a_2, int type);
/*
    void Demo();
    void Bench();
*/
