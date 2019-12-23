package assign;
import java.util.*;

public class DES {
	
	
	public int[] generatekey1(int[] k){
		int p10[] = {3,5,2,7,4,10,1,9,8,6};
		int p8[] = {6,3,7,4,8,5,10,9};
		
		int inter[] =new int[10];

		for(int i=0;i<10;i++)
		{
			inter[i] = k[p10[i]-1];
		}
		
		int temp = inter[0], i; 
	    for (i = 0; i < 4; i++) 
	        inter[i] = inter[i + 1]; 
	  
	    inter[i] = temp;
	    temp = inter[5]; 
	    for (i = 5; i < 9; i++) 
	        inter[i] = inter[i + 1]; 
	  
	    inter[i] = temp;
	    
		int result[] =new int [8];
		for(int l=0;l<8;l++)
		{
			result[l] = inter[p8[l] -1];
		}
		
		return result;
		
	}
	
	public int[] generatekey2(int[] k){
		int p10[] = {3,5,2,7,4,10,1,9,8,6};
		int p8[] = {6,3,7,4,8,5,10,9};
		
		int inter[] =new int[10];

		for(int i=0;i<10;i++)
		{
			inter[i] = k[p10[i]-1];
		}
		
		for(int itr = 0; itr<3; itr++)
		{	
			int temp = inter[0], i; 
			for (i = 0; i < 4; i++) 
				inter[i] = inter[i + 1]; 
	  
			inter[i] = temp;
			temp = inter[5]; 
			for (i = 5; i < 9; i++) 
				inter[i] = inter[i + 1]; 
	  
			inter[i] = temp;
		}
		
		int result[] =new int [8];
		for(int l=0;l<8;l++)
		{
			result[l] = inter[p8[l] -1];
		}
		
		return result;
		
	}
	
	int [] IP(int[] p)
	{
		int res[] =new int [8];
		int ip[] = {2,6,3,1,4,8,5,7};
		for(int i=0;i<8;i++)
		{
			res[i] = p[ip[i]-1];
		}
		return res;
	}
	
	int [] IPInv(int[] p)
	{
		int res[] =new int [8];
		int ip[] = {4,1,3,5,7,2,8,6};
		for(int i=0;i<8;i++)
		{
			res[i] = p[ip[i]-1];
		}
		return res;
	}
	
	int[] bigf(int[] p,int[] key)
	{
		int res[] =new int [8];
		int ep[] = {4,1,2,3,2,3,4,1};
		int p4[] = {2,3,4,1};
		//int sbox[] = {};
		for(int i=0;i<8;i++)
		{
			res[i] = p[ep[i]-1];
		}
		
		for(int i=0;i<8;i++)
		{
			res[i] = (res[i]+key[i])%2;
		}
		
		// let s box output 1000
		
		//P4 will output 0001
		
		int result[] = {0,0,0,1};
		
		return result;
	}
	
	int[] generateLeft(int[] p)
	{
		int res[] = new int[4];
		for(int i=0;i<4;i++)
		{
			res[i]=p[i];
		}
		return res;
	}
	
	int[] generateRight(int[] p)
	{
		int res[] = new int[4];
		for(int i=0;i<4;i++)
		{
			res[i]=p[i+4];
		}
		return res;
	}
	
	int[] smallf(int[] l ,int[] r,int[] key, int flag)
	{
		int[] Fres=this.bigf(r, key);
		System.out.print("Big F returned value\n");
		for(int i=0;i<4;i++)
		{
			System.out.print(Fres[i]);
		}
		System.out.print("\n");
		for(int i=0;i<4;i++)
		{
			Fres[i] = (Fres[i]+l[i])%2;
		}
		System.out.print("After xor value\n");
		for(int i=0;i<4;i++)
		{
			System.out.print(Fres[i]);
		}
		System.out.print("\n");
		int[] res=new int [8];
		int x=0,y=0;
		if(flag==1)
		{	
			for(int i=0;i<8;i++)
			{
				if(i<4)
				{
					res[i]=r[x];
					x++;
				}
				else
				{
					res[i]=Fres[y];
					y++;
				}
			}
		}
		else 
		{
			for(int i=0;i<8;i++)
			{
				if(i<4)
				{
					res[i]=Fres[x];
					x++;
				}
				else
				{
					res[i]=r[y];
					y++;
				}
			}
		}
		return res;
	}
	
	
	
	public static void main(String args[])
	{
		
		int key[] = {1,1,0,0,0,1,1,1,1,0};
		int pt[] = {0,0,1,0,1,0,0,0};
		
		DES d =new DES();
		
		System.out.print("Generated key1\n");
		int k1[] = d.generatekey1(key);
		for(int i=0;i<8;i++)
		{
			System.out.print(k1[i]);
		}
		System.out.print("\n");
		System.out.print("Generated key2\n");
		int k2[] = d.generatekey2(key);
		for(int i=0;i<8;i++)
		{
			System.out.print(k2[i]);
		}
		System.out.print("\n");
		
		int ipt[] = d.IP(pt);
		System.out.print("IP of the plane text\n");
		for(int i=0;i<8;i++)
		{
			System.out.print(ipt[i]);
		}
		System.out.print("\n");
		
		int left1[] = d.generateLeft(ipt);
		int right1[] = d.generateRight(ipt);
		
		int result1[]= d.smallf(left1, right1, k1,1);
		System.out.print("After fk1 and sw\n");
		for(int i=0;i<8;i++)
		{
			System.out.print(result1[i]);
		}
		System.out.print("\n");
		
		int left2[] = d.generateLeft(result1);
		int right2[] = d.generateRight(result1);
		
		int result2[] = d.smallf(left2, right2, k2,2);
		
		
		int finalresult[] = d.IPInv(result2);
		System.out.print("Before Inverse Permutations :-\n");
		for(int i=0;i<8;i++)
		{
			System.out.print(result2[i]);
		}
		System.out.print("\n");
		
		System.out.print("Final cipher text is:-\n");
		for(int i=0;i<8;i++)
		{
			System.out.print(finalresult[i]);
		}
		System.out.print("\n");
	}
}

