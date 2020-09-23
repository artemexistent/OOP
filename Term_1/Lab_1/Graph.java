package Lab_1;

import java.util.Vector;

public class Graph {
    private final Vector  < Vector < Pair<Integer,Integer> > >  next;
    private final Vector <Boolean> used;
    public Vector <Integer> distance;


    public Graph(int n){
        Vector < Pair<Integer,Integer> > p = new Vector<>(0);
        next = new Vector<> (0);
        used = new Vector<>(0);
        for (int i=0;i<n;i++) {
            used.addElement(true);
            next.addElement(p);
            p = new Vector<>(0);
        }
    }

    public void dfs(int x){
        used.set(x,false);
        for (int i=0;i<next.get(x).size();i++){
            if (used.get(next.get(x).get(i).first))
                dfs(next.get(x).get(i).first);
        }
    }

    public int connect(){
        for (int i=0;i<next.size();i++)
            used.set(i,true);
        int kol=0;
        for (int i=0;i<next.size();i++){
            if (used.get(i)){
                kol++;
                dfs(i);
            }
        }
        for (int i=0;i<next.size();i++)
            used.set(i,true);
        return kol;
    }

    public int distance(int x, int y){
        used.set(x,false);
        for (Pair<Integer, Integer> i : next.get(x)){
            if (used.get(i.first)){
                if (i.first==y)
                    return i.second;
                int k=distance(i.first,y);
                if (k!=-1){
                    used.set(x,true);
                    return k+i.second;
                }
            }
        }
        used.set(x,true);
        if (x==y)
            return 0;
        else return -1;
    }

    public void deicstra(int x){
        used.set(x,false);
        int mi = Integer.MAX_VALUE;
        int id = -1;
        for (Pair<Integer, Integer> i : next.get(x)){
            if (used.get(i.first)){
                distance.set(i.first,Math.min(distance.get(i.first),distance.get(x)+i.second));
                if (mi>distance.get(i.first)){
                    mi = distance.get(i.first);
                    id = i.first;
                }
            }
        }
        if (id!=-1)
            deicstra(id);
        used.set(x,true);
    }

    public String distance_2(int x){
        distance = new Vector<>(0);
        for (int i=0;i< next.size();i++) {
            distance.addElement(Integer.MAX_VALUE);
            used.set(i,true);
        }
        distance.set(x,0);
        deicstra(x);
        String ans = "";
        for (int i=0;i< next.size();i++){
            ans += i + " = " + distance.get(i) + "\n";
        }
        return ans;
    }

    public void push_edge(int x, Pair<Integer, Integer> y){
        next.get(x).addElement(y);
        int p = x;
        x = y.first;
        Pair <Integer, Integer> t = new Pair<Integer, Integer>();
        t.make_pair(p,y.second);
        next.get(x).addElement(t);
    }

    public void push_vertex(){
        Vector < Pair<Integer,Integer> > p = new Vector<>(0);
        next.addElement(p);
    }

    public String write(){
        String ans = "";
        for (int i=0;i<next.size();i++){
            ans += i+ ":  " ;
            for (int j=0;j<next.get(i).size();j++){
                ans += next.get(i).get(j).first + " ";
            }
            ans += "\n";
        }
        return ans;
    }

    public void delete_edge(int x, int y){
        int kol=-1;
        int i = 0;
        for (i=0;i<next.get(x).size();i++){
            if (next.get(x).get(i).first==y){
                kol=i;
                break;
            }
        }
        if (kol!=-1)
            next.get(x).remove(i);
    }

    public void delete_vertex(int x){
        next.get(x).clear();
        for (int i=0;i< next.size();i++)
            for (int j=0;j<next.get(i).size();j++)
                if (next.get(i).get(j).first==x) {
                    next.get(i).remove(j);
                }
    }
}
