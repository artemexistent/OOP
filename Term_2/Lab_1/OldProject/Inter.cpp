// @startuml

#include "Header.h"

void Body(int type){
    int type_2 = 1;
    int n;
    cout << "Enter number of vertices:";
    cin >> n;
    Graph_1 a_1(n);
    Graph_2 a_2(n);
    int m;
    cout << "Enter number of ribs:";
    cin >> m;
    cout << "Enter ribs between x and y with weight z:\n";
    for (int i=1;i<=m;i++){
        int x; cout << "x="; cin >> x;
        int y; cout << "y="; cin >> y;
        int z; cout << "z="; cin >> z;
        if (type==1)
            a_1.add_edge(x,y,z,type_2);
        else a_2.add_edge(x,y,z,type_2);
        cout << endl;
    }
    int answer = 6;
    while(answer!=8){

        switch (answer){

            case 0: if (type==1) a_1.write_matrix(); else a_2.write_List(); system("pause"); break;
            case 1: Components(a_1,a_2,type,1); system("pause"); break;
            case 2: Components(a_1,a_2,type,1); system("pause"); break;
            case 3: Components(a_1,a_2,type,2); system("pause"); break;
            case 4: Decstra(a_1,a_2,type,0); system("pause"); break;
            case 5: Topoligical(a_1,a_2,type); system("pause"); break;
            case 6: Tree(a_1,a_2,type,0); system("pause"); break;
            case 7: minTree(a_1,a_2,type); system("pause"); break;
        }
        answer = 4;
    }

}
// @enduml