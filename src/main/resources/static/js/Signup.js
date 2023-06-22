/**
 * 
 */

var header = $("meta[name='_csrf_header']").attr('content');
var token = $("meta[name='_csrf']").attr('content');
 
const usernameTag = document.getElementById('username')
const nicknameTag = document.getElementById('nickname')
const emailTag = document.getElementById('email')
usernameTag .addEventListener('change', function(e){
	console.log(e)
	console.log(e.target.value)
	console.log(typeof e.target.value)
	duplicateCheck("username", e.target.value)
})

nicknameTag.addEventListener('change', function(e){
	console.log(e)
	console.log(e.target.value)
	console.log(typeof e.target.value)
	duplicateCheck("nickname", e.target.value)
})

emailTag.addEventListener('change', function(e){
	console.log(e)
	console.log(e.target.value)
	console.log(typeof e.target.value)
	duplicateCheck("nickname", e.target.value)
})
 
function duplicateCheck(column, inputValue){
	console.log(column);
	console.log(inputValue);
	
	var checkDTO = {
		"column" : column
		, "data" : inputValue
	}
	
	$.ajax({
    type : 'POST',           // 타입 (get, post, put 등등)
    url : '/user/duplicate/check'           // 요청할 서버url
    // async : true,            // 비동기화 여부 (default : true)
    // headers : {              // Http header
    //   "Content-Type" : "application/json",
    //   "X-HTTP-Method-Override" : "POST"
    // },
    // dataType : 'text',       // 데이터 타입 (html, xml, json, text 등등)
    , data : checkDTO  // 보낼 데이터 (Object , String, Array)
    , beforeSend: function(xhr){
        xhr.setRequestHeader(header, token);
    }
    , success : function(result) { // 결과 성공 콜백함수
        console.log(result);
    },
    // error : function(request, status, error) { // 결과 에러 콜백함수
    //     console.log(error)
    // }
	})
}