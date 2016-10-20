class CastingExam{
	public static void main(String []args){
		byte b1;
		byte b2,b3; 
		int i1=7;
		System.out.println("int i1="+i1); 
		System.out.println("int i1="+i1); 
		System.out.println("int i1="+i1); 

		b1=(byte)i1 ; //int를 byte에 저장X => Casting 필요
		System.out.println("byte b1="+b1);

		b2=20;
		System.out.println("byte b2="+b2);
		
	   	b3=(byte)(b2+b1); //연산시 최소한 4바이트 공간 필요 => int형

		System.out.println("byte b3="+b3);
		
		long lo=56897L;
		System.out.println("long lo="+lo);

		int i4=(int)lo+i1; // = (int)(lo+i1);  
		System.out.println("int i4="+i4);
		
		boolean boo;
		boo=false; //boolean은 반드시 true/false만 
		
		System.out.println("boolean boo="+boo);

		double d=4.67e-3; //e : 지수표현 0.00467
		System.out.println("double d="+d);

		float f1=lo; /*long은 8바이트 float은 4바이트이지만 
		실수타입이 정수타입보다 저장공간을 더 크게 차지 하므로
		정수형 < 실수형 */
		System.out.println("float f1="+f1);
				
		
	    float f2=(float)d;
		System.out.println("float f2="+f2);

		char c1,c2, c3;
		c1='\u0167';
		System.out.println("char c1="+c1);

		b2=(byte)c1; 
		System.out.println("byte b2="+b2);
		
		c2='가';
		System.out.println("char c2="+c2);
		
		b2=(byte)c1;
		System.out.println("byte b2="+b2);
		
		c3=(char)(c2+2); //'가'의 코드값 + 2 => int형
		System.out.println("char c3="+c3);
	}

}