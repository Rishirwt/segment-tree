import java.util.Scanner;

public class MINQUERY {

    public static void main(String[] args) {
        Scanner s=new Scanner(System.in);
        int a[]={1,3,2,-2,4,-3};
        int n=a.length;
        int[] tree=new int[4*n+1];

        buildtree(tree,a,1,0,n-1);
        int noofqueries;
        int t=s.nextInt();

        while(t>0)
        {
            int l=s.nextInt();
            int r=s.nextInt();
            int answer=query(tree,1,0,n,l,r);
            System.out.println(answer);
        }

    }
    public static void updaterange(int[] tree,int index,int s,int e,int rs,int re,int inc)
    {
        //no overlap
        if(re<s||rs>e)
            return;
        //reached leaf node
        if(s==e)
        {
            tree[index]+=inc;

            return;
        }
        int mid=(s+e)/2;
        updaterange(tree,2*index,s,mid,rs,re,inc);
        updaterange(tree,2*index+1,mid+1,e,rs,re,inc);
        tree[index]=Integer.min(tree[2*index],tree[2*index+1]);
        return;


    }
   public static void buildtree(int[] tree,int[] a,int index,int s,int e)
    {
        if(s>e)
        {
            return;
        }
        if(s==e)
        {
            tree[index]=a[s];
            return;
        }
        int mid=(s+e)/2;
        //left subtree
        buildtree(tree,a,2*index,s,mid);
        //right subtree
        buildtree(tree,a,2*index+1,mid+1,e);

        int left=tree[2*index];
        int right=tree[2*index + 1];

        tree[index]=Integer.min(left,right);

    }

    public static void update(int[] tree,int index,int s,int e,int i,int value)
    {
        //no overlap
        if(i<s||i>e)
        {
            return;
        }
        //reched leaf node
        if(s==e)
        {
            tree[index]=value;
            return;
        }
        //partial overlap or i is b/w s and e
        int mid=(s+e)/2;
        update(tree,2*index,s,mid,i,value);
        update(tree,2*index+1,mid+1,e,i,value);
        tree[index]=Integer.min(tree[2*index],tree[2*index+1]);
        return;
    }
    public static int query(int[] tree,int index,int s,int e,int qs,int qe)
    {
        //no overlap
     if(qs>e||qe<s)
     {
         return Integer.MAX_VALUE;
     }
     //complete overlap
         if(qs<=s&&qe>=e)
         {
             return tree[index];
         }
         // partial overlap.call both sides
         int mid=(s+e)/2;
     int left = query(tree,2*index,s,mid,qs,qe);
     int right= query(tree,2*index+1,mid+1,e,qs,qe);

     return Integer.min(left,right);
    }


}
