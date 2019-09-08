package test03;

import java.util.Scanner;

/**
 * 二十四点问题
 *<p>Title:Main.java</p>
 *<p>Description:
 *	计算表达式，算出计算结果与24进行比较，符合的话则输出true
 *</p>
 * @author YBN
 * @date: 2019年9月8日
 */
public class Main {
	
	public static void main(String[] args) {
		//从标准输入读入数据
		Scanner sc = new Scanner(System.in);
		//第一行输入一个整数n
		int n = sc.nextInt();
		//从2到n+1行每行输入一个7位长度的字符串
		//定义一个字符串数组，用来存放每行输入的字符串,大小为n
		String[] s = new String[n];
		for(int i = 0;i < n; i++) {
			s[i] = sc.next();
		}
		for(int j = 0;j < n;j++) {
			judge(s[j]);
		}
	}

	private static void judge(String s) {
		//传过来的字符串长度为7
		//设置索引位置index
		int index = 0;
		//读取的单个字符
		char ch = ' ';
		//定义循环条件
		boolean loop = true;
		//定义两个栈，数字栈和符号栈
		int[] numStack = new int[10];
		char[] operStack = new char[10];
		//定义两栈的top索引
		int top1 = -1;
		int top2 = -1;
		//定义两个数的运算与结果
		int num1 = 0;
		int num2 = 0;
		int sum = 0;
		while(loop) {
			ch = s.charAt(index);
			//如果为操作数的话，进入
			if(isOper(ch)) {
				if(isEmpty(operStack, top2)) {
					operStack[++top2] = ch;
					
				}else {
					//如果不空，则判断与栈顶元素的优先级比较
					int priority = priority(ch, top2, operStack);
					switch (priority) {
					case 0:
						num2 = numStack[top1];
						num1 = numStack[--top1];
						sum = total(num1, num2, operStack[top2]);
						numStack[top1] = sum;
						operStack[top2] = ch;
						break;
					case 1:
						operStack[++top2] = ch;
						break;
					default:
						break;
					}
				}
			}else {		//如果不是则为数字，直接入栈
				//判断栈空和栈满,为true表示可以插入数据
				if(isEmpty(numStack, top1)) {
					//char为字符，对应ASCII码表应减去48；
					numStack[++top1] = ch-48;
				}else {
					System.out.println("栈满");
				}
			}
			index++;
			if(index >= s.length()) {
				loop = false;
			}
		}
		//计算栈
		/*for(int i:numStack) {
			System.out.println(i);
		}
		for(char c:operStack) {
			System.out.println(c);
		}*/
		sum = 0;
		while(top1 != 0) {
			num2 = numStack[top1];
			num1 = numStack[--top1];
			numStack[top1] = total(num1, num2, operStack[top2--]);
		}
		if(numStack[0]==24) {
			System.out.println("Yes");
		}else {
			System.out.println("No");
		}
	}
	//是否为操作数
	public static boolean isOper(char c) {
		if(c == '+' || c == '-' || c == '*' || c == '/') {
			return true;
		}else {
			return false;
		}
	}
	//判断数子栈空？
	public static boolean isEmpty(int[] num,int top) {
		if(top>num.length) {
			return false;
		}else {
			return true;
		}
	}
	//判断operation栈是否空？
	public static boolean isEmpty(char[] c,int top) {
		if(top==-1) {
			return true;
		}else {
			return false;
		}
	}
	//判断优先级，为0则低，为1则高
	public static int priority(char ch,int top2,char[] oper) {
		switch (ch) {
		case '*':
		case '/':
		case '+':
		case '-':
			if(oper[top2] == '*' || oper[top2] == '/') {
				return 0;
			}else {
				return 1;
			}
		default:
			throw new RuntimeException("操作数错误");
		}
	}
	//计算结果
	public static int total(int num1,int num2,char op) {
		switch (op) {
		case '+':
			return num1+num2;
		case '-':
			return num1-num2;
		case '*':
			return num1*num2;
		case '/':
			return num1/num2;
		default:
			return 0;
		}
	}
}
