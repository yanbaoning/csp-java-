package test02;

import java.util.Scanner;

public class Main {
	public static int road(int t) {
		return t;
	}
	public static int red(int t) {
		return t;
	}
	public static int green() {
		return 0;
	}
	public static int yellow(int t,int r) {
		return t+r;
	}
	/*
	 * 输入的第一行包含空格分隔的三个正整数 r、y、g，表示红绿灯的设置。这三个数均不超过 106。
　　输入的第二行包含一个正整数 n（n ≤ 100），表示小明总共经过的道路段数和看到的红绿灯数目。
	
　　接下来的 n 行，每行包含空格分隔的两个整数 k、t。k=0 表示经过了一段道路，耗时 t 秒，此处 t 不超过 106；
	k=1、2、3 时，分别表示看到了一个红灯、黄灯、绿灯，且倒计时显示牌上显示的数字是 t，此处 t 分别不会超过 r、y、g。
	 */
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		//定义总时间
		int sumtime=0;
		//同步时间
		int currenttime=0;
		//正整数 r、y、g，表示红绿灯的设置
		int r = sc.nextInt();
		int y = sc.nextInt();
		int g = sc.nextInt();
		int T=r+y+g;
		//n表示小明总共经过的道路段数和看到的红绿灯数目
		int n = sc.nextInt();
		//k=0 表示经过了一段道路，耗时 t 秒
		//k=1、2、3 时，分别表示看到了一个红灯、黄灯、绿灯
		//且倒计时显示牌上显示的数字是 t
		for(int i=1;i<=n;i++) {
			int k = sc.nextInt();
			int t = sc.nextInt();
			currenttime=sumtime%T;
			switch(k) {
			case 0:		//0表示走了t秒路
				sumtime+=road(t);
				break;
			case 1:		//1表示红灯t秒
				if(currenttime>t && currenttime<t+g) {
					
				}else if(currenttime<t) {
					sumtime+=t-sumtime;
				}else if(currenttime>=t+g) {
					sumtime+=t+g+y+r-currenttime;
				}
				break;
			case 2:		//2表示黄灯t秒
				if(currenttime>=t+r&&currenttime<t+r+g) {
					
				}else if(currenttime<t+r) {
					sumtime+=t+r-currenttime;
				}else if(currenttime>=t+r+g) {
					sumtime+=t+r+g+y+r-currenttime;
				}
				break;
			case 3:		//3表示绿灯t秒
				//sumtime+=green();
				if(sumtime>=t && sumtime<t+y+r) {
					sumtime+=t+y+r-sumtime;
				}
				break;
			}
		}
		System.out.println(sumtime);
	}

}
