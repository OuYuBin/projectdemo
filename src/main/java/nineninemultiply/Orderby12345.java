package nineninemultiply;

public class Orderby12345 {
	public static void main(String[] args) {

		for (int i = 1; i < 6; i++) {
			for (int j = 1; j < 6; j++) {
				if (i==j) {
					continue;
				}
				for (int m = 1; m < 6; m++) {
					if (i==m||j==m) {
						continue;
					}
					for (int k = 1; k < 6; k++) {
						if (k==i||k==j||k==m) {
							continue;
						}
						for (int n = 1; n < 6; n++) {
							if (n==i||n==j||n==m||n==k) {
								continue;
							}
							System.out.print(i);
							System.out.print(j);
							System.out.print(m);
							System.out.print(k);
							System.out.print(n);
							System.out.println();
						}
						
					}
				}
			}
		}
	}
}
