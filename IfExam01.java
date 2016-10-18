class IfExam01 {
	public static void main(String[] args) {
	
		int i = (int)(Math.random()*10)+1;

		// if문
		if(i % 2 == 0) { 
			System.out.println(i + "은/는 짝수입니다."); // 2의 배수(짝수)
		} else {
			System.out.println(i + "은/는 홀수입니다.");
		} 

		// 삼항연산자
		String result = (i%2 == 0) ? "짝수" : "홀수"; 
		System.out.println(i + "은/는 " + result);
	}
}