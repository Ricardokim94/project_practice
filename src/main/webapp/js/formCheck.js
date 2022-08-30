/**
 * 	2022. 06. 02
	작성자 : 김창목
	내용 : 회원가입 폼체크
 */
 
 	function idCheck(){
		//아이디값이 없는 경우 아이디를 입력하세요 경고메세지주고,커서를 id입력란에 포커스 나타나도록
		var id = document.forms["memRegForm"]["id"].value;
		
		var x = new XMLHttpRequest();
		
		//요청하는것(1번째는 방식 / 2번째는 url /3번째는 비동기식 요청)
		x.open("get","/idDoubleCheck.do?id=" + id , true);
		x.send();

		x.onreadystatechange = function(){ //상태를 수시로 감지하는 거임
			
			var msg = document.getElementById("idCheckMsg");
			
			
			// === : 타입까지 비교하는것
			if(x.readyState === 4 && x.status === 200){ 	
				console.log("ok");
				var rsp = x.responseText.trim();
				document.getElementById("isIdCheck").value = rsp; //문자 앞뒤공백삭제					
				
				if(rsp == "0"){
					msg.innerText ="사용가능한 아이디 입니다";
				}else{
					msg.innerText = "중복된 아이디 입니다";
				}
			}else {
				console.log("서버에러(403,404)");
				//오류발생
			}
		};
	}
 
	function formCheck() {
		
 		//비밀번호 체크
		var pw = document.forms["memRegForm"]["pw"].value;
		//alert(pw);
		if(pw.length < 3){
			alert("적어도 3자리는 입력을 하세요*^^*");
			return false;
		} 
		//이름체크
		var name = document.forms["memRegForm"]["name"].value;
		if(pw.length < 2 ){
			alert("두글자를 안쓴 당신은 ()입니다");
			//document.getElementById("msg").innerHTML = "이름을입력하세요.";
			return false;
		} 
		//성별체크
		var gender = document.forms["memRegForm"]["gender"].value;
		if(gender == "" ){
			alert("성별체크를 안한 당신 당신은 ()입니다");
			return false;
		}  
		
		//취미체크
		
		var hobby_length= document.forms["memRegForm"]["hobby"].length;
		/* if(hobby_length < 1);
			return false; */
		/* var z =0; */
		for(var i =0; i< hobby_length; i++){
			if(document.forms["memRegForm"]["hobby"][i].checked){
				/* alert((i+1) + "번째 취미가 선택되었음"); */
				console.log(i + "번째 취미가 선택됨");
		/* 		z++;	 */	
				break;
			}
		}
		if(i == hobby_length){
			return false;
		}
		/* if(z ===0){
			alert("취미를 선택하지 않은 당신 거짓말쟁이군요");
			return false;
		} */
	} 
	
	
	
	function inputDomain() {
		console.log("도메인 선택함")
		var sel = document.forms["memRegForm"]["selDomain"].value;
		console.log("선택옵션값 :" + sel);
		 document.forms["memRegForm"]["domain"].value = sel;
		
		 if(sel != ""){
			 document.forms["memRegForm"]["domain"].readOnly = true;
			 document.forms["memRegForm"]["domain"].style.backgroundColor = 'lightgray';
		 } else {
			 document.forms["memRegForm"]["domain"].readOnly = false;
			 document.forms["memRegForm"]["domain"].focus();
			 document.forms["memRegForm"]["domain"].style.backgroundColor = 'white';
		 }
		 
	}
	 