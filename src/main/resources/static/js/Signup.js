/**
 * 
 */

// spring security를 사용하기에 ajax의 post 통신을 하기 위해 함께 넘기는 데이터
var header = $("meta[name='_csrf_header']").attr('content');
var token = $("meta[name='_csrf']").attr('content');

// Input Tag Element
const usernameTag = document.getElementById('username')
const nicknameTag = document.getElementById('nickname')
const emailTag = document.getElementById('email')
const passwordTag = document.getElementById('password')
const passwordCheckTag = document.getElementById('password2')

// id, 닉네임, 비밀번호, 이메일 입력칸을 잘 입력되었는지 확인하는 변수
// 모든 변수가 true여야 최종 진행 가능
var IdCheck = false;
var passwordCheck = false;
var nicknameCheck = false;
var emailCheck = false;

// id, 비밀번호, 닉네임, 이메일 정규표현식
// 영어와 숫자로 이루어진 6~20 글자의 정규식
var IdRegex = /[A-z0-9]{6,20}/;
// 영어, 숫자, 특수문자 ( !@#$%^&*()_+ ) 로 이루어진 8~20글자의 정규식
var passwordRegex = /[A-z\d!@#$%^&*()_+]{8,20}/;
// 공백으로 시작하 (거나 끝나)지 않으 면서
// 한글, 영어, 숫자, 공백으로 이루어진 3~20글자의 정규식
var nicknameRegex = /(?!\s)[가-힣A-z0-9\s]{2,19}((?!\s)[가-힣A-z0-9])/;
var emailRegex = /[A-z0-9]+@[a-zA-Z]+\.[A-z-.]+/;

// 값이 입력되는 input event로는 유효성 검사만 진행 할 예정
// 값이 바뀌는 change event로 중복검사를 진행 할 예정
usernameTag.addEventListener('input', function(e){
	// console.log(e)
	// console.log(e.target.value)
	// console.log(typeof e.target.value)
	if(validateCheck("username", e.target.value) == true){
		duplicateCheck("username", e.target.value)
	}
})

passwordTag.addEventListener('input', function(e){
	if(validateCheck("password", e.target.value) == true){
		duplicatePassword(e.target.value, $('#password2').val())
	}
})

passwordCheckTag.addEventListener('input', function(e){
	if(validateCheck("passwordCheck", e.target.value) == true){
		duplicatePassword($('#password').val() , e.target.value)
	}
})

nicknameTag.addEventListener('input', function(e){
	if(validateCheck("nickname", e.target.value) == true){
		duplicateCheck("nickname", e.target.value)
	}
})

emailTag.addEventListener('input', function(e){
	if(validateCheck("email", e.target.value) == true){
		duplicateCheck("email", e.target.value)
	}
})

/**
 * 정규식에 맞게 작성되었는지 비교하는 함수
 * @param {string} column 유저가 입력한 태그의 이름
 * @param {string} inputValue 유저가 입력한 값
 * @returns 맞게 작성되었으면 true, 틀리면 false를 반환
 */
function validateCheck(column, inputValue){
	var rule = ""; // 정규식
	var checkValue = ""; // 정규식 비교 할 대상
	var errorTag = ""; // 문제시 수정 할 에러 태그
	var invalidComment = ""; // 유효성 문제시 언급 할 문구
	var successComment = ""; // 사용 가능 할 때 언급 할 문구
	
	// console.log(column)
	// console.log(inputValue)
	
	// 이런 작성 방법도 존재
	// rule = (column == "username") ? IdRegex :
	// 	(column == "password") ? passwordRegex :
	// 	(column == "nickname") ? nicknameRegex :
	// 	(column == "email") ? emailRegex :
	// 	"";
	
	if(column == "username"){
		rule = IdRegex;
		errorTag = $("#IdError");
		invalidComment = '<div id="IdError" class="error">'
							+ '6~20자의 영문, 숫자로 작성해주세요.'
						+ '</div>';
		successComment = '<div id="IdError" class="error"></div>';
	} else if (column == "password"){
		rule = passwordRegex;
		errorTag = $("#PasswordError");
		invalidComment = '<div id="PasswordError" class="error">'
							+ '8~20자의 영문, 숫자, 특수문자 !@#$%^&*()_+ 로 작성해주세요.'
						+ '</div>';
		successComment = '<div id="PasswordError" class="error"></div>';
	} else if (column == "passwordCheck"){
		rule = passwordRegex;
		errorTag = $("#PasswordCheckError");
		invalidComment = '<div id="PasswordCheckError" class="error">'
							+ '8~20자의 영문, 숫자, 특수문자 !@#$%^&*()_+ 로 작성해주세요.'
						+ '</div>';
		successComment = '<div id="PasswordCheckError" class="error"></div>';
	} else if (column == "nickname"){
		rule = nicknameRegex;
		errorTag = $("#NicknameError");
		invalidComment = '<div id="NicknameError" class="error">'
							+ '3~20자의 한글, 영문, 숫자로 작성해주세요.(띄어쓰기 가능)'
						+ '</div>';
		successComment = '<div id="NicknameError" class="error"></div>';
	} else if (column == "email"){
		rule = emailRegex;
		errorTag = $("#EmailError");
		invalidComment = '<div id="EmailError" class="error">'
							+ '이메일 형식으로 작성해주세요'
						+ '</div>';
		successComment = '<div id="EmailError" class="error"></div>';
	}
	
	try{
		checkValue = inputValue.match(rule)[0];
	} catch{
		console.log("정규식에 맞는 단어를 찾을수없음.");
	}
	// console.log("checkValue : " ,checkValue);
	
	// 값이 정규식에 맞지 않거나 찾은 값이 공백일경우 false를 반환
	if (checkValue != inputValue || checkValue == ""){
		errorTag.replaceWith(invalidComment);
		
		if(column == "username"){
			IdCheck = false;
		} else if (column == "password"){
			passwordCheck = false;
		} else if (column == "passwordCheck"){
			passwordCheck = false;
		} else if (column == "nickname"){
			nicknameCheck = false;
		} else if (column == "email"){
			emailCheck = false;
		}
		return false;
		
	} else if(checkValue == inputValue){
		// 정규식에 맞는 값일경우 true를 반환
		errorTag.replaceWith(successComment);
		return true;
	}
}
 
/**
 * 중복하는 데이터가 있는지 확인하는 함수
 * 중복하면 true 없으면 false
 */  
function duplicateCheck(column, inputValue){
	
	var returnResult = true;
	var errorTag = ""; // 문제시 수정 할 에러 태그
	var duplicatedComment = ""; // 중복될때 언급 할 문구
	var successComment = ""; // 사용 가능 할 때 언급 할 문구
	
	if(column == "username"){
		errorTag = $("#IdError");
		duplicatedComment = '<div id="IdError" class="error">'
								+ '이미 사용중인 아이디입니다.'
							+ '</div>';
		successComment = '<div id="IdError" class="error"></div>';
		permitCheck = IdCheck;
	} else if (column == "nickname"){
		errorTag = $("#NicknameError");
		duplicatedComment = '<div id="NicknameError" class="error">'
								+ '이미 사용중인 닉네임입니다.'
							+ '</div>';
		successComment = '<div id="NicknameError" class="error"></div>';
		permitCheck = nicknameCheck;
	} else if (column == "email"){
		errorTag = $("#EmailError");
		duplicatedComment = '<div id="EmailError" class="error">'
								+ '이미 사용중인 이메일입니다.'
							+ '</div>';
		successComment = '<div id="EmailError" class="error"></div>';
		permitCheck = emailCheck;
	}
	
	var checkDTO = {
		"column" : column
		, "data" : inputValue
	}
	
	$.ajax({
	    type : 'POST'           // 타입 (get, post, put 등등)
	    , url : '/user/duplicate/check'           // 요청할 서버url
	    , async : false            // 비동기화 여부 (default : true)
	    , data : checkDTO  // 보낼 데이터 (Object , String, Array)
	    , beforeSend: function(xhr){
			// spring security를 사용할때
			// header에 token값을 함께 넘겨줘야 에러가 발생하지 않음
	        xhr.setRequestHeader(header, token);
	    }
	    , success : function(result) { // 결과 성공 콜백함수
	        returnResult = result;
	    },
	})
	
	// 중복된 값이 있는지 확인
	if(returnResult == true){
		// 중복될때
		errorTag.replaceWith(duplicatedComment);
		
		if(column == "username"){
			IdCheck = false;
		} else if (column == "nickname"){
			nicknameCheck = false;
		} else if (column == "email"){
			emailCheck = false;
		}
		return returnResult;
	} else{
		// 사용가능할때
		errorTag.replaceWith(successComment);
		
		if(column == "username"){
			IdCheck = true;
		} else if (column == "nickname"){
			nicknameCheck = true;
		} else if (column == "email"){
			emailCheck = true;
		}
		return returnResult;
	}
}

// 비밀번호 중복검사
function duplicatePassword(password, password2){
	
	var errorTag = $("#PasswordCheckError");
	var passwordErrorComment = '<div id="PasswordCheckError" class="error">'
							+ '비밀번호가 일치하지 않습니다.'
						+ '</div>';
	var successComment = '<div id="PasswordCheckError" class="error"></div>';
	
	// 비밀번호가 두개가 다르다면 false를 반환
	if(password != password2){
		errorTag.replaceWith(passwordErrorComment);
		passwordCheck = false;
		return false;
	} else if(password == password2){
		// 비밀번호가 두개가 일치한다면 true를 반환
		errorTag.replaceWith(successComment);
		passwordCheck = true;
		return true;
	}
}

function passSignup(){
	
	if(IdCheck == false){
		alert("아이디를 확인해주세요");
		return false;
	} else if(passwordCheck == false){
		alert("비밀번호를 확인해주세요");
		return false;
	} else if(nicknameCheck == false){
		alert("닉네임을 확인해주세요");
		return false;
	} else if(emailCheck == false){
		alert("이메일을 확인해주세요");
		return false;
	} else if(duplicateCheck("username", $('#username').val()) == true){
		alert("이미 가입되어있는 아이디입니다.\r아이디를 바꾸시거나 새로고침을 부탁드립니다.");
		return false;
	} else if(duplicateCheck("nickname", $('#nickname').val()) == true){
		alert("이미 가입되어있는 닉네임입니다.\r닉네임을 바꾸시거나 새로고침을 부탁드립니다.");
		return false;
	} else if(duplicateCheck("email", $('#email').val()) == true){
		alert("이미 가입되어있는 이메일입니다.\r이메일을 바꾸시거나 새로고침을 부탁드립니다.");
		return false;
	} else{
		return true;
	}
}