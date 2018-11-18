

/*
* Get Maxor value from 2 values
* Test confilict
* Test Rebase
* Test Rebase2
* Test Rebase3*/
public class MaxXor {
	static MaxXor mx = new MaxXor();
	private static final int N = 100002;
	private static final long INF = (long) 1<<61;
	static long[] a = new long[N];
	static Tree root;
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		long tmp = 0;
		root = mx.new Tree(-1);
	
		for(int i=0; i<n; i++) {
			a[i] = Long.parseLong(in.next());
			tmp ^= a[i];
			insert(tmp);
		}
		
		tmp = 0;
		long ans = -INF;
		
		for(int i=0; i<n; i++){
			tmp ^= a[n-1-i];
			ans = max(ans , match(tmp));
		} 
		System.out.println(ans);
		in.close();

	}
	
	class Tree {
		private int val;
		Tree left , right;
		public Tree(int v) {
			this.val = v;
		}
	}
	
	public static void insert(long x) {
		Tree t = root;
		for(int i=40; i>=0; i--) {
			int id = (int) ((x >> i) & 1);
			if(id == 0){
				if(t.left == null) t.left = mx.new Tree(id);
				t = t.left;
			}
			else {
				if(t.right == null) t.right = mx.new Tree(id);
				t = t.right;
			}
		}
	}

	public static long match (long m) {
		m = ~m;
		long ans = 0;
		Tree t = root;
		
		for(int i=40; i>=0; i--) {
			ans <<= 1;
			int bit = (int) (m >> i) & 1;
			if(bit == 1) {
				if(t.right != null) {
					t = t.right;
					ans++;
				}
				else {
					t = t.left;
				}
			}
			else{
				if(t.left != null) {
					t = t.left;
					ans++;
				}
				else {
					t = t.right;
				}
			}
		}
		return ans;
	}
	
	public static long max (long a , long b) {
		return a>b ? a : b;
	}
	
}
