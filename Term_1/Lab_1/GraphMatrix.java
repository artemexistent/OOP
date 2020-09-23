package Lab_1;

import java.util.Vector;

public class GraphMatrix{
    private final Vector <Vector< Integer >>  next;
    private final Vector <Boolean> used;
    public Vector <Integer> distance;


    public GraphMatrix(int n){
        Vector < Integer > p = new Vector<>(0);
        next = new Vector<> (0);
        used = new Vector<>(0);
        for (int i=0;i<n;i++) {
            used.addElement(true);
            next.addElement(p);
            p = new Vector<>(0);
            for (int j=0;j<n;j++)
                next.get(i).addElement(0);
        }
    }

    private void dfs(int x){
        used.set(x,false);
        for (int i=0;i<next.get(x).size();i++){
            if (next.get(x).get(i)!=0 && used.get(i))
                dfs(i);
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
        for (int i=0;i<next.size();i++){
            if (next.get(x).get(i)!=0 && used.get(i)){
                if (i==y)
                    return next.get(x).get(i);
                int k=distance(i,y);
                if (k!=-1){
                    used.set(x,true);
                    return k+next.get(x).get(i);
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
        for (int i=0;i<next.size();i++){
            if (next.get(x).get(i)!=0 && used.get(i)){
                distance.set(i,Math.min(distance.get(i),distance.get(x)+next.get(x).get(i)));
                if (mi>distance.get(i)){
                    mi = distance.get(i);
                    id = i;
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

    public void push_edge(int x, int y, int z){
        next.get(x).set(y,z);
        next.get(y).set(x,y);
    }

    public void push_vertex(){
        Vector < Integer > p = new Vector<>(0);
        next.addElement(p);
        for (int i=0;i<next.size();i++)
            next.get(next.size()).addElement(0);
        for (int i=0;i<next.size()-1;i++)
            next.get(i).addElement(0);
    }

    public String write(){
        String ans= "";
        for (int i=0;i<next.size();i++){
            ans += i+":\t";
            for (int j=0;j<next.get(i).size();j++){
                ans += next.get(i).get(j)+" ";
            }
            ans += "\n";
        }
        return ans;
    }

    public void delete_edge(int x, int y){
        int kol=-1;
        for (int i=0;i<next.get(x).size();i++){
            if (next.get(x).get(i)!=0 && i==y){
                kol=i;
                break;
            }
        }
        if (kol!=-1)
            next.get(x).remove(y);
    }

    public void delete_vertex(int x){
        for (int i=0;i<next.size();i++){
            next.get(i).set(x,0);
            next.get(x).set(i,0);
        }
    }

}
